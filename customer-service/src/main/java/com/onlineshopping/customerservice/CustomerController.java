package com.onlineshopping.customerservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CustomerController {
	
	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Autowired
	CustomConfiguration custConfig;
	
	@GetMapping("/Customer/{custId}")
	public Customer getCustomer(@PathVariable Long custId) {
		//return new Customer(206428l, "Gayatri", "SW", "gsw@gmail.com");
		
		Customer cust = custRepo.getOne(custId);
		Customer custCopy = new Customer(cust.getId(), cust.getFirst_name(), cust.getLast_name(), cust.getEmail());
		return custCopy;
	}
	
	@GetMapping("/Customers")
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = custRepo.findAll();
		List<Customer> returnCustList = new ArrayList<>();
		for(Customer c1 : customerList) {
			Customer custCopy = new Customer(c1.getId(), c1.getFirst_name(), c1.getLast_name(), c1.getEmail());
			returnCustList.add(custCopy);
		}
		return returnCustList;
	}
	
	
	@PostMapping("/Customer")
	public Long addCustomer(@RequestBody Customer cust) {
		Customer addedCust = custRepo.save(cust);
		
	    String message = "customer created#"+addedCust.getId().toString()+"#"+addedCust.getFirst_name()
	    		+"#"+addedCust.getLast_name()+"#"+addedCust.getEmail();
	    System.out.println("publishing event to exchange :"+custConfig.getExchange()
	    		+" binds to queue : "+custConfig.getQueueName()
	    		+" with rounting key : "+custConfig.getRoutingkey());
	    amqpTemplate.convertAndSend(custConfig.getExchange(), custConfig.getRoutingkey(), message);
	    
		return addedCust.getId();
	}
	
}
