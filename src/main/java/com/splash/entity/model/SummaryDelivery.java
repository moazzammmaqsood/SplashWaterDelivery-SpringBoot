package com.splash.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity(name="SummaryDelivery")
@Table(name="orders")
@NamedNativeQuery(name="OrderEntity.getdeliveryBydate",query="SELECT  c.clientid,c.userid,u.name,c.address,o.bottlesdelivered ,o.bottlesrecieved ,o.payment, c.oncall,DATE_FORMAT(o.date , '%Y-%m-%d') as date"
		+ " FROM   worthywa_splash.client c inner join worthywa_splash.orders o on c.clientid=o.clientid "
		+ " inner join worthywa_splash.users u on c.userid = u.userid  where "
		+ " DATE_FORMAT(o.date , '%Y-%m-%d') = ?  and o.vendorid =? and o.status != 'D' " ,resultClass =SummaryDelivery.class)



public class SummaryDelivery {
	
	@Id
	 
	@Column(name="clientid")
	Integer clientid;
	
	@Column(name="userid")
	Integer userid;
	
	@Column(name="name")
	String name;
	
	@Column(name="address")
	String address;
	
	@Column(name="bottlesdelivered")
	Integer bottlesdelivered;
	
	@Column(name="bottlesrecieved")
	Integer bottlesrecieved;
	
	@Column(name="payment")
	Integer payment;
	
	@Column(name="oncall")
	String oncall;
	
 
	@Column(name="date")
	String date;


	public Integer getClientid() {
		return clientid;
	}


	public void setClientid(Integer clientid) {
		this.clientid = clientid;
	}


	public Integer getUserid() {
		return userid;
	}


	public void setUserid(Integer userid) {
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


	public Integer getBottlesdelivered() {
		return bottlesdelivered;
	}


	public void setBottlesdelivered(Integer bottlesdelivered) {
		this.bottlesdelivered = bottlesdelivered;
	}


	public Integer getBottlesrecieved() {
		return bottlesrecieved;
	}


	public void setBottlesrecieved(Integer bottlesrecieved) {
		this.bottlesrecieved = bottlesrecieved;
	}


	public Integer getPayment() {
		return payment;
	}


	public void setPayment(Integer payment) {
		this.payment = payment;
	}


	public String getOncall() {
		return oncall;
	}


	public void setOncall(String oncall) {
		this.oncall = oncall;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public SummaryDelivery(int clientid, int userid, String name, String address, int bottlesdelivered,
			int bottlesrecieved, int payment, String oncall, String date) {
		super();
		this.clientid = clientid;
		this.userid = userid;
		this.name = name;
		this.address = address;
		this.bottlesdelivered = bottlesdelivered;
		this.bottlesrecieved = bottlesrecieved;
		this.payment = payment;
		this.oncall = oncall;
		this.date = date;
	}


	@Override
	public String toString() {
		return "SummaryDelivery [clientid=" + clientid + ", userid=" + userid + ", name=" + name + ", address="
				+ address + ", bottlesdelivered=" + bottlesdelivered + ", bottlesrecieved=" + bottlesrecieved
				+ ", payment=" + payment + ", oncall=" + oncall + ", date=" + date + "]";
	}


	public SummaryDelivery() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
