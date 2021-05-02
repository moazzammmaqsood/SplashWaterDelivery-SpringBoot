package com.splash.domain.entity;

import javax.persistence.*;

@Entity
@Table(name="payments")
public class PaymentEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int paymentid; 
	
	@Column(name="clientid")
	private int clientid ;
	
	@Column(name="vendorid")
	private int vendorid ; 
	
	@Column(name="payment")
	private int payment ;
	
	@Column(name="bottles")
	private int bottles ;
	
	
	
}
