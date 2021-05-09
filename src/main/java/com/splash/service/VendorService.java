package com.splash.service;

import java.util.List;

import com.splash.controller.vendor.BottleDelivered;
import com.splash.controller.vendor.ClientRequest;
import com.splash.controller.vendor.ClientUpdateRequest;
import com.splash.controller.vendor.GetClientsResponse;
import com.splash.controller.vendor.UserClient;
import com.splash.domain.entity.ClientDelivery;
import com.splash.domain.entity.ClientEntity;
import com.splash.entity.model.ClientDetails;

public interface VendorService {
	
	List<GetClientsResponse> getClients();

	String getuser();

	void addUser(ClientRequest request);

	void updateUser(ClientUpdateRequest request); 
	
	void DeliverBottle(BottleDelivered request);

	List<ClientDelivery> getDeliveries();

	ClientDetails getclient(int clientid, int userid);
	
	List<UserClient> getClientsbyvendor( );

}


