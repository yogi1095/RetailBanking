package com.bank.retailbanking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.bank.retailbanking.dto.TransactionListResponseDTO;
import com.bank.retailbanking.dto.TransactionRequestDto;
import com.bank.retailbanking.dto.TransactionResponseDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Transaction;
import com.bank.retailbanking.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TransactionServiceTest {
	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;

	TransactionResponseDto transactionResponseDto = null;
	TransactionRequestDto transactionRequestDto = null;
	TransactionListResponseDTO transactionListResponseDTO = null;
	List<TransactionListResponseDTO> transactionsList = new ArrayList<>();
	Account account = null;
	List<Transaction> transactionList = new ArrayList<>();
	Transaction transaction = null;

	@Before
	public void setup() {
		transactionRequestDto = new TransactionRequestDto();
		transactionRequestDto.setAccountNumber(734734L);
		transactionRequestDto.setMonth(11);
		transactionRequestDto.setYear(2019);

		transactionListResponseDTO = new TransactionListResponseDTO();
		transactionListResponseDTO.setAccountNumber(734734L);
		transactionListResponseDTO.setTransactionAmount(884.09);
		transactionListResponseDTO.setTransactionDate(LocalDate.parse("2019-11-03"));
		transactionListResponseDTO.setTransactionDescription("transfering");
		transactionListResponseDTO.setTransactionType("credit");
		transactionsList.add(transactionListResponseDTO);

		transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setMessage("sucess");
		transactionResponseDto.setStatuscode(200);
		transactionResponseDto.setTransactionListResponseDTO(transactionsList);

		account = new Account();
		account.setAccountNumber(734734L);

		transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setTransactionAmount(589.98);
		transaction.setTransactionDate(LocalDate.parse("2019-11-03"));
		transaction.setTransactionDescription("Desc");
		transaction.setTransactionId(1);
		transaction.setTransactionType("credit");
		transactionList.add(transaction);
	}

	@Test
	public void monthlyTransactionsTest() {
		Mockito.when(transactionRepository.getAllByAccountAndTransactionDateBetween(account,
				LocalDate.parse("2019-11-01"), LocalDate.parse("2019-11-30"))).thenReturn(transactionList);
		 transactionResponseDto = transactionServiceImpl
				.monthlyTransactions(transactionRequestDto);
		Assert.assertNotNull(transactionResponseDto);
	}

}
