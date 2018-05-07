package com.kepu.pojo;
import java.math.BigDecimal;
import java.util.Date;

public class StProduct {
    private Integer uid;

    private Integer classfyid;

    private String title;

    private String introduce;

    private String mobile;

    private BigDecimal money;

    private Integer villageid;

    private String coverpic;

    private String detailpics;

    private Integer state;

    private String classfyname;

    private String endpic;

    private Integer carousel;

    private Date createtime;

    private Integer sort;

    private Integer userid;

    private String avatar;

    private Date carouseltime;

    private String username;

    private String address;

    private String integral;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getClassfyid() {
        return classfyid;
    }

    public void setClassfyid(Integer classfyid) {
        this.classfyid = classfyid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getVillageid() {
        return villageid;
    }

    public void setVillageid(Integer villageid) {
        this.villageid = villageid;
    }

    public String getCoverpic() {
        return coverpic;
    }

    public void setCoverpic(String coverpic) {
        this.coverpic = coverpic == null ? null : coverpic.trim();
    }

    public String getDetailpics() {
        return detailpics;
    }

    public void setDetailpics(String detailpics) {
        this.detailpics = detailpics == null ? null : detailpics.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getClassfyname() {
        return classfyname;
    }

    public void setClassfyname(String classfyname) {
        this.classfyname = classfyname == null ? null : classfyname.trim();
    }

    public String getEndpic() {
        return endpic;
    }

    public void setEndpic(String endpic) {
        this.endpic = endpic == null ? null : endpic.trim();
    }

    public Integer getCarousel() {
        return carousel;
    }

    public void setCarousel(Integer carousel) {
        this.carousel = carousel;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Date getCarouseltime() {
        return carouseltime;
    }

    public void setCarouseltime(Date carouseltime) {
        this.carouseltime = carouseltime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral == null ? null : integral.trim();
    }
}