package com.system.fsoft.exception;

@SuppressWarnings("serial")
public class CertificateNotFoundException extends RuntimeException {
	public CertificateNotFoundException(String msg) {
		super(msg);
	}
}