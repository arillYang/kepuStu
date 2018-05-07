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
import com.kepu.mapper.StVillageMapper;
import com.kepu.mapper.StVillageNewsContentMapper;
import com.kepu.mapper.StVillageNewsMapper;
import com.kepu.mapper.StVillageNewsRelationMapper;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StNewsContent;
import com.kepu.pojo.StNewsExample;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageExample;
import com.kepu.pojo.StVillageNews;
import com.kepu.pojo.StVillageNewsContent;
import com.kepu.pojo.StVillageNewsExample;
import com.kepu.pojo.StVillageNewsRelation;
import com.kepu.pojo.StVillageNewsRelationExample;
import com.kepu.service.VillageNewsService;
import com.kepu.util.ImgHelperUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.StringUtil;

@Service
public class VillageNewsServiceImpl implements VillageNewsService{

	@Autowired
	private StVillageNewsMapper villageNewsMapper;
	@Autowired
	private StVillageNewsContentMapper villageNewsContentMapper;
	@Autowired
	private StVillageNewsRelationMapper villageNewsRelationMapper;
	@Autowired
	private StVillageMapper villageMapper;
	@Override
	public Map<String, Object> findStVillageNews(PageBean pageBean,StVillageNews news,Integer town,Integer village) {
		Map<String,Object> map=new HashMap<String, Object>();
		StVillageNewsExample example=new StVillageNewsExample();
		example.setOrderByClause("updateTime desc");
		StVillageNewsExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		Map<String,Object> param=new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(news.getTitle()))
			criteria.andTitleLike("%"+news.getTitle()+"%");
		if(town!=0&&village==0){
			List<Integer> r = getVillageNewsIdsByTownId(town);
			if(r.size()!=0){
				criteria.andUidIn(r);
				param.put("uids", r);
			}
		}
		else if(village!=0){
			List<Integer> r =getVillageNewsIdsByVillageId(village);
			if(r.size()!=0){
				criteria.andUidIn(r);
				param.put("uids", r);
			}
		}
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		List<StVillageNews> list = villageNewsMapper.selectByExample(example);
		PageInfo<StVillageNews> pageInfo=new PageInfo<StVillageNews>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		int viewCount=villageNewsMapper.getTotalViewNumber(param)==null?0:villageNewsMapper.getTotalViewNumber(param);
		map.put("viewCount", viewCount);
		return map;
	}

	@Override
	public StVillageNews getVillageNewsById(Integer newsId) {
		return villageNewsMapper.selectByPrimaryKey(newsId);
	}

	@Override
	public StVillageNewsContent getVillageNewsContent(Integer newsId) {
		return villageNewsContentMapper.selectByPrimaryKey(newsId);
	}

	@Override
	public void deleteStVillageNewsById(Integer newsId) {
		villageNewsMapper.deleteByPrimaryKey(newsId);
		villageNewsContentMapper.deleteByPrimaryKey(newsId);
	}

	@Override
	public void saveNews(StVillageNews news, String realcontent,
			String mycontent,String town,String[] villageIds) {
		if(news!=null){
			if(news.getUid()!=null){
				if(StringUtil.isEmpty(news.getNewsimages()))
					news.setNewsimages(null);
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
								temp.put("contentIndex",i+++"");
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
						news.setContent(content);
				}
				news.setUpdatetime(new Date());
				villageNewsMapper.updateByPrimaryKeySelective(news);
				Integer newsId=news.getUid();
				StVillageNewsContent c = villageNewsContentMapper.selectByPrimaryKey(newsId);
				c.setContent(realcontent);
				c.setUpdatetime(new Date());
				villageNewsContentMapper.updateByPrimaryKeySelective(c);
				//  
				StVillageNewsRelationExample e=new StVillageNewsRelationExample();
				StVillageNewsRelationExample.Criteria cc =e.createCriteria();
				cc.andNewsidEqualTo(newsId);
				villageNewsRelationMapper.deleteByExample(e);
				//  
				if(villageIds!=null){
					for (String string : villageIds) {
						StVillageNewsRelation r=new StVillageNewsRelation();
						r.setNewsid(newsId);
						r.setVillageid(Integer.valueOf(string));
						villageNewsRelationMapper.insert(r);
					}
				}else{
					StVillageExample example=new StVillageExample();
					StVillageExample.Criteria criteria=example.createCriteria();
					criteria.andParentEqualTo(StringUtil.isEmpty(town)?1:Integer.valueOf(town));
					List<StVillage> l = villageMapper.selectByExample(example);
					for (StVillage stVillage : l) {
						StVillageNewsRelation r=new StVillageNewsRelation();
						r.setNewsid(newsId);
						r.setVillageid(stVillage.getId());
						villageNewsRelationMapper.insert(r);
					}
				}
				
				
			}else{
				news.setCreatetime(new Date());
				news.setUpdatetime(new Date());
			//  
				if(StringUtil.isNotEmpty(mycontent)){
					mycontent=mycontent.replaceAll("<img", "<END><img");
					mycontent=mycontent.replaceAll("/>", "/><END>");
					mycontent=mycontent.replaceAll("\r\n", "<br/>");
					mycontent=mycontent.replaceAll("\t", "");
					mycontent=mycontent.replaceAll("<br/><br/><br/>", "<br/>");
					//String regEx="<img.*src=(.*?)[^>]*?>";
					/*Pattern p=Pattern.compile(regEx);
					Matcher m=p.matcher(mycontent);*/
					/*while(m.find()){
						System.out.println(m.group());
					}*/
						String[] cc=mycontent.split("<END>");
						/*for (String string : cc) {
								System.out.println(string);
						}*/
						String content="";
						List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
						Map<String,String> temp;
						int i=1;
						for (String string : cc) {
							if(string.length()>0){
								temp=new HashMap<String, String>();
								temp.put("contentIndex",i+++"");
								int type=string.indexOf("<img")>=0?0:1;
								temp.put("contentType",type+"");
								if(type==0){
									List<String> list = ImgHelperUtil.getImgSrcList(string);
									if(list.size()>0)
										string=list.get(0);
								}
								temp.put("contentNews", string);
								myList.add(temp);
							}
						}
						content=JsonUtils.objectToJson(myList);
						news.setContent(content);
				}
				villageNewsMapper.insertSelective(news);
				Integer newsId=news.getUid();
				StVillageNewsContent c =new StVillageNewsContent();
				c.setNewsid(newsId);
				c.setContent(realcontent);
				c.setUpdatetime(new Date());
				c.setCreatetime(new Date());
				villageNewsContentMapper.insert(c);
				//  
				if(villageIds!=null){
					for (String string : villageIds) {
						StVillageNewsRelation r=new StVillageNewsRelation();
						r.setNewsid(newsId);
						r.setVillageid(Integer.valueOf(string));
						villageNewsRelationMapper.insert(r);
					}
				}else{
					StVillageExample example=new StVillageExample();
					StVillageExample.Criteria criteria=example.createCriteria();
					criteria.andParentEqualTo(StringUtil.isEmpty(town)?1:Integer.valueOf(town));
					List<StVillage> l = villageMapper.selectByExample(example);
					for (StVillage stVillage : l) {
						StVillageNewsRelation r=new StVillageNewsRelation();
						r.setNewsid(newsId);
						r.setVillageid(stVillage.getId());
						villageNewsRelationMapper.insert(r);
					}
				}
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

	@Override
	public List<Integer> getVillageNewsIdsByVillageId(Integer village) {
		StVillageNewsRelationExample example=new StVillageNewsRelationExample();
		StVillageNewsRelationExample.Criteria criteria=example.createCriteria();
		criteria.andVillageidEqualTo(village);
		List<StVillageNewsRelation> r = villageNewsRelationMapper.selectByExample(example);
		LinkedList<Integer> list=new LinkedList<Integer>();
		for (StVillageNewsRelation stVillageNewsRelation : r) {
			list.add(stVillageNewsRelation.getNewsid());
		}
		return list;
	}

	@Override
	public List<Integer> getVillageNewsIdsByTownId(Integer townId) {
		StVillageNewsRelationExample example=new StVillageNewsRelationExample();
		StVillageNewsRelationExample.Criteria criteria=example.createCriteria();
		List<Integer> vs = getVillageIds(townId);
		criteria.andVillageidIn(vs);
		List<StVillageNewsRelation> r = villageNewsRelationMapper.selectByExample(example);
		LinkedList<Integer> list=new LinkedList<Integer>();
		for (StVillageNewsRelation stVillageNewsRelation : r) {
			list.add(stVillageNewsRelation.getNewsid());
		}
		return list;
	}

	@Override
	public List<Integer> getVillageIdsBelongByNewsId(Integer newsId) {
		StVillageNewsRelationExample example=new StVillageNewsRelationExample();
		StVillageNewsRelationExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		List<StVillageNewsRelation> r = villageNewsRelationMapper.selectByExample(example);
		LinkedList<Integer> list=new LinkedList<Integer>();
		for (StVillageNewsRelation stVillageNewsRelation : r) {
			list.add(stVillageNewsRelation.getVillageid());
		}
		return list;
	}
}
