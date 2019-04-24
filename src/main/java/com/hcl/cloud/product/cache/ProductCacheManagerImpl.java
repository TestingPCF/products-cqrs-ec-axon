package com.hcl.cloud.product.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.hcl.cloud.product.request.CreateproductReq;

@Configuration
public class ProductCacheManagerImpl implements ProductCacheManager {
	public static final String PRODUCT_CACHE = "PRODUCT_CACHE";
	public static final String SKU_CODE = "SKUCODE:";
	private RedisUtil<CreateproductReq> redisUtil;

	@Autowired
	public ProductCacheManagerImpl(RedisUtil<CreateproductReq> redisUtil) {
		this.redisUtil = redisUtil;
	}

	@Override
	public void cacheProductDetails(CreateproductReq product) {
		redisUtil.putMap(PRODUCT_CACHE, SKU_CODE + product.getSkuCode(), product);
		redisUtil.setExpire(PRODUCT_CACHE, 1, TimeUnit.HOURS);
	}

	@Override
	public void removeProductFromCache(CreateproductReq product) {
		redisUtil.removeMap(PRODUCT_CACHE, SKU_CODE + product.getSkuCode(), product);
	}

	@Override
	public CreateproductReq getProductFromCache(String skuCode) {
		CreateproductReq product= redisUtil.getMapAsSingleEntry(PRODUCT_CACHE, SKU_CODE+skuCode);
		return product;
	}
	
	
}
