package com.kepu.pojo;

import java.util.Date;

public class StNewsTimetask {
    private Integer newsid;

    private Date publishtime;

    private Integer exec;

    private Date createtime;

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public Integer getExec() {
        return exec;
    }

    public void setExec(Integer exec) {
        this.exec = exec;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}