package com.retail.ecom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.retail.ecom.requestdto.UserRequest;
import com.retail.ecom.responsedto.UserResponse;
import com.retail.ecom.service.UserService;
import com.retail.ecom.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
public class UserController {
	private UserService userService;
	
	
	@PostMapping("/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>>UserRegister(@RequestBody@Valid UserRequest userRequest){
		return userService.UserRegisteration(userRequest);
	}

}
