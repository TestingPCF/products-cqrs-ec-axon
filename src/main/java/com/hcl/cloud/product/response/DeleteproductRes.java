package com.hcl.cloud.product.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 
 * @author BrijendraK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteproductRes {

	private String skuCode = null;
	private String statusCode = null;
	private String status = null;

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String created) {
		this.statusCode = created;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}