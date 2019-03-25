package com.cn.sysManager.models;

public class TSysConfig {
    private Integer id;

    private String configCode;

    private String configName;

    private String configDesc;

    private Byte busiType;

    private Byte childType;

    private Byte configType;

    private String configVlaue;

    private String configDefaultValue;

    private Long createTime;

    private String createUser;

    private Byte isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode == null ? null : configCode.trim();
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc == null ? null : configDesc.trim();
    }

    public Byte getBusiType() {
        return busiType;
    }

    public void setBusiType(Byte busiType) {
        this.busiType = busiType;
    }

    public Byte getChildType() {
        return childType;
    }

    public void setChildType(Byte childType) {
        this.childType = childType;
    }

    public Byte getConfigType() {
        return configType;
    }

    public void setConfigType(Byte configType) {
        this.configType = configType;
    }

    public String getConfigVlaue() {
        return configVlaue;
    }

    public void setConfigVlaue(String configVlaue) {
        this.configVlaue = configVlaue == null ? null : configVlaue.trim();
    }

    public String getConfigDefaultValue() {
        return configDefaultValue;
    }

    public void setConfigDefaultValue(String configDefaultValue) {
        this.configDefaultValue = configDefaultValue == null ? null : configDefaultValue.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }
}