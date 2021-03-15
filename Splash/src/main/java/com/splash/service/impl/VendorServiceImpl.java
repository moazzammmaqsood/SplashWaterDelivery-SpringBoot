package com.splash.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splash.controller.base.BaseService;
import com.splash.controller.vendor.ClientRequest;
import com.splash.domain.ApiException;
import com.splash.domain.constants.ApiStatusCodes;
import com.splash.domain.constants.ErrorMessages;
import com.splash.domain.entity.ClientEntity;
import com.splash.domain.entity.User;
import com.splash.domain.entity.UserEntity;
import com.splash.repository.ClientRepository;
import com.splash.repository.UserRepository;
import com.splash.service.VendorService;

@Service
public class VendorServiceImpl extends BaseService implements VendorService  {

	@Autowired
	UserRepository userrepo;

	@Autowired
	ClientRepository clientrepo;

	
	@Override
	public List<ClientEntity> getClients() {

//		userrepo.findByusername(username);
		User user = getCurrentUser();
		System.out.println(user);
		
	Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername()); 
	if(!optionaluser.isPresent())
	{
		throw new ApiException(ApiStatusCodes.NOT_FOUND, ErrorMessages.USERNAME_NOT_FOUND);
	}
	
	List<UserEntity> users=userrepo.findAllByCreatedby(optionaluser.get().getUsername());
		
	List<ClientEntity> list=new ArrayList<ClientEntity>();
	if(user!=null) {
		for (UserEntity userEntity : users) {
			Optional<ClientEntity> client= clientrepo.findByuserid(userEntity.getUserid());
			if(!client.isPresent()) break;
			list.add( client.get()) ;		

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
	public String addUser(ClientRequest request) {
		
		UserEntity user =new UserEntity();
		return null;
			
	}

}
