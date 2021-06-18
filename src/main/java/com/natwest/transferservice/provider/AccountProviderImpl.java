package com.natwest.transferservice.provider;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natwest.transferservice.dao.AccountDao;
import com.natwest.transferservice.dto.Account;

@Service
public class AccountProviderImpl implements AccountProvider {

	@Autowired
	AccountDao accountDao;
	
	@Override
	public Optional<Account> getAccountDetailsByAccountNumber(String accountNumber) {
		
		return accountDao.findByAccountNumber(accountNumber);
	}
	
	@Override
	public void updateAccountBalance(Account account) {
		
		accountDao.save(account);
		
	}
}
