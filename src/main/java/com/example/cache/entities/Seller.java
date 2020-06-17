package com.example.cache.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seller implements Serializable{
	
	private static final long serialVersionUID = -8729652893792540271L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	
	public Seller() {
		
	}

	public Seller(String name) {
		super();
		this.name = name;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + "]";
	}

}
