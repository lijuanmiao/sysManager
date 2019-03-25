package com.cn.sysManager.controller;

import com.cn.sysManager.common.Order;
import com.cn.sysManager.service.sys.IDictCountryCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lijm on 2018-10-19.
 */
@RestController
@RequestMapping(value = "inner/dict", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "城市编码", description = "城市编码")
public class DictCountryCityController {

    @Autowired
    private IDictCountryCityService dictCountryCityService;

    @RequestMapping(value = "/getCity",method = RequestMethod.GET)
    @ApiOperation(value = "获取所的城市编码", notes = "获取所的城市编码")
    public List getDictCountryCity()throws Exception{

        Map<String,Object> params = new HashMap<String,Object>();
        return dictCountryCityService.findListByParams(params, Order.desc("id"));
    }

    @RequestMapping(value = "/getCode",method = RequestMethod.GET)
    @ApiOperation(value = "获取城市编码和下级编码", notes = "获取城市编码和下级编码")
    public List getDictCode(@ApiParam(name = "cCode", value = "城市编码", required = true)
                            @RequestParam(value = "cCode")String cCode,
                            @ApiParam(name = "upCode", value = "上级城市编码", required = true)
                            @RequestParam(value = "upCode")String upCode)throws Exception{

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("cCode",cCode);
        params.put("upCode",upCode);
        return dictCountryCityService.findListByParams(params, Order.desc("id"));
    }
}
