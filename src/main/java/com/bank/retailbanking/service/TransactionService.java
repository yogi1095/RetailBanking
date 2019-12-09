package com.bank.retailbanking.service;

import com.bank.retailbanking.dto.TransactionRequestDto;
import com.bank.retailbanking.dto.TransactionResponseDto;

public interface TransactionService {

	public TransactionResponseDto monthlyTransactions(TransactionRequestDto transactionRequestDto);
	
}
