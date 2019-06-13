package com.hcl.cloud.product.command;

public class AddInventoryCommand {
	private String skuCode = null;
	private long quantity = 0;
	private boolean status = true;

	public String getSkuCode() {
		return skuCode;
	}

	public long getQuantity() {
		return quantity;
	}

	public boolean getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "AddInventoryCommand [skuCode=" + skuCode + ", quantity=" + quantity + ", status=" + status + "]";
	}

	public AddInventoryCommand(String skuCode, long quantity, boolean status) {
		super();
		this.skuCode = skuCode;
		this.quantity = quantity;
		this.status = status;
	}

}
