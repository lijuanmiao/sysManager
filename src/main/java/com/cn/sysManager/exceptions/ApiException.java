package com.cn.sysManager.exceptions;

/**
 * Created by lijm on 2018-03-26.
 */
public class ApiException extends Exception{

    private String resCode;

    public ApiException(String code, String msg) {
        super(msg);
        this.resCode = code;
    }
}
