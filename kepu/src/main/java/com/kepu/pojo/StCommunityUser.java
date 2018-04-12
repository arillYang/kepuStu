package com.kepu.pojo;

import java.util.Date;

public class StCommunityUser {
    private Integer uid;

    private Integer communityid;

    private Integer userid;

    private Integer type;

    private Date jointime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCommunityid() {
        return communityid;
    }

    public void setCommunityid(Integer communityid) {
        this.communityid = communityid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }
}