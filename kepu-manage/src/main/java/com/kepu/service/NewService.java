package com.kepu.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StClassify;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StNewsContent;
import com.kepu.pojo.StNewsDetail;
import com.kepu.pojo.StNewsQuestion;
import com.kepu.pojo.StNewsTimetask;

public interface NewService {
	
	KePuResult getTopnav();
	
	KePuResult getCarousel(Integer total);
	
	KePuResult getNews(Integer type,Integer page,Integer size);
	
	StNews getStickNews();
	
	KePuResult  getNewsDetail(Integer userId,Integer newsId);
	
	List<StNewsDetail> getNewsDetailById(Integer newsId);
	
	Map<String, Object> findStNews(PageBean pageBean,StNews news);
	
	void deleteStNewsById(Integer newsId);
	
	void cancelNewsById(Integer newsId,Integer type);
	
	KePuResult getNewsComment(Integer newsId,Integer userId,Integer page,Integer size);
	
	KePuResult getCommentReply(Long commentId,Integer userId,Integer page,Integer size);
	
	String checkPraise(Integer type,Integer userId,Long typeId);
	
	Boolean checkNews(Integer newsId);
	
	Boolean checkComment(Long commentId);
	
	KePuResult searchNews(String query,Integer page,Integer size);
	
	void addHotSearch(String query);
	
	KePuResult getHotSearch();
	
	StNews getNewsById(Integer newsId);
	
	List<StClassify> getAllClass();
	
	// 返回newsId
	Integer saveNews(StNews news,String realcontent,String mycontent,String classify,Date publishTime,boolean preView);
	
	StNewsContent getNewsContent(Integer newsId);
	
	//  新闻分类模块
	Map<String, Object> findStClassify(PageBean pageBean,StClassify classify);
	
	List<Integer>  getClassfyByNewsId(Integer newsId);
	
	StClassify  getClassifyById(Integer classifyId);
	
	void saveStClassify(StClassify classify);
	
	int deleteStClassify(Integer classifyId);
	
	// 发布定时新闻
	void publishTask()throws Exception;
	
	StNewsTimetask getTask(Integer newsId);
	
	void deleteNewsTimetask(Integer newsId);
	
	List<StNewsQuestion> getStNewsQuestion(Integer newsId);
	
	int saveStNewsQuestion(StNewsQuestion newsQuestion);
	
	int deleteStNewsQuestion(Integer newsId);
}
