package com.project.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dao.UserDao;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.security.JwtUserDetails;

@Service
public class UserDetailServiceImp implements UserDetailsService{
	@Autowired
	private UserDao dao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = dao.findByEmail(email);
		return JwtUserDetails.create(user);
	}
	
	
	public UserDetails loadUserById(int id ) {
		User user = dao.findById(id);
		return JwtUserDetails.create(user);
	}

}
