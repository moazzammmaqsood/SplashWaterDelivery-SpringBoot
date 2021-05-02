package com.splash.controller.auth.signup;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


public class SignupRequest {

	 @NotEmpty(message = "name must not be empty.")
	private String name; 
	 @NotEmpty(message = "email must not be empty.")
	private String email;
	 @NotEmpty(message = "password must not be empty.")
	private String password ;
	 @NotEmpty(message = "username must not be empty.")
	private String username ;
	 @NotEmpty(message = "phone must not be empty.")
	private String phone;
	 @NotEmpty(message = "createdby must not be empty.")
	 private String createdby;
	 
	 @Size(max = 1, message = "Max length for userrole can not exceed 1 characters.")
	 @NotEmpty(message = "userrole must not be empty.")
	 private String userrole;
	 
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
	
	
	
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}


	
	
	public SignupRequest(@NotEmpty(message = "name must not be empty.") String name,
			@NotEmpty(message = "email must not be empty.") String email,
			@NotEmpty(message = "password must not be empty.") String password,
			@NotEmpty(message = "username must not be empty.") String username,
			@NotEmpty(message = "phone must not be empty.") String phone,
			@NotEmpty(message = "createdby must not be empty.") String createdby,
			@Size(max = 1, message = "Max length for userrole can not exceed 1 characters.") @NotEmpty(message = "userrole must not be empty.") String userrole) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.username = username;
		this.phone = phone;
		this.createdby = createdby;
		this.userrole = userrole;
	}
	public SignupRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "SignupRequest [name=" + name + ", email=" + email + ", password=" + password + ", username=" + username
				+ ", phone=" + phone + ", createdby=" + createdby + ", userrole=" + userrole + "]";
	}
	
	
	 
	 
	 
	
	 
	
	
}
