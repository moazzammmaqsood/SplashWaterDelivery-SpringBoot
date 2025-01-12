package com.splash.utils;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @org.junit.jupiter.api.Test
    void getLastMonth() {

        Calendar c= Calendar.getInstance();
        int year;
        switch (c.get(Calendar.MONTH)){
            case 0:
                year= c.get(Calendar.YEAR)-1;
                assertEquals(year+"-Dec",Utils.getLastMonth());
                break;
            case 1:
                 year= c.get(Calendar.YEAR);
                assertEquals(year+"-Jan",Utils.getLastMonth());
        }
      }
}