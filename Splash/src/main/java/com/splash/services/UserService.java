package com.splash.services;


import com.splash.entity.mysql.UserEntity;

 
public interface UserService {

	void adduser(UserEntity user);
	
	UserEntity getuser(int id);
	
	void edituser(UserEntity user);
	
	void disableuser(int id);
}
