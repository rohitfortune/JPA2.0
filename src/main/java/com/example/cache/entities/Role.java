package com.example.cache.entities;

import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;



@Entity
public class Role extends AbstractPersistable<Long> implements Serializable{

	private static final long serialVersionUID = -2716348754532601761L;
	
	private String name;
		
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(String name) {
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
		return "Role [name=" + name + "]";
	}
}