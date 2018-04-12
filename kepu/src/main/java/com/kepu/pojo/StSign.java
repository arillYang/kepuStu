package com.kepu.pojo;

import java.util.Date;

public class StSign {
    private Integer userid;

    private Date signdate;
    
    private String  ajxsigndate;
    
    private int  sqlsigndate;




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

	private Integer score;

    private Integer keysta;

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
}