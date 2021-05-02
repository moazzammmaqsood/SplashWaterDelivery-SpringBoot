package com.splash.repository;
import com.splash.domain.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity,Integer> {

	VendorEntity findByVendorid(int vendorid); 
	VendorEntity findByUserid(int Userid); 
	
}
