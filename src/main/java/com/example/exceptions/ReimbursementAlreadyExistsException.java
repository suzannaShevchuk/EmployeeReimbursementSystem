package com.example.exceptions;

public class ReimbursementAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReimbursementAlreadyExistsException() {
		super("Reimbursement with this ID already exists.");	
		}

}

