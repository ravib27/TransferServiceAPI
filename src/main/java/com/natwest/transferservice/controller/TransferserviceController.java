package com.natwest.transferservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.natwest.transferservice.dto.Transaction;
import com.natwest.transferservice.provider.TransferserviceProvider;

@RestController
public class TransferserviceController {

	@Autowired
	TransferserviceProvider transferserviceProvider;
	
	@PostMapping(value = "/natwest/executetransfer", produces = MediaType.APPLICATION_JSON_VALUE)
	public String executeTransfer(@RequestBody Transaction transaction) {
		String message = transferserviceProvider.executeTransfer(transaction);
		return message;
	}
}
