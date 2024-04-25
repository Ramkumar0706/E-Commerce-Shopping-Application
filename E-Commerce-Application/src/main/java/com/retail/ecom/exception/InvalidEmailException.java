package com.retail.ecom.exception;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor 
@Getter

public class InvalidEmailException  extends RuntimeException{
	private String message;
}
