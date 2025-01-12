package com.splash.controller;

import com.splash.common.BasicAction;
import com.splash.controller.base.BaseController;
import com.splash.domain.SuccessResponse;
import com.splash.domain.entity.*;
import com.splash.entity.model.ClientDetails;
import com.splash.entity.model.UploadInfo;
import com.splash.repository.ClientRepository;
import com.splash.repository.InvoiceRepository;
import com.splash.service.ReportService;
import com.splash.service.VendorService;
import com.splash.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ReportController extends BaseController {


    @Autowired
    VendorService vendorService;

    @Autowired
    ReportService reportService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    InvoiceRepository invoiceRepository;
    static Logger logger= LoggerFactory.getLogger(ReportController.class);


    @GetMapping(
            value = "/api/v1/private/re_generate_pdf/{clientid}/{userid}/{monthYear}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> v1regeneratetBillV1(@PathVariable("clientid") int clientid, @PathVariable(name = "userid") int userid,@PathVariable(name="monthYear") String monthYear) {
        BasicAction<ResponseEntity<?>> v1regetBillV1 = () -> {
            String finalMonthYear =  monthYear;
            if(finalMonthYear.contains("Sept"))
                finalMonthYear = finalMonthYear.replace("Sept","Sep");
            InvoiceEntity invoiceEntity=invoiceRepository.getLastMonthInvoice(clientid,finalMonthYear,true);
            ClientDetails details = vendorService.getclient(clientid,userid);
            VendorEntity vendor=vendorService.getClientVendor();
            ClientEntity clientEntity=clientRepository.findById(details.getClientid()).get();
            Utils.validateMonthYear(finalMonthYear);
            List<MonthlyBill> monthlyBillList = reportService.getMonthlyBill(clientid,finalMonthYear);
            logger.info("Monthly Bill fetched order size={}",monthlyBillList.size());
            if(monthlyBillList.size()==0){
                monthlyBillList=new ArrayList<>();
                MonthlyBill monthlyBill=new MonthlyBill("Zero Bottles",0,0,0);
                monthlyBillList.add(monthlyBill) ;
            }

            UploadInfo uploadInfo=reportService.getUploadEntity(clientEntity,details,vendor,monthlyBillList,finalMonthYear);
            String invoiceUrl=reportService.generatePdf(uploadInfo);
            PdfUrl pdfUrl=new PdfUrl();
            pdfUrl.setUrl(invoiceUrl);
            invoiceEntity.setStatus(false);
            invoiceRepository.save(invoiceEntity);
            return ResponseEntity.ok(pdfUrl);

        };

        return execute(v1regetBillV1);
    }


    @GetMapping(
            value = "/api/v1/private/pdf/generate_pdf/{clientid}/{userid}/{monthYear}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> v1getBillV1(@PathVariable("clientid") int clientid, @PathVariable(name = "userid") int userid,@PathVariable(name="monthYear") String monthYear) {
        BasicAction<ResponseEntity<?>> v1getBill = () -> {
            String finalMonthYear = monthYear;
            if(finalMonthYear.contains("Sept"))
                finalMonthYear = finalMonthYear.replace("Sept","Sep");
            InvoiceEntity invoiceEntity=invoiceRepository.getLastMonthInvoice(clientid,finalMonthYear,true);
            if(invoiceEntity!=null){
                logger.info("Invoice Entity fetched invoice Entity"+invoiceEntity.getUrl());
                return ResponseEntity.ok(invoiceEntity.getUrl());
            }
            logger.info("Generating new Invoice");

            ClientDetails details = vendorService.getclient(clientid,userid);
            VendorEntity vendor=vendorService.getClientVendor();
            ClientEntity clientEntity=clientRepository.findById(details.getClientid()).get();
            Utils.validateMonthYear(finalMonthYear);
            List<MonthlyBill> monthlyBillList = reportService.getMonthlyBill(clientid,finalMonthYear);
            logger.info("Monthly Bill fetched order size={}",monthlyBillList.size());

            if(monthlyBillList.size()==0){
                monthlyBillList=new ArrayList<>();
                MonthlyBill monthlyBill=new MonthlyBill("Zero Bottles",0,0,0);
                monthlyBillList.add(monthlyBill) ;
            }

            UploadInfo uploadInfo=reportService.getUploadEntity(clientEntity,details,vendor,monthlyBillList,finalMonthYear);
            String invoiceUrl=reportService.generatePdf(uploadInfo);
            PdfUrl pdfUrl=new PdfUrl();
            pdfUrl.setUrl(invoiceUrl);
            return ResponseEntity.ok(pdfUrl);

        };

        return execute(v1getBill);
    }

}
