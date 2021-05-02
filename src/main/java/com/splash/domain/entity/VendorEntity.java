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
	
	@Column(name="userid")
	private int userid ;
	
	@Column(name="address")
	private String address ;
	
	@Column(name="logo")
	private String logo ;
	
	@Column(name="contactperson")
	private String contactperson;
	
	@Column(name="contactno")
	private String 	contactno;
	
	@Column(name="shortcode")
	private String 	shortcode;

	public int getVendorid() {
		return vendorid;
	}

	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getShortcode() {
		return shortcode;
	}

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}
	

	public int getUserid() {
		return userid;
	}

	public void setUserid( int userid) {
		this.userid = userid;
	}



	
	
	public VendorEntity(int vendorid, String name,  int userid, String address, String logo, String contactperson,
			String contactno, String shortcode) {
		super();
		this.vendorid = vendorid;
		this.name = name;
		this.userid = userid;
		this.address = address;
		this.logo = logo;
		this.contactperson = contactperson;
		this.contactno = contactno;
		this.shortcode = shortcode;
	}

	public VendorEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "VendorEntity [vendorid=" + vendorid + ", name=" + name + ", userid=" + userid + ", address=" + address
				+ ", logo=" + logo + ", contactperson=" + contactperson + ", contactno=" + contactno + ", shortcode="
				+ shortcode + "]";
	}



	
}
