package com.kepu.pojo;

import java.util.Date;

public class StUserAccount {
    private Integer userid;

    private Double score;

    private Date createtime;

    private Date updatetime;

    private Integer town;

    private Integer village;

    private String mobile;

    private String showname;
    
    /**
     * 最新添加
     * 账户余额
     */
    private String balance;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getTown() {
        return town;
    }

    public void setTown(Integer town) {
        this.town = town;
    }

    public Integer getVillage() {
        return village;
    }

    public void setVillage(Integer village) {
        this.village = village;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname == null ? null : showname.trim();
    }

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}
    
    
}