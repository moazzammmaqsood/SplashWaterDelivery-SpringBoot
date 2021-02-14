package com.splash.dao;


import com.splash.entity.mysql.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserDAO {
	
	void adduser(UserEntity user);
	
	UserEntity getUserbyid(int id);
	
	void disableuser(UserEntity user);
	
	void updateuser(UserEntity user);
	
	UserEntity getuser(String loginid);
	
	
	

}
