package com.hcl.cloud.product.controller;

import static com.hcl.cloud.product.constants.ProductConstants.ACCESS_TOKEN;
import static com.hcl.cloud.product.constants.ProductConstants.SKU_CODE;
import static com.hcl.cloud.product.constants.ProductConstants.VIEW_PRODUCT_BYSKUCODE_URI;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.cloud.product.config.RabbitmqConfigProduct;
import com.hcl.cloud.product.constants.ProductConstants;
import com.hcl.cloud.product.datatranslator.CreateProductResponseTranslator;
import com.hcl.cloud.product.datatranslator.DeleteProductResponseTranslator;
import com.hcl.cloud.product.datatranslator.UpdateProductResponseTranslator;
import com.hcl.cloud.product.datatranslator.ViewProductbySkuCodeResponseTranslator;
import com.hcl.cloud.product.datatranslator.ViewProductsResponseTranslator;
import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.request.DeleteproductReq;
import com.hcl.cloud.product.request.UpdateproductReq;
import com.hcl.cloud.product.resources.TransactionBean;
import com.hcl.cloud.product.response.CreateproductRes;
import com.hcl.cloud.product.response.DeleteproductRes;
import com.hcl.cloud.product.response.UpdateproductRes;
import com.hcl.cloud.product.response.ViewproductRes;
import com.hcl.cloud.product.service.ProductService;

/**
 * 
 * @author Brijendra and Kapil
 * ProductController
 */
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    Environment env;
    
    @Autowired
	private RabbitTemplate rabbitTemplate;

    public void setEnv(Environment env) {
        this.env = env;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    static Logger log = LoggerFactory.getLogger(ProductController.class);

    /**
     * This method is used for create the product.
     * 
     * @param accessToken
     * @param createproductReq
     * @return ResponseEntity
     * @throws ProductException
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateproductRes> createProduct(
            @RequestHeader(value = ACCESS_TOKEN) String accessToken,
            @Valid @RequestBody CreateproductReq createproductReq) throws ProductException {

        log.info("createProduct call start");
        CreateproductRes createproductRes = null;
        CreateProductResponseTranslator cprtrans = new CreateProductResponseTranslator();
        TransactionBean txBean = new TransactionBean();
        txBean.setAccessToken(accessToken);
        try {

            createproductReq = productService.createProduct(createproductReq, env, txBean);
            if(ProductConstants.SUCCESS.equals(createproductReq.getStatus())) {
            	log.info("Product event is ready to publish for product  "+createproductReq.getProductName());
            	rabbitTemplate.convertAndSend(RabbitmqConfigProduct.EXCHANGE_NAME,RabbitmqConfigProduct.ROUTING_KEY,createproductReq);
            }
            createproductRes = cprtrans.createproductresponsetranslator(createproductReq, env);
        } catch (Exception ex) {
            throw new ProductException(ex.getMessage());
        }

        log.info("createProduct call end");
        return ResponseEntity.ok().body(createproductRes);
    }

    /**
     * This method is used for soft delete product.
     * 
     * @param accessToken
     * @param deleteproductReq
     * @return ResponseEntity
     * @throws ProductException
     */

    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteproductRes> deleteProduct(
            @RequestHeader(value = ACCESS_TOKEN) String accessToken,
            @Valid @RequestBody DeleteproductReq deleteproductReq) throws ProductException {
        log.info("deleteProduct call start");
        CreateproductReq createproductReq = null;
        DeleteproductRes deleteproductRes = null;
        DeleteProductResponseTranslator dpt = new DeleteProductResponseTranslator();
        try {
            createproductReq = productService.deleteProduct(deleteproductReq, env);
            deleteproductRes = dpt.deleteproductresponseTranslator(createproductReq, env);
        } catch (Exception ex) {
            throw new ProductException(ex.getMessage());
        }

        log.info("deleteProduct call end");
        return ResponseEntity.ok().body(deleteproductRes);

    }

    /**
     * This method is used for update the product.
     * 
     * @param accessToken
     * @param updateproductReq
     * @return ResponseEntity
     * @throws ProductException 
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdateproductRes> updateProduct(
            @RequestHeader(value = ACCESS_TOKEN) String accessToken,
            @Valid @RequestBody UpdateproductReq updateproductReq) throws ProductException {
        log.info("updateProduct call start");
        CreateproductReq createproductReq = null;
        UpdateproductRes updateproductRes = null;
        UpdateProductResponseTranslator updateTranslator = new UpdateProductResponseTranslator();
        try {
            createproductReq = productService.updateProduct(updateproductReq, env);
            updateproductRes = updateTranslator.updateProductResponseTranslator(createproductReq, env);
        } catch (Exception ex) {
            throw new ProductException(ex.getMessage());
        }

        log.info("updateProduct call end");
        return ResponseEntity.ok().body(updateproductRes);

    }

    /**
     * This method is used for view single entry of active product based on skuCode.
     * 
     * @param accessToken
     * @param skuCode
     * @return ResponseEntity
     * @throws ProductException
     *
    @RequestMapping(method = RequestMethod.GET, value = VIEW_PRODUCT_BYSKUCODE_URI, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViewproductRes> viewProductBySkuCode(
            @RequestHeader(value = ACCESS_TOKEN, required = true) String accessToken,
            @PathVariable(SKU_CODE) String skuCode) throws ProductException {
        log.info("viewProductBySkuCode call start");
        ViewProductbySkuCodeResponseTranslator vpt = new ViewProductbySkuCodeResponseTranslator();
        ViewproductRes viewproductRes = null;
        try {
            if (!StringUtils.isEmpty(skuCode)) {
                List<CreateproductReq> pList = productService.viewproductbyskuCode(skuCode, env);
                viewproductRes = vpt.viewProductbySkuCodeResponseTranslator(pList, env);
            }
        } catch (Exception ex) {
            throw new ProductException(ex.getMessage());
        }
        log.info("viewProductBySkuCode call end");
        return ResponseEntity.ok().body(viewproductRes);

    } */

    /**
     * This method is used for view all active products.
     * 
     * @param accessToken
     * @return ResponseEntity
     * @throws ProductException
     *
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViewproductRes> viewProducts(
            @RequestHeader(value = ACCESS_TOKEN, required = true) String accessToken) throws ProductException {
        log.info("viewProducts call start"); 
        log.info("accessToken"+ accessToken);
        ViewProductsResponseTranslator vproductst = new ViewProductsResponseTranslator();
        ViewproductRes viewproductRes = null;

        List<CreateproductReq> pList = productService.viewProducts(env);
        viewproductRes = vproductst.viewProductsResponseTranslator(pList, env);
        log.info("viewProducts call end");
        return ResponseEntity.ok().body(viewproductRes);

    } */
}