package com.splash.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.splash.controller.base.BaseService;
import com.splash.controller.vendor.BottleDelivered;
import com.splash.controller.vendor.ClientDeliveryRequest;
import com.splash.controller.vendor.ClientRequest;
import com.splash.controller.vendor.ClientUpdateRequest;
import com.splash.controller.vendor.GetClientsResponse;
import com.splash.controller.vendor.UserClient;
import com.splash.domain.ApiException;
import com.splash.domain.constants.ApiStatusCodes;
import com.splash.domain.constants.ErrorMessages;
import com.splash.domain.entity.ClientDelivery;
import com.splash.domain.entity.ClientEntity;
import com.splash.domain.entity.ClientTotalDetail;
import com.splash.domain.entity.OrderEntity;
import com.splash.domain.entity.User;
import com.splash.domain.entity.UserEntity;
import com.splash.domain.entity.VendorEntity;
import com.splash.entity.model.ClientDetails;
import com.splash.repository.ClientRepository;
import com.splash.repository.OrderRepository;
import com.splash.repository.UserRepository;
import com.splash.repository.VendorRepository;
import com.splash.service.VendorService;
import com.splash.utils.Utils;

@Service
public class VendorServiceImpl extends BaseService implements VendorService  {

	@Autowired
	UserRepository userrepo;

	@Autowired
	ClientRepository clientrepo;
	
	@Autowired
	OrderRepository orderrepo;

	@Autowired
	VendorRepository vendorrepo; 
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Override
	public List<GetClientsResponse> getClients() {


	User user = getCurrentUser();
	
	Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername()); 
	if(!optionaluser.isPresent())
	{
		throw new ApiException(ApiStatusCodes.INTERNAL_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
	}
	
	List<UserEntity> users=userrepo.findAllByCreatedby(optionaluser.get().getUsername());
		
	List<GetClientsResponse> list=new ArrayList<GetClientsResponse>();
	if(user!=null) {
		for (UserEntity userEntity : users) {
			
			Optional<ClientEntity> client= clientrepo.findByuserid(userEntity.getUserid());
			
			if(!client.isPresent()) break;
			OrderEntity order = orderrepo.getClientlastDelivery(client.get().getClientid());
			GetClientsResponse clientres=new GetClientsResponse(client.get().getAddress(),userEntity.getName(),userEntity.getPhone(),client.get().getClientid(),client.get().getRate(),client.get().getFrequency(),client.get().getBottles(),order.getDate());
					list.add( clientres) ;		

			System.out.println(client.get());
		}
	}
	
	return list;
		
		
		
		
	}

	
	

	@Override
	public String getuser() {
		User user = getCurrentUser();
		return user.getUsername() ;
	}


	@Override
	public void addUser(ClientRequest request) {
		
 
		User user = getCurrentUser();
		 
		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername()); 
		if(!optionaluser.isPresent())
		{ 
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}
 
		VendorEntity vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		
		 
