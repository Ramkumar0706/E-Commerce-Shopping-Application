package com.retail.ecom.requestdto;

import com.retail.ecom.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@AllArgsConstructor
@Getter
@Setter
public class UserRequest {
	@NotBlank(message = "fill the name")
	private String name;
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$",message = "fill the email with last @gmail.com")
	@NotNull(message = "Fill the email")
	private String email;
	@NotNull(message = "Password is required")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must"
			+ " contain at least one letter, one number, one special character")
	private String password;
	private UserRole userRole;

}
