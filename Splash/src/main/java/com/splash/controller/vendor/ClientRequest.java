package com.splash.controller.vendor;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClientRequest {
	
    @Size(max = 100, message = "Max length for name can not exceed 100 characters.")
    @NotEmpty(message = "name must not be empty.")
	private String name;
    
    @Size(max = 45, message = "Max length for contactno can not exceed 100 characters.")
    private String contactno;
    
    @Size(max = 100, message = "Max length for address can not exceed 100 characters.")
    private String address;
    
    
    private String email;
	

    private int frequency;
	
    
	private int rate;

	private int deposit;
	
	private String lastdelivery;
 
    int lastbottles;
    
    int lastrecieved;
    
    int lastpayment;

	private int noofbottles;
	
	private String oncall;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public String getLastdelivery() {
		return lastdelivery;
	}

	public void setLastdelivery(String lastdelivery) {
		this.lastdelivery = lastdelivery;
	}

	public int getNoofbottles() {
		return noofbottles;
	}

	public void setNoofbottles(int noofbottles) {
		this.noofbottles = noofbottles;
	}

	



	public ClientRequest(
			@Size(max = 100, message = "Max length for name can not exceed 100 characters.") @NotEmpty(message = "name must not be empty.") String name,
			@Size(max = 45, message = "Max length for contactno can not exceed 100 characters.") String contactno,
			@Size(max = 100, message = "Max length for address can not exceed 100 characters.") String address,
			String email, int frequency, int rate, int deposit, String lastdelivery, int lastbottles, int lastrecieved,
			int lastpayment, int noofbottles, String oncall) {
		super();
		this.name = name;
		this.contactno = contactno;
		this.address = address;
		this.email = email;
		this.frequency = frequency;
		this.rate = rate;
		this.deposit = deposit;
		this.lastdelivery = lastdelivery;
		this.lastbottles = lastbottles;
		this.lastrecieved = lastrecieved;
		this.lastpayment = lastpayment;
		this.noofbottles = noofbottles;
		this.oncall = oncall;
	}

	public ClientRequest() {
		super();
		// TODO Auto-generated constructor stub
	}




	public int getLastbottles() {
		return lastbottles;
	}

	public void setLastbottles(int lastbottles) {
		this.lastbottles = lastbottles;
	}

	public int getLastrecieved() {
		return lastrecieved;
	}

	public void setLastrecieved(int lastrecieved) {
		this.lastrecieved = lastrecieved;
	}

	public int getLastpayment() {
		return lastpayment;
	}

	public void setLastpayment(int lastpayment) {
		this.lastpayment = lastpayment;
	}

	public String getOncall() {
		return oncall;
	}

	public void setOncall(String oncall) {
		this.oncall = oncall;
	}
	
	
	
	
	
	
	

}
