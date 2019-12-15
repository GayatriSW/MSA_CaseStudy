package com.onlineshopping.saleorderservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="sales-order-service")
public class SalesOrderConfig {
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
