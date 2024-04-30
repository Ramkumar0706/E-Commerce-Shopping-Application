package com.retail.ecom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.ecom.requestdto.ContactRequest;
import com.retail.ecom.responsedto.ContactResponse;
import com.retail.ecom.service.ContactService;
import com.retail.ecom.utility.ResponseStructure;


import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/ecav1")
@CrossOrigin(allowCredentials = "true",origins = "http://localhost:5173/")
public class ContactCoontroller {
	private ContactService contactSrevice;
	
	
	@PostMapping("/address/addContact")
	public ResponseEntity<ResponseStructure<ContactResponse>>addContact(@RequestBody  ContactRequest contactRequest,@RequestParam int  addressId){
		String name = contactRequest.getName();
		System.out.println(name);
		return contactSrevice.addContact(contactRequest,addressId);
	}

}
