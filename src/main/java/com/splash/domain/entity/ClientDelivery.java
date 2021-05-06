package com.splash.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;



@Entity(name="ClientDelivery")

@NamedNativeQuery(name="OrderEntity.getDailydelivery",query="select o.orderid,  c.clientid,c.userid,u.name,c.address,c.bottles,c.frequency,c.rate,DATEDIFF(CURDATE(),o.date ) AS days from worthywa_splash.client c inner join  worthywa_splash.orders o  on c.clientid =o.clientid "
		+ "Inner join worthywa_splash.users u on c.userid = u.userid"
		+ " where o.date = (select max(date) from  worthywa_splash.orders where clientid=o.clientid)"
		+ " and o.vendorid= ?",resultClass =ClientDelivery.class)



@Table(name="orders")


public class ClientDelivery {
	
	@Id
	int orderid;
	
	@Column(name="clientid")
	int clientid;
	
	@Column(name="userid")
	int userid;
	
	@Column(name="name")
	String name;
	
	@Column(name="address")
	String address;
	
	@Column(name="bottles")
	int bottles;
	
	@Column(name="frequency")
	int frequency;
	
	@Column(name="rate")
	int rate;
	
	@Column(name="days")
	int days;

	public ClientDelivery(int orderid, int clientid, int userid, String name, String address, int bottles,
			int frequency, int rate, int days) {
		super();
		this.orderid = orderid;
		this.clientid = clientid;
		this.userid = userid;
		this.name = name;
		this.address = address;
		this.bottles = bottles;
		this.frequency = frequency;
		this.rate = rate;
		this.days = days;
	}

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

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBottles() {
		return bottles;
	}

	public void setBottles(int bottles) {
		this.bottles = bottles;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public ClientDelivery() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ClientDelivery [orderid=" + orderid + ", clientid=" + clientid + ", userid=" + userid + ", name=" + name
				+ ", address=" + address + ", bottles=" + bottles + ", frequency=" + frequency + ", rate=" + rate
				+ ", days=" + days + "]";
	}
	
	
	
	
	public boolean bottlefinished() {
		
		
		if(days >= frequency) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	
	
	

}
