package com.kepu.pojo;

import java.util.Date;

public class StExchangeBalance {
    private Integer id;

    private String exchange;

    private Date insertdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange == null ? null : exchange.trim();
    }

    public Date getInsertdate() {
        return insertdate;
    }

    public void setInsertdate(Date insertdate) {
        this.insertdate = insertdate;
    }
}