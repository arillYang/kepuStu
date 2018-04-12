package com.kepu.service;


import java.util.Map;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StAuthenticCompany;
import com.kepu.pojo.StAuthenticPeople;
import com.kepu.pojo.StSkillContent;
import com.kepu.pojo.StSkillContent;
import com.kepu.pojo.StTaskContent;

public interface SkillService {
	
	KePuResult authPersonal(StAuthenticPeople sap);
	
	KePuResult authCompany(StAuthenticCompany sac);
	
	KePuResult getAuthStatus(Integer userId);
	
	KePuResult getClassify();
	
	Boolean checkAuth(Integer userId,Integer type); // type=1 个人 type=2 企业
	
	// 发布技能
	KePuResult releaseSkill(StSkillContent skillContent);  
	
	KePuResult getCarousel(Integer type);
	
	KePuResult getskillDetail(Integer skillId);
	
	// 筛选条件
	KePuResult getSkillList(Integer page,Integer size,Map<String, String> map);
	
	StSkillContent getSkillContentById(Integer skillId);
	
	int saveSkillContent(StSkillContent skillContent);
	
	// 发布任务
    KePuResult releaseTask(StTaskContent stTaskContent);  
    
    // 筛选条件
 	KePuResult getTaskList(Integer page,Integer size,Map<String, String> map);
 	
 	KePuResult getTaskDetail(Integer taskId);
 	
 	StTaskContent getTaskContentById(Integer taskId);
 	
 	int saveTaskContent(StTaskContent stTaskContent);
 	
	KePuResult getMySkill(Integer userId,Integer page, Integer size); 
 	
	KePuResult getMyTask(Integer userId,Integer page, Integer size); 
}
