package com.kepu.service;

import java.util.List;
import java.util.Map;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StActivityRecord;
import com.kepu.pojo.StDevice;
import com.kepu.pojo.StUser;







public interface SysService {
	
	String getLaunchPage();
	
	String getAboutUs();
	
	void insertDeviceMessage(StDevice stDevice);
	
	//  举报评论
	KePuResult reportNewsComment(Integer userId,Long commentId);
	
	//  手机归属地开关
	String getParam(String param);
	
	void addHotSearch(String query,Integer type); //1:技能 2：任务 3出租出售 4求租求购
	
	KePuResult getHotSearch(Integer type);
	
	KePuResult deletePublish(Integer type,Integer typeId,Integer userId);
	
	KePuResult getComment(Integer type,Integer uid,Integer userId, Integer page, Integer size);
	
	String checkPraise(Integer type,Integer userId,Integer typeId,Integer fromType);
	
	KePuResult sentComment(StUser user,Integer uid,String comment,Integer fromType);
	
	Boolean checkExist(Integer fromType,Integer uid);
	
	KePuResult replyComment(StUser user,Integer commentId,String comment);
	
	KePuResult getCommentReply(Integer commentId,Integer userId,Integer page,Integer size);
	
	Boolean checkComment(Integer commentId);
	
	KePuResult praise(Integer type,Integer typeId,Integer userId);
	
	// 活动积分统计
	int  insertActivityRecord(StUser user,StActivityRecord record,int type,String message);
	
	Double getActivityScore(Integer userId);
	
	String getParameter(String key);
	
	List<Map<String,String>> getLinkMapByType(Integer type);
}
