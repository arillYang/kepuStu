package com.kepu.pojo;

import java.util.Date;

public class StCommunityArticle {
    private Integer uid;

    private Integer communityid;

    private String title;

    private Date publishtime;

    private Integer userid;

    private String username;

    private String avatar;

    private String detailpics;

    private String content;

    private Integer replynum;

    private Integer praisenum;

    private Integer state;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getDetailpics() {
        return detailpics;
    }

    public void setDetailpics(String detailpics) {
        this.detailpics = detailpics == null ? null : detailpics.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getReplynum() {
        return replynum;
    }

    public void setReplynum(Integer replynum) {
        this.replynum = replynum;
    }

    public Integer getPraisenum() {
        return praisenum;
    }

    public void setPraisenum(Integer praisenum) {
        this.praisenum = praisenum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}