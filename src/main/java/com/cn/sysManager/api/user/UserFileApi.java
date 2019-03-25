package com.cn.sysManager.api.user;

import com.cn.sysManager.common.ApiResultHelper;
import com.cn.sysManager.exceptions.ApiException;
import com.cn.sysManager.toolbox.DateUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

/**文件上传
 * Created by lijm on 2018-10-11.
 */
@RestController
@RequestMapping(value = "/api/file", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "文件上传", description = "文件上传")
public class UserFileApi {

    @Autowired
    private Environment env;

    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public ApiResultHelper uploadFile(@RequestParam(value = "file") MultipartFile file)throws Exception{

        String subName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
        if (!(subName.equalsIgnoreCase("JPG") || subName.equalsIgnoreCase("PNG") || subName.equalsIgnoreCase("BMP"))) {
            throw new ApiException("61204", "文件格式不支持");
        }
        String sufferName = file.getOriginalFilename();
        if (file!=null) {

            //获取文件的后缀名
            String subffix = sufferName.substring(sufferName.lastIndexOf(".") + 1, sufferName.length());

            String path = env.getProperty("path.upload");//文件暂时存放
            File targetFile = new File(path);
            if(!targetFile.isDirectory()) targetFile.mkdirs();

            //对文件名进行处理
            String fileName = DateUtil.formatDate(DateUtil.getTime(), "yyyyMMddHHmmss")
                    +DateUtil.randomChar(3)+ "." + subffix;
            file.transferTo(new File(path + fileName));
        }
        return ApiResultHelper.success();
    }
}