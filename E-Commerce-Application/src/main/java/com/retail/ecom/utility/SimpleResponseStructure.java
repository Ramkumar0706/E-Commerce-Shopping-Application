package com.retail.ecom.utility;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter

public class SimpleResponseStructure {
	private int statusCode;
	private String message;
	public SimpleResponseStructure setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	public SimpleResponseStructure  setMessage(String message) {
		this.message = message;
		return this;
	}
	

}
