package com.cn.sysManager.core.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lijm on 2018-04-04.
 */
public class SysConfigCache {
    final static Logger logger = LoggerFactory.getLogger(SysConfigCache.class);

 /*   @Autowired
     private ISysConfigService sysConfigService;

    public  List<TSysConfig> getSysConfigValue()throws Exception{

        logger.info("============加载缓存数据================");
        sysConfigService = (ISysConfigService) SpringBeanHelper.getBean("sysConfigService");
        Map<String,Object> params = new HashMap<String,Object>();
        List<TSysConfig> sysConfigList = sysConfigService.findListByParams(params, Order.asc("create_time"));
        return sysConfigList;
    }*/
}
