package com.bank.retailbanking.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.bank.retailbanking.entity.Account;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionListResponseDTO {
	private String transactionType;
	private Double transactionAmount;
	private String transactionDescription;
	private Long accountNumber;
	private LocalDate transactionDate;
}
