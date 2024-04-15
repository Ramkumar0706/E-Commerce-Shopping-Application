package com.retail.ecom.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.ecom.model.Customer;

public interface CustomerRepostiory extends JpaRepository<Customer, Integer> {
	

}