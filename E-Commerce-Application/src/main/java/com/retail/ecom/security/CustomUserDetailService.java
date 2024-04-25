package com.retail.ecom.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.retail.ecom.repository.UserRepostiory;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

	private UserRepostiory userRepostiory;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepostiory.findByUsername(username)
				.map((user)->new CustomUserDetails(user))
				.orElseThrow(()->new UsernameNotFoundException("user email is not found in database"));
	}
}
