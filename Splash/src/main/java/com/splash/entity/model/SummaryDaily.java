package com.splash.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import com.splash.domain.entity.ClientDelivery;

@Entity(name="SummaryDaily")
@Table(name="orders")
@NamedNativeQuery(name= "OrderEntity.getDailySummary" ,query =  "SELECT 1 id,COUNT(*) houses, SUM(o.bottlesdelivered) bottlesdelivered, SUM(o.bottlesdelivered*c.rate) revenue,"
		+ "SUM(o.bottlesrecieved ) bottlesrecieved,SUM(o.payment) payment "
		+ " FROM   worthywa_splash.client c inner join worthywa_splash.orders o on c.clientid=o.clientid "
		+ " inner join worthywa_splash.users u on c.userid = u.userid  where "
		+ " DATE_FORMAT(o.date , '%Y-%m-%d') = ?  and o.vendorid = ?",resultClass =SummaryDaily.class  )



public class SummaryDaily {
	
	@Id
  	@Column(name="id")
	int id;
	
	 
	
	@Column(name="houses")
	int houses;
	
	@Column(name="bottlesdelivered")
	int bottlesdelivered;
	
	@Column(name="revenue")
	int revenue;
	
 	
	@Column(name="bottlesrecieved")
	int bottlesrecieved;
	
	@Column(name="payment")
	int payment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBottlesdelivered() {
		return bottlesdelivered;
	}

	public void setBottlesdelivered(int bottlesdelivered) {
		this.bottlesdelivered = bottlesdelivered;
	}

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	public int getBottlesrecieved() {
		return bottlesrecieved;
	}

	public void setBottlesrecieved(int bottlesrecieved) {
		this.bottlesrecieved = bottlesrecieved;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	
	
	public int getHouses() {
		return houses;
	}

	public void setHouses(int houses) {
		this.houses = houses;
	}




	public SummaryDaily(int id, int houses, int bottlesdelivered, int revenue, int bottlesrecieved, int payment) {
		super();
		this.id = id;
		this.houses = houses;
		this.bottlesdelivered = bottlesdelivered;
		this.revenue = revenue;
		this.bottlesrecieved = bottlesrecieved;
		this.payment = payment;
	}

	public SummaryDaily() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "SummaryDaily [id=" + id + ", houses=" + houses + ", bottlesdelivered=" + bottlesdelivered + ", revenue="
				+ revenue + ", bottlesrecieved=" + bottlesrecieved + ", payment=" + payment + "]";
	}
	

	

	
	
	

}
