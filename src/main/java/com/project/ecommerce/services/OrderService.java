package com.project.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dao.CartDao;
import com.project.ecommerce.dao.CartItemDao;
import com.project.ecommerce.dao.OrderDao;
import com.project.ecommerce.dao.UserDao;
import com.project.ecommerce.entities.Cart;
import com.project.ecommerce.entities.Order;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.requests.OrderRequest;

@Service
public class OrderService {
	
	@Autowired
	private OrderDao dao;

	@Autowired
	private CartItemDao cartItemDao;
	
	@Autowired
	private CartDao cartDao;
	
	
	public Order create(OrderRequest r) {
		Order o = new Order();
		o.setCreditCard(r.getCreditCard());
		o.setAddress(r.getAddress());
		o.setUser(r.getUser());
		o.setPrice(r.getPrice());
		this.cartItemDao.deleteAll();
		Cart cart = this.cartDao.findByUserId(r.getUser().getId());
		cart.setTotal(0);
		this.cartDao.save(cart);
		return this.dao.save(o);
	}
	
	public List<Order> orders(int id) {
		
		return this.dao.findByUserId(id);
	}
	
}
