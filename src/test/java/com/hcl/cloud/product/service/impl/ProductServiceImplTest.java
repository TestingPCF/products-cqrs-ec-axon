package com.hcl.cloud.product.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;

import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.repository.ProductRepository;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.request.DeleteproductReq;
import com.hcl.cloud.product.request.UpdateproductReq;
import com.hcl.cloud.product.resources.TransactionBean;

public class ProductServiceImplTest {

	ProductServiceImpl productService = new ProductServiceImpl();

	Environment env;

	@Test()
	public void testCreateProduct() {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");

		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		when(repository.save(createproductReq)).thenReturn(createproductReq);
		createproductReq = productService.createProduct(createproductReq, env, txnBean);
		assertEquals("ABC", createproductReq.getSkuCode());
	}

	@Test(expected = ProductException.class)
	public void testCreateProductExcpetion() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");

		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		// productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		when(repository.save(createproductReq)).thenReturn(createproductReq);
		createproductReq = productService.createProduct(createproductReq, env, txnBean);
		assertEquals("ABC", createproductReq.getSkuCode());
	}

	@Test()
	public void testUpdateProduct() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
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
		updateproductReq.setProductDescrition("Desc");
		updateproductReq.setListPrice(12);
		updateproductReq.setSalePrice(10);
		createproductReq = productService.updateProduct(updateproductReq, env);
		assertEquals("ABC", createproductReq.getSkuCode());
	}

	@Test(expected = ProductException.class)
	public void testUpdateProductExcpetion() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");

		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		// productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		when(repository.save(createproductReq)).thenReturn(createproductReq);
		UpdateproductReq updateproductReq = new UpdateproductReq();
		updateproductReq.setCategory("B");
		updateproductReq.setProductName("MOBILE");
		updateproductReq.setSkuCode("ABC");
		createproductReq = productService.updateProduct(updateproductReq, env);
		assertEquals("ABC", createproductReq.getSkuCode());
	}

	@Test()
	public void testViewProductByskuCode() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		// when(repository.save(createproductReq)).thenReturn(createproductReq);
		String skuCode = "ABC";
		List<CreateproductReq> returnList = productService.viewproductbyskuCode(skuCode, env);
		assertEquals("ABC", returnList.get(0).getSkuCode());
	}

	@Test(expected = ProductException.class)
	public void testViewProductByskuCodeException() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		// productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		// when(repository.save(createproductReq)).thenReturn(createproductReq);
		String skuCode = "ABC";
		List<CreateproductReq> returnList = productService.viewproductbyskuCode(skuCode, env);
		assertEquals("ABC", returnList.get(0).getSkuCode());
	}

	@Test()
	public void testViewProducts() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		List<CreateproductReq> productRequest = new ArrayList<CreateproductReq>();
		productRequest.add(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findAll()).thenReturn(productRequest);
		// when(repository.save(createproductReq)).thenReturn(createproductReq);

		List<CreateproductReq> returnList = productService.viewProducts(env);
		assertEquals("ABC", returnList.get(0).getSkuCode());
	}

	@Test(expected = ProductException.class)
	public void testViewProductsException() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		createproductReq.setStatus("success");
		List<CreateproductReq> productRequest = new ArrayList<CreateproductReq>();
		productRequest.add(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		// productService.setRepository(repository);
		when(repository.findAll()).thenReturn(productRequest);
		// when(repository.save(createproductReq)).thenReturn(createproductReq);

		List<CreateproductReq> returnList = productService.viewProducts(env);
		assertEquals("ABC", returnList.get(0).getSkuCode());
	}

	@Test()
	public void testDeleteProduct() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		// createproductReq.setStatus("success");
		createproductReq.setIs_deleted(true);
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		// when(repository.save(createproductReq)).thenReturn(createproductReq);

		DeleteproductReq deleteproductReq = new DeleteproductReq();
		deleteproductReq.setSkuCode("ABC");
		CreateproductReq response = productService.deleteProduct(deleteproductReq, env);
		assertEquals("ABC", response.getSkuCode());
	}

	@Test(expected = ProductException.class)
	public void testDeleteProductException() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		// createproductReq.setStatus("success");
		createproductReq.setIs_deleted(true);
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		// productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		// when(repository.save(createproductReq)).thenReturn(createproductReq);

		DeleteproductReq deleteproductReq = new DeleteproductReq();
		deleteproductReq.setSkuCode("ABC");
		CreateproductReq response = productService.deleteProduct(deleteproductReq, env);
		assertEquals("ABC", response.getSkuCode());
	}

	@Test()
	public void testDeleteProductWhenIsDeleteFalse() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		// createproductReq.setStatus("success");
		createproductReq.setIs_deleted(false);
		Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
		TransactionBean txnBean = new TransactionBean();
		txnBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		when(repository.findById("ABC")).thenReturn(productRequest);
		when(repository.save(createproductReq)).thenReturn(createproductReq);

		DeleteproductReq deleteproductReq = new DeleteproductReq();
		deleteproductReq.setSkuCode("ABC");
		CreateproductReq response = productService.deleteProduct(deleteproductReq, env);
		assertEquals("ABC", response.getSkuCode());
	}

	@Test(expected = ProductException.class)
	public void testCreateFallBack() throws ProductException {

		CreateproductReq createproductReq = new CreateproductReq();
		createproductReq.setSkuCode("ABC");
		// createproductReq.setStatus("success");
		createproductReq.setIs_deleted(false);

		TransactionBean txBean = new TransactionBean();
		txBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		productService.createProductFallback(createproductReq, env, txBean);
	}

	@Test(expected = ProductException.class)
	public void testDeleteFallBack() throws ProductException {

		DeleteproductReq deleteproductReq = new DeleteproductReq();
		deleteproductReq.setSkuCode("ABC");
		// createproductReq.setStatus("success");
		TransactionBean txBean = new TransactionBean();
		txBean.setAccessToken("gasfdghsaf");
		ProductRepository repository = Mockito.mock(ProductRepository.class);
		env = Mockito.mock(Environment.class);
		productService.setRepository(repository);
		productService.deleteProductFallback(deleteproductReq, env);
	}
}
