package com.splash.repository;

import com.splash.domain.entity.SmsEntity;
import com.splash.domain.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsRepository extends JpaRepository<SmsEntity, Integer> {

    SmsEntity findByOrderid(int Orderid);

    @Query(value = "from SmsEntity where status = 'N'")
    List<SmsEntity> fetchUnSentSms();
}
