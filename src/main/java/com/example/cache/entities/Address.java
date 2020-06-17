package com.example.cache.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Address extends AbstractPersistable<Long> implements Serializable{

	private static final long serialVersionUID = -4863536267672915815L;
		
	private String addressLine;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String pinCode;
	
	@ManyToOne
	@JsonBackReference(value = "addresses")
	private User user;
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String addressLine, String city, String state, String country, String pinCode, User user) {
		super();
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
		this.user = user;
	}	
		
	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [addressLine=" + addressLine + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", pinCode=" + pinCode + "]";
	}
}