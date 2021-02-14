package com.splash.entity.mysql;

import java.util.Date;

import javax.persistence.*;


@Entity
@Table(name="orders")
public class OrderEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderid ;
	
	@Column(name="clientid")
	private int clientid ;
	
	@Column(name="vendorid")
	private int vendorid ;
	
	@Column(name="bottlesdelivered")
	private int bottlesdelivered ;
	
	@Column(name="bottlesrecieved")
	private int bottlesrecieved ;
	
	@Column(name="payment")
	private int payment; 
	
	@Column(name="date")
	private Date date;

}
