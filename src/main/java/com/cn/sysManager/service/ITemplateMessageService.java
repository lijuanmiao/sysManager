package com.cn.sysManager.service;

/**
 * Created by lijm on 2018-11-08.
 */
public interface ITemplateMessageService {

    void sendUnpaidBillMsg(String openId,String billType,String needPay,String day,String billStatus,String url);

}
