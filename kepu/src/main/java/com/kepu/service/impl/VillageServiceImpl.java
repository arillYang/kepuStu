package com.kepu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.constant.MyConstant;
import com.kepu.constant.ResultConstant;
import com.kepu.dao.JedisClient;
import com.kepu.mapper.StVillageContentMapper;
import com.kepu.mapper.StVillageMapper;
import com.kepu.mapper.StVillageNewsCollectionMapper;
import com.kepu.mapper.StVillageNewsCommentMapper;
import com.kepu.mapper.StVillageNewsHotSearchMapper;
import com.kepu.mapper.StVillageNewsMapper;
import com.kepu.mapper.StVillageNewsRelationMapper;
import com.kepu.mapper.StVillageNewsReplyMapper;
import com.kepu.mapper.StVillageNewsReportMapper;
import com.kepu.mapper.StVillageNewsVoteMapper;
import com.kepu.mapper.StVillageVoteMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StActivityRecord;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageContentWithBLOBs;
import com.kepu.pojo.StVillageExample;
import com.kepu.pojo.StVillageNews;
import com.kepu.pojo.StVillageNewsCollection;
import com.kepu.pojo.StVillageNewsCollectionExample;
import com.kepu.pojo.StVillageNewsComment;
import com.kepu.pojo.StVillageNewsCommentExample;
import com.kepu.pojo.StVillageNewsExample;
import com.kepu.pojo.StVillageNewsHotSearch;
import com.kepu.pojo.StVillageNewsHotSearchExample;
import com.kepu.pojo.StVillageNewsRelation;
import com.kepu.pojo.StVillageNewsRelationExample;
import com.kepu.pojo.StVillageNewsReply;
import com.kepu.pojo.StVillageNewsReplyExample;
import com.kepu.pojo.StVillageNewsReport;
import com.kepu.pojo.StVillageNewsReportExample;
import com.kepu.pojo.StVillageNewsVote;
import com.kepu.pojo.StVillageNewsVoteExample;
import com.kepu.pojo.StVillageVote;
import com.kepu.pojo.StVillageVoteExample;
import com.kepu.pojo.news.NewsContent;
import com.kepu.pojo.news.NewsTemp;
import com.kepu.pojo.news.VillageTotalResult;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.service.VillageService;
import com.kepu.util.DateUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.StrSimilarityUtils;
import com.kepu.util.StringUtil;
import com.kepu.util.SystemSession;

@Service
public class VillageServiceImpl implements VillageService {
	
	private static final Logger LOG = Logger.getLogger(VillageServiceImpl.class);
	@Autowired
	private StVillageMapper villageMapper;
	@Autowired
	private StVillageNewsMapper villageNewsMapper;
	@Autowired
	private StVillageNewsRelationMapper relationMapper;
	@Autowired
	private StVillageNewsCollectionMapper villageNewsCollectionMapper;
	@Autowired
	private StVillageNewsVoteMapper villageNewsVoteMapper;
	@Autowired
	private StVillageNewsCommentMapper villageNewsCommentMapper;
	@Autowired
	private StVillageNewsReplyMapper villageNewsReplyMapper;
	@Autowired
	private StVillageVoteMapper villageVoteMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private StVillageNewsReportMapper villageNewsReportMapper;
	@Autowired
	private StVillageNewsHotSearchMapper villageNewsHotSearchMapper;
	@Autowired
	private StVillageContentMapper villageContentMapper;
	@Autowired
	private SysService sysService;
	@Autowired
	private UserService userService;
	@Override
	public List<StVillage> getPicture(Integer townId) {
		StVillageExample example=new StVillageExample();
		StVillageExample.Criteria criteria=example.createCriteria();
		criteria.andParentEqualTo(townId);
		return villageMapper.selectByExample(example);
	}
	
