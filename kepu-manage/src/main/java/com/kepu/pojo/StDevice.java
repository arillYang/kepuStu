package com.kepu.pojo;

import java.util.Date;

public class StDevice {
    private Integer uid;

    private String appldentifier;

    private String appversion;

    private String application;

    private String hardware;

    private String pagename;

    private String systemversion;

    private Date createtime;

    private Integer userid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAppldentifier() {
        return appldentifier;
    }

    public void setAppldentifier(String appldentifier) {
        this.appldentifier = appldentifier == null ? null : appldentifier.trim();
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion == null ? null : appversion.trim();
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application == null ? null : application.trim();
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware == null ? null : hardware.trim();
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename == null ? null : pagename.trim();
    }

    public String getSystemversion() {
        return systemversion;
    }

    public void setSystemversion(String systemversion) {
        this.systemversion = systemversion == null ? null : systemversion.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}