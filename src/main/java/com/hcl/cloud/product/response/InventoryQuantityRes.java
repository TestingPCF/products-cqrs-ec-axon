package com.hcl.cloud.product.response;

/**
 * @author Entity class for Inventory Response
 *
 */
public class InventoryQuantityRes {

    // skuCode
    private String skuCode = null;
    // quantity
    private Integer quantity = 0;
    // activeStatus
    private boolean activeStatus;
    // inStock
    private boolean inStock;

    /**
     * @return the String
     */
    public String getSkuCode() {
        return skuCode;
    }

    /**
     * @param skuCode to set
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
     * @param quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the boolean
     */
    public boolean isActiveStatus() {
        return activeStatus;
    }

    /**
     * @param activeStatus to set
     */
    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    /**
     * @return the boolean
     */
    public boolean isInStock() {
        return inStock;
    }

    /**
     * @param inStock to set
     */
    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}