package com.cn.sysManager.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("接口返回数据")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ApiResultHelper  {

    @ApiModelProperty(value = "返回代码", required = true)
    private String resCode;

    @ApiModelProperty(value = "返回代码描述")
    private String resDesc;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    public ApiResultHelper(String resCode, String resDesc, Object data) {
        this.resCode = resCode;
        this.resDesc = resDesc;
        this.version = "1.0";
        this.data = data;
    }

    public static ApiResultHelper success() {
        return new ApiResultHelper("0000", "", "");
    }

    public static ApiResultHelper success(Object obj) {
        return new ApiResultHelper("0000", "操作成功", obj);
    }

    public static ApiResultHelper error(String resCode,String errorMessage) {
        return new ApiResultHelper(resCode, errorMessage,"");
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}