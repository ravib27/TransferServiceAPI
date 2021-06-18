package com.natwest.transferservice.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.natwest.transferservice.dto.Account;

@Repository
public interface AccountDao extends CrudRepository<Account, Long> {

	Optional<Account> findByAccountNumber(String accountNumber);
}
