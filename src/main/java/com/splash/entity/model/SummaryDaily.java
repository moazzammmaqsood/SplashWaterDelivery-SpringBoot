package com.splash.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

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
	Integer id;
	
	@Column(name="houses")
	Integer houses;
	
	@Column(name="bottlesdelivered")
    Integer bottlesdelivered;
	
	@Column(name="revenue")
	Integer revenue;
	
 	
	@Column(name="bottlesrecieved")
	Integer bottlesrecieved;
	
	@Column(name="payment")
	Integer payment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBottlesdelivered() {
		return bottlesdelivered;
	}

	public void setBottlesdelivered(Integer bottlesdelivered) {
		this.bottlesdelivered = bottlesdelivered;
	}

	public Integer getRevenue() {
		return revenue;
	}

	public void setRevenue(Integer revenue) {
		this.revenue = revenue;
	}

	public Integer getBottlesrecieved() {
		return bottlesrecieved;
	}

	public void setBottlesrecieved(Integer bottlesrecieved) {
		this.bottlesrecieved = bottlesrecieved;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	
	
	public Integer getHouses() {
		return houses;
	}

	public void setHouses(Integer houses) {
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
