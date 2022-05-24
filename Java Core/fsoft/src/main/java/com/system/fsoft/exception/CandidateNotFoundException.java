package com.system.fsoft.exception;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException(String msg) {
        super(msg);
    }
}