package com.splash.repository;

import com.splash.domain.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity,Long> {


    @Query(value = "select * from invoice where clientid = :clientId and yearmonth = :yearMonth and status = :status limit 1",nativeQuery = true)
    InvoiceEntity getLastMonthInvoice(@Param("clientId") int clientid , @Param("yearMonth") String yearMonth, @Param("status") Boolean status);


}
