package com.cn.sysManager.core.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijm on 2019-03-21.
 */
public class UserDetailHelper {

    private static final ThreadLocal<Map> threadData = new ThreadLocal<Map>(){
        @Override
        public Map initialValue(){
            return new HashMap();
        }
    };

    /**
     * 设置当前线程 用户ip
     * @param userIp
     */
    static void setCurrentUserIp(String userIp){
        setDataByKey("userIp",userIp);
    }

    /**
     * 获取当前线程 用户ip
     * @return
     */
    static String getCurrentUserIp(){
        return getDataByKey("userIp",String.class);
    }

    private static void setDataByKey(String key,Object value){
        Map map = getThreadData();
        map.put(key,value);
    }

    private static <T> T getDataByKey(String key,Class<T> classType){
        Map map = getThreadData();
        Object o = map.get(key);
        if (o==null) {
            return null;
        }
        return (T)o;
    }

    private static Map getThreadData(){
        Map map = threadData.get();
//        if (map==null) {
//            map = new HashMap()
//            threadData.set(map)
//        }
        return map;
    }
}
