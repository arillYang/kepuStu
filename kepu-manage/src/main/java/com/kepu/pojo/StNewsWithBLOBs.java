package com.kepu.pojo;

public class StNewsWithBLOBs extends StNews {
    private String content;

    private String realcontent;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRealcontent() {
        return realcontent;
    }

    public void setRealcontent(String realcontent) {
        this.realcontent = realcontent == null ? null : realcontent.trim();
    }
}