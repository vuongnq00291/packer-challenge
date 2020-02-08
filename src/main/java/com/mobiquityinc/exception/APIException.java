package com.mobiquityinc.exception;

/**
 * Custom Exception
 * 
 * 
 * 
 * 
 * @see https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html
 * @author Maha M. Hamza
 */
public class APIException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public APIException(String msg) {
		super(msg);

	}

	public APIException(String msg, Exception e) {
		super(msg, e);

	}

}
