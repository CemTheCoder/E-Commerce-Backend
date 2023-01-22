package com.project.ecommerce.config;

import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.project.ecommerce.security.JwtAuthenticationEntryPoint;
import com.project.ecommerce.security.JwtAuthenticationFilter;
import com.project.ecommerce.services.UserDetailServiceImp;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailServiceImp userDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint handler;
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	 	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Override
	    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }
	    
	    @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowedOriginPatterns(Arrays.asList("*"));
	       
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("OPTIONS");
	        config.addAllowedMethod("HEAD");
	        config.addAllowedMethod("GET");
	        config.addAllowedMethod("PUT");
	        config.addAllowedMethod("POST");
	        config.addAllowedMethod("DELETE");
	        config.addAllowedMethod("PATCH");
	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }
	    
	    @Override
	    public void configure(HttpSecurity httpSecurity) throws Exception {
	    	httpSecurity
	    		.cors()
	    		.and()
	    		.csrf().disable()
	    		.exceptionHandling().authenticationEntryPoint(handler).and()
	    		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	    		.authorizeRequests()
	    		.antMatchers(HttpMethod.GET, "/products")
	    		.permitAll()
	    		.antMatchers(HttpMethod.POST, "/order")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/orders")
	    		.permitAll()
	    		.antMatchers(HttpMethod.POST, "/payment")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/user")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/cards")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/card")
	    		.permitAll()
	    		.antMatchers(HttpMethod.DELETE, "/card")
	    		.permitAll()
	    		.antMatchers(HttpMethod.POST, "/credit")
	    		.permitAll()
	    		.antMatchers(HttpMethod.POST, "/address")
	    		.permitAll()
	    		.antMatchers(HttpMethod.DELETE, "/address")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/address")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/addresses")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/addressesmain")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/categories")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/category")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/products/byname")
	    		.permitAll()
	    		.antMatchers(HttpMethod.POST, "/avatar")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/avatar")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/avatars")
	    		.permitAll()
	    		.antMatchers(HttpMethod.POST, "/product")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/product/**")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/productske")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/cartitems")
	    		.permitAll()
	    		.antMatchers(HttpMethod.POST, "/cart")
	    		.permitAll()
	    		.antMatchers(HttpMethod.DELETE, "/cart")
	    		.permitAll()
	    		.antMatchers(HttpMethod.GET, "/cart")
	    		.permitAll()
	    		.antMatchers("/auth/**")
	    		.permitAll()
	    		.anyRequest().authenticated();
	    	
	    	httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	    	
	    		
	    		
	    }
	
}

	
