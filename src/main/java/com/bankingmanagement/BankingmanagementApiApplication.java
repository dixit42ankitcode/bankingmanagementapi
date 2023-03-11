package com.bankingmanagement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
@Slf4j
public class BankingmanagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingmanagementApiApplication.class, args);
		log.info("bank application has been started");
	}


}
