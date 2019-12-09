package com.bank.retailbanking.service;

import com.bank.retailbanking.dto.AccountSummaryResponseDto;

/**
 * This service is having all the implementations of methods of the accounts.
 * @author yoga
 */

public interface AccountService {

	public AccountSummaryResponseDto accountSummary(Integer customerId);

}
