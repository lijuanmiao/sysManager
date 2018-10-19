package com.cn.sysManager.toolbox;

import org.springframework.web.multipart.MultipartFile;

/**文件上传工具
 * Created by lijm on 2018-10-11.
 */
public class FileUploadUtil {

    public static String localUpload(MultipartFile file,String uploadBasePath,String accessBasePath,String busiType,String fileType)throws Exception{

        String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();


        return "";
    }

}
