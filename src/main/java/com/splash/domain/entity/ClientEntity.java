package com.splash.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="client")
public class ClientEntity {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientid ;
	
	
	@Column(name="userid")
	private int userid ; 

	@Column(name="address")
	private String address;
	
	@Column(name="rate")
	private int rate ;
	
	@Column(name="vendorid")
	private int vendorid;

	
	@Column(name="frequency")
	private int frequency;

	@Column(name="bottles")
	private int bottles;

	@Column(name="deposit")
	private int deposit;

	
	 @OneToOne(mappedBy = "client")
	  private UserEntity user;

	 
	 


	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getVendorid() {
		return vendorid;
	}

	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
	}

	
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}




	public int getBottles() {
		return bottles;
	}

	public void setBottles(int bottles) {
		this.bottles = bottles;
	}
	
	
	

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	
	public ClientEntity(int clientid, int userid, String address, int rate, int vendorid, int frequency, int bottles,
			int deposit) {
		super();
		this.clientid = clientid;
		this.userid = userid;
		this.address = address;
		this.rate = rate;
		this.vendorid = vendorid;
		this.frequency = frequency;
		this.bottles = bottles;
		this.deposit = deposit;
	}

	public ClientEntity(int userid, String address, int rate, int vendorid, int frequency, int bottles,int deposit) {
		super();
		this.userid = userid;
		this.address = address;
		this.rate = rate;
		this.vendorid = vendorid;
		this.frequency = frequency;
		this.bottles = bottles;
		this.deposit= deposit;
	}

	public ClientEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ClientEntity [clientid=" + clientid + ", userid=" + userid + ", address=" + address + ", rate=" + rate
				+ ", vendorid=" + vendorid + ", frequency=" + frequency + ", bottles=" + bottles + ", deposit="
				+ deposit + ", user=" + user + "]";
	}




	
	
}
