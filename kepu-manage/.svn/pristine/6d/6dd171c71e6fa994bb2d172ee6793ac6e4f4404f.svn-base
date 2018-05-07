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
import com.kepu.mapper.StNoticeNewsContentMapper;
import com.kepu.mapper.StNoticeNewsMapper;
import com.kepu.mapper.StNoticeNewsRelationMapper;
import com.kepu.mapper.StVillageMapper;
import com.kepu.mapper.StVillageNewsContentMapper;
import com.kepu.mapper.StVillageNewsMapper;
import com.kepu.mapper.StVillageNewsRelationMapper;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StNewsContent;
import com.kepu.pojo.StNewsExample;
import com.kepu.pojo.StNoticeNews;
import com.kepu.pojo.StNoticeNewsContent;
import com.kepu.pojo.StNoticeNewsExample;
import com.kepu.pojo.StNoticeNewsRelation;
import com.kepu.pojo.StNoticeNewsRelationExample;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageExample;
import com.kepu.pojo.StVillageNews;
import com.kepu.pojo.StVillageNewsContent;
import com.kepu.pojo.StVillageNewsExample;
import com.kepu.pojo.StVillageNewsRelation;
import com.kepu.pojo.StVillageNewsRelationExample;
import com.kepu.service.NoticeNewsService;
import com.kepu.service.VillageNewsService;
import com.kepu.util.ImgHelperUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.StringUtil;

@Service
public class NoticeNewsServiceImpl implements NoticeNewsService{

	@Autowired
	private StNoticeNewsMapper noticeNewsMapper;
	@Autowired
	private StNoticeNewsContentMapper noticeNewsContentMapper;
	@Autowired
	private StNoticeNewsRelationMapper noticeNewsRelationMapper;
	@Autowired
	private StVillageMapper villageMapper;
	@Autowired
	private VillageNewsService villageNewsService;

	

	@Override
	public Integer getTownIdByNewsId(Integer newsId) {
		StNoticeNewsRelationExample example=new StNoticeNewsRelationExample();
		StNoticeNewsRelationExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		List<StNoticeNewsRelation> l = noticeNewsRelationMapper.selectByExample(example);
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
	public Map<String, Object> findNoticeNews(PageBean pageBean,
			StNoticeNews news,Integer town,Integer village) {
		Map<String,Object> map=new HashMap<String, Object>();
		StNoticeNewsExample example=new StNoticeNewsExample();
		example.setOrderByClause("updateTime desc");
		StNoticeNewsExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		if(StringUtil.isNotEmpty(news.getTitle()))
			criteria.andTitleLike("%"+news.getTitle()+"%");
		if(town!=0&&village==0){
			List<Integer> r = villageNewsService.getVillageNewsIdsByTownId(town);
			if(r.size()!=0)
				criteria.andUidIn(r);
		}
		else if(village!=0){
			List<Integer> r =villageNewsService.getVillageNewsIdsByVillageId(village);
			if(r.size()!=0)
				criteria.andUidIn(r);
		}
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		List<StNoticeNews> list = noticeNewsMapper.selectByExample(example);
		PageInfo<StNoticeNews> pageInfo=new PageInfo<StNoticeNews>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StNoticeNews getStNoticeNewsById(Integer newsId) {
		return noticeNewsMapper.selectByPrimaryKey(newsId);
	}

	@Override
	public StNoticeNewsContent getStNoticeNewsContent(Integer newsId) {
		return noticeNewsContentMapper.selectByPrimaryKey(newsId);
	}

	@Override
	public void deleteStNoticeNewsById(Integer newsId) {
		noticeNewsMapper.deleteByPrimaryKey(newsId);
		noticeNewsContentMapper.deleteByPrimaryKey(newsId);
	}

	@Override
	public void saveNews(StNoticeNews news, String realcontent,
			String mycontent, String town, String[] villageIds) {
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
				noticeNewsMapper.updateByPrimaryKeySelective(news);
				Integer newsId=news.getUid();
				StNoticeNewsContent c = noticeNewsContentMapper.selectByPrimaryKey(newsId);
				c.setContent(realcontent);
				c.setUpdatetime(new Date());
				noticeNewsContentMapper.updateByPrimaryKeySelective(c);
				
				StNoticeNewsRelationExample e=new StNoticeNewsRelationExample();
				StNoticeNewsRelationExample.Criteria cc =e.createCriteria();
				cc.andNewsidEqualTo(newsId);
				noticeNewsRelationMapper.deleteByExample(e);
				if(villageIds!=null){
					for (String string : villageIds) {
						StNoticeNewsRelation r=new StNoticeNewsRelation();
						r.setNewsid(newsId);
						r.setVillageid(Integer.valueOf(string));
						noticeNewsRelationMapper.insert(r);
					}
				}else{
					StVillageExample example=new StVillageExample();
					StVillageExample.Criteria criteria=example.createCriteria();
					criteria.andParentEqualTo(StringUtil.isEmpty(town)?1:Integer.valueOf(town));
					List<StVillage> l = villageMapper.selectByExample(example);
					for (StVillage stVillage : l) {
						StNoticeNewsRelation r=new StNoticeNewsRelation();
						r.setNewsid(newsId);
						r.setVillageid(stVillage.getId());
						noticeNewsRelationMapper.insert(r);
					}
				}
				
			}else{
				news.setCreatetime(new Date());
				news.setUpdatetime(new Date());
			
				if(StringUtil.isNotEmpty(mycontent)){
					mycontent=mycontent.replaceAll("<img", "<END><img");
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
				noticeNewsMapper.insertSelective(news);
				Integer newsId=news.getUid();
				StNoticeNewsContent c =new StNoticeNewsContent();
				c.setNewsid(newsId);
				c.setContent(realcontent);
				c.setUpdatetime(new Date());
				c.setCreatetime(new Date());
				noticeNewsContentMapper.insert(c);
				if(villageIds!=null){
					for (String string : villageIds) {
						StNoticeNewsRelation r=new StNoticeNewsRelation();
						r.setNewsid(newsId);
						r.setVillageid(Integer.valueOf(string));
						noticeNewsRelationMapper.insert(r);
					}
				}else{
					StVillageExample example=new StVillageExample();
					StVillageExample.Criteria criteria=example.createCriteria();
					criteria.andParentEqualTo(StringUtil.isEmpty(town)?1:Integer.valueOf(town));
					List<StVillage> l = villageMapper.selectByExample(example);
					for (StVillage stVillage : l) {
						StNoticeNewsRelation r=new StNoticeNewsRelation();
						r.setNewsid(newsId);
						r.setVillageid(stVillage.getId());
						noticeNewsRelationMapper.insert(r);
					}
				}
			}
		}
		
	}

	@Override
	public List<Integer> getVillageIdsBelongByNewsId(Integer newsId) {
		StNoticeNewsRelationExample example=new StNoticeNewsRelationExample();
		StNoticeNewsRelationExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		List<StNoticeNewsRelation> r = noticeNewsRelationMapper.selectByExample(example);
		LinkedList<Integer> list=new LinkedList<Integer>();
		for (StNoticeNewsRelation stVillageNewsRelation : r) {
			list.add(stVillageNewsRelation.getVillageid());
		}
		return list;
	}
}
