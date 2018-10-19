package com.cn.sysManager.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 后台登录用户基本信息
 * Created by lijm on 2017-10-27.
 */
@ApiModel("登录用户基本信息")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class TOpUserBasic implements Serializable{

     private Integer id;
    @ApiModelProperty(value = "登录名")
     private String loginName;

    @ApiModelProperty(value = "真实姓名")
     private String realName;
     private String password;
     private String mobile;
     private String email;
     private Integer upUserId;
     private Integer departmentId;
     private Long createTime;
     private Integer createOpId;
     private String createOpName;
     private String upIp;
     private Long upTime;
     private Integer lockFlag;
     private Integer errorCount;
     private Long limitLockTime;
     private Integer isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUpUserId() {
        return upUserId;
    }

    public void setUpUserId(Integer upUserId) {
        this.upUserId = upUserId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateOpId() {
        return createOpId;
    }

    public void setCreateOpId(Integer createOpId) {
        this.createOpId = createOpId;
    }

    public String getCreateOpName() {
        return createOpName;
    }

    public void setCreateOpName(String createOpName) {
        this.createOpName = createOpName;
    }

    public String getUpIp() {
        return upIp;
    }

    public void setUpIp(String upIp) {
        this.upIp = upIp;
    }

    public Long getUpTime() {
        return upTime;
    }

    public void setUpTime(Long upTime) {
        this.upTime = upTime;
    }

    public Integer getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(Integer lockFlag) {
        this.lockFlag = lockFlag;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public Long getLimitLockTime() {
        return limitLockTime;
    }

    public void setLimitLockTime(Long limitLockTime) {
        this.limitLockTime = limitLockTime;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /*@Override
    public String toString() {
        return "TOpUserBasic [loginName=" + loginName + ", realName=" + realName + "]";
    }*/
}
