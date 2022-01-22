package com.splash.service.impl;

import com.splash.controller.auth.login.LoginRequest;
import com.splash.controller.auth.login.LoginResponse;
import com.splash.domain.ApiException;
import com.splash.domain.constants.ApiStatusCodes;
import com.splash.domain.constants.ErrorMessages;
import com.splash.domain.entity.ClientEntity;
import com.splash.domain.entity.UserEntity;
import com.splash.domain.entity.VendorEntity;
import com.splash.infrastructure.security.UserDetailsServiceImpl;
import com.splash.infrastructure.security.jwt.JwtUtils;
import com.splash.repository.ClientRepository;
import com.splash.repository.VendorRepository;
import com.splash.service.LoginService;
import com.splash.service.UserService;
import com.splash.service.VendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

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

    @Autowired
    ClientRepository clientRepository;
     
    @Autowired
    VendorRepository vendorRepository;
    
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
            
            if(user.getUserrole().equals("C")) {
            	
                Optional<ClientEntity> client =clientRepository.findByuserid(user.getUserid());
                
                if(!client.isPresent()) {
                	throw new ApiException(ApiStatusCodes.INTERNAL_ERROR, ErrorMessages.CLIENT_NOT_FOUND);
                	
                }
 

           	 VendorEntity  vendor= vendorRepository.findByVendorid(client.get().getVendorid());
           	 
           	 if(vendor ==null) {
           		throw new ApiException(ApiStatusCodes.INTERNAL_ERROR, ErrorMessages.VENDOR_NOT_FOUND);
           	 }
           	 loginresponse.setVendorname(vendor.getName());
           	
               
            }else if(user.getUserrole().equals("V")) {
            	
            	 VendorEntity  vendor= vendorRepository.findByUserid(user.getUserid()); 
            	 
            	 if(vendor== null) {
            		 throw new ApiException(ApiStatusCodes.INTERNAL_ERROR, ErrorMessages.VENDOR_NOT_FOUND);
            	 }
            	 
            	 loginresponse.setVendorname(vendor.getName());
            }else {
            	
            	loginresponse.setVendorname("Admin");
            }

            return loginresponse;
        } catch (AccountStatusException e){
            throw new ApiException(ApiStatusCodes.UNAUTHORIZED, ErrorMessages.ACCOUNT_LOCKED);
        } catch (AuthenticationException e){
            throw new ApiException(ApiStatusCodes.UNAUTHORIZED, ErrorMessages.BAD_CREDENTIALS);
        }
    }
}
