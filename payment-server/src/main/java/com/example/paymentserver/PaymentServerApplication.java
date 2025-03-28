package com.example.paymentserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PaymentServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServerApplication.class, args);
	}

}
