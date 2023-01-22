package com.project.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ecommerce.entities.User;

public interface UserDao extends JpaRepository<User, Integer>{

	
	public User findById(int id);
	
	public User findByEmail(String email);
	public User findByName(String name);
}
