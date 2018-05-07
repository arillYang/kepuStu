package com.kepu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.mapper.StLinkMapper;
import com.kepu.pojo.StLink;
import com.kepu.pojo.StLinkExample;
import com.kepu.service.LinkService;

@Service
public class LinkServiceImpl implements LinkService {

	@Autowired
	private StLinkMapper linkMapper;
	@Override
	public Map<String, Object> getLinkList(int page, int size) {
		PageHelper.startPage(page, size);
		Map<String,Object> map=new HashMap<String, Object>();
		StLinkExample example=new StLinkExample();
		example.setOrderByClause("id desc");
		StLinkExample.Criteria criteria=example.createCriteria();
		List<StLink> list = linkMapper.selectByExample(example);
		PageInfo<StLink> pageInfo=new PageInfo<StLink>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}
	@Override
	public StLink getStLinkById(long linkId) {
		return linkMapper.selectByPrimaryKey(linkId);
	}
	@Override
	public void deleteLinkById(Long LinkId) {
		linkMapper.deleteByPrimaryKey(LinkId);	
	}
	@Override
	public void save(StLink link) {
		if(link!=null){
			link.setHotTime(new Date());
			if(link.getId()!=null){
				linkMapper.updateByPrimaryKeySelective(link);
			}else{
				linkMapper.insertSelective(link);
			}
		}
		
	}
	

}
