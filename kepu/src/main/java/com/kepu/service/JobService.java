package com.kepu.service;




import java.util.Map;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StJobApply;
import com.kepu.pojo.StJob;
import com.kepu.pojo.StJobApply;

public interface JobService {
	
	KePuResult getClassify();
	
	// 发布招聘
    KePuResult releaseJob(StJob job);
    
    KePuResult getJobDetail(Integer jobId);
    
    // 筛选条件
 	KePuResult getJobList(Integer page,Integer size,Map<String, String> map);
 	
 	String  getAuthCompanyName(Integer userId);
 	
 	StJob getJobById(Integer jobId);
 	
 	int saveJob(StJob stJob);
 	
 	// 发布求职
    KePuResult releaseApply(StJobApply apply);
    
    // 筛选条件
  	KePuResult getApplyList(Integer page,Integer size,Map<String, String> map);
  	
  	KePuResult getApplyDetail(Integer applyId);
  	
  	StJobApply getApplyById(Integer applyId);
  	
  	int saveApply(StJobApply apply); 
  	
  	KePuResult getCarousel();
}
