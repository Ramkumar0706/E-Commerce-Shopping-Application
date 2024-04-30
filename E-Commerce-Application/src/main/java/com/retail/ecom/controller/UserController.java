package com.retail.ecom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.ecom.jwt.JwtService;
import com.retail.ecom.requestdto.AuthRequest;
import com.retail.ecom.requestdto.OTPRequest;
import com.retail.ecom.requestdto.UserRequest;
import com.retail.ecom.responsedto.AuthResponse;
import com.retail.ecom.responsedto.UserResponse;
import com.retail.ecom.service.UserService;
import com.retail.ecom.utility.ResponseStructure;
import com.retail.ecom.utility.SimpleResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("api/ecav1")
@CrossOrigin(allowCredentials = "true",origins = "http://localhost:5173/")
public class UserController {
	private UserService userService;
	private JwtService jwtService;


	@PostMapping("/users/register")
	public ResponseEntity<SimpleResponseStructure>UserRegister(@RequestBody@Valid UserRequest userRequest){
		return userService.UserRegisteration(userRequest);
	}
	@PostMapping("/verify-email")
	public  ResponseEntity<ResponseStructure<UserResponse>>verifiedOTP(@RequestBody @Valid OTPRequest otpRequest){
		return userService.verifiedOTP(otpRequest);
	}

	@GetMapping("test")
	public String getMethodName() {
		return jwtService.genaretAccessToken("ram", "CUSTOMER");
	}
	


	@PostMapping("/user/login")
	public ResponseEntity<ResponseStructure<AuthResponse>>userLogin(@RequestBody AuthRequest authRequest){
		return userService.userLogin(authRequest);
	}

	@PostMapping("/user/logout")
	public ResponseEntity<SimpleResponseStructure>userLogout(@CookieValue(name = "rt",required = false)String refreshToken
			,@CookieValue(name="at", required = false)String accessToken){
		System.out.println(accessToken);
		System.out.println(refreshToken);
		return userService.userLogout(refreshToken,accessToken);
	}
	
	 @PostMapping("/login/refresh")
	    public ResponseEntity<ResponseStructure<AuthResponse>> refreshLogin(@CookieValue(name = "rt", required = false) String refreshToken,
	                                                                        @CookieValue(name = "at", required = false) String accessToken) {
	        return userService.refreshLogin(refreshToken, accessToken);
	    }
}
