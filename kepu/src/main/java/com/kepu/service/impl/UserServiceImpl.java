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
import com.kepu.mapper.StAdviceMapper;
import com.kepu.mapper.StCollectionMapper;
import com.kepu.mapper.StCommentMapper;
import com.kepu.mapper.StLogMapper;
import com.kepu.mapper.StNewsDetailMapper;
import com.kepu.mapper.StNewsMapper;
import com.kepu.mapper.StNewsVoteMapper;
import com.kepu.mapper.StNoticeNewsCommentMapper;
import com.kepu.mapper.StNoticeNewsMapper;
import com.kepu.mapper.StNoticeNewsReplyMapper;
import com.kepu.mapper.StReplyMapper;
import com.kepu.mapper.StShareRecordMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.mapper.StVillageMapper;
import com.kepu.mapper.StVillageNewsCollectionMapper;
import com.kepu.mapper.StVillageNewsCommentMapper;
import com.kepu.mapper.StVillageNewsMapper;
import com.kepu.mapper.StVillageNewsRelationMapper;
import com.kepu.mapper.StVillageNewsReplyMapper;
import com.kepu.mapper.StVillageNewsVoteMapper;
import com.kepu.mapper.StVillageVoteMapper;
import com.kepu.mapper.StVoteMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StActivityRecord;
import com.kepu.pojo.StAdvice;
import com.kepu.pojo.StCollection;
import com.kepu.pojo.StCollectionExample;
import com.kepu.pojo.StComment;
import com.kepu.pojo.StLog;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StNewsExample;
import com.kepu.pojo.StNewsVote;
import com.kepu.pojo.StNewsVoteExample;
import com.kepu.pojo.StNoticeNews;
import com.kepu.pojo.StNoticeNewsComment;
import com.kepu.pojo.StNoticeNewsReply;
import com.kepu.pojo.StReply;
import com.kepu.pojo.StShareRecord;
import com.kepu.pojo.StShareRecordExample;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StUserExample;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageNews;
import com.kepu.pojo.StVillageNewsComment;
import com.kepu.pojo.StVillageNewsReply;
import com.kepu.pojo.StVote;
import com.kepu.pojo.news.NewsTemp;
import com.kepu.service.NewService;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.service.VillageService;
import com.kepu.util.DateUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.StringUtil;
import com.kepu.util.UUIDFactory;



