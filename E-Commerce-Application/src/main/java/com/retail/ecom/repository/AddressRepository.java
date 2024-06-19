package com.retail.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.ecom.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
