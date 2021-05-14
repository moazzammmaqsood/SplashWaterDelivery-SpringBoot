

package com.splash.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.splash.domain.entity.ClientDelivery;
import com.splash.domain.entity.ClientEntity;
import com.splash.domain.entity.ClientTotalDetail;
import com.splash.domain.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

	
	@Query(nativeQuery = true, value = "SELECT * FROM orders WHERE clientid = :clientid ORDER BY date  DESC LIMIT 1")
	OrderEntity getClientlastDelivery(int clientid); 
	
//	@Query(nativeQuery=true, value ="select NEW ClientDelivery (o.orderid,  c.clientid,c.userid,u.name,c.address,c.bottles,c.frequency,c.rate,DATEDIFF(CURDATE(),o.date ) AS days ) from worthywa_splash.client c inner join  worthywa_splash.orders o  on c.clientid =o.clientid "
//			+ "	Inner join worthywa_splash.users u on c.userid = u.userid "
//			+ "  where o.date = (select max(date) from  worthywa_splash.orders where clientid=o.clientid) "
//			+ "	 and o.vendorid= ?1")
	List<ClientDelivery> getDailydelivery(int vendorid);
	
	
//	@Query(nativeQuery = true, value = "SELECT clientid ,sum(bottlesdelivered) as totalbottles ,sum(bottlesrecieved) as totalrecieved ,sum(payment) as totalpayment FROM worthywa_splash.orders where clientid = ?1 group by clientid")
	ClientTotalDetail getClientTotalDetail(int clientid);
	
	
	Optional<List<OrderEntity>>  findAllByclientid(int clientid);
}
