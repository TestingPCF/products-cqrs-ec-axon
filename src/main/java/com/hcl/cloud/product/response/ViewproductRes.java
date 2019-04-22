package com.hcl.cloud.product.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hcl.cloud.product.request.CreateproductReq;
/**
 * 
 * @author BrijendraK
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViewproductRes {

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	List<CreateproductReq> productList = new ArrayList<CreateproductReq>();
	private String skuCode = null;
	private String statusCode=null;
	private String status = null;
	
	public List<CreateproductReq> getProductList() {
		return productList;
	}

	public void setProductList(List<CreateproductReq> productList) {
		this.productList = productList;
	}

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
