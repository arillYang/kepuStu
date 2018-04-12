package com.kepu.pojo;

import java.util.Date;

public class StNoticeNewsVote {
    private Long uid;

    private Date votetime;

    private Integer newsid;

    private Integer userid;

    private Integer liked;

    private Integer dislike;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Date getVotetime() {
        return votetime;
    }

    public void setVotetime(Date votetime) {
        this.votetime = votetime;
    }

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }
}