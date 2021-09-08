package com.splash.domain.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="sms")
public class SmsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    int id ;
    @Column
    int userid;
    @Column
    String phoneno;
    @Column
    String smstext;
    @Column
    String status;
    @Column
    Date senttime;
    @Column
    String response;
    @Column
    int orderid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getSmstext() {
        return smstext;
    }

    public void setSmstext(String smstext) {
        this.smstext = smstext;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSenttime() {
        return senttime;
    }

    public void setSenttime(Date senttime) {
        this.senttime = senttime;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public SmsEntity() {
    }


    public SmsEntity(int id, int userid, String phoneno, String smstext, String status, Date senttime, String response, int orderid) {
        this.id = id;
        this.userid = userid;
        this.phoneno = phoneno;
        this.smstext = smstext;
        this.status = status;
        this.senttime = senttime;
        this.response = response;
        this.orderid = orderid;
    }
}
