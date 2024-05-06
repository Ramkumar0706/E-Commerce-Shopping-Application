package com.retail.ecom.model;

import java.util.List;

import com.retail.ecom.enums.AddressType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	private String streetAddress;
	private String streetAddressAdditional;
	private String city;
	 private String state;
	 private String pincode;
	 private AddressType addressType;
	    @OneToMany
	    private List<Contact> contacts;


}
