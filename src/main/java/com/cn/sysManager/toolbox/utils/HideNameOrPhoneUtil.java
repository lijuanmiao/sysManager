package com.cn.sysManager.toolbox.utils;

/**
 * Created by lijm on 2018-03-02.
 */
public class HideNameOrPhoneUtil {

    /**
     * 对名进行隐藏
     * @param name
     * @return
     */
    public static String hideName(String name) {
        if (name!=null) {
            String newName = name.substring(0, 1);
            int asteriskLen = name.length() - 1;
            for(int i=0;i<asteriskLen;i++){
                newName += "*";
            }
            return newName;
        }
        return name;
    }

    /**
     * 对手机号码进行隐藏
     * @param phone
     * @return
     */
    public static String hidePhone(String phone){
        if(phone!="" && phone.length() == 11){

            return phone.substring(0, 3) + "*****" + phone.substring(8);
        }
        return  phone;
    }

    /**
     * 对身份证号码进行隐藏处理
     * @param idCard
     * @return
     */
    public static String hideIdCard(String idCard){
        if(idCard!=null && idCard.length()>7){

            String part1 = idCard.substring(0,3);
            String part2 = idCard.substring(idCard.length()-4);
            StringBuffer part3 = new StringBuffer();
            for(int i=0;i<idCard.length()-7;i++){
                part3.append("*");
            }
            return part1+part3+part2;
        }
        return idCard;
    }
}
