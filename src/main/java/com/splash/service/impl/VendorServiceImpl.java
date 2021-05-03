package com.splash.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.splash.controller.base.BaseService;
import com.splash.controller.vendor.BottleDelivered;
import com.splash.controller.vendor.ClientDeliveryRequest;
import com.splash.controller.vendor.ClientRequest;
import com.splash.controller.vendor.ClientUpdateRequest;
import com.splash.controller.vendor.GetClientsResponse;
import com.splash.domain.ApiException;
import com.splash.domain.constants.ApiStatusCodes;
import com.splash.domain.constants.ErrorMessages;
import com.splash.domain.entity.ClientEntity;
import com.splash.domain.entity.OrderEntity;
import com.splash.domain.entity.User;
import com.splash.domain.entity.UserEntity;
import com.splash.domain.entity.VendorEntity;
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
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}

		VendorEntity vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		

		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR, ErrorMessages.VENDOR_NOT_FOUND);
		}
		String Clientusername=generateusername(vendor.getShortcode());
		
		UserEntity newuser =new UserEntity(request.getEmail(),passwordEncoder.encode("splash123123"),request.getName(),request.getContactno(),Clientusername,"C",optionaluser.get().getUsername());
		
		newuser.setCreatedon(new Date());
		newuser.setStatus("E");
		userrepo.save(newuser);
		
		ClientEntity client=new ClientEntity(newuser.getUserid(), request.getAddress(), request.getRate(), vendor.getVendorid(), request.getFrequency(), request.getNoofbottles(),request.getDeposit());
		clientrepo.save(client);
	
			
	}
	
	public String generateusername(String shortcode) {
		
		String username= "";
		boolean usernamegenerated=false;
		while(!usernamegenerated) {
			username=shortcode+ Utils.generateRandomnumber(4);
		if(!userrepo.findByusername(username).isPresent()) {
			usernamegenerated=true;
		}
		
		}
		
		
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
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);	
		}
		
		VendorEntity vendor= vendorrepo.findByUserid(vendoruser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}	
		
		ClientEntity client= clientrepo.getOne(request.getId());
		if(client==null) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}
	
		
		UserEntity clientuser= userrepo.getOne(client.getUserid());
		
		if(clientuser==null) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}
		
		if(!clientuser.getCreatedby().equals(vendoruser.get().getUsername())) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.UNAUTHORIZED_USER_TYPE);
		}
		
		order.setClientid(client.getClientid());
		order.setVendorid(vendor.getVendorid());
		order.setBottlesdelivered(request.getBottlesdel());
		order.setBottlesrecieved(request.getBottlesrec());
		order.setPayment(request.getBottlesdel());
		order.setDate(new Date());
		
		orderrepo.save(order);
		
	}



 	
	
	
	

}