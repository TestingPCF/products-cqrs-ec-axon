package com.hcl.cloud.product.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
/**
 * 
 * @author Brijendra and Kapil
 *
 */
public class UpdateproductReq {

    /**.
     * skuCode
     */
    @Id
    @NotNull
    private String skuCode = null;

    /**.
     * productName
     */
    private String productName = null;
    /**.
     * salePrice
     */
    @Min(value = 1)
    private Integer salePrice;
    /**.
     * listPrice
     */
    @Min(value = 1)
    private Integer listPrice;
    /**.
     * productDescrition
     */
    private String productDescrition = null;
    /**.
     * category
     */
    private String category = null;
    /**.
     * is_deleted flag
     */
    private boolean is_deleted = false;
    /**.
     * @return the String
     */
    public String getSkuCode() {
        return skuCode;
    }
    /**
     * @param skuCode  to set
     */
    public void setSkuCode(final String skuCode) {
        this.skuCode = skuCode;
    }
    /**
     * @return the String
     */
    public String getProductName() {
        return productName;
    }
    /**
     * @param productName  to set
     */
    public void setProductName( final String productName) {
        this.productName = productName;
    }
    /**
     * @return the Integer
     */
    public Integer getSalePrice() {
        return salePrice;
    }
    /**
     * @param salePrice  to set
     */
    public void setSalePrice(final Integer salePrice) {
        this.salePrice = salePrice;
    }
    /**
     * @return the Integer
     */
    public Integer getListPrice() {
        return listPrice;
    }
    /**
     * @param listPrice  to set
     */
    public void setListPrice( final Integer listPrice) {
        this.listPrice = listPrice;
    }
    /**
     * @return the String
     */
    public String getProductDescrition() {
        return productDescrition;
    }
    /**
     * @param productDescrition  to set
     */
    public void setProductDescrition(String productDescrition) {
        this.productDescrition = productDescrition;
    }
    /**
     * @return the String
     */
    public String getCategory() {
        return category;
    }
    /**
     * @param category  to set
     */
    public void setCategory(final String category) {
        this.category = category;
    }
    /**
     * @return the boolean
     */
    public boolean isIs_deleted() {
        return is_deleted;
    }
    /**
     * @param is_deleted  to set
     */
    public void setIs_deleted(final boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}