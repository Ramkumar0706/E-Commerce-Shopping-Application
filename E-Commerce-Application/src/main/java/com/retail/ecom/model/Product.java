package com.retail.ecom.model;



import com.retail.ecom.enums.AvailabilityStatus;
import com.retail.ecom.enums.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String productBrand;
	private String productModel;
	private String productDescription;
	private double productPrice;
	private int productQuantity;
	private Category category;
	private AvailabilityStatus availabilityStatus;
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productBrand=" + productBrand + ", productModel=" + productModel
				+ ", productDescription=" + productDescription + ", productPrice=" + productPrice + ", productQuantity="
				+ productQuantity + ", category=" + category + ", availabilityStatus=" + availabilityStatus + "]";
	}

	
	
}