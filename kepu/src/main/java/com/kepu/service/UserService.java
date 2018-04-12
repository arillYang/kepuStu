package com.kepu.service;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StLog;
import com.kepu.pojo.StUser;

public interface UserService {
	KePuResult createUser(String account,String mobile,String password,Integer area,String address,String mac,String appVersion);
	
	Boolean checkMobile(String mobile);
	
	Boolean checkAccount(String account);
	
	Boolean checkMac(String mac);
	
	KePuResult login(String account,String password,String appVersion);
	
	KePuResult logout(String token);
	
	KePuResult resetPassword(String mobile,String newpassword);
	
	StUser getUserByToken(String token);
	
	StUser getUserById(Integer userId);
	
	void updateStUser(StUser user,String token);
	
	KePuResult sentComment(StUser user,Integer newsId,String comment);
	
	KePuResult villageSentComment(StUser user,Integer newsId,String comment);
	
	KePuResult noticeSentComment(StUser user,Integer newsId,String comment);
	
	KePuResult replyComment(StUser user,Long commentId,String comment);
	
	KePuResult villageReplyComment(StUser user,Long commentId,String comment);
	
	KePuResult noticeReplyComment(StUser user,Long commentId,String comment);
	
	KePuResult likeNews(Integer newsId,Integer userId);
	
	KePuResult deletelikeNews(String[] likeNewsIds,Integer userId);
	
	KePuResult getMyLikeNews(Integer userId,Integer page,Integer size);
	
	KePuResult resetMyPassword(Integer userId,String password,String newPassword);
	
	KePuResult sentAdvice(Integer userId,String comment);
	
    // ����������/�ظ� ����
	KePuResult praise(Integer type,Long typeId,Integer userId);
	
	//  �����ŵ���/��ϲ��
	KePuResult dpNews(Integer newsId,Integer type,Integer userId,Integer operate);
	
	void saveUser(StUser user);
	
	void saveLog(StLog log);
	
	KePuResult share(Integer type,Integer typeId,Integer userId);

	
}
