package com.hcl.cloud.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * This is configuration loader class. Which pulls data at runtime.
 * @author shikhar.a
 */
@Configuration
@RefreshScope
public class ConfigLoader {

    private String exchangeName;

    private String routingKey;

    private String queueSpecificName;

    private String productCreateSuccessMsg;

    private String productAlreadyExistMsg;

    private String productAlreadyDeletedMsg;

    private String productDeleteSuccessMsg;

    private String productNotExistMsg;

    private String productUpdateSuccessMsg;

    public String getExchangeName() {
        return exchangeName;
    }

    @Value("${product.mq.exchange}")
    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    @Value("${product.mq.routing.key}")
    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getQueueSpecificName() {
        return queueSpecificName;
    }

    @Value("${product.mq.queue.name}")
    public void setQueueSpecificName(String queueSpecificName) {
        this.queueSpecificName = queueSpecificName;
    }

    public String getProductCreateSuccessMsg() {
        return productCreateSuccessMsg;
    }

    @Value("${product.create.success.msg}")
    public void setProductCreateSuccessMsg(String productCreateSuccessMsg) {
        this.productCreateSuccessMsg = productCreateSuccessMsg;
    }

    public String getProductAlreadyExistMsg() {
        return productAlreadyExistMsg;
    }

    @Value("${product.alread.exist.msg}")
    public void setProductAlreadyExistMsg(String productAlreadyExistMsg) {
        this.productAlreadyExistMsg = productAlreadyExistMsg;
    }

    public String getProductAlreadyDeletedMsg() {
        return productAlreadyDeletedMsg;
    }

    @Value("${product.already.deleted}")
    public void setProductAlreadyDeletedMsg(String productAlreadyDeletedMsg) {
        this.productAlreadyDeletedMsg = productAlreadyDeletedMsg;
    }

    public String getProductDeleteSuccessMsg() {
        return productDeleteSuccessMsg;
    }

    @Value("${product.delete.successmsg}")
    public void setProductDeleteSuccessMsg(String productDeleteSuccessMsg) {
        this.productDeleteSuccessMsg = productDeleteSuccessMsg;
    }

    public String getProductNotExistMsg() {
        return productNotExistMsg;
    }

    @Value("${product.notexistmsg}")
    public void setProductNotExistMsg(String productNotExistMsg) {
        this.productNotExistMsg = productNotExistMsg;
    }

    public String getProductUpdateSuccessMsg() {
        return productUpdateSuccessMsg;
    }

    @Value("${product.update.successmsg}")
    public void setProductUpdateSuccessMsg(String productUpdateSuccessMsg) {
        this.productUpdateSuccessMsg = productUpdateSuccessMsg;
    }
}
