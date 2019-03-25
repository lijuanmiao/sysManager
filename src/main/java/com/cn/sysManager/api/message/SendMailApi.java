package com.cn.sysManager.api.message;

import com.cn.sysManager.common.ApiResultHelper;
import com.cn.sysManager.service.ISendMailService;
import com.cn.sysManager.toolbox.utils.Validator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**发送电子邮件
 * Created by lijm on 2019-03-21.
 */
@RestController
@RequestMapping(value = "/api/send", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "发送电子邮件", description = "发送电子邮件")
public class SendMailApi {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ISendMailService sendEmailService;

    @GetMapping("/email")
    @ApiOperation(value = "发送邮件", notes = "发送邮件,9101电子邮箱格式不正确")
    ApiResultHelper sendEmail(@ApiParam(required = true, name = "type", value = "操作类型(1:简单文本2:文本和带附件)",
                                      allowableValues = "1,2", defaultValue = "1")
                              @RequestParam(value = "type") Integer type,
                              @ApiParam(required = true, name = "to", value = "to发送人")
                              @RequestParam(value = "to", required = true)String to) throws Exception{

        try{
            if (!Validator.isEmail(to)) {
                return  ApiResultHelper.error("9101","电子邮箱格式不正确");//电子邮箱格式不正确
            }
            if(type == 1){
                sendEmailService.sendSimpleEmail(to);
            }else if(type == 2){
                sendEmailService.sendMessageUrlEmail(to,"");
            }
            return ApiResultHelper.success();

        }catch (Exception ex){
            logger.error("发送邮件异常:"+ex);
            return  ApiResultHelper.error("9999",ex.getMessage());
        }
    }
}
