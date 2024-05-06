package com.retail.ecom.service;

import org.springframework.http.ResponseEntity;

import com.retail.ecom.responsedto.*;
import com.retail.ecom.requestdto.AddressRequest;
import com.retail.ecom.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface AddressService {

	ResponseEntity<ResponseStructure<AddressResponse>> addAddress( AddressRequest addressRequest,String accessToken);

	ResponseEntity<?> findAddressByUser(String accessToken);

	ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest, int addressId);
	

}
