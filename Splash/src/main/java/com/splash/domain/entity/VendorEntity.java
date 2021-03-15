package com.splash.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="vendor")
public class VendorEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vendorid")
	private int vendorid ;

	@Column(name="name")
	private String name ;
	
	@Column(name="address")
	private String address ;
	
	@Column(name="logo")
	private String logo ;
	
	@Column(name="contactperson")
	private String contactperson;
	
	@Column(name="contactno")
	private String 	contactno;
	
	
	
}
