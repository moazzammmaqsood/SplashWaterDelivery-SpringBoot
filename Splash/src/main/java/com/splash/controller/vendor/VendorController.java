package com.splash.controller.vendor;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.splash.domain.entity.ClientEntity;
import com.splash.common.BasicAction;
import com.splash.common.ParameterizedAction;
import com.splash.controller.auth.login.LoginRequest;
import com.splash.controller.auth.login.LoginResponse;
import com.splash.controller.base.BaseController;
import com.splash.service.LoginService;
import com.splash.service.VendorService;

@RestController
public class VendorController extends BaseController  {


		@Autowired
		VendorService vendorservice;
		
		

//	    @PostMapping(
//	            value = "/api/v1/public/vendor/add_client",
//	            produces = MediaType.APPLICATION_JSON_VALUE,
//	            consumes = MediaType.APPLICATION_JSON_VALUE
//	    )
//	    public ResponseEntity<?> v1Login(@Valid @RequestBody ClientRequest client) {
//
//	    	
//	        ParameterizedAction<ClientRequest, ResponseEntity<?>> v1LoginInternal = (request) -> {
//	            String token = vendorservice.addUser(request);
//	            return ResponseEntity.ok(new LoginResponse(token));
//	        };
//
//	        return execute(ClientRequest, v1LoginInternal);
//	    }
	

	    @GetMapping(
	            value = "/api/v1/private/vendor/getclients",
	            produces = MediaType.APPLICATION_JSON_VALUE,
	            consumes = MediaType.APPLICATION_JSON_VALUE
	    )
	    public ResponseEntity<?> v1getclients() {

	    	
	    	BasicAction< ResponseEntity<?>> v1getclients = () -> {
	    		List<ClientEntity> list= vendorservice.getClients();
//	    		System.out.println(list);
	    		return ResponseEntity.ok(list);
	        };

	        return execute(v1getclients);
	    }

	    
}
