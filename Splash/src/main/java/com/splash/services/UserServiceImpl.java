package com.splash.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.splash.dao.UserDAO;
import com.splash.entity.mysql.UserEntity;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Override
	@Transactional
	public void adduser(UserEntity user) {
		 userDAO.adduser(user);
		
	}

	@Override
	@Transactional
	public UserEntity getuser(int id) {
		return userDAO.getUserbyid(id);
	}

	@Override
	@Transactional
	public void edituser(UserEntity user) {
		userDAO.updateuser(user);
	}

	@Override
	@Transactional
	public void disableuser(int id) {
		UserEntity user=userDAO.getUserbyid(id);
		
		 userDAO.disableuser(user);
	}

}
