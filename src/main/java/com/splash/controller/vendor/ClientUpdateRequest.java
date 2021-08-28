package com.splash.controller.vendor;

public class ClientUpdateRequest {

	private String username;
	private String name;
	private String contactno;
	private String address;
	private int daysdelivery;
	private int rate;	
	private int deposit;
	private int noofbottles;
	private String oncall;

	public String getOncall() {
		return oncall;
	}

	public void setOncall(String oncall) {
		this.oncall = oncall;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getDaysdelivery() {
		return daysdelivery;
	}
	public void setDaysdelivery(int daysdelivery) {
		this.daysdelivery = daysdelivery;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getNoofbottles() {
		return noofbottles;
	}
	public void setNoofbottles(int noofbottles) {
		this.noofbottles = noofbottles;
	}

	public ClientUpdateRequest(String username, String name, String contactno, String address, int daysdelivery, int rate, int deposit, int noofbottles, String oncall) {
		this.username = username;
		this.name = name;
		this.contactno = contactno;
		this.address = address;
		this.daysdelivery = daysdelivery;
		this.rate = rate;
		this.deposit = deposit;
		this.noofbottles = noofbottles;
		this.oncall = oncall;
	}

	@Override
	public String toString() {
		return "ClientUpdateRequest{" +
				"username='" + username + '\'' +
				", name='" + name + '\'' +
				", contactno='" + contactno + '\'' +
				", address='" + address + '\'' +
				", daysdelivery=" + daysdelivery +
				", rate=" + rate +
				", deposit=" + deposit +
				", noofbottles=" + noofbottles +
				", oncall='" + oncall + '\'' +
				'}';
	}

	public ClientUpdateRequest() {
	}
}
