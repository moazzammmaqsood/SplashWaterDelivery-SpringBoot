package com.splash.repository;

import java.util.List;
import java.util.Optional;

import com.splash.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.splash.controller.vendor.UserClient;
import com.splash.domain.entity.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
	
	Optional<ClientEntity> findByuser(UserEntity user);
	
	Optional<List<ClientEntity> >findAllByvendorid(int vendorid);
	
	
//	List<UserClient> getClientList(int vendorid);
	
		List<UserClient> getbyClientsbyvendor(int vendorid);

}