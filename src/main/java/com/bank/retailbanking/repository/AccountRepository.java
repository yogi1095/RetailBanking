package com.bank.retailbanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByAccountNumber(Long accountNumber);

	Optional<Account> findByCustomer(Optional<Customer> customerId);

}
