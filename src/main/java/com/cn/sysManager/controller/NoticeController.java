package com.cn.sysManager.controller;

import com.cn.sysManager.service.INotictService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lijm on 2018-09-18.
 */

@RestController
@RequestMapping(value = "inner/notice", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "公告接口", description = "公告接口")
public class NoticeController {

    @Autowired
    private INotictService notictService;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List getNotice(){

        return notictService.getNotice();
    }
}
