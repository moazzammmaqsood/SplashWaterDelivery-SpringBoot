package com.splash.entity.model;

import com.splash.domain.entity.ClientEntity;
import com.splash.domain.entity.MonthlyBill;
import com.splash.domain.entity.VendorEntity;

import java.util.List;

public class UploadInfo {
    String fileName;
    List<MonthlyBill> monthlyBillList;
    ClientDetails clientDetails;
    VendorEntity vendorEntity;

    String monthYear;

    ClientEntity clientEntity;
    public UploadInfo() {
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public UploadInfo(String fileName, List<MonthlyBill> monthlyBillList, ClientDetails clientDetails, VendorEntity vendorEntity, String monthYear, ClientEntity clientEntity) {
        this.fileName = fileName;
        this.monthlyBillList = monthlyBillList;
        this.clientDetails = clientDetails;
        this.vendorEntity = vendorEntity;
        this.monthYear = monthYear;
        this.clientEntity = clientEntity;
    }

//    public UploadInfo(String fileName, List<MonthlyBill> monthlyBillList, ClientDetails clientDetails, VendorEntity vendorEntity, String monthYear) {
//        this.fileName = fileName;
//        this.monthlyBillList = monthlyBillList;
//        this.clientDetails = clientDetails;
//        this.vendorEntity = vendorEntity;
//        this.monthYear = monthYear;
//    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<MonthlyBill> getMonthlyBillList() {
        return monthlyBillList;
    }

    public void setMonthlyBillList(List<MonthlyBill> monthlyBillList) {
        this.monthlyBillList = monthlyBillList;
    }

    public ClientDetails getClientDetails() {
        return clientDetails;
    }

    public void setClientDetails(ClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    public VendorEntity getVendorEntity() {
        return vendorEntity;
    }

    public void setVendorEntity(VendorEntity vendorEntity) {
        this.vendorEntity = vendorEntity;
    }
}
