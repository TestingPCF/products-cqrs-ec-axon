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

	List<CreateproductReq> productList = new ArrayList<CreateproductReq>();

	public List<CreateproductReq> getProductList() {
		return productList;
	}

	public void setProductList(List<CreateproductReq> productList) {
		this.productList = productList;
	}

}
