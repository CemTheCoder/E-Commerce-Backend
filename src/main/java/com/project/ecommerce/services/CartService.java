package com.project.ecommerce.services;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;

import com.project.ecommerce.dao.CartDao;
import com.project.ecommerce.dao.CartItemDao;
import com.project.ecommerce.dao.ProductDao;
import com.project.ecommerce.dao.UserDao;
import com.project.ecommerce.entities.Cart;
import com.project.ecommerce.entities.CartItem;
import com.project.ecommerce.entities.Product;
import com.project.ecommerce.requests.CartRequest;

@Service
public class CartService  {
	@Autowired
	private CartItemDao dao;
	
	@Autowired
	private CartDao cartdao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProductDao productDao;
	

	public CartService(CartItemDao dao,UserDao userDao,ProductDao productDao) {
		this.userDao = userDao;
		this.productDao = productDao;
		this.dao = dao;
	}
	
	
	public CartItem create(CartItem c) {
		if(this.dao.findByUserIdAndProductId(c.getUser().getId(), c.getProduct().getId()) !=null) {
			 CartItem cartItem = this.dao.findByUserIdAndProductId(c.getUser().getId(),c.getProduct().getId());
			 cartItem.setQuantity(cartItem.getQuantity()+1);
			 Cart cart = this.cartdao.findByUserId(c.getUser().getId());
			 cart.setTotal(cart.getTotal()+c.getProduct().getPrice());
			 this.cartdao.save(cart);
			return this.dao.save(cartItem);
		}
		else {
			 Cart cart = this.cartdao.findByUserId(c.getUser().getId());
			 cart.setTotal(cart.getTotal()+c.getProduct().getPrice());
			 this.cartdao.save(cart);
			
			c.setQuantity(1);
			
			return this.dao.save(c);
		}
		
	}
	
	
	public void delete(CartItem c) {
		if(this.dao.findByUserIdAndProductId(c.getUser().getId(), c.getProduct().getId()) !=null) {
			 CartItem cartItem = this.dao.findByUserIdAndProductId(c.getUser().getId(),c.getProduct().getId());
			 if(cartItem.getQuantity()>1) {
				 cartItem.setQuantity(cartItem.getQuantity()-1);
				 Cart cart = this.cartdao.findByUserId(c.getUser().getId());
				 cart.setTotal(cart.getTotal()-c.getProduct().getPrice());
				 this.cartdao.save(cart);
				 this.dao.save(cartItem);
			 }else {
				 Cart cart = this.cartdao.findByUserId(c.getUser().getId());
				 cart.setTotal(cart.getTotal()-c.getProduct().getPrice());
				 this.cartdao.save(cart);
					 this.dao.delete(cartItem);
				}
		}
	}

	
	
	public void del(int id) {
		CartItem c =this.dao.getById(id);
		this.dao.delete(c);
	}
	
	
	public List<CartItem> findByUserId(int id) {
		return this.dao.findByUserId(id);
	}
	
	public Cart getByUserId(int id) {
		return this.cartdao.findByUserId(id);
	}

}
