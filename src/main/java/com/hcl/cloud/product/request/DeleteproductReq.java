package com.hcl.cloud.product.request;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author Brijendra and Kapil
 *
 */
public class DeleteproductReq {

    @NotNull
    private String skuCode = null;

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
}