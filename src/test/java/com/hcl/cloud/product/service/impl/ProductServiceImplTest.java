package com.hcl.cloud.product.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import com.hcl.cloud.product.controller.ProductController;
import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.repository.ProductRepository;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.request.DeleteproductReq;
import com.hcl.cloud.product.request.UpdateproductReq;
import com.hcl.cloud.product.resources.TransactionBean;
import com.hcl.cloud.product.response.ViewproductRes;
import com.hcl.cloud.product.service.ProductService;

public class ProductServiceImplTest {

	
	ProductServiceImpl productService = new ProductServiceImpl();

	Environment env;

	@Test()
	public void testCreateProduct() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq );
		TransactionBean txnBean =  new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		when(repository.save(createproductReq)).thenReturn(createproductReq);
		createproductReq =productService.createProduct(createproductReq, env, txnBean );
		assertEquals("ABC", createproductReq.getSkuCode());
	}

	@Test()
	public void testUpdateProduct() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq );
		TransactionBean txnBean =  new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		when(repository.save(createproductReq)).thenReturn(createproductReq);
		UpdateproductReq updateproductReq = new UpdateproductReq();
		updateproductReq.setCategory("B");
		updateproductReq.setProductName("MOBILE");
		updateproductReq.setSkuCode("ABC");
		createproductReq =productService.updateProduct(updateproductReq , env);
		assertEquals("ABC", createproductReq.getSkuCode());
	}

	
}
