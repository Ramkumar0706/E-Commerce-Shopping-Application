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
import com.retail.ecom.exception.AddressNotFoundByIdException;
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
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress( AddressRequest addressRequest, String accessToken) {
	    String username = jwtService.getUsername(accessToken);
	    System.out.println(addressRequest.getState());
	    return userRepostiory.findByUsername(username)
	            .map(user -> {
	                Address address = mapToAddress(addressRequest, new Address()); // Instantiate address here
	                if (user.getUserRole() == UserRole.SELLER) {
	                    if (((Seller) user).getAddress()== null) {
	                        address = addressRepository.save(address); // Save address here
	                        ((Seller) user).setAddress(address);
	                        userRepostiory.save(user);
	                    }
	                } else if (user.getUserRole() == UserRole.CUSTOMER) {
	                    if (((Customer) user).getAddress().size() < 5) {
	                        address = addressRepository.save(address); // Save address here
	                        ((Customer) user).getAddress().add(address);
	                        userRepostiory.save(user);
	                    }
	                }
	                return ResponseEntity.ok().body(responseStructure.setStatuscode(HttpStatus.OK.value())
	                        .setMessage("The address was added successfully")
	                        .setData(mapToAddressRespons(address)));
	            })
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	}

	private AddressResponse mapToAddressRespons(Address address) {
		List<ContactResponse> mapToContactResponses =null;
		return AddressResponse.builder().addressId(address.getAddressId()).streetAddress(address.getStreetAddress())
				.streetAddressAdditional(address.getStreetAddressAdditional()).city(address.getCity())
				.state(address.getState()).pincode(address.getPincode()).addressType(address.getAddressType())
				. contacts( mapToContactResponses )
				.build();
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
		address.setAddressType(addressRequest.getAddressType());
		return address;
	}
	@Override
	public ResponseEntity<?> findAddressByUser(String accessToken) {
	    String username = jwtService.getUsername(accessToken);
	    return userRepostiory.findByUsername(username)
	            .map(user -> {
	                List<Address> addresses = null;
	                Address address = null;
	                List<Contact> contacts = null;
	                if (user.getUserRole() == UserRole.SELLER) {
	                    address = ((Seller) user).getAddress();
	                } else if (user.getUserRole() == UserRole.CUSTOMER) {
	                    addresses = ((Customer) user).getAddress();
	                }
	                if (address == null && (addresses == null || addresses.isEmpty())) {
	                    throw new AddressNotFoundByUser("The user doesn't have any addresses.");
	                }

	                if (address != null) {
	                    return ResponseEntity.ok().body(responseStructure.setData(mapToAddressResponse(address)));
	                } else {
	                    return ResponseEntity.ok().body(responseStructure.setLists(mapToAddressResponseList(addresses)));
	                }
	            })
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
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
	
	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(AddressRequest addressRequest,
			int addressId) {
		return addressRepository.findById(addressId).map(address -> {
			address=addressRepository.save(mapToAddress(address, addressRequest));
		
		return ResponseEntity.ok(new ResponseStructure<AddressResponse>()
				.setData(mapToAddressResponse(address))
				.setStatuscode(HttpStatus.OK.value())
				.setMessage("address updated"));
		}).orElseThrow(()-> new AddressNotFoundByIdException("address not found by id"));
	}
	private Address mapToAddress(Address address,AddressRequest addressRequest) {
		address.setAddressType(addressRequest.getAddressType());
		address.setCity(addressRequest.getCity());
		address.setPincode(addressRequest.getPincode());
		address.setState(addressRequest.getState());
		address.setStreetAddress(addressRequest.getStreetAddress());
		address.setStreetAddressAdditional(addressRequest.getStreetAddressAdditional());
		return address;
	}


}
