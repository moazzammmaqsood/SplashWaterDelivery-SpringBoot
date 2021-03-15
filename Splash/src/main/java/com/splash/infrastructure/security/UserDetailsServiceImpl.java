package com.splash.infrastructure.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.splash.domain.entity.User;
import com.splash.domain.entity.UserEntity;
import com.splash.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        
    	Optional<UserEntity> useropt= userRepository.findByusername(s);
    	
    	if(!useropt.isPresent()) {
    		throw new UsernameNotFoundException("User not found");
    	}
    	
    	User user =new User(useropt.get());
    	
    	
    	return user;
    }
}
