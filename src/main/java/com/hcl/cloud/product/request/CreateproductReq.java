package com.hcl.cloud.product.request;

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
public class CreateproductReq {

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

	public CreateproductReq() {
	}

	public CreateproductReq(String skuCode, String productName, Integer listPrice, Integer salePrice,
			String productDescrition, String category, boolean is_deleted) {
		this.skuCode = skuCode;
		this.productName = productName;
		this.listPrice = listPrice;
		this.salePrice = salePrice;
		this.productDescrition = productDescrition;
		this.category = category;
		this.is_deleted = is_deleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescrition() {
		return productDescrition;
	}

	public void setProductDescrition(String productDescrition) {
		this.productDescrition = productDescrition;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Integer getSalePrice() {
		return salePrice;
	}
	
	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getListPrice() {
		return listPrice;
	}

	public void setListPrice(Integer listPrice) {
		this.listPrice = listPrice;
	}

}
