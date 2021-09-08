package com.splash.repository;

import com.splash.domain.entity.SmsEntity;
import com.splash.domain.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<SmsEntity, Integer> {

    SmsEntity findByOrderid(int Orderid);


}
