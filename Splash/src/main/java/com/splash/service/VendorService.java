package com.splash.service;

import java.util.List;

import com.splash.controller.vendor.ClientRequest;
import com.splash.domain.entity.ClientEntity;

public interface VendorService {
	
	List<ClientEntity> getClients();

	String getuser();

	String addUser(ClientRequest request); 

}

