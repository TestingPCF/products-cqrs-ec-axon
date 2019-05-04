
package com.hcl.cloud.product.service.impl;

import static com.hcl.cloud.product.constants.ProductConstants.ALREADY;
import static com.hcl.cloud.product.constants.ProductConstants.FAILED;
import static com.hcl.cloud.product.constants.ProductConstants.SUCCESS;
import static com.hcl.cloud.product.constants.ProductConstants.INVENTORY_URL;

import java.net.URI;
import java.net.URISyntaxException;
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

import com.hcl.cloud.product.cache.ProductCacheManager;
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
 * @author Brijendra and Kapil ProductServiceImpl
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    private ProductCacheManager productCacheManager;

    /**
     * @param productCacheManager to set
     */
    public void setProductCacheManager(ProductCacheManager productCacheManager) {
        this.productCacheManager = productCacheManager;
    }

    ProductServiceImpl() {
    }

    /**
     * @param productCacheManager
     */
    @Autowired
    public ProductServiceImpl(ProductCacheManager productCacheManager) {
        this.productCacheManager = productCacheManager;
    }

    @Autowired
    HystrixCommandPropertyResource hystrixCommandProp;
    static Logger log = LoggerFactory.getLogger(ProductController.class);

    /**
     * This method is used for create the product.
     * 
     * @param accessToken
     * @param createproductReq
     * @return CreateproductReq
     * @throws ProductException
     * @throws URISyntaxException
     */
    @Override
    @HystrixCommand(fallbackMethod = "createProductFallback", commandKey = "CREATEPRODUCTCommand", threadPoolKey = "PRODUCTThreadPool")
    public CreateproductReq createProduct(CreateproductReq createproductReq, Environment env, TransactionBean txBean)
            throws ProductException {

        log.info("Product detail insetion DB call Start");

        Optional<CreateproductReq> product = repository.findById(createproductReq.getSkuCode());

        if (!product.isPresent()) {

            createproductReq = repository.save(createproductReq);

            // inventory call for initial product quantity as 0.
            boolean inventoryCallStatus = inventoryCall(createproductReq, env, txBean);
            if (inventoryCallStatus == true) {
                createproductReq.setStatus(SUCCESS);
            }
        } else {
            createproductReq.setStatus(env.getProperty(ALREADY));
        }
        log.info("Product created successfully");

        log.info("Product detail insetion DB call End");
        return createproductReq;
    }

    /**
     * This method is used for soft delete product.
     * 
     * @param accessToken
     * @param deleteproductReq
     * @return CreateproductReq
     * @throws ProductException
     */
    @Override
    @HystrixCommand(fallbackMethod = "deleteProductFallback", commandKey = "DELETEPRODUCTCommand", threadPoolKey = "PRODUCTThreadPool")
    public CreateproductReq deleteProduct(DeleteproductReq deleteproductReq, Environment env) throws ProductException {
        CreateproductReq createproductReq = new CreateproductReq();
        log.info("Product detail delete DB call Start");

        createproductReq.setSkuCode(deleteproductReq.getSkuCode());
        Optional<CreateproductReq> product = repository.findById(deleteproductReq.getSkuCode());
        if (product.isPresent()) {
            if (product.get().isIs_deleted() == false) {
                product.get().setIs_deleted(true);
                createproductReq = repository.save(product.get());
                createproductReq.setStatus(SUCCESS);
                // After successful deletion of product remove it from cache
                productCacheManager.removeProductFromCache(createproductReq);
            } else {
                createproductReq.setStatus(ALREADY);
            }

        } else {
            createproductReq.setStatus(FAILED);
        }

        log.info("Product deleted successfully");

        log.info("Product detail delete DB call End");
        return createproductReq;
    }

    /**
     * This method is used for update the product.
     * 
     * @param accessToken
     * @param updateproductReq
     * @return CreateproductReq
     */
    @Override
    @HystrixCommand(fallbackMethod = "updateProductFallback", commandKey = "UPDATEPRODUCTCommand", threadPoolKey = "PRODUCTThreadPool")
    public CreateproductReq updateProduct(UpdateproductReq updateproductReq, Environment env) throws ProductException {
        log.info("Product detail update DB call Start");
        CreateproductReq createproductReq = new CreateproductReq();

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
            productCacheManager.cacheProductDetails(createproductReq);
            createproductReq.setStatus(SUCCESS);
        } else {
            createproductReq.setStatus(ALREADY);
        }

        log.info("Product detail update DB call end");
        return createproductReq;

    }

    /**
     * This method is used for view single entry of active product based on skuCode.
     * 
     * @param accessToken
     * @param skuCode
     * @return List<CreateproductReq>
     * @throws ProductException
     */
    @Override
    @HystrixCommand(fallbackMethod = "viewproductbyskuCodeFallback", commandKey = "VIEWPRODUCTBYSKUCODECommand", threadPoolKey = "PRODUCTThreadPool")
    public List<CreateproductReq> viewproductbyskuCode(String skuCode, Environment env) throws ProductException {
        log.info("View ProductBySkuCode DB call start");
        List<CreateproductReq> productList = new ArrayList<CreateproductReq>();
        if (null != skuCode) {

            // try to get product from cache
            CreateproductReq cachedProduct = productCacheManager.getProductFromCache(skuCode);
            if (cachedProduct != null) {
                log.info("Found product from cache. Returning !!!!");
                productList.add(cachedProduct);
            } else {
                log.info("Could not found product from cahce. Going to fetch from DB now !!!!");
                Optional<CreateproductReq> product = repository.findById(skuCode);
                if (product.isPresent()) {
                    productList.add(product.get());
                }
            }

        }

        log.info("View ProductBySkuCode DB call end");
        return productList;
    }

    /**
     * This method is used for view all active products.
     * 
     * @param accessToken
     * @return List<CreateproductReq>
     * @throws ProductException
     */
    @Override
    @HystrixCommand(fallbackMethod = "viewProductsFallback", commandKey = "VIEWPRODUCTSCommand", threadPoolKey = "PRODUCTThreadPool")
    public List<CreateproductReq> viewProducts(Environment env) throws ProductException {
        log.info("View Products DB call start");
        List<CreateproductReq> productList = new ArrayList<CreateproductReq>();

        productList = repository.findAll();

        log.info("View Products DB call end");

        return productList;
    }

    public boolean inventoryCall(CreateproductReq createproductReq, Environment env, TransactionBean txBean)
            throws ProductException {
        RestTemplate restTemplate = new RestTemplate();
        URI uri = null;
        final String url = INVENTORY_URL;
        try {
            uri = new URI(url);
        } catch (URISyntaxException ex) {
            throw new ProductException(ex.getMessage());
        }
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
            log.info("Product created successfully putting into cache");
            // If product created successfully put it in cache for future use
            productCacheManager.cacheProductDetails(createproductReq);
            return true;
        }
        return false;
    }

    public void setHystrixCommandProp(HystrixCommandPropertyResource hystrixCommandProp) {
        this.hystrixCommandProp = hystrixCommandProp;
    }

    public CreateproductReq createProductFallback(CreateproductReq createproductReq, Environment env,
            TransactionBean txBean) throws ProductException {
        log.error("Exception occured during Product insertion moved to Hystrix fallback");
        throw new ProductException(env.getProperty("Create.fallback"));
    }

    public CreateproductReq deleteProductFallback(DeleteproductReq deleteproductReq, Environment env)
            throws ProductException {
        log.error("Exception occured during find Product moved to Hystrix fallback");
        throw new ProductException(env.getProperty("hystrix.fallback"));

    }

    public CreateproductReq updateProductFallback(UpdateproductReq updateproductReq, Environment env)
            throws ProductException {
        log.error("Exception occured during update Product moved to Hystrix fallback");
        throw new ProductException(env.getProperty("hystrix.fallback"));

    }

    public List<CreateproductReq> viewproductbyskuCodeFallback(String skuCode, Environment env)
            throws ProductException {
        log.error("Exception occured during view Product moved to Hystrix fallback");
        throw new ProductException(env.getProperty("hystrix.fallback"));
    }

    public List<CreateproductReq> viewProductsFallback(Environment env) throws ProductException {
        log.error("Exception occured during view Products moved to Hystrix fallback");
        throw new ProductException(env.getProperty("hystrix.fallback"));
    }

    /*
     * public static void setLog(Logger log) { ProductServiceImpl.log = log; }
     */
}
