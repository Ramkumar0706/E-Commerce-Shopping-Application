package com.retail.ecom.responsedto;

import java.util.List;

import com.retail.ecom.enums.AddressType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class AddressResponse {
	private int addressId;
	private String streetAddress;
	private String streetAddressAdditional;
	private String city;
	 private String state;
	 private String pincode;
	 private AddressType addressType;
	 private List<ContactResponse> contacts;

}
