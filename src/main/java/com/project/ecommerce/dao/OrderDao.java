package com.project.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Order;

public interface OrderDao  extends JpaRepository<Order, Integer>{
	
	public Order findById(int id);
	
	public List<Order> findByUserId(int id);

}
