package com.cn.sysManager.toolbox.constant;

/**
 * Created by lijm on 2017-12-15.
 * 枚举类
 */
public class CommonTypeConstant {

    /**
     * 默认页面大小
     */
    public static final Integer DEFAULT_PAGE_SIZE = 20;

    /**
     * 是和否
     */
    public enum YesOrNo{

        Yes("是","1"),
        No("否","2");

        //成员变量
        private String name;
        private String  value;

        //构造方法
        YesOrNo(String name,String value){
            this.name = name;
            this.value = value;
        }
        @Override//覆盖方法
        public String toString(){
            return this.value;
        }
     }
    /**
     * 是否有效
     */
    public enum IsActive{

        True("1", "有效"),
        False("2","无效");

        String value;
        String desc;
        IsActive(String value,String desc){
            this.value = value;
            this.desc = desc;
        }

        @Override
        public String toString(){
            return this.value;
        }
        public String getValue(){
            return this.value;
        }
        public String getDesc(){
            return this.desc;
        }
    }
}