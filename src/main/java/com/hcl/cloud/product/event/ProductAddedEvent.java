package com.hcl.cloud.product.event;

/**
 * Created by benwilcock on 18/04/2017.
 */
public class ProductAddedEvent {

	private String skuCode = null;   
    private String productName = null;
    private Integer salePrice;
    private Integer listPrice;
    private String productDescrition = null;
    private String category = null;
    private boolean is_deleted = false;
    private String status = null;
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
	public ProductAddedEvent() {
    }
	public ProductAddedEvent(String skuCode, String productName, Integer salePrice, Integer listPrice,
			String productDescrition, String category, boolean is_deleted, String status) {
		super();
		this.skuCode = skuCode;
		this.productName = productName;
		this.salePrice = salePrice;
		this.listPrice = listPrice;
		this.productDescrition = productDescrition;
		this.category = category;
		this.is_deleted = is_deleted;
		this.status = status;
	}
	
}
