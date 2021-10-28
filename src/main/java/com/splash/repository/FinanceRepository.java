package com.splash.repository;

import com.splash.domain.entity.FinanceEntitiy;
import com.splash.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceRepository
        extends JpaRepository<FinanceEntitiy, Integer> {
}
