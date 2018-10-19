package com.cn.sysManager.toolbox.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by lijm on 2018-03-02.
 */
public class BigDecimalUtil {

    /**
     * 数组大写
     */
    private static final String[] ChineseNum = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    /**
     * 中文单位
     */
    private static final String[] ChineseUnit = {"分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟"};

    public static String parseMoney(String pattern,BigDecimal bd){
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(bd);
    }

    /**
     * 格式化货币
     * @param bd
     * @return
     */
    public static String currencyFormat(BigDecimal bd){
        if(bd == null) return "";
        return parseMoney(",###,##0.00",bd);
    }

    public static String percentFormat2(BigDecimal bd) { //不返回%
        if (bd == null) return "";
        String res= parseMoney("0.00%", bd);
        return  res.substring(0,res.length()-1);
    }
}
