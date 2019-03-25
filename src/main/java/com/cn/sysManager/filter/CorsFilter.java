package com.cn.sysManager.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**跨域过滤器
 * Created by lijm on 2018-10-08.
 */
@Configuration
@ConfigurationProperties
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException,ServletException{

        long startTime = System.currentTimeMillis();
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String uuid = UUID.randomUUID().toString() + "-";
        String realIp = getRealIp(request);
        String origin = request.getHeader("Origin");
        String refer = request.getHeader("Refer");
        response.setHeader("Access-Control-Allow-Origin",origin);
        /*if(origin!=null){

        }else if(refer!=null){
            response.setHeader("Access-Control-Allow-Origin",refer);
        }*/
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "client, auth-token, x-auth-token, x-requested-with,Authorization,Origin, Accept, Content-Type,x-xsrf-token");
        if (request.getMethod() != "OPTIONS") {
            chain.doFilter(req, res);
        }else{
            logger.info(uuid+ "客户端[OPTIONS]");
        }
        long endTime = System.currentTimeMillis();
        logger.info(uuid + "客户端IP:{} {} {} 开始时间:{} 结束时间{} 耗时:{} ms", realIp, request.getMethod(),
                request.getRequestURI(), //DateUtil.formatTimeInMillis(startTime),
                (endTime - startTime));
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}
    private String getRealIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        final String[] arr = ip.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) ip = str;
            break;
        }
        return ip;
    }
}