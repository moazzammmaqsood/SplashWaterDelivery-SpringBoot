package com.splash.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.splash.entity.model.ErrorResponse;
import com.splash.entity.model.LoginForm;
import com.splash.entity.model.UserForm;
import com.splash.entity.mysql.UserEntity;
import com.splash.enums.ErrorEnums;
import com.splash.services.UserService;


//401 user not found
//402 user disabled
//500 Server Error

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="add-user",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> addUser(@RequestBody UserForm user,final BindingResult bindingResult){
		
		if (bindingResult.hasErrors()) {  
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(asJsonString(bindingResult.getAllErrors()));
		}
		
		
		UserEntity userEntity =new UserEntity();
		userEntity.setUsername(user.getUsername());
    	userEntity.setUserrole(user.getUserrole());
    	userEntity.setEmail(user.getEmail());
    	userEntity.setPassword(user.getPassword());
    	userEntity.setPhone(user.getPhone());
    	userEntity.setStatus(user.getStatus());
    	userEntity.setCreatedon(new Date());
    	userEntity.setCreatedby(user.getCreatedby());
    	userEntity.setName(user.getName());


		userService.adduser(userEntity);

		return ResponseEntity.status(HttpStatus.OK).build();
		
		
	}
	
	@RequestMapping(value="login-action",method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> loginAction(@RequestBody LoginForm login,final BindingResult bindingResult){
		
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
