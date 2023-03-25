package com.splash.domain.entity;


import com.splash.entity.model.SummaryDaily;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="orders")
@NamedNativeQuery(name= "OrderEntity.getMonthlyBill" ,query = "select date_format(date,\"%d-%M-%Y\") date,bottlesdelivered,rate,payment from orders  where  clientId =  ?1 " +
        " and date  between" +
        " (last_day(current_date() - interval 2 MONTH)+ interval 1 day) " +
        " AND CURRENT_DATE() and status='A'",resultClass = MonthlyBill.class  )

//@NoArgsConstructor
public class MonthlyBill {
    @Id
    @Column(name="date")
    String date;

    @Column(name="bottlesdelivered")
    int bottlesdelivered;

    @Column(name="rate")
    int rate;

    @Column(name="payment")
    int payment;

    public MonthlyBill() {
    }

    public MonthlyBill(String date, int bottlesdelivered, int rate, int payment) {
        this.date = date;
        this.bottlesdelivered = bottlesdelivered;
        this.rate = rate;
        this.payment = payment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBottlesdelivered() {
        return bottlesdelivered;
    }

    public void setBottlesdelivered(int bottlesdelivered) {
        this.bottlesdelivered = bottlesdelivered;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "MonthlyBill{" +
                "date='" + date + '\'' +
                ", bottlesdelivered=" + bottlesdelivered +
                ", rate=" + rate +
                ", payment=" + payment +
                '}';
    }
}
