package com.example.cache.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ProductCategory implements Serializable{

	private static final long serialVersionUID = -3373710230878130L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
		
	public ProductCategory(String name) {
		super();
		this.name = name;		
	}

	public ProductCategory() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductCategory [id=" + id + ", name=" + name + "]";
	}		
}
