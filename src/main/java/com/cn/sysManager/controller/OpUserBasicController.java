package com.cn.sysManager.controller;

import com.cn.sysManager.common.ApiResultHelper;
import com.cn.sysManager.models.TOpUserBasic;
import com.cn.sysManager.service.IOpuserBasicService;
import com.cn.sysManager.toolbox.DateUtil;
import com.cn.sysManager.toolbox.PasswordHandler;
import com.cn.sysManager.toolbox.constant.CommonTypeConstant;
import com.cn.sysManager.toolbox.excel.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by lijm on 2018-09-18.
 */
@RestController
@RequestMapping("/api/opUser")
@Api(value = "用户信息接口", description = "用户信息接口")
public class OpUserBasicController {

    @Autowired
    private IOpuserBasicService opuserBasicService;

    @Autowired
    private Environment env;


    @RequestMapping(value = "/getBasic",method = RequestMethod.GET)
    @ResponseBody
    public TOpUserBasic getOpuser(String id){

        return  opuserBasicService.getOpUserBasic(id);
    }


    @RequestMapping(value = "/reImport",method = RequestMethod.POST)
    @ApiOperation(value = "批量新增用户信息", notes = "批量新增用户信息")
    public ApiResultHelper reImport(@RequestPart(value = "file", required = false) MultipartFile file)throws Exception{

        String fileName = file.getOriginalFilename();//获取文件名称
        if(file!=null){

            String subffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
            if("XLS".equalsIgnoreCase(subffix) || "XLSX".equalsIgnoreCase(subffix)){

                String path = env.getProperty("path.upload");//文件暂时存放
                File targetFile = new File(path,fileName);
                file.transferTo(targetFile);//临时存放文件

                String[][] excel = ExcelUtil.readExcel(targetFile,2);

                List<TOpUserBasic> succList = new ArrayList<TOpUserBasic>();
                int rowLength = excel.length;
                if(rowLength>0){
                    for(int i=0;i<rowLength;i++){

                        TOpUserBasic user = new TOpUserBasic();
                        String loginName = excel[i][0]==null?"":excel[i][0];
                        String relName = excel[i][1] == null?"":excel[i][1];
                        String mobile = excel[i][2] == null?"":excel[i][2];
                        String email = excel[i][3] == null?"":excel[i][3];

                        user.setLoginName(loginName);
                        user.setRealName(relName);
                        user.setMobile(mobile);
                        user.setEmail(email);
                        user.setIsActive(Integer.parseInt(CommonTypeConstant.IsActive.True.getValue()));
                        user.setCreateTime(DateUtil.getTime().getTime());
                        user.setPassword(PasswordHandler.getPassword("888888"));
                        user.setUpUserId(0);
                        user.setLockFlag(2);
                        succList.add(user);
                    }
                }
                opuserBasicService.addBatchUser(succList);
            }
        }
        return  ApiResultHelper.success();
    }
    /**
     * excel导出
     * @param response
     */
    @RequestMapping(value = "/reExport", method = RequestMethod.GET)
    @ApiOperation(value = "导出用户信息", notes = "导出用户信息")
    public void reExcept(HttpServletResponse response)throws Exception{

        String destFilePath = URLEncoder.encode(DateUtil.formatDate(DateUtil.getTime(),"yyyyMMdd")+"用户信息.xlsx","utf-8");
        response.reset();
        response.setContentType("application/msexcel");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition","attachment; filename*=utf-8'zh_cn'" + destFilePath);

        InputStream inputStream = null;
        OutputStream out = null;
        String templateName =  "templates/user.xlsx";
        inputStream = this.getClass().getClassLoader().getResourceAsStream(templateName);

        Map<String,Object> params = new HashMap<String,Object>();
        List<TOpUserBasic> userList = opuserBasicService.getAll(params);
        out = response.getOutputStream();

        int maxLen = 3;//excel的sheet最大存多少数据
       ExcelUtil.generateExcelByTemplate(out, inputStream, userList, "userList", maxLen);
    }

}
