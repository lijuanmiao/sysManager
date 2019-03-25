package com.cn.sysManager.api.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.sysManager.common.ApiResultHelper;
import com.cn.sysManager.models.api.OrderCodeReq;
import com.cn.sysManager.toolbox.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**下载小程序二维码
 * Created by lijm on 2019-03-25.
 */
@RestController
@RequestMapping("/api/generate")
@Api(value = "生成二维码", description = "生成二维码", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GenerateWxCodeUnlimitApi {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    String codeUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";//生成二维码url

    @PostMapping(value = "/code")
    @ApiOperation(value = "获取二维码", notes = "获取二维码")
    public ApiResultHelper codeMit(@RequestBody @Validated OrderCodeReq orderCodeReq) {

        String fileName = "";
        try {
            String appId = "";//小程序appId
            String appSecret = ""; //小程序秘钥

            String url = "https://api.weixin.qq.com/cgi-bin/token?appid="+appId+"&secret="+appSecret+"&grant_type=client_credential";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            logger.info("responseEntity授权获取accessToken:"+responseEntity);
            if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {

                String sessionData = responseEntity.getBody();
                logger.info("sessionData = " + sessionData);
                JSONObject jsonObj = JSON.parseObject(sessionData);
                String accessToken = jsonObj.getString("access_token");


                URL url_Code= new URL(codeUrl+accessToken);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url_Code.openConnection();
                httpURLConnection.setRequestMethod("POST");//提交模式
                // 发送POST请求必须设置如下两行
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
                // 发送请求参数
                JSONObject paramJson = new JSONObject();
                paramJson.put("scene",orderCodeReq.getScene());
                paramJson.put("page", orderCodeReq.getPage());
                paramJson.put("width", orderCodeReq.getWidth());//二维码的宽度，默认为 430px，最小 280px，最大 1280px
                paramJson.put("auto_color", orderCodeReq.getAutoColor());
                Map<String,Object> line_color = new HashMap<>();
                line_color.put("r", 0);
                line_color.put("g", 0);
                line_color.put("b", 0);
                paramJson.put("line_color", line_color);
                printWriter.write(paramJson.toString());
                // flush输出流的缓冲
                printWriter.flush();
                //开始获取数据
                String uploadBasePath = "";
                String accessPath= ""; //访问路径
                String imei = RandomUtil.getCharAndNum(10);
                String name = imei+".png";

                if(uploadBasePath.charAt(uploadBasePath.length()-1) != '/') uploadBasePath=uploadBasePath+"/";
                String basePath=uploadBasePath+"";
                File fileDir=new File(basePath);
                if(!fileDir.isDirectory()) fileDir.mkdirs();
                fileName = accessPath+"/"+""+"/"+name;

                BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
                OutputStream os = new FileOutputStream(new File(basePath+"/"+name));
                int len;
                byte[] arr = new byte[1024];
                while ((len = bis.read(arr)) != -1)
                {
                    os.write(arr, 0, len);
                    os.flush();
                }
                os.close();

            }
        }catch (Exception ex){

        }
        return ApiResultHelper.success(fileName);
    }
}
