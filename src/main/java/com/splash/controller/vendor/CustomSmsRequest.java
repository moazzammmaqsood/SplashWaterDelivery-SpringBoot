package com.splash.controller.vendor;

public class CustomSmsRequest {

    String sms;

    public CustomSmsRequest(String sms) {
        this.sms = sms;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public CustomSmsRequest() {
    }

    @Override
    public String toString() {
        return "CustomSmsRequest{" +
                "sms='" + sms + '\'' +
                '}';
    }
}


