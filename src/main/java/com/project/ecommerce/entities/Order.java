package com.project.ecommerce.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="orders")
public class Order {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "price")
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "user_id" , referencedColumnName = "id")
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name = "address_id" , referencedColumnName = "id")
	private Address address;
	
	
	@ManyToOne
	@JoinColumn(name = "card_id" , referencedColumnName = "id")
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
