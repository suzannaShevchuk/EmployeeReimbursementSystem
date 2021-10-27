package com.example.exceptions;

public class ReimbursementDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReimbursementDoesNotExistException() {
		super("No reimbursement with this id");	
		}

}
