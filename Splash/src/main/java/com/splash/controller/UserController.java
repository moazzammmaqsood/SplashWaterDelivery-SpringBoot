package com.splash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.splash.entity.mysql.UserEntity;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	
	
	@RequestMapping(value="add-user",method=RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody UserEntity user){
		addUser(user);
		return ResponseEntity.status(HttpStatus.OK).build();
		
		
	}
	
	
	
	
	
}
