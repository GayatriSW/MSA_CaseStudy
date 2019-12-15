package com.onlineshopping.saleorderservice;

import org.springframework.stereotype.Component;

@Component
public class OrderInfo {
	private Long orderId;
	private String orderMssg;
	
	public OrderInfo() { }
	public OrderInfo(Long orderId, String orderMssg) {
		super();
		this.orderId = orderId;
		this.orderMssg = orderMssg;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderMssg() {
		return orderMssg;
	}
	public void setOrderMssg(String orderMssg) {
		this.orderMssg = orderMssg;
	}
	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", orderMssg=" + orderMssg + "]";
	}
}
