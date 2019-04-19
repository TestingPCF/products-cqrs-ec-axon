package com.hcl.cloud.product.request;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 
 * @author BrijendraK
 *
 */
@Entity
public class CreateproductReq {

	@Id
	private String skuCode = null;
	private String productName = null;
	private Double salePrice = 0.0;
	private Double listPrice = 0.0;
	private String productDescrition = null;
	private String category = null;

	@JsonIgnore
	private boolean is_deleted = false;
	@JsonIgnore
	private String status = null;

	public CreateproductReq() {
	}

	public CreateproductReq(String skuCode, String productName, Double listPrice, Double salePrice,
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

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getListPrice() {
		return listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

}
