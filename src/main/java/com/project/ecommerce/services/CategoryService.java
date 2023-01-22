package com.project.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dao.CategoryDao;
import com.project.ecommerce.entities.Category;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public Category getOneCategory(String name) {
		return this.categoryDao.findByCategoryName(name);
	}
	
	public Category byId(int id) {
		return this.categoryDao.findById(id);
	}
	
	public List<Category> categories() { 
		return this.categoryDao.findAll();
	}
	
	
}
