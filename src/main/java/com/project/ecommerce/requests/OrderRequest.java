package com.project.ecommerce.requests;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.project.ecommerce.entities.Address;
import com.project.ecommerce.entities.CreditCard;
import com.project.ecommerce.entities.User;

public class OrderRequest {

	private int id;
	
	
	private double price;
	
	
	private User user;
	
	
	
	private Address address;
	
	
	
	private CreditCard creditCard;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public CreditCard getCreditCard() {
		return creditCard;
	}



	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	
	
	

}
