package com.splash.service.impl;

import com.splash.controller.auth.login.LoginRequest;
import com.splash.controller.auth.login.LoginResponse;
import com.splash.domain.ApiException;
import com.splash.domain.constants.ApiStatusCodes;
import com.splash.domain.constants.ErrorMessages;
import com.splash.domain.entity.UserEntity;
import com.splash.infrastructure.security.UserDetailsServiceImpl;
import com.splash.infrastructure.security.jwt.JwtUtils;
import com.splash.service.LoginService;
import com.splash.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired 
    UserService userservice;
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    public LoginResponse login(LoginRequest login) {
        try {
        	 if(login==null) {
             	throw new ApiException(ApiStatusCodes.BAD_REQUEST, ErrorMessages.BAD_CREDENTIALS);
             }
            authManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(),
                    login.getPassword(), Collections.emptyList()));

           
            UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());
             
            UserEntity user = userservice.getUserbyloginid(login.getUsername());
            if(user==null) {
            	throw new ApiException(ApiStatusCodes.NOT_FOUND, ErrorMessages.USERNAME_NOT_FOUND);
            }
            LoginResponse loginresponse=new LoginResponse(jwtUtils.generateToken(userDetails),user);
            return loginresponse;
        } catch (AccountStatusException e){
            throw new ApiException(ApiStatusCodes.UNAUTHORIZED, ErrorMessages.ACCOUNT_LOCKED);
        } catch (AuthenticationException e){
            throw new ApiException(ApiStatusCodes.UNAUTHORIZED, ErrorMessages.BAD_CREDENTIALS);
        }
    }
}
