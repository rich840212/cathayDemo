package com.cathey.tw.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CathayDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CathayDemoApplication.class, args);
	}

}
