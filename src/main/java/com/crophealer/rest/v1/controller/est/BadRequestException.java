package com.crophealer.rest.v1.controller.est;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)  // 400
public class BadRequestException extends GenericRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
