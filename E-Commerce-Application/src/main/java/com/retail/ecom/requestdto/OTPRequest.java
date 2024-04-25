package com.retail.ecom.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class OTPRequest {
	@NotBlank(message = "enter the otp to perform the operation")
	private String otp;
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$",message = "fill the email with last @gmail.com")
	@NotNull(message = "Fill the email")
	private String email;

}
