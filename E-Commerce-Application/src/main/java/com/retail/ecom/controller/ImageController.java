package com.retail.ecom.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.retail.ecom.enums.ImageType;
import com.retail.ecom.model.Image;
import com.retail.ecom.repository.ImageRepository;
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
	private ImageRepository imageRepository;
	@PostMapping("/products/{productId}/image-type/{imageType}/image")
	public ResponseEntity<SimpleResponseStructure> addImage(@RequestParam int productId,
			@RequestParam String imageType,
			@RequestParam MultipartFile image) {
		return imageServie.addImage(productId,imageType,image);

	}
	@GetMapping("image/{imageId}")
	public ResponseEntity<byte[]>findById(@PathVariable String imageId){
		return imageServie.findById(imageId);
	}

	@GetMapping("/product/image/{productId}")
	public ResponseEntity<byte[]>findById(@PathVariable int productId){
		return imageServie.findByproductId(productId);
	}

	@GetMapping("/product/image")
	public Optional<String>  imageFindByProductId(int productId,ImageType imageType) {
		Optional<Image> imageOptional = imageRepository.findImageIdByProductIdAndImageType(productId,imageType);
		return imageOptional.map(image -> image.getImageId());
	}
	@GetMapping("/product/images")
	public Optional<List<String>>  imagesFindByProductId(int productId,ImageType imageType) {
		Optional<List<Image>> imageOptional = imageRepository.findImageIdsByProductIdAndImageType(productId,imageType);
		return imageOptional.map(images -> images.stream().map(Image::getImageId).collect(Collectors.toList()));
	}





}
