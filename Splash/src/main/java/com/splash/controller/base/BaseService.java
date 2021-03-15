package com.splash.controller.base;

import org.springframework.security.core.Authentication;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.splash.domain.entity.User;
import com.splash.repository.UserRepository;

public abstract class BaseService {

	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@Autowired
	UserRepository userRepository;
	
	
	protected User getCurrentUser() {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		String username= authentication.getName();
		return (User) userDetailsService.loadUserByUsername(username);
	}
	
	
	
}
