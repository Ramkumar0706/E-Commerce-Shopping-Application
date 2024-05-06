package com.retail.ecom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.retail.ecom.service.ImageService;
import com.retail.ecom.service.ProductService;
import com.retail.ecom.utility.ResponseStructure;
import com.retail.ecom.utility.SimpleResponseStructure;

import lombok.AllArgsConstructor;
@RestController
@AllArgsConstructor
@RequestMapping("api/ecav1")
@CrossOrigin(allowCredentials = "true" , origins =  "http://localhost:5173")
public class ImageController {
	private ImageService imageServie;
	@PostMapping("/products/{productId}/image-type/{imageType}/image")
	public ResponseEntity<SimpleResponseStructure> addImage(@RequestParam int productId,
			@RequestParam String imageType,
			@RequestParam MultipartFile image) {
		return imageServie.addImage(productId,imageType,image);
		
	}
	@GetMapping("image/{imageId}")
	public ResponseEntity<byte[]>findById(@RequestParam String imageId){
		return imageServie.findById(imageId);
	}

}
