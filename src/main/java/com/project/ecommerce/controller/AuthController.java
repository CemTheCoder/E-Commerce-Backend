package com.project.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.dao.CartDao;
import com.project.ecommerce.dao.UserDao;
import com.project.ecommerce.entities.Cart;
import com.project.ecommerce.entities.User;
import com.project.ecommerce.requests.UserRequest;
import com.project.ecommerce.responses.AuthResponse;
import com.project.ecommerce.security.JwtTokenProvider;
import com.project.ecommerce.services.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider provider;
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody UserRequest loginRequest) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword());
		Authentication auth = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwtToken = provider.generateJwtToken(auth);
		User user = userService.getOneUserByEmail(loginRequest.getEmail());
		AuthResponse authResponse = new AuthResponse();
		authResponse.setMessage("Bearer " + jwtToken);
		authResponse.setUserId(user.getId());
		authResponse.setRoleId(user.getRoleId());
		return authResponse ;
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register (@RequestBody UserRequest registerRequest) {
		AuthResponse authResponse = new AuthResponse();
		if(userService.getOneUserByEmail(registerRequest.getEmail()) != null) {
			authResponse.setMessage("Email already in use.");
			return new ResponseEntity<>(authResponse,HttpStatus.BAD_REQUEST);
		}
		else {
		User user = new User();
		user.setEmail(registerRequest.getEmail());
		user.setPassword(encoder.encode(registerRequest.getPassword()));
		user.setRoleId(2);
		userService.save(user);
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setTotal(0);
		this.cartDao.save(cart);
		authResponse.setMessage("User succesfully registered.");
		return new ResponseEntity<>(authResponse,HttpStatus.CREATED);
		}
		}

}
