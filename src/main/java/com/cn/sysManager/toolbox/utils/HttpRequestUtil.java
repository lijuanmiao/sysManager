package com.cn.sysManager.toolbox.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/** get/post请求工具类
 * Created by lijm on 2018-03-22.
 */
public class HttpRequestUtil {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(30000)
            .setConnectTimeout(30000)
            .setConnectionRequestTimeout(30000)
            .build();

    private static HttpRequestUtil instance = new HttpRequestUtil();

    private HttpRequestUtil(){}

    public static HttpRequestUtil getInstance(){
        return instance;
    }


    /**
     * 向指定URL发送GET方法的请求[第①种方式]
     * @param url
     * @param param-请求参数应该是name1=value1&name2=value2的形式
     * @return
     */
    public static String sendGet(String url,String param){

        String result = "";
        StringBuilder jsonStr = new StringBuilder();
        BufferedReader in = null;
        try{

            String urlNameString = url + param;
            URL realUrl = new URL(urlNameString);

            //打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();

            //设置通用的请求属性
            connection.setRequestProperty("accept","*/*");
            connection.setRequestProperty("connection","Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible;MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect(); //建立实际的连接
            Map<String,List<String>> map = connection.getHeaderFields();

            for(String key:map.keySet()){
                System.out.print(key+"---->"+map.get(key));
            }

            InputStreamReader reader = new InputStreamReader(
                    connection.getInputStream(), "UTF-8");
            char[] buff = new char[1024];
            int length = 0;
            while((length = reader.read(buff))!= -1){
                result = new String(buff,0,length);
                jsonStr.append(result);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                if(in!=null){
                    in.close();
                }
            }catch (Exception ex2){
                ex2.printStackTrace();
            }
        }
        return jsonStr.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return
     */
    public static String sendPost(String url,String param){

        PrintWriter out = null;
        BufferedReader in = null;
        URLConnection conn = null;
        StringBuilder jsonStr = new StringBuilder();
        try{

            URL realUrl = new URL(url);
            //打开和URL之间的连接
            conn = realUrl.openConnection();
            conn.setConnectTimeout(20000);
            //设置通用的请求属性
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
            //发送post请求必须设置下面两行
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);//发送请求参数
            out.flush();//flush输出流的缓存
            InputStreamReader reader = new InputStreamReader(conn.getInputStream(),"UTF-8");
            char[] buff = new char[1024];
            int length = 0;
            while ((length = reader.read(buff)) !=-1){
                String result = new String(buff,0,length);
                jsonStr.append(result);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                if(in!=null){
                    in.close();
                }
            }catch (Exception ex2){
                ex2.printStackTrace();
            }
        }
        return jsonStr.toString();
    }
    /**
     * 发送 get请求[第②种方式]
     * @param httpUrl
     */
    public String sendHttpGet(String httpUrl)throws Exception{
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        return send_HttpGet(httpGet);
    }

    /**
     * 发送Get请求
     * @param httpGet
     * @return
     */
    private String send_HttpGet(HttpGet httpGet)throws Exception{
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            responseContent = convertStreamToString(response.getEntity().getContent());
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return responseContent;
    }

    private String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } finally {
            is.close();
        }
        return sb.toString();
    }
}