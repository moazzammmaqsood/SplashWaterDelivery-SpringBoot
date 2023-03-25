package com.splash.service.impl;

import com.splash.domain.entity.MonthlyBill;
import com.splash.repository.OrderRepository;
import com.splash.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

   @Autowired
    OrderRepository orderRepository;

    @Override
    public List<MonthlyBill> getMonthlyBill(int clientId) {
        return orderRepository.getMonthlyBill(clientId);
    }
}
