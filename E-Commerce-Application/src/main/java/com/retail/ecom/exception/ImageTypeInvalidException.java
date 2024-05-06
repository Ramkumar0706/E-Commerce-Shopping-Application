package com.retail.ecom.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class ImageTypeInvalidException extends RuntimeException {
private String message;
}
