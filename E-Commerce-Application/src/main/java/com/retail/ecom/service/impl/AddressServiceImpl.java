package com.retail.ecom.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.retail.ecom.enums.AddressType;
import com.retail.ecom.enums.UserRole;
import com.retail.ecom.exception.AddressNotFoundByUser;
import com.retail.ecom.exception.ContactNotFoundByUser;
import com.retail.ecom.jwt.JwtService;
import com.retail.ecom.model.Address;
import com.retail.ecom.model.Contact;
import com.retail.ecom.model.Customer;
import com.retail.ecom.model.Seller;
import com.retail.ecom.repository.AddressRepository;
import com.retail.ecom.repository.ContactRepository;
import com.retail.ecom.repository.UserRepostiory;
import com.retail.ecom.requestdto.AddressRequest;
import com.retail.ecom.responsedto.*;
import com.retail.ecom.service.AddressService;
import com.retail.ecom.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService{
	private JwtService jwtService;
	private UserRepostiory userRepostiory;
	private AddressRepository addressRepository;
	private ContactRepository contactRepository;
	private ResponseStructure<AddressResponse> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@Valid AddressRequest addressRequest, String accesstoken) {
		System.out.println("hello iam ram");
		String username = jwtService.getUsername(accesstoken);
		System.out.println("hello iam kumar");
		return  userRepostiory.findByUsername(username).map(user->{
			Address address=null;
			if(user.getUserRole()==UserRole.SELLER) {
				if(((Seller)user).getAddress()!=null) {
					address = addressRepository.save(mapToAddress(addressRequest,new Address()));
					((Seller)user).setAddress(address);
					userRepostiory.save(user);
				}
			}
			if(user.getUserRole()==UserRole.CUSTOMER) {
				if(((Customer)user).getAddress().size()<5) {
					address=addressRepository.save(mapToAddress(addressRequest,new Address()));
					((Customer)user).getAddress().add(address);
					userRepostiory.save(user);
				}
			}
			return ResponseEntity.ok().body(responseStructure.setStatuscode(HttpStatus.OK.value())
					.setMessage("the address added successfully")
					.setData(mapToAddressResponse(address)));

		}).orElseThrow(()->new UsernameNotFoundException("user is not found"));

	}
	private AddressResponse mapToAddressResponse(Address address) {
		List<ContactResponse> mapToContactResponses =null;
		if(address.getContacts()!=null) {
			mapToContactResponses= mapToContactResponses(address.getContacts());
		}

		return AddressResponse.builder().addressId(address.getAddressId()).streetAddress(address.getStreetAddress())
				.streetAddressAdditional(address.getStreetAddressAdditional()).city(address.getCity())
				.state(address.getState()).pincode(address.getPincode()).addressType(address.getAddressType())
				. contacts( mapToContactResponses )
				.build();

	}
	private List<ContactResponse> mapToContactResponses(List<Contact> contacts) {
		List<ContactResponse> contactResponses=new ArrayList<>();

		for(Contact contact:contacts)
		{
			contactResponses.add(mapToContactResponse(contact));
		}
		return contactResponses;
	}
	private ContactResponse mapToContactResponse(Contact contact) {
		return ContactResponse.builder()
				.contactId(contact.getContactId())
				.name(contact.getName())
				.email(contact.getEmail())
				.phoneNumber(contact.getPhoneNumber()).build();
	}
	private Address mapToAddress(AddressRequest addressRequest, Address address) {
		address.setStreetAddress(addressRequest.getStreetAddress());
		address.setStreetAddressAdditional(addressRequest.getStreetAddressAdditional());
		address.setCity(addressRequest.getCity());
		address.setState(addressRequest.getState());
		address.setPincode(addressRequest.getPincode());
		return address;
	}
	@Override
	public ResponseEntity<?> findAddressByUser(String accessToken) {
		String username = jwtService.getUsername(accessToken);
		return userRepostiory.findByUsername(username).map(user->{
			List<Address> addresses=null;
			Address address=null;
			List<Contact> contacts=null;
			if(user.getUserRole()==UserRole.SELLER) {
				address=((Seller)user).getAddress();
				if(address==null) throw new AddressNotFoundByUser("The User don't have address");
				//contacts = address.getContacts();
				//if(contacts==null)throw new ContactNotFoundByUser("the user don't have any contact");
				return ResponseEntity.ok().body(responseStructure.setData(mapToAddressResponse(address)));
			}
			if(user.getUserRole()==UserRole.CUSTOMER) {
				addresses=((Customer)user).getAddress();
				if(addresses==null) throw new AddressNotFoundByUser("The User don't have address");
				//contacts = address.getContacts();
				//if(contacts==null)throw new ContactNotFoundByUser("the user don't have any contact");
				return ResponseEntity.ok().body(responseStructure.setLists(mapToAddressResponseList(addresses)));
			}



			return null;
		}
				).orElseThrow(()->new UsernameNotFoundException("user is not found"));
	}

	private List<AddressResponse> mapToAddressResponseList(List<Address> addresses)
	{
		
		List<AddressResponse> addressResponses=new ArrayList<>();
		for(Address address: addresses)
		{
			addressResponses.add(mapToAddressResponse(address));
		}
		return addressResponses;
	}	


}
