package com.cn.sysManager.models;

import java.io.Serializable;

/**
 * Created by lijm on 2019-03-25.
 */
public class MessageRes implements Serializable{

    Integer errcode;

    String errmsg;

    String template_id;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }
}
