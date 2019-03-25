package com.cn.sysManager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijm on 2018-11-08.
 */
@Service
public class TemplateMessageService implements ITemplateMessageService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

   // @Autowired
    //private WxMpService wxMpService;

    @Override
    public void sendUnpaidBillMsg(String openId, String billType, String needPay, String day, String billStatus,String url) {
        try{

           /* WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();

            templateMessage.setToUser(openId);
            templateMessage.setTemplateId("k3tZs-qhTUA0uApiGg6gXoK9t3fXRbLoGdlatJYY1Jg");
            templateMessage.setUrl(url);

            List<WxMpTemplateData> wxMpTemplateDataList = new ArrayList<>();
            WxMpTemplateData wxMpTemplateData1 = new WxMpTemplateData();
            wxMpTemplateData1.setName("keyword1");
            wxMpTemplateData1.setValue(billType);

            WxMpTemplateData wxMpTemplateData2 = new WxMpTemplateData();
            wxMpTemplateData2.setName("keyword2");
            wxMpTemplateData2.setValue(needPay);

            WxMpTemplateData wxMpTemplateData3 = new WxMpTemplateData();
            wxMpTemplateData3.setName("keyword3");
            wxMpTemplateData3.setValue(day);

            WxMpTemplateData wxMpTemplateData4 = new WxMpTemplateData();
            wxMpTemplateData4.setName("keyword4");
            wxMpTemplateData4.setValue(billStatus);

            wxMpTemplateDataList.add(wxMpTemplateData1);
            wxMpTemplateDataList.add(wxMpTemplateData2);
            wxMpTemplateDataList.add(wxMpTemplateData3);
            wxMpTemplateDataList.add(wxMpTemplateData4);

            templateMessage.setDatas(wxMpTemplateDataList);
            wxMpService.templateSend(templateMessage);*/
//            println "===========================1==2==============="
        }catch (Exception e){
            logger.error("向用户发送未缴费账单异常:",e);
        }
    }
}
