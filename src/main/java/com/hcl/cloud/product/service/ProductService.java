package com.hcl.cloud.product.service;

import java.util.List;

import org.springframework.core.env.Environment;

import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.request.DeleteproductReq;
import com.hcl.cloud.product.request.UpdateproductReq;

/**
 * 
 * @author BrijendraK
 *
 */
public interface ProductService {
	
	/**
	 * This method implementation works for create product.
	 * 
	 * @param createproductReq
	 * @param env
	 * @return
	 * @throws ProductException
	 */
	public CreateproductReq createProduct(CreateproductReq createproductReq, Environment env) throws ProductException;

	/**
	 * This method implementation works for delete product.
	 * 
	 * @param deleteproductReq
	 * @param env
	 * @return
	 * @throws ProductException
	 */

	public CreateproductReq deleteProduct(DeleteproductReq deleteproductReq, Environment env) throws ProductException;

	/**
	 * This method implementation works for update product.
	 * 
	 * @param updateproductReq
	 * @param env
	 * @return
	 * @throws ProductException
	 */

	public CreateproductReq updateProduct(UpdateproductReq updateproductReq, Environment env) throws ProductException;

	/**
	 * This method implementation works for view active product based on skucode.
	 * 
	 * @param skuCode
	 * @param env
	 * @return
	 * @throws ProductException
	 */

	public List<CreateproductReq> viewproductbyskuCode(String skuCode, Environment env) throws ProductException;

	/**
	 * This method implementation works for view all active products.
	 * 
	 * @param env
	 * @return
	 * @throws ProductException
	 */

	public List<CreateproductReq> viewProducts(Environment env) throws ProductException;

}
