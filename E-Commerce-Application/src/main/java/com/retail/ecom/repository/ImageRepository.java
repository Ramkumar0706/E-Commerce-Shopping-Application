package com.retail.ecom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.retail.ecom.enums.ImageType;
import com.retail.ecom.model.Image;

public interface ImageRepository extends MongoRepository<Image, String> {
	boolean existsByProductIdAndImageType(int productId, ImageType imageType);
	Optional<Image> findByProductIdAndImageType(int productId, ImageType cover);
	//@Query(value = "{'productId': ?0, 'imageType': ?1}", fields = "{'imageId': 1}")
	  Optional<Image> findImageIdByProductIdAndImageType(int productId, ImageType imageType);
	  
	  Optional<List<Image>>findImageIdsByProductIdAndImageType(int productId, ImageType imageType);
}
