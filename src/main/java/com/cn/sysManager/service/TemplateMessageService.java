package com.cn.sysManager.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.sysManager.models.MessageRes;
import com.cn.sysManager.models.TemplateData;
import com.cn.sysManager.models.WxTemplate;
import com.cn.sysManager.toolbox.utils.HttpClientUtil;
import com.cn.sysManager.toolbox.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijm on 2018-11-08.
 */
@Service
public class TemplateMessageService implements ITemplateMessageService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    static final String SEND_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";

    @Override
    public void sendMsg() {
        try{

            String appId = "appId";
            String appSecret = "appSecret";
            //授权（必填）
            String url = "https://api.weixin.qq.com/cgi-bin/token?appid="+appId+"&secret="+appSecret+"&grant_type=client_credential";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            logger.info("responseEntity授权获取accessToken:"+responseEntity);
            if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK){
                //代表授权成功
                String sessionData = responseEntity.getBody();
                logger.info("sessionData = "+ sessionData);
                JSONObject jsonObj = JSON.parseObject(sessionData);
                String accessToken = jsonObj.getString("access_token");

                //发送模板消息 到小程序进行数据封装
                WxTemplate template = new WxTemplate();
                template.setTouser("");//发送人
                template.setForm_id("");
                template.setPage("");
                template.setTemplate_id (""); //订单支付成功

                Map<String,TemplateData> data = new HashMap<String,TemplateData>();

                TemplateData  keyword1 = new TemplateData();
                keyword1.setValue("");

                TemplateData  keyword2 = new TemplateData();
                keyword2.setValue("");

                TemplateData  keyword3 = new TemplateData();
                keyword3.setValue("");

                TemplateData  keyword4 = new TemplateData();
                keyword4.setValue("");

                data.put("keyword1",keyword1);
                data.put("keyword2",keyword2);
                data.put("keyword3",keyword3);
                data.put("keyword4",keyword4);
                template.setData(data);

                String json =  JSONObject.toJSONString(template);
                logger.info("订单发送通知模板消息JSON数据:"+ JsonUtils.toJson(template));

                String ret = HttpClientUtil.sendPost(SEND_TEMPLATE_MESSAGE+accessToken, json);
                MessageRes res = getObject(ret,MessageRes.class);
                if("ok".equals(res.getErrmsg())) {//发送成功
                    logger.info("发送模板消息成功!");
                }
            }
        }catch (Exception e){
            logger.error("向用户发送模板消息异常:",e);
        }
    }


    /**
     * 字符串转对象
     * @param pojo
     * @param tclass
     * @return
     */
    public static <T> T getObject(String pojo, Class<T> tclass) {
        try {
            return JSONObject.parseObject(pojo, tclass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
