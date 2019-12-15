package com.onlineshopping.saleorderservice;

public class ItemResponseModel {
	
	//To consume the "Item" that Item-service returns, 
	//we are creating a new Java class with class fields that match names in the Item JSON object.
	private Long id;
	private String item_name;
	private String item_desc;
	private Integer price;
	
	public ItemResponseModel(Long id, String itemName, String itemDesc, Integer price) {
		this.id = id;
		this.item_name = itemName;
		this.item_desc = itemDesc;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ItemResponseModel [id=" + id + ", item_name=" + item_name + ", item_desc=" + item_desc + ", price="
				+ price + "]";
	}
	
}
