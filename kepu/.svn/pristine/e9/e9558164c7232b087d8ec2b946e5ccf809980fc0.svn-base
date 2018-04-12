package com.kepu.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.constant.MyConstant;
import com.kepu.constant.ResultConstant;
import com.kepu.dao.JedisClient;
import com.kepu.mapper.StClassifyMapper;
import com.kepu.mapper.StCollectionMapper;
import com.kepu.mapper.StCommentMapper;
import com.kepu.mapper.StHotSearchMapper;
import com.kepu.mapper.StLinkMapper;
import com.kepu.mapper.StLogMapper;
import com.kepu.mapper.StNewsDetailMapper;
import com.kepu.mapper.StNewsMapper;
import com.kepu.mapper.StNewsQuestionMapper;
import com.kepu.mapper.StNewsQuestionrecordMapper;
import com.kepu.mapper.StNewsRelationMapper;
import com.kepu.mapper.StNewsVoteMapper;
import com.kepu.mapper.StReplyMapper;
import com.kepu.mapper.StUserAccountMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.mapper.StVillageNewsCommentMapper;
import com.kepu.mapper.StVoteMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StActivityRecord;
import com.kepu.pojo.StClassify;
import com.kepu.pojo.StClassifyExample;
import com.kepu.pojo.StCollection;
import com.kepu.pojo.StCollectionExample;
import com.kepu.pojo.StComment;
import com.kepu.pojo.StCommentExample;
import com.kepu.pojo.StHotSearch;
import com.kepu.pojo.StHotSearchExample;
import com.kepu.pojo.StLink;
import com.kepu.pojo.StLinkExample;
import com.kepu.pojo.StLog;
import com.kepu.pojo.StLogExample;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StNewsDetail;
import com.kepu.pojo.StNewsDetailExample;
import com.kepu.pojo.StNewsExample;
import com.kepu.pojo.StNewsQuestion;
import com.kepu.pojo.StNewsQuestionExample;
import com.kepu.pojo.StNewsQuestionrecord;
import com.kepu.pojo.StNewsQuestionrecordExample;
import com.kepu.pojo.StNewsRelation;
import com.kepu.pojo.StNewsRelationExample;
import com.kepu.pojo.StNewsVote;
import com.kepu.pojo.StNewsVoteExample;
import com.kepu.pojo.StReply;
import com.kepu.pojo.StReplyExample;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StUserAccount;
import com.kepu.pojo.StVillageNewsComment;
import com.kepu.pojo.StVillageNewsCommentExample;
import com.kepu.pojo.StVote;
import com.kepu.pojo.StVoteExample;
import com.kepu.pojo.news.NewsContent;
import com.kepu.service.NewService;
import com.kepu.service.SysService;
import com.kepu.util.DateUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.StrSimilarityUtils;
import com.kepu.util.StringUtil;
import com.kepu.util.SystemSession;

@Service
public class NewServiceImpl implements NewService {

