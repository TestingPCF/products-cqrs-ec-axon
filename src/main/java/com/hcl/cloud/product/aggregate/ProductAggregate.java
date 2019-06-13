package com.hcl.cloud.product.aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.hcl.cloud.product.command.AddInventoryCommand;
import com.hcl.cloud.product.command.AddProductToCatalogCommand;
import com.hcl.cloud.product.event.InventoryAddedEvent;
import com.hcl.cloud.product.event.ProductAddedEvent;

public class ProductAggregate {
	 private static final Logger LOG = LoggerFactory.getLogger(ProductAggregate.class);
	
	 @AggregateIdentifier
	private String skuCode = null;   
    private String productName = null;
    private Integer salePrice;
    private Integer listPrice;
    private String productDescrition = null;
    private String category = null;
    private boolean is_deleted = false;
    private String status = null;
    private long quantity=0;
    private boolean productAdded;
    
    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(AddProductToCatalogCommand cmd) {
        LOG.info("Handling {} command: {}, {}", cmd.getClass().getSimpleName(), cmd.getSkuCode(), cmd.getProductName());
        Assert.hasLength(cmd.getSkuCode(), "ID should NOT be empty or null.");
        Assert.hasLength(cmd.getProductName(), "Name should NOT be empty or null.");
        AggregateLifecycle.apply(new ProductAddedEvent(cmd.getSkuCode(), cmd.getProductName(),cmd.getSalePrice(),cmd.getListPrice(),cmd.getProductDescrition(),cmd.getCategory(),cmd.isIs_deleted(),cmd.getStatus()));
        LOG.info("Done handling {} command: {}, {}", cmd.getClass().getSimpleName(), cmd.getSkuCode(), cmd.getProductName());
        productAdded=false;
    }
    
    @CommandHandler
    public void addItemToInventory(AddInventoryCommand cmd) {
    	if (!productAdded) { 
            throw new IllegalStateException("Cannot add a Item to Inventory which has not been confirmed yet to Product-MS."); 
        }
    	apply(new InventoryAddedEvent(cmd.getSkuCode(),cmd.getQuantity(),cmd.getStatus()));
    }

    @EventSourcingHandler
    public void on(ProductAddedEvent evnt) {
        LOG.info("Handling {} event: {}, {}", evnt.getClass().getSimpleName(), evnt.getSkuCode(), evnt.getProductName());
        this.skuCode = evnt.getSkuCode();
        this.productName = evnt.getProductName();
        this.salePrice = evnt.getSalePrice();
		this.listPrice = evnt.getListPrice();
		this.productDescrition = evnt.getProductDescrition();
		this.category = evnt.getCategory();
		this.is_deleted = evnt.isIs_deleted();
		this.status = evnt.getStatus();
        LOG.info("Done handling {} event: {}, {}", evnt.getClass().getSimpleName(), evnt.getSkuCode(), evnt.getProductName());
        productAdded=true;
    }

	public String getSkuCode() {
		return skuCode;
	}

	public String getProductName() {
		return productName;
	}

	public Integer getSalePrice() {
		return salePrice;
	}

	public Integer getListPrice() {
		return listPrice;
	}

	public String getProductDescrition() {
		return productDescrition;
	}

	public String getCategory() {
		return category;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public String getStatus() {
		return status;
	}
    
}
