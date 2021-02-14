package com.splash.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mysql.cj.Query;
import com.splash.entity.mysql.UserEntity;

@Repository
public class UserDAOImpl implements UserDAO {
	
	
	 @PersistenceContext
	 EntityManager entityManager;

	@Override
	public void adduser(UserEntity user) {
		
	}

	@Override
	public UserEntity getUserbyid(int id) {
	UserEntity user = 		(UserEntity) entityManager.createQuery("FROM UserEntity where userid=:userid ").setParameter("userid", id).getSingleResult();
		
		
		return user;
		
	}

	@Override
	public void disableuser(UserEntity user) {
		entityManager.persist(user);
	}

	@Override
	public void updateuser(UserEntity user) {
		entityManager.merge(user);
	}

	@Override
	public UserEntity getuser(String loginid) {
		UserEntity user = 		(UserEntity) entityManager.createQuery("FROM UserEntity where userid=:userid ").setParameter("username", loginid).getSingleResult();
		
		
		return user;
	}

}
