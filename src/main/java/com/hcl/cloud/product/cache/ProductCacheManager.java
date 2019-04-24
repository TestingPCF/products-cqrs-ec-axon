package com.hcl.cloud.product.cache;

import com.hcl.cloud.product.request.CreateproductReq;

public interface ProductCacheManager {
    void cacheProductDetails(CreateproductReq product);
    void removeProductFromCache(CreateproductReq product);
    CreateproductReq getProductFromCache(String skuCode);
}
