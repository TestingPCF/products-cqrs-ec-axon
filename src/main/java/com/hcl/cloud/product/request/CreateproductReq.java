package com.hcl.cloud.product.request;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @author BrijendraK
 *
 */

@Entity
public class CreateproductReq  implements Serializable{


    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    private String skuCode = null;
    @NotNull
    private String productName = null;
    @Min(value=1)
    @NotNull
    private Integer salePrice;
    @Min(value=1)
    @NotNull
    private Integer listPrice;
    @NotNull
    private String productDescrition = null;
    @NotNull
    private String category = null;
    private boolean is_deleted = false;
    @Transient
    @JsonIgnore
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
    /**
     * @return the long
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}