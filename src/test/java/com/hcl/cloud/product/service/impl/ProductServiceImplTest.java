package com.hcl.cloud.product.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hcl.cloud.product.cache.ProductCacheManager;
import com.hcl.cloud.product.cache.ProductCacheManagerImpl;
import com.hcl.cloud.product.cache.RedisUtil;
import com.hcl.cloud.product.datatranslator.CreateProductResponseTranslator;
import com.hcl.cloud.product.datatranslator.DeleteProductResponseTranslator;
import com.hcl.cloud.product.exception.ProductException;
import com.hcl.cloud.product.repository.ProductRepository;
import com.hcl.cloud.product.request.CreateproductReq;
import com.hcl.cloud.product.request.DeleteproductReq;
import com.hcl.cloud.product.request.InventoryQuantityReq;
import com.hcl.cloud.product.request.UpdateproductReq;
import com.hcl.cloud.product.resources.TransactionBean;
import com.hcl.cloud.product.response.CreateproductRes;
import com.hcl.cloud.product.response.DeleteproductRes;
import com.hcl.cloud.product.response.InventoryQuantityRes;
import com.hcl.cloud.product.response.UpdateproductRes;
import com.hcl.cloud.product.response.ViewproductRes;

public class ProductServiceImplTest {

    ProductServiceImpl productService = new ProductServiceImpl();
    RedisTemplate<String, CreateproductReq> redisTemplate = new RedisTemplate();
    RedisUtil<CreateproductReq> redisUtil = new RedisUtil<CreateproductReq>(redisTemplate);
    ProductCacheManagerImpl productCacheManager = new ProductCacheManagerImpl(redisUtil);
    Environment env;
    //HystrixCommandPropertyResource hystrixCommandProp = new HystrixCommandPropertyResource();

    @Test()
    public void testCreateProduct() throws ProductException {

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

    @Test(expected= Exception.class) 
    public void testinventoryCall() throws ProductException {
        CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
        createproductReq.setStatus("success");
        TransactionBean txnBean = new TransactionBean();
        txnBean.setAccessToken("gasfdghsaf");
        RestTemplate restTemplate = new RestTemplate();
        URI uri = null;
        final String url = "dasdasf";
        // "http://inventory.apps.cnpsandbox.dryice01.in.hclcnlabs.com/inventory-test";
        try {
            uri = new URI(url);
        } catch (URISyntaxException ex) {
            throw new ProductException(ex.getMessage());
        }
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        requestHeaders.add("ACCESS_TOKEN", txnBean.getAccessToken());
        InventoryQuantityReq inventory = new InventoryQuantityReq();
        inventory.setSkuCode(createproductReq.getSkuCode());
        inventory.setQuantity(0);
        HttpEntity<InventoryQuantityReq> requestEntity = new HttpEntity<>(inventory, requestHeaders);
        ResponseEntity<InventoryQuantityRes> responseEntity = restTemplate.postForEntity(uri, requestEntity,
                InventoryQuantityRes.class);
        if (responseEntity != null) {

            // If product created successfully put it in cache for future use
            productCacheManager.cacheProductDetails(createproductReq);

        }

    }

    @Test(expected = Exception.class)
    public void testCreateProductCase2() throws ProductException {

        CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
        createproductReq.setStatus("success");
        Optional<CreateproductReq> productRequest = Optional.empty();
        TransactionBean txnBean = new TransactionBean();
        txnBean.setAccessToken("gasfdghsaf");

        ProductRepository repository = Mockito.mock(ProductRepository.class);
        env = Mockito.mock(Environment.class);
        productService.setRepository(repository);
        when(repository.findById("ABC")).thenReturn(productRequest);
        when(repository.save(createproductReq)).thenReturn(createproductReq);
        createproductReq = productService.createProduct(createproductReq, env, txnBean);
        // assertEquals("ABC", createproductReq.getSkuCode());
    }

    @Test(expected = Exception.class)
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

    @Test(expected=Exception.class)
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

    @Test(expected = Exception.class)
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

        ProductCacheManager productCacheManager = Mockito.mock(ProductCacheManager.class);
        productService.setProductCacheManager(productCacheManager);
        // when(repository.save(createproductReq)).thenReturn(createproductReq);
        String skuCode = "ABC";
        List<CreateproductReq> returnList = productService.viewproductbyskuCode(skuCode, env);
        CreateProductResponseTranslator createProductResponseTranslator = new CreateProductResponseTranslator();
        createProductResponseTranslator.createproductresponsetranslator(createproductReq, env);
        assertEquals("ABC", returnList.get(0).getSkuCode());
    }

