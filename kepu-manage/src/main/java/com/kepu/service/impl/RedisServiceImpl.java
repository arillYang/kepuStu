package com.kepu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kepu.dao.JedisClient;
import com.kepu.util.StringUtil;



@Service
public class RedisServiceImpl implements com.kepu.service.RedisService {

	@Autowired
	private JedisClient jedisClient;
	@Override
	public Boolean checkSmsCode(String mobile, String pin,int type) {
		
		String real="";
		if(type==1)
			real=jedisClient.get("sms_"+mobile);
		else if(type==2)
			real=jedisClient.get("sms_find_password_"+mobile);
		if(StringUtil.isEmpty(real))
			return false;
		return real.equals(pin);
	}
	@Override
	public void set(String key, String value,Integer second) {
		jedisClient.set(key, value);
		if(second!=null)
			jedisClient.expire(key, second);
	}

}
