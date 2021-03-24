

package com.splash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.splash.domain.entity.ClientEntity;
import com.splash.domain.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

	
	@Query(nativeQuery = true, value = "SELECT * FROM orders WHERE clientid = :clientid ORDER BY date  DESC LIMIT 1")
	OrderEntity getClientlastDelivery(int clientid); 
	
	
}
