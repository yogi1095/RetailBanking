package com.bank.retailbanking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.retailbanking.dto.TransactionRequestDto;
import com.bank.retailbanking.dto.TransactionResponseDto;
import com.bank.retailbanking.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

/**
 * This Controller is having the transaction related functionalities. This
 * Controller will call the transactionService in which the implementations are
 * done.
 * 
 * @API It has a method get monthlyTransactions which will find all the
 *      accountNumbers except the current customer's accountNumber.
 * @author Yoga
 */

@RestController
@RequestMapping("/transactions")
@CrossOrigin
@Slf4j
public class TransactionController {
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	/**
	 * This will inject all the implementations in the transactionService.
	 */
	@Autowired
	private TransactionService transactionService;

	/**
	 * This API is used to find monthlyTransaction which will find monthly
	 * transaction
	 * 
	 * @pathVariable AccountNumber and month and year.
	 * @return This has the return type of transactionResponseDto.This returns
	 *         monthly transactions and statusCode.
	 */

	@PostMapping("/monthly")
	public TransactionResponseDto monthlyTransactions(@RequestBody TransactionRequestDto transactionRequestDto) {
		logger.info("to get Monthly transactions");
		return transactionService.monthlyTransactions(transactionRequestDto);
	}

}
