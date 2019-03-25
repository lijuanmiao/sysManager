package com.cn.sysManager.controller;

import com.cn.sysManager.common.Order;
import com.cn.sysManager.service.sys.ISysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lijm on 2018-10-23.
 */
@RestController
@RequestMapping("/api/config")
@Api(value = "系统参数配置", description = "系统参数配置")
public class SysConfigController {

    @Autowired
    private ISysConfigService sysConfigService;

    @RequestMapping(value = "/getSysConfig",method = RequestMethod.GET)
    @ApiOperation(value = "获取参数列表", notes = "获取参数列表")
    public List getSysConfig()throws Exception{

        Map<String,Object> params = new HashMap<String,Object>();
        return sysConfigService.findListByParams(params, Order.desc("id"));
    }
}
