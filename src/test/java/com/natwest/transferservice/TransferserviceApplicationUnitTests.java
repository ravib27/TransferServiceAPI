package com.natwest.transferservice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.natwest.transferservice.controller.TransferserviceController;
import com.natwest.transferservice.provider.TransferserviceProvider;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TransferserviceController.class)
public class TransferserviceApplicationUnitTests {
	
	Logger logger = LoggerFactory.getLogger(TransferserviceApplicationUnitTests.class);

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TransferserviceProvider transferserviceProvider;
	
	@Test
	public void executeTransferSuccessful() throws Exception {
	
		String txnMessage = "Transaction Successful : Funds transferred from Source Account to Destination Account";
		
		String contentJson = "{\"srcAccountNumber\":\"6101161\",\"destAccountNumber\":\"6101162\",\"amount\":\"1000.00\"}";

		Mockito.when(
				transferserviceProvider.executeTransfer(Mockito.any())).thenReturn(txnMessage);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/natwest/executetransfer")
				.content(contentJson)
				.contentType(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		String expected = "Transaction Successful : Funds transferred from Source Account to Destination Account";
		assertEquals(expected, result.getResponse().getContentAsString());
	}
	
	@Test
	public void executeTransferInvalidSrcAccountNotFound() throws Exception {
	
		String txnMessage = "Invalid Transfer Details : Source Account is not valid";
		
		String contentJson = "{\"srcAccountNumber\":\"6101170\",\"destAccountNumber\":\"6101162\",\"amount\":\"1000.00\"}";

		Mockito.when(
				transferserviceProvider.executeTransfer(Mockito.any())).thenReturn(txnMessage);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/natwest/executetransfer")
				.content(contentJson)
				.contentType(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		String expected = "Invalid Transfer Details : Source Account is not valid";
		assertEquals(expected, result.getResponse().getContentAsString());
	}
	
	@Test
	public void executeTransferInvalidDestAccountNotFound() throws Exception {
	
		String txnMessage = "Invalid Transfer Details : Destination Account is not valid";
		
		String contentJson = "{\"srcAccountNumber\":\"6101161\",\"destAccountNumber\":\"6101172\",\"amount\":\"1000.00\"}";

		Mockito.when(
				transferserviceProvider.executeTransfer(Mockito.any())).thenReturn(txnMessage);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/natwest/executetransfer")
				.content(contentJson)
				.contentType(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		String expected = "Invalid Transfer Details : Destination Account is not valid";
		assertEquals(expected, result.getResponse().getContentAsString());
	}
	
	@Test
	public void executeTransferInvalidSrcAndDestAccountNotFound() throws Exception {
	
		String txnMessage = "Invalid Transfer Details : Source and Destination Accounts are not valid";
		
		String contentJson = "{\"srcAccountNumber\":\"6101171\",\"destAccountNumber\":\"6101172\",\"amount\":\"1000.00\"}";

		Mockito.when(
				transferserviceProvider.executeTransfer(Mockito.any())).thenReturn(txnMessage);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/natwest/executetransfer")
				.content(contentJson)
				.contentType(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		String expected = "Invalid Transfer Details : Source and Destination Accounts are not valid";
		assertEquals(expected, result.getResponse().getContentAsString());
	}
	
	@Test
	public void executeTransferInvalidSrcAndDestAccountAreSame() throws Exception {
	
		String txnMessage = "Invalid Transfer Details : Source and Destination Accounts are same";
		
		String contentJson = "{\"srcAccountNumber\":\"6101161\",\"destAccountNumber\":\"6101161\",\"amount\":\"1000.00\"}";

		Mockito.when(
				transferserviceProvider.executeTransfer(Mockito.any())).thenReturn(txnMessage);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/natwest/executetransfer")
				.content(contentJson)
				.contentType(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		String expected = "Invalid Transfer Details : Source and Destination Accounts are same";
		assertEquals(expected, result.getResponse().getContentAsString());
	}
	
	@Test
	public void executeTransferUnsuccessfulSrcAccHasInsufficientFunds() throws Exception {
	
		String txnMessage = "Transaction Unsuccessful : Source Account does not have sufficient funds";
		
		String contentJson = "{\"srcAccountNumber\":\"6101161\",\"destAccountNumber\":\"6101162\",\"amount\":\"4000.00\"}";

		Mockito.when(
				transferserviceProvider.executeTransfer(Mockito.any())).thenReturn(txnMessage);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/natwest/executetransfer")
				.content(contentJson)
				.contentType(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		String expected = "Transaction Unsuccessful : Source Account does not have sufficient funds";
		assertEquals(expected, result.getResponse().getContentAsString());
	}
	
}
