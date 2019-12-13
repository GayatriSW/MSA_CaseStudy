package com.onlineshopping.saleorderservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sales_order")
public class Order {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
	private Long id;
	
	@Column(name = "Order_Date")
	private Date orderDate;
	
	@Column(name = "Cust_ID")
	private Long custId;
	
	@Column(name = "Order_Desc")
	private String orderDesc;
	
	@Column(name = "Total_Price")
	private Integer totalPrice;
	
	@OneToMany(mappedBy="order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = OrderLineItems.class)
    private List<OrderLineItems> orderLineItems;
	
	public Order() {
	}

	public Order(Date orderDate, Long custId, String orderDesc, Integer totalPrice) {
		super();
		this.orderDate = orderDate;
		this.custId = custId;
		this.orderDesc = orderDesc;
		this.totalPrice = totalPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<OrderLineItems> getOrderLineItems() {
		if (this.orderLineItems == null) {
            this.orderLineItems = new ArrayList<>();
        }
		return orderLineItems;
	}

	public void setOrderLineItems(List<OrderLineItems> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}
	
	public void addOrderLineItem(OrderLineItems ordLineItem) {
		getOrderLineItems().add(ordLineItem);
		ordLineItem.setOrder(this);
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", custId=" + custId + ", orderDesc=" + orderDesc
				+ ", totalPrice=" + totalPrice + ", orderLineItems=" + orderLineItems + "]";
	}
	
}