	@Autowired
	private StClassifyMapper classifyMapper;
	@Autowired
	private StNewsMapper stNewsMapper;
	@Autowired
	private StNewsDetailMapper stNewsDetailMapper;
	@Autowired
	private StCommentMapper commentMapper;
	@Autowired
	private StHotSearchMapper hotSearchMapper;
	@Autowired
	private StReplyMapper replyMapper; 
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private StCollectionMapper collectionMapper;
	@Autowired
	private StVoteMapper voteMapper;
	@Autowired
	private StNewsVoteMapper newsVoteMapper;
	@Autowired
	private StNewsRelationMapper newsRelationMapper;
	@Autowired
	private StNewsQuestionMapper newsQuestionMapper;
	@Autowired
	private StUserAccountMapper accountMapper;
	@Autowired
	private StNewsQuestionrecordMapper recordMapper;
	@Autowired
	private StUserMapper userMapper;
	@Autowired
	private StLogMapper logMapper;
	@Autowired
	private StVillageNewsCommentMapper villageNewsCommentMapper;
	@Autowired
	private SysService sysService;
	@Autowired
	private StLinkMapper linkMapper;
	@Override
	public KePuResult getTopnav() {
		StClassifyExample example=new StClassifyExample();
		StClassifyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		List<StClassify> list = classifyMapper.selectByExample(example);
		List<Map<String,String>> data=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StClassify stClassify : list) {
			temp=new HashMap<String, String>();
			temp.put("classId", stClassify.getUid()+"");
			temp.put("fiexed", stClassify.getFixed()+"");
			temp.put("className", stClassify.getClassifyname());
			data.add(temp);
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", data);
	}
	@Override
	public KePuResult getCarousel(Integer total,Integer type) {
		StNewsExample example=new StNewsExample();
		StNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andCarouselEqualTo(1);
		criteria.andStateEqualTo(0);
		criteria.andClassifyidEqualTo(type);
		criteria.andDraftEqualTo(0);  // 非草稿
		PageHelper.startPage(1, total);
		List<StNews> list = stNewsMapper.selectByExample(example);
		List<Map<String,String>> data=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		String version=SystemSession.get().getAppVersion();
		int isNew=1;
		if(StringUtil.isNotEmpty(version)&&version.compareTo("6.2.0")<0)
			isNew=0;
		for (StNews stNews:list ) {
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
			StLinkExample example2=new StLinkExample();
			StLinkExample.Criteria criteria2=example2.createCriteria();
			criteria2.andIsHomeEqualTo(1);
			List<StLink> r2 = linkMapper.selectByExample(example2);
			for (StLink stLink : r2) {
				String types=stLink.getClassifyids();
				String tp[]=types.split(",");
				List<String> lt = Arrays.asList(tp);
				if(lt.contains(type.toString())){
					temp=new HashMap<String, String>();
					temp.put("link", stLink.getLink());
					temp.put("pic", stLink.getLunbo());
					temp.put("type", "2");
					data.add(temp);
				}
			}
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", data);
	}
	@Override
	public KePuResult getNews(Integer type,Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		// page=1  获取置顶新闻放最第一个
		Integer stickNewsId=null;
		StNews stick=getStickNews(type);
		if(page==1){
			if(stick!=null){
				temp=new HashMap<String, String>();
				stickNewsId=stick.getUid();
				temp.put("newsId", stick.getUid()+"");
				temp.put("newsStyle", stick.getNewsstyle()+"");
				temp.put("pics", stick.getNewsimages());
				temp.put("auchor", stick.getKeywords()); //文章来源
				temp.put("publishTime",DateUtil.formatDate(stick.getUpdatetime(), MyConstant.updatetime));
				temp.put("view", stick.getView()+"");
				temp.put("title", stick.getTitle());
				temp.put("stick", "1");
				myList.add(temp);
			}
		}
		StNewsExample example=new StNewsExample();
		StNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		//criteria.andClassifyidEqualTo(type);
		criteria.andUidIn(getNewsIdsByType(type));
		criteria.andDraftEqualTo(0);
		if(stick!=null)
			criteria.andUidNotEqualTo(stick.getUid());
		PageHelper.startPage(page, size);
		List<StNews> list = stNewsMapper.selectByExample(example);
		PageInfo<StNews> pageInfo=new PageInfo<StNews>(list);
		for (StNews stNews : list) {
			temp=new HashMap<String, String>();
			temp.put("newsId", stNews.getUid()+"");
			temp.put("newsStyle", stNews.getNewsstyle()+"");
			temp.put("pics", stNews.getNewsimages());
			temp.put("auchor", stNews.getNewsauthor());
			temp.put("publishTime",DateUtil.formatDate(stNews.getCreatetime(), MyConstant.updatetime));
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
	public StNews getStickNews(Integer type) {
		StNewsExample example=new StNewsExample();
		StNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		criteria.andStickEqualTo(1);
		//criteria.andClassifyidEqualTo(type);
		criteria.andUidIn(getNewsIdsByType(type));
		criteria.andDraftEqualTo(0);
		PageHelper.startPage(1, 1);
		List<StNews> list = stNewsMapper.selectByExample(example);
		if(list.size()==0)
			return null;
		return  list.get(0);
	}
	@Override
	public KePuResult getNewsDetail(Integer userId,Integer newsId,String appVersion) {
		Map<String,Object> map=new HashMap<String, Object>();
		StNews news = stNewsMapper.selectByPrimaryKey(newsId);
		if(news==null||news.getState()==1)
			return KePuResult.ok(ResultConstant.code_yewu, "该新闻已被删除或不存在", "");
		String pic=news.getNewsimages();
		String image="";
		if(StringUtil.isNotEmpty(pic)){
			image=pic.split(",")[0];
		}
		//  是否是我收藏的   从缓存取   
		String myLikes=jedisClient.get("news_like_user_"+userId);
		String myLike="0";
		if(StringUtil.isNotEmpty(myLikes)){
			List<Integer> likeList=JsonUtils.jsonToList(myLikes, Integer.class);
			if(likeList.contains(newsId))
				myLike="1";
			else{
				StCollectionExample example=new StCollectionExample();
				StCollectionExample.Criteria criteria=example.createCriteria();
				criteria.andNewsidEqualTo(newsId);
				criteria.andUseridEqualTo(userId);
				List<StCollection> t = collectionMapper.selectByExample(example);
				if(t.size()==1){
					myLike="1";
					likeList.add(newsId);
					jedisClient.set("news_like_user_"+userId,JsonUtils.objectToJson(likeList));
				}
			}
		}else{
			StCollectionExample example=new StCollectionExample();
			StCollectionExample.Criteria criteria=example.createCriteria();
			criteria.andNewsidEqualTo(newsId);
			criteria.andUseridEqualTo(userId);
			List<StCollection> t = collectionMapper.selectByExample(example);
			if(t.size()==1){
				myLike="1";
				List<Integer> likeList=new LinkedList<Integer>();
				likeList.add(newsId);
				jedisClient.set("news_like_user_"+userId,JsonUtils.objectToJson(likeList));
			}
		}
		map.put("myLike", myLike);
		map.put("topPic", image);
		map.put("title", news.getTitle());
		map.put("publishTime",DateUtil.formatDate(news.getCreatetime(), MyConstant.updatetime));
		map.put("auchor", news.getKeywords()); //文章来源
		if(StringUtil.isNotEmpty(appVersion)&&appVersion.compareTo("6.1.0")>0)
			map.put("editor", news.getNewsauthor()); //2017-11-03  责任编辑
		map.put("likeCount", news.getLikecount()+"");
		map.put("commentCount", news.getCommentcount()+"");
		/*2017-6-26  新闻底部  点击喜欢(区别与收藏),不喜欢*/
		if(StringUtil.isNotEmpty(appVersion)&&appVersion.compareTo("3.0.1")>=0){
			map.put("voteNum", news.getVotenum()+"");
			map.put("dislikeNum", news.getDislikenum()+"");
			StNewsVoteExample ex=new StNewsVoteExample();
			StNewsVoteExample.Criteria c=ex.createCriteria();
			c.andNewsidEqualTo(newsId);
			c.andUseridEqualTo(userId);
			int myVote=0;
			int myDislike=0;
			List<StNewsVote> r = newsVoteMapper.selectByExample(ex);
			if(r.size()!=0){
				StNewsVote v=r.get(0);
				if(v.getDislike()==1)
					myDislike=1;
				if(v.getLiked()==1)
					myVote=1;
			}
			map.put("myVote", myVote+"");
			map.put("myDislike", myDislike+"");
		}
		if(StringUtil.isNotEmpty(appVersion)&&appVersion.compareTo("5.2.0")>=0){
			String url=news.getFullurl();
			if(StringUtil.isNotEmpty(url)){
				String[] urls=url.split(",");
				if(urls.length==2){
					map.put("link",urls[1]);
					map.put("linkName",urls[0]);
				}
			}
		}
		/*end*/
		String content=news.getContent();
		if(StringUtil.isEmpty(appVersion)||appVersion.compareTo("3.0.1")<0){
			List<NewsContent> c = JsonUtils.jsonToList(content, NewsContent.class);
			List<NewsContent> n=new LinkedList<NewsContent>();
			for (NewsContent newsContent : c) {
				if(!newsContent.getContentType().equals("2")){
					n.add(newsContent);
				}
			}
			map.put("content",n);
		}else{
			map.put("content",JsonUtils.jsonToList(content, NewsContent.class));
		}
		/*if(StringUtil.isEmpty(content)){
			List<StNewsDetail> list=getNewsDetailById(newsId);
			List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
			Map<String,String> temp;
			for (StNewsDetail stNewsDetail : list) {
				temp=new HashMap<String, String>();
				temp.put("contentIndex", stNewsDetail.getNewindex()+"");
				temp.put("contentType", stNewsDetail.getType()+"");
				temp.put("contentNews", stNewsDetail.getContent());
				myList.add(temp);
			}
			content=JsonUtils.objectToJson(myList);
			news.setContent(content);
			stNewsMapper.updateByPrimaryKeyWithBLOBs(news);
		}*/
		
		//  访问量+1
		news.setView(news.getView()+1);
		stNewsMapper.updateByPrimaryKeySelective(news);
		//  每次点击按乡镇统计
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public List<StNewsDetail> getNewsDetailById(Integer newsId) {
		StNewsDetailExample example=new StNewsDetailExample();
		StNewsDetailExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		criteria.andStateEqualTo(0);
		return stNewsDetailMapper.selectByExample(example);
	}
	@Override
	public KePuResult getNewsComment(Integer newsId,Integer userId,Integer page,Integer size) {
		if(!checkNews(newsId))
			return KePuResult.ok(ResultConstant.code_yewu, "该新闻已被删除或不存在", "");
		Map<String,Object> map=new HashMap<String, Object>();
		StCommentExample example=new StCommentExample();
		example.setOrderByClause("createTime");
		StCommentExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andNewsidEqualTo(newsId);
		PageHelper.startPage(page, size);
		List<StComment> myList = commentMapper.selectByExample(example);
		List<Map<String,String>> commentList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StComment stComment : myList) {
			temp=new HashMap<String, String>();
			temp.put("commentId", stComment.getUid()+"");
			temp.put("createTime",DateUtil.formatDate(stComment.getCreatetime(), MyConstant.updatetime));
			temp.put("replyNum", stComment.getReplynum()+"");
			temp.put("praiseNum", stComment.getPraisenum()+"");
			temp.put("comment", stComment.getContent());
			temp.put("userId", stComment.getUserid()+"");
			temp.put("nickName", stComment.getUsername());
			temp.put("avatar", stComment.getAvatar());
			String myPraise=jedisClient.hget("commentPraise", "commentPraise_"+stComment.getUid()+
					"_"+userId);
			if("0".equals(myPraise)||StringUtil.isEmpty(myPraise)){
				String t=checkPraise(1, userId, stComment.getUid());
				if("1".equals(t)){
				     jedisClient.hset("commentPraise", "commentPraise_"+stComment.getUid()+
							"_"+userId,"1");
				}
				myPraise=t;
			}
			temp.put("myPraise", StringUtil.isEmpty(myPraise)?"0":myPraise);
			commentList.add(temp);
		}
		PageInfo<StComment> pageInfo=new PageInfo<StComment>(myList);
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
		StNews news = stNewsMapper.selectByPrimaryKey(newsId);
		if(news==null||news.getState()==1)
			return false;
		return true;
	}
	@Override
	public KePuResult searchNews(String query, Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StNewsExample example=new StNewsExample();
		StNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		criteria.andTitleLike("%"+query+"%");
		PageHelper.startPage(page, size);
		List<StNews> myList = stNewsMapper.selectByExample(example);
		List<Map<String,String>> newsList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StNews news : myList) {
			temp=new HashMap<String, String>();
			temp.put("newsId", news.getUid()+"");
			temp.put("title", news.getTitle());
			temp.put("auchor", news.getKeywords()); //文章来源 2017-09-14 20:36:25
			temp.put("newsStyle", news.getNewsstyle()+"");
			temp.put("publishTime", DateUtil.formatDate(news.getUpdatetime(), MyConstant.updatetime));
			temp.put("pics", news.getNewsimages());
			temp.put("view", news.getView()+"");
			newsList.add(temp);
		}
		PageInfo<StNews> pageInfo=new PageInfo<StNews>(myList);
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
		StHotSearchExample example=new StHotSearchExample();
		StHotSearchExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		List<StHotSearch> list = hotSearchMapper.selectByExample(example);
		float maxSilimar=0l;
		int tempId=1;
		for (StHotSearch stHotSearch : list) {
			String word=stHotSearch.getWord();
			float current=StrSimilarityUtils.getSimilarityRatio(word, query);
			if(current>maxSilimar){
				maxSilimar=current;
				tempId=stHotSearch.getUid();
			}	
		}
		if(maxSilimar>0.4){
			StHotSearch r = hotSearchMapper.selectByPrimaryKey(tempId);
			r.setSearchnum(r.getSearchnum()+1);
			r.setUpdatetime(new Date());
			hotSearchMapper.updateByPrimaryKeySelective(r);
		}else{
			StHotSearch r =new StHotSearch();
			r.setWord(query);
			r.setCreatetime(new Date());
			r.setUpdatetime(new Date());
			hotSearchMapper.insertSelective(r);
		}
	}
	@Override
	public KePuResult getHotSearch() {
		Map<String,Object> map=new HashMap<String, Object>();
		StHotSearchExample example=new StHotSearchExample();
		StHotSearchExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		PageHelper.startPage(1, 20);
		List<StHotSearch> list = hotSearchMapper.selectByExample(example);
		List<String> r=new LinkedList<String>();
		for (StHotSearch stHotSearch : list) {
			r.add(stHotSearch.getWord());
		}
		map.put("hotWords", r);
		map.put("totalcount", r.size()+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getCommentReply(Long commentId,Integer userId,Integer page,Integer size){
		if(!checkComment(commentId))
			return KePuResult.ok(ResultConstant.code_yewu, "该评论已被删除或不存在", "");
		Map<String,Object> map=new HashMap<String, Object>();
		StReplyExample example=new StReplyExample();
		example.setOrderByClause("createTime");
		StReplyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andCommentidEqualTo(commentId);
		PageHelper.startPage(page, size);
		List<StReply> myList = replyMapper.selectByExample(example);
		List<Map<String,String>> replyList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StReply stReply : myList) {
			temp=new HashMap<String, String>();
			temp.put("replyId", stReply.getUid()+"");
			temp.put("createTime",DateUtil.formatDate(stReply.getCreatetime(), MyConstant.updatetime));
			temp.put("praiseNum", stReply.getPraisenum()+"");
			temp.put("comment", stReply.getContent());
			temp.put("userId", stReply.getUserid()+"");
			temp.put("nickName", stReply.getUsername());
			temp.put("avatar", stReply.getAvatar());
			String myPraise=jedisClient.hget("replyPraise", "replyPraise_"+stReply.getUid()+
					"_"+userId);
			if("0".equals(myPraise)||StringUtil.isEmpty(myPraise)){
				String t=checkPraise(2, userId, stReply.getUid());
				if("1".equals(t)){
				     jedisClient.hset("replyPraise", "replyPraise_"+stReply.getUid()+
							"_"+userId,"1");
				}
				myPraise=t;
			}
			temp.put("myPraise", StringUtil.isEmpty(myPraise)?"0":myPraise);
			replyList.add(temp);
		}
		PageInfo<StReply> pageInfo=new PageInfo<StReply>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("replyList", replyList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public Boolean checkComment(Long commentId) {
		StComment comment=commentMapper.selectByPrimaryKey(commentId);
		if(comment==null||comment.getState()==1)
			return false;
		return true;
	}
	@Override
	public String checkPraise(Integer type, Integer userId, Long typeId) {
		StVoteExample example=new StVoteExample();
		StVoteExample.Criteria criteria=example.createCriteria();
		criteria.andTypeEqualTo(type);
		criteria.andTypeidEqualTo(typeId);
		criteria.andUseridEqualTo(userId);
		criteria.andStatusEqualTo(0);
		List<StVote> list = voteMapper.selectByExample(example);
		return list.size()==0?"0":"1";
	}
	@Override
	public List<Integer> getNewsIdsByType(Integer type) {
		StNewsRelationExample example=new StNewsRelationExample();
		StNewsRelationExample.Criteria criteria=example.createCriteria();
		criteria.andClassfyidEqualTo(type);
		List<StNewsRelation> l = newsRelationMapper.selectByExample(example);
		List<Integer> list=new LinkedList<Integer>();
		for (StNewsRelation stNewsRelation : l) {
			list.add(stNewsRelation.getNewsid());
		}
		return list;
	}
	@Override
	public KePuResult getCommend() {
		Map<String,Object> map=new HashMap<String, Object>();
		List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		StNewsExample example=new StNewsExample();
		StNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause(" RAND() ");
		criteria.andStateEqualTo(0);
		criteria.andDraftEqualTo(0);
		PageHelper.startPage(1, 3);
		List<StNews> list = stNewsMapper.selectByExample(example);
		for (StNews stNews : list) {
			temp=new HashMap<String, String>();
			temp.put("newsId", stNews.getUid()+"");
			temp.put("newsStyle", stNews.getNewsstyle()+"");
			temp.put("pics", stNews.getNewsimages());
			temp.put("auchor", stNews.getKeywords()); //文章来源
			temp.put("publishTime",DateUtil.formatDate(stNews.getCreatetime(), MyConstant.updatetime));
			temp.put("title", stNews.getTitle());
			myList.add(temp);
		}
		map.put("newsList", myList);
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getQuestion(Integer newsId) {
		Map<String,Object> map=new HashMap<String, Object>();
		List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		StNewsQuestionExample example=new StNewsQuestionExample();
		StNewsQuestionExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		List<StNewsQuestion> list = newsQuestionMapper.selectByExample(example);
		for (StNewsQuestion question : list) {
			temp=new HashMap<String, String>();
			temp.put("qid", question.getUrid()+"");
			temp.put("optionA", question.getChoice1());
			temp.put("optionB", question.getChoice2());
			temp.put("optionC", question.getChoice3());
			temp.put("optionD", question.getChoice4());
			String[] type=question.getAnswer().split(",");
			temp.put("type", type.length==1?"1":"2");
			temp.put("title", question.getSubject());
			myList.add(temp);
		}
		map.put("questionList", myList);
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult submitAnswer(List<Map<String, String>> answerList,Integer userId) {
		Map<String,Object> mapR=new HashMap<String, Object>();
		List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		double score=0;
		for (Map<String, String> map : answerList) {
			String qid=map.get("qid");
			String answer=map.get("answer");
			// 检测是否已作答
			String rc=jedisClient.get("question_"+userId+"_"+qid);
			if(StringUtil.isEmpty(rc)){
				StNewsQuestionrecordExample example=new StNewsQuestionrecordExample();
				StNewsQuestionrecordExample.Criteria criteria=example.createCriteria();
				criteria.andUseridEqualTo(userId);
				criteria.andQuestionidEqualTo(Integer.valueOf(qid));
				List<StNewsQuestionrecord> m = recordMapper.selectByExample(example);
				if(m.size()!=0){
					jedisClient.set("question_"+userId+"_"+qid, "1");
					jedisClient.expire("question_"+userId+"_"+qid,172800);
					return KePuResult.ok(ResultConstant.code_yewu, "已作答", "");
				}
			}else
				return KePuResult.ok(ResultConstant.code_yewu, "已作答", "");
			String as=jedisClient.get("question_"+qid);
			if(StringUtil.isEmpty(as)){
				StNewsQuestion q = newsQuestionMapper.selectByPrimaryKey(Integer.valueOf(qid));
				as=q.getAnswer();
				jedisClient.set("question_"+qid, as);
				jedisClient.expire("question_"+qid, 172800);
			}
			Boolean c=StringUtil.rightAnswer(answer, as);
			StNewsQuestionrecord record=new StNewsQuestionrecord();
			record.setActiontime(new Date());
			record.setAnswer(answer);
			record.setQuestionid(Integer.valueOf(qid));
			record.setUserid(userId);
			recordMapper.insertSelective(record);
			temp=new HashMap<String, String>();
			temp.put("rightAnswer", as);
			temp.put("qid", qid);
			temp.put("match", c?"1":"0");
			myList.add(temp);
			if(c) score+=1;
		}
		StUserAccount acc = accountMapper.selectByPrimaryKey(userId);
		if(acc==null){
			acc=new StUserAccount();
			acc.setScore(Double.valueOf(score));
			acc.setCreatetime(new Date());
			acc.setUpdatetime(new Date());
			acc.setUserid(userId);
			StUser user=userMapper.selectByPrimaryKey(userId);
			acc.setMobile(user.getMobile());
			acc.setShowname(user.getNickname());
			acc.setVillage(user.getTownid());
			acc.setVillage(user.getArea());
			accountMapper.insertSelective(acc);
		}else{
			acc.setScore(acc.getScore()+score);
			acc.setUpdatetime(new Date());
			accountMapper.updateByPrimaryKeySelective(acc);
		}
		StActivityRecord record=new StActivityRecord();
		record.setScore(score);
		StUser user=userMapper.selectByPrimaryKey(userId);
		sysService.insertActivityRecord(user, record, 7, "答题得分");
		mapR.put("resultList", myList);
		mapR.put("score", score+"");
		return KePuResult.ok(ResultConstant.code_ok, "提交成功", mapR);
	}
	@Override
	public Boolean hasRecord(Integer newsId, Integer userId,Integer style) {
		StLogExample example=new StLogExample();
		StLogExample.Criteria criteria=example.createCriteria();
		criteria.andStyleEqualTo(style);
		criteria.andTypeEqualTo(newsId);
		criteria.andUseridEqualTo(userId);
		List<StLog> r = logMapper.selectByExample(example);
		return r.size()!=0;
	}
	@Override
	public Boolean hasComment(Integer newsId, Integer userId, Integer style) {
		if(style==1){
			StCommentExample example=new StCommentExample();
			StCommentExample.Criteria criteria=example.createCriteria();
			criteria.andNewsidEqualTo(newsId);
			criteria.andUseridEqualTo(userId);
			List<StComment> r = commentMapper.selectByExample(example);
			for (StComment stComment : r) {
				if(stComment.getContent().length()>=10)
					return true;
			}
			return false;
		}else if(style==2){
			StVillageNewsCommentExample example=new StVillageNewsCommentExample();
			StVillageNewsCommentExample.Criteria criteria=example.createCriteria();
			criteria.andNewsidEqualTo(newsId);
			criteria.andUseridEqualTo(userId);
			List<StVillageNewsComment> r = villageNewsCommentMapper.selectByExample(example);
			for (StVillageNewsComment stVillageNewsComment : r) {
				if(stVillageNewsComment.getContent().length()>=10)
					return true;
			}
			return false;
		}
		return false;
	}

}
