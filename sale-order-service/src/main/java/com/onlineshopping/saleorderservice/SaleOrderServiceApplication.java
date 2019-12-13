package com.onlineshopping.saleorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.onlineshopping.saleorderservice")
@EnableDiscoveryClient
public class SaleOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaleOrderServiceApplication.class, args);
	}

}
