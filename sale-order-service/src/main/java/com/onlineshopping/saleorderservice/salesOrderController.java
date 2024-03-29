package com.onlineshopping.saleorderservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class salesOrderController {
	
	private static final Logger log = LoggerFactory.getLogger(salesOrderController.class);
	
	@Autowired
	SalesOrderRepository ordRepo;
	
	@Autowired
	ItemService itmSrv;
	
	@Autowired
	OrderInfo ordInfo;
	
	@Autowired
	CustomerSalesRepository custRepo;
	
	@PostMapping("/SalesOrder")
	public OrderInfo placeOrder(@RequestBody Order order) {

		if(null != order.getOrderLineItems() && !order.getOrderLineItems().isEmpty()) {
			// validate items by calling items-service with item name
			Boolean isItemInValid = false;
			Boolean isCustValid = false;
			//System.out.println("order :"+order.getOrderLineItems().get(0).getOrder().getOrderDesc());
			for(OrderLineItems l1 : order.getOrderLineItems()) {
				l1.setOrder(order);
				isItemInValid = validateRequestedItem(l1.getItemName());
				if(isItemInValid)
					break;
			}
			log.info("order in line item :"+order.getOrderLineItems().get(0).getOrder().getOrderDesc());
			
			isCustValid = validateCustomerDetails(order.getCustId());
			
			log.info("Flag : cust-"+isCustValid+", item-"+isItemInValid);
			Order addedOrder = null;
			if(isCustValid && !isItemInValid) {
				addedOrder= ordRepo.save(order);
				ordInfo.setOrderId(addedOrder.getId());
				ordInfo.setOrderMssg("Order Placed Successfully..!!");
			}else { 
				log.info("either custID or item is invalid");
				if(!isCustValid){
					ordInfo.setOrderId(0L);
					ordInfo.setOrderMssg("Order Failed as CustomerID is invalid..!!");
				}
				if(isItemInValid) {
					ordInfo.setOrderId(0L);
					ordInfo.setOrderMssg("Order Falied as one of the item is invalid..!!");
				}
			}
		}else {
			ordInfo.setOrderId(0L);
			ordInfo.setOrderMssg("Order can't be placed as there are no items..!!");
		}
		
		log.info("Returning order details : "+ordInfo);
		return ordInfo;
	}
	
	private Boolean validateCustomerDetails(Long custId) {
		log.info("checking customer having ID = "+custId+" exist or not?");
		Optional<CustomerSalesOrder> cust = custRepo.findById(custId);
		log.info("Fetched customer details : "+cust);
		if(cust.isPresent()) 
			return true;
		else
			return false;
	}

	private Boolean validateRequestedItem(String itemName) {
		log.info("Invoking itemservice for item - "+itemName);
		ItemResponseModel itmRes = itmSrv.getItemDetails(itemName);
		log.info("ItemsResponse with PORT : "+itmRes);
		if(itmRes.getItem_name().equalsIgnoreCase("UnknownInvalidItem"))
			return true;
		else
			return false;
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
			log.info("retOrderLineItems size = "+ retOrderLineItems.size());
			o1.setOrderLineItems(retOrderLineItems);
			retOrder.add(o1);
		}
		return retOrder;
	}
	
	@GetMapping("/CustomersFromSales")
	public List<CustomerSalesOrder> getCustomerDetails() {
		List<CustomerSalesOrder> custInfo =  custRepo.findAll();
		List<CustomerSalesOrder> retCustInfo = new ArrayList<>();
		for(CustomerSalesOrder c1 : custInfo) {
			CustomerSalesOrder cust = new CustomerSalesOrder(c1.getId(), c1.getFirst_name(),
					c1.getLast_name(), c1.getEmail());
			retCustInfo.add(cust);
		}
		log.info("returning customer info list of size : "+retCustInfo.size());
		return retCustInfo;
	}

}
