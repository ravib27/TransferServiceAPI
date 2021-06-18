package com.natwest.transferservice.provider;

import com.natwest.transferservice.dto.Transaction;

public interface TransferserviceProvider {

	public String executeTransfer(Transaction transaction);
}
