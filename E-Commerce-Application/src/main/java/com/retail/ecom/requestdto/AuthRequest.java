package com.retail.ecom.requestdto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthRequest {
	private String username;
	private String password;
	

}
