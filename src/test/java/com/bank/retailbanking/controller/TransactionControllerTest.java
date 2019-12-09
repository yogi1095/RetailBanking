package com.bank.retailbanking.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.dto.TransactionRequestDto;
import com.bank.retailbanking.dto.TransactionResponseDto;
import com.bank.retailbanking.service.TransactionService;

/**
 * The ShoppingCartApplication
 *
 * @author Yoga
 * @version 1.0
 * @since 2019-11-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionControllerTest {
	@Mock
	TransactionService transactionService;

	@InjectMocks
	TransactionController transactionController;

	TransactionResponseDto transactionResponseDto = null;
	TransactionRequestDto transactionRequestDto = null;

	@Before
	public void setUp() {
		transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setMessage(Constant.SUCCESS);
		transactionResponseDto.setStatuscode(Constant.SUCCESS_CODE);

		transactionRequestDto = new TransactionRequestDto();
		transactionRequestDto.setAccountNumber(60987651L);
		transactionRequestDto.setMonth(11);
		transactionRequestDto.setYear(2019);
	}

	/**
	* This method is used to get orders by orderId.
	* @param orderId This is the parameters to get ordered products method
	* @return This returns ordereResponseDto.
	*/
	
	@Test
	public void monthlyTransactionsTest() throws Exception {
		transactionRequestDto = new TransactionRequestDto();
		transactionRequestDto.setAccountNumber(60987651L);
		transactionRequestDto.setMonth(11);
		transactionRequestDto.setYear(2019);
		transactionResponseDto = new TransactionResponseDto();
		transactionResponseDto.setMessage(Constant.SUCCESS);
		transactionResponseDto.setStatuscode(Constant.SUCCESS_CODE);

		Mockito.when(transactionService.monthlyTransactions(transactionRequestDto)).thenReturn(transactionResponseDto);
		TransactionResponseDto transactions = transactionController.monthlyTransactions(transactionRequestDto);
		Assert.assertNotNull(transactions);
	}
}
