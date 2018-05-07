package com.kepu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.mapper.StVillageContentMapper;
import com.kepu.mapper.StVillageMapper;
import com.kepu.mapper.StVillageNewsContentMapper;
import com.kepu.mapper.StVillageNewsMapper;
import com.kepu.mapper.StVillageNewsRelationMapper;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StNewsContent;
import com.kepu.pojo.StNewsExample;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageContent;
import com.kepu.pojo.StVillageContentWithBLOBs;
import com.kepu.pojo.StVillageExample;
import com.kepu.pojo.StVillageNews;
import com.kepu.pojo.StVillageNewsContent;
import com.kepu.pojo.StVillageNewsExample;
import com.kepu.pojo.StVillageNewsRelation;
import com.kepu.pojo.StVillageNewsRelationExample;
import com.kepu.service.VillageNewsService;
import com.kepu.service.VillageService;
import com.kepu.util.ImgHelperUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.StringUtil;

@Service
public class VillageServiceImpl implements VillageService{

	@Autowired
	private StVillageMapper villageMapper;
	@Autowired
	private StVillageContentMapper villageContentMapper;
	@Autowired
	private StVillageNewsRelationMapper villageNewsRelationMapper;
	@Override
	public Map<String, Object> findStVillage(PageBean pageBean,StVillage village,Integer parent) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StVillageExample example=new StVillageExample();
		StVillageExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(village.getName()))
			criteria.andNameLike("%"+village.getName()+"%");
		criteria.andParentEqualTo(parent);
		List<StVillage> list = villageMapper.selectByExample(example);
		PageInfo<StVillage> pageInfo=new PageInfo<StVillage>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StVillage getVillageById(Integer Id) {
		return villageMapper.selectByPrimaryKey(Id);
	}

	@Override
	public StVillageContentWithBLOBs getVillageContent(Integer villageId) {
		return villageContentMapper.selectByPrimaryKey(villageId);
	}

	@Override
	public void deleteStVillageById(Integer villageId) {
		villageMapper.deleteByPrimaryKey(villageId);
		villageContentMapper.deleteByPrimaryKey(villageId);
	}

	@Override
	public void saveVillage(StVillage village,String realcontent,String mycontent) {
		if(village!=null){
			if(village.getId()!=null){
				if(StringUtil.isEmpty(village.getPic()))
					village.setPic(null);
				villageMapper.updateByPrimaryKeySelective(village);
				Integer villageId=village.getId();
				StVillageContentWithBLOBs c = villageContentMapper.selectByPrimaryKey(villageId);
				if(StringUtil.isNotEmpty(mycontent)){
					mycontent=mycontent.replaceAll("<img", "<END><img");
					mycontent=mycontent.replaceAll("<embed", "<END><embed");
					mycontent=mycontent.replaceAll("/>", "/><END>");
					mycontent=mycontent.replaceAll("\r\n", "<br/>");
					mycontent=mycontent.replaceAll("\t", "");
					mycontent=mycontent.replaceAll("<br/><br/><br/>", "<br/>");
					String[] cc=mycontent.split("<END>");
						
						String content="";
						List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
						Map<String,String> temp;
						int i=1;
						for (String string : cc) {
							if(string.length()>0){
								temp=new HashMap<String, String>();
								int type=1;
								if(string.indexOf("<img")>=0)
									type=0;
								if(string.indexOf("<embed")>=0)
									type=2;
								temp.put("contentType",type+"");
								if(type==0){
									List<String> list = ImgHelperUtil.getImgSrcList(string);
									if(list.size()>0)
										string=list.get(0);
								}else if(type==2){
									List<String> list = ImgHelperUtil.getVedioSrcList(string);
									if(list.size()>0)
										string=list.get(0);
								}
								temp.put("contentNews", string);
								myList.add(temp);
							}
						}
						content=JsonUtils.objectToJson(myList);
						c.setContent(content);
				}
				c.setHtmlcontent(realcontent);
				c.setUpdatetime(new Date());
				villageContentMapper.updateByPrimaryKeySelective(c);
			}else{
				StVillageExample example=new StVillageExample();
				example.setOrderByClause("id desc");
				StVillageExample.Criteria criteria=example.createCriteria();
				criteria.andParentEqualTo(village.getParent());
				List<StVillage> vl = villageMapper.selectByExample(example);
				Integer id=0;
				if(vl.size()!=0){
					id=vl.get(0).getId()+1;
				}else{
					if(village.getParent()==-1)
						id=1;
					else{
						Integer p=village.getParent();
						id=Integer.valueOf(p+"0001");
					}
				}
				village.setId(id);
				villageMapper.insertSelective(village);
				StVillageContentWithBLOBs c =new StVillageContentWithBLOBs();
				c.setVillageid(id);
				if(StringUtil.isNotEmpty(mycontent)){
					mycontent=mycontent.replaceAll("<img", "<END><img");
					mycontent=mycontent.replaceAll("<embed", "<END><embed");
					mycontent=mycontent.replaceAll("/>", "/><END>");
					mycontent=mycontent.replaceAll("\r\n", "<br/>");
					mycontent=mycontent.replaceAll("\t", "");
					mycontent=mycontent.replaceAll("<br/><br/><br/>", "<br/>");
					String[] cc=mycontent.split("<END>");
						
						String content="";
						List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
						Map<String,String> temp;
						int i=1;
						for (String string : cc) {
							if(string.length()>0){
								temp=new HashMap<String, String>();
								int type=1;
								if(string.indexOf("<img")>=0)
									type=0;
								if(string.indexOf("<embed")>=0)
									type=2;
								temp.put("contentType",type+"");
								if(type==0){
									List<String> list = ImgHelperUtil.getImgSrcList(string);
									if(list.size()>0)
										string=list.get(0);
								}else if(type==2){
									List<String> list = ImgHelperUtil.getVedioSrcList(string);
									if(list.size()>0)
										string=list.get(0);
								}
								temp.put("contentNews", string);
								myList.add(temp);
							}
						}
						content=JsonUtils.objectToJson(myList);
						c.setContent(content);
				}
				c.setHtmlcontent(realcontent);
				c.setUpdatetime(new Date());
				villageContentMapper.insertSelective(c);
			}
		}
		
	}

	@Override
	public Integer getTownIdByNewsId(Integer newsId) {
		StVillageNewsRelationExample example=new StVillageNewsRelationExample();
		StVillageNewsRelationExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		List<StVillageNewsRelation> l = villageNewsRelationMapper.selectByExample(example);
		if(l.size()!=0){
			Integer vId=l.get(0).getVillageid();
			StVillage l2 = villageMapper.selectByPrimaryKey(vId);
			if(l2!=null)
				return l2.getParent();
			else
				return -1;
		}
		return -1;
	}
	@Override
	public List<Integer> getVillageIds(Integer townId) {
		StVillageExample example2=new StVillageExample();
		StVillageExample.Criteria criteria2=example2.createCriteria();
		criteria2.andParentEqualTo(townId);
		List<StVillage> l = villageMapper.selectByExample(example2);
		List<Integer> r=new LinkedList<Integer>();
		for (StVillage stVillage : l) {
			r.add(stVillage.getId());
		}
		return r;
	}
}
