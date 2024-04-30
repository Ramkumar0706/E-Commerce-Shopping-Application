package com.retail.ecom.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;import com.retail.ecom.enums.ContactPriority;
import com.retail.ecom.exception.AddressNotFoundByIdException;
import com.retail.ecom.exception.ContactLimitOverFlowException;
import com.retail.ecom.model.Address;
import com.retail.ecom.model.Contact;
import com.retail.ecom.repository.AddressRepository;
import com.retail.ecom.repository.ContactRepository;
import com.retail.ecom.requestdto.ContactRequest;
import com.retail.ecom.responsedto.ContactResponse;
import com.retail.ecom.service.ContactService;
import com.retail.ecom.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ContactServiceImpl  implements ContactService{
	private ResponseStructure<ContactResponse> responseStructure;
	private AddressRepository addressRepository;
	private ContactRepository contactRepository;
	@Override
	public ResponseEntity<ResponseStructure<ContactResponse>> addContact(ContactRequest contactRequest,
			int addressId) {
		return addressRepository.findById(addressId).map(address->{
			Contact contact=maptoContact(contactRequest);
			if(address.getContacts().size()>2)throw new ContactLimitOverFlowException("the contact limit is reached");
	
			address.getContacts().add(contact);
			contactRepository.save(contact);
			addressRepository.save(address);
			return ResponseEntity.ok().body(responseStructure.setMessage("Contact add to address Successfully")
					.setStatuscode(HttpStatus.CREATED.value()).setData(maptoContactResponse(contact)));
			
		}).orElseThrow(()->new AddressNotFoundByIdException("Address is Not found"));
		
	}
	private Contact maptoContact( ContactRequest contactRequest) {
		Contact contact=new Contact();
		System.out.println(contactRequest.getName());
		contact.setName(contactRequest.getName());
		contact.setEmail(contactRequest.getEmail());
		contact.setPhoneNumber(contactRequest.getPhoneNumber());
		contact.setPriority(contactRequest.getPriority());
		return contact;
	}
	private ContactResponse maptoContactResponse(Contact contact) {
		return ContactResponse.builder()
				.contactId(contact.getContactId())
				.name(contact.getName())
				.email(contact.getEmail())
				.phoneNumber(contact.getPhoneNumber()).build();
	}

}
