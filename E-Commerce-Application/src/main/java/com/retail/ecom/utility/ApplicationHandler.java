package com.retail.ecom.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.retail.ecom.exception.AddressNotFoundByIdException;
import com.retail.ecom.exception.AddressNotFoundByUser;
import com.retail.ecom.exception.ContactLimitOverFlowException;
import com.retail.ecom.exception.ContactNotFoundByIdException;
import com.retail.ecom.exception.ContactNotFoundByUser;
import com.retail.ecom.exception.ImageNotFormatedExcption;
import com.retail.ecom.exception.ImageTypeInvalidException;
import com.retail.ecom.exception.InvalidEmailException;
import com.retail.ecom.exception.InvalidUserRoleSpecfiedException;
import com.retail.ecom.exception.OTPExpiredException;
import com.retail.ecom.exception.OTPInvalidException;
import com.retail.ecom.exception.RegistrationSessionExpiredException;
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
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleOTPInvalidException(OTPInvalidException excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "enter the valid otp");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleOTPExpiredException(OTPExpiredException excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "the user enter otp is time out");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleRegistrationSessionExpiredException(RegistrationSessionExpiredException excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "the user registration session is expired");
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleInvalidEmailException(InvalidEmailException excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "please enter valid email");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleAddressNotFoundByIdException(AddressNotFoundByIdException excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "address is not found by Id");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleAddressNotFoundByUserException(AddressNotFoundByUser excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "address is not found by current user");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleContactLimitOverFlowException(ContactLimitOverFlowException excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "contact limit is exceeded");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleContactNotFoundByIdException(ContactNotFoundByIdException excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "contact not found by id");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleContactNotFoundByUser(ContactNotFoundByUser excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "contact is not found by User");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleImageNotFormatedExcption(ImageNotFormatedExcption excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "Image is not formated type ");
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> handeleImageTypeInvalidException(ImageTypeInvalidException excption){
		return errorResponse(HttpStatus.BAD_REQUEST,excption.getMessage() , "ImageType is not valid");
	}

}
