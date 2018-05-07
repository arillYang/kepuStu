package com.kepu.pojo;

import java.util.Date;

public class StSign {
    private Integer userid;

    private Date signdate;

    private Integer score;

    private Integer keysta;

    private Integer days;

    private Integer type;
    
    
    private int sqlsigndate;
    
    private String ajxsigndate;
    
    

    public int getSqlsigndate() {
		return sqlsigndate;
	}

	public void setSqlsigndate(int sqlsigndate) {
		this.sqlsigndate = sqlsigndate;
	}



	public String getAjxsigndate() {
		return ajxsigndate;
	}

	public void setAjxsigndate(String ajxsigndate) {
		this.ajxsigndate = ajxsigndate;
	}

	public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getKeysta() {
        return keysta;
    }

    public void setKeysta(Integer keysta) {
        this.keysta = keysta;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}