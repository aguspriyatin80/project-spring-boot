package com.belajar.springboot.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductPayload {
	@JsonProperty("name")
	private String name;
	@JsonProperty("price")
	private double price;
	@JsonProperty("quantity")
	private int quantity;
	@JsonProperty("reorderLevel")
	private int reorderLevel;	
	@JsonProperty("supplier")
	private String supplier;
	
	//GETTER
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getReorderLevel() {
		return reorderLevel;
	}
	public String getSupplier() {
		return supplier;
	}
	public ProductPayload(String name, double price, int quantity, int reorderLevel, String supplier) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.reorderLevel = reorderLevel;
		this.supplier = supplier;
	}
	
	
	//CONSTRUCTOR TIDAK PERLU
	
	
}
