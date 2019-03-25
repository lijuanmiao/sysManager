package com.cn.sysManager.models;

import java.io.Serializable;

/**
 * Created by lijm on 2019-03-25.
 */
public class TemplateData implements Serializable{

    String value;
    String color;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
