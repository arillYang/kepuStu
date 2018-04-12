package com.kepu.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;



public class LotteryUtil {
	
	private static final Logger LOG = Logger.getLogger(LotteryUtil.class);
	
	public static void main(String[] args) throws Exception {
		Map<String,Object> l=getLottery("李四","111112233","#汽车#111111111","http://www.qq.com","2017年现场抽奖活动第一期");
		System.out.println(l.get("code"));
		System.out.println(l.get("message"));
	}

	public static Map<String,Object> getLottery(String nickname,String phone,String comment,String path,String keyword) {
		Map<String,String> params=new HashMap<String, String>();
		params.put("nickname",nickname);
		params.put("phone", phone);
		params.put("comment",comment);
		params.put("path", path);
		params.put("keyword", keyword);
		String result=null;
		try {
			result = FindMobileAddress.net("http://120.25.96.236/api/add", params, "POST");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> r=JsonUtils.jsonToPojo(result, Map.class);
		int code=(int) r.get("code");
		String message=(String) r.get("message");
		LOG.info(phone+":状态"+code+";信息:"+message);
		return r;
	}
}
