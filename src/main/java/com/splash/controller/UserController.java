package com.splash.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.splash.domain.entity.UserEntity;
import com.splash.entity.model.ErrorResponse;
import com.splash.entity.model.LoginForm;
import com.splash.enums.ErrorEnums;
import com.splash.enums.Status;
import com.splash.service.UserService;


@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	


    @Autowired
    UserService userService;

    @RequestMapping(value="/signup",method = RequestMethod.POST)
    public Status signup(@Valid @RequestBody UserEntity newUser){


        return Status.SUCCESS;
    }

    @RequestMapping(value="/get-user/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> signup(@Valid @PathVariable ("id") int id){

    	if(userService.getUser(id)==null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
    	}
    	
        return ResponseEntity.ok(userService.getUser(id));

    }
	
	@RequestMapping(value="login-action",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> loginAction(@RequestBody LoginForm login,BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {  
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(asJsonString(bindingResult.getAllErrors()));
		}
		UserEntity userEntity =userService.LoginUser(login.getUsername(), login.getPassword());

		if(userEntity==null) {
			ErrorResponse error=new ErrorResponse(ErrorEnums.USER_NOT_FOUND.getResponseCode(),ErrorEnums.USER_NOT_FOUND.getError(),ErrorEnums.USER_NOT_FOUND.getErrorDescription());
			return ResponseEntity.status(HttpStatus.OK).body(asJsonString(error));
	
		}
		
		if(userEntity.getStatus().equals("D")) {
			ErrorResponse error=new ErrorResponse(ErrorEnums.USER_DISABLED.getResponseCode(),ErrorEnums.USER_DISABLED.getError(),ErrorEnums.USER_DISABLED.getErrorDescription());
			return ResponseEntity.status(HttpStatus.OK).body(asJsonString(error));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(asJsonString(userEntity));
		
		
	}
	

	

    
    static String  asJsonString(final Object obj) {
    	try {
    		return new ObjectMapper().writeValueAsString(obj);
    	}catch (Exception exp) {
    		throw new RuntimeException(exp);
    	}
    }
	
	




}
