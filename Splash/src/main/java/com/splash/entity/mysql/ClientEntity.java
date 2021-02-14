package com.splash.entity.mysql;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="clients")
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
	
}
