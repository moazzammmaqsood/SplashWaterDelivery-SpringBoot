package com.splash.domain.entity;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="orders")
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderid ;
	
	@Column(name="clientid")
	private int clientid ;
	
	@Column(name="vendorid")
	private int vendorid ;
	
	@Column(name="bottlesdelivered")
	private int bottlesdelivered ;
	
	@Column(name="bottlesrecieved")
	private int bottlesrecieved ;
	
	@Column(name="payment")
	private int payment; 
	
	@Column(name="date")
	private Date date;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public int getVendorid() {
		return vendorid;
	}

	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
	}

	public int getBottlesdelivered() {
		return bottlesdelivered;
	}

	public void setBottlesdelivered(int bottlesdelivered) {
		this.bottlesdelivered = bottlesdelivered;
	}

	public int getBottlesrecieved() {
		return bottlesrecieved;
	}

	public void setBottlesrecieved(int bottlesrecieved) {
		this.bottlesrecieved = bottlesrecieved;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public OrderEntity(int orderid, int clientid, int vendorid, int bottlesdelivered, int bottlesrecieved, int payment,
			Date date) {
		super();
		this.orderid = orderid;
		this.clientid = clientid;
		this.vendorid = vendorid;
		this.bottlesdelivered = bottlesdelivered;
		this.bottlesrecieved = bottlesrecieved;
		this.payment = payment;
		this.date = date;
	}

	public OrderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
}
