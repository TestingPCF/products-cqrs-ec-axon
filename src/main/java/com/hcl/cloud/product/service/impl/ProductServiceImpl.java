
package com.hcl.cloud.product.service.impl;

import static com.hcl.cloud.product.constants.ProductConstants.ALREADY;
import static com.hcl.cloud.product.constants.ProductConstants.FAILED;
import static com.hcl.cloud.product.constants.ProductConstants.SUCCESS;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.hcl.cloud.product.controller.ProductController;
import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.repository.ProductRepository;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.request.DeleteproductReq;
import com.hcl.cloud.product.request.InventoryQuantityReq;
import com.hcl.cloud.product.request.UpdateproductReq;
import com.hcl.cloud.product.resources.HystrixCommandPropertyResource;
import com.hcl.cloud.product.resources.TransactionBean;
import com.hcl.cloud.product.response.InventoryQuantityRes;
import com.hcl.cloud.product.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 
 * @author BrijendraK
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	public void setRepository(ProductRepository repository) {
		this.repository = repository;
	}

	@Autowired
	HystrixCommandPropertyResource hystrixCommandProp;
	static Logger log = LoggerFactory.getLogger(ProductController.class);

	@Override
	@HystrixCommand(fallbackMethod = "createProductFallback", commandKey = "CREATEPRODUCTCommand", threadPoolKey = "PRODUCTThreadPool")
	public CreateproductReq createProduct(CreateproductReq createproductReq, Environment env, TransactionBean txBean)
			throws ProductException {

		log.info("Product detail insetion DB call Start");
		try {

			Optional<CreateproductReq> product = repository.findById(createproductReq.getSkuCode());

			if (!product.isPresent()) {
				createproductReq = repository.save(createproductReq);

				// inventory call for initial product quantity as 0.

				RestTemplate restTemplate = new RestTemplate();
				final String url = "http://Inventory-MS-soppy-weathering.apps.cnpsandbox.dryice01.in.hclcnlabs.com/inventory";
				URI uri = new URI(url);
				HttpHeaders requestHeaders = new HttpHeaders();
				requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
				requestHeaders.add("ACCESS_TOKEN", txBean.getAccessToken());
				InventoryQuantityReq inventory = new InventoryQuantityReq();
				inventory.setSkuCode(createproductReq.getSkuCode());
				inventory.setQuantity(0);
				HttpEntity<InventoryQuantityReq> requestEntity = new HttpEntity<>(inventory, requestHeaders);
				ResponseEntity<InventoryQuantityRes> responseEntity = restTemplate.postForEntity(uri, requestEntity,
						InventoryQuantityRes.class);
				if (responseEntity != null) {
					createproductReq.setStatus(SUCCESS);
				}
			} else {
				createproductReq.setStatus(env.getProperty(ALREADY));
			}
			log.info("Product created successfully");
		} catch (Exception ex) {
			log.error("Error occured during Product creation");
			throw new ProductException(ex.getMessage());
		}
		log.info("Product detail insetion DB call End");
		return createproductReq;
	}

	@Override
	@HystrixCommand(fallbackMethod = "deleteProductFallback", commandKey = "DELETEPRODUCTCommand", threadPoolKey = "PRODUCTThreadPool")
	public CreateproductReq deleteProduct(DeleteproductReq deleteproductReq, Environment env) throws ProductException {
		CreateproductReq createproductReq = new CreateproductReq();
		log.info("Product detail delete DB call Start");
		try {

			createproductReq.setSkuCode(deleteproductReq.getSkuCode());
			Optional<CreateproductReq> product = repository.findById(deleteproductReq.getSkuCode());
			if (product.isPresent()) {
				if (product.get().isIs_deleted() == false) {
					product.get().setIs_deleted(true);
					createproductReq = repository.save(product.get());
					createproductReq.setStatus(SUCCESS);
				} else {
					createproductReq.setStatus(ALREADY);
				}

			} else {
				createproductReq.setStatus(FAILED);
			}

			log.info("Product deleted successfully");
		} catch (Exception ex) {
			log.error("Error occured during Product deletion");
			throw new ProductException(ex.getMessage());
		}
		log.info("Product detail delete DB call End");
		return createproductReq;
	}

	@Override
	public CreateproductReq updateProduct(UpdateproductReq updateproductReq, Environment env) throws ProductException {
		log.info("Product detail update DB call Start");
		CreateproductReq createproductReq = new CreateproductReq();
		try {

			Optional<CreateproductReq> product = repository.findById(updateproductReq.getSkuCode());

			if (product.isPresent()) {
				// product.get().setSkuCode(updateproductReq.getSkuCode());
				if (!StringUtils.isEmpty(updateproductReq.getProductName())) {
					product.get().setProductName(updateproductReq.getProductName());
				}
				if (!StringUtils.isEmpty(updateproductReq.getProductDescrition())) {
					product.get().setProductDescrition(updateproductReq.getProductDescrition());
				}
				if (!StringUtils.isEmpty(updateproductReq.getListPrice())) {
					product.get().setListPrice(updateproductReq.getListPrice());
				}
				if (!StringUtils.isEmpty(updateproductReq.getSalePrice())) {
					product.get().setSalePrice(updateproductReq.getSalePrice());
				}
				if (!StringUtils.isEmpty(updateproductReq.getCategory())) {
					product.get().setCategory(updateproductReq.getCategory());
				}
				product.get().setIs_deleted(updateproductReq.isIs_deleted());
				createproductReq = repository.save(product.get());
				createproductReq.setStatus(SUCCESS);
			} else {
				createproductReq.setStatus(ALREADY);
			}

		} catch (Exception ex) {
			log.error("Error occured during Product detail update DB");
			throw new ProductException(ex.getMessage());
		}

		log.info("Product detail update DB call end");
		return createproductReq;

	}

	@Override
	public List<CreateproductReq> viewproductbyskuCode(String skuCode, Environment env) throws ProductException {
		log.info("View ProductBySkuCode DB call start");
		List<CreateproductReq> productList = new ArrayList<CreateproductReq>();
		if (null != skuCode) {
			try {

				Optional<CreateproductReq> product = repository.findById(skuCode);
				if (product.isPresent()) {
					productList.add(product.get());
				}
			} catch (Exception e) {
				log.error("Error occured during view Product detail");
				throw new ProductException(e.getMessage());

			}
		}

		log.info("View ProductBySkuCode DB call end");
		return productList;
	}

	@Override
	public List<CreateproductReq> viewProducts(Environment env) throws ProductException {
		log.info("View Products DB call start");
		List<CreateproductReq> productList = new ArrayList<CreateproductReq>();

		try {

			productList = repository.findAll();

		} catch (Exception e) {
			log.error("Error occured during view Product detail");
			throw new ProductException(e.getMessage());

		}

		log.info("View Products DB call end");

		return productList;
	}

	/*
	 * public void setHystrixCommandProp(HystrixCommandPropertyResource
	 * hystrixCommandProp) { this.hystrixCommandProp = hystrixCommandProp; }
	 */

	public CreateproductReq createProductFallback(CreateproductReq createproductReq, Environment env,
			TransactionBean txBean) throws ProductException {
		log.error("Exception occured during Product insertion moved to Hystrix fallback");
		throw new ProductException(env.getProperty("hystrix.fallback"));
	}

	public CreateproductReq deleteProductFallback(DeleteproductReq deleteproductReq, Environment env)
			throws ProductException {
		log.error("Exception occured during find Product moved to Hystrix fallback");
		throw new ProductException(env.getProperty("hystrix.fallback"));

	}

	/*
	 * public static void setLog(Logger log) { ProductServiceImpl.log = log; }
	 */
}
