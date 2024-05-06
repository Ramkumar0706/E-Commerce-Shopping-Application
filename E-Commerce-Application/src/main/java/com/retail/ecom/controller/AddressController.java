package com.retail.ecom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.retail.ecom.requestdto.AddressRequest;
import com.retail.ecom.responsedto.AddressResponse;
import com.retail.ecom.service.AddressService;
import com.retail.ecom.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("api/ecav1")
@CrossOrigin(allowCredentials = "true",origins = "http://localhost:5173/")
public class AddressController {
	private AddressService addressService;

	@PostMapping("/addAddress")
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@RequestBody @Valid AddressRequest addressRequest 
			,@CookieValue(name = "at",required = false) String accessToken) {
		System.out.println("ram");
		System.out.println("ramkumarr"+addressRequest.getState());
		return addressService.addAddress(addressRequest,accessToken);
	}

	@GetMapping("/findAddress")
	public ResponseEntity<?>findAddressByUser(@CookieValue(name = "at",required = false) String accessToken){
		System.out.println("hello   o"+accessToken);
		return addressService.findAddressByUser(accessToken);
	}

	@PutMapping("/updateAddress/{addressId}")
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@PathVariable int addressId,@RequestBody AddressRequest addressRequest ){
		System.out.println("rrrrr");
		return addressService.updateAddress(addressRequest,addressId);

	}
}
