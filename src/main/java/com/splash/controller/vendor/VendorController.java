package com.splash.controller.vendor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.splash.common.BasicAction;
import com.splash.common.ParameterizedAction;
import com.splash.controller.base.BaseController;
import com.splash.service.VendorService;
import com.splash.domain.SuccessResponse;
import com.splash.domain.entity.*;
import com.splash.entity.model.ClientDetails;
@RestController
public class VendorController extends BaseController  {


		@Autowired
		VendorService vendorservice;
		
		

	    @PostMapping(
	            value = "/api/v1/private/vendor/add_client",
	            produces = MediaType.APPLICATION_JSON_VALUE,
	            consumes = MediaType.APPLICATION_JSON_VALUE
	    )
	    public ResponseEntity<?> v1addclient(@Valid @RequestBody ClientRequest client,BindingResult bindingResult) {

	   	 
			 if(bindingResult.hasErrors()) {
				 System.out.println(bindingResult.getAllErrors());
			 }
			 
	 
			 
	        ParameterizedAction<ClientRequest, ResponseEntity<?>> v1addclient = (request) -> {
	        	vendorservice.addUser(request);
	            return ResponseEntity.ok(new SuccessResponse("Successfull added Client"));
	        };

	        return execute(client,v1addclient);
	    }
	

	    @GetMapping(
	            value = "/api/v1/private/vendor/getclients",
	            produces = MediaType.APPLICATION_JSON_VALUE,
	            consumes = MediaType.APPLICATION_JSON_VALUE
	    )
	    public ResponseEntity<?> v1getclients() {

	    	
	    	BasicAction< ResponseEntity<?>> v1getclients = () -> {
	    		List<UserClient> list= vendorservice.getClientsbyvendor();
//	    		System.out.println(list);
	    		return ResponseEntity.ok(list);
	        };

	        return execute(v1getclients);
	    }

	    
	    
	    @PostMapping(
	            value = "/api/v1/private/vendor/update_client",
	            produces = MediaType.APPLICATION_JSON_VALUE,
	            consumes = MediaType.APPLICATION_JSON_VALUE
	    )
	    public ResponseEntity<?> v1updateclient(@Valid @RequestBody ClientUpdateRequest client,BindingResult bindingResult) {

	   	 
			 if(bindingResult.hasErrors()) {
				 System.out.println(bindingResult.getAllErrors());
			 }
			 
			 System.out.println("Check");
			 
	        ParameterizedAction<ClientUpdateRequest, ResponseEntity<?>> v1update = (request) -> {
	        	vendorservice.updateUser(request);
	            return ResponseEntity.ok("SUCCESS");
	        };

	        return execute(client,v1update);
	    }
	
	    
	    @PostMapping(
	            value = "/api/v1/private/vendor/deliver",
	            produces = MediaType.APPLICATION_JSON_VALUE,
	            consumes = MediaType.APPLICATION_JSON_VALUE
	    )
	    public ResponseEntity<?> v1deliverclient(@Valid @RequestBody BottleDelivered delivery,BindingResult bindingResult) {

	   	 
			 if(bindingResult.hasErrors()) {
				 System.out.println(bindingResult.getAllErrors());
			 }
			 
			
			 
	        ParameterizedAction<BottleDelivered, ResponseEntity<?>> v1deliverclient = (request) -> {
	        	vendorservice.DeliverBottle(request);
	            return ResponseEntity.ok(new SuccessResponse("Bottle Sucessfully Delivered"));
	        };

	        return execute(delivery,v1deliverclient);
	    }

	    
	    @GetMapping(
	            value = "/api/v1/private/vendor/getdelivery",
	            produces = MediaType.APPLICATION_JSON_VALUE,
	            consumes = MediaType.APPLICATION_JSON_VALUE
	    )
	    public ResponseEntity<?> v1getdeliveries() {

	    	
	    	BasicAction< ResponseEntity<?>> v1getdeliveries = () -> {
	    		List<ClientDelivery> list= vendorservice.getDeliveries();
 
	    		return ResponseEntity.ok(list);
	        };

	        return execute(v1getdeliveries);
	    }
	    
	    
	    @GetMapping(
	            value = "/api/v1/private/vendor/getclient/{clientid}/{userid}",
	            produces = MediaType.APPLICATION_JSON_VALUE,
	            consumes = MediaType.APPLICATION_JSON_VALUE
	    )
	    public ResponseEntity<?> v1getclientbyid(@PathVariable(name="clientid") int clientid,@PathVariable(name="userid") int userid ) {

	    	
	    	BasicAction< ResponseEntity<?>> v1getdeliveries = () -> {
	    		ClientDetails client= vendorservice.getclient(clientid,userid);
 
	    		return ResponseEntity.ok(client);
	        };

	        return execute(v1getdeliveries);
	    }


}
