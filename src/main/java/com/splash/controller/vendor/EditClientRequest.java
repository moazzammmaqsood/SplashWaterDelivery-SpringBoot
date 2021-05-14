package com.splash.controller.vendor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EditClientRequest {
	
	@Min(value=1,message="Invalid User Id")
	int userid ;
	
	@Min(value=1,message="Invalid Client Id")
	int clientid;
	
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

	private int noofbottles;
	
	 @NotEmpty(message = "On Call must not be empty.")
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

 

	public int getNoofbottles() {
		return noofbottles;
	}

	public void setNoofbottles(int noofbottles) {
		this.noofbottles = noofbottles;
	}

	




	public String getOncall() {
		return oncall;
	}

	public void setOncall(String oncall) {
		this.oncall = oncall;
	}

	public EditClientRequest(@Min(value = 1, message = "Invalid User Id") int userid,
			@Min(value = 1, message = "Invalid Client Id") int clientid,
			@Size(max = 100, message = "Max length for name can not exceed 100 characters.") @NotEmpty(message = "name must not be empty.") String name,
			@Size(max = 45, message = "Max length for contactno can not exceed 100 characters.") @NotEmpty(message = "contactno must not be empty.") String contactno,
			@Size(max = 100, message = "Max length for address can not exceed 100 characters.") @NotEmpty(message = "address must not be empty.") String address,
			String email, int frequency, int rate, int deposit, int noofbottles, String oncall) {
		super();
		this.userid = userid;
		this.clientid = clientid;
		this.name = name;
		this.contactno = contactno;
		this.address = address;
		this.email = email;
		this.frequency = frequency;
		this.rate = rate;
		this.deposit = deposit;
		this.noofbottles = noofbottles;
		this.oncall = oncall;
	}
 
	public EditClientRequest() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
	



  
	
	
	
	
}
