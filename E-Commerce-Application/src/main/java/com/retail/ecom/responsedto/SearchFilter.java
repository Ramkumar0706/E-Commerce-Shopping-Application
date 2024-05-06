package com.retail.ecom.responsedto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SearchFilter {
	private int minPrice;
	private int maxPrice;
	private String category;
	
	private int rating;
	private int discount;
	private String availability;
	

}
