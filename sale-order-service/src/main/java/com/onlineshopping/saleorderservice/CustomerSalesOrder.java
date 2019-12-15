package com.onlineshopping.saleorderservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Customer_SOS")
public class CustomerSalesOrder {

	@Id
	@Column(name="cust_id")
	private Long id;
	
	@Column(name="cust_first_name")
	private String first_name;
	
	@Column(name="cust_last_name")
	private String last_name;
	
	@Column(name="cust_email")
	private String email;

	public CustomerSalesOrder () {}
	
	public CustomerSalesOrder(Long id, String first_name, String last_name, String email) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CustomerSalesOrder [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email="
				+ email + "]";
	}
}
