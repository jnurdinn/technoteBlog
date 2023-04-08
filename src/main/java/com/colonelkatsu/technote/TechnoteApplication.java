package com.colonelkatsu.technote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TechnoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnoteApplication.class, args);
	}

}
