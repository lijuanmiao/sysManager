package com.cn.sysManager.service;

/**
 * Created by lijm on 2019-03-22.
 */
public interface ISendMailService {

    void sendSimpleEmail(String to);//简单的发送内容

    void sendMessageAndFileEmail(String to,String orderNum)throws Exception;//发送带附件[适合附件在本地服务器上]

    void sendMessageAndHtmlEmail(String to,String orderNum)throws Exception;//发送附件直接预览[适合附件在本地服务器上]

    void sendMessageUrlEmail(String to,String orderNum)throws Exception; //根据url地址发送邮件-[对附件在哪里无要求]
}
