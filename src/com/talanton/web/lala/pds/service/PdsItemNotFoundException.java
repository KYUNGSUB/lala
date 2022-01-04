package com.talanton.web.lala.pds.service;

public class PdsItemNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public PdsItemNotFoundException() { }
	public PdsItemNotFoundException(String message) {
		super(message);
	}
}