package com.kepu.service;

import java.util.List;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StNoticeNews;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageNews;

public interface NoticeService {
	
	
	KePuResult getCarousel(Integer total,Integer type);
	
	KePuResult getNews(Integer type,Integer page,Integer size);
	
	// 查询村ID的所有新闻ID
	List<Integer> findNewsIdsByVillageId(Integer villageId);
	
	// 查询乡镇ID的所有新闻ID
	List<Integer> findNewsIdsByTownId(Integer townId);
	
	StNoticeNews getStickNews(Integer villageId);
	
	StNoticeNews getStickTownNews(Integer townId);
	
	// 兼容版本
	KePuResult  getNewsDetail(Integer userId,Integer newsId,String appVersion);
	
	KePuResult getNewsComment(Integer newsId,Integer userId,Integer page,Integer size);
	
	Boolean checkNews(Integer newsId);
	
	Boolean checkComment(Long commentId);
	
	String checkPraise(Integer type,Integer userId,Long typeId);
	
	KePuResult getCommentReply(Long commentId,Integer userId,Integer page,Integer size);
	
	KePuResult likeNews(Integer newsId,Integer userId);
	
	KePuResult deletelikeNews(String[] likeNewsIds,Integer userId);
	
	KePuResult getMyLikeNews(Integer userId,Integer page,Integer size);
	
	// 举报评论
	KePuResult reportNewsComment(Integer userId,Long commentId);
	
	// 对乡镇新闻评论/回复 点赞
	KePuResult praise(Integer type,Long typeId,Integer userId);
	
	//  对乡镇新闻点赞/不喜欢
	KePuResult dpNews(Integer newsId,Integer type,Integer userId,Integer operate);
	
	
	KePuResult searchNews(String query,Integer page,Integer size);
	
	void addHotSearch(String query);
	
	KePuResult getHotSearch();
}
