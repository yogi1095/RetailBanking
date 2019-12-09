package com.bank.retailbanking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.controller.AccountController;
import com.bank.retailbanking.dto.AccountSummaryResponseDto;
import com.bank.retailbanking.dto.TransactionDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.entity.Transaction;
import com.bank.retailbanking.exception.AccountNotFoundException;
import com.bank.retailbanking.exception.CustomerNotFoundException;
import com.bank.retailbanking.exception.TransactionsNotFoundException;
import com.bank.retailbanking.repository.AccountRepository;
import com.bank.retailbanking.repository.CustomerRepository;
import com.bank.retailbanking.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * This service is having all the implementations of methods of the accounts.
 * 
 * @author yoga
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	/**
	 * This will inject all the methods in the accountRepository.
	 */

	@Autowired
	private AccountRepository accountRepository;

	/**
	 * This will inject all the methods in the customerRepository.
	 */

	@Autowired
	CustomerRepository customerRepository;

	/**
	 * This will inject all the methods in the transactionRepository.
	 */

	@Autowired
	private TransactionRepository transactionRepository;

	/**
	 * This API is used to get accountSummary which will find all the accountNumbers
	 * and five transactions.
	 * 
	 * @pathVariable customerId.This is the customerId of the customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         accountSummary and String of result along with the statusCode.
	 */

	@Override
	public AccountSummaryResponseDto accountSummary(Integer customerId) {
		logger.info("to get account summary");
		AccountSummaryResponseDto accountSummaryResponseDto = null;
		Optional<Customer> customer = customerRepository.findById(customerId);
		
		if (!customer.isPresent()) {
			throw new CustomerNotFoundException("From account does not exists");
		}
		Optional<Account> accountDetails = accountRepository.findByCustomer(customer);
		
		if (!accountDetails.isPresent()) {
			throw new AccountNotFoundException("From account does not exists");
		}
		List<Transaction> transactions = transactionRepository
				.findTop5ByAccountOrderByTransactionIdDesc(accountDetails);
		List<TransactionDto> transactionsList = new ArrayList<TransactionDto>();

		if (accountDetails.isPresent() && customer.isPresent()) {
			accountSummaryResponseDto= new AccountSummaryResponseDto();
			accountSummaryResponseDto.setAccountNumber(accountDetails.get().getAccountNumber());
			accountSummaryResponseDto.setBalance(accountDetails.get().getBalance());
			accountSummaryResponseDto
					.setName(Customer.fullName(customer.get().getFirstName(), customer.get().getLastName()));
			accountSummaryResponseDto.setMessage(Constant.SUCCESS);
			accountSummaryResponseDto.setStatusCode(Constant.SUCCESS_CODE);
			for (Transaction transaction : transactions) {
				TransactionDto transactionDto = new TransactionDto();
				transactionDto.setTransactionAmount(transaction.getTransactionAmount());
				transactionDto.setTransactionDate(transaction.getTransactionDate());
				transactionDto.setTransactionDescription(transaction.getTransactionDescription());
				transactionDto.setTransactionType(transaction.getTransactionType());
				transactionDto.setTransactionId(transaction.getTransactionId());
				transactionsList.add(transactionDto);
			}
			accountSummaryResponseDto.setTransactions(transactionsList);

		}

		return accountSummaryResponseDto;
	}

}
