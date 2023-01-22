package com.project.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entities.Address;
@Repository
public interface AddressDao extends JpaRepository<Address, Integer>{
	
	public Address findById(int id);

	public List<Address> findByUserId(int id);
}
