package com.belajar.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="product")

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	@Column(name="name", nullable=false)
	private String name;
	@Column(name="price", nullable=false)
	private double price;
	@Column(name="quantity", nullable=false)	
	private int quantity;
	@Column(name="reorder_level")
	private int reorderLevel;
	
	@JoinColumn(name="supplier_id", nullable=false)
	@ManyToOne(targetEntity=Supplier.class, fetch=FetchType.LAZY)
	@JsonIgnore
	private Supplier supplier;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public Product() {}
	public Product(String name, double price, int quantity, int reorderLevel, Supplier supplier) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.reorderLevel = reorderLevel;
		this.supplier = supplier;
	}
	
	
	
}
