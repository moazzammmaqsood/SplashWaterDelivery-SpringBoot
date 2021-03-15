package com.splash.domain.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="request")
public class RequestEntity {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="requestid")
	private int requestid ; 
	
	@Column(name="userid")
	private int userid;
	
	@Column(name="vendorid")
	private int vendorid;
	
	@Column(name="bottlesrequested")
	private int bottlesrequested ;
	
	@Column(name="status")
	private String status;
	
	@Column(name="date")
	private Date date;
	
	
}
