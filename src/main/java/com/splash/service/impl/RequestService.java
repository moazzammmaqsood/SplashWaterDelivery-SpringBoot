package com.splash.service.impl;

import com.splash.domain.entity.RequestEntity;

import java.util.Date;
import java.util.List;

public interface RequestService {

    RequestEntity addRequest();
    RequestEntity upsertRequest();

    List<RequestEntity> getAllRequestByDate(Date date);

    void updateRequestStatus();



}
