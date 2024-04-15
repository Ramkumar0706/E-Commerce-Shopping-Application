package com.retail.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.ecom.model.Seller;

public interface SellerRepostiory extends JpaRepository<Seller, Integer> {
	
	

}