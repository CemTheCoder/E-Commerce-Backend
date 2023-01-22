package com.project.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.entities.CreditCard;
@Repository
public interface CreditCardDao extends JpaRepository<CreditCard, Integer> {
	
	public CreditCard findById(int id);
	
	public List<CreditCard> findByUserId(int id);

}
