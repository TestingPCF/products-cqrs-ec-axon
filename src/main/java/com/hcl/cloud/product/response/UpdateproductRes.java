package com.hcl.cloud.product.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author Entity class for Product Response
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateproductRes {

    private String skuCode = null;
    private String statusCode = null;
    private String status = null;
    /**
     * @return the String
     */
    public String getSkuCode() {
        return skuCode;
    }
    /**
     * @param skuCode  to set
     */
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    /**
     * @return the String
     */
    public String getStatusCode() {
        return statusCode;
    }
    /**
     * @param statusCode  to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    /**
     * @return the String
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status  to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}