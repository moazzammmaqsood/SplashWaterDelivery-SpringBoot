package com.splash.entity.model;

public class ClientDetails {
	
	int userid;
	int clientid;
	String name;
	String contact;
	String address;
	int totalbottles;
	int bottlesholding;
	int rate;
	String  lastdelivery;
	int daysperdelivery;
	int paymentremaining;
	int paid;
	int deposit;
	int bottles;
	String oncall;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getBottlesholding() {
		return bottlesholding;
	}
	public void setBottlesholding(int bottlesholding) {
		this.bottlesholding = bottlesholding;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getTotalbottles() {
		return totalbottles;
	}
	public void setTotalbottles(int totalbottles) {
		this.totalbottles = totalbottles;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getLastdelivery() {
		return lastdelivery;
	}
	public void setLastdelivery(String lastdelivery) {
		this.lastdelivery = lastdelivery;
	}
	public int getDaysperdelivery() {
		return daysperdelivery;
	}
	public void setDaysperdelivery(int daysperdelivery) {
		this.daysperdelivery = daysperdelivery;
	}
	
	
	


	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getBottles() {
		return bottles;
	}
	public void setBottles(int bottles) {
		this.bottles = bottles;
	}
	public String getOncall() {
		return oncall;
	}
	public void setOncall(String oncall) {
		this.oncall = oncall;
	}
	public int getPaymentremaining() {
		return paymentremaining;
	}
	public void setPaymentremaining(int paymentremaining) {
		this.paymentremaining = paymentremaining;
	}
	public int getPaid() {
		return paid;
	}
	public void setPaid(int paid) {
		this.paid = paid;
	}






	public ClientDetails(int userid, int clientid, String name, String contact, String address, int totalbottles,
			int bottlesholding, int rate, String lastdelivery, int daysperdelivery, int paymentremaining, int paid,
			int deposit, int bottles, String oncall) {
		super();
		this.userid = userid;
		this.clientid = clientid;
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.totalbottles = totalbottles;
		this.bottlesholding = bottlesholding;
		this.rate = rate;
		this.lastdelivery = lastdelivery;
		this.daysperdelivery = daysperdelivery;
		this.paymentremaining = paymentremaining;
		this.paid = paid;
		this.deposit = deposit;
		this.bottles = bottles;
		this.oncall = oncall;
	}
	public ClientDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "ClientDetails [userid=" + userid + ", clientid=" + clientid + ", name=" + name + ", contact=" + contact
				+ ", address=" + address + ", totalbottles=" + totalbottles + ", bottlesholding=" + bottlesholding
				+ ", rate=" + rate + ", lastdelivery=" + lastdelivery + ", daysperdelivery=" + daysperdelivery
				+ ", paymentremaining=" + paymentremaining + ", paid=" + paid + "]";
	}


	
	
	
	
	
	

}
