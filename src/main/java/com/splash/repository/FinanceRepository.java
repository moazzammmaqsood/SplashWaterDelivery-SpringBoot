package com.splash.repository;

import com.splash.domain.entity.FinanceEntitiy;
import com.splash.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinanceRepository
        extends JpaRepository<FinanceEntitiy, Integer> {


    @Query(nativeQuery = true,value = "SELECT * FROM worthywa_splash.finance where date like :date and status = 'A' and  vendorid = :vendorid ")
    List<FinanceEntitiy> getList(String date,int vendorid);
}
