package com.kepu.pojo;

import java.util.Date;
//兑换记录
public class StRecordExchange {
    private Integer recordid;  //兑换记录

    private String recordname; //兑换记录商品名字

    private Date recordtime;//兑换记录时间

    private Double recordintegral;//兑换记录积分

    private String recordimg;//兑换记录图片

    private Integer userid;//兑换记录userid

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public String getRecordname() {
        return recordname;
    }

    public void setRecordname(String recordname) {
        this.recordname = recordname == null ? null : recordname.trim();
    }

    public Date getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(Date recordtime) {
        this.recordtime = recordtime;
    }

    public Double getRecordintegral() {
        return recordintegral;
    }

    public void setRecordintegral(Double recordintegral) {
        this.recordintegral = recordintegral;
    }

    public String getRecordimg() {
        return recordimg;
    }

    public void setRecordimg(String recordimg) {
        this.recordimg = recordimg == null ? null : recordimg.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}