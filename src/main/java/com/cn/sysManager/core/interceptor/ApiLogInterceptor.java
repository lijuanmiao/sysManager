package com.cn.sysManager.core.interceptor;

import com.cn.sysManager.exceptions.ApiException;
import com.cn.sysManager.toolbox.utils.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by lijm on 2018-04-27.
 */
@Component
@Aspect
@Order(0)
public class ApiLogInterceptor {

    Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(* cn.no7player.controller..*.*(..))")
    private void pointCutMethod(){

    }

    //声明环绕通知
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint)throws Throwable{

        StringBuilder sb = new StringBuilder("\r\n");
        Object result = null ;
        long begin = System.currentTimeMillis();

        try{
            sb.append("[方法]"+joinPoint.getTarget().getClass()+"."+joinPoint.getSignature().getName()+"\r\n");
            logger.info("开始："+sb.toString());
            if (joinPoint.getArgs()!=null && joinPoint.getArgs().length > 0) {
                sb.append("[参数]");
                for (Object obj : joinPoint.getArgs()) {
                    try {
                        if (obj instanceof MultipartFile) {
                            MultipartFile file = (MultipartFile)obj;
                            sb.append(file.getOriginalFilename() + "[" + (file.getSize() / 1024) + "KB],");
                        } else if (obj instanceof File) {
                            File file =(File) obj ;
                            sb.append(file.getName());
                        } else if (obj instanceof HttpServletResponse || obj instanceof HttpServletRequest) {
                        } else {
                            sb.append(JsonUtil.toJson(obj)+",");
                        }
                    } catch (Exception e) {
                        //转json异常后直接toString
                        sb.append(obj+",");
                    }
                }
                sb.append("\r\n");
            }
            result = joinPoint.proceed();
            return result;
        }catch (Exception ex){
            sb.append("[异常]");
            if (ex instanceof ApiException) {
                 //sb.append(ex["resCode"]+":"+ex["resDesc"]);
            } else {
                sb.append(ex.getMessage());
            }
            throw ex;
        }finally {
            long end = System.currentTimeMillis();
            sb.append("[返回]" + JsonUtil.toJson(result) +" \r\n");
            sb.append("[耗时]" + (end - begin) +" ms\r\n");
            logger.info("结束："+sb.toString());
        }
    }
}
