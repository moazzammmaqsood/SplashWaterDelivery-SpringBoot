package com.splash.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.splash.domain.entity.UserEntity;

@Repository
public class UserDAOImpl implements UserDAO {
	
	
	 @PersistenceContext
	 EntityManager entityManager;

	@Override
	public void adduser(UserEntity user) {
		entityManager.persist(user);	
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

	@Override
	public UserEntity getLoginUser(String username, String password) {
		List<UserEntity> list= 		entityManager.createQuery("FROM UserEntity where username=:userid and password=:password ")
				.setParameter("userid", username)
				.setParameter("password", password).getResultList();
		
		if(list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}


	}

	@Override
	public UserEntity getuserbyUsername(String username) {
		List<UserEntity> list= 		entityManager.createQuery("FROM UserEntity where username=:userid ")
				.setParameter("userid", username)
				.getResultList();
		
		if(list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
	}

}
