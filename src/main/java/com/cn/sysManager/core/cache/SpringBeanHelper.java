package com.cn.sysManager.core.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by lijm on 2018-04-04.
 */
@Component
public class SpringBeanHelper implements ApplicationContextAware {

    //Spring应用上下文环境
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
    /**
     * 根据bean的名称或
     * @param beanName
     * @return
     */
    static Object getBean(String beanName) {
      return  applicationContext.getBean(beanName);
    }

    static <T> T getBean(Class<T> clazz) {
       return applicationContext.getBean(clazz);
    }
}