package com.retail.ecom.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.retail.ecom.enums.ImageType;

import lombok.Getter;
import lombok.Setter;
@Document(collection = "Images")
@Getter
@Setter
public class Image {
	@MongoId
	private String  imageId;
	private ImageType imageType;
	private byte[] imageByte;
	private int productId;
	private String contentType;
	
}
