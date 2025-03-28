package com.example.subscriptionserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SubscriptionServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionServerApplication.class, args);
	}

}
