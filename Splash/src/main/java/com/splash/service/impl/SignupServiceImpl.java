package com.splash.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.splash.controller.auth.signup.SignupRequest;
import com.splash.domain.ApiException;
import com.splash.domain.constants.ApiStatusCodes;
import com.splash.domain.constants.ErrorMessages;
import com.splash.domain.entity.UserEntity;
import com.splash.infrastructure.security.jwt.JwtUtils;
import com.splash.repository.UserRepository;
import com.splash.service.SignupService;


@Service
public class SignupServiceImpl implements SignupService{

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsService userDetailsService;
	
    @Autowired
    JwtUtils jwtUtils;
    
    
	@Override
	public String signup(SignupRequest request) {

		UserEntity user=new UserEntity(request.getEmail(),passwordEncoder.encode(request.getPassword()),request.getName(),request.getPhone(),request.getUsername(),request.getUserrole(),request.getCreatedby());
		user.setStatus("E");
		user.setCreatedon(new Date());
		
		
		Optional<UserEntity> username= userrepo.findByusername(user.getUsername());
		Optional<UserEntity> useremail= userrepo.findByemail(user.getEmail());
		if(username.isPresent()) {
			throw new ApiException(ApiStatusCodes.DUPLICATE_USERNAME, ErrorMessages.DUPLICATE_USERNAME);
		}
		if(useremail.isPresent()) {
			throw new ApiException(ApiStatusCodes.DUPLICATE_EMAIL, ErrorMessages.DUPLICATE_EMAIL_ADDRESS);
		}
		
		try {
			userrepo.save(user);
			UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
	        return jwtUtils.generateToken(userDetails);
		}catch (AccountStatusException e){
            throw new ApiException(ApiStatusCodes.UNAUTHORIZED, ErrorMessages.ACCOUNT_LOCKED);
        } catch (AuthenticationException e){
            throw new ApiException(ApiStatusCodes.UNAUTHORIZED, ErrorMessages.BAD_CREDENTIALS);
        }

	}

}
