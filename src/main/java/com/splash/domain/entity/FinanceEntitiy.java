package com.splash.domain.entity;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name="finance")
public class FinanceEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    int income;

    @Column
    int expense;

    @Column
    Date date;

    @Column
    String remarks;

    @Column
    String tags;

    @Column
    String status;

    public FinanceEntitiy(int id, int income, int expense, Date date, String remarks, String tags, String status) {
        this.id = id;
        this.income = income;
        this.expense = expense;
        this.date = date;
        this.remarks = remarks;
        this.tags = tags;
        this.status = status;
    }

    public FinanceEntitiy() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
