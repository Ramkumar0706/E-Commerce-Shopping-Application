package com.retail.ecom.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.retail.ecom.model.Product;
import com.retail.ecom.requestdto.ProductRequest;
import com.retail.ecom.requestdto.SearchFilter;
import com.retail.ecom.responsedto.ProductResponse;
import com.retail.ecom.utility.ResponseStructure;

public interface ProductService {

	ResponseEntity<ResponseStructure<ProductResponse>> addProduct(ProductRequest productRequest);

	ResponseEntity<ResponseStructure<ProductResponse>> updateProduct(ProductRequest productRequest, int productId);

	ResponseEntity<?> findProducts();

	ResponseEntity<ResponseStructure<ProductResponse>> findByProductId(int productId);

	ResponseEntity<?> findProductByFilter(SearchFilter searchFilter);

}
