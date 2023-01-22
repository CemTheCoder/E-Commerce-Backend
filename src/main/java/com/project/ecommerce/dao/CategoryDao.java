package com.project.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

	
	
	public Category findById(int id);
	
	public Category findByCategoryName(String name);
	
}
