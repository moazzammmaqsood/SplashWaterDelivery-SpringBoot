package com.splash.service;

import java.util.Date;
import java.util.List;

import com.splash.controller.vendor.BottleDelivered;
import com.splash.controller.vendor.ClientRequest;
import com.splash.controller.vendor.ClientUpdateRequest;
import com.splash.controller.vendor.EditClientRequest;
import com.splash.controller.vendor.GetClientsResponse;
import com.splash.controller.vendor.UserClient;
import com.splash.domain.entity.*;
import com.splash.entity.model.ClientDetails;
import com.splash.entity.model.SummaryDaily;
import com.splash.entity.model.SummaryDelivery;

public interface VendorService {
	
	List<GetClientsResponse> getClients();

	String getuser();

	void addUser(ClientRequest request);

	void updateUser(ClientUpdateRequest request); 
	
	void DeliverBottle(BottleDelivered request);

	List<ClientDelivery> getDeliveries();

	ClientDetails getclient(int clientid, int userid);
	
	List<UserClient> getClientsbyvendor( );

	List<OrderEntity> getClientOrders(int clientid);

	void deleteClientOrder(int orderid);

	void editUser(EditClientRequest request);

	VendorEntity getVendor(int id);

	List<SummaryDelivery> getVendorDailySummaryDeliveries(String date);
	
	SummaryDaily getVendorSummarybyDate(String date);

	 void senddeliverysms(SmsEntity entity , String name, String Vendorname , int noofbottles,int noofbottlerec,int payment, Date date,int remainingpayment,int orderid);

	 void deletesms(int orderid);

	 void disableclient(int clientid);

	void enableclient(int clientid);
}




