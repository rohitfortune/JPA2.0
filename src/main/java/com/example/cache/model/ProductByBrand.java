package com.example.cache.model;

import java.io.Serializable;
import java.util.List;

import com.example.cache.entities.Product;

public class ProductByBrand implements Serializable{

	private static final long serialVersionUID = -5128434336923454203L;
	private String brand;
	private List<Product> products;
	
	public ProductByBrand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductByBrand(String brand, List<Product> products) {
		super();
		this.brand = brand;
		this.products = products;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
