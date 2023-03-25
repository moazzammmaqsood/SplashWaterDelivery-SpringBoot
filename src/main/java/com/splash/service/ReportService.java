package com.splash.service;

import com.splash.domain.entity.MonthlyBill;

import java.util.List;

public interface ReportService {
    List<MonthlyBill> getMonthlyBill(int clientId);
}
