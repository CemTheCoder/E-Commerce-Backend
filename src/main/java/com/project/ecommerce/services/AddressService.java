package com.project.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dao.AddressDao;
import com.project.ecommerce.entities.Address;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.requests.AddressRequest;

@Service
public class AddressService {

	@Autowired
	private AddressDao dao;
	
	@Autowired
	private UserService userService;
	
	public Address getById(int id) {
		return this.dao.findById(id);
	}
	
	public List<Address> addresses() {
		return this.dao.findAll();
	}
	
	public List<Address> byUser(int id) {
		return this.dao.findByUserId(id);
	}
	
	public Address create(AddressRequest r) {
		Address a = new Address();
		a.setAddress(r.getAddress());
		a.setAddressName(r.getAddressName());
		a.setCity(r.getCity());
		a.setNumber(r.getNumber());
		
		User user = this.userService.getUser(r.getUserId());
		
		a.setUser(user);
		a.setCustomerName(user.getName());
		a.setCustomerSurname(user.getSurname());
		return this.dao.save(a);
	}
	
	public void deleteke(int id) {
		Address a = this.dao.findById(id);
		this.dao.delete(a);
	}
	
	

}
