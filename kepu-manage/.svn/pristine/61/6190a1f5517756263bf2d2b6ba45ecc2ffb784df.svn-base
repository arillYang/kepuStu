package com.kepu.service.impl;



import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.kepu.mapper.StClassifyMapper;
import com.kepu.mapper.StClassifyStatisticMapper;
import com.kepu.mapper.StCollectionMapper;
import com.kepu.mapper.StCommentMapper;
import com.kepu.mapper.StLogMapper;
import com.kepu.mapper.StNewsDetailMapper;
import com.kepu.mapper.StNewsMapper;
import com.kepu.mapper.StPushMapper;
import com.kepu.mapper.StReplyMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.mapper.StVillageMapper;
import com.kepu.mapper.StVoteMapper;
import com.kepu.mapper.TMenuMapper;
import com.kepu.mapper.TRelationMapper;
import com.kepu.mapper.TRoleMapper;
import com.kepu.mapper.TUserMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StAdvice;
import com.kepu.pojo.StClassify;
import com.kepu.pojo.StClassifyExample;
import com.kepu.pojo.StClassifyStatistic;
import com.kepu.pojo.StClassifyStatisticExample;
import com.kepu.pojo.StCollection;
import com.kepu.pojo.StCollectionExample;
import com.kepu.pojo.StComment;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StNewsExample;
import com.kepu.pojo.StPush;
import com.kepu.pojo.StPushExample;
import com.kepu.pojo.StReply;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StUserExample;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageExample;
import com.kepu.pojo.StVote;
import com.kepu.pojo.TMenu;
import com.kepu.pojo.TMenuExample;
import com.kepu.pojo.TRelation;
import com.kepu.pojo.TRelationExample;
import com.kepu.pojo.TRole;
import com.kepu.pojo.TRoleExample;
import com.kepu.pojo.TUser;
import com.kepu.pojo.TUserExample;
import com.kepu.pojo.news.NewsTemp;
import com.kepu.pojo.user.AdviceFeedback;
import com.kepu.pojo.user.AuthorResult;
import com.kepu.pojo.user.ClassifyResult;
import com.kepu.pojo.user.ClassifyResultEx;
import com.kepu.pojo.user.UserStatistic;
import com.kepu.pojo.user.active.CountyActive;
import com.kepu.pojo.user.active.CountySS;
import com.kepu.pojo.user.active.NoActive;
import com.kepu.pojo.user.active.PeopleSS;
import com.kepu.pojo.user.active.TownActive;
import com.kepu.pojo.user.active.TownSS;
import com.kepu.pojo.user.click.ClickResult;
import com.kepu.pojo.user.detail.DetailResult;
import com.kepu.pojo.user.read.ReadResult;
import com.kepu.service.UserService;
import com.kepu.service.VillageNewsService;
import com.kepu.util.DateUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.MD5Util;
import com.kepu.util.StringUtil;
import com.kepu.util.UUIDFactory;

