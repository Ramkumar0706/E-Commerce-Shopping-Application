package com.retail.ecom.requestdto;

import org.springframework.stereotype.Component;

import com.retail.ecom.enums.AvailabilityStatus;
import com.retail.ecom.enums.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
public class ProductRequest {
	
	@NotNull(message = "product name is requred")
	@NotBlank(message = "product name is required")
	private String productBrand;
	private String productModel;
	private String productDescription;
	@NotNull(message = "product price is requred")
	@NotBlank(message = "product price is required")
	private double productPrice;
	private int productQuantity;
	private Category category;
	private AvailabilityStatus availabilityStatus;

}