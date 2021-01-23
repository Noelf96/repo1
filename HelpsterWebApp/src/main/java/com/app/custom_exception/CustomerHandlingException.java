package com.app.custom_exception;

@SuppressWarnings("serial")
public class CustomerHandlingException extends RuntimeException {
	public CustomerHandlingException(String mesg) {
		super(mesg);
	}
}
