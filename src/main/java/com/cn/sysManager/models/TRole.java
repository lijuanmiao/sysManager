package com.cn.sysManager.models;

import org.joda.time.DateTime;

/**
 * Created by lijm on 2018-09-17.
 */
public class TRole {

    private  Long id;
    private String name;
    private DateTime  lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getLastName() {
        return lastName;
    }

    public void setLastName(DateTime lastName) {
        this.lastName = lastName;
    }
}