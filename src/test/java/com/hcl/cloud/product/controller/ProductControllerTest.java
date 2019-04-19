
package com.hcl.cloud.product.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;

import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.request.DeleteproductReq;
import com.hcl.cloud.product.service.ProductService;
import com.hcl.cloud.product.service.impl.ProductServiceImpl;
/**
 * 
 * @author BrijendraK
 *
 */
public class ProductControllerTest {

	ProductController productController = new ProductController();

	Environment env;

	@Test
	public void testCreateProduct() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		ProductService productService = Mockito.mock(ProductServiceImpl.class);
		env = Mockito.mock(Environment.class);
		productController.setEnv(env);
		productController.setProductService(productService);

		when(productService.createProduct(createproductReq, env)).thenReturn(createproductReq);
		productController.createProduct("ABC", createproductReq);
		assertEquals("ABC", createproductReq.getSkuCode());
	}

	@Test
	public void testDeleteProduct() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq(); //
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		DeleteproductReq deleteproductReq = new DeleteproductReq();
		deleteproductReq.setSkuCode("ABC");
		ProductService productService = Mockito.mock(ProductServiceImpl.class);
		env = Mockito.mock(Environment.class);
		productController.setEnv(env);
		productController.setProductService(productService);

		when(productService.deleteProduct(deleteproductReq, env)).thenReturn(createproductReq);
		productController.deleteProduct("abjjhfgk", deleteproductReq);
		assertEquals("ABC", deleteproductReq.getSkuCode());
	}
}
