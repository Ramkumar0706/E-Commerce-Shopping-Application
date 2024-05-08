package com.retail.ecom.controller;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

import com.retail.ecom.model.Product;
import com.retail.ecom.repository.ProductRepository;
import com.retail.ecom.requestdto.ProductRequest;
import com.retail.ecom.requestdto.SearchFilter;
import com.retail.ecom.responsedto.ProductResponse;
import com.retail.ecom.service.ProductService;
import com.retail.ecom.utility.ProductSpecification;
import com.retail.ecom.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/ecav1")
@CrossOrigin(allowCredentials = "true" , origins =  "http://localhost:5173")
public class ProductController {

	private ProductService productService;
	private ProductRepository productRepository;
	

	@PreAuthorize("hasAuthority('SELLER')")
	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<ProductResponse>> addProduct(@RequestBody ProductRequest productRequest){
		return productService.addProduct(productRequest);
	}

	@PreAuthorize("hasAuthority('SELLER')")
	@PutMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<ProductResponse>> updateProduct(@RequestBody ProductRequest productRequest,@PathVariable int productId){
		return productService.updateProduct(productRequest,productId);
	}

	@GetMapping("/products")
	public ResponseEntity<?> findProducts(){
		return productService.findProducts();
	}



	@GetMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<ProductResponse>> findByProductId(@PathVariable int productId){
		return productService.findByProductId(productId);
	}


	@GetMapping("/findProduct")
	public ResponseEntity<?> findProductByFilter(SearchFilter searchFilter) {
		return productService.findProductByFilter(searchFilter);
	}


}