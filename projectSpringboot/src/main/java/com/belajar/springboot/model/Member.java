package com.belajar.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member")
public class Member {	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int memberId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="surname")
	private String surname;
	@Column(name="address")
	private String address;
	@Column(name="contact")
	private String contact;
	@Column(name="expires")
	private String expires;
	
	// CONSTRUCTOR
	public Member() {
		
	}

	public Member(String firstName, String surname, String address, String contact, String expires) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.address = address;
		this.contact = contact;
		this.expires = expires;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	// GETTER SETTER

}


