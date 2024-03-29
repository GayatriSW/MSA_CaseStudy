package com.onlineshopping.itemsservice;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@EnableHystrix
public class ItemController {
	
	private static final Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	ItemsRepository itemRepo;
	
	@Autowired
	Environment envmt;
	
	@Autowired
	ItemsConfiguration itemsConfiguration;

	@GetMapping("/Items")
	public List<Item> getItems(){
		log.info("Service /Items is invoked from Items-service...!!!");
		List<Item> returnItemsList = new ArrayList<>();
		
		List<Item> itemsList = itemRepo.findAll();
		for(Item i1 : itemsList) {
			returnItemsList.add(new Item(i1.getId(), i1.getItem_name(), i1.getItem_desc(), i1.getPrice()));
		}
		return returnItemsList;
	}
	
	@GetMapping("/Item/{itemName}")
	@HystrixCommand(fallbackMethod= "fallBackItemDetails")
	public Item getItemDetails(@PathVariable String itemName){
		System.out.println("incoming itemName - "+itemName);
		Item item = itemRepo.findItemByName(itemName);
		System.out.println("item : "+item);
		
		Item itemCopy = null;
		if(null == item)
			throw new RuntimeException("some issue");
		else
			itemCopy = new Item(item.getId(), item.getItem_name(), item.getItem_desc(), item.getPrice());
		
		System.out.println("Port used : "+envmt.getProperty("local.server.port"));
		itemCopy.setPort(Integer.parseInt(envmt.getProperty("local.server.port")));
		log.info("Post used - Items-service : "+itemCopy.getPort());
		return itemCopy;
	}
	
	public Item fallBackItemDetails(String mssg) {
		log.info("fallback method invoked in item-service");
		//return new Item(0l, "UnknownItem", "UnknowItem", 0);
		return new Item(0l, itemsConfiguration.getDefaultItemName(), itemsConfiguration.getDefaultItemDesc(), 0);
	}
}
