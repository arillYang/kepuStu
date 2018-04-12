package com.kepu.pojo;

import java.util.Date;

public class StCommunity {
    private Integer uid;

    private String name;

    private String introduce;

    private String logo;

    private Integer type;

    private Date createtime;

    private Integer state;

    private Integer carousel;

    private String display;

    private Integer membernum;

    private Integer applynum;

    private Integer town;

    private Integer country;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getCarousel() {
        return carousel;
    }

    public void setCarousel(Integer carousel) {
        this.carousel = carousel;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display == null ? null : display.trim();
    }

    public Integer getMembernum() {
        return membernum;
    }

    public void setMembernum(Integer membernum) {
        this.membernum = membernum;
    }

    public Integer getApplynum() {
        return applynum;
    }

    public void setApplynum(Integer applynum) {
        this.applynum = applynum;
    }

    public Integer getTown() {
        return town;
    }

    public void setTown(Integer town) {
        this.town = town;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }
}