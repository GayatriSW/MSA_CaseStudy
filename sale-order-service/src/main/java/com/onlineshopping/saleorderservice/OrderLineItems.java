package com.onlineshopping.saleorderservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_line_item")
public class OrderLineItems {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Item_ID")
	private Long id;
	
	@Column(name = "Item_name")
	private String itemName;
	
	@Column(name = "Item_Quantity")
	private Integer itemQty;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Order order;
	
	public OrderLineItems() {
		super();
	}
	

	public OrderLineItems(Long id, String itemName, Integer itemQty) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemQty = itemQty;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemQty() {
		return itemQty;
	}

	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderLineItems [id=" + id + ", itemName=" + itemName + ", itemQty=" + itemQty + ", order=" + order
				+ "]";
	}
	
}
