package com.hcl.cloud.product.components;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.hcl.cloud.product.event.ProductAddedEvent;
import com.hcl.cloud.product.repository.ProductRepository;
import com.hcl.cloud.product.request.CreateproductReq;

@Component
public class ProductCatalogObjectUpdater {

	private ProductRepository repository;

	public ProductCatalogObjectUpdater(ProductRepository repository) {
		this.repository = repository;
	}
	
	@EventHandler
	public void on (ProductAddedEvent request) {
		repository.save(new CreateproductReq(request.getSkuCode(), request.getProductName(),request.getSalePrice(), request.getListPrice(),request.getProductDescrition(), request.getCategory(),request.isIs_deleted(),request.getStatus()));
	}
}
