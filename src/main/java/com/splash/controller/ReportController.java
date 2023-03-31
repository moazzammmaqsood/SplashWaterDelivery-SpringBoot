package com.splash.controller;

import com.splash.common.BasicAction;
import com.splash.controller.base.BaseController;
import com.splash.domain.SuccessResponse;
import com.splash.domain.entity.ClientEntity;
import com.splash.domain.entity.MonthlyBill;
import com.splash.domain.entity.PdfUrl;
import com.splash.domain.entity.VendorEntity;
import com.splash.entity.model.ClientDetails;
import com.splash.entity.model.UploadInfo;
import com.splash.repository.ClientRepository;
import com.splash.service.ReportService;
import com.splash.service.VendorService;
import com.splash.utils.Utils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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

    @GetMapping(
            value = "/api/v1/private/pdf/generate_pdf/{clientid}/{userid}/{monthYear}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> v1getBillV1(@PathVariable("clientid") int clientid, @PathVariable(name = "userid") int userid,@PathVariable(name="monthYear") String monthYear) {
        BasicAction<ResponseEntity<?>> v1getBill = () -> {
            ClientDetails details = vendorService.getclient(clientid,userid);
            VendorEntity vendor=vendorService.getClientVendor();
            ClientEntity clientEntity=clientRepository.findById(details.getClientid()).get();
            Utils.validateMonthYear(monthYear);
            List<MonthlyBill> monthlyBillList = reportService.getMonthlyBill(clientid,monthYear);
            reportService.processPdfQueue();
            UploadInfo uploadInfo=reportService.getUploadEntity(clientEntity,details,vendor,monthlyBillList,Utils.getLastMonth());
            String invoiceUrl=reportService.generatePdf(uploadInfo);
            PdfUrl pdfUrl=new PdfUrl();
            pdfUrl.setUrl(invoiceUrl);
            return ResponseEntity.ok(pdfUrl);

        };

        return execute(v1getBill);
    }



}