import sun.rmi.runtime.Log;



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
	private StVillageMapper villageMapper;
	@Autowired
	private VillageNewsService villageNewsService;
	@Autowired
	private TUserMapper tUserMapper;
	@Autowired
	private StPushMapper pushMapper;
	@Autowired
	private StClassifyMapper classifyMapper;
	@Autowired
	private StLogMapper logMapper;
	@Autowired
	private TMenuMapper menuMapper;
	@Autowired
	private TRoleMapper roleMapper;
	@Autowired
	private TRelationMapper relationMapper;
	@Autowired
	private StClassifyStatisticMapper classifyStatisticMapper;
	@Override
	public KePuResult createUser(String account,String mobile,String password,Integer area,String address) {
		
		if(!checkMobile(mobile)){
			return KePuResult.ok(ResultConstant.code_yewu, "错误信息", "error");
		}
		if(!checkAccount(account)){
			return KePuResult.ok(ResultConstant.code_yewu, "错误信息", "error");
		}
		StUser user=new StUser();
		user.setNickname(account);
		user.setAddress(address);
		user.setMobile(mobile);
		user.setArea(area);
		user.setState(0);
		user.setPassword(password);
		user.setRegtime(new Date());
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
	public KePuResult login(String account, String password) {
		StUserExample example=new StUserExample();
		StUserExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isMobileNO(account)){
			criteria.andMobileEqualTo(account);
		}else{
			criteria.andNicknameEqualTo(account);
		}
		criteria.andPasswordEqualTo(password);
		List<StUser> list = stUserMapper.selectByExample(example);
		boolean status=list.size()>0;
		if(status){
			StUser user=list.get(0);
			if(user.getState()==-1)
				return KePuResult.ok(ResultConstant.code_NoPermission, "错误信息", "");
			else{
				String token=UUIDFactory.getUUID();
				jedisClient.set("st_user_"+token, JsonUtils.objectToJson(user));
				jedisClient.expire("st_user_"+token, 2592000);
				Map<String,String> map=new HashMap<String, String>();
				map.put("token", token);
				map.put("userId", user.getUserid()+"");
				map.put("avatar", user.getAvatar());
				map.put("nickName", user.getNickname());
				
				return KePuResult.ok(ResultConstant.code_ok, "错误信息",map);
			}
		}
		return KePuResult.ok(ResultConstant.code_yewu, "错误信息","");
	}

	@Override
	public KePuResult resetPassword(String mobile, String newpassword) {
		StUserExample example=new StUserExample();
		StUserExample.Criteria criteria=example.createCriteria();
		criteria.andMobileEqualTo(mobile);
		List<StUser> u=stUserMapper.selectByExample(example);
		if(u.size()==0)
			return KePuResult.build(ResultConstant.code_yewu, "错误信息", "");
		StUser user=u.get(0);
		user.setPassword(newpassword);
		stUserMapper.updateByPrimaryKey(user);
		return KePuResult.ok(ResultConstant.code_ok, "错误信息", "");	}

	@Override
	public KePuResult logout(String token) {
		StUser user=getUserByToken(token);
		if(user!=null)
			jedisClient.del("st_user_"+token);
		return KePuResult.ok(ResultConstant.code_ok, "错误信息", "");
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
			return KePuResult.ok(ResultConstant.code_yewu, "错误信息", "");
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
		news.setCommentcount(news.getCommentcount()+1);
		newsMapper.updateByPrimaryKeySelective(news);
		return KePuResult.ok(ResultConstant.code_ok, "错误信息", "");
	}

	@Override
	public KePuResult replyComment(StUser user, Long commentId,
			String comment) {
		StComment myComment = commentMapper.selectByPrimaryKey(commentId);
		if(myComment==null||myComment.getState()==1){
			LOG.info("错误信息");
			return KePuResult.ok(ResultConstant.code_yewu, "错误信息", "");
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
		return KePuResult.ok(ResultConstant.code_ok, "错误信息", "");
	}

	@Override
	public KePuResult likeNews(Integer newsId,Integer userId) {
		StCollectionExample example=new StCollectionExample();
		StCollectionExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		criteria.andUseridEqualTo(userId);
		List<StCollection> list = collectionMapper.selectByExample(example);
		if(list.size()>0){
			return KePuResult.ok(ResultConstant.code_ok, "错误信息", "");
		}
		StCollection collection=new StCollection();
		collection.setNewsid(newsId);
		collection.setUserid(userId);
		collection.setCreatetime(new Date());
		collectionMapper.insertSelective(collection);
		StNews news=newsMapper.selectByPrimaryKey(newsId);
		news.setLikecount(news.getLikecount()+1);
		newsMapper.updateByPrimaryKeySelective(news);
		//  д�뻺��
		String myLikes=jedisClient.get("news_like_user_"+userId);
		List<Integer> likeList;
		if(StringUtil.isNotEmpty(myLikes)){
			likeList=JsonUtils.jsonToList(myLikes, Integer.class);
		}else{
			likeList=new LinkedList<Integer>();
		}
		likeList.add(newsId);
		jedisClient.set("news_like_user_"+userId,JsonUtils.objectToJson(likeList));
		LOG.info("错误信息");
		return KePuResult.ok(ResultConstant.code_ok, "错误信息", "");
	}

	@Override
	public KePuResult deletelikeNews(String[] newsIds, Integer userId) {
		List<Integer> values=null;
		try {
			values = StringUtil.asIntegerList(newsIds);
		} catch (Exception e) {
			e.printStackTrace();
			return KePuResult.ok(ResultConstant.code_yewu, "newsId错误", "");
		}
		if(values==null)
			return KePuResult.ok(ResultConstant.code_yewu, "错误信息", "");
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
		return KePuResult.ok(ResultConstant.code_ok, "错误信息", "");
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
		return KePuResult.ok(ResultConstant.code_ok, "错误信息", map);
	}

	@Override
	public KePuResult resetMyPassword(Integer userId,String password, String newPassword) {
		StUser user = stUserMapper.selectByPrimaryKey(userId);
		if(user==null)
			return KePuResult.ok(ResultConstant.code_yewu, "错误信息", "");
		String old=user.getPassword();
		if(!old.equals(password))
			return KePuResult.ok(ResultConstant.code_yewu, "错误信息", "");
		user.setPassword(newPassword);
		stUserMapper.updateByPrimaryKeySelective(user);
		return KePuResult.ok(ResultConstant.code_ok, "错误信息", "");
	}

	@Override
	public KePuResult sentAdvice(Integer userId, String comment) {
		StUser user = stUserMapper.selectByPrimaryKey(userId);
		if(user==null)
			return KePuResult.ok(ResultConstant.code_yewu, "错误信息", "");
		StAdvice advice=new StAdvice();
		advice.setAdvice(comment);
		advice.setUserid(userId);
		advice.setCreatetime(new Date());
		adviceMapper.insertSelective(advice);
		return KePuResult.ok(ResultConstant.code_ok, "错误信息", "");
	}

	@Override
	public KePuResult praise(Integer type, Long typeId,Integer userId) {
		if(type==1){
			StComment r = commentMapper.selectByPrimaryKey(typeId);
			if(r==null)
				return KePuResult.ok(ResultConstant.code_yewu, "typeId", "");
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
			return KePuResult.ok(ResultConstant.code_ok, "错误信息", "");
		}else if(type==2){
			StReply r=stReplyMapper.selectByPrimaryKey(typeId);
			if(r==null)
				return KePuResult.ok(ResultConstant.code_yewu, "typeId����ȷ", "");
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
			return KePuResult.ok(ResultConstant.code_ok, "错误信息", "");
		}
		return KePuResult.ok(ResultConstant.code_yewu, "错误信息", "");
	}

	@Override
	public void saveUser(StUser user) {
		stUserMapper.insertSelective(user);
	}

	
	@Override
	public Map<String, Object> findStUser(PageBean pageBean, StUser stUser) {
		List<Integer> ll=new LinkedList<Integer>();
		if(stUser.getArea()!=null&&stUser.getArea()<=50)
			ll=villageNewsService.getVillageIds(stUser.getArea());
		Map<String,Object> map=new HashMap<String, Object>();
		StUserExample example=new StUserExample();
		example.setOrderByClause("regtime desc");
		StUserExample.Criteria criteria=example.createCriteria();
		if(pageBean!=null)
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Integer area=stUser.getArea();
		Integer town=stUser.getTownid();
		if(area!=null){
			criteria.andAreaEqualTo(stUser.getArea());
		}
		if(town!=null){
			criteria.andTownidEqualTo(town);
		}
		List<StUser> list = stUserMapper.selectByExample(example);
		PageInfo<StUser> pageInfo=new PageInfo<StUser>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		criteria.andSexEqualTo(1);
		Integer manNum=stUserMapper.countByExample(example);
		map.put("manNum", manNum+"");
		return map;
	}

	@Override
	public List<StVillage> getAddressByParent(Integer parentId) {
		StVillageExample example=new StVillageExample();
		StVillageExample.Criteria criteria=example.createCriteria();
		if(parentId!=null)
			criteria.andParentEqualTo(parentId);
		return villageMapper.selectByExample(example);
	}

	@Override
	public Map<String, Object> getStatisticByTownId(PageBean pageBean,String townId) {
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		List<UserStatistic> list=stUserMapper.getStatisticByTownId(Integer.valueOf(townId));
		PageInfo<UserStatistic> pageInfo=new PageInfo<UserStatistic>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		map.put("count", stUserMapper.getStatisticByTownIdNumber(Integer.valueOf(townId)));
		return map;
	}

	@Override
	public Map<String, Object> getStatistic(PageBean pageBean) {
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		List<UserStatistic> list = stUserMapper.getStatistic();
		PageInfo<UserStatistic> pageInfo=new PageInfo<UserStatistic>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		map.put("count", stUserMapper.getStatisticNumber());
		return map;
	}

	@Override
	public Map<String,Object> getPushRecord(PageBean pageBean,StPush push,Integer townId,Integer villageId) {
		List<Integer> ll=new LinkedList<Integer>();
		Map<String,Object> map=new HashMap<String, Object>();
		StPushExample example=new StPushExample();
		example.setOrderByClause("pushtime desc");
		StPushExample.Criteria criteria=example.createCriteria();
		if(pageBean!=null)
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		if(townId!=0){
			if(villageId==0)
				criteria.andTownEqualTo(townId);
			else
				criteria.andVillageEqualTo(villageId);
		}
		List<StPush> list = pushMapper.selectByExample(example);
		PageInfo<StPush> pageInfo=new PageInfo<StPush>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		return map;
	}

	@Override
	public void savePushRecord(StPush push) {
		pushMapper.insertSelective(push);
	}

	@Override
	public List<String> getPushCids(List<Integer> towns, List<Integer> countrys) {
		Map<String,Object> param=new HashMap<String, Object>();
		if(towns.size()!=0)
			param.put("town", towns);
		if(countrys.size()!=0)
			param.put("country", countrys);
		return stUserMapper.getPushCids(param);
	}

	@Override
	public TUser login(TUser user) {
		TUserExample example=new TUserExample();
		TUserExample.Criteria criteria=example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		String real=MD5Util.GetMD5Code(user.getPassword());
		criteria.andPasswordEqualTo(real);
		List<TUser> list = tUserMapper.selectByExample(example);
		if(list.size()==0)
			return null;
		else{
			user=list.get(0);
			user.setLastlogintime(new Date());
			tUserMapper.updateByPrimaryKeySelective(user);
		}
		return user;
	}

	@Override
	public void saveTUser(TUser tUser) {
		if(tUser!=null){
			tUser.setUpdatetime(new Date());
			if(tUser.getId()==null){
				tUser.setCreatetime(new Date());
				tUser.setState(0);
				tUser.setPassword(MD5Util.GetMD5Code(tUser.getPassword()));
				tUserMapper.insertSelective(tUser);
			}else{
				if(StringUtil.isNotEmpty(tUser.getPassword()))
					tUser.setPassword(MD5Util.GetMD5Code(tUser.getPassword()));
				tUserMapper.updateByPrimaryKeySelective(tUser);
			}
		}
		
	}

	@Override
	public void deleteTUserById(Integer tUserId, Integer type) {
		TUser tuser = tUserMapper.selectByPrimaryKey(tUserId);
		if(tuser!=null){
			if(type==1)
				tuser.setState(0);
			else
				tuser.setState(1);
			tUserMapper.updateByPrimaryKeySelective(tuser);
		}
		
	}

	@Override
	public Map<String, Object> findTUser(PageBean pageBean, TUser tUser) {
		Map<String,Object> map=new HashMap<String, Object>();
		TUserExample  example=new TUserExample();
		example.setOrderByClause("createtime desc");
		TUserExample.Criteria criteria=example.createCriteria();
		if(pageBean!=null)
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		if(StringUtil.isNotEmpty(tUser.getUsername())){
			criteria.andUsernameLike("%"+tUser.getUsername()+"%");
		}
		List<TUser> list = tUserMapper.selectByExample(example);
		PageInfo<TUser> pageInfo=new PageInfo<TUser>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		return map;
	}
	
	@Override
	public TUser getTuserById(Integer tUserId) {
		return tUserMapper.selectByPrimaryKey(tUserId);
	}

	@Override
	public Map<String, Object> findStAdvice(PageBean pageBean, StAdvice advice) {
		Map<String,Object> map=new HashMap<String, Object>();
		if(pageBean!=null)
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		List<AdviceFeedback> list = stUserMapper.getAdviceFeedback();
		PageInfo<AdviceFeedback> pageInfo=new PageInfo<AdviceFeedback>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		return map;
	}

	@Override
	public Map<String, Object> getCountySS(Date d1,Date d2,Integer c1,Integer c2,PageBean pageBean,Integer townId) {
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> param=new HashMap<String, Object>();
		if(d1!=null)
			param.put("d1", d1);
		if(d2!=null)
			param.put("d2", d2);
		param.put("c1", c1==null?0:c1);
		if(c2!=null)
			param.put("c2", c2);
		param.put("townId", townId);
		List<CountySS> list = stUserMapper.getCountySS(param);
		if(c1==null||c1.intValue()==0){
			Map<Integer, Integer> m = getAllActiveNum(townId);
			for (CountySS countySS : list) {
				Integer am=m.get(countySS.getArea())==null?0:m.get(countySS.getArea());
				countySS.setPeople(countySS.getPeople()+(countySS.getTotal()-am));
			}
		}
		if(pageBean.getPage()==1){
			int stotal=getPeopleNum(townId,-1);
			int people=0;
			int view=0;
			for (CountySS countySS : list) {
				people+=countySS.getPeople();
				view+=countySS.getView();
			}
			map.put("stotal", stotal);
			map.put("people", people);
			map.put("view", view);
		}
		PageInfo<CountySS> pageInfo=new PageInfo<CountySS>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		return map;
	}

	@Override
	public Map<String, Object> getTownSS(Date d1,Date d2,Integer c1,Integer c2,PageBean pageBean) {
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> param=new HashMap<String, Object>();
		if(d1!=null)
			param.put("d1", d1);
		if(d2!=null)
			param.put("d2", d2);
		param.put("c1", c1==null?0:c1);
		if(c2!=null)
			param.put("c2", c2);
		List<TownSS> list = stUserMapper.getTownSS(param);
		if(c1==null||c1.intValue()==0){
			Map<Integer, Integer> m = getAllActiveNum(null);
			for (TownSS townSS : list) {
				Integer am=m.get(townSS.getTownId())==null?0:m.get(townSS.getTownId());
				townSS.setPeople(townSS.getPeople()+(townSS.getTotal()-am));
			}
		}
		PageInfo<TownSS> pageInfo=new PageInfo<TownSS>(list);
		if(pageBean.getPage()==1){
			int stotal=0;
			int people=0;
			int view=0;
			for (TownSS townSS : list) {
				stotal+=townSS.getTotal();
				people+=townSS.getPeople();
				view+=townSS.getView();
			}
			map.put("stotal", stotal);
			map.put("people", people);
			map.put("view", view);
		}
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		return map;
	}

	@Override
	public Map<String, Object> getClickTown(Date d1, Date d2, PageBean pageBean) {
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> param=new HashMap<String, Object>();
		if(d1!=null)
			param.put("d1", d1);
		if(d2!=null)
			param.put("d2", d2);
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		List<ClickResult> list = stUserMapper.getClickTown(param);
		PageInfo<ClickResult> pageInfo=new PageInfo<ClickResult>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		if(pageBean.getPage()==1){
			int stotal=0;
			int stype1=0;
			int stype2=0;
			int stype3=0;
			int stype4=0;
			for (ClickResult clickResult : list) {
				stotal+=clickResult.getTotal();
				stype1+=clickResult.getType1();
				stype2+=clickResult.getType2();
				stype3+=clickResult.getType3();
				stype4+=clickResult.getType4();
			}
			map.put("stotal", stotal);
			map.put("stype1", stype1);
			map.put("stype2", stype2);
			map.put("stype3", stype3);
			map.put("stype4", stype4);
		}
		return map;
	}

	@Override
	public Map<String, Object> getClickCounty(Date d1, Date d2,
			PageBean pageBean, Integer townId) {
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> param=new HashMap<String, Object>();
		if(d1!=null)
			param.put("d1", d1);
		if(d2!=null)
			param.put("d2", d2);
		param.put("townId", townId);
		List<ClickResult> list = stUserMapper.getClickCounty(param);
		if(pageBean.getPage()==1){
			int stotal=0;
			int stype1=0;
			int stype2=0;
			int stype3=0;
			int stype4=0;
			for (ClickResult clickResult : list) {
				stotal+=clickResult.getTotal();
				stype1+=clickResult.getType1();
				stype2+=clickResult.getType2();
				stype3+=clickResult.getType3();
				stype4+=clickResult.getType4();
			}
			map.put("stotal", stotal);
			map.put("stype1", stype1);
			map.put("stype2", stype2);
			map.put("stype3", stype3);
			map.put("stype4", stype4);
		}
		PageInfo<ClickResult> pageInfo=new PageInfo<ClickResult>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		return map;
	}

	@Override
	public Map<String, Object> getReadDetail(Date d1, Date d2,String query) {
		StNewsExample example=new StNewsExample();
		StNewsExample.Criteria criteria=example.createCriteria();
		Map<String,Object> map=new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(query)){
			criteria.andTitleLike("%"+query+"%");
			List<StNews> newList = newsMapper.selectByExample(example);
			if(newList.size()!=0){
				StNews news = newList.get(0);
				PageHelper.startPage(1,100);
				Map<String,Object> param=new HashMap<String, Object>();
				if(d1!=null)
					param.put("d1", d1);
				if(d2!=null)
					param.put("d2", d2);
				param.put("newsId", news.getUid());
				int view=0;
				List<ReadResult> list = stUserMapper.getReadDetail(param);
				for (ReadResult readResult : list) {
					view+=readResult.getNum();
				}
				PageInfo<ReadResult> pageInfo=new PageInfo<ReadResult>(list);
				map.put("total", pageInfo.getTotal());
				map.put("list", list);
				map.put("view", view);
				map.put("realTitle", news.getTitle());
			}else{
				map.put("total",0L);
				map.put("list", null);
			}
		}else{
			map.put("total",0L);
			map.put("list", null);
		}
		return map;
	}

	@Override
	public Map<String, Object> getPeopleSS(Date d1, Date d2, Integer c1,
			Integer c2, PageBean pageBean, Integer villageId) {
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> param=new HashMap<String, Object>();
		if(d1!=null)
			param.put("d1", d1);
		if(d2!=null)
			param.put("d2", d2);
		if(c1!=null)
			param.put("c1", c1);
		if(c2!=null)
			param.put("c2", c2);
		param.put("villageId", villageId);
		List<PeopleSS> list = stUserMapper.getPeopleSS(param);
		PageInfo<PeopleSS> pageInfo=new PageInfo<PeopleSS>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		if(pageBean.getPage()==1){
			int stotal=getPeopleNum(-1,villageId);
			int people=0;
			int view=0;
			PageHelper.startPage(1, 20000);
			List<PeopleSS> list2 = stUserMapper.getPeopleSS(param);
			for (PeopleSS townSS : list2) {
				people+=townSS.getPeople();
				view+=townSS.getView();
			}
			map.put("stotal", stotal);
			map.put("people", people);
			map.put("view", view);
		}
		return map;
	}

	@Override
	public Map<String, Object> getDetail(Date d1, Date d2, Integer townId) {
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> param;
		StClassifyExample example=new StClassifyExample();
		StClassifyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		List<StClassify> classList = classifyMapper.selectByExample(example);
		List<StVillage> villageList = getAddressByParent(townId);
		int vNum=villageList.size();
		int cNum=classList.size();
		String[][] data=new String[vNum][cNum+1];
		param=new HashMap<String, Object>();
		if(d1!=null)
			param.put("d1", d1);
		if(d2!=null)
			param.put("d2", d2);
		List<DetailResult> list;
		if(townId==null||townId.intValue()==-1)
			list= stUserMapper.getDetail(param);
		else{
			param.put("townId", townId);
			list=stUserMapper.getDetailCounty(param);
		}
		int stotal=0;
		for (int i = 0; i < list.size(); i++) {
			data[i][0]=list.get(i).getView()+"";
			stotal+=list.get(i).getView();
		}
		int j=1;
		int sp[]=new int[cNum];
		DecimalFormat df=new DecimalFormat("0.00");
		for (StClassify stClassify : classList) {
			param=new HashMap<String, Object>();
			if(d1!=null)
				param.put("d1", d1);
			if(d2!=null)
				param.put("d2", d2);
			param.put("classfyId", stClassify.getUid());
			List<DetailResult> list1 ;
			if(!(townId==null||townId.intValue()==-1)){
				param.put("townId", townId);
				list1=stUserMapper.getDetailCounty(param);
			}
			else
				list1=stUserMapper.getDetail(param);
			for (int i = 0; i < list1.size(); i++) {
				if(data[i][0].equals("0")){
					data[i][j]="0.00";
				}
				else{
					data[i][j]=df.format(list1.get(i).getView()*1.0/Integer.valueOf(data[i][0]));
					sp[j-1]+=list1.get(i).getView();
				}
			}
			j++;
		}
		map.put("villageList",villageList);
		map.put("classList",classList);
		map.put("total",vNum);
		map.put("data", data);
		map.put("stotal", stotal);
		map.put("sp", sp);
		return map;
	}

	@Override
	public Map<Integer, Integer> getActiveTown() {
		Map<Integer, Integer> r=new HashMap<Integer, Integer>();
		HashMap<String, Object> param=new HashMap<String, Object>();
		Date now=new Date();
		param.put("d1", DateUtil.addDate(now, -30));
		param.put("d2", now);
		List<TownActive> m = stUserMapper.getTownActive(param);
		for (TownActive townActive : m) {
			r.put(townActive.getTownId(), townActive.getNum());
		}
		return r;
	}

	@Override
	public Map<Integer, Integer> getActiveArea(Integer townId) {
		Map<Integer, Integer> r=new HashMap<Integer, Integer>();
		HashMap<String, Object> param=new HashMap<String, Object>();
		Date now=new Date();
		param.put("d1", DateUtil.addDate(now, -30));
		param.put("d2", now);
		param.put("townId", townId);
		List<CountyActive> m = stUserMapper.getCountyActive(param);
		for (CountyActive townActive : m) {
			r.put(townActive.getArea(), townActive.getNum());
		}
		return r;
	}

	@Override
	public Map<Integer, Integer> getAllActiveNum(Integer townId) {
		Map<Integer, Integer> r=new HashMap<Integer, Integer>();
		List<NoActive> result=null;
		if(townId!=null){
			HashMap<String, Object> param=new HashMap<String, Object>();
			param.put("townId", townId);
			 result = stUserMapper.getAllActiveNumCounty(param);
		}else{
			 result =stUserMapper.getAllActiveNumTown();
		}
		for (NoActive noActive : result) {
			r.put(noActive.getUid(), noActive.getNum());
		}
		return r;
	}

	@Override
	public Map<String, Object> getMenu(Integer roleId) {
		TMenuExample example=new TMenuExample();
		TMenuExample.Criteria criteria=example.createCriteria();
		criteria.andParentEqualTo(-1);
		List<TMenu> pm = menuMapper.selectByExample(example);
		TRelationExample example3=new TRelationExample();
		example3.setOrderByClause("uid asc");
		TRelationExample.Criteria criteria3=example3.createCriteria();
		criteria3.andRoleidEqualTo(roleId);
		List<TRelation> relation = relationMapper.selectByExample(example3);
		LinkedList<Integer> menus=new LinkedList<Integer>();
		for (TRelation tRelation : relation) {
			menus.add(tRelation.getMenuid());
		}
		Map<String, Object> r=new LinkedHashMap<String, Object>();
		for (TMenu tMenu : pm) {
			TMenuExample example2=new TMenuExample();
			TMenuExample.Criteria criteria2=example2.createCriteria();
			criteria2.andParentEqualTo(tMenu.getUid());
			criteria2.andUidIn(menus);
			List<TMenu> pm2 = menuMapper.selectByExample(example2);
			if(pm2.size()!=0)
				r.put(tMenu.getMenuname(), pm2);
		}
		return r;
	}

	@Override
	public List<TRole> getRoleList() {
		TRoleExample example=new TRoleExample();
		TRoleExample.Criteria criteria=example.createCriteria();
		return roleMapper.selectByExample(example);
	}

	@Override
	public void saveRole(TRole role,List<Integer> menuIds) {
		if(role.getUid()!=null){
			roleMapper.updateByPrimaryKeySelective(role);
		}else{
			roleMapper.insertSelective(role);
		}
		Integer roleId=role.getUid();
		if(menuIds.size()!=0){
			TRelationExample example=new TRelationExample();
			TRelationExample.Criteria criteria=example.createCriteria();
			criteria.andRoleidEqualTo(roleId);
			relationMapper.deleteByExample(example);
			for (Integer mid : menuIds) {
				TRelation record=new TRelation();
				record.setRoleid(roleId);
				record.setMenuid(mid);
				relationMapper.insert(record);
			}
		}
	}

	@Override
	public int deleteRoleById(Integer roleId) {
		TUserExample example=new TUserExample();
		TUserExample.Criteria criteria=example.createCriteria();
		criteria.andRoleidEqualTo(roleId);
		List<TUser> r = tUserMapper.selectByExample(example);
		if(r.size()==0){
			roleMapper.deleteByPrimaryKey(roleId);
			return 1;
		}else
			return -1;
	}

	@Override
	public Map<String, Object> findRole(PageBean pageBean, TRole role) {
		Map<String,Object> map=new HashMap<String, Object>();
		TRoleExample example=new TRoleExample();
		TRoleExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(role.getRolename()))
			criteria.andRolenameLike("%"+role.getRolename()+"%");
		if(pageBean!=null)
			PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		List<TRole> list = roleMapper.selectByExample(example);
		PageInfo<TRole> pageInfo=new PageInfo<TRole>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		return map;
	}

	@Override
	public TRole getTRoleById(Integer roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public String getMenusByRoleId(Integer roleId) {
		TRelationExample example=new TRelationExample();
		TRelationExample.Criteria criteria=example.createCriteria();
		criteria.andRoleidEqualTo(roleId);
		List<TRelation> list = relationMapper.selectByExample(example);
		String result="";
		for (TRelation tRelation : list) {
			result+=tRelation.getMenuid()+",";
		}
		if(result.length()!=0)
			result=result.substring(0,result.length()-1);
		return result;
	}

	@Override
	public List<String> getMenuUrlsByRoleId(Integer roleId) {
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("roleId", roleId);
		return menuMapper.getMenuUrlsByRoleId(param);
	}

	@Override
	public Map<String, Object> getClickPeople(Date d1, Date d2,
			PageBean pageBean, Integer village) {
		Map<String,Object> map=new HashMap<String, Object>();
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> param=new HashMap<String, Object>();
		if(d1!=null)
			param.put("d1", d1);
		if(d2!=null)
			param.put("d2", d2);
		param.put("countyId", village);
		List<ClickResult> list = stUserMapper.getClickPeople(param);
		PageInfo<ClickResult> pageInfo=new PageInfo<ClickResult>(list);
		map.put("total", pageInfo.getTotal());
		map.put("list", list);
		if(pageBean.getPage()==1){
			PageHelper.startPage(pageBean.getPage(), 20000);
			List<ClickResult> list2 = stUserMapper.getClickPeople(param);
			int stotal=0;
			int stype1=0;
			int stype2=0;
			int stype3=0;
			int stype4=0;
			for (ClickResult clickResult : list2) {
				stotal+=clickResult.getTotal();
				stype1+=clickResult.getType1();
				stype2+=clickResult.getType2();
				stype3+=clickResult.getType3();
				stype4+=clickResult.getType4();
			}
			map.put("stotal", stotal);
			map.put("stype1", stype1);
			map.put("stype2", stype2);
			map.put("stype3", stype3);
			map.put("stype4", stype4);
		}
		return map;
	}

	@Override
	public Integer getPeopleNum(Integer townId, Integer area) {
		StUserExample example=new StUserExample();
		StUserExample.Criteria criteria=example.createCriteria();
		if(area!=-1){
			criteria.andAreaEqualTo(area);
		}else if(townId!=-1){
			criteria.andTownidEqualTo(townId);
		}
		return stUserMapper.countByExample(example);
	}

	@Override
	public Map<String, Object> getAuthorStatistic(Date d1, Date d2, String name,PageBean pageBean) {
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> param=new HashMap<String, Object>();
		if(d1!=null)
			param.put("d1", d1);
		if(d2!=null)
			param.put("d2", d2);
		if(StringUtil.isNotEmpty(name))
			param.put("name", "%"+name+"%");
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		List<AuthorResult> m = stUserMapper.getAuthorStatistic(param);
		AuthorResult t=stUserMapper.getAllAuthorStatistic(param);
		PageInfo<AuthorResult> pageInfo=new PageInfo<AuthorResult>(m);
		map.put("list", m);
		map.put("t", t);
		map.put("total", pageInfo.getTotal());
		return map;
	}

	@Override
	public Map<String, Object> getClassifyStatistic(String name,
			PageBean pageBean) {
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> param=new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(name))
			param.put("name", "%"+name+"%");
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		List<ClassifyResult> m = stUserMapper.getClassifyStatistic(param);
		List<ClassifyResultEx> ex=new LinkedList<ClassifyResultEx>();
		for (ClassifyResult classifyResult : m) {
			ClassifyResultEx ext=new ClassifyResultEx();
			int view=stUserMapper.getViewByClassifyId(classifyResult.getClassfyId());
			ext.setClassifyResult(classifyResult);
			ext.setView(view);
			ex.add(ext);
		}
		int classifyNum=0;
		int classifyView=0;
		for (ClassifyResultEx classifyResultEx : ex) {
			classifyNum+=classifyResultEx.getClassifyResult().getPm();
			classifyView+=classifyResultEx.getView();
		}
		PageInfo<ClassifyResult> pageInfo=new PageInfo<ClassifyResult>(m);
		map.put("list", ex);
		map.put("total", pageInfo.getTotal());
		map.put("classifyNum", classifyNum);
		map.put("classifyView", classifyView);
		return map;
	}

	@Override
	public void readStatistics() throws Exception {
		// 清除上周标记
		classifyStatisticMapper.updateLastWeekState();
		PageBean pageBean=new PageBean(1,100);
		Map<String,Object> map=getClassifyStatistic(null,pageBean);
		Calendar calendar = Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int week=calendar.get(Calendar.WEEK_OF_YEAR);
		Date now=new Date();
		List<ClassifyResultEx> list=(List<ClassifyResultEx>) map.get("list");
		for (ClassifyResultEx classifyResultEx : list) {
			StClassifyStatistic statistic=new StClassifyStatistic();
			statistic.setClassfyid(classifyResultEx.getClassifyResult().getClassfyId());
			statistic.setClassifyname(classifyResultEx.getClassifyResult().getClassifyName());
			statistic.setPm(classifyResultEx.getClassifyResult().getPm());
			statistic.setView(classifyResultEx.getView());
			statistic.setState(false);
			statistic.setCreatetime(now);
			statistic.setYear(year);
			statistic.setWeek(week);
			classifyStatisticMapper.insertSelective(statistic);
		}
		int classifyNum=(int) map.get("classifyNum");
		int classifyView=(int) map.get("classifyView");
		StClassifyStatistic statistic=new StClassifyStatistic();
		statistic.setClassfyid(-1);
		statistic.setClassifyname("总计");
		statistic.setPm(classifyNum);
		statistic.setView(classifyView);
		statistic.setState(false);
		statistic.setCreatetime(now);
		statistic.setYear(year);
		statistic.setWeek(week);
		classifyStatisticMapper.insertSelective(statistic);
	}

	@Override
	public List<StClassifyStatistic> getWeekClassifyStatistic(String name,int year,int week) {
		StClassifyStatisticExample example=new StClassifyStatisticExample();
		StClassifyStatisticExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(name))
			criteria.andClassifynameLike("%"+name+"%");
		//criteria.andStateEqualTo(false);
		criteria.andWeekEqualTo(week);
		criteria.andYearEqualTo(year);
		return classifyStatisticMapper.selectByExample(example);
	}

	@Override
	public Map<String, Object> getMyWeekClassifyStatistic(String name,
			int year, int week,int year2,int week2) {
		Map<String,Object> map=new HashMap<String, Object>();
		boolean status=year==-1&&week==-1;
		List<StClassifyStatistic> list1 = getWeekClassifyStatistic(name,year,week);
		List<StClassifyStatistic> list2 = getWeekClassifyStatistic(name,year2,week2);
		int length=list2.size();
		List<StClassifyStatistic> list=new LinkedList<StClassifyStatistic>();
		for (int i = 0; i < length; i++) {
			StClassifyStatistic tp=new StClassifyStatistic();
			tp.setClassifyname(list2.get(i).getClassifyname());
			tp.setPm(list2.get(i).getPm()-(status?0:list1.get(i).getPm()));
			tp.setView(list2.get(i).getView()-(status?0:list1.get(i).getView()));
			list.add(tp);
		}
		map.put("list", list);
		map.put("total", list.size());
		return map;
	}
	

	/**
	 * 会员 信息的修改
	 */
	@Override
	public int updateByPrimaryKey(StUser stUser) {
		// TODO Auto-generated method stub
		int result = 0;
		if(stUser.getUserid()!=null){
			result = stUserMapper.updateByPrimaryKeySelective(stUser);
			if(result >0){
				LOG.info("会员信息修改成功！");
			}
		}else{
			LOG.info("会员ID为0！");
		}
		return result;
	}
	

}
