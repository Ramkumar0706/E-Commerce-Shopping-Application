package com.retail.ecom.responsedto;

import com.retail.ecom.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class UserResponse {
	private int userId;
	private String displayName;
	private String username;
	private String email;
	private UserRole userRole;
	private boolean isEmailVerified;
	private boolean isDeleted;


}
