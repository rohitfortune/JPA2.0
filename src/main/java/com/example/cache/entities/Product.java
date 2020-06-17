package com.example.cache.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 6520399206355458653L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String sku;
	private BigDecimal price;
	private String color;
	private String brand;
	private int size;
	private int quantity;

	@ManyToOne(fetch = FetchType.EAGER)
	private Seller seller;

	@ManyToOne(fetch = FetchType.EAGER)
	private ProductCategory category;

	public Product() {
	}

	public Product(String sku, BigDecimal price, String color, String brand, int size, int quantity, Seller seller,
			ProductCategory category) {
		super();
		this.sku = sku;
		this.price = price;
		this.color = color;
		this.brand = brand;
		this.size = size;
		this.quantity = quantity;
		this.seller = seller;
		this.category = category;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", sku=" + sku + ", price=" + price + ", color=" + color + ", brand=" + brand
				+ ", size=" + size + ", quantity=" + quantity + ", seller=" + seller + ", category=" + category + "]";
	}

}