    @Test()
    public void testViewProductByskuCodeForCache() throws ProductException {

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
        ProductCacheManager productCacheManager = Mockito.mock(ProductCacheManager.class);
        when(productCacheManager.getProductFromCache("ABC")).thenReturn(createproductReq);
        productService.setProductCacheManager(productCacheManager);
        String skuCode = "ABC";
        List<CreateproductReq> returnList = productService.viewproductbyskuCode(skuCode, env);
        assertEquals("ABC", returnList.get(0).getSkuCode());
    }

    @Test(expected = Exception.class)
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
        CreateProductResponseTranslator createProductResponseTranslator = new CreateProductResponseTranslator();
        createproductReq.setStatus("fail");
        createProductResponseTranslator.createproductresponsetranslator(createproductReq, env);
        assertEquals("ABC", returnList.get(0).getSkuCode());
    }

    @Test(expected = Exception.class)
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

    @Test(expected = Exception.class)
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
        createproductReq.setIs_deleted(false);
        Optional<CreateproductReq> productRequest = Optional.of(createproductReq);
        TransactionBean txnBean = new TransactionBean();
        txnBean.setAccessToken("gasfdghsaf");
        ProductRepository repository = Mockito.mock(ProductRepository.class);
        env = Mockito.mock(Environment.class);
        productService.setRepository(repository);
        when(repository.findById("ABC")).thenReturn(productRequest);
        when(repository.save(createproductReq)).thenReturn(createproductReq);
        ProductCacheManager productCacheManager = Mockito.mock(ProductCacheManager.class);
        productService.setProductCacheManager(productCacheManager);
        DeleteproductReq deleteproductReq = new DeleteproductReq();
        deleteproductReq.setSkuCode("ABC");
        CreateproductReq response = productService.deleteProduct(deleteproductReq, env);
        assertEquals("ABC", response.getSkuCode());
    }

    @Test(expected = ProductException.class)
    public void testCreateFallBack() throws ProductException {

        CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
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
        TransactionBean txBean = new TransactionBean();
        txBean.setAccessToken("gasfdghsaf");
        ProductRepository repository = Mockito.mock(ProductRepository.class);
        env = Mockito.mock(Environment.class);
        productService.setRepository(repository);
        productService.deleteProductFallback(deleteproductReq, env);
    }

    @Test(expected = ProductException.class)
    public void testUpdateFallBack() throws ProductException {

        UpdateproductReq updateproductReq = new UpdateproductReq();
        updateproductReq.setSkuCode("ABC");
        updateproductReq.setCategory("mobie");
        updateproductReq.setListPrice(20000);
        updateproductReq.setSalePrice(18000);
        updateproductReq.setProductName("VIVO Mobile");
        updateproductReq.setProductDescrition("Test Mobile");
        TransactionBean txBean = new TransactionBean();
        txBean.setAccessToken("gasfdghsaf");
        ProductRepository repository = Mockito.mock(ProductRepository.class);
        env = Mockito.mock(Environment.class);
        productService.setRepository(repository);
        productService.updateProductFallback(updateproductReq, env);
    }

    

    @Test(expected = ProductException.class)
    public void testviewproductbyskuCodeFallback() throws ProductException {

        String skucode = "ABC";
        ProductRepository repository = Mockito.mock(ProductRepository.class);
        env = Mockito.mock(Environment.class);
        productService.setRepository(repository);
        productService.viewproductbyskuCodeFallback(skucode, env);
    }

    @Test(expected = ProductException.class)
    public void testviewProductsFallback() throws ProductException {

        // String skucode = "ABC";
        ProductRepository repository = Mockito.mock(ProductRepository.class);
        env = Mockito.mock(Environment.class);
        productService.setRepository(repository);
        productService.viewProductsFallback(env);
    }

    @Test
    public void testDeleteTranslatorForAreadyDeleted() throws ProductException {

        CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
        env = Mockito.mock(Environment.class);
        createproductReq.setStatus("already");
        DeleteProductResponseTranslator deleteProductResponseTranslator = new DeleteProductResponseTranslator();
        deleteProductResponseTranslator.deleteproductresponseTranslator(createproductReq, env);
    }

    @Test
    public void testDeleteTranslator() throws ProductException {

        CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
        env = Mockito.mock(Environment.class);
        createproductReq.setStatus("change");
        DeleteProductResponseTranslator deleteProductResponseTranslator = new DeleteProductResponseTranslator();
        deleteProductResponseTranslator.deleteproductresponseTranslator(createproductReq, env);
    }

    @Test
    public void testResponse() {

        InventoryQuantityRes inventoryQuantityRes = new InventoryQuantityRes();
        inventoryQuantityRes.setActiveStatus(true);
        inventoryQuantityRes.setInStock(true);
        inventoryQuantityRes.setQuantity(2);
        inventoryQuantityRes.setSkuCode("ABC");
        inventoryQuantityRes.getQuantity();
        inventoryQuantityRes.getSkuCode();
        ViewproductRes viewproductRes = new ViewproductRes();
        viewproductRes.setProductList(new ArrayList<>());
        viewproductRes.setStatus("a");
        viewproductRes.setStatusCode("ABC");
        viewproductRes.setSkuCode("ABC");
        viewproductRes.getSkuCode();
        viewproductRes.getStatus();
        viewproductRes.getStatusCode();

        UpdateproductRes updateproductRes = new UpdateproductRes();
        updateproductRes.setSkuCode("ABC");
        updateproductRes.setStatus("updated");
        updateproductRes.setStatusCode("200");
        updateproductRes.getSkuCode();
        updateproductRes.getStatus();
        updateproductRes.getStatusCode();
        CreateproductRes createproductRes = new CreateproductRes();
        createproductRes.setSkuCode("ABC");
        createproductRes.setStatus("created");
        createproductRes.setStatusCode("200");
        createproductRes.getSkuCode();
        createproductRes.getStatus();
        createproductRes.getStatusCode();

        DeleteproductRes deleteproductRes = new DeleteproductRes();
        deleteproductRes.setSkuCode("ABC");
        deleteproductRes.setStatus("deleted");
        deleteproductRes.setStatusCode("200");
        deleteproductRes.getSkuCode();
        deleteproductRes.getStatus();
        deleteproductRes.getStatusCode();

    }

    @Test
    public void testRequest() {

        InventoryQuantityReq inventoryQuantityReq = new InventoryQuantityReq();
        inventoryQuantityReq.setQuantity(0);
        inventoryQuantityReq.setSkuCode("ABC");
        inventoryQuantityReq.getQuantity();
        inventoryQuantityReq.getSkuCode();

        UpdateproductReq updateproductReq = new UpdateproductReq();
        updateproductReq.setSkuCode("ABC");
        updateproductReq.setSalePrice(10000);
        updateproductReq.setProductName("TV");
        updateproductReq.setProductDescrition("Television");
        updateproductReq.setListPrice(15000);
        updateproductReq.setIs_deleted(false);
        updateproductReq.setCategory("MTV");
        updateproductReq.getCategory();
        updateproductReq.getListPrice();
        updateproductReq.getProductDescrition();
        updateproductReq.getProductName();
        updateproductReq.getSalePrice();
        updateproductReq.getSkuCode();
        CreateproductReq createproductReq = new CreateproductReq();
        createproductReq.setSkuCode("ABC");
        createproductReq.setSalePrice(10000);
        createproductReq.setProductName("TV");
        createproductReq.setProductDescrition("Television");
        createproductReq.setListPrice(15000);
        createproductReq.setIs_deleted(false);
        createproductReq.setCategory("MTV");
        createproductReq.getCategory();
        createproductReq.getListPrice();
        createproductReq.getProductDescrition();
        createproductReq.getProductName();
        createproductReq.getSalePrice();
        createproductReq.getSkuCode();

    }

}
