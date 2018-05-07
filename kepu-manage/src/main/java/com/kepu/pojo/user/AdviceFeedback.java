package com.kepu.pojo.user;

import java.util.Date;

public class AdviceFeedback {
	
		private Integer uid;

	    private String advice;

	    private Date createtime;
	    
	    private String nickname;
	    
	    private String mobile;

		public Integer getUid() {
			return uid;
		}

		public void setUid(Integer uid) {
			this.uid = uid;
		}

		public String getAdvice() {
			return advice;
		}

		public void setAdvice(String advice) {
			this.advice = advice;
		}

		public Date getCreatetime() {
			return createtime;
		}

		public void setCreatetime(Date createtime) {
			this.createtime = createtime;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
	    
	    
}
