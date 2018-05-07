package com.kepu.pojo;

public class StCommunityTeachDetail {
    private Integer uid;

    private Integer teachid;

    private String description;

    private String coverpic;

    private String vediourl;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTeachid() {
        return teachid;
    }

    public void setTeachid(Integer teachid) {
        this.teachid = teachid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCoverpic() {
        return coverpic;
    }

    public void setCoverpic(String coverpic) {
        this.coverpic = coverpic == null ? null : coverpic.trim();
    }

    public String getVediourl() {
        return vediourl;
    }

    public void setVediourl(String vediourl) {
        this.vediourl = vediourl == null ? null : vediourl.trim();
    }
}