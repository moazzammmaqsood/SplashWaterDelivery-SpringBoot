package com.splash.entity.model;

import javax.persistence.*;

@Entity(name="SummaryMonthly")
@Table(name="orders")
@NamedNativeQuery(name="OrderEntity.getMonthlySunmary",query="SELECT sum(bottlesdelivered) as totalbottles, sum(payment) as totalpayments ,count(Distinct(clientid)) as totalactiveclients ,0 as countersale , 0 as expense ,0 as totalrevenue FROM worthywa_splash.orders where date like :date and status = 'A' and  vendorid = :vendorid",resultClass = SummaryMonthly.class)

public class SummaryMonthly {
    @Id
    @Column
    Integer totalbottles;

    @Column
    Integer totalpayments;
    @Column
    Integer totalactiveclients;
    @Column
    Integer countersale;
    @Column
    Integer expense;
    @Column
    Integer totalrevenue;

    @Override
    public String toString() {
        return "SummaryMonthly{" +
                "totalPayments=" + totalpayments +
                ", totalBottles=" + totalbottles +
                ", totalActiveClients=" + totalactiveclients +
                ", counterSale=" + countersale +
                ", expense=" + expense +
                ", totalrevenue=" + totalrevenue +
                '}';
    }

    public Integer getTotalpayments() {
        return totalpayments;
    }

    public void setTotalpayments(Integer totalpayments) {
        this.totalpayments = totalpayments;
    }

    public Integer getTotalbottles() {
        return totalbottles;
    }

    public void setTotalbottles(Integer totalbottles) {
        this.totalbottles = totalbottles;
    }

    public Integer getTotalactiveclients() {
        return totalactiveclients;
    }

    public void setTotalactiveclients(Integer totalactiveclients) {
        this.totalactiveclients = totalactiveclients;
    }

    public Integer getCountersale() {
        return countersale;
    }

    public void setCountersale(Integer countersale) {
        this.countersale = countersale;
    }

    public Integer getExpense() {
        return expense;
    }

    public void setExpense(Integer expense) {
        this.expense = expense;
    }

    public Integer getTotalrevenue() {
        return totalrevenue;
    }

    public void setTotalrevenue(Integer totalrevenue) {
        this.totalrevenue = totalrevenue;
    }

    public SummaryMonthly() {
    }

    public SummaryMonthly(Integer totalPayments, int totalBottles, int totalActiveClients, Integer counterSale, Integer expense, Integer totalrevenue) {
        this.totalpayments = totalPayments;
        this.totalbottles = totalBottles;
        this.totalactiveclients = totalActiveClients;
        this.countersale = counterSale;
        this.expense = expense;
        this.totalrevenue = totalrevenue;
    }
}
