package com.hcl.cloud.product.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author BrijendraK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateproductRes {

	private String skuCode = null;
	private int statusCode;
	private String status = null;

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int created) {
		this.statusCode = created;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
