package com.cn.sysManager.toolbox.utils;

import java.util.Random;

/**
 * Created by lijm on 2018-03-02.
 */
public class RandomUtil{

    /**
     * 生成随机数字组合
     * @param length [生成随机数的长度]
     * @return
     */
    public static String getNum(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

    /**
     * 生成随机数和字母
     * @param length
     * @return
     */
    public static String getCharAndNum(int length){

        String val = "";
        Random random = new Random();
        for(int i=0;i<length;i++){
            String charorNum = random.nextInt(2)% 2 ==0?"char":"num";
            if("char".equalsIgnoreCase(charorNum)){

                int choice = random.nextInt(2) % 2 ==0 ? 65 : 97;
                val +=(char) (choice + random.nextInt(26));
            }else if("num".equalsIgnoreCase(charorNum)){
                val +=String.valueOf(random.nextInt(10));
             }
        }
        return val;
    }
}