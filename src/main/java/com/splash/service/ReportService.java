package com.splash.service;

import com.splash.domain.entity.ClientEntity;
import com.splash.domain.entity.MonthlyBill;
import com.splash.domain.entity.VendorEntity;
import com.splash.entity.model.ClientDetails;
import com.splash.entity.model.UploadInfo;

import java.util.List;

public interface ReportService {
    List<MonthlyBill> getMonthlyBill(int clientId,String monthYear);

    String uploadToB2(ClientEntity client,String clientName, String vendorName, String monthYear , byte[] bytes);

//    void addInUploadQueue(ClientDetails client, VendorEntity vendor,List<MonthlyBill> list,String yearMonth);

    String generatePdf(UploadInfo uploadInfo);

    void processPdfQueue();
     UploadInfo getUploadEntity(ClientEntity clientEntity,ClientDetails client, VendorEntity vendor, List<MonthlyBill> list ,String yearMonth);
}
