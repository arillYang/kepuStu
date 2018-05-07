package com.kepu.service;

import java.util.List;
import java.util.Map;

import com.kepu.pojo.PageBean;
import com.kepu.pojo.StNoticeNews;
import com.kepu.pojo.StNoticeNewsContent;

public interface NoticeNewsService {

	Map<String, Object> findNoticeNews(PageBean pageBean,StNoticeNews news,Integer town,Integer village);
	
	StNoticeNews getStNoticeNewsById(Integer newsId);
	
	StNoticeNewsContent getStNoticeNewsContent(Integer newsId);
	
	void deleteStNoticeNewsById(Integer newsId);
	
	void saveNews(StNoticeNews news,String realcontent,String mycontent,String town,String[] villageIds);
	
	Integer getTownIdByNewsId(Integer newsId);
	
	List<Integer>  getVillageIdsBelongByNewsId(Integer newsId);
}
