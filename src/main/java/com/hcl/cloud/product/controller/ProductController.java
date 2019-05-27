package com.hcl.cloud.product.controller;

import static com.hcl.cloud.product.constants.ProductConstants.ACCESS_TOKEN;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.cloud.product.config.ConfigLoader;
import com.hcl.cloud.product.constants.ProductConstants;
import com.hcl.cloud.product.datatranslator.CreateProductResponseTranslator;
import com.hcl.cloud.product.datatranslator.DeleteProductResponseTranslator;
import com.hcl.cloud.product.datatranslator.UpdateProductResponseTranslator;
import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.request.DeleteproductReq;
import com.hcl.cloud.product.request.UpdateproductReq;
import com.hcl.cloud.product.resources.TransactionBean;
import com.hcl.cloud.product.response.CreateproductRes;
import com.hcl.cloud.product.response.DeleteproductRes;
import com.hcl.cloud.product.response.UpdateproductRes;
import com.hcl.cloud.product.service.ProductService;

/**
 * 
 * @author Brijendra and Kapil ProductController
 */
@RefreshScope
@RestController
public class ProductController {

    @Autowired
    private ConfigLoader configLoader;
    @Autowired
    private ProductService productService;
    @Autowired
    Environment env;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${product.create.log}")
    private String createProductLog;

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
    public ResponseEntity<CreateproductRes> createProduct(@RequestHeader(value = ACCESS_TOKEN) String accessToken,
            @Valid @RequestBody CreateproductReq createproductReq) throws ProductException {

        log.info(createProductLog);
        CreateproductRes createproductRes = null;
        CreateProductResponseTranslator cprtrans = new CreateProductResponseTranslator();
        TransactionBean txBean = new TransactionBean();
        txBean.setAccessToken(accessToken);
        try {

            createproductReq = productService.createProduct(createproductReq, env, txBean);
            if (ProductConstants.SUCCESS.equals(createproductReq.getStatus())) {
                log.info("Product event is ready to publish for product:" + createproductReq.getProductName());
                rabbitTemplate.convertAndSend(configLoader.getExchangeName(),
                        configLoader.getRoutingKey(),
                        createproductReq);
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
    public ResponseEntity<DeleteproductRes> deleteProduct(@RequestHeader(value = ACCESS_TOKEN) String accessToken,
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
    public ResponseEntity<UpdateproductRes> updateProduct(@RequestHeader(value = ACCESS_TOKEN) String accessToken,
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

}