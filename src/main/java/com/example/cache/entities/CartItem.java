package com.example.cache.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class CartItem implements Serializable{
	
	private static final long serialVersionUID = -5920375544960564307L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@ManyToOne
	@JsonBackReference(value = "cart")
	private User user;
	
	@ManyToOne
	private Product product;
	
	private Integer buyQuantity;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(Integer quantity) {
		this.buyQuantity = quantity;
	}
	
}
