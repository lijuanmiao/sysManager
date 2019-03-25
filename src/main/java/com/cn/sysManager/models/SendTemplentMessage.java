package com.cn.sysManager.models;

/**
 * Created by lijm on 2018-11-08.
 */
public class SendTemplentMessage {
    //@ApiModelProperty(value = "用户openId")
    private String  openId;

    //@ApiModelProperty(value = "快递公司")
    private String  company;

    // @ApiModelProperty(value = "发货时间")
    private String fhTime;

    //@ApiModelProperty(value = "购买时间")
    private String buyTime;


    // @ApiModelProperty(value = "商品名称")
    private String goodsName;


    //@ApiModelProperty(value = "发送模板")
    private String sendUrl;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFhTime() {
        return fhTime;
    }

    public void setFhTime(String fhTime) {
        this.fhTime = fhTime;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }
}
