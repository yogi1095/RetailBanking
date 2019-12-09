package com.bank.retailbanking.controller;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.dto.AccountSummaryResponseDto;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.service.AccountService;


/**
* The ShoppingCartApplication
*
* @author Yoga
* @version 1.0
* @since 2019-11-22
*/
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountControllerTest {
	
			@Mock
			AccountService accountService;
			
			@InjectMocks
			AccountController accountController;
			
			AccountSummaryResponseDto accountSummaryResponseDto = null;
			@Before
			public void setUp()
			{
				accountSummaryResponseDto = new AccountSummaryResponseDto();
				accountSummaryResponseDto.setMessage(Constant.SUCCESS);
				accountSummaryResponseDto.setStatusCode(Constant.SUCCESS_CODE);
				
				Customer customer = new Customer();
				customer.setCustomerId(1);
			}
			/**
			* This method is used to orderProductsTest.
			* @param orderRequestDto This is the parameters to add products method
			* @return This returns orderResponseDto.
			*/
			@Test
			public void accountSummaryTest()
			{
				
				Mockito.when(accountService.accountSummary(1)).thenReturn(accountSummaryResponseDto);
				AccountSummaryResponseDto response = accountController.accountSummary(1);
				Integer expected = Constant.SUCCESS_CODE;
				assertEquals(expected, response.getStatusCode());
			}
}
