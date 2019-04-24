package com.hcl.cloud.product.request;

/**
 * @author BrijendraK
 *
 */
public class InventoryQuantityReq {

    private String skuCode = null;
    private Integer quantity = 0;

    public String getSkuCode() {
        return skuCode;
    }
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}