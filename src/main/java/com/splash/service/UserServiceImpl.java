package com.splash.service;

import com.splash.controller.base.BaseService;
import com.splash.dao.UserDAO;
import com.splash.domain.ApiException;
import com.splash.domain.constants.ApiStatusCodes;
import com.splash.domain.constants.ErrorMessages;
import com.splash.domain.entity.User;
import com.splash.domain.entity.UserEntity;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl extends BaseService implements UserService {


	@Autowired
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

	@Override
	public UserEntity getUserbytoken() {
		// TODO Auto-generated method stub
		User user = getCurrentUser();
		
		UserEntity userentity= userDAO.getuserbyUsername(user.getUsername());
		if(userentity==null)	throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		
		return userentity;
	}
}
