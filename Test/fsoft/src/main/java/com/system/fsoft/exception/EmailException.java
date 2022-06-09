package com.system.fsoft.exception;

@SuppressWarnings("serial")
public class EmailException extends RuntimeException {
	public EmailException(String msg) {
		super(msg);
	}
}