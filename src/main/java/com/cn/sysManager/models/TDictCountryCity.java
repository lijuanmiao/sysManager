package com.cn.sysManager.models;

public class TDictCountryCity {
    private Integer id;

    private String ccCode;

    private String ccName;

    private String ccUpCode;

    private String ccDesc;

    private String ccZip;

    private String ccPy;

    private String ccShortName;

    private String ext1;

    private String ext2;

    private Byte isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCcCode() {
        return ccCode;
    }

    public void setCcCode(String ccCode) {
        this.ccCode = ccCode == null ? null : ccCode.trim();
    }

    public String getCcName() {
        return ccName;
    }

    public void setCcName(String ccName) {
        this.ccName = ccName == null ? null : ccName.trim();
    }

    public String getCcUpCode() {
        return ccUpCode;
    }

    public void setCcUpCode(String ccUpCode) {
        this.ccUpCode = ccUpCode == null ? null : ccUpCode.trim();
    }

    public String getCcDesc() {
        return ccDesc;
    }

    public void setCcDesc(String ccDesc) {
        this.ccDesc = ccDesc == null ? null : ccDesc.trim();
    }

    public String getCcZip() {
        return ccZip;
    }

    public void setCcZip(String ccZip) {
        this.ccZip = ccZip == null ? null : ccZip.trim();
    }

    public String getCcPy() {
        return ccPy;
    }

    public void setCcPy(String ccPy) {
        this.ccPy = ccPy == null ? null : ccPy.trim();
    }

    public String getCcShortName() {
        return ccShortName;
    }

    public void setCcShortName(String ccShortName) {
        this.ccShortName = ccShortName == null ? null : ccShortName.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }
}