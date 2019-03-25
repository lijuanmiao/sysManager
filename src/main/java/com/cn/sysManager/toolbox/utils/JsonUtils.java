package com.cn.sysManager.toolbox.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lijm on 2019-03-25.
 */
public class JsonUtils {
    final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private final static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object objec) {
        try {
            if (objec != null) {
                return mapper.writeValueAsString(objec);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    public static String toJson(Map map) {
        return toJson((Object) map);
    }

    public static Map toMap(String json) throws Exception {
        if (json == null || json.isEmpty() || json.equals("{}")) {
            return new HashMap();
        }
        return mapper.readValue(json.replaceAll("'","\""), Map.class);
    }

    public static List toList(String json) throws Exception {
        if (json == null || json.isEmpty() || json.equals("[]")) {
            return new ArrayList();
        }
        return mapper.readValue(json.replaceAll("'","\""), List.class);
    }

    public static <T> T toObject(String json,Class<T> clazz) throws Exception {
        if (json == null || json.isEmpty() || json == "{}") {
            return null;
        }
        return mapper.readValue(json, clazz);
    }
    /*  public static Map<String, String> jsonToMap(String json) {
         if (json == null || json.isEmpty() || json.equals("{}")) {
             return new HashMap<String, String>();
         }
         JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, String.class);
         return mapper.readValue(json, javaType)
     }

    public static List<Map> toListMap(Object json,String type) throws Exception
     {
         String objjson=toJson(json)
         List<Map> listMap=[]
         Map objMap=toMap(objjson)


         if("resultList" == type || "data" ==type)
         {
                 JSONArray json1=new JSONArray(objMap.get(type))
                 if(json1!=null || json1.length()!=0)
                 {
                     for(int i=0;i<json1.length();i++)
                     {
                         String str1=json1.get(i).toString()
                         Map map=toMap(str1)
                         listMap.add(i,map)
                     }
                 }

         }
         return listMap
     }*/

  /*  public static String listToStr(Object json,String type)throws Exception
    {
        String objjson=toJson(json)
        String str=""
        Map objMap=toMap(objjson)
        if("resultList" == type || "data" ==type)
        {
            JSONArray json1=new JSONArray(objMap.get(type))
            if(json1!=null || json1.length()!=0)
            {
                for(int i=0;i<json1.length();i++)
                {
                    String str1=json1.get(i).toString()
                    Map map=toMap(str1)
                    str=map.get("userId")+","
                }
            }

        }
       return str.substring(0,str.length()-1)

    }*/
}
