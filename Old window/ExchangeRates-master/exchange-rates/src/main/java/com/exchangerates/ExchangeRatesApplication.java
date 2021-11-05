package com.exchangerates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ExchangeRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRatesApplication.class, args);
		
	}

	/**
	 * This method returns the instance of RestTemplate
	 * @return
	 */
	@Bean
	public RestTemplate getRestTemplate(){
		
		return new RestTemplate();
	}
}
