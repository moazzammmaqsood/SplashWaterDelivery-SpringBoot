package com.splash.repository;

import com.splash.domain.entity.AppParamsEntity;
import com.splash.domain.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppParamRepository extends JpaRepository<AppParamsEntity, String> {

    @Override
    Optional<AppParamsEntity> findById(String s);
}
