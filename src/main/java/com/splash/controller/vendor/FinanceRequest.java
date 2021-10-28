package com.splash.controller.vendor;

public class FinanceRequest
{
    int amount;
    String remark;
    String date;
    String type;

    public FinanceRequest(int amount, String remark, String date, String type) {
        this.amount = amount;
        this.remark = remark;
        this.date = date;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
