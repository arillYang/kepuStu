package com.kepu.pojo.community;

import com.kepu.pojo.StCommunityUser;

public class MemberList extends StCommunityUser{
	private String nickName;
	private String mobile;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
