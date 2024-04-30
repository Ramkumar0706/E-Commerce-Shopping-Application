package com.retail.ecom.service;

import org.springframework.http.ResponseEntity;

import com.retail.ecom.requestdto.ContactRequest;
import com.retail.ecom.responsedto.ContactResponse;
import com.retail.ecom.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface ContactService {

	public ResponseEntity<ResponseStructure<ContactResponse>> addContact( ContactRequest contactRequest, int addressId);
	

}
