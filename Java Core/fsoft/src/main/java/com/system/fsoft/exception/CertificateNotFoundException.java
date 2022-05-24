package com.system.fsoft.exception;

public class CertificateNotFoundException extends RuntimeException {
    public CertificateNotFoundException(String msg) {
        super(msg);
    }
}