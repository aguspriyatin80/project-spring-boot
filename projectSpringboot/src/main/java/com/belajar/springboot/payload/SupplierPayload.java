package com.belajar.springboot.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SupplierPayload {
	@JsonProperty("name")
	private String name;
	@JsonProperty("address")
	private String address;
	@JsonProperty("contact")
	private String contact;

	//GETTER
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getContact() {
		return contact;
	}
		
	//CONSTRUCTOR TIDAK PERLU
	
	
}
