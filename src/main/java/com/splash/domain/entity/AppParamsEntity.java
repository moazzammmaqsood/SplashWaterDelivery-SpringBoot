package com.splash.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="appparams")
public class AppParamsEntity {

    @Id
    @Column
    String key;

    @Column
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public AppParamsEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public AppParamsEntity() {
    }




}

