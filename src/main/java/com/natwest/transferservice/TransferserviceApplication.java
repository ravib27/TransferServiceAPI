package com.natwest.transferservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.natwest.transferservice.dto")
public class TransferserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransferserviceApplication.class, args);
	}
}

