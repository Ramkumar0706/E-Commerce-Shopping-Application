package com.retail.ecom.service.impl;

import java.util.Random;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.retail.ecom.enums.UserRole;
import com.retail.ecom.exception.InvalidUserRoleSpecfiedException;
import com.retail.ecom.exception.UserAlreadyExistsByEmailException;
import com.retail.ecom.model.Customer;
import com.retail.ecom.model.Seller;
import com.retail.ecom.model.User;
import com.retail.ecom.repository.CustomerRepostiory;
import com.retail.ecom.repository.SellerRepostiory;
import com.retail.ecom.repository.UserRepostiory;
import com.retail.ecom.requestdto.UserRequest;
import com.retail.ecom.responsedto.UserResponse;
import com.retail.ecom.service.UserService;
import com.retail.ecom.utility.ResponseStructure;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class UserServiceImpl  implements UserService {
	private CustomerRepostiory customerRepository;
	private SellerRepostiory sellerRepository;
	private UserRepostiory userRepository;
	private ResponseStructure<UserResponse> userResponseStructure;
	
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> UserRegisteration(UserRequest userRequest) {
	//	User user =null;
		if(userRepository.existsByEmail(userRequest.getEmail()))
			throw new UserAlreadyExistsByEmailException("The User Email is Already Present");
	
		/*
		 * if(userRequest.getUserRole()==UserRole.SELLER) {
		 * saveUser=sellerRepository.save((Seller)mapToUser(userRequest,new Seller()));
		 * } else if(userRequest.getUserRole()==UserRole.CUSTOMER){
		 * saveUser=customerRepository.save((Customer)mapToUser(userRequest,new
		 * Customer())); }
		 */
		
		User user=mapToChildEntity(userRequest);
		String otp=generateOTP();
		return ResponseEntity.ok(userResponseStructure.setStatuscode(HttpStatus.CREATED.value())
				.setData(mapToUserResponse(user))
				.setMessage("User Object is created Successfully"));
	}

	private String generateOTP() {
		
		return String.valueOf(new Random().nextInt(6));
	}

	public <T extends User>T  mapToChildEntity(UserRequest userRequest) {
		UserRole role=userRequest.getUserRole();
		User user=null;
		switch(role) {
		case SELLER->user=new Seller();
		case CUSTOMER->user=new Customer();
		default -> throw new InvalidUserRoleSpecfiedException("UserRole is not valid");
		}
		return (T)user;
	}

	public UserResponse mapToUserResponse(User user) {
	
		return UserResponse.builder().displayName(user.getDisplayName())
				.userId(user.getUserId())
				.username(user.getUsername())
				.email(user.getEmail())
				.userRole(user.getUserRole())
				.isEmailVerified(user.isEmailVerified())
				.isDeleted(user.isDeleted())
				.build();
	}

	public User mapToUser(UserRequest userRequest,User user) {
		user.setDisplayName(userRequest.getName());
		user.setPassword(userRequest.getPassword());
		user.setEmail(userRequest.getEmail());
		user.setUsername(userRequest.getEmail().substring(0,userRequest.getEmail().indexOf("@")));
		user.setEmailVerified(true);
		user.setUserRole(userRequest.getUserRole());
		return user;
	}
}
