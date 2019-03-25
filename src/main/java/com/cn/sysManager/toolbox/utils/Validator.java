package com.cn.sysManager.toolbox.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**y验证正则
 * Created by lijm on 2018-10-26.
 */
public class Validator {

    /**
     * 校验手机号
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile){
       Pattern p = Pattern.compile("^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\\\d{8}$");
       Matcher m = p.matcher(mobile);
       return m.matches();
    }

    /**
     * 校验email
     * @param email
     * @return  校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

}
