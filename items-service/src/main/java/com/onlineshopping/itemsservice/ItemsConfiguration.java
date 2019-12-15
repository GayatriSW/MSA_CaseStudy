package com.onlineshopping.itemsservice;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="items-service")
public class ItemsConfiguration {
	
	private String defaultItemName;
	private String defaultItemDesc;
	public String getDefaultItemName() {
		return defaultItemName;
	}
	public void setDefaultItemName(String defaultItemName) {
		this.defaultItemName = defaultItemName;
	}
	public String getDefaultItemDesc() {
		return defaultItemDesc;
	}
	public void setDefaultItemDesc(String defaultItemDesc) {
		this.defaultItemDesc = defaultItemDesc;
	}
	

}
