package com.cn.sysManager.controller;

import com.cn.sysManager.common.ApiResultHelper;
import com.cn.sysManager.toolbox.utils.HttpRequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lijm on 2018-10-17.
 */
@RestController
@RequestMapping("/api/test")
@Api(value = "测试demo", description = "测试demo")
public class TestController {

    @RequestMapping(value="/getCity",method = RequestMethod.GET)
    ApiResultHelper testGet(@ApiParam(required = true, name = "pCode", value = "编号",defaultValue = "1")
                            @RequestParam("pCode") String pCode)throws Exception{

        String url = "http://10.52.2.203:7016/ppm/contentNew/"+pCode+"/detail";

        //String res = HttpRequestUtil.getInstance().sendHttpGet(url);
        String res = HttpRequestUtil.sendGet(url,"");
        System.out.print(res);
        return ApiResultHelper.success(res);
    }
}