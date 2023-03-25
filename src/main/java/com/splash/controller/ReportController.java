package com.splash.controller;

import com.splash.common.BasicAction;
import com.splash.controller.base.BaseController;
import com.splash.domain.entity.MonthlyBill;
import com.splash.domain.entity.VendorEntity;
import com.splash.entity.model.ClientDetails;
import com.splash.service.ReportService;
import com.splash.service.VendorService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(
            value = "/api/v1/private/pdf/generate_pdf/{clientid}/{userid}/{monthYear}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> v1getBillV1(@PathVariable("clientid") int clientid, @PathVariable(name = "userid") int userid,@PathVariable(name="monthYear") String monthYear) {

        BasicAction<ResponseEntity<?>> v1getBill = () -> {
            ClientDetails details = vendorService.getclient(clientid,userid);
            VendorEntity vendor=vendorService.getClientVendor();
            List<MonthlyBill> monthlyBillList = reportService.getMonthlyBill(clientid);
            details.getAddress();
            details.getPaid();
            Map<String ,Object> map =new HashMap<>();
            map.put("clientId",clientid);
            map.put("vendorName",vendor.getName());
            map.put("totalBill",details.getPaymentremaining());
            map.put("address",vendor.getAddress());
            try {
                JRBeanCollectionDataSource dataSource = new
                        JRBeanCollectionDataSource(monthlyBillList);
                JasperPrint empReport =
                        JasperFillManager.fillReport
                                (
                                        JasperCompileManager.compileReport(
                                                ResourceUtils.getFile("classpath:splashInvoice.jrxml")
                                                        .getAbsolutePath()) // path of the jasper report
                                        , map // dynamic parameters
                                        , dataSource
                                );
                HttpHeaders headers = new HttpHeaders();
                //set the PDF format
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("filename", "invoice.pdf");
                System.out.println("check test");
                return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);
            } catch (JRException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        };

        return execute(v1getBill);
    }



}
