package com.splash.controller.vendor;

public class BottleDelivered {

	int clientid;
	int bottlesdel;
	int bottlesrec;
	int payment;
	
	
	
	


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




	public BottleDelivered(int clientid, int bottlesdel, int bottlesrec, int payment) {
		super();
		this.clientid = clientid;
		this.bottlesdel = bottlesdel;
		this.bottlesrec = bottlesrec;
		this.payment = payment;
	}
	public BottleDelivered() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	
	
	
}
