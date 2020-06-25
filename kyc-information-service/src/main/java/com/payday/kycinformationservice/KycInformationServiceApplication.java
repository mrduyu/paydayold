package com.payday.kycinformationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
public class KycInformationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KycInformationServiceApplication.class, args);
	}

}
