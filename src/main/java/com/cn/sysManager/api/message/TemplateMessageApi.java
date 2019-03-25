package com.cn.sysManager.api.message;

import com.cn.sysManager.common.ApiResultHelper;
import com.cn.sysManager.service.ITemplateMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**发送模板消息[微信小程序]
 * Created by lijm on 2019-03-25.
 */
@RestController
@RequestMapping(value = "/inner/message", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "发送消息API", description = "发送消息API")
public class TemplateMessageApi {

    @Autowired
    private ITemplateMessageService templateSendService;

    @RequestMapping(value = "/sendOrderSuccMessage", method = RequestMethod.GET)
    @ApiOperation(value = "支付成功发送消息", notes = "支付成功发送消息")
    ApiResultHelper sendMessage() {

        templateSendService.sendMsg();
        return  ApiResultHelper.success();
    }
}
