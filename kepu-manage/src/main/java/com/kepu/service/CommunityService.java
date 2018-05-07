package com.kepu.service;

import java.util.Map;

import com.kepu.pojo.PageBean;
import com.kepu.pojo.StCommunity;
import com.kepu.pojo.StCommunityApply;
import com.kepu.pojo.StCommunityArticle;
import com.kepu.pojo.StCommunityNotice;
import com.kepu.pojo.StCommunityNoticeExample;
import com.kepu.pojo.StCommunityTeach;
import com.kepu.pojo.StCommunityUser;
import com.kepu.pojo.community.MemberList;

public interface CommunityService {

	Map<String, Object> findStCommunity(PageBean pageBean,StCommunity community);
	
	StCommunity getCommunityById(Integer Id);
	
	StCommunityUser getCommunityUser(Integer community,Integer userId);
	
	void saveCommunityUser(StCommunityUser communityUser);
	
	void deleteStCommunityById(Integer Id);
	
	void saveStCommunity(StCommunity community);
	
	Map<String, Object> findMemberlist(PageBean pageBean,MemberList member);
	
	Map<String, Object> findStCommunityNotice(PageBean pageBean,StCommunityNotice communityNotice);
	
	Map<String, Object> findStCommunityArticle(PageBean pageBean,StCommunityArticle communityArticle,Integer town,Integer village);
	
	Map<String, Object> findStCommunityTeach(PageBean pageBean,StCommunityTeach communityTeach);
	
	StCommunityNotice getCommunityNoticeById(Integer noticeId);
	
	void saveStCommunityNotice(StCommunityNotice communityNotice);
	
	Map<String, Object> findApplylist(PageBean pageBean,StCommunityApply communityApply);
	
	StCommunityApply getStCommunityApplyById(Integer applyId);
	
	void saveStCommunityApply(StCommunityApply communityApply);
	
	int deleteCommunityNotice(Integer noticeId);
	
	int deleteCommunityArticle(Integer articleId);
	
	int deleteCommunityTeach(Integer teachId);
	
}
