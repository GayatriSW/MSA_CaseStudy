package com.onlineshopping.saleorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude= {ManagementWebSecurityAutoConfiguration.class,
		SecurityAutoConfiguration.class})
@EnableFeignClients("com.onlineshopping.saleorderservice")
@EnableDiscoveryClient
public class SaleOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaleOrderServiceApplication.class, args);
	}

}
