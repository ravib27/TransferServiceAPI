package com.natwest.transferservice.provider;

import java.util.Optional;

import com.natwest.transferservice.dto.Account;

public interface AccountProvider {

	public Optional<Account> getAccountDetailsByAccountNumber(String accountNumber);
	public void updateAccountBalance(Account account);
}
