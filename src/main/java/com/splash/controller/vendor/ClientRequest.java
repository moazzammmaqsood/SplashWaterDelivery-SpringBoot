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
    @NotEmpty(message = "contactno must not be empty.")
    private String contactno;
    
    @Size(max = 100, message = "Max length for address can not exceed 100 characters.")
    @NotEmpty(message = "address must not be empty.")
    private String address;
    
    
    private String email;
	

    private int frequency;
	

	private int rate;

	private int deposit;
	
	private String lastdelivery;


	private int noofbottles;

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
			@Size(max = 45, message = "Max length for contactno can not exceed 100 characters.") @NotEmpty(message = "contactno must not be empty.") String contactno,
			@Size(max = 100, message = "Max length for address can not exceed 100 characters.") @NotEmpty(message = "address must not be empty.") String address,
			String email, @NotNull int frequency, @NotNull int rate, @NotNull int deposit, String lastdelivery,
			@NotNull int noofbottles) {
		super();
		this.name = name;
		this.contactno = contactno;
		this.address = address;
		this.email = email;
		this.frequency = frequency;
		this.rate = rate;
		this.deposit = deposit;
		this.lastdelivery = lastdelivery;
		this.noofbottles = noofbottles;
	}

	public ClientRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ClientRequest [name=" + name + ", contactno=" + contactno + ", address=" + address + ", email=" + email
				+ ", frequency=" + frequency + ", rate=" + rate + ", deposit=" + deposit + ", lastdelivery="
				+ lastdelivery + ", noofbottles=" + noofbottles + "]";
	}
	
	
	
	
	
	
	

}
