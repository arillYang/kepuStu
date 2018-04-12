package com.kepu.service;

import java.util.List;
import java.util.Map;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StNewsDetail;

public interface NewService {
	
	KePuResult getTopnav();
	
	KePuResult getCarousel(Integer total,Integer type);
	
	KePuResult getNews(Integer type,Integer page,Integer size);
	
	KePuResult getCommend();
	
	KePuResult getQuestion(Integer newsId);
	
	KePuResult submitAnswer(List<Map<String,String>> answerList,Integer userId);
	
	StNews getStickNews(Integer type);
	
	// ºÊ»›∞Ê±æ
	KePuResult  getNewsDetail(Integer userId,Integer newsId,String appVersion);
	
	List<StNewsDetail> getNewsDetailById(Integer newsId);
	
	
	KePuResult getNewsComment(Integer newsId,Integer userId,Integer page,Integer size);
	
	KePuResult getCommentReply(Long commentId,Integer userId,Integer page,Integer size);
	
	String checkPraise(Integer type,Integer userId,Long typeId);
	
	Boolean checkNews(Integer newsId);
	
	Boolean checkComment(Long commentId);
	
	KePuResult searchNews(String query,Integer page,Integer size);
	
	void addHotSearch(String query);
	
	KePuResult getHotSearch();
	
	List<Integer> getNewsIdsByType(Integer type);
	
	Boolean hasRecord(Integer newsId,Integer userId,Integer style);
	
	Boolean hasComment(Integer newsId,Integer userId,Integer style);
}
