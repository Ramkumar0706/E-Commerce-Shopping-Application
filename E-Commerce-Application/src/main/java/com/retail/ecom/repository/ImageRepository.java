package com.retail.ecom.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.retail.ecom.enums.ImageType;
import com.retail.ecom.model.Image;

public interface ImageRepository extends MongoRepository<Image, String> {
	boolean existsByProductIdAndImageType(int productId, ImageType imageType);



Optional<Image> findByProductIdAndImageType(int productId, ImageType cover);
}
