package com.kepu.service;

import java.util.Map;

import com.kepu.pojo.StLink;


public interface LinkService {

	Map<String,Object> getLinkList(int page,int size);
		
	StLink getStLinkById(long linkId);
	
	void deleteLinkById(Long linkId);
	
	
	void save(StLink link);
	
}
