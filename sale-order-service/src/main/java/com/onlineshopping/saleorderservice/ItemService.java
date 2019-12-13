package com.onlineshopping.saleorderservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="items-service")
//@RibbonClient(name="items-service", configuration = RibbonConfiguration.class)
@RibbonClient(name="items-service")
public interface ItemService {

	@GetMapping("/Item/{itemName}")
	public ItemResponseModel getItemDetails(@PathVariable("itemName") String itemName);
}
