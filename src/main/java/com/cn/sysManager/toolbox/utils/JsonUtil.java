package com.cn.sysManager.toolbox.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lijm on 2018-04-24.
 */
public class JsonUtil {

    static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private final static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object object)throws Exception{
        if(object == null) return "null";
        return mapper.writeValueAsString(object);
    }

    public static Map toMap(String json)throws Exception{
        if(json == null || json.isEmpty() || json.equals("{}")){
            return new HashMap();
        }
        return mapper.readValue(json,Map.class);
    }

    public static List toList(String json)throws Exception{
        if(json == null || json.isEmpty() || json.equals("[]")){
            return new ArrayList();
        }
        return mapper.readValue(json,List.class);
    }

    public static <T> T parseToObj(String json,Class<T> valueType)throws Exception{
        if(json == null || json.isEmpty()){
            return null;
        }
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,false);
        return mapper.readValue(json,valueType);
    }
}