	@Override
	public List<Integer> findNewsIdsByVillageId(Integer villageId) {
		StVillageNewsRelationExample ex=new StVillageNewsRelationExample();
		StVillageNewsRelationExample.Criteria criteria1=ex.createCriteria();
		criteria1.andVillageidEqualTo(villageId);  //  二级ID
		List<StVillageNewsRelation> n = relationMapper.selectByExample(ex);
		List<Integer> l=new LinkedList<Integer>();
		for (StVillageNewsRelation stVillageNewsRelation : n) {
			l.add(stVillageNewsRelation.getNewsid());
		}
		return l;
	}
	@Override
	public KePuResult getCarousel(Integer total, Integer type) {
		List<Integer> l=null;
		if(type>30)
		 l=findNewsIdsByVillageId(type);
		else
		l=findNewsIdsByTownId(type);
		if(l.size()==0){
			//return KePuResult.ok(ResultConstant.code_yewu, "请输入正确的乡镇ID", null);
			LOG.info("请输入正确的乡镇ID");
			return KePuResult.ok(ResultConstant.code_yewu, "暂无最新消息", null);
		}
		StVillageNewsExample example=new StVillageNewsExample();
		StVillageNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andCarouselEqualTo(1);
		criteria.andStateEqualTo(0);
		criteria.andUidIn(l);
		criteria.andDraftEqualTo(0);  // 非草稿
		PageHelper.startPage(1, total);
		List<StVillageNews> list = villageNewsMapper.selectByExample(example);
		List<Map<String,String>> data=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		String version=SystemSession.get().getAppVersion();
		int isNew=1;
		if(StringUtil.isNotEmpty(version)&&version.compareTo("6.2.0")<0)
			isNew=0;
		for (StVillageNews stNews:list ) {
			temp=new HashMap<String, String>();
			temp.put("newsId", stNews.getUid()+"");
			temp.put("title", stNews.getTitle());
			String pic=stNews.getNewsimages();
			String image="";
			if(StringUtil.isNotEmpty(pic)){
				image=pic.split(",")[0];
			}
			temp.put("pic", image);
			if(isNew==1)
				temp.put("type", "1");
			data.add(temp);
		}
		if(isNew==1){
			List<Map<String, String>> linkList = sysService.getLinkMapByType(2);
			for (Map<String, String> map : linkList) {
				data.add(map);
			}
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", data);
	}
	@Override
	public KePuResult getNews(Integer type, Integer page, Integer size) {
		// type 乡村ID 
		List<Integer> l=null;
		if(type>50)
			l=findNewsIdsByVillageId(type);
		else
			l=findNewsIdsByTownId(type);
		if(l.size()==0){
			return KePuResult.ok(ResultConstant.code_ok, "暂无消息", null);
		}
		List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		Integer stickNewsId=null;
		// page=1  获取置顶新闻放最第一个
		StVillageNews stick=null;
		if(type>50)
			stick=getStickNews(type);
		else
			stick=getStickTownNews(type);
		if(page==1){
			if(stick!=null){
				temp=new HashMap<String, String>();
				stickNewsId=stick.getUid();
				temp.put("newsId", stick.getUid()+"");
				temp.put("newsStyle", stick.getNewsstyle()+"");
				temp.put("pics", stick.getNewsimages());
				temp.put("auchor", stick.getNewsauthor());
				temp.put("publishTime",DateUtil.formatDate(stick.getUpdatetime(), MyConstant.updatetime));
				temp.put("view", stick.getView()+"");
				temp.put("title", stick.getTitle());
				temp.put("stick", "1");
				myList.add(temp);
			}
		}
		Map<String,Object> map=new HashMap<String, Object>();
		StVillageNewsExample example=new StVillageNewsExample();
		StVillageNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		criteria.andUidIn(l);
		criteria.andDraftEqualTo(0);  // 不是草稿
		if(stick!=null)
			criteria.andUidNotEqualTo(stick.getUid());
		PageHelper.startPage(page, size);
		// type=villageId  新闻也可以从一级乡镇获取
		List<StVillageNews> list = villageNewsMapper.selectByExample(example);
		PageInfo<StVillageNews> pageInfo=new PageInfo<StVillageNews>(list);
		for (StVillageNews stNews : list) {
			temp=new HashMap<String, String>();
			temp.put("newsId", stNews.getUid()+"");
			temp.put("newsStyle", stNews.getNewsstyle()+"");
			temp.put("pics", stNews.getNewsimages());
			temp.put("auchor", stNews.getNewsauthor());
			temp.put("publishTime",DateUtil.formatDate(stNews.getUpdatetime(), MyConstant.updatetime));
			temp.put("view", stNews.getView()+"");
			temp.put("stick", "0");
			temp.put("title", stNews.getTitle());
			myList.add(temp);
		}
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("newsList", myList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public StVillageNews getStickNews(Integer villageId) {
		List<Integer> l=findNewsIdsByVillageId(villageId);
		StVillageNewsExample example=new StVillageNewsExample();
		StVillageNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		criteria.andStickEqualTo(1);
		criteria.andUidIn(l);
		criteria.andDraftEqualTo(0);
		PageHelper.startPage(1, 1);
		List<StVillageNews> list = villageNewsMapper.selectByExample(example);
		if(list.size()==0)
			return null;
		return  list.get(0);
	}

	@Override
	public StVillageNews getStickTownNews(Integer townId) {
		List<Integer> l=findNewsIdsByTownId(townId);
		StVillageNewsExample example=new StVillageNewsExample();
		StVillageNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		criteria.andStickEqualTo(1);
		criteria.andUidIn(l);
		criteria.andDraftEqualTo(0);
		PageHelper.startPage(1, 1);
		List<StVillageNews> list = villageNewsMapper.selectByExample(example);
		if(list.size()==0)
			return null;
		return  list.get(0);
	}
	@Override
	public KePuResult getNewsDetail(Integer userId, Integer newsId,
			String appVersion) {
		Map<String,Object> map=new HashMap<String, Object>();
		StVillageNews news = villageNewsMapper.selectByPrimaryKey(newsId);
		if(news==null||news.getState()==1)
			return KePuResult.ok(ResultConstant.code_yewu, "该乡村新闻已被删除或不存在", "");
		String pic=news.getNewsimages();
		String image="";
		if(StringUtil.isNotEmpty(pic)){
			image=pic.split(",")[0];
		}
		String myLike="0";
		StVillageNewsCollectionExample  example=new StVillageNewsCollectionExample();
		StVillageNewsCollectionExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		criteria.andUseridEqualTo(userId);
		List<StVillageNewsCollection> t = villageNewsCollectionMapper.selectByExample(example);
		if(t.size()==1){
			myLike="1";
			List<Integer> likeList=new LinkedList<Integer>();
			likeList.add(newsId);
		}
		map.put("myLike", myLike);
		map.put("topPic", image);
		map.put("title", news.getTitle());
		map.put("publishTime",DateUtil.formatDate(news.getUpdatetime(), MyConstant.updatetime));
		map.put("auchor", news.getNewsauthor());
		map.put("likeCount", news.getLikecount()+"");
		map.put("commentCount", news.getCommentcount()+"");
		//2017-6-26  新闻底部  点击喜欢(区别与收藏),不喜欢
		map.put("voteNum", news.getVotenum()+"");
		map.put("dislikeNum", news.getDislikenum()+"");
		StVillageNewsVoteExample ex=new StVillageNewsVoteExample();
		StVillageNewsVoteExample.Criteria c=ex.createCriteria();
		c.andNewsidEqualTo(newsId);
		c.andUseridEqualTo(userId);
		int myVote=0;
		int myDislike=0;
		List<StVillageNewsVote> r = villageNewsVoteMapper.selectByExample(ex);
		if(r.size()!=0){
			StVillageNewsVote v=r.get(0);
			if(v.getDislike()==1)
				myDislike=1;
			if(v.getLiked()==1)
				myVote=1;
		}
		map.put("myVote", myVote+"");
		map.put("myDislike", myDislike+"");
		String content=news.getContent();
		map.put("content",JsonUtils.jsonToList(content, NewsContent.class));
		//  访问量+1
		news.setView(news.getView()+1);
		villageNewsMapper.updateByPrimaryKeySelective(news);
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult getNewsComment(Integer newsId, Integer userId,
			Integer page, Integer size) {
		if(!checkNews(newsId))
			return KePuResult.ok(ResultConstant.code_yewu, "该乡镇新闻已被删除或不存在", "");
		Map<String,Object> map=new HashMap<String, Object>();
		StVillageNewsCommentExample example=new StVillageNewsCommentExample();
		example.setOrderByClause("createTime");
		StVillageNewsCommentExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andNewsidEqualTo(newsId);
		PageHelper.startPage(page, size);
		List<StVillageNewsComment> myList = villageNewsCommentMapper.selectByExample(example);
		List<Map<String,String>> commentList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StVillageNewsComment stComment : myList) {
			temp=new HashMap<String, String>();
			temp.put("commentId", stComment.getUid()+"");
			temp.put("createTime",DateUtil.formatDate(stComment.getCreatetime(), MyConstant.updatetime));
			temp.put("replyNum", stComment.getReplynum()+"");
			temp.put("praiseNum", stComment.getPraisenum()+"");
			temp.put("comment", stComment.getContent());
			temp.put("userId", stComment.getUserid()+"");
			temp.put("nickName", stComment.getUsername());
			temp.put("avatar", stComment.getAvatar());
			String myPraise=jedisClient.hget("VcommentPraise", "VcommentPraise_"+stComment.getUid()+
					"_"+userId);
			if("0".equals(myPraise)||StringUtil.isEmpty(myPraise)){
				String t=checkPraise(1, userId, stComment.getUid());
				if("1".equals(t)){
				     jedisClient.hset("VcommentPraise", "VcommentPraise_"+stComment.getUid()+
							"_"+userId,"1");
				}
				myPraise=t;
			}
			temp.put("myPraise", StringUtil.isEmpty(myPraise)?"0":myPraise);
			commentList.add(temp);
		}
		PageInfo<StVillageNewsComment> pageInfo=new PageInfo<StVillageNewsComment>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("commentList", commentList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public Boolean checkNews(Integer newsId) {
		StVillageNews news = villageNewsMapper.selectByPrimaryKey(newsId);
		if(news==null||news.getState()==1)
			return false;
		return true;
	}

	@Override
	public Boolean checkComment(Long commentId) {
		StVillageNewsComment comment=villageNewsCommentMapper.selectByPrimaryKey(commentId);
		if(comment==null||comment.getState()==1)
			return false;
		return true;
	}

	@Override
	public String checkPraise(Integer type, Integer userId, Long typeId) {
		StVillageVoteExample example=new StVillageVoteExample();
		StVillageVoteExample.Criteria criteria=example.createCriteria();
		criteria.andTypeEqualTo(type);
		criteria.andTypeidEqualTo(typeId);
		criteria.andUseridEqualTo(userId);
		criteria.andStatusEqualTo(0);
		List<StVillageVote> list = villageVoteMapper.selectByExample(example);
		return list.size()==0?"0":"1";
	}

	@Override
	public KePuResult getCommentReply(Long commentId, Integer userId,
			Integer page, Integer size) {
		if(!checkComment(commentId))
			return KePuResult.ok(ResultConstant.code_yewu, "该评论已被删除或不存在", "");
		Map<String,Object> map=new HashMap<String, Object>();
		StVillageNewsReplyExample example=new StVillageNewsReplyExample();
		example.setOrderByClause("createTime");
		StVillageNewsReplyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andCommentidEqualTo(commentId);
		PageHelper.startPage(page, size);
		List<StVillageNewsReply> myList = villageNewsReplyMapper.selectByExample(example);
		List<Map<String,String>> replyList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StVillageNewsReply stReply : myList) {
			temp=new HashMap<String, String>();
			temp.put("replyId", stReply.getUid()+"");
			temp.put("createTime",DateUtil.formatDate(stReply.getCreatetime(), MyConstant.updatetime));
			temp.put("praiseNum", stReply.getPraisenum()+"");
			temp.put("comment", stReply.getContent());
			temp.put("userId", stReply.getUserid()+"");
			temp.put("nickName", stReply.getUsername());
			temp.put("avatar", stReply.getAvatar());
			String myPraise=jedisClient.hget("VreplyPraise", "VreplyPraise_"+stReply.getUid()+
					"_"+userId);
			if("0".equals(myPraise)||StringUtil.isEmpty(myPraise)){
				String t=checkPraise(2, userId, stReply.getUid());
				if("1".equals(t)){
				     jedisClient.hset("VreplyPraise", "VreplyPraise_"+stReply.getUid()+
							"_"+userId,"1");
				}
				myPraise=t;
			}
			temp.put("myPraise", StringUtil.isEmpty(myPraise)?"0":myPraise);
			replyList.add(temp);
		}
		PageInfo<StVillageNewsReply> pageInfo=new PageInfo<StVillageNewsReply>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("replyList", replyList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult likeNews(Integer newsId, Integer userId) {
		StVillageNewsCollectionExample example=new StVillageNewsCollectionExample();
		StVillageNewsCollectionExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		criteria.andUseridEqualTo(userId);
		List<StVillageNewsCollection> list = villageNewsCollectionMapper.selectByExample(example);
		if(list.size()>0){
			return KePuResult.ok(ResultConstant.code_ok, "已收藏", "");
		}
		StVillageNewsCollection collection=new StVillageNewsCollection();
		collection.setNewsid(newsId);
		collection.setUserid(userId);
		collection.setCreatetime(new Date());
		villageNewsCollectionMapper.insertSelective(collection);
		StVillageNews news=villageNewsMapper.selectByPrimaryKey(newsId);
		news.setLikecount(news.getLikecount()+1);
		villageNewsMapper.updateByPrimaryKeySelective(news);
		//  写入缓存
		String myLikes=jedisClient.get("Vnews_like_user_"+userId);
		List<Integer> likeList;
		if(StringUtil.isNotEmpty(myLikes)){
			likeList=JsonUtils.jsonToList(myLikes, Integer.class);
		}else{
			likeList=new LinkedList<Integer>();
		}
		likeList.add(newsId);
		jedisClient.set("Vnews_like_user_"+userId,JsonUtils.objectToJson(likeList));
		LOG.info("收藏成功");
		return KePuResult.ok(ResultConstant.code_ok, "收藏成功", "");
	}

	@Override
	public KePuResult deletelikeNews(String[] newsIds, Integer userId) {
		List<Integer> values=null;
		try {
			values = StringUtil.asIntegerList(newsIds);
		} catch (Exception e) {
			e.printStackTrace();
			return KePuResult.ok(ResultConstant.code_yewu, "newsId有误，操作失败", "");
		}
		if(values==null)
			return KePuResult.ok(ResultConstant.code_yewu, "请选择要删除的收藏", "");
		StVillageNewsCollectionExample example=new StVillageNewsCollectionExample();
		StVillageNewsCollectionExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		criteria.andNewsidIn(values);
		villageNewsCollectionMapper.deleteByExample(example);
		jedisClient.del("Vnews_like_user_"+userId);
		StVillageNewsExample example2=new StVillageNewsExample();
		StVillageNewsExample.Criteria criteria2=example2.createCriteria();
		criteria2.andUidIn(values);
		List<StVillageNews> r = villageNewsMapper.selectByExample(example2);
		for (StVillageNews stNews : r) {
			stNews.setLikecount(stNews.getLikecount()-1);
			villageNewsMapper.updateByPrimaryKeySelective(stNews);
		}
		return KePuResult.ok(ResultConstant.code_ok, "删除成功", "");
	}

	@Override
	public KePuResult getMyLikeNews(Integer userId, Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StVillageNewsCollectionExample example=new StVillageNewsCollectionExample();
		StVillageNewsCollectionExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		PageHelper.startPage(page, size);
		List<StVillageNewsCollection> myList = villageNewsCollectionMapper.selectByExample(example);
		List<Map<String,String>> collectionList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StVillageNewsCollection stCollection : myList) {
			temp=new HashMap<String, String>();
			Integer newsId=stCollection.getNewsid();
			temp.put("newsId", newsId+"");
			String newsCache=jedisClient.get("Vnews_"+newsId);
			if(StringUtil.isNotEmpty(newsCache)){
				NewsTemp news=JsonUtils.jsonToPojo(newsCache, NewsTemp.class);
				temp.put("title", news.getTitle());
				temp.put("auchor", news.getNewsAuthor());
				temp.put("newsStyle", news.getNewsStyle());
				temp.put("publishTime", news.getUpdateTime());
				temp.put("pics", news.getNewsImages());
				temp.put("view", news.getView());
				
			}else{
				StVillageNews news=villageNewsMapper.selectByPrimaryKey(newsId);
				temp.put("title", news.getTitle());
				temp.put("auchor", news.getNewsauthor());
				temp.put("newsStyle", news.getNewsstyle()+"");
				temp.put("publishTime", DateUtil.formatDate(news.getUpdatetime(), MyConstant.updatetime));
				temp.put("pics", news.getNewsimages());
				temp.put("view", news.getView()+"");
				NewsTemp t=new NewsTemp(news.getTitle(), news.getNewsimages(), news.getNewsauthor(),
						 news.getNewsstyle()+"", DateUtil.formatDate(news.getUpdatetime(),MyConstant.updatetime),
						 news.getView()+"");
				jedisClient.set("Vnews_"+newsId, JsonUtils.objectToJson(t));
				jedisClient.expire("Vnews_"+newsId, 2592000);
			}
			collectionList.add(temp);
		}
		PageInfo<StVillageNewsCollection> pageInfo=new PageInfo<StVillageNewsCollection>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("collectionList", collectionList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult reportNewsComment(Integer userId, Long commentId) {
		StVillageNewsComment comment = villageNewsCommentMapper.selectByPrimaryKey(commentId);
		if(comment==null)
			return KePuResult.ok(ResultConstant.code_yewu, "该评论已被删除或不存在", "");
		StVillageNewsReportExample example=new StVillageNewsReportExample();
		StVillageNewsReportExample.Criteria criteria=example.createCriteria();
		criteria.andCommentidEqualTo(commentId);
		List<StVillageNewsReport> reportList = villageNewsReportMapper.selectByExample(example);
		if(reportList.size()!=0)
			return KePuResult.ok(ResultConstant.code_yewu, "您的举报已被其他用户提交过，感谢您的支持", "");
		StVillageNewsReport report=new StVillageNewsReport();
		report.setCommentid(commentId);
		report.setReportuser(userId);
		report.setCreatetime(new Date());
		villageNewsReportMapper.insertSelective(report);
		return KePuResult.ok(ResultConstant.code_ok, "举报成功","");
	}
	
	@Override
	public KePuResult praise(Integer type, Long typeId,Integer userId) {
		if(type==1){
			StVillageNewsComment r = villageNewsCommentMapper.selectByPrimaryKey(typeId);
			if(r==null)
				return KePuResult.ok(ResultConstant.code_yewu, "typeId不正确", "");
			r.setPraisenum(r.getPraisenum()+1);
			villageNewsCommentMapper.updateByPrimaryKeySelective(r);
			StVillageVote vote=new StVillageVote(); 
			vote.setType(type);
			vote.setTypeid(typeId);
			vote.setVotetime(new Date());
			vote.setUserid(userId);
			jedisClient.hset("VcommentPraise", "VcommentPraise_"+typeId+
					"_"+userId,"1");
			villageVoteMapper.insertSelective(vote);
			return KePuResult.ok(ResultConstant.code_ok, "点赞成功", "");
		}else if(type==2){
			StVillageNewsReply r=villageNewsReplyMapper.selectByPrimaryKey(typeId); 
			if(r==null)
				return KePuResult.ok(ResultConstant.code_yewu, "typeId不正确", "");
			r.setPraisenum(r.getPraisenum()+1);
			villageNewsReplyMapper.updateByPrimaryKeySelective(r);
			StVillageVote vote=new StVillageVote();
			vote.setType(type);
			vote.setTypeid(typeId);
			vote.setVotetime(new Date());
			vote.setUserid(userId);
			jedisClient.hset("VreplyPraise", "VreplyPraise_"+typeId+
					"_"+userId,"1");
			villageVoteMapper.insertSelective(vote);
			return KePuResult.ok(ResultConstant.code_ok, "点赞成功", "");
		}
		return KePuResult.ok(ResultConstant.code_yewu, "type不正确", "");
	}
	
	@Override
	public KePuResult dpNews(Integer newsId, Integer type, Integer userId,Integer operate) {
		StVillageNewsVoteExample example=new StVillageNewsVoteExample();
		StVillageNewsVoteExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		criteria.andUseridEqualTo(userId);
		List<StVillageNewsVote> resultList = villageNewsVoteMapper.selectByExample(example);
		StVillageNews news=villageNewsMapper.selectByPrimaryKey(newsId);
		if(news==null){
			return KePuResult.ok(ResultConstant.code_yewu, "该新闻已被删除或不存在", "");
		}
		if(resultList.size()==0&&operate==1){
			StVillageNewsVote v=new StVillageNewsVote();
			if(type==1){
				v.setLiked(1);
				news.setVotenum(news.getVotenum()+1);
			}
			if(type==2){
				v.setDislike(1);
				news.setDislikenum(news.getDislikenum()+1);
			}
			villageNewsMapper.updateByPrimaryKeySelective(news);
			// 点赞积分  begin
			StActivityRecord record=new StActivityRecord();
			record.setScore(1.0);
			StUser user = userService.getUserById(userId);
			sysService.insertActivityRecord(user, record, 21, "乡镇新闻点赞,新闻ID:"+newsId);
			// end
			v.setVotetime(new Date());
			v.setNewsid(newsId);
			v.setUserid(userId);
			villageNewsVoteMapper.insertSelective(v);
		}else if(resultList.size()!=0){
			StVillageNewsVote v=resultList.get(0);
			if(type==1){
				if(v.getLiked()==0&&operate==1){
					v.setLiked(1);
					news.setVotenum(news.getVotenum()+1);
				}
				if(v.getLiked()==1&&operate==0){
					v.setLiked(0);
					news.setVotenum(news.getVotenum()-1);
				}
			}
			if(type==2){
				if(v.getDislike()==0&&operate==1){
					news.setDislikenum(news.getDislikenum()+1);
					v.setDislike(1);
				}
				if(v.getDislike()==1&&operate==0){
					news.setDislikenum(news.getDislikenum()-1);
					v.setDislike(0);
				}
			}
			villageNewsMapper.updateByPrimaryKeySelective(news);
			villageNewsVoteMapper.updateByPrimaryKeySelective(v);
		}
		return KePuResult.ok(ResultConstant.code_ok, "操作成功", "");
	}
	
	
	@Override
	public KePuResult searchNews(String query, Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StVillageNewsExample example=new StVillageNewsExample();
		StVillageNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		criteria.andTitleLike("%"+query+"%");
		PageHelper.startPage(page, size);
		List<StVillageNews> myList = villageNewsMapper.selectByExample(example);
		List<Map<String,String>> newsList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StVillageNews news : myList) {
			temp=new HashMap<String, String>();
			temp.put("newsId", news.getUid()+"");
			temp.put("title", news.getTitle());
			temp.put("auchor", news.getNewsauthor());
			temp.put("newsStyle", news.getNewsstyle()+"");
			temp.put("publishTime", DateUtil.formatDate(news.getUpdatetime(), MyConstant.updatetime));
			temp.put("pics", news.getNewsimages());
			temp.put("view", news.getView()+"");
			newsList.add(temp);
		}
		PageInfo<StVillageNews> pageInfo=new PageInfo<StVillageNews>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("newsList", newsList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public void addHotSearch(String query) {
		if(query.length()<2)
			return;
		StVillageNewsHotSearchExample example=new StVillageNewsHotSearchExample();
		StVillageNewsHotSearchExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		List<StVillageNewsHotSearch> list = villageNewsHotSearchMapper.selectByExample(example);
		float maxSilimar=0l;
		int tempId=1;
		for (StVillageNewsHotSearch stHotSearch : list) {
			String word=stHotSearch.getWord();
			float current=StrSimilarityUtils.getSimilarityRatio(word, query);
			if(current>maxSilimar){
				maxSilimar=current;
				tempId=stHotSearch.getUid();
			}	
		}
		if(maxSilimar>0.4){
			StVillageNewsHotSearch r = villageNewsHotSearchMapper.selectByPrimaryKey(tempId);
			r.setSearchnum(r.getSearchnum()+1);
			r.setUpdatetime(new Date());
			villageNewsHotSearchMapper.updateByPrimaryKeySelective(r);
		}else{
			StVillageNewsHotSearch r =new StVillageNewsHotSearch();
			r.setWord(query);
			r.setCreatetime(new Date());
			r.setUpdatetime(new Date());
			villageNewsHotSearchMapper.insertSelective(r);
		}
	}
	@Override
	public KePuResult getHotSearch() {
		Map<String,Object> map=new HashMap<String, Object>();
		StVillageNewsHotSearchExample example=new StVillageNewsHotSearchExample();
		StVillageNewsHotSearchExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		PageHelper.startPage(1, 20);
		List<StVillageNewsHotSearch> list = villageNewsHotSearchMapper.selectByExample(example);
		List<String> r=new LinkedList<String>();
		for (StVillageNewsHotSearch stHotSearch : list) {
			r.add(stHotSearch.getWord());
		}
		map.put("hotWords", r);
		map.put("totalcount", r.size()+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public List<Integer> getAllTownId() {
		StVillageExample example=new StVillageExample();
		StVillageExample.Criteria criteria=example.createCriteria();
		criteria.andParentEqualTo(-1);
		List<StVillage> list = villageMapper.selectByExample(example);
		List<Integer> l=new LinkedList<Integer>();
		for (StVillage stVillage : list) {
			l.add(stVillage.getId());
		}
		return l;
	}

	@Override
	public String getVillageMessage(Integer villageId) {
		StVillageContentWithBLOBs content = villageContentMapper.selectByPrimaryKey(villageId);
		if(content!=null)
			return content.getContent();
		return null;
	}

	@Override
	public KePuResult searchVillageTotal(String query, Integer page,
			Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(page, size);
		List<VillageTotalResult> myList = villageNewsMapper.searchVillageTotal("%"+query+"%");
		List<Map<String,String>> newsList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (VillageTotalResult news : myList) {
			temp=new HashMap<String, String>();
			temp.put("newsId", news.getUid()+"");
			temp.put("title", news.getTitle());
			temp.put("auchor", news.getNewsAuthor());
			temp.put("newsStyle", news.getNewsStyle()+"");
			temp.put("publishTime", DateUtil.formatDate(news.getUpdatetime(), MyConstant.updatetime));
			temp.put("pics", news.getNewsImages());
			temp.put("view", news.getView()+"");
			temp.put("searchType", news.getSearchType()+"");
			newsList.add(temp);
		}
		PageInfo<VillageTotalResult> pageInfo=new PageInfo<VillageTotalResult>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("newsList", newsList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public StVillage getVillageNameById(Integer villageId) {
		return villageMapper.selectByPrimaryKey(villageId);
	}

	@Override
	public List<Integer> findNewsIdsByTownId(Integer townId) {
		StVillageExample se=new StVillageExample();
		StVillageExample.Criteria c1=se.createCriteria();
		c1.andParentEqualTo(townId);
		List<StVillage> v1 = villageMapper.selectByExample(se);
		List<Integer> l1=new LinkedList<Integer>();
		for (StVillage stVillage : v1) {
			l1.add(stVillage.getId());
		}
		StVillageNewsRelationExample ex=new StVillageNewsRelationExample();
		StVillageNewsRelationExample.Criteria criteria1=ex.createCriteria();
		//criteria1.andVillageidEqualTo(villageId);  // 一级地名
		criteria1.andVillageidIn(l1);
		List<StVillageNewsRelation> n = relationMapper.selectByExample(ex);
		List<Integer> l=new LinkedList<Integer>();
		for (StVillageNewsRelation stVillageNewsRelation : n) {
			l.add(stVillageNewsRelation.getNewsid());
		}
		return l;
	}

	@Override
	public Integer getParentIdByCountyId(Integer villageId) {
		StVillage v = villageMapper.selectByPrimaryKey(villageId);
		if(v!=null)
			return v.getParent();
		else
			return -1;
	}

	@Override
	public String getVillageName(Integer villageId) {
		StVillage v = villageMapper.selectByPrimaryKey(villageId);
		String vName=v.getName();
		StVillage t = villageMapper.selectByPrimaryKey(v.getParent());
		String tName=t.getName();
		return tName+"."+vName;
	}
	
	@Override
	public List<StVillage> getAddressByParent(Integer parentId) {
		StVillageExample example=new StVillageExample();
		StVillageExample.Criteria criteria=example.createCriteria();
		if(parentId!=null)
			criteria.andParentEqualTo(parentId);
		return villageMapper.selectByExample(example);
	}

}
