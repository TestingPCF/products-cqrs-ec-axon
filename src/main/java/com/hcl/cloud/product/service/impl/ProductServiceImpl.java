
package com.hcl.cloud.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hcl.cloud.product.controller.ProductController;
import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.repository.ProductRepository;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.request.DeleteproductReq;
import com.hcl.cloud.product.request.UpdateproductReq;
import com.hcl.cloud.product.resources.HystrixCommandPropertyResource;
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

	@Autowired
	HystrixCommandPropertyResource hystrixCommandProp;
	static Logger log = LoggerFactory.getLogger(ProductController.class);

	@Override
	@HystrixCommand(fallbackMethod = "createProductFallback", commandKey = "CREATEPRODUCTCommand", threadPoolKey = "PRODUCTThreadPool")
	public CreateproductReq createProduct(CreateproductReq createproductReq, Environment env) throws ProductException {

		log.info("Product detail insetion DB call Start");
		try {

			Optional<CreateproductReq> product = repository.findById(createproductReq.getSkuCode());

			if (!product.isPresent()) {
				createproductReq = repository.save(createproductReq);
				createproductReq.setStatus(env.getProperty("success"));
			} else {
				createproductReq.setStatus(env.getProperty("failed"));
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
			if (product.isPresent() && product.get().isIs_deleted()==false) {
				product.get().setIs_deleted(true);
				createproductReq = repository.save(product.get());
				createproductReq.setStatus(env.getProperty("success"));
			} else {
				createproductReq.setStatus(env.getProperty("failed"));
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
				createproductReq.setSkuCode(updateproductReq.getSkuCode());
				createproductReq.setProductName(updateproductReq.getProductName());
				createproductReq.setProductDescrition(updateproductReq.getProductDescrition());
				createproductReq.setListPrice(updateproductReq.getListPrice());
				createproductReq.setSalePrice(updateproductReq.getSalePrice());
				createproductReq.setCategory(updateproductReq.getCategory());
				createproductReq = repository.save(createproductReq);
				createproductReq.setStatus(env.getProperty("success"));
			} else {
				createproductReq.setStatus(env.getProperty("failed"));
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

	public void setHystrixCommandProp(HystrixCommandPropertyResource hystrixCommandProp) {
		this.hystrixCommandProp = hystrixCommandProp;
	}

	public CreateproductReq createProductFallback(CreateproductReq createproductReq, Environment env)
			throws ProductException {
		log.error("Exception occured during Product insertion moved to Hystrix fallback");
		throw new ProductException(env.getProperty("hystrix.fallback"));
	}

	public CreateproductReq deleteProductFallback(DeleteproductReq deleteproductReq, Environment env)
			throws ProductException {
		log.error("Exception occured during find Product moved to Hystrix fallback");
		throw new ProductException(env.getProperty("hystrix.fallback"));

	}

}
