package com.cn.sysManager.controller;

import com.cn.sysManager.models.TNotice;
import com.cn.sysManager.service.INotictService;
import com.cn.sysManager.toolbox.DateUtil;
import com.cn.sysManager.toolbox.excel.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/noExport", method = RequestMethod.GET)
    @ApiOperation(value = "导出公告信息", notes = "导出公告信息")
    public void reExcept(HttpServletResponse response)throws Exception{

        String destFilePath = URLEncoder.encode(DateUtil.formatDate(DateUtil.getTime(),"yyyyMMdd")+"公告信息.xlsx","utf-8");
        response.reset();
        response.setContentType("application/msexcel");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition","attachment; filename*=utf-8'zh_cn'" + destFilePath);

        InputStream inputStream = null;
        OutputStream out = null;
        String templateName =  "templates/notice.xlsx";
        inputStream = this.getClass().getClassLoader().getResourceAsStream(templateName);

        List<TNotice> noticeList = notictService.getNotice();

        out = response.getOutputStream();

        int maxLen = 10000;//excel的sheet最大存多少数据
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("count",noticeList.size());
        model.put("noticeList",noticeList);
        ExcelUtil.generateExcelByTemplates(out,inputStream,model);
    }
}
