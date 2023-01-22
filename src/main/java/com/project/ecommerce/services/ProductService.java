package com.project.ecommerce.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.dao.CategoryDao;
import com.project.ecommerce.dao.ProductDao;
import com.project.ecommerce.entities.Product;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.requests.ProductRequest;

@Service
public class ProductService {

	private ProductDao dao;
	private CategoryDao categoryDao;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	public ProductService(ProductDao dao,CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
		this.dao = dao;
	}

	public List<Product> allke() {
		return this.dao.findAll();
	}
	
	public Product createPro(Product p) {
		return this.dao.save(p);
	}
	
	
	public Product getOne(int id) {
		return this.dao.findById(id);
	}
	
	public List<Product> getByCategory(int id) {
		return this.dao.findByCategory(id);
	}
	
	
	public Product updateOneProductById(int id,ProductRequest p) {
		Product product = this.dao.findById(id);
		com.project.ecommerce.entities.Category c = this.categoryDao.findById(p.getCategoryId());
		product.setBrand(p.getBrand());
		product.setPrice(p.getPrice());
		product.setProductName(p.getProductName());
		product.setStock(p.getStock());
		product.setCategory(c);
		return this.dao.save(product);
	}
	public void deleteProductById(int id ) {
		Product p = this.dao.findById(id);
		this.dao.delete(p);
	}
	
	public void deleteProduct(Product p) {
		this.dao.delete(p);
	}
	public void  saveProductToDB(MultipartFile file,String productName,String description
			,double price , String brand , int stock , int categoryId , int userId)
	{
		Product p = new Product();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setDescription(description);
		p.setBrand(brand);
		p.setStock(stock);
        p.setProductName(productName);
        p.setPrice(price);
        
        com.project.ecommerce.entities.Category c =  this.categoryService.byId(categoryId);
        
        p.setCategory(c);
        
        User u = this.userService.getUser(userId);
        
        p.setUser(u);
        
        this.dao.save(p);
	}
	
	public List<Product> getProductsByName(String productName) {
		return this.dao.findByProductName(productName);
	}
	
}
