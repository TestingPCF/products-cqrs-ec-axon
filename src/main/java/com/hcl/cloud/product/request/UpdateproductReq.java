package com.hcl.cloud.product.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
/**
 * 
 * @author BrijendraK
 *
 */
public class UpdateproductReq {

    @Id
    @NotNull
    private String skuCode = null;

    private String productName = null;
    @Min(value=1)
    private Integer salePrice;
    @Min(value=1)
    private Integer listPrice;
    private String productDescrition = null;
    private String category = null;
    private boolean is_deleted = false;
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
    public String getProductName() {
        return productName;
    }
    /**
     * @param productName  to set
     */
    public void setProductName(String productName) {
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
    public void setSalePrice(Integer salePrice) {
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
    public void setListPrice(Integer listPrice) {
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
    public void setCategory(String category) {
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
    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}