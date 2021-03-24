package com.splash.controller.vendor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetClientsResponse {

	
	String address;
	String clientname;
	String contactno;
	int id;
	int frequency;
	int rate;
	int bottles;
	Date lastdelivered;

	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getBottles() {
		return bottles;
	}
	public void setBottles(int bottles) {
		this.bottles = bottles;
	}
	public Date getLastDelivery() {
		return lastdelivered;
	}
	public void setLastDelivery(Date lastDelivery) {
		this.lastdelivered = lastDelivery;
	}
	public GetClientsResponse(String address, String clientname, String contactno, int id,
			int frequency, int rate, int bottles, Date lastDelivery) {
		super();
		this.address = address;
		this.clientname = clientname;
		this.contactno = contactno;
		this.id = id;
		this.frequency = frequency;
		this.rate = rate;
		this.bottles = bottles;
		lastdelivered = lastDelivery;

	}


	
	
	@Override
	public String toString() {
		return "GetClientsResponse [address=" + address + ", clientname=" + clientname + ", contactno=" + contactno
				+ ", id=" + id + ", frequency=" + frequency + ", rate=" + rate + ", bottles=" + bottles
				+ ", lastdelivered=" + lastdelivered + "]";
	}
	public GetClientsResponse() {
		super();

	}
	

}
