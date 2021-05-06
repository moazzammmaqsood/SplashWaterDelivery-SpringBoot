package com.splash.controller.auth.login;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.splash.domain.entity.UserEntity;

public class LoginResponse implements Serializable {
    private String token;
  
	private int userid;
	
	 
	private String name ;
	
	 
	private String email ;
	
	 
	private String password ;
	
	 
	private String  username;
 
 
	private String phone ;
	

	private String userrole ;
	
	
	private String status ;
	
	
	private String createdby ;
	
	
	private Date createdon;


	public LoginResponse(String token, int userid, String name, String email, String password, String username,
			String phone, String userrole, String status, String createdby, Date createdon) {
		super();
		this.token = token;
		this.userid = userid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.username = username;
		this.phone = phone;
		this.userrole = userrole;
		this.status = status;
		this.createdby = createdby;
		this.createdon = createdon;
	}


	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", userid=" + userid + ", name=" + name + ", email=" + email
				+ ", password=" + password + ", username=" + username + ", phone=" + phone + ", userrole=" + userrole
				+ ", status=" + status + ", createdby=" + createdby + ", createdon=" + createdon + "]";
	}


	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	
	public LoginResponse(String token,UserEntity user) {
		super();
		this.token = token;
		this.userid = user.getUserid();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.username = user.getUsername();
		this.phone = user.getPhone();
		this.userrole = user.getUserrole();
		this.status = user.getStatus();
		this.createdby = user.getCreatedby();
		this.createdon = user.getCreatedon();
	}



}
