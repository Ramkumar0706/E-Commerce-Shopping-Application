package com.retail.ecom.service;

import org.springframework.http.ResponseEntity;

import com.retail.ecom.exception.InvalidEmailException;
import com.retail.ecom.requestdto.AuthRequest;
import com.retail.ecom.requestdto.OTPRequest;
import com.retail.ecom.requestdto.UserRequest;
import com.retail.ecom.responsedto.AuthResponse;
import com.retail.ecom.responsedto.UserResponse;
import com.retail.ecom.utility.ResponseStructure;
import com.retail.ecom.utility.SimpleResponseStructure;

import jakarta.validation.Valid;

public interface UserService {
	public ResponseEntity<SimpleResponseStructure> UserRegisteration(UserRequest userRequest) ;

	public ResponseEntity<ResponseStructure<UserResponse>> verifiedOTP( OTPRequest otpRequest);

	public ResponseEntity<ResponseStructure<AuthResponse>> userLogin(AuthRequest authRequest);

	public ResponseEntity<SimpleResponseStructure> userLogout(String refreshToken, String accessToken);

	public ResponseEntity<ResponseStructure<AuthResponse>> refreshLogin(String refreshToken, String accessToken);

}
