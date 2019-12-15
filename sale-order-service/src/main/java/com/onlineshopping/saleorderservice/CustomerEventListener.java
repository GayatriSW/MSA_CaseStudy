package com.onlineshopping.saleorderservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventListener {
	@Autowired
	CustomerSalesRepository custSaleRepo;
	
	@Autowired
	SalesOrderConfig salesOrderConfig;
	
	private static final String qname = "TestQ";//salesOrderConfig.getQueueName();
	
	@RabbitListener(queues=qname)
	public void readEvents(String mssg) {
		System.out.println("salesOrderConfig props : "+salesOrderConfig.getExchange()+", "
				+ salesOrderConfig.getRoutingkey()+", "+salesOrderConfig.getQueueName());
		System.out.println("Event received : "+mssg);
		String[] mssgInfo = mssg.split("#");
		CustomerSalesOrder rxCustInfo = new CustomerSalesOrder();
		rxCustInfo.setId(Long.parseLong(mssgInfo[1]));
		rxCustInfo.setFirst_name(mssgInfo[2]);
		rxCustInfo.setLast_name(mssgInfo[3]);
		rxCustInfo.setEmail(mssgInfo[4]);
		custSaleRepo.save(rxCustInfo);
		System.out.println("Event read and customer info is saved in salesOrderService DB");
	}
}
