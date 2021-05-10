package com.splash.domain.entity;

 

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity(name="ClientTotalDetail")

@NamedNativeQuery(name="OrderEntity.getClientTotalDetail",query=" SELECT clientid ,sum(bottlesdelivered) as totalbottles ,sum(bottlesrecieved) as totalrecieved ,sum(payment) as totalpayment , max(date) date FROM worthywa_splash.orders where clientid = ?1 group by clientid ",resultClass =ClientTotalDetail.class)


@Table(name="orders")
public class ClientTotalDetail {
	@Id
	@Column(name="clientid")
	int clientid;
	
	@Column(name="totalbottles")
	int totalbottles;
	
	@Column(name="totalrecieved")
	int totalrecieved;
	
	@Column(name="totalpayment")
	int totalpayment;
	
	
	@Column(name="date")
	Date date;
	
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	public int getTotalbottles() {
		return totalbottles;
	}
	public void setTotalbottles(int totalbottles) {
		this.totalbottles = totalbottles;
	}
	public int getTotalrecieved() {
		return totalrecieved;
	}
	public void setTotalrecieved(int totalrecieved) {
		this.totalrecieved = totalrecieved;
	}
	public int getTotalpayment() {
		return totalpayment;
	}
	public void setTotalpayment(int totalpayment) {
		this.totalpayment = totalpayment;
	}
	
	
	
	public ClientTotalDetail(int clientid, int totalbottles, int totalrecieved, int totalpayment, Date date) {
		super();
		this.clientid = clientid;
		this.totalbottles = totalbottles;
		this.totalrecieved = totalrecieved;
		this.totalpayment = totalpayment;
		this.date = date;
	}
	public ClientTotalDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ClientTotalDetail [clientid=" + clientid + ", totalbottles=" + totalbottles + ", totalrecieved="
				+ totalrecieved + ", totalpayment=" + totalpayment + ", date=" + date + "]";
	}
	
	
	
	
	
	
	 
	
	

}
