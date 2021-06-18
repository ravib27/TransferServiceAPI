package com.natwest.transferservice.provider;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natwest.transferservice.dao.TransactionDao;
import com.natwest.transferservice.dto.Account;
import com.natwest.transferservice.dto.Transaction;

@Service
public class TransferserviceProviderImpl implements TransferserviceProvider {
	
	Logger logger = LoggerFactory.getLogger(TransferserviceProviderImpl.class);

	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	AccountProvider accountProvider;
	
	@Override
	public String executeTransfer(Transaction transaction) {
		String txnMessage = "Transaction To Be Performed";
		
		Optional<Account> srcAccount = accountProvider.getAccountDetailsByAccountNumber(transaction.getSrcAccountNumber());
		Optional<Account> destAccount = accountProvider.getAccountDetailsByAccountNumber(transaction.getDestAccountNumber());
		if(srcAccount.isPresent() && destAccount.isPresent()) {
			
			txnMessage = validateTransferDetails(transaction, srcAccount.get(), destAccount.get());
			
			if(!srcAccount.get().getAccountNumber().equals(destAccount.get().getAccountNumber())) {
				logger.info("srcAccount ::> " + srcAccount.get().getAccountNumber());
				logger.info("destAccount ::> " + destAccount.get().getAccountNumber());
				logger.info("srcAccount balance before transfer ::> " + srcAccount.get().getAccountBalance());
				logger.info("destAccount balance before transfer ::> " + destAccount.get().getAccountBalance());
				logger.info("transactionAmount ::> " + transaction.getAmount());
				
				if(transaction.getAmount().compareTo(srcAccount.get().getAccountBalance()) <= 0) {
					BigDecimal updatedSrcAccountBal = srcAccount.get().getAccountBalance().subtract(transaction.getAmount());
					Account updateSrcAccountDtl = srcAccount.get();
					updateSrcAccountDtl.setAccountBalance(updatedSrcAccountBal);
					accountProvider.updateAccountBalance(updateSrcAccountDtl);
					
					BigDecimal updatedDestAccountBal = destAccount.get().getAccountBalance().add(transaction.getAmount());
					Account updatedDestAccountDtl = destAccount.get();
					updatedDestAccountDtl.setAccountBalance(updatedDestAccountBal);
					accountProvider.updateAccountBalance(updatedDestAccountDtl);
					
					Calendar calendar = Calendar.getInstance();
					java.util.Date currentTime = calendar.getTime();
					transaction.setTransactionDate(currentTime);
					
					transactionDao.save(transaction);
					
					txnMessage = "Transaction Successful : Funds transffered from Source Account to Destination Account";
					
				}
				else {
					txnMessage = "Transaction Unsuccessful : Source Account does not have sufficient funds";
				}
				
				logger.info("srcAccount balance after transfer ::> " + srcAccount.get().getAccountBalance());
				logger.info("destAccount balance after transfer ::> " + destAccount.get().getAccountBalance());
			}
			else {
				txnMessage = "Invalid Transfer Details : Source and Destination Accounts are same";
			}
			
		}
		else {
			
			if(!srcAccount.isPresent() && !destAccount.isPresent())
				txnMessage = "Invalid Transfer Details : Source and Destination Accounts are not valid";
			else if(!srcAccount.isPresent())
				txnMessage = "Invalid Transfer Details : Source Account is not valid";
			else if(!destAccount.isPresent())
				txnMessage = "Invalid Transfer Details : Destination Account is not valid";
		}
		
		return txnMessage;
	}
	
	private String validateTransferDetails(Transaction transaction, Account srcAccount, Account destAccount) {
		return "";
	}
}
