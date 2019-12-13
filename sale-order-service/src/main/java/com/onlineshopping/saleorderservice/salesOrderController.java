package com.onlineshopping.saleorderservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class salesOrderController {
	
	@Autowired
	SalesOrderRepository ordRepo;
	
	@Autowired
	ItemService itmSrv;
	
	@PostMapping("/SalesOrder")
	public Long placeOrder(@RequestBody Order order) {

		// validate items by calling items-service with item name
		Boolean isValid = false;
		//System.out.println("order :"+order.getOrderLineItems().get(0).getOrder().getOrderDesc());
		X:for(OrderLineItems l1 : order.getOrderLineItems()) {
			l1.setOrder(order);
			isValid = validateRequestedItem(l1.getItemName());
			if(isValid)
				continue X;
		}
		System.out.println("order in line item :"+order.getOrderLineItems().get(0).getOrder().getOrderDesc());
		Order addedOrder = null;
		if(isValid) {
			addedOrder= ordRepo.save(order);
		}else {
			addedOrder = new Order();
			addedOrder.setId(0L);
		}
		return addedOrder.getId();
	}
	
	private Boolean validateRequestedItem(String itemName) {
		System.out.println("invoking itemservice for item - "+itemName);
		ItemResponseModel itmRes = itmSrv.getItemDetails(itemName);
		System.out.println("itmRes : "+itmRes);
		if(itmRes.getItem_name().equalsIgnoreCase("UnknownItem"))
			return false;
		else
			return true;
	}
	
	@GetMapping("/SalesOrders")
	public List<Order> getOrders() {
		List<Order> orders = ordRepo.findAll();
		List<Order> retOrder = new ArrayList<>();
		for(Order i1 : orders) {
			Order o1 = new Order(i1.getOrderDate(),i1.getCustId(),i1.getOrderDesc(),i1.getTotalPrice());
			o1.setId(i1.getId());
			List<OrderLineItems> retOrderLineItems = new ArrayList<>();
			for(OrderLineItems l1 : i1.getOrderLineItems()) {
				OrderLineItems ol1 = new OrderLineItems(l1.getId(),l1.getItemName(), l1.getItemQty());
				retOrderLineItems.add(ol1);
			}
			System.out.println("retOrderLineItems size = "+ retOrderLineItems.size());
			o1.setOrderLineItems(retOrderLineItems);
			retOrder.add(o1);
		}
		return retOrder;
	}

}
