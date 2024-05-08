package com.retail.ecom.service.impl;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.retail.ecom.enums.ImageType;
import com.retail.ecom.exception.ImageNotFormatedExcption;
import com.retail.ecom.exception.ImageNotFoundByIdException;
import com.retail.ecom.exception.ImageTypeInvalidException;
import com.retail.ecom.exception.ProductNotFoundById;
import com.retail.ecom.model.Image;
import com.retail.ecom.repository.ImageRepository;
import com.retail.ecom.repository.ProductRepository;
import com.retail.ecom.service.ImageService;
import com.retail.ecom.utility.SimpleResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageServiceimpl implements ImageService  {
	private ProductRepository productRepository;
	private ImageRepository imageRepository;
	private SimpleResponseStructure responseStructure;
	@Override
	public ResponseEntity<SimpleResponseStructure> addImage(int productId, java.lang.String imageType,
			MultipartFile image) {
		if(!productRepository.existsById(productId)) throw new ProductNotFoundById("the product is not found by Id in the database");
		
		
		if(ImageType.valueOf(imageType.toUpperCase())==ImageType.COVER) {
			imageRepository.findByProductIdAndImageType(productId,ImageType.COVER).ifPresent((img)->{
				img.setImageType(ImageType.NORMAL);
				imageRepository.save(img);
				
			});
		}
		Image image1=new Image();
		image1.setProductId(productId);
		try {
			image1.setImageType(ImageType.valueOf(imageType.toUpperCase()));
			image1.setImageByte(image.getBytes());
			image1.setContentType(image.getContentType());
			imageRepository.save(image1);
		} catch (IOException e) {
			throw new ImageNotFormatedExcption("image format is not correct");
		}catch(IllegalArgumentException e) {
			throw new ImageTypeInvalidException("image type not valided");
		}
		return ResponseEntity.ok(responseStructure.setStatusCode(HttpStatus.CREATED.value()).setMessage("image add successfully"));
	}
	
	@Override
	public ResponseEntity<byte[]> findById(String imageId) {


			return imageRepository.findById(imageId).map(image -> {
				return ResponseEntity.ok()
						.contentLength(image.getImageByte().length)
						.contentType(MediaType.valueOf(image.getContentType()))
						.body(image.getImageByte());
			}).orElseThrow(()->new ImageNotFoundByIdException("image not prasent"));
		}

	@Override
	public ResponseEntity<byte[]> findByproductId(int productId) {
		
		return imageRepository.findByProductIdAndImageType(productId,ImageType.COVER).map(image -> {
			return ResponseEntity.ok()
					.contentLength(image.getImageByte().length)
					.contentType(MediaType.valueOf(image.getContentType()))
					.body(image.getImageByte());
		}).orElseThrow(()->new ImageNotFoundByIdException("image not prasent"));
	}
		
			

}
