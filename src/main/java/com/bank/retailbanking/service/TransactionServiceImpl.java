package com.bank.retailbanking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.controller.AccountController;
import com.bank.retailbanking.dto.TransactionListResponseDTO;
import com.bank.retailbanking.dto.TransactionRequestDto;
import com.bank.retailbanking.dto.TransactionResponseDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Transaction;
import com.bank.retailbanking.repository.TransactionRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * This service is having all the implementations of methods of the Transaction.
 * 
 * @author yoga
 */
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	/**
	 * This will inject all the methods in the transactionRepository.
	 */
	@Autowired
	private TransactionRepository transactionRepository;

	/**
	 * This API is used to get monthlyTransactions which will find all the
	 * accountNumbers and five transactions.
	 * 
	 * @pathVariable customerId.This is the customerId of the customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         accountSummary and String of result along with the statusCode.
	 */

	@Override
	public TransactionResponseDto monthlyTransactions(TransactionRequestDto transactionRequestDto) {
		logger.info("to get monthly transactions");
		String month = String.format("%02d", transactionRequestDto.getMonth());
		Integer year = transactionRequestDto.getYear();
		LocalDate startDate = LocalDate.parse(year + "-" + month + "-" + "01");
		LocalDate endDate = LocalDate.parse(year + "-" + month + "-" + "30");

		Account account = new Account();
		account.setAccountNumber(transactionRequestDto.getAccountNumber());

		List<Transaction> transactions = transactionRepository.getAllByAccountAndTransactionDateBetween(account,
				startDate, endDate);

		List<TransactionListResponseDTO> transactionListResponseDTOList = new ArrayList<>();

		transactions.forEach(transaction -> {
			TransactionListResponseDTO transactionListResponseDTO = new TransactionListResponseDTO();
			transactionListResponseDTO.setTransactionType(transaction.getTransactionType());
			transactionListResponseDTO.setTransactionDate(transaction.getTransactionDate());
			transactionListResponseDTO.setTransactionAmount(transaction.getTransactionAmount());
			transactionListResponseDTO.setTransactionDescription(transaction.getTransactionDescription());
			transactionListResponseDTO.setAccountNumber(transaction.getAccount().getAccountNumber());
			transactionListResponseDTOList.add(transactionListResponseDTO);
		});
		TransactionResponseDto transactionResponseDTO = new TransactionResponseDto();
		transactionResponseDTO.setTransactionListResponseDTO(transactionListResponseDTOList);
		transactionResponseDTO.setStatuscode(Constant.SUCCESS_CODE);
		transactionResponseDTO.setMessage(Constant.SUCCESS);

		return transactionResponseDTO;
	}

}
