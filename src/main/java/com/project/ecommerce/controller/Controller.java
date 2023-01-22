package com.project.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.dao.CartDao;
import com.project.ecommerce.dao.CartItemDao;
import com.project.ecommerce.dao.ProductDao;
import com.project.ecommerce.dao.UserDao;
import com.project.ecommerce.entities.Address;
import com.project.ecommerce.entities.Cart;
import com.project.ecommerce.entities.CartItem;
import com.project.ecommerce.entities.Category;
import com.project.ecommerce.entities.CreditCard;
import com.project.ecommerce.entities.Order;
import com.project.ecommerce.entities.Product;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.requests.AddressRequest;
import com.project.ecommerce.requests.CartRequest;
import com.project.ecommerce.requests.CreditCardRequest;
import com.project.ecommerce.requests.OrderRequest;
import com.project.ecommerce.requests.ProductRequest;
import com.project.ecommerce.services.AddressService;
import com.project.ecommerce.services.CartService;
import com.project.ecommerce.services.CategoryService;
import com.project.ecommerce.services.CreditCardService;
import com.project.ecommerce.services.OrderService;
import com.project.ecommerce.services.ProductService;
import com.project.ecommerce.services.UserService;

@RestController
@CrossOrigin
public class Controller {
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService service;

	@Autowired
	private CartItemDao cartDao;

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private CreditCardService creditCardService;

	@GetMapping("/products")
	public List<Product> getall() {
		return this.service.allke();
	}

	@GetMapping("/productske")
	public List<Product> getke(@RequestParam(name = "id") int id) {

		return this.service.getByCategory(id);
	}

	@GetMapping("/product")
	public Product getOne(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		return this.service.getOne(id);

	}

	@PutMapping("/product")
	public Product updateOne(@RequestParam(name = "id") int id, @RequestBody ProductRequest p) {

		return this.service.updateOneProductById(id, p);

	}

	@DeleteMapping("/products")
	public void deleteById(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		this.service.deleteProductById(id);
	}

	@DeleteMapping("/product")
	public void deletePro(@RequestBody Product p) {
		this.service.deleteProduct(p);
	}

	@GetMapping("/users")
	public List<User> users() {
		return this.userDao.findAll();
	}
	
	@GetMapping("/user")
	public User getUserbyId(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		return this.userService.getUser(id);
	}

	@GetMapping("/carts")
	public List<CartItem> carts() {
		return this.cartDao.findAll();
	}

	@PostMapping("/cart")
	public CartItem createCart(@RequestBody CartItem c) {
		return this.cartService.create(c);
	}

	@DeleteMapping("/cart")
	public void delete(@RequestBody CartItem c) {
		this.cartService.delete(c);
	}

	@DeleteMapping("/cartke")
	public void deleteCart(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		this.cartService.del(id);
	}

	@GetMapping("/cartitems")
	public List<CartItem> getByUserId(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		return this.cartService.findByUserId(id);
	}

	@GetMapping("/cart")
	public Cart getUserCart(@RequestParam(name = "id") int id) {
		return this.cartService.getByUserId(id);
	}

	@PostMapping("/product")
	public void saveProduct(@ModelAttribute("file") MultipartFile file, @RequestParam("productName") String name,
			@RequestParam("price") double price, @RequestParam("description") String description,
			@RequestParam("brand") String brand, @RequestParam("stock") int stock,
			@RequestParam("categoryId") int categoryId, @RequestParam("userId") int userId) {
		this.service.saveProductToDB(file, name, description, price, brand, stock, categoryId, userId);

	}
	
	@PostMapping("/avatar")
	public void uploadAvatar(@ModelAttribute("file") MultipartFile file ,@RequestParam("id") int id) {
		this.userService.uploadImage(file, id);
	}

	@GetMapping("/categories")
	public List<Category> getCategories() {
		return this.categoryService.categories();
	}

	@GetMapping("/category")
	public Category CategoryById(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		return this.categoryService.byId(id);
	}

	@GetMapping("/products/byname")
	public List<Product> productsByName(
			@RequestParam(name = "name", required = false, defaultValue = "") String productName) {
		return this.service.getProductsByName("%" + productName + "%");
	}
	
	@PostMapping("/credit")
	public CreditCard createCard(@RequestBody CreditCardRequest r) {
		return this.creditCardService.create(r);
	}
	
	@GetMapping("/card")
	public CreditCard getCard(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		return this.creditCardService.getById(id);
	}
	
	@GetMapping("/cards")
	public List<CreditCard> creditCards(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		return this.creditCardService.cards(id);
	}
	
	@DeleteMapping("/card")
	public void deleteCard(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		this.creditCardService.delete(id);
	}
	
	@PostMapping("/address")
	public Address createAddress(@RequestBody AddressRequest r) {
		return this.addressService.create(r);
	}
	
	@GetMapping("/address")
	public Address getAddress(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		return this.addressService.getById(id);
	}
	
	@DeleteMapping("/address")
	public void deleteAddress(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		this.addressService.deleteke(id);
	}

	@GetMapping("/addressesmain")
	public List<Address> getAddresses() {
		return this.addressService.addresses();
	}
	

	@GetMapping("/addresses")
	public List<Address> getAddresses(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		return this.addressService.byUser(id);
	}
	
	@PostMapping("/order")
	public Order createOrder(@RequestBody OrderRequest r) {
		return this.orderService.create(r);
	}
	
	@PostMapping("/payment")
	public void mainPayment(@RequestParam(name = "id", required = false, defaultValue = "") int id,
			@RequestParam(name = "price", required = false, defaultValue = "") double price) {
		
		this.creditCardService.payment(id, price);
	}
	
	@GetMapping("/orders")
	public List<Order> getOrders(@RequestParam(name = "id", required = false, defaultValue = "") int id) {
		return this.orderService.orders(id);
	}
}
