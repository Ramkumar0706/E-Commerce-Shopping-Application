package com.retail.ecom.responsedto;

import java.util.List;
import java.util.Optional;

import com.retail.ecom.enums.AvailabilityStatus;
import com.retail.ecom.enums.Category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ProductResponse {
	
	private int productId;
	private String productBrand;
	private String productModel;
	private String productDescription;
	private double productPrice;
	private int productQuantity;
	private Category category;
	private AvailabilityStatus availabilityStatus;
	private Optional<String> coverImage;
	private Optional<List<String>> normalImage;

}
