package com.project.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Integer> {
	
	public List<CartItem> findByUserId(int id);
	
	boolean existsCartItemByUserIdAndProductId(int userId,int productId);

	public CartItem findByUserIdAndProductId(int userId,int productId);
	
	
}


