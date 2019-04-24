package com.hcl.cloud.product.request;

/**
 * @author BrijendraK
 *
 */
public class InventoryQuantityReq {

    private String skuCode = null;
    private Integer quantity = 0;
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
     * @return the Integer
     */
    public Integer getQuantity() {
        return quantity;
    }
    /**
     * @param quantity  to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}