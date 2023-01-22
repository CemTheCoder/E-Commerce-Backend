package com.project.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dao.CreditCardDao;
import com.project.ecommerce.entities.CreditCard;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.requests.CreditCardRequest;

@Service
public class CreditCardService {

	@Autowired
	private CreditCardDao dao;
	
	@Autowired
	private UserService userService;
	
	
	public CreditCard getById(int id) {
		return this.dao.findById(id);
	}
	
	public List<CreditCard> cards(int id) {
		return this.dao.findByUserId(id);
	}
	
	public CreditCard create(CreditCardRequest r) {
		CreditCard c = new CreditCard();
		c.setBalance(100000);
		c.setCardName(r.getCardName());
		c.setCardNumber(r.getCardNumber());
		c.setCvc(r.getCvc());
		c.setDate(r.getDate());
		
		User user = this.userService.getUser(r.getUserId());
		
		c.setUser(user);
		
		return this.dao.save(c);
	}
	
	public void payment(int id , double price) {
		CreditCard c = this.dao.findById(id);
		c.setBalance(c.getBalance()-price);
		this.dao.save(c);
	}
	
	public void delete(int id) {
		CreditCard c = this.dao.findById(id);
		this.dao.delete(c);
	}
	

}
