package com.splash.entity.model;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetialCredentials implements UserDetails {

	String username;
	String password;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	return Arrays.asList(new SimpleGrantedAuthority("USERS"));
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	public UserDetialCredentials(String username) {
 
		this.username = username;
	}
	
	public UserDetialCredentials() {
			
	}
	


	
	
	
	
	
}
