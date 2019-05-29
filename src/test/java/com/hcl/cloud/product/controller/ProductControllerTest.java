
package com.hcl.cloud.product.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.hcl.cloud.product.config.ConfigLoader;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;

import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.request.DeleteproductReq;
import com.hcl.cloud.product.request.UpdateproductReq;
import com.hcl.cloud.product.resources.TransactionBean;
import com.hcl.cloud.product.response.UpdateproductRes;
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

    @Test(expected = ProductException.class)
    public void testCreateProduct() throws ProductException {

        CreateproductReq createproductReq = new CreateproductReq();
        TransactionBean txnBean = new TransactionBean();
        txnBean.setAccessToken("gasfdghsaf");
        createproductReq.setSkuCode("ABC");
        createproductReq.setStatus("success");
        ProductService productService = Mockito.mock(ProductServiceImpl.class);
        env = Mockito.mock(Environment.class);
        productController.setEnv(env);
        productController.setProductService(productService);

        when(productService.createProduct(createproductReq, env, txnBean)).thenReturn(createproductReq);
        productController.createProduct("ABC", createproductReq);
        assertEquals("ABC", createproductReq.getSkuCode());
    }

    @Test(expected = ProductException.class)
    public void testCreateProductException() throws ProductException {

        CreateproductReq createproductReq = new CreateproductReq();
        TransactionBean txnBean = new TransactionBean();
        txnBean.setAccessToken("gasfdghsaf");
        createproductReq.setSkuCode("ABC");
        createproductReq.setStatus("success");
        ProductService productService = Mockito.mock(ProductServiceImpl.class);
        env = Mockito.mock(Environment.class);
        productController.setEnv(env);
        // productController.setProductService(productService);

        when(productService.createProduct(createproductReq, env, txnBean)).thenReturn(createproductReq);
        productController.createProduct("ABC", createproductReq);
    }

    @Test(expected = Exception.class)
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

    @Test(expected = ProductException.class)
    public void testDeleteProductException() throws ProductException {

        CreateproductReq createproductReq = new CreateproductReq(); //
        createproductReq.setSkuCode("ABC");
        createproductReq.setStatus("success");
        DeleteproductReq deleteproductReq = new DeleteproductReq();
        deleteproductReq.setSkuCode("ABC");
        ProductService productService = Mockito.mock(ProductServiceImpl.class);
        env = Mockito.mock(Environment.class);
        productController.setEnv(env);
        // productController.setProductService(productService);

        when(productService.deleteProduct(deleteproductReq, env)).thenReturn(createproductReq);
        productController.deleteProduct("abjjhfgk", deleteproductReq);
        assertEquals("ABC", deleteproductReq.getSkuCode());
    }

    @Test(expected = Exception.class)
    public void testUpdateProduct() throws ProductException {

        UpdateproductReq updateproductReq = new UpdateproductReq();
        updateproductReq.setSkuCode("ABC");
        updateproductReq.setProductName("updated");
        ProductService productService = Mockito.mock(ProductServiceImpl.class);
        env = Mockito.mock(Environment.class);
        productController.setEnv(env);
        productController.setProductService(productService);

        CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
        createproductReq.setStatus("success");
        when(productService.updateProduct(updateproductReq, env)).thenReturn(createproductReq);
        productController.updateProduct("abcd", updateproductReq);
        assertEquals("200 OK", updateproductReq.getSkuCode());
    }

    @Test(expected = ProductException.class)
    public void testUpdateProductException() throws ProductException {

        UpdateproductReq updateproductReq = new UpdateproductReq();
        updateproductReq.setSkuCode("ABC");
        updateproductReq.setProductName("updated");
        DeleteproductReq deleteproductReq = new DeleteproductReq();
        deleteproductReq.setSkuCode("ABC");
        ProductService productService = Mockito.mock(ProductServiceImpl.class);
        env = Mockito.mock(Environment.class);
        productController.setEnv(env);
        // productController.setProductService(productService);

        CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
        when(productService.updateProduct(updateproductReq, env)).thenReturn(createproductReq);
        ResponseEntity<UpdateproductRes> response = productController.updateProduct("abcd", updateproductReq);
        assertEquals("200 OK", response.getStatusCode().toString());
    }

    @Test(expected = Exception.class)
    public void testUpdateProductStatusCode() throws ProductException {

        UpdateproductReq updateproductReq = new UpdateproductReq();
        updateproductReq.setSkuCode("ABC");
        updateproductReq.setProductName("updated");
        DeleteproductReq deleteproductReq = new DeleteproductReq();
        deleteproductReq.setSkuCode("ABC");
        ProductService productService = Mockito.mock(ProductServiceImpl.class);
        env = Mockito.mock(Environment.class);
        productController.setEnv(env);
        productController.setProductService(productService);

        CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
        createproductReq.setStatus("success");
        when(productService.updateProduct(updateproductReq, env)).thenReturn(createproductReq);
        ResponseEntity<UpdateproductRes> response = productController.updateProduct("abcd", updateproductReq);
        assertEquals("200 OK", response.getStatusCode().toString());
    }

}