@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private StUserMapper stUserMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private StCommentMapper commentMapper;
	@Autowired
	private StNewsMapper newsMapper;
	@Autowired
	private StReplyMapper stReplyMapper;
	@Autowired
	private StCollectionMapper collectionMapper;
	@Autowired
	private StAdviceMapper adviceMapper;
	@Autowired
	private StVoteMapper voteMapper;
	@Autowired
	private StNewsDetailMapper newsDetailMapper;
	@Autowired
	private StNewsVoteMapper newsVoteMapper;
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
	private StNoticeNewsMapper noticeNewsMapper;
	@Autowired
	private  StNoticeNewsCommentMapper noticeNewsCommentMapper;
	@Autowired
	private StNoticeNewsReplyMapper noticeNewsReplyMapper;
	@Autowired
	private StLogMapper stLogMapper;
	@Autowired
	private StVillageMapper stVillageMapper;
	@Autowired
	private StShareRecordMapper shareRecordMapper;
	@Autowired
	private SysService sysService;
	@Autowired
	private NewService newService;
	@Override
	public  KePuResult  createUser(String account,String mobile,String password,Integer area,String address,String mac,String appVersion) {
		if(!checkMobile(mobile)){
			return KePuResult.ok(ResultConstant.code_yewu, "该手机号已被注册", "error");
		}
		/*if(!checkAccount(account)){
			return KePuResult.ok(ResultConstant.code_yewu, "该用户名已被注册", "error");
		}*/
		if(StringUtil.isNotEmpty(appVersion)&&appVersion.compareTo("3.0.1")>=0){
			if(StringUtil.isNotEmpty(mac)&&!checkMac(mac)){
				return KePuResult.ok(ResultConstant.code_yewu, "该设备已注册过帐号", "error");
			}
		}
		StUser user=new StUser();
		user.setNickname(account);
		user.setAddress(address);
		user.setMobile(mobile);
		user.setArea(area);
		user.setState(0);
		user.setPassword(password);
		user.setMac(mac);
		user.setRegtime(new Date());
		user.setTownid(stVillageMapper.selectByPrimaryKey(area).getParent());
		stUserMapper.insertSelective(user);
		return KePuResult.ok(ResultConstant.code_ok, "ok", "");
	}

	@Override
	public Boolean checkMobile(String mobile) {
		StUserExample example=new StUserExample();
		StUserExample.Criteria criteria=example.createCriteria();
		criteria.andMobileEqualTo(mobile);
		List<StUser> list = stUserMapper.selectByExample(example);
		return list.size()==0;
	}

	@Override
	public Boolean checkAccount(String account) {
		StUserExample example=new StUserExample();
		StUserExample.Criteria criteria=example.createCriteria();
		criteria.andNicknameEqualTo(account);
		List<StUser> list = stUserMapper.selectByExample(example);
		return list.size()==0;
	}
	
	@Override
	public KePuResult login(String account, String password,String appVersion) {
		StUserExample example=new StUserExample();
		StUserExample.Criteria criteria=example.createCriteria();
		criteria.andMobileEqualTo(account);
		/*if(StringUtil.isMobileNO(account)){
		}else{
			criteria.andNicknameEqualTo(account);
		}*/
		criteria.andPasswordEqualTo(password);
		List<StUser> list = stUserMapper.selectByExample(example);
		boolean status=list.size()>0;
		if(status){
			StUser user=list.get(0);
			if(user.getState()==-1)
				return KePuResult.ok(ResultConstant.code_NoPermission, "帐号暂时无法登录", "");
			else{
				String token=UUIDFactory.getUUID();
				jedisClient.set("st_user_"+token, JsonUtils.objectToJson(user));
				jedisClient.expire("st_user_"+token, 31104000);
				jedisClient.set("token", token);
				jedisClient.expire(token, 31104000);
				Map<String,String> map=new HashMap<String, String>();
				map.put("token", token);
				map.put("userId", user.getUserid()+"");
				map.put("avatar", user.getAvatar());
				map.put("nickName", user.getNickname());
				map.put("score", sysService.getActivityScore(user.getUserid())+"");
				// 6-30  新版本添加 
				
				if(StringUtil.isNotEmpty(appVersion)&&appVersion.compareTo("3.0.1")>=0){
					map.put("villageId", user.getArea()+"");
					StVillage v = villageMapper.selectByPrimaryKey(user.getArea());
					map.put("villageName", v.getName());
				}
				if(user.getState()==2){
					user.setState(0);
					stUserMapper.updateByPrimaryKeySelective(user);
				}
				return KePuResult.ok(ResultConstant.code_ok, "登录成功",map);
			}
		}
		//  老用户专属提示
		StUserExample example2=new StUserExample();
		StUserExample.Criteria criteria2=example2.createCriteria();
		if(StringUtil.isMobileNO(account)){
			criteria2.andMobileEqualTo(account);
			List<StUser> list2 = stUserMapper.selectByExample(example2);
			if(list2.size()>0){
				StUser u=list2.get(0);
				if(u.getState()==2)
					return KePuResult.ok(ResultConstant.code_oldUser_login, "老用户密码重置为手机后6位","");
			}
		}
		return KePuResult.ok(ResultConstant.code_failue, "用户名或密码错误","");
	}

	@Override
	public KePuResult resetPassword(String mobile, String newpassword) {
		StUserExample example=new StUserExample();
		StUserExample.Criteria criteria=example.createCriteria();
		criteria.andMobileEqualTo(mobile);
		List<StUser> u=stUserMapper.selectByExample(example);
		if(u.size()==0)
			return KePuResult.build(ResultConstant.code_yewu, "没有该用户", "");
		StUser user=u.get(0);
		user.setPassword(newpassword);
		stUserMapper.updateByPrimaryKey(user);
		return KePuResult.ok(ResultConstant.code_ok, "修改成功", "");	}

	@Override
	public KePuResult logout(String token) {
		StUser user=getUserByToken(token);
		if(user!=null)
			jedisClient.del("st_user_"+token);
		return KePuResult.ok(ResultConstant.code_ok, "退出成功", "");
	}

	@Override
	public StUser getUserByToken(String token) {
		String s=jedisClient.get("st_user_"+token);
		if(StringUtil.isEmpty(s))
			return null;
		return JsonUtils.jsonToPojo(s, StUser.class);
	}

	@Override
	public StUser getUserById(Integer userId) {
		return stUserMapper.selectByPrimaryKey(userId);
	}

	@Override
	public void updateStUser(StUser user,String token) {
		user.setUpdatetime(new Date());
		stUserMapper.updateByPrimaryKeySelective(user);
		jedisClient.set("st_user_"+token, JsonUtils.objectToJson(user));
	}

	@Override
	public KePuResult sentComment(StUser user, Integer newsId, String comment) {
		StNews news = newsMapper.selectByPrimaryKey(newsId);
		if(news==null||news.getState()==1){
			LOG.info("该新闻已被删除或不存在");
			return KePuResult.ok(ResultConstant.code_yewu, "该新闻已被删除或不存在", "");
		}
		if(comment.length()>=10){
			if(!newService.hasComment(newsId, user.getUserid(), 1)){
				StActivityRecord record=new StActivityRecord();
				record.setScore(1.0);
				sysService.insertActivityRecord(user, record, 3, "发表新闻评论,新闻ID:"+newsId);
			}
		}
		StComment stComment=new StComment();
		stComment.setUserid(user.getUserid());
		stComment.setUsername(user.getNickname());
		stComment.setState(0);
		stComment.setAvatar(user.getAvatar());
		stComment.setNewsid(newsId);
		stComment.setReplynum(0);
		stComment.setPraisenum(0);
		stComment.setContent(comment);
		stComment.setCreatetime(new Date());
		commentMapper.insertSelective(stComment);
		//jedisClient.set("commentNum_"+newsId, value)
		news.setCommentcount(news.getCommentcount()+1);
		newsMapper.updateByPrimaryKeySelective(news);
		return KePuResult.ok(ResultConstant.code_ok, "发表成功", "");
	}

	@Override
	public KePuResult villageSentComment(StUser user, Integer newsId, String comment) {
		StVillageNews  news = villageNewsMapper.selectByPrimaryKey(newsId);
		if(news==null||news.getState()==1){
			LOG.info("该乡镇新闻已被删除或不存在");
			return KePuResult.ok(ResultConstant.code_yewu, "该乡镇新闻已被删除或不存在", "");
		}
		if(comment.length()>=10){
			if(!newService.hasComment(newsId, user.getUserid(), 2)){
				StActivityRecord record=new StActivityRecord();
				record.setScore(1.0);
				sysService.insertActivityRecord(user, record, 31, "发表乡镇新闻评论,新闻ID:"+newsId);
			}
		}
		StVillageNewsComment stComment=new StVillageNewsComment();
		stComment.setUserid(user.getUserid());
		stComment.setUsername(user.getNickname());
		stComment.setState(0);
		stComment.setAvatar(user.getAvatar());
		stComment.setNewsid(newsId);
		stComment.setReplynum(0);
		stComment.setPraisenum(0);
		stComment.setContent(comment);
		stComment.setCreatetime(new Date());
		villageNewsCommentMapper.insertSelective(stComment);
		//jedisClient.set("commentNum_"+newsId, value)
		news.setCommentcount(news.getCommentcount()+1);
		villageNewsMapper.updateByPrimaryKeySelective(news);
		return KePuResult.ok(ResultConstant.code_ok, "发表成功", "");
	}
	
	@Override
	public KePuResult noticeSentComment(StUser user, Integer newsId, String comment) {
		StNoticeNews  news = noticeNewsMapper.selectByPrimaryKey(newsId);
		if(news==null||news.getState()==1){
			LOG.info("该公告已被删除或不存在");
			return KePuResult.ok(ResultConstant.code_yewu, "该公告已被删除或不存在", "");
		}
		StNoticeNewsComment stComment=new StNoticeNewsComment();
		stComment.setUserid(user.getUserid());
		stComment.setUsername(user.getNickname());
		stComment.setState(0);
		stComment.setAvatar(user.getAvatar());
		stComment.setNewsid(newsId);
		stComment.setReplynum(0);
		stComment.setPraisenum(0);
		stComment.setContent(comment);
		stComment.setCreatetime(new Date());
		noticeNewsCommentMapper.insertSelective(stComment);
		//jedisClient.set("commentNum_"+newsId, value)
		news.setCommentcount(news.getCommentcount()+1);
		noticeNewsMapper.updateByPrimaryKeySelective(news);
		return KePuResult.ok(ResultConstant.code_ok, "发表成功", "");
	}
	@Override
	public KePuResult replyComment(StUser user, Long commentId,
			String comment) {
		StComment myComment = commentMapper.selectByPrimaryKey(commentId);
		if(myComment==null||myComment.getState()==1){
			LOG.info("该评论已被删除或不存在");
			return KePuResult.ok(ResultConstant.code_yewu, "该评论已被删除或不存在", "");
		}
		StReply reply=new StReply();
		reply.setUserid(user.getUserid());
		reply.setUsername(user.getNickname());
		reply.setState(0);
		reply.setAvatar(user.getAvatar());
		reply.setCommentid(commentId);
		reply.setPraisenum(0);
		reply.setContent(comment);
		reply.setCreatetime(new Date());
		stReplyMapper.insertSelective(reply);
		myComment.setReplynum(myComment.getReplynum()+1);
		commentMapper.updateByPrimaryKeySelective(myComment);
		LOG.info("回复成功");
		return KePuResult.ok(ResultConstant.code_ok, "回复成功", "");
	}
	@Override
	public KePuResult villageReplyComment(StUser user, Long commentId,
			String comment) {
		StVillageNewsComment myComment = villageNewsCommentMapper.selectByPrimaryKey(commentId);
		if(myComment==null||myComment.getState()==1){
			LOG.info("该评论已被删除或不存在");
			return KePuResult.ok(ResultConstant.code_yewu, "该评论已被删除或不存在", "");
		}
		StVillageNewsReply reply=new StVillageNewsReply();
		reply.setUserid(user.getUserid());
		reply.setUsername(user.getNickname());
		reply.setState(0);
		reply.setAvatar(user.getAvatar());
		reply.setCommentid(commentId);
		reply.setPraisenum(0);
		reply.setContent(comment);
		reply.setCreatetime(new Date());
		villageNewsReplyMapper.insertSelective(reply);
		myComment.setReplynum(myComment.getReplynum()+1);
		villageNewsCommentMapper.updateByPrimaryKeySelective(myComment);
		LOG.info("回复成功");
		return KePuResult.ok(ResultConstant.code_ok, "回复成功", "");
	}
	@Override
	public KePuResult noticeReplyComment(StUser user, Long commentId,
			String comment) {
		StNoticeNewsComment myComment = noticeNewsCommentMapper.selectByPrimaryKey(commentId);
		if(myComment==null||myComment.getState()==1){
			LOG.info("该评论已被删除或不存在");
			return KePuResult.ok(ResultConstant.code_yewu, "该评论已被删除或不存在", "");
		}
		StNoticeNewsReply reply=new StNoticeNewsReply();
		reply.setUserid(user.getUserid());
		reply.setUsername(user.getNickname());
		reply.setState(0);
		reply.setAvatar(user.getAvatar());
		reply.setCommentid(commentId);
		reply.setPraisenum(0);
		reply.setContent(comment);
		reply.setCreatetime(new Date());
		noticeNewsReplyMapper.insertSelective(reply);
		myComment.setReplynum(myComment.getReplynum()+1);
		noticeNewsCommentMapper.updateByPrimaryKeySelective(myComment);
		LOG.info("回复成功");
		return KePuResult.ok(ResultConstant.code_ok, "回复成功", "");
	}
	@Override
	public KePuResult likeNews(Integer newsId,Integer userId) {
		StCollectionExample example=new StCollectionExample();
		StCollectionExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		criteria.andUseridEqualTo(userId);
		List<StCollection> list = collectionMapper.selectByExample(example);
		if(list.size()>0){
			return KePuResult.ok(ResultConstant.code_ok, "已收藏", "");
		}
		StCollection collection=new StCollection();
		collection.setNewsid(newsId);
		collection.setUserid(userId);
		collection.setCreatetime(new Date());
		collectionMapper.insertSelective(collection);
		StNews news=newsMapper.selectByPrimaryKey(newsId);
		news.setLikecount(news.getLikecount()+1);
		newsMapper.updateByPrimaryKeySelective(news);
		//  写入缓存
		String myLikes=jedisClient.get("news_like_user_"+userId);
		List<Integer> likeList;
		if(StringUtil.isNotEmpty(myLikes)){
			likeList=JsonUtils.jsonToList(myLikes, Integer.class);
		}else{
			likeList=new LinkedList<Integer>();
		}
		likeList.add(newsId);
		jedisClient.set("news_like_user_"+userId,JsonUtils.objectToJson(likeList));
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
		StCollectionExample example=new StCollectionExample();
		StCollectionExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		criteria.andNewsidIn(values);
		collectionMapper.deleteByExample(example);
		jedisClient.del("news_like_user_"+userId);
		StNewsExample example2=new StNewsExample();
		StNewsExample.Criteria criteria2=example2.createCriteria();
		criteria2.andUidIn(values);
		List<StNews> r = newsMapper.selectByExample(example2);
		for (StNews stNews : r) {
			stNews.setLikecount(stNews.getLikecount()-1);
			newsMapper.updateByPrimaryKeySelective(stNews);
		}
		return KePuResult.ok(ResultConstant.code_ok, "删除成功", "");
	}
	
	@Override
	public KePuResult getMyLikeNews(Integer userId,Integer page,Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StCollectionExample example=new StCollectionExample();
		StCollectionExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		PageHelper.startPage(page, size);
		List<StCollection> myList = collectionMapper.selectByExample(example);
		List<Map<String,String>> collectionList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StCollection stCollection : myList) {
			temp=new HashMap<String, String>();
			Integer newsId=stCollection.getNewsid();
			temp.put("newsId", newsId+"");
			String newsCache=jedisClient.get("news_"+newsId);
			if(StringUtil.isNotEmpty(newsCache)){
				NewsTemp news=JsonUtils.jsonToPojo(newsCache, NewsTemp.class);
				temp.put("title", news.getTitle());
				temp.put("auchor", news.getNewsAuthor());
				temp.put("newsStyle", news.getNewsStyle());
				temp.put("publishTime", news.getUpdateTime());
				temp.put("pics", news.getNewsImages());
				temp.put("view", news.getView());
				
			}else{
				StNews news=newsMapper.selectByPrimaryKey(newsId);
				temp.put("title", news.getTitle());
				temp.put("auchor", news.getNewsauthor());
				temp.put("newsStyle", news.getNewsstyle()+"");
				temp.put("publishTime", DateUtil.formatDate(news.getUpdatetime(), MyConstant.updatetime));
				temp.put("pics", news.getNewsimages());
				temp.put("view", news.getView()+"");
				NewsTemp t=new NewsTemp(news.getTitle(), news.getNewsimages(), news.getNewsauthor(),
						 news.getNewsstyle()+"", DateUtil.formatDate(news.getUpdatetime(),MyConstant.updatetime),
						 news.getView()+"");
				jedisClient.set("news_"+newsId, JsonUtils.objectToJson(t));
				jedisClient.expire("news_"+newsId, 2592000);
			}
			collectionList.add(temp);
		}
		PageInfo<StCollection> pageInfo=new PageInfo<StCollection>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("collectionList", collectionList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult resetMyPassword(Integer userId,String password, String newPassword) {
		StUser user = stUserMapper.selectByPrimaryKey(userId);
		if(user==null)
			return KePuResult.ok(ResultConstant.code_yewu, "该用户不存在", "");
		String old=user.getPassword();
		if(!old.equals(password))
			return KePuResult.ok(ResultConstant.code_yewu, "原始密码不正确", "");
		user.setPassword(newPassword);
		stUserMapper.updateByPrimaryKeySelective(user);
		return KePuResult.ok(ResultConstant.code_ok, "修改成功,下次登录生效", "");
	}

	@Override
	public KePuResult sentAdvice(Integer userId, String comment) {
		StUser user = stUserMapper.selectByPrimaryKey(userId);
		if(user==null)
			return KePuResult.ok(ResultConstant.code_yewu, "该用户不存在", "");
		StAdvice advice=new StAdvice();
		advice.setAdvice(comment);
		advice.setUserid(userId);
		advice.setCreatetime(new Date());
		advice.setMobile(user.getMobile());
		adviceMapper.insertSelective(advice);
		return KePuResult.ok(ResultConstant.code_ok, "谢谢您的反馈", "");
	}

	@Override
	public KePuResult praise(Integer type, Long typeId,Integer userId) {
		if(type==1){
			StComment r = commentMapper.selectByPrimaryKey(typeId);
			if(r==null)
				return KePuResult.ok(ResultConstant.code_yewu, "typeId不正确", "");
			r.setPraisenum(r.getPraisenum()+1);
			commentMapper.updateByPrimaryKeySelective(r);
			StVote vote=new StVote();
			vote.setType(type);
			vote.setTypeid(typeId);
			vote.setVotetime(new Date());
			vote.setUserid(userId);
			jedisClient.hset("commentPraise", "commentPraise_"+typeId+
					"_"+userId,"1");
			voteMapper.insertSelective(vote);
			return KePuResult.ok(ResultConstant.code_ok, "点赞成功", "");
		}else if(type==2){
			StReply r=stReplyMapper.selectByPrimaryKey(typeId);
			if(r==null)
				return KePuResult.ok(ResultConstant.code_yewu, "typeId不正确", "");
			r.setPraisenum(r.getPraisenum()+1);
			stReplyMapper.updateByPrimaryKeySelective(r);
			StVote vote=new StVote();
			vote.setType(type);
			vote.setTypeid(typeId);
			vote.setVotetime(new Date());
			vote.setUserid(userId);
			jedisClient.hset("replyPraise", "replyPraise_"+typeId+
					"_"+userId,"1");
			voteMapper.insertSelective(vote);
			return KePuResult.ok(ResultConstant.code_ok, "点赞成功", "");
		}
		return KePuResult.ok(ResultConstant.code_yewu, "type不正确", "");
	}

	@Override
	public void saveUser(StUser user) {
		if(user!=null){
			if(user.getUserid()==null)
				stUserMapper.insertSelective(user);
			else
				stUserMapper.updateByPrimaryKeySelective(user);
		}
	}

	@Override
	public KePuResult dpNews(Integer newsId, Integer type, Integer userId,Integer operate) {
		StNewsVoteExample example=new StNewsVoteExample();
		StNewsVoteExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		criteria.andUseridEqualTo(userId);
		List<StNewsVote> resultList = newsVoteMapper.selectByExample(example);
		StNews news=newsMapper.selectByPrimaryKey(newsId);
		if(news==null){
			return KePuResult.ok(ResultConstant.code_yewu, "该新闻已被删除或不存在", "");
		}
		if(resultList.size()==0&&operate==1){
			StNewsVote v=new StNewsVote();
			if(type==1){
				v.setLiked(1);
				news.setVotenum(news.getVotenum()+1);
			}
			if(type==2){
				v.setDislike(1);
				news.setDislikenum(news.getDislikenum()+1);
			}
			newsMapper.updateByPrimaryKeySelective(news);
			// 点赞积分  begin
			StActivityRecord record=new StActivityRecord();
			record.setScore(1.0);
			StUser user = getUserById(userId);
			sysService.insertActivityRecord(user, record, 2, "新闻点赞,新闻ID:"+newsId);
			// end
			v.setVotetime(new Date());
			v.setNewsid(newsId);
			v.setUserid(userId);
			newsVoteMapper.insertSelective(v);
		}else if(resultList.size()!=0){
			StNewsVote v=resultList.get(0);
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
			newsMapper.updateByPrimaryKeySelective(news);
			newsVoteMapper.updateByPrimaryKeySelective(v);
		}
		return KePuResult.ok(ResultConstant.code_ok, "操作成功", "");
	}

	@Override
	public Boolean checkMac(String mac) {
		StUserExample example=new StUserExample();
		StUserExample.Criteria criteria=example.createCriteria();
		criteria.andMacEqualTo(mac);
		List<StUser> list = stUserMapper.selectByExample(example);
		return list.size()==0;
	}

	@Override
	public void saveLog(StLog log) {
		stLogMapper.insertSelective(log);	
	}

	@Override
	public KePuResult share(Integer type, Integer typeId, Integer userId) {
		if(type==1){
			StNews news = newsMapper.selectByPrimaryKey(typeId);
			if(news==null)
				return KePuResult.ok(ResultConstant.code_yewu, "新闻不存在", "");
		}
		if(type==2){
			StVillageNews news = villageNewsMapper.selectByPrimaryKey(typeId);
			if(news==null)
				return KePuResult.ok(ResultConstant.code_yewu, "乡镇新闻不存在", "");
		}
		StShareRecordExample example=new StShareRecordExample();
		StShareRecordExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		criteria.andTypeEqualTo(type);
		criteria.andTypeidEqualTo(typeId);
		List<StShareRecord> r = shareRecordMapper.selectByExample(example);
		if(r.size()<4){
			StShareRecord record=new StShareRecord();
			record.setCreatetime(new Date());
			record.setType(type);
			record.setTypeid(typeId);
			record.setUserid(userId);
			shareRecordMapper.insertSelective(record);
			StUser user=getUserById(userId);
			if(type==1){
				StActivityRecord arecord=new StActivityRecord();
				arecord.setScore(1.0);
				sysService.insertActivityRecord(user, arecord, 4, "分享新闻,新闻ID:"+typeId);
			}else if(type==2){
				StActivityRecord arecord=new StActivityRecord();
				arecord.setScore(1.0);
				sysService.insertActivityRecord(user, arecord, 41, "分享乡镇新闻,新闻ID:"+typeId);
			}
			return KePuResult.ok(ResultConstant.code_ok, "分享成功", "");
		}else{
			return KePuResult.ok(ResultConstant.code_ok, "超出分享次数限制,本次分享不计分", "");
		}
	}

	

	

}
