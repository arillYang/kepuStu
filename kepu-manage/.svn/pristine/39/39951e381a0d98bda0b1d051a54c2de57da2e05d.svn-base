package com.kepu.service;

import java.util.List;
import java.util.Map;

import com.kepu.pojo.PageBean;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageContent;
import com.kepu.pojo.StVillageContentWithBLOBs;
import com.kepu.pojo.StVillageNews;
import com.kepu.pojo.StVillageNewsContent;

public interface VillageService {

	Map<String, Object> findStVillage(PageBean pageBean,StVillage village,Integer parent);
	
	StVillage getVillageById(Integer Id);
	
	StVillageContentWithBLOBs getVillageContent(Integer villageId);
	
	void deleteStVillageById(Integer Id);
	
	void saveVillage(StVillage village,String realcontent,String mycontent);
	
	Integer getTownIdByNewsId(Integer newsId);
	
	List<Integer> getVillageIds(Integer townId);
}
