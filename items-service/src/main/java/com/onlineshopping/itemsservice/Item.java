package com.onlineshopping.itemsservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item {
	
	@Id
	@Column(name="item_id")
	private Long id;
	private String item_name;
	private String item_desc;
	private Integer price;
	
	public Item() {
	}
	
	public Item(Long id, String item_name, String item_desc, Integer price) {
		super();
		this.id = id;
		this.item_name = item_name;
		this.item_desc = item_desc;
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
}
