package com.splash.service;

import java.util.List;

import com.splash.controller.vendor.BottleDelivered;
import com.splash.controller.vendor.ClientRequest;
import com.splash.controller.vendor.ClientUpdateRequest;
import com.splash.controller.vendor.GetClientsResponse;
import com.splash.domain.entity.ClientDelivery;

public interface VendorService {
	
	List<GetClientsResponse> getClients();

	String getuser();

	void addUser(ClientRequest request);

	void updateUser(ClientUpdateRequest request); 
	
	void DeliverBottle(BottleDelivered request);

	List<ClientDelivery> getDeliveries();

}

