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

	public UserEntity(int userid, String name, String email, String password, String username, String phone,
			String userrole, String status, String createdby, Date createdon) {
		super();
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

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UserEntity [userid=" + userid + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", username=" + username + ", phone=" + phone + ", userrole=" + userrole + ", status=" + status
				+ ", createdby=" + createdby + ", createdon=" + createdon + "]";
	}
	
	
	
	
	
}
