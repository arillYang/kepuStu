package com.kepu.pojo;

import java.util.Date;

public class StVillageNewsReport {
    private Integer uid;

    private Long commentid;

    private Integer reportuser;

    private Date createtime;

    private Integer state;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Long getCommentid() {
        return commentid;
    }

    public void setCommentid(Long commentid) {
        this.commentid = commentid;
    }

    public Integer getReportuser() {
        return reportuser;
    }

    public void setReportuser(Integer reportuser) {
        this.reportuser = reportuser;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}