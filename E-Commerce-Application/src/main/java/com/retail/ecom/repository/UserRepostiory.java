package com.retail.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retail.ecom.model.Customer;
import com.retail.ecom.model.Seller;
import com.retail.ecom.model.User;
@Repository
public interface UserRepostiory extends JpaRepository<User, Integer> {
	
	boolean existsByEmail(String email);

}




