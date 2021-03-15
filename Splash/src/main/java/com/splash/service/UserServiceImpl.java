package com.splash.service;

import com.splash.dao.UserDAO;
import com.splash.domain.entity.UserEntity;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {


    UserDAO userDAO;

    @Override
    @Transactional
    public UserEntity getUser(Integer id) {
      return null;//  userDAO.getUserbyid(id);
    }

    @Override
    @Transactional
    public UserEntity getUserbyloginid(String loginid) {
        return null;
    }

    @Override
    @Transactional
    public void addUser(UserEntity user) {
//        userDAO.adduser(user);
    }

    @Override
    @Transactional
    public void updateUser(UserEntity user) {
//        userDAO.updateuser(user);
    }

    @Override
    @Transactional
    public void DeleteUser(UserEntity user) {
//        userDAO.disableuser(user);
    }
    
    
	@Override
	@Transactional
	public UserEntity LoginUser(String username, String password) {
		return null;//  userDAO.getLoginUser(username,password);
		
	}
}
