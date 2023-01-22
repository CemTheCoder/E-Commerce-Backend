package com.project.ecommerce.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entities.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	
	public Product findById(int id);
	
	
	public Optional<Product> findById(Integer id);
	
	@Query("select p from Product p where category_id = ?1 ")
	public List<Product> findByCategory(int id);
	
	
	@Query("select p from Product p where productName like ?1 ")
	public List<Product> findByProductName(String productName); 
}
