package com.project.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entities.Cart;


@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

	public Cart findByUserId(int userId);

}
