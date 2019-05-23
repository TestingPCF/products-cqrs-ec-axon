package com.hcl.cloud.product.cache;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.hcl.cloud.product.request.CreateproductReq;

@Configuration
public class ProductCacheManagerImpl implements ProductCacheManager {
    public static final String PRODUCT_CACHE = "PRODUCT_CACHE";
    public static final String SKU_CODE = "SKUCODE:";
    private RedisUtil<CreateproductReq> redisUtil;
    static Logger log = LoggerFactory.getLogger(ProductCacheManagerImpl.class);
    @Autowired
    public ProductCacheManagerImpl(RedisUtil<CreateproductReq> redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public void cacheProductDetails(CreateproductReq product) {
        log.debug("Product putting into cache call start");
        redisUtil.putMap(PRODUCT_CACHE, SKU_CODE + product.getSkuCode(), product);
        redisUtil.setExpire(PRODUCT_CACHE, 1, TimeUnit.HOURS);
        log.debug("Product putting into cache call end");
    }

    @Override
    public void removeProductFromCache(CreateproductReq product) {
        log.debug("Product remove from cache call start");
        redisUtil.removeMap(PRODUCT_CACHE, SKU_CODE + product.getSkuCode(), product);
        log.debug("Product remove from cache call end");
    }

    @Override
    public CreateproductReq getProductFromCache(String skuCode) {
        log.debug("Fetching product from cache call start");
        CreateproductReq product= redisUtil.getMapAsSingleEntry(PRODUCT_CACHE, SKU_CODE+skuCode);
        log.debug("Fetching product from cache call end");
        return product;
    }


}