		if(vendor==null) { 
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.VENDOR_NOT_FOUND);
		}
 
		int nextval=userrepo.findlastuserid() + 1 ;
		 
		String Clientusername=generateusername(vendor.getShortcode(),nextval);
 
		UserEntity newuser =new UserEntity(request.getEmail(),passwordEncoder.encode("splash123123"),request.getName(),request.getContactno(),Clientusername,"C",optionaluser.get().getUsername());
		
		 
		newuser.setCreatedon(new Date());
		newuser.setStatus("E");
		userrepo.save(newuser);
		 
		ClientEntity client=new ClientEntity(newuser.getUserid(), request.getAddress(), request.getRate(), vendor.getVendorid(), request.getFrequency(), request.getNoofbottles(),request.getDeposit());
		clientrepo.save(client);
	 

		if(request.getLastdelivery()!=null && !request.getLastdelivery().isEmpty()) { 
			OrderEntity order=new OrderEntity();
			order.setBottlesdelivered(request.getLastbottles());
			order.setBottlesrecieved(request.getLastrecieved());
			order.setPayment(request.getLastpayment());
			order.setClientid(client.getClientid());
			order.setVendorid(vendor.getVendorid());
	 
			try {
				order.setDate(Utils.StringtoDate(request.getLastdelivery()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
		 
				throw new ApiException(ApiStatusCodes.SERVER_ERROR, e.getLocalizedMessage());
			}
			
			orderrepo.save(order);
	 
		}
			
	}
	
	public String generateusername(String shortcode,int nextval) {
		
		String username= "";
		String usernum= String.valueOf(nextval);
		username=shortcode+StringUtils.leftPad(usernum, 5,"0" );
		
		
		return username;
	}
	
	
	@Override
	public void updateUser(ClientUpdateRequest request) {
	
		User sessionuser= getCurrentUser();
		
		if(request==null) {
			throw new ApiException(ApiStatusCodes.BAD_REQUEST,ErrorMessages.NULL_REQUEST);
		}
		
	 Optional<UserEntity>  user =  userrepo.findByusername(request.getUsername());
		
	 if(!user.isPresent()) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
	 }
	 
	 
	 if(!user.get().getCreatedby().equals(sessionuser.getUsername())) {
			throw new ApiException(ApiStatusCodes.UNAUTHORIZED,ErrorMessages.AUTHENTICATION_FAILED);
	 }
	 
	 Optional<ClientEntity> client= clientrepo.findByuserid(user.get().getUserid());

	 if(!client.isPresent()) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.CLIENT_NOT_FOUND);
	 }
		
	 
	 ClientEntity Clientupdate= client.get();
	 if(Clientupdate==null) {
		 throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.CLIENT_NOT_FOUND);
	 }
	 Clientupdate.setAddress(request.getAddress());
	 Clientupdate.setBottles(request.getNoofbottles());
	 Clientupdate.setFrequency(request.getDaysdelivery());
	 Clientupdate.setRate(request.getRate());
	 Clientupdate.setDeposit(request.getDeposit());
	 
	 clientrepo.save(Clientupdate);
	 
	 UserEntity Userupdate =user.get();
	 if(Userupdate==null) {
		 throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
	 }
	 
	 Userupdate.setPhone(request.getContactno());
	 System.out.println(Userupdate);
	 userrepo.save(Userupdate);
	 
	 
	};
	
	
	
	
	public void DeliverBottle(BottleDelivered request) {
		
		
		OrderEntity order= new OrderEntity();
		
		User user =getCurrentUser();
		
		Optional<UserEntity> vendoruser= userrepo.findByusername(user.getUsername());
		
		if(!vendoruser.isPresent()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USERNAME_NOT_FOUND);	
		}
		
		VendorEntity vendor= vendorrepo.findByUserid(vendoruser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}	
		
		ClientEntity client= clientrepo.getOne(request.getClientid());
		if(client==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}
	
		
		UserEntity clientuser= userrepo.getOne(client.getUserid());
		
		if(clientuser==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}
		
		if( client.getVendorid()!= vendor.getVendorid()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.UNAUTHORIZED_USER_TYPE);
		}
		
		order.setClientid(client.getClientid());
		order.setVendorid(vendor.getVendorid());
		order.setBottlesdelivered(request.getBottlesdel());
		order.setBottlesrecieved(request.getBottlesrec());
		order.setPayment(request.getBottlesdel());
		order.setDate(new Date());
		
		orderrepo.save(order);
		
	}


	@Override
	public List<ClientDelivery> getDeliveries() {

		User user =getCurrentUser();
		
		Optional<UserEntity> vendoruser= userrepo.findByusername(user.getUsername());
		
		if(!vendoruser.isPresent()) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);	
		}
		
		VendorEntity vendor= vendorrepo.findByUserid(vendoruser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}
		
		List<ClientDelivery> clientdel=orderrepo.getDailydelivery(vendor.getVendorid());
		if(clientdel==null || clientdel.isEmpty()) {
			return null;
		}
		 
		List<ClientDelivery> resultlist=new ArrayList<>();
		for (ClientDelivery clientDelivery : clientdel) {
			System.out.println(clientDelivery);
			if(clientDelivery.bottlefinished()) {
				resultlist.add(clientDelivery);
			}
		}
		
		return resultlist;
	}


	@Override
	public ClientDetails getclient(int clientid, int userid) {

		User user =getCurrentUser();
		
		Optional<UserEntity> vendoruser= userrepo.findByusername(user.getUsername());
		
		if(!vendoruser.isPresent()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}
		
		VendorEntity  vendor = vendorrepo.findByUserid(vendoruser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.VENDOR_NOT_FOUND);
		}
	
		
		Optional<ClientEntity> clientent=clientrepo.findById(clientid);
		if(!clientent.isPresent()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.CLIENT_NOT_FOUND);
		}
		
		if(clientent.get().getUserid()!=userid) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.UNAUTHORIZED_USER_TYPE);
		}
		
		if(clientent.get().getVendorid()!=vendor.getVendorid()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.UNAUTHORIZED_USER_TYPE);
		}
		
		Optional<UserEntity> userent =userrepo.findById(userid);
		if(!userent.isPresent()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USER_NOT_FOUND);
		}
		
		ClientTotalDetail clienttotal = orderrepo.getClientTotalDetail(clientid);
		
		ClientDetails clientdetails;
		int bottlesholding =0;
		int payment=0;
		int paymentrecieved=0;
		if(clienttotal!= null) {
		 bottlesholding =  clienttotal.getTotalbottles()-clienttotal.getTotalrecieved();
		 payment= clienttotal.getTotalbottles()*clientent.get().getRate()-clienttotal.getTotalpayment();
		 paymentrecieved=clienttotal.getTotalpayment();
		
		
//		System.out.println(clienttotal);
			if(clienttotal.getDate()!=null) {
				 clientdetails= new ClientDetails(userid, clientid, userent.get().getName(), userent.get().getPhone(),clientent.get().getAddress(), clienttotal.getTotalbottles() , bottlesholding, clientent.get().getRate(), Utils.Datetostring(clienttotal.getDate()),clientent.get().getFrequency(),payment,paymentrecieved);	
			}else {
				 clientdetails= new ClientDetails(userid, clientid, userent.get().getName(), userent.get().getPhone(),clientent.get().getAddress(), clienttotal.getTotalbottles(),bottlesholding, clientent.get().getRate(), " ",clientent.get().getFrequency(),payment,paymentrecieved);
			}
		 
		} else {
			 clientdetails= new ClientDetails(userid, clientid, userent.get().getName(), userent.get().getPhone(),clientent.get().getAddress(),0,bottlesholding, clientent.get().getRate(), " ",clientent.get().getFrequency(),payment,paymentrecieved);
		}
		return clientdetails;
	}




	@Override
	public List<UserClient> getClientsbyvendor() {
		User user = getCurrentUser(); 
		
		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername()); 
		
		if(!optionaluser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}
		
		VendorEntity  vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.VENDOR_NOT_FOUND);
		}
		
		
		Optional<List<ClientEntity>> clients=clientrepo.findAllByvendorid(vendor.getVendorid());
	List<UserClient> userclient=new ArrayList<UserClient>();
		
		if(clients.isPresent()) {
			for (ClientEntity clientdetails : clients.get()) {
				if (clientdetails.getUser()!=null) {
					userclient.add(new UserClient(clientdetails.getUserid(), clientdetails.getClientid(), clientdetails.getUser().getName(), clientdetails.getAddress(), clientdetails.getBottles()));
							
				}
					
			} 
		}
		
		
		
		return userclient;
	}



 	
	
	
	

}
