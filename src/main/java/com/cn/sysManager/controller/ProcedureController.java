package com.cn.sysManager.controller;

import com.cn.sysManager.service.IProcedureService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijm on 2018-10-22.
 */
@RestController
@RequestMapping(value = "inner/proce", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "存储过程查询", description = "存储过程查询")
public class ProcedureController{

    @Autowired
    private IProcedureService procedureService;

    @RequestMapping(value = "/getPro",method = RequestMethod.GET)
    public void getProTest(String date){

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("start_date",date);
        procedureService.addBasicTemp(params);
    }
}
