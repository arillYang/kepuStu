package com.kepu.service;

import java.util.Date;
import java.util.List;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StCommunity;
import com.kepu.pojo.StUser;
import com.kepu.pojo.community.TeachResult;

public interface CommunityService {
	
	
	KePuResult getCarousel(Integer userId,Integer total);
	
	KePuResult getAllCommunity(Integer userId,Integer page,Integer size,String query);
	
	KePuResult getMyCommunity(Integer userId,Long timestamp);
	
	List<Integer> getMyJoinCommunityId(Integer userId);
	
	KePuResult joinCommunity(Integer userId,Integer communityId);
	
	KePuResult applyCommunity(Integer userId,Integer communityId,String userName,String mobile,String IDcardZ,
			String IDcardF,String position,String credential,String sex,String birthday,String photo,
			String career,String positional,String education,String selfIntroduction,String IDcardZHold);
	
	KePuResult quitCommunity(Integer userId,Integer communityId);
	
	KePuResult  publishArticle(Integer userId,Integer communityId,String content,String detailPics);
	
	KePuResult getArticle(Integer userId,Integer communityId,Integer page,Integer size);
	
	
	KePuResult getMyArticle(Integer userId,Integer page,Integer size,Integer type);
	
	KePuResult dpArticle(Integer articleId,Integer userId,Integer operate);
	
	KePuResult getCommunityDetail(Integer userId,Integer communityId,Date timestamp);
	
	KePuResult getArticleDetail(Integer articleId,Integer userId);
	
	StCommunity getCommunityByArticleId(Integer articleId);
	
	Integer getCommunityIdByArticleId(Integer articleId);
	
	StCommunity getCommunityByCommentId(Integer commentId);
	
	Integer getCommunityIdByCommentId(Integer commentId);
	
	StCommunity getCommunityByNoticeId(Integer noticeId);
	
	StCommunity getCommunityByTeachId(Integer teachId);
	
	KePuResult sentComment(StUser user,Integer articleId,String comment);
	
	KePuResult replyComment(StUser user,Integer commentId,String comment);
	
	KePuResult getNoticeList(Integer communityId,Integer page,Integer size);
	
	KePuResult  publishNotice(Integer userId,Integer communityId,String title,String auchor,String content);
	
	KePuResult getNoticeDetail(Integer noticeId);
	
	KePuResult getTeachList(Integer communityId,Integer page,Integer size);
	
	KePuResult  publishTeach(Integer userId,Integer communityId,String title,List<TeachResult> teachList);
	
	KePuResult getTeachDetail(Integer teachId);
	
	KePuResult getMyRemind(Integer userId,Integer communityId,Date timestamp);
	
	Boolean whetherMyJoin(Integer userId,Integer communityId);
	
	Boolean whetherAdmin(Integer userId,Integer communityId);
	
	void addHotSearch(String query);
	
	KePuResult getHotSearch();
	
	KePuResult editArticle(Integer userId,Integer articleId,String content,String detailPics);
	
	KePuResult deleteArticle(Integer userId,Integer articleId);
	
	 
}
