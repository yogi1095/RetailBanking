package com.bank.retailbanking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.retailbanking.dto.AccountSummaryResponseDto;
import com.bank.retailbanking.service.AccountService;

import lombok.extern.slf4j.Slf4j;

/**
 * This Controller is having the account related functionalities. This
 * Controller will call the accountService in which the implementations are
 * done.
 * 
 * @API It has a method accountSummary which will find all the accountNumbers
 *      and last five transactions.
 * @author Yoga
 */

@RestController
@RequestMapping("/accounts")
@CrossOrigin
@Slf4j
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	/**
	 * This will inject all the implementations in the accountService.
	 */
	@Autowired
	private AccountService accountService;

	/**
	 * This API is used to find accountSummary which will find last five
	 * transactions and account number and name
	 * 
	 * @pathVariable customerId.This is the customerId of the current customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         last five transactions and accountNumbers and String of result along
	 *         with the statusCode.
	 */

	@GetMapping(value = "/{customerId}")
	public AccountSummaryResponseDto accountSummary(@PathVariable Integer customerId) {
		logger.info("to get Account Summary");
		return accountService.accountSummary(customerId);
	}

}
