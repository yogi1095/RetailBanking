package com.bank.retailbanking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.bank.retailbanking.dto.AccountSummaryResponseDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.entity.Transaction;
import com.bank.retailbanking.exception.AccountNotFoundException;
import com.bank.retailbanking.exception.CustomerNotFoundException;
import com.bank.retailbanking.repository.AccountRepository;
import com.bank.retailbanking.repository.CustomerRepository;
import com.bank.retailbanking.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountServiceTest {
	@Mock
	AccountRepository accountRepository;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	TransactionRepository transactionRepository;

	@InjectMocks
	AccountServiceImpl accountServiceImpl;

	AccountSummaryResponseDto accountSummaryResponseDto = null;
	Customer customer = null;
	Customer customer1 = null;
	Account account = null;
	Transaction transaction = null;
	List<Transaction> transactionsList = new ArrayList<>();

	@Before
	public void setup() {

		customer = new Customer();
		customer.setCustomerId(1);
		customer.setAddress("Bng");
		customer.setDateOfBirth(LocalDate.parse("2019-12-03"));
		customer.setEmailId("yoga@gmail.com");
		customer.setFirstName("yoga");
		customer.setLastName("reddy");
		customer.setMobileNo(9491777925L);
		customer.setUserName("HI");
		customer.setPassword("Hello");

		account = new Account();
		account.setAccountNumber(60987651L);
		account.setAccountStatus("Active");
		account.setAccountType("Savings");
		account.setBalance((double) 3489);
		account.setCustomer(customer);

		transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setTransactionAmount(3489.00);
		transaction.setTransactionDate(LocalDate.parse("2019-12-03"));
		transaction.setTransactionDescription("use");
		transaction.setTransactionId(1);
		transaction.setTransactionType("credit");
		transactionsList.add(transaction);

		customer1 = new Customer();
		customer1.setCustomerId(4);
		customer1.setAddress("Bng");
		customer1.setDateOfBirth(LocalDate.parse("2019-12-03"));
		customer1.setEmailId("yoga@gmail.com");
		customer1.setFirstName("yoga");
		customer1.setLastName("reddy");
		customer1.setMobileNo(9491777925L);
		customer1.setUserName("HI");
		customer1.setPassword("Hello");
	}

	/**
	 * This method is used to test accountSummary and five transactions.
	 * 
	 * @pathVariable customerId.This is the customerId of the customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         accountSummary and String of result along with the statusCode.
	 */
	@Test
	public void testAccountSummary() {
		Mockito.when(customerRepository.findById(5)).thenReturn(Optional.of(customer));
		Mockito.when(accountRepository.findByCustomer(Optional.of(customer))).thenReturn(Optional.of(account));
		Mockito.when(transactionRepository.findTop5ByAccountOrderByTransactionIdDesc(Optional.of(account)))
				.thenReturn(transactionsList);

		AccountSummaryResponseDto accountSummaryResponseDto = accountServiceImpl.accountSummary(5);
		Assert.assertNotNull(accountSummaryResponseDto);
	}

	/**
	 * This method is used to test accountSummary in negative scenario and five
	 * transactions.
	 * 
	 * @pathVariable customerId.This is the customerId of the customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         accountSummary and String of result along with the statusCode.
	 */

	@Test(expected = CustomerNotFoundException.class)
	public void testCustomerAccountSummaryNegative() {
		Mockito.when(customerRepository.findById(5)).thenReturn(Optional.of(customer));
		AccountSummaryResponseDto accountSummaryResponseDto = accountServiceImpl.accountSummary(6);
	}

	@Test(expected = AccountNotFoundException.class)
	public void testAccountSummaryNegative() {
		Mockito.when(customerRepository.findById(6)).thenReturn(Optional.of(customer));
		Mockito.when(accountRepository.findByCustomer(Optional.of(customer1))).thenReturn(Optional.of(account));
		AccountSummaryResponseDto accountSummaryResponseDto = accountServiceImpl.accountSummary(6);
	}

}
