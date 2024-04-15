package com.retail.ecom.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.retail.ecom.exception.InvalidUserRoleSpecfiedException;
import com.retail.ecom.exception.UserAlreadyExistsByEmailException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestControllerAdvice
public class ApplicationHandler {
	
	private ErrorStructure<String> errorStructure;
	
	public ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus code,String message,String rootCause){
		return ResponseEntity.ok(errorStructure.setErrorStatuscode(code.value()).setErrorMessage(message).setRootCause(rootCause));
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleUserAlreadyExistByEmail(UserAlreadyExistsByEmailException excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "user Email is already exist in database");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleInvalidUserRoleSpecfid(InvalidUserRoleSpecfiedException excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "UserRole is not specficed correctly");
	}

}
