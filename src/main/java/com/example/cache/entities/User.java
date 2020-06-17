package com.example.cache.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class User extends AbstractPersistable<Long> implements Serializable{
	
	private static final long serialVersionUID = -7302800336276816169L;	
	
	private String fullName;
		
	private String userName;
	
	private String password;
	
	private String email;
	
	private String mobile;
		
	@ManyToOne	
	private Role role;

	@OneToMany(targetEntity = Address.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference(value = "addresses")
	private Set<Address> addresses = new HashSet<>();
	
	@OneToMany(targetEntity = CartItem.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference(value = "cart")
	private List<CartItem> cartItems = new ArrayList<>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String fullName, String userName, String password, String email, String mobile, Role role) {
		super();
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
	}
			
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [fullName=" + fullName + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", mobile=" + mobile + ", role=" + role + ", addresses=" + addresses + "]";
	}
		
}