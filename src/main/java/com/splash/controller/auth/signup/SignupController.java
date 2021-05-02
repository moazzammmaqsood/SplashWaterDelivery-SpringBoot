package com.splash.controller.auth.signup;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.splash.controller.base.BaseController;

import com.splash.common.ParameterizedAction;

import com.splash.service.SignupService;

@RestController
public class SignupController extends BaseController {

	@Autowired
	private SignupService signupService; 
	
	 @PostMapping(
	            value = "/api/v1/public/auth/signup",
	            produces = MediaType.APPLICATION_JSON_VALUE,
	            consumes = MediaType.APPLICATION_JSON_VALUE
	    )
	    public ResponseEntity<?> v1Signup(@Valid @RequestBody SignupRequest signupRequest,BindingResult bindingResult) {

		 
		 if(bindingResult.hasErrors()) {
			 System.out.println(bindingResult.getAllErrors());
		 }
		 	
	    	
	        ParameterizedAction<SignupRequest, ResponseEntity<?>> v1SignupInternal = (request) -> {
	            String token = signupService.signup(request);
	            return ResponseEntity.ok(new SignupResponse(token));
	        };

	        return execute(signupRequest, v1SignupInternal);
	    }	
	
	
}
