package com.retail.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.retail.ecom.model.User;

import java.util.Optional;


@Repository
public interface UserRepostiory extends JpaRepository<User, Integer> {
	
	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String username);

}




