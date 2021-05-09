package com.splash.controller.vendor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
 
 
public class UserClient {

 
	int userid ; 
	int clientid; 
	String name; 
	String Address; 
	int bottles;
//	u.userid,
//	  u.name,
//	  u.email, 
//	  u.username,
//	  u.phone,
//	  u.userrole,
//	  u.status,
//	  u.createdby  
//	  ,c.clientid ,
//	  c.address , 
//	  c.rate,
//	  vendorid,
//	  frequency,
//	  bottles,
//	  deposit 
	
	
	public UserClient(int userid, int clientid, String name, String address, int bottles) {
		super();
		this.userid = userid;
		this.clientid = clientid;
		this.name = name;
		Address = address;
		this.bottles = bottles;
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
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getBottles() {
		return bottles;
	}
	public void setBottles(int bottles) {
		this.bottles = bottles;
	}
	
	public UserClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserClient [userid=" + userid + ", clientid=" + clientid + ", name=" + name + ", Address=" + Address
				+ ", bottles=" + bottles + "]";
	}


	
	
	


}
