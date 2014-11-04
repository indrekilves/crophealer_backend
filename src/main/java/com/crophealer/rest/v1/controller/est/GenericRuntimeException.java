package com.crophealer.rest.v1.controller.est;

public class GenericRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message = null;
	
	public GenericRuntimeException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
