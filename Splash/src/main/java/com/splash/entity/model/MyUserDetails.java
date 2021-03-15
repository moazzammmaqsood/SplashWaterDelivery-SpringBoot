package com.splash.entity.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.splash.domain.entity.UserEntity;

public class MyUserDetails implements UserDetails{


	String username;
	String password;
	List<GrantedAuthority> authorities;
	boolean active;
	

	

	public MyUserDetails(UserEntity user) {
		authorities= new ArrayList<GrantedAuthority>();
		this.username = user.getUsername();
		this.password = user.getPassword();
		if(user.getStatus().equals("E")) {
			active=true;
			
		}else {
			active =false;
		}
		
		if(user.getUserrole().equals("V")) {
			this.authorities.add(new SimpleGrantedAuthority("VENDOR"));	
		}else if(user.getUserrole().equals("C")) {
			this.authorities.add(new SimpleGrantedAuthority("CLIENT"));
			
		}else if(user.getUserrole().equals("A")) {System.out.println("check admin");
//			this.authorities.add(new SimpleGrantedAuthority("ADMIN"));
			this.authorities.add(new SimpleGrantedAuthority("CLIENT"));
//			System.out.println(authorities.get(0).getAuthority());
		}
		 
	
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return active;
	}

}
