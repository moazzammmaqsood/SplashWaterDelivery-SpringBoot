package com.splash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.splash.domain.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	 Optional<UserEntity> findByemail(String email);

	Optional<UserEntity> findByusername(String username);
	
	List<UserEntity> findAllByCreatedby(String createdby);
}
