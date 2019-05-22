package com.hcl.cloud.product.service;

import org.springframework.core.env.Environment;

import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.request.DeleteproductReq;
import com.hcl.cloud.product.request.UpdateproductReq;
import com.hcl.cloud.product.resources.TransactionBean;

/**
 * 
 * @author Brijendra and Kapil Interface for ProductService
 *
 */
public interface ProductService {

    /**
     * This method implementation works for create product.
     * 
     * @param createproductReq
     * @param env
     * @return CreateproductReq
     * @throws ProductException
     */
    public CreateproductReq createProduct(CreateproductReq createproductReq, Environment env, TransactionBean txBean)
            throws ProductException;

    /**
     * This method implementation works for delete product.
     * 
     * @param deleteproductReq
     * @param env
     * @return CreateproductReq
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

}
