package com.cn.sysManager.models;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lijm on 2019-03-25.
 */
public class WxTemplate implements Serializable {

    String template_id;

    String touser;

    String form_id;//请自行添加上set,get方法

    String page;

    Map<String,TemplateData> data;

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Map<String, TemplateData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateData> data) {
        this.data = data;
    }
}
