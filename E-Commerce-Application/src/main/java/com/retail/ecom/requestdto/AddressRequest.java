package com.retail.ecom.requestdto;

import com.retail.ecom.enums.AddressType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class AddressRequest {
	// @NotBlank
	  //  @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Street address must be alphanumeric")
	    private String streetAddress;

	  //  @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Street address additional must be alphanumeric or blank")
	    private String streetAddressAdditional;

	  //  @NotBlank
	   // @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "City must contain only alphabets and spaces")
	    private String city;

	   // @NotBlank
	   // @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "State must contain only alphabets and spaces")
	    private String state;

	   // @NotBlank
	   // @Pattern(regexp = "^\\d+$", message = "Pincode must be numeric")
	    private String pincode;

	   // @NotNull(message = "Address type is required")
	    private AddressType addressType;

}
