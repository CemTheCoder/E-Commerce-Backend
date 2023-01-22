package com.project.ecommerce.services;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.dao.UserDao;
import com.project.ecommerce.entities.User;

@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	
	
	public User getOneUserByEmail(String email) {
		return dao.findByEmail(email);
	}
	
	public User save(User user) {
		return dao.save(user);
	}
	
	public User getUser(int id) {
		return this.dao.findById(id);
	}
	
	public void uploadImage(MultipartFile file,int id) {
		User user  = this.dao.findById(id);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			user.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.dao.save(user);
	}
	
}
