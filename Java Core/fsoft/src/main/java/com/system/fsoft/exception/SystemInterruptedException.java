package com.system.fsoft.exception;

@SuppressWarnings("serial")
public class SystemInterruptedException extends RuntimeException {
	public SystemInterruptedException(String msg, Throwable cause) {
		super(msg, cause);
	}
}