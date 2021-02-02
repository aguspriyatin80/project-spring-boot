package com.belajar.springboot.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SalePayload {	
	@JsonProperty("date")
	private String date;
	@JsonProperty("price")
	private double price;
	@JsonProperty("product")
	private String product;
	@JsonProperty("member")
	private String member;

	//GETTER
	public String getDate() {
		return date;
	}
	public double getPrice() {
		return price;
	}
	public String getProduct() {
		return product;
	}
	public String getMember() {
		return member;
	}
	public SalePayload(String date, double price, String product, String member) {
		super();
		this.date = date;
		this.price = price;
		this.product = product;
		this.member = member;
	}		
	
	//CONSTRUCTOR TIDAK PERLU
	
	
}
