package com.retail.ecom.responsedto;

import com.retail.ecom.enums.UserRole;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class AuthResponse {
	private int userId;
	private String username;
	private long accessExpiration;
	private long refreshExpiration;
	private boolean isAuthenticated;
	private UserRole role;

}
