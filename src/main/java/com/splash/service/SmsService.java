package com.splash.service;

import com.splash.domain.entity.ClientEntity;

public interface SmsService {
    void createSms(String smsText, String phoneNo, ClientEntity clientEntity);
}
