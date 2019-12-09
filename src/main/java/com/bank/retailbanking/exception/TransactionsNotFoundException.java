package com.bank.retailbanking.exception;

public class TransactionsNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionsNotFoundException(String string) {
		super(string);
	}

}
