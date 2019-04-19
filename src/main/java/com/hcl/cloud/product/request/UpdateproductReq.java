package com.hcl.cloud.product.request;

import org.springframework.data.annotation.Id;
/**
 * 
 * @author BrijendraK
 *
 */
public class UpdateproductReq {

	@Id
	private String skuCode = null;
	private String productName = null;
	private Double salePrice = 0.0;
	private Double listPrice = 0.0;
	private String productDescrition = null;
	private String category = null;

	public UpdateproductReq(String skuCode, String productName, Double salePrice, Double listPrice,
			String productDescrition, String category) {
		this.skuCode = skuCode;
		this.productName = productName;
		this.listPrice = listPrice;
		this.salePrice = salePrice;
		this.productDescrition = productDescrition;
		this.category = category;
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

}
