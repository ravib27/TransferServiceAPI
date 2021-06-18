package com.natwest.transferservice;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TransferserviceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransferserviceApplicationIntegrationTests {
	
	Logger logger = LoggerFactory.getLogger(TransferserviceApplicationIntegrationTests.class);

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void executeTransferSuccessful() throws JSONException, URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	     
		final String baseUrl = createURLWithPort("/natwest/executetransfer");
		URI uri = new URI(baseUrl);
		     
		HttpHeaders headers = new HttpHeaders();
		List mediaTypeLst = new ArrayList();
		mediaTypeLst.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypeLst);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String contentJson = "{\"srcAccountNumber\":\"6101161\",\"destAccountNumber\":\"6101162\",\"amount\":\"1000.00\"}";
		 
		HttpEntity<String> requestEntity = new HttpEntity<>(contentJson, headers);
		logger.info("Has Body ::> " + requestEntity.hasBody());
		logger.info("Request Body ::> " + requestEntity.getBody());
		 
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		     
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		//Verify result body matches the expected output
		String expected = "Transaction Successful : Funds transffered from Source Account to Destination Account";
		assertEquals(expected, result.getBody());
	}
	
	@Test
	public void executeTransferInvalidSrcAccountNotFound() throws JSONException, URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	     
		final String baseUrl = createURLWithPort("/natwest/executetransfer");
		URI uri = new URI(baseUrl);
		     
		HttpHeaders headers = new HttpHeaders();
		List mediaTypeLst = new ArrayList();
		mediaTypeLst.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypeLst);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String contentJson = "{\"srcAccountNumber\":\"6101171\",\"destAccountNumber\":\"6101162\",\"amount\":\"1000.00\"}";
		 
		HttpEntity<String> requestEntity = new HttpEntity<>(contentJson, headers);
		logger.info("Has Body ::> " + requestEntity.hasBody());
		logger.info("Request Body ::> " + requestEntity.getBody());
		 
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		     
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		//Verify result body matches the expected output
		String expected = "Invalid Transfer Details : Source Account is not valid";
		assertEquals(expected, result.getBody());
	}
	
	@Test
	public void executeTransferInvalidDestAccountNotFound() throws JSONException, URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	     
		final String baseUrl = createURLWithPort("/natwest/executetransfer");
		URI uri = new URI(baseUrl);
		     
		HttpHeaders headers = new HttpHeaders();
		List mediaTypeLst = new ArrayList();
		mediaTypeLst.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypeLst);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String contentJson = "{\"srcAccountNumber\":\"6101161\",\"destAccountNumber\":\"6101172\",\"amount\":\"1000.00\"}";
		 
		HttpEntity<String> requestEntity = new HttpEntity<>(contentJson, headers);
		logger.info("Has Body ::> " + requestEntity.hasBody());
		logger.info("Request Body ::> " + requestEntity.getBody());
		 
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		     
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		//Verify result body matches the expected output
		String expected = "Invalid Transfer Details : Destination Account is not valid";
		assertEquals(expected, result.getBody());
	}
	
	@Test
	public void executeTransferInvalidSrcAndDestAccountNotFound() throws JSONException, URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	     
		final String baseUrl = createURLWithPort("/natwest/executetransfer");
		URI uri = new URI(baseUrl);
		     
		HttpHeaders headers = new HttpHeaders();
		List mediaTypeLst = new ArrayList();
		mediaTypeLst.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypeLst);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String contentJson = "{\"srcAccountNumber\":\"6101171\",\"destAccountNumber\":\"6101172\",\"amount\":\"1000.00\"}";
		 
		HttpEntity<String> requestEntity = new HttpEntity<>(contentJson, headers);
		logger.info("Has Body ::> " + requestEntity.hasBody());
		logger.info("Request Body ::> " + requestEntity.getBody());
		 
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		     
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		//Verify result body matches the expected output
		String expected = "Invalid Transfer Details : Source and Destination Accounts are not valid";
		assertEquals(expected, result.getBody());
	}
	
	@Test
	public void executeTransferInvalidSrcAndDestAccountAreSame() throws JSONException, URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	     
		final String baseUrl = createURLWithPort("/natwest/executetransfer");
		URI uri = new URI(baseUrl);
		     
		HttpHeaders headers = new HttpHeaders();
		List mediaTypeLst = new ArrayList();
		mediaTypeLst.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypeLst);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String contentJson = "{\"srcAccountNumber\":\"6101161\",\"destAccountNumber\":\"6101161\",\"amount\":\"1000.00\"}";
		 
		HttpEntity<String> requestEntity = new HttpEntity<>(contentJson, headers);
		logger.info("Has Body ::> " + requestEntity.hasBody());
		logger.info("Request Body ::> " + requestEntity.getBody());
		 
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		     
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		//Verify result body matches the expected output
		String expected = "Invalid Transfer Details : Source and Destination Accounts are same";
		assertEquals(expected, result.getBody());
	}
	
	@Test
	public void executeTransferUnsuccessfulSrcAccHasInsufficientFunds() throws JSONException, URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	     
		final String baseUrl = createURLWithPort("/natwest/executetransfer");
		URI uri = new URI(baseUrl);
		     
		HttpHeaders headers = new HttpHeaders();
		List mediaTypeLst = new ArrayList();
		mediaTypeLst.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypeLst);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String contentJson = "{\"srcAccountNumber\":\"6101161\",\"destAccountNumber\":\"6101162\",\"amount\":\"4000.00\"}";
		 
		HttpEntity<String> requestEntity = new HttpEntity<>(contentJson, headers);
		logger.info("Has Body ::> " + requestEntity.hasBody());
		logger.info("Request Body ::> " + requestEntity.getBody());
		 
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		     
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		//Verify result body matches the expected output
		String expected = "Transaction Unsuccessful : Source Account does not have sufficient funds";
		assertEquals(expected, result.getBody());
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
