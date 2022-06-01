package com.system.fsoft.exception;

@SuppressWarnings("serial")
public class CandidateNotFoundException extends RuntimeException {
	public CandidateNotFoundException(String msg) {
		super(msg);
	}
}