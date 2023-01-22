package com.project.ecommerce.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "products")

public class Product {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "price")
	private double price;
	
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	
	
	@ManyToOne()
	@JoinColumn(name = "category_id" , referencedColumnName = "id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "seller_id" , referencedColumnName = "id")
	private Seller seller;
	
	@ManyToOne
	@JoinColumn(name = "user_id" , referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "order_id" , referencedColumnName = "id")
	private Order order;
	


	@OneToMany(mappedBy = "product" ,fetch = FetchType.LAZY ,cascade = CascadeType.MERGE)
	@JsonIgnore
	private List<CartItem> cart;
	
    
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
	public List<CartItem> getCart() {
		return cart;
	}
	public void setCart(List<CartItem> cart) {
		this.cart = cart;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	

	}
