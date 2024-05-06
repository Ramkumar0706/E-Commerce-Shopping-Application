package com.retail.ecom.service;

import org.springframework.http.ResponseEntity;

import com.retail.ecom.requestdto.ProductRequest;
import com.retail.ecom.responsedto.ProductResponse;
import com.retail.ecom.utility.ResponseStructure;

public interface ProductService {

	ResponseEntity<ResponseStructure<ProductResponse>> addProduct(ProductRequest productRequest);

	ResponseEntity<ResponseStructure<ProductResponse>> updateProduct(ProductRequest productRequest, int productId);

	ResponseEntity<ResponseStructure<ProductResponse>> findProducts();

	ResponseEntity<ResponseStructure<ProductResponse>> findByProductId(int productId);

}
