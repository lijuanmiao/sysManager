package com.cn.sysManager.common;

import java.io.Serializable;

/**
 * Created by lijm on 2017-12-15.
 * 排序
 */
public class Order implements Serializable{

    private String column;
    private String orderType;

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    private Order(String column,String orderType){

        this.column = column;
        this.orderType = orderType;
    }

    public static Order desc(String column){

        Order order = new Order(column,"desc");
        return order;
    }

    public static Order asc(String column){
        Order order = new Order(column,"asc");
        return order;
    }

    @Override
    public String toString(){

        StringBuffer sb = new StringBuffer();
        sb.append(this.column).append(" ").append(this.orderType);
        return sb.toString();
    }
}
