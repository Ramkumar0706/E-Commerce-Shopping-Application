package com.retail.ecom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.retail.ecom.enums.AvailabilityStatus;
import com.retail.ecom.enums.Category;
import com.retail.ecom.model.Product;
import com.retail.ecom.responsedto.SearchFilter;

import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class ProductSpecification {
	
	
	

	public Specification<Product> buildSpecification(SearchFilter filter){
		
		return (root,query,builder)->{
			List<Predicate> predicate=new ArrayList<>();
			if(filter.getMinPrice()>0)
				predicate.add(builder.greaterThanOrEqualTo(root.get("productPrice")
						,filter.getMinPrice()));
			if(filter.getMaxPrice()>0)
				predicate.add(builder.lessThanOrEqualTo(root.get("productPrice"),filter.getMaxPrice()));
			if(filter.getCategory()!=null)
				predicate.add(builder.equal(root.get("category"),Category.valueOf(filter.getCategory())));
		if(filter.getAvailability()!=null)
			predicate.add(builder.equal(root.get("availabilityStatus"), AvailabilityStatus.valueOf(filter.getAvailability())));
		if(filter.getRating()>0) {
			
		}
		if(filter.getDiscount()>0) {
			
		}
		Predicate[] predicates = predicate.toArray(new Predicate[0]);
		return builder.and(predicates);
		};
			

	}



	

}




