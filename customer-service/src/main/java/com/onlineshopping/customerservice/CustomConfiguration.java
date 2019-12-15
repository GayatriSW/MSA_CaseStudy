package com.onlineshopping.customerservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@ConfigurationProperties(prefix="customer-service")
public class CustomConfiguration {

	private String queueName;

	private String exchange;

	private String routingkey;

	public String getQueueName() {
		return queueName;
	}

	public String getExchange() {
		return exchange;
	}

	public String getRoutingkey() {
		return routingkey;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public void setRoutingkey(String routingkey) {
		this.routingkey = routingkey;
	}
	
}
