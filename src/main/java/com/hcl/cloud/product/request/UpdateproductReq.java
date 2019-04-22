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

}
