package com.retail.ecom.service;

import org.springframework.http.ResponseEntity;

import com.retail.ecom.requestdto.UserRequest;
import com.retail.ecom.responsedto.UserResponse;
import com.retail.ecom.utility.ResponseStructure;

public interface UserService {
	public ResponseEntity<ResponseStructure<UserResponse>>UserRegisteration(UserRequest userRequest);

}
