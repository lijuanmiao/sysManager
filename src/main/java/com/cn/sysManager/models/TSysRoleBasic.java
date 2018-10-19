package com.cn.sysManager.models;

import java.io.Serializable;

/**
 * Created by lijm on 2018-04-02.
 */
public class TSysRoleBasic implements Serializable{

    private Integer id;

    private String channelCode;

    private String roleName;

    private String roleDesc;

    private Byte isDefaultAllocate;

    private Integer createOpId;

    private String createOpName;

    private Long createTime;

    private Byte isActive;

    static final long serialVersionUID = 1L;

    private String menuIds;

    private Boolean isSelected = false;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Byte getIsDefaultAllocate() {
        return isDefaultAllocate;
    }

    public void setIsDefaultAllocate(Byte isDefaultAllocate) {
        this.isDefaultAllocate = isDefaultAllocate;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }



    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
