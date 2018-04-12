package com.kepu.service;

import java.util.List;

import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageNews;

public interface VillageService {
	
	List<StVillage> getPicture(Integer townId);
	
	String getVillageMessage(Integer villageId);
	
	List<Integer> getAllTownId();
	
	KePuResult getCarousel(Integer total,Integer type);
	
	KePuResult getNews(Integer type,Integer page,Integer size);
	
	// ��ѯ��ID����������ID
	List<Integer> findNewsIdsByVillageId(Integer villageId);
	
	// ��ѯ����ID����������ID
	List<Integer> findNewsIdsByTownId(Integer townId);
	
	StVillageNews getStickNews(Integer villageId);
	
	StVillageNews getStickTownNews(Integer townId);
	
	// ���ݰ汾
	KePuResult  getNewsDetail(Integer userId,Integer newsId,String appVersion);
	
	KePuResult getNewsComment(Integer newsId,Integer userId,Integer page,Integer size);
	
	Boolean checkNews(Integer newsId);
	
	Boolean checkComment(Long commentId);
	
	String checkPraise(Integer type,Integer userId,Long typeId);
	
	KePuResult getCommentReply(Long commentId,Integer userId,Integer page,Integer size);
	
	KePuResult likeNews(Integer newsId,Integer userId);
	
	KePuResult deletelikeNews(String[] likeNewsIds,Integer userId);
	
	KePuResult getMyLikeNews(Integer userId,Integer page,Integer size);
	
	// �ٱ�����
	KePuResult reportNewsComment(Integer userId,Long commentId);
	
	// ��������������/�ظ� ����
	KePuResult praise(Integer type,Long typeId,Integer userId);
	
	//  ���������ŵ���/��ϲ��
	KePuResult dpNews(Integer newsId,Integer type,Integer userId,Integer operate);
	
	
	KePuResult searchNews(String query,Integer page,Integer size);
	
	void addHotSearch(String query);
	
	KePuResult getHotSearch();
	
	//  ��ѯ����������ź͹���
	KePuResult searchVillageTotal(String query,Integer page,Integer size);
	
	StVillage getVillageNameById(Integer villageId);
	
	Integer getParentIdByCountyId(Integer villageId);
	
	String getVillageName(Integer villageId);
	
	List<StVillage> getAddressByParent(Integer parentId);
}