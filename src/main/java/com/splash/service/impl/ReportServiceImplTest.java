package com.splash.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportServiceImplTest {

    ReportServiceImpl reportService =new ReportServiceImpl();
    @Test
    void getLastDateOfMonth() {

        String date = reportService.getLastDateOfMonth("2024-Sept");
        assertEquals(date,"2024-09-30");

    }
}