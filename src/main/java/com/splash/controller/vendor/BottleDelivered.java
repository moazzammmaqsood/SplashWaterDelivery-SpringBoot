package com.splash.controller.vendor;

import java.util.Date;

public class BottleDelivered {

	int clientid;
	int bottlesdel;
	int bottlesrec;
	int payment;
	String date;
	
	
	


	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	public int getBottlesdel() {
		return bottlesdel;
	}
	public void setBottlesdel(int bottlesdel) {
		this.bottlesdel = bottlesdel;
	}
	public int getBottlesrec() {
		return bottlesrec;
	}
	public void setBottlesrec(int bottlesrec) {
		this.bottlesrec = bottlesrec;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}




	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


	
	
	
	public BottleDelivered(int clientid, int bottlesdel, int bottlesrec, int payment, String date) {
		super();
		this.clientid = clientid;
		this.bottlesdel = bottlesdel;
		this.bottlesrec = bottlesrec;
		this.payment = payment;
		this.date = date;
	}
	
	
	public BottleDelivered() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	
	
	
}
