package com.splash.entity.mysql;


import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="users")
public class UserEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
	@Column(name="name")
	String name ;
	
	@Column(name="email")
	String email ;
	
	@Column(name="password")
	String password ;
	
	@Column(name="username")
	String  username;

	@Column(name="phone")
	String phone ;
	
	@Column(name="userrole")
	String userrole ;
	
	@Column(name="status")
	String status ;
	
	@Column(name="createdby")
	String createdby ;
	
	@Column(name="createdon")
	Date createdon;
}
