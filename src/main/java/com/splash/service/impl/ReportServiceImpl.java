package com.splash.service.impl;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.contentSources.B2ByteArrayContentSource;
import com.backblaze.b2.client.contentSources.B2ContentSource;
import com.backblaze.b2.client.contentSources.B2ContentTypes;
import com.backblaze.b2.client.structures.B2FileVersion;
import com.backblaze.b2.client.structures.B2GetUploadUrlRequest;
import com.backblaze.b2.client.structures.B2UploadFileRequest;
import com.backblaze.b2.client.structures.B2UploadListener;
import com.backblaze.b2.util.B2ExecutorUtils;
import com.splash.domain.entity.*;
import com.splash.entity.model.ClientDetails;
import com.splash.entity.model.UploadInfo;
import com.splash.repository.ClientRepository;
import com.splash.repository.InvoiceRepository;
import com.splash.repository.OrderRepository;
import com.splash.repository.SmsRepository;
import com.splash.service.ReportService;
import com.splash.utils.Constants;
import com.splash.utils.Utils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.splash.utils.Constants.bucketId;

@Service
public class ReportServiceImpl implements ReportService {

    Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SmsRepository smsRepository;

    @Override
    public List<MonthlyBill> getMonthlyBill(int clientId,String monthYear) {
        return orderRepository.getMonthlyBill(clientId,monthYear);
    }

    @Autowired
    B2StorageClient client;


    @Autowired
    InvoiceRepository invoiceRepository;


    final B2UploadListener uploadListener = (progress) -> {
        final double percent = (100. * (progress.getBytesSoFar() / (double) progress.getLength()));
        logger.debug(String.format("  progress(%3.2f, %s)", percent, progress.toString()));
    };
    @Override
    public String uploadToB2(ClientEntity clientEntity,String clientName, String vendorName, String monthYear , byte[] bytes) {

       InvoiceEntity invoice= invoiceRepository.getLastMonthInvoice(clientEntity.getClientid(),Utils.getLastMonth());
       if(invoice!=null){
           return invoice.getUrl();
       }
        String url=null;
        try{
            final B2ContentSource source = B2ByteArrayContentSource.build(bytes);
            final String fileName = Utils.getFileName(clientName,clientEntity.getClientid(),vendorName,monthYear);
            B2UploadFileRequest request = B2UploadFileRequest
                    .builder(bucketId, fileName, B2ContentTypes.B2_AUTO, source)
                    .setListener(uploadListener)
                    .build();

            B2FileVersion file2 = client.uploadSmallFile(request);
            url="https://" +Constants.bucketName+"."+Constants.bucketEndpoint+"/"+fileName;
            logger.info("File status: "+ file2);
            InvoiceEntity invoiceEntity=new InvoiceEntity();
            invoiceEntity.setFileName(fileName);
            invoiceEntity.setUrl(url);
            invoiceEntity.setYearMonth(monthYear);
            invoiceEntity.setClient(clientEntity);
            invoiceRepository.save(invoiceEntity);

        }catch (Exception e){
            logger.info(e.getMessage() +" : "+e.getLocalizedMessage());
        }
        return url;
    }

    void sendInvoiceSms(ClientEntity clientEntity, String url){
        SmsEntity smsEntity=new SmsEntity();
        smsEntity.setPhoneno(clientEntity.getUser().getPhone());
        smsEntity.setStatus("N");
        smsEntity.setSenttime(new Date());
        smsEntity.setSmstext("Your Bill for March can be found on this url : "+url);
        smsRepository.save(smsEntity);

    }
//    @Override
//    public void addInUploadQueue(ClientEntity clientEntity,ClientDetails client, VendorEntity vendor, List<MonthlyBill> list ,String yearMonth) {
//        UploadInfo uploadInfo=new UploadInfo(null,list,client,vendor,yearMonth);
//        Constants.UploadQueue.add(uploadInfo);
//    }

    @Override
    public UploadInfo getUploadEntity(ClientEntity clientEntity,ClientDetails client, VendorEntity vendor, List<MonthlyBill> list ,String yearMonth) {
        UploadInfo uploadInfo=new UploadInfo(null,list,client,vendor,yearMonth,clientEntity);
        return uploadInfo;
    }
    @Override
    public String generatePdf(UploadInfo uploadInfo) {
        Map<String ,Object> map =new HashMap<>();
        map.put("clientId",uploadInfo.getClientDetails().getClientid());
        map.put("vendorName",uploadInfo.getVendorEntity().getName());
        map.put("totalBill",uploadInfo.getClientDetails().getPaymentremaining());
        map.put("address",uploadInfo.getVendorEntity().getAddress());
        map.put("clientName",uploadInfo.getClientDetails().getName());
        map.put("yearMonth",Utils.getLastMonth());
//        ClientEntity clientEntity=clientRepository.findById(uploadInfo.getClientDetails().getClientid()).get();
        String url = null;
        try {
            JRBeanCollectionDataSource dataSource = new
                    JRBeanCollectionDataSource(uploadInfo.getMonthlyBillList());
            JasperPrint empReport =
                    JasperFillManager.fillReport
                            (
                                    JasperCompileManager.compileReport(
                                            ResourceUtils.getFile("classpath:splashInvoicenew.jrxml")
                                                    .getAbsolutePath()) // path of the jasper report
                                    , map // dynamic parameters
                                    , dataSource
                            );
            byte[] bytes = JasperExportManager.exportReportToPdf(empReport);
            url= uploadToB2(uploadInfo.getClientEntity(),uploadInfo.getClientDetails().getName(), uploadInfo.getVendorEntity().getName(),uploadInfo.getMonthYear() , bytes);

        }catch (Exception e){
            logger.error(e.getMessage()+" "+e.getLocalizedMessage());
        }
        return url;
    }

    @Override
    synchronized public void processPdfQueue() {
        while (!Constants.UploadQueue.isEmpty()){
            UploadInfo uploadInfo=Constants.UploadQueue.poll();
            generatePdf(uploadInfo);
        }
    }


}
