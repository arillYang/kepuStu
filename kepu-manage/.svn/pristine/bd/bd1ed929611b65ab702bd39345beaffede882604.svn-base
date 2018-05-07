package com.kepu.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StAdvice;
import com.kepu.pojo.StClassifyStatistic;
import com.kepu.pojo.StPush;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.TRole;
import com.kepu.pojo.TUser;

public interface UserService {
	KePuResult createUser(String account,String mobile,String password,Integer area,String address);
	
	Boolean checkMobile(String mobile);
	
	Boolean checkAccount(String account);
	
	KePuResult login(String account,String password);
	
	KePuResult logout(String token);
	
	KePuResult resetPassword(String mobile,String newpassword);
	
	StUser getUserByToken(String token);
	
	StUser getUserById(Integer userId);
	
	void updateStUser(StUser user,String token);
	
	KePuResult sentComment(StUser user,Integer newsId,String comment);
	
	KePuResult replyComment(StUser user,Long commentId,String comment);
	
	KePuResult likeNews(Integer newsId,Integer userId);
	
	KePuResult deletelikeNews(String[] likeNewsIds,Integer userId);
	
	KePuResult getMyLikeNews(Integer userId,Integer page,Integer size);
	
	KePuResult resetMyPassword(Integer userId,String password,String newPassword);
	
	KePuResult sentAdvice(Integer userId,String comment);
	
	KePuResult praise(Integer type,Long typeId,Integer userId);
	
	void saveUser(StUser user);
	
	Map<String,Object>  findStUser(PageBean pageBean,StUser stUser);
	
	Map<String,Object>  findStAdvice(PageBean pageBean,StAdvice advice);
	
	List<StVillage> getAddressByParent(Integer parentId);
	
	Map<String, Object> getStatisticByTownId(PageBean pageBean,String townId);
	
	Map<String, Object> getStatistic(PageBean pageBean);
	
	Map<String,Object> getPushRecord(PageBean pageBean,StPush push,Integer townId,Integer villageId);
	
	void savePushRecord(StPush push);
	
	List<String> getPushCids(List<Integer> towns,List<Integer> countrys);
	
	TUser login(TUser user);
	
	void saveTUser(TUser tUser);
	
	void deleteTUserById(Integer tUserId,Integer type);
	
	Map<String,Object>  findTUser(PageBean pageBean,TUser tUser);
	
	TUser getTuserById(Integer tUserId);
	
	Map<String, Object> getCountySS(Date d1,Date d2,Integer c1,Integer c2,PageBean pageBean,Integer townId);
	
	Map<String, Object> getTownSS(Date d1,Date d2,Integer c1,Integer c2,PageBean pageBean);
	
	Map<String, Object> getPeopleSS(Date d1,Date d2,Integer c1,Integer c2,PageBean pageBean,Integer villageId);
	
	Map<String, Object> getClickTown(Date d1,Date d2,PageBean pageBean);
	
	Map<String, Object> getClickCounty(Date d1,Date d2,PageBean pageBean,Integer townId);
	
	Map<String, Object> getClickPeople(Date d1,Date d2,PageBean pageBean,Integer village);
	
	// 用户具体点击
	Map<String, Object> getReadDetail(Date d1,Date d2,String query);
	
	// 用户阅读喜好
	Map<String, Object> getDetail(Date d1,Date d2,Integer townId);
	
	// 作者统计
	Map<String, Object> getAuthorStatistic(Date d1,Date d2,String name,PageBean pageBean);
	
	// 新闻分类统计
    Map<String, Object> getClassifyStatistic(String name,PageBean pageBean);
    
    List<StClassifyStatistic> getWeekClassifyStatistic(String name,int year,int week);
    
    Map<String, Object> getMyWeekClassifyStatistic(String name,int year,int week,int year2,int week2);
	
	Map<Integer,Integer> getActiveTown();
	
	Map<Integer,Integer> getActiveArea(Integer townId);
	
	Map<Integer,Integer> getAllActiveNum(Integer townId);
	
	Map<String,Object> getMenu(Integer roleId);
	
	List<TRole> getRoleList();
	
	// 角色管理
	void saveRole(TRole role,List<Integer> menuIds);
	
	int deleteRoleById(Integer roleId);
	
	Map<String,Object>  findRole(PageBean pageBean,TRole role);
	
	TRole getTRoleById(Integer roleId);
	
	String getMenusByRoleId(Integer roleId);
	
	List<String> getMenuUrlsByRoleId(Integer roleId);
	
	Integer getPeopleNum(Integer townId,Integer area);
	
	// 每周统计下分类新闻阅读数
	void readStatistics()throws Exception;
	
	
	/**
	 * 会员信息的修改
	 */
	int updateByPrimaryKey(StUser stUser);
	
}
