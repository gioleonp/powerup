package com.pragma.twilio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TwilioApplication {

	public static void main(String[] args) {
    SpringApplication.run(TwilioApplication.class, args);
	}

}
