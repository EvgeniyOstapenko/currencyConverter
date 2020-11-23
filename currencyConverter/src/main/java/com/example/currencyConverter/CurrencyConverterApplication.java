package com.example.currencyConverter;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyConverterApplication {
	static Logger log = Logger.getLogger(CurrencyConverterApplication.class.getName());

	public static void main(String[] args) {
		log.info("Application started..");
		SpringApplication.run(CurrencyConverterApplication.class, args);

		log.info("Exiting the application");
	}

}
