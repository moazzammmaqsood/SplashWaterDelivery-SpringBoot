package com.splash.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String Name; 
    private String username;
    @JsonIgnore
    private String password;
    private boolean isActive;

    public User() {
    }
    
    public User(UserEntity userentity) {
    	Name=userentity.getName();
    	username=userentity.getUsername();
    	password=userentity.getPassword();
    	if(userentity.getStatus().equals("E")) {
    		isActive=true;
    	}else {
    		isActive=false;
    	}
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
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
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", Name=" + Name + ", username=" + username + ", password=" + password
				+ ", isActive=" + isActive + "]";
	}
    
    
}
