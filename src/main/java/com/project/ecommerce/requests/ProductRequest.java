package com.project.ecommerce.requests;

import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.entities.Category;
import com.project.ecommerce.entities.Seller;

public class ProductRequest {

	
	private int id;
	
	private String productName;
	
	private String brand;
	
	private double price;
	
	private int stock;
	
	private MultipartFile avatar;
	
	private int categoryId;
	
	private String description;

	private Category category;
	
	private Seller seller;

	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
