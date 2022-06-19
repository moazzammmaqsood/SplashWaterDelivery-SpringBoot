package com.splash.entity.model;

import javax.persistence.*;

@Entity(name="SummaryMonthly")
@Table(name="orders")
@NamedNativeQuery(name="OrderEntity.getMonthlySunmary",query="select sum(totalbottles) as totalbottles, sum(totalpayments) as totalpayments , sum(totalactiveclients) as totalactiveclients ,sum(countersale) as countersale , sum(expense) as expense ,sum(totalrevenue) as totalrevenue from ( SELECT sum(bottlesdelivered) as totalbottles, sum(payment) as totalpayments , count(Distinct(o.clientid)) as totalactiveclients ,0 as countersale , 0 as expense , 0 as totalrevenue FROM worthywa_splash.orders o inner join worthywa_splash.client c on o.clientid = c.clientid where o.date like :date and o.status = 'A' and c.clienttype is null and  o.vendorid = :vendorid  union all select 0 as totalbottles,  0 as totalpayments , 0 as totalactiveclients ,sum(f.income) as countersale , sum(f.expense) as expense , (sum(f.income)-sum(f.expense) )as totalrevenue from worthywa_splash.finance f where f.date like :date and f.status = 'A' and  f.vendorid = :vendorid  )foo ",resultClass = SummaryMonthly.class)

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
