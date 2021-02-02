package com.belajar.springboot.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberPayload {
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("surname")
	private String surname;
	@JsonProperty("address")
	private String address;
	@JsonProperty("contact")
	private String contact;
	@JsonProperty("expires")
	private String expires;

	//GETTER
	public String getFirstName() {
		return firstName;
	}
	public String getSurname() {
		return surname;
	}
	public String getAddress() {
		return address;
	}
	public String getContact() {
		return contact;
	}
	public String getExpires() {
		return expires;
	}
	
	
	//CONSTRUCTOR TIDAK PERLU
	
	
}
