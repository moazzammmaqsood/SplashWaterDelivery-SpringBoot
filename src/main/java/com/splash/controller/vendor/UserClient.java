package com.splash.controller.vendor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.splash.domain.entity.ClientDelivery;
 
 
@Entity(name="UserClient")

@NamedNativeQuery(name="ClientEntity.getbyClientsbyvendor",query=" SELECT c.userid,c.clientid,u.name,c.address,c.bottles ,u.status FROM worthywa_splash.client c Inner Join worthywa_splash.users u on c.userid =  u.userid where vendorid=? order by u.name",resultClass =UserClient.class)



@Table(name="client")
public class UserClient {

 
	@Id
	int userid ; 
	@Column
	int clientid;
	@Column
	String name;
	@Column
	String Address;
	@Column
	int bottles;

	@Column
	String status;


	public UserClient(int userid, int clientid, String name, String address, int bottles,String status) {
		super();
		this.userid = userid;
		this.clientid = clientid;
		this.name = name;
		Address = address;
		this.bottles = bottles;
		this.status= status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
