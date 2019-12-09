package com.bank.retailbanking.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class AccountResponseDto {
	private List<Long> accountNumber;
	private String message;
	private Integer statusCode;

}
