package com.cn.sysManager.models.api;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by lijm on 2019-03-25.
 */
public class OrderCodeReq implements Serializable {

    @ApiModelProperty(value = "最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）", required = false)
    String scene;

    @NotNull(message = "Url跳转地址")
    @ApiModelProperty(value = "必须是已经发布的小程序存在的页面（否则报错），例如 pages/index/index, 根路径前不要填加 /,不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面", required = true)
    String page;

    @NotNull(message = "二维码生成大小")
    @ApiModelProperty(value = "默认为 430px，最小 280px，最大 1280px", required = true)
    Integer width;

    @ApiModelProperty(value = "默认为 false线条颜色为黑色，true是自动填充颜色", required = false)
    Boolean autoColor;

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Boolean getAutoColor() {
        return autoColor;
    }

    public void setAutoColor(Boolean autoColor) {
        this.autoColor = autoColor;
    }
}
