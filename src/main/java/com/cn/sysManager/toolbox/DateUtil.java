package com.cn.sysManager.toolbox;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by lijm on 2018-10-15.
 */
public class DateUtil {

    /**
     * 格式化日期
     * @param date
     * @param fmt ,fmt为空，默认是 yyyy-MM-dd
     * @return
     */
    public static String formatDate(Date date, String fmt) {
        if (date == null) {
            return "";
        }
        if (fmt == null) fmt = "yyyy-MM-dd";
        SimpleDateFormat myFormat = new SimpleDateFormat(fmt);
        return myFormat.format(date);
    }

    /**
     * 获取当前 时间
     * @return
     */
    public static Timestamp getTime() {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String mystrdate = myFormat.format(calendar.getTime());
        return Timestamp.valueOf(mystrdate);
    }

    /**
     * 获取当前时间的Long类型
     * @return
     */
    public static long currentTimeLong(){
        return new Date().getTime();
    }

    /**
     * 获取num位随机数字
     * @param num
     * @return
     */
    public static String randomChar(int num){
        Random random = new Random();
        String val="";
        for(int i=0;i<num;i++){
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

    /**
     * 根据毫秒数 获取当前时间
     * @param time
     * @return
     */
    static Timestamp getTime(Long time) {
        return new Timestamp(time);
    }

    /**
     * 自定义格式的字符串转换成日期
     * @author lijm
     * @param timeString
     * @param fmt
     * @return
     * @throws Exception
     */
    public static Timestamp getTime(String timeString, String fmt)
            throws Exception {
        SimpleDateFormat myFormat = new SimpleDateFormat(fmt);
        Date date = myFormat.parse(timeString);
        myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return getTime(myFormat.format(date));
    }

    public static Timestamp getTime(String timeString) {
        return Timestamp.valueOf(timeString);
    }

    public static String formatTime(Timestamp time, String fmt) {
        if (time!=null) {
            if (fmt == null) fmt = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat myFormat = new SimpleDateFormat(fmt);
            return myFormat.format(time);
        } else {
            return "";
        }
    }
}
