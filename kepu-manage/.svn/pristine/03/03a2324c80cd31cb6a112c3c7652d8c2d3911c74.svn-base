package com.kepu.service;

import java.util.List;
import java.util.Map;

import com.kepu.pojo.PageBean;
import com.kepu.pojo.StVillageNews;
import com.kepu.pojo.StVillageNewsContent;

public interface VillageNewsService {

	// 根据权限获取
	Map<String, Object> findStVillageNews(PageBean pageBean,StVillageNews news,Integer town,Integer village);
	
	StVillageNews getVillageNewsById(Integer newsId);
	
	StVillageNewsContent getVillageNewsContent(Integer newsId);
	
	void deleteStVillageNewsById(Integer newsId);
	
	void saveNews(StVillageNews news,String realcontent,String mycontent,String town,
			String[] villageIds);
	
	Integer getTownIdByNewsId(Integer newsId);
	
	List<Integer> getVillageIds(Integer townId);
	
	List<Integer> getVillageNewsIdsByVillageId(Integer village);
	
	List<Integer>  getVillageIdsBelongByNewsId(Integer newsId);
	
	List<Integer> getVillageNewsIdsByTownId(Integer townId);
}
