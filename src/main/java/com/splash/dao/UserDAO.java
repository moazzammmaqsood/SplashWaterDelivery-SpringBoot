package com.splash.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.splash.domain.entity.UserEntity;


public interface UserDAO {
	
	void adduser(UserEntity user);
	
	UserEntity getUserbyid(int id);
	
	void disableuser(UserEntity user);
	
	void updateuser(UserEntity user);
	
	UserEntity getuser(String loginid);

	UserEntity getLoginUser(String username, String password);
	
	UserEntity getuserbyUsername(String username);
	
	

}
