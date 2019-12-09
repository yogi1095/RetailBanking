package com.bank.retailbanking.exception;

import lombok.NoArgsConstructor;

public class AccountNotFoundException extends RuntimeException{
	
	public AccountNotFoundException(String string) {
		super(string);
	}

}
