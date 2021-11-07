package com.splash.entity.model;

import javax.persistence.*;

@Entity(name="SummaryMonthly")
@Table(name="orders")
@NamedNativeQuery(name="OrderEntity.getMonthlySunmary",query="SELECT sum(bottlesdelivered) as totalbottles, sum(payment) as totalpayments ,count(Distinct(clientid)) as totalactiveclients ,0 as countersale , 0 as expense ,0 as totalrevenue FROM worthywa_splash.orders where date like :date and status = 'A' and  vendorid = :vendorid",resultClass = SummaryMonthly.class)

public class SummaryMonthly {
    @Id
    @Column
    Long totalpayments;
    @Column
    int totalbottles;
    @Column
    int totalactiveclients;
    @Column
    Long countersale;
    @Column
    Long expense;
    @Column
    Long totalrevenue;

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

    public Long getTotalpayments() {
        return totalpayments;
    }

    public void setTotalpayments(Long totalpayments) {
        this.totalpayments = totalpayments;
    }

    public int getTotalbottles() {
        return totalbottles;
    }

    public void setTotalbottles(int totalbottles) {
        this.totalbottles = totalbottles;
    }

    public int getTotalactiveclients() {
        return totalactiveclients;
    }

    public void setTotalactiveclients(int totalactiveclients) {
        this.totalactiveclients = totalactiveclients;
    }

    public Long getCountersale() {
        return countersale;
    }

    public void setCountersale(Long countersale) {
        this.countersale = countersale;
    }

    public Long getExpense() {
        return expense;
    }

    public void setExpense(Long expense) {
        this.expense = expense;
    }

    public Long getTotalrevenue() {
        return totalrevenue;
    }

    public void setTotalrevenue(Long totalrevenue) {
        this.totalrevenue = totalrevenue;
    }

    public SummaryMonthly() {
    }

    public SummaryMonthly(Long totalPayments, int totalBottles, int totalActiveClients, Long counterSale, Long expense, Long totalrevenue) {
        this.totalpayments = totalPayments;
        this.totalbottles = totalBottles;
        this.totalactiveclients = totalActiveClients;
        this.countersale = counterSale;
        this.expense = expense;
        this.totalrevenue = totalrevenue;
    }
}
