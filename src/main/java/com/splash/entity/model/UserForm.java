package com.splash.entity.model;


public class UserForm {




	String name ;

	String email ;
	
	String password ;
	 
	String  username;

	String phone ;

	String userrole ;

	String status ;

	String createdby ;

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

	public UserForm(String name, String email, String password, String username, String phone, String userrole,
			String status, String createdby) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.username = username;
		this.phone = phone;
		this.userrole = userrole;
		this.status = status;
		this.createdby = createdby;
	}

	@Override
	public String toString() {
		return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", username=" + username
				+ ", phone=" + phone + ", userrole=" + userrole + ", status=" + status + ", createdby=" + createdby
				+ "]";
	}

	public UserForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


}
