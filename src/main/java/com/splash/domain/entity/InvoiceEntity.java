package com.splash.domain.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "invoice")
@NoArgsConstructor
@ToString
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Long id;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="clientid" ,referencedColumnName = "clientid")
    private ClientEntity client;


    @Column(name = "yearmonth")
    private String yearMonth;

    @Column(name="url")
    private String url;

    @Column(name="filename")
    private String fileName;

    @Column(name="status")
    private Boolean status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public InvoiceEntity(Long id, ClientEntity client, String yearMonth, String url, String fileName, Boolean status) {
        this.id = id;
        this.client = client;
        this.yearMonth = yearMonth;
        this.url = url;
        this.fileName = fileName;
        this.status = status;
    }
}
