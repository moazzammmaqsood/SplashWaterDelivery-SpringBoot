package com.splash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.splash.controller.vendor.UserClient;
import com.splash.domain.entity.ClientEntity;
import com.splash.domain.entity.UserEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
	
	Optional<ClientEntity> findByuserid(int Userid);
	
	Optional<List<ClientEntity> >findAllByvendorid(int vendorid);
	
	
	List<UserClient> getClientList(int vendorid);
	
		

}