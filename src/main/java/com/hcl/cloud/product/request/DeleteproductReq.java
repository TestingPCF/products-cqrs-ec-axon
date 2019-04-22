package com.hcl.cloud.product.request;

import javax.validation.constraints.NotNull;

/**
 * 
 * @author BrijendraK
 *
 */
public class DeleteproductReq {

	@NotNull
	private String skuCode = null;

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
}
