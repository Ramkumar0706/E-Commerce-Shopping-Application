package com.retail.ecom.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.retail.ecom.utility.SimpleResponseStructure;

public interface ImageService {

	ResponseEntity<SimpleResponseStructure> addImage(int productId, String imageType, MultipartFile image);

	ResponseEntity<byte[]> findById(String imageId);

}
