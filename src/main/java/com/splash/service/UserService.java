package com.splash.service;


import com.splash.domain.entity.UserEntity;

public interface UserService {


   public UserEntity getUser(Integer id);

    public UserEntity getUserbyloginid(String loginid);

    public void addUser(UserEntity user);

    public void updateUser(UserEntity user);

    public  void DeleteUser(UserEntity user);


	UserEntity LoginUser(String username, String password);

	public UserEntity getUserbytoken();

}
