package com.splash.controller.auth.login;

import java.io.Serializable;
import java.util.Date;



import com.splash.domain.entity.UserEntity;

public class LoginResponse{
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


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getUserrole() {
		return userrole;
	}


	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getCreatedby() {
		return createdby;
	}


	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}


	public Date getCreatedon() {
		return createdon;
	}


	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}


	

}
