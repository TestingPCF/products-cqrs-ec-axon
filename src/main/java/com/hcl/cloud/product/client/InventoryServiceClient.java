package com.hcl.cloud.product.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hcl.cloud.product.request.InventoryQuantityReq;
import com.hcl.cloud.product.response.InventoryQuantityRes;

/**
 * This is an interface, which works as a client for inventory API.
 * @author mohitkri
 */
//@FeignClient("inventory")
public interface InventoryServiceClient {
    
    /**
     * This method invokes the PUT api call for inventory.
     * @param inventory inventory object
     * @return ResponseEntity ResponseEntity
     */
    @PostMapping
    public ResponseEntity<InventoryQuantityRes> createInventory(@RequestBody InventoryQuantityReq inventory);

}
