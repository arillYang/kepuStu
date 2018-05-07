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
import com.kepu.mapper.StNoticeNewsCollectionMapper;
import com.kepu.mapper.StNoticeNewsCommentMapper;
import com.kepu.mapper.StNoticeNewsHotSearchMapper;
import com.kepu.mapper.StNoticeNewsMapper;
import com.kepu.mapper.StNoticeNewsRelationMapper;
import com.kepu.mapper.StNoticeNewsReplyMapper;
import com.kepu.mapper.StNoticeNewsReportMapper;
import com.kepu.mapper.StNoticeNewsVoteMapper;
import com.kepu.mapper.StNoticeVoteMapper;
import com.kepu.mapper.StVillageMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StNoticeNews;
import com.kepu.pojo.StNoticeNewsCollection;
import com.kepu.pojo.StNoticeNewsCollectionExample;
import com.kepu.pojo.StNoticeNewsComment;
import com.kepu.pojo.StNoticeNewsCommentExample;
import com.kepu.pojo.StNoticeNewsExample;
import com.kepu.pojo.StNoticeNewsHotSearch;
import com.kepu.pojo.StNoticeNewsHotSearchExample;
import com.kepu.pojo.StNoticeNewsRelation;
import com.kepu.pojo.StNoticeNewsRelationExample;
import com.kepu.pojo.StNoticeNewsReply;
import com.kepu.pojo.StNoticeNewsReplyExample;
import com.kepu.pojo.StNoticeNewsReport;
import com.kepu.pojo.StNoticeNewsReportExample;
import com.kepu.pojo.StNoticeNewsVote;
import com.kepu.pojo.StNoticeNewsVoteExample;
import com.kepu.pojo.StNoticeVote;
import com.kepu.pojo.StNoticeVoteExample;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageExample;
import com.kepu.pojo.news.NewsContent;
import com.kepu.pojo.news.NewsTemp;
import com.kepu.service.NoticeService;
import com.kepu.service.SysService;
import com.kepu.util.DateUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.StrSimilarityUtils;
import com.kepu.util.StringUtil;
import com.kepu.util.SystemSession;

@Service
public class NoticeServiceImpl implements NoticeService {

	private static final Logger LOG = Logger.getLogger(NoticeServiceImpl.class);
	@Autowired
	private StNoticeNewsMapper noticeNewsMapper;
	@Autowired
	private StNoticeNewsCollectionMapper noticeNewsCollectionMapper;
	@Autowired
	private StNoticeNewsRelationMapper relationMapper;
	@Autowired
	private StNoticeNewsVoteMapper noticeNewsVoteMapper;
	@Autowired
	private StNoticeNewsCommentMapper noticeNewsCommentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private StNoticeVoteMapper noticeVoteMapper;
	@Autowired
	private StNoticeNewsReplyMapper noticeNewsReplyMapper;
	@Autowired
	private StNoticeNewsReportMapper noticeNewsReportMapper;
	@Autowired
	StNoticeNewsHotSearchMapper noticeNewsHotSearchMapper;
	@Autowired
	private StVillageMapper villageMapper;
	@Autowired
	private SysService sysService;

	@Override
	public List<Integer> findNewsIdsByVillageId(Integer villageId) {
		StNoticeNewsRelationExample ex = new StNoticeNewsRelationExample();
		StNoticeNewsRelationExample.Criteria criteria1 = ex.createCriteria();
		criteria1.andVillageidEqualTo(villageId); // 二级ID
		List<StNoticeNewsRelation> n = relationMapper.selectByExample(ex);
		List<Integer> l = new LinkedList<Integer>();
		for (StNoticeNewsRelation stNoticeNewsRelation : n) {
			l.add(stNoticeNewsRelation.getNewsid());
		}
		return l;
	}

	@Override
	public KePuResult getCarousel(Integer total, Integer type) {
		List<Integer> l = null;
		if (type > 30)
			l = findNewsIdsByVillageId(type);
		else
			l = findNewsIdsByTownId(type);
		if (l.size() == 0) {
			LOG.info("请输入正确的乡镇ID");
			return KePuResult.ok(ResultConstant.code_yewu, "", null);
		}
		StNoticeNewsExample example = new StNoticeNewsExample();
		StNoticeNewsExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andCarouselEqualTo(1);
		criteria.andStateEqualTo(0);
		criteria.andUidIn(l);
		criteria.andDraftEqualTo(0); // 非草稿
		PageHelper.startPage(1, total);
		List<StNoticeNews> list = noticeNewsMapper.selectByExample(example);
		List<Map<String, String>> data = new LinkedList<Map<String, String>>();
		Map<String, String> temp;
		String version = SystemSession.get().getAppVersion();
		int isNew = 1;
		if (StringUtil.isNotEmpty(version) && version.compareTo("6.2.0") < 0)
			isNew = 0;
		for (StNoticeNews stNews : list) {
			temp = new HashMap<String, String>();
			temp.put("newsId", stNews.getUid() + "");
			temp.put("title", stNews.getTitle());
			String pic = stNews.getNewsimages();
			String image = "";
			if (StringUtil.isNotEmpty(pic)) {
				image = pic.split(",")[0];
			}
			temp.put("pic", image);
			if (isNew == 1)
				temp.put("type", "1");
			data.add(temp);
		}
		if (isNew == 1) {
			List<Map<String, String>> linkList = sysService.getLinkMapByType(3);
			for (Map<String, String> map : linkList) {
				data.add(map);
			}
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", data);
	}

	@Override
	public KePuResult getNews(Integer type, Integer page, Integer size) {
		List<Integer> l = null;
		if (type > 30)
			l = findNewsIdsByVillageId(type);
		else
			l = findNewsIdsByTownId(type);
		if (l.size() == 0) {
			LOG.info("请输入正确的乡镇ID");
			return KePuResult.ok(ResultConstant.code_yewu, "", null);
		}
		List<Map<String, String>> myList = new LinkedList<Map<String, String>>();
		Map<String, String> temp;
		Integer stickNewsId = null;
		// page=1 获取置顶新闻放最第一个
		StNoticeNews stick = null;
		if (type > 30)
			stick = getStickNews(type);
		else
			stick = getStickTownNews(type);
		if (page == 1) {
			if (stick != null) {
				temp = new HashMap<String, String>();
				stickNewsId = stick.getUid();
				temp.put("newsId", stick.getUid() + "");
				temp.put("newsStyle", stick.getNewsstyle() + "");
				temp.put("pics", stick.getNewsimages());
				temp.put("auchor", stick.getNewsauthor());
				temp.put("publishTime", DateUtil.formatDate(stick.getUpdatetime(), MyConstant.updatetime));
				temp.put("view", stick.getView() + "");
				temp.put("title", stick.getTitle());
				temp.put("stick", "1");
				myList.add(temp);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		StNoticeNewsExample example = new StNoticeNewsExample();
		StNoticeNewsExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		criteria.andUidIn(l);
		criteria.andDraftEqualTo(0); // 不是草稿
		if (stick != null)
			criteria.andUidNotEqualTo(stick.getUid());
		PageHelper.startPage(page, size);
		// type=villageId 新闻也可以从一级乡镇获取
		List<StNoticeNews> list = noticeNewsMapper.selectByExample(example);
		PageInfo<StNoticeNews> pageInfo = new PageInfo<StNoticeNews>(list);
		for (StNoticeNews stNews : list) {
			temp = new HashMap<String, String>();
			temp.put("newsId", stNews.getUid() + "");
			temp.put("newsStyle", stNews.getNewsstyle() + "");
			temp.put("pics", stNews.getNewsimages());
			temp.put("auchor", stNews.getNewsauthor());
			temp.put("publishTime", DateUtil.formatDate(stNews.getUpdatetime(), MyConstant.updatetime));
			temp.put("view", stNews.getView() + "");
			temp.put("stick", "0");
			temp.put("title", stNews.getTitle());
			myList.add(temp);
		}
		long total = pageInfo.getTotal();
		map.put("totalcount", total + "");
		map.put("newsList", myList);
		map.put("pagesize", size + "");
		map.put("totalpage", (total / size + 1) + "");
		map.put("currentpage", page + "");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public StNoticeNews getStickNews(Integer villageId) {
		List<Integer> l = findNewsIdsByVillageId(villageId);
		StNoticeNewsExample example = new StNoticeNewsExample();
		StNoticeNewsExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		criteria.andStickEqualTo(1);
		criteria.andUidIn(l);
		criteria.andDraftEqualTo(0);
		PageHelper.startPage(1, 1);
		List<StNoticeNews> list = noticeNewsMapper.selectByExample(example);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}

	@Override
	public KePuResult getNewsDetail(Integer userId, Integer newsId, String appVersion) {
		Map<String, Object> map = new HashMap<String, Object>();
		StNoticeNews news = noticeNewsMapper.selectByPrimaryKey(newsId);
		if (news == null || news.getState() == 1)
			return KePuResult.ok(ResultConstant.code_yewu, "该公告已被删除或不存在", "");
		String pic = news.getNewsimages();
		String image = "";
		if (StringUtil.isNotEmpty(pic)) {
			image = pic.split(",")[0];
		}
		String myLike = "0";
		StNoticeNewsCollectionExample example = new StNoticeNewsCollectionExample();
		StNoticeNewsCollectionExample.Criteria criteria = example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		criteria.andUseridEqualTo(userId);
		List<StNoticeNewsCollection> t = noticeNewsCollectionMapper.selectByExample(example);
		if (t.size() == 1) {
			myLike = "1";
			List<Integer> likeList = new LinkedList<Integer>();
			likeList.add(newsId);
		}
		map.put("myLike", myLike);
		map.put("topPic", image);
		map.put("title", news.getTitle());
		map.put("publishTime", DateUtil.formatDate(news.getUpdatetime(), MyConstant.updatetime));
		map.put("auchor", news.getNewsauthor());
		map.put("likeCount", news.getLikecount() + "");
		map.put("commentCount", news.getCommentcount() + "");
		// 2017-6-26 新闻底部 点击喜欢(区别与收藏),不喜欢
		map.put("voteNum", news.getVotenum() + "");
		map.put("dislikeNum", news.getDislikenum() + "");
		StNoticeNewsVoteExample ex = new StNoticeNewsVoteExample();
		StNoticeNewsVoteExample.Criteria c = ex.createCriteria();
		c.andNewsidEqualTo(newsId);
		c.andUseridEqualTo(userId);
		int myVote = 0;
		int myDislike = 0;
		List<StNoticeNewsVote> r = noticeNewsVoteMapper.selectByExample(ex);
		if (r.size() != 0) {
			StNoticeNewsVote v = r.get(0);
			if (v.getDislike() == 1)
				myDislike = 1;
			if (v.getLiked() == 1)
				myVote = 1;
		}
		map.put("myVote", myVote + "");
		map.put("myDislike", myDislike + "");
		String content = news.getContent();
		map.put("content", JsonUtils.jsonToList(content, NewsContent.class));
		// 访问量+1
		news.setView(news.getView() + 1);
		noticeNewsMapper.updateByPrimaryKeySelective(news);
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult getNewsComment(Integer newsId, Integer userId, Integer page, Integer size) {
		if (!checkNews(newsId))
			return KePuResult.ok(ResultConstant.code_yewu, "该乡镇新闻已被删除或不存在", "");
		Map<String, Object> map = new HashMap<String, Object>();
		StNoticeNewsCommentExample example = new StNoticeNewsCommentExample();
		example.setOrderByClause("createTime");
		StNoticeNewsCommentExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andNewsidEqualTo(newsId);
		PageHelper.startPage(page, size);
		List<StNoticeNewsComment> myList = noticeNewsCommentMapper.selectByExample(example);
		List<Map<String, String>> commentList = new LinkedList<Map<String, String>>();
		Map<String, String> temp;
		for (StNoticeNewsComment stComment : myList) {
			temp = new HashMap<String, String>();
			temp.put("commentId", stComment.getUid() + "");
			temp.put("createTime", DateUtil.formatDate(stComment.getCreatetime(), MyConstant.updatetime));
			temp.put("replyNum", stComment.getReplynum() + "");
			temp.put("praiseNum", stComment.getPraisenum() + "");
			temp.put("comment", stComment.getContent());
			temp.put("userId", stComment.getUserid() + "");
			temp.put("nickName", stComment.getUsername());
			temp.put("avatar", stComment.getAvatar());
			String myPraise = jedisClient.hget("NcommentPraise", "NcommentPraise_" + stComment.getUid() + "_" + userId);
			if ("0".equals(myPraise) || StringUtil.isEmpty(myPraise)) {
				String t = checkPraise(1, userId, stComment.getUid());
				if ("1".equals(t)) {
					jedisClient.hset("NcommentPraise", "NcommentPraise_" + stComment.getUid() + "_" + userId, "1");
				}
				myPraise = t;
			}
			temp.put("myPraise", StringUtil.isEmpty(myPraise) ? "0" : myPraise);
			commentList.add(temp);
		}
		PageInfo<StNoticeNewsComment> pageInfo = new PageInfo<StNoticeNewsComment>(myList);
		long total = pageInfo.getTotal();
		map.put("totalcount", total + "");
		map.put("commentList", commentList);
		map.put("pagesize", size + "");
		map.put("totalpage", (total / size + 1) + "");
		map.put("currentpage", page + "");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public Boolean checkNews(Integer newsId) {
		StNoticeNews news = noticeNewsMapper.selectByPrimaryKey(newsId);
		if (news == null || news.getState() == 1)
			return false;
		return true;
	}

	@Override
	public Boolean checkComment(Long commentId) {
		StNoticeNewsComment comment = noticeNewsCommentMapper.selectByPrimaryKey(commentId);
		if (comment == null || comment.getState() == 1)
			return false;
		return true;
	}

	@Override
	public String checkPraise(Integer type, Integer userId, Long typeId) {
		StNoticeVoteExample example = new StNoticeVoteExample();
		StNoticeVoteExample.Criteria criteria = example.createCriteria();
		criteria.andTypeEqualTo(type);
		criteria.andTypeidEqualTo(typeId);
		criteria.andUseridEqualTo(userId);
		criteria.andStatusEqualTo(0);
		List<StNoticeVote> list = noticeVoteMapper.selectByExample(example);
		return list.size() == 0 ? "0" : "1";
	}

	@Override
	public KePuResult getCommentReply(Long commentId, Integer userId, Integer page, Integer size) {
		if (!checkComment(commentId))
			return KePuResult.ok(ResultConstant.code_yewu, "该评论已被删除或不存在", "");
		Map<String, Object> map = new HashMap<String, Object>();
		StNoticeNewsReplyExample example = new StNoticeNewsReplyExample();
		example.setOrderByClause("createTime");
		StNoticeNewsReplyExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andCommentidEqualTo(commentId);
		PageHelper.startPage(page, size);
		List<StNoticeNewsReply> myList = noticeNewsReplyMapper.selectByExample(example);
		List<Map<String, String>> replyList = new LinkedList<Map<String, String>>();
		Map<String, String> temp;
		for (StNoticeNewsReply stReply : myList) {
			temp = new HashMap<String, String>();
			temp.put("replyId", stReply.getUid() + "");
			temp.put("createTime", DateUtil.formatDate(stReply.getCreatetime(), MyConstant.updatetime));
			temp.put("praiseNum", stReply.getPraisenum() + "");
			temp.put("comment", stReply.getContent());
			temp.put("userId", stReply.getUserid() + "");
			temp.put("nickName", stReply.getUsername());
			temp.put("avatar", stReply.getAvatar());
			String myPraise = jedisClient.hget("NreplyPraise", "NreplyPraise_" + stReply.getUid() + "_" + userId);
			if ("0".equals(myPraise) || StringUtil.isEmpty(myPraise)) {
				String t = checkPraise(2, userId, stReply.getUid());
				if ("1".equals(t)) {
					jedisClient.hset("NreplyPraise", "NreplyPraise_" + stReply.getUid() + "_" + userId, "1");
				}
				myPraise = t;
			}
			temp.put("myPraise", StringUtil.isEmpty(myPraise) ? "0" : myPraise);
			replyList.add(temp);
		}
		PageInfo<StNoticeNewsReply> pageInfo = new PageInfo<StNoticeNewsReply>(myList);
		long total = pageInfo.getTotal();
		map.put("totalcount", total + "");
		map.put("replyList", replyList);
		map.put("pagesize", size + "");
		map.put("totalpage", (total / size + 1) + "");
		map.put("currentpage", page + "");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult likeNews(Integer newsId, Integer userId) {
		StNoticeNewsCollectionExample example = new StNoticeNewsCollectionExample();
		StNoticeNewsCollectionExample.Criteria criteria = example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		criteria.andUseridEqualTo(userId);
		List<StNoticeNewsCollection> list = noticeNewsCollectionMapper.selectByExample(example);
		if (list.size() > 0) {
			return KePuResult.ok(ResultConstant.code_ok, "已收藏", "");
		}
		StNoticeNewsCollection collection = new StNoticeNewsCollection();
		collection.setNewsid(newsId);
		collection.setUserid(userId);
		collection.setCreatetime(new Date());
		noticeNewsCollectionMapper.insertSelective(collection);
		StNoticeNews news = noticeNewsMapper.selectByPrimaryKey(newsId);
		news.setLikecount(news.getLikecount() + 1);
		noticeNewsMapper.updateByPrimaryKeySelective(news);
		// 写入缓存
		String myLikes = jedisClient.get("Nnews_like_user_" + userId);
		List<Integer> likeList;
		if (StringUtil.isNotEmpty(myLikes)) {
			likeList = JsonUtils.jsonToList(myLikes, Integer.class);
		} else {
			likeList = new LinkedList<Integer>();
		}
		likeList.add(newsId);
		jedisClient.set("Nnews_like_user_" + userId, JsonUtils.objectToJson(likeList));
		LOG.info("收藏成功");
		return KePuResult.ok(ResultConstant.code_ok, "收藏成功", "");
	}

	@Override
	public KePuResult deletelikeNews(String[] newsIds, Integer userId) {
		List<Integer> values = null;
		try {
			values = StringUtil.asIntegerList(newsIds);
		} catch (Exception e) {
			e.printStackTrace();
			return KePuResult.ok(ResultConstant.code_yewu, "newsId有误，操作失败", "");
		}
		if (values == null)
			return KePuResult.ok(ResultConstant.code_yewu, "请选择要删除的收藏", "");
		StNoticeNewsCollectionExample example = new StNoticeNewsCollectionExample();
		StNoticeNewsCollectionExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userId);
		criteria.andNewsidIn(values);
		noticeNewsCollectionMapper.deleteByExample(example);
		jedisClient.del("Nnews_like_user_" + userId);
		StNoticeNewsExample example2 = new StNoticeNewsExample();
		StNoticeNewsExample.Criteria criteria2 = example2.createCriteria();
		criteria2.andUidIn(values);
		List<StNoticeNews> r = noticeNewsMapper.selectByExample(example2);
		for (StNoticeNews stNews : r) {
			stNews.setLikecount(stNews.getLikecount() - 1);
			noticeNewsMapper.updateByPrimaryKeySelective(stNews);
		}
		return KePuResult.ok(ResultConstant.code_ok, "删除成功", "");
	}

	@Override
	public KePuResult getMyLikeNews(Integer userId, Integer page, Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		StNoticeNewsCollectionExample example = new StNoticeNewsCollectionExample();
		StNoticeNewsCollectionExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(userId);
		PageHelper.startPage(page, size);
		List<StNoticeNewsCollection> myList = noticeNewsCollectionMapper.selectByExample(example);
		List<Map<String, String>> collectionList = new LinkedList<Map<String, String>>();
		Map<String, String> temp;
		for (StNoticeNewsCollection stCollection : myList) {
			temp = new HashMap<String, String>();
			Integer newsId = stCollection.getNewsid();
			temp.put("newsId", newsId + "");
			String newsCache = jedisClient.get("Nnews_" + newsId);
			if (StringUtil.isNotEmpty(newsCache)) {
				NewsTemp news = JsonUtils.jsonToPojo(newsCache, NewsTemp.class);
				temp.put("title", news.getTitle());
				temp.put("auchor", news.getNewsAuthor());
				temp.put("newsStyle", news.getNewsStyle());
				temp.put("publishTime", news.getUpdateTime());
				temp.put("pics", news.getNewsImages());
				temp.put("view", news.getView());

			} else {
				StNoticeNews news = noticeNewsMapper.selectByPrimaryKey(newsId);
				temp.put("title", news.getTitle());
				temp.put("auchor", news.getNewsauthor());
				temp.put("newsStyle", news.getNewsstyle() + "");
				temp.put("publishTime", DateUtil.formatDate(news.getUpdatetime(), MyConstant.updatetime));
				temp.put("pics", news.getNewsimages());
				temp.put("view", news.getView() + "");
				NewsTemp t = new NewsTemp(news.getTitle(), news.getNewsimages(), news.getNewsauthor(),
						news.getNewsstyle() + "", DateUtil.formatDate(news.getUpdatetime(), MyConstant.updatetime),
						news.getView() + "");
				jedisClient.set("Nnews_" + newsId, JsonUtils.objectToJson(t));
				jedisClient.expire("Nnews_" + newsId, 2592000);
			}
			collectionList.add(temp);
		}
		PageInfo<StNoticeNewsCollection> pageInfo = new PageInfo<StNoticeNewsCollection>(myList);
		long total = pageInfo.getTotal();
		map.put("totalcount", total + "");
		map.put("collectionList", collectionList);
		map.put("pagesize", size + "");
		map.put("totalpage", (total / size + 1) + "");
		map.put("currentpage", page + "");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult reportNewsComment(Integer userId, Long commentId) {
		StNoticeNewsComment comment = noticeNewsCommentMapper.selectByPrimaryKey(commentId);
		if (comment == null)
			return KePuResult.ok(ResultConstant.code_yewu, "该评论已被删除或不存在", "");
		StNoticeNewsReportExample example = new StNoticeNewsReportExample();
		StNoticeNewsReportExample.Criteria criteria = example.createCriteria();
		criteria.andCommentidEqualTo(commentId);
		List<StNoticeNewsReport> reportList = noticeNewsReportMapper.selectByExample(example);
		if (reportList.size() != 0)
			return KePuResult.ok(ResultConstant.code_yewu, "您的举报已被其他用户提交过，感谢您的支持", "");
		StNoticeNewsReport report = new StNoticeNewsReport();
		report.setCommentid(commentId);
		report.setReportuser(userId);
		report.setCreatetime(new Date());
		noticeNewsReportMapper.insertSelective(report);
		return KePuResult.ok(ResultConstant.code_ok, "举报成功", "");
	}

	@Override
	public KePuResult praise(Integer type, Long typeId, Integer userId) {
		if (type == 1) {
			StNoticeNewsComment r = noticeNewsCommentMapper.selectByPrimaryKey(typeId);
			if (r == null)
				return KePuResult.ok(ResultConstant.code_yewu, "typeId不正确", "");
			r.setPraisenum(r.getPraisenum() + 1);
			noticeNewsCommentMapper.updateByPrimaryKeySelective(r);
			StNoticeVote vote = new StNoticeVote();
			vote.setType(type);
			vote.setTypeid(typeId);
			vote.setVotetime(new Date());
			vote.setUserid(userId);
			jedisClient.hset("NcommentPraise", "NcommentPraise_" + typeId + "_" + userId, "1");
			noticeVoteMapper.insertSelective(vote);
			return KePuResult.ok(ResultConstant.code_ok, "点赞成功", "");
		} else if (type == 2) {
			StNoticeNewsReply r = noticeNewsReplyMapper.selectByPrimaryKey(typeId);
			if (r == null)
				return KePuResult.ok(ResultConstant.code_yewu, "typeId不正确", "");
			r.setPraisenum(r.getPraisenum() + 1);
			noticeNewsReplyMapper.updateByPrimaryKeySelective(r);
			StNoticeVote vote = new StNoticeVote();
			vote.setType(type);
			vote.setTypeid(typeId);
			vote.setVotetime(new Date());
			vote.setUserid(userId);
			jedisClient.hset("NreplyPraise", "NreplyPraise_" + typeId + "_" + userId, "1");
			noticeVoteMapper.insertSelective(vote);
			return KePuResult.ok(ResultConstant.code_ok, "点赞成功", "");
		}
		return KePuResult.ok(ResultConstant.code_yewu, "type不正确", "");
	}

	@Override
	public KePuResult dpNews(Integer newsId, Integer type, Integer userId, Integer operate) {
		StNoticeNewsVoteExample example = new StNoticeNewsVoteExample();
		StNoticeNewsVoteExample.Criteria criteria = example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		criteria.andUseridEqualTo(userId);
		List<StNoticeNewsVote> resultList = noticeNewsVoteMapper.selectByExample(example);
		StNoticeNews news = noticeNewsMapper.selectByPrimaryKey(newsId);
		if (news == null) {
			return KePuResult.ok(ResultConstant.code_yewu, "该新闻已被删除或不存在", "");
		}
		if (resultList.size() == 0 && operate == 1) {
			StNoticeNewsVote v = new StNoticeNewsVote();
			if (type == 1) {
				v.setLiked(1);
				news.setVotenum(news.getVotenum() + 1);
			}
			if (type == 2) {
				v.setDislike(1);
				news.setDislikenum(news.getDislikenum() + 1);
			}
			noticeNewsMapper.updateByPrimaryKeySelective(news);
			v.setVotetime(new Date());
			v.setNewsid(newsId);
			v.setUserid(userId);
			noticeNewsVoteMapper.insertSelective(v);
		} else if (resultList.size() != 0) {
			StNoticeNewsVote v = resultList.get(0);
			if (type == 1) {
				if (v.getLiked() == 0 && operate == 1) {
					v.setLiked(1);
					news.setVotenum(news.getVotenum() + 1);
				}
				if (v.getLiked() == 1 && operate == 0) {
					v.setLiked(0);
					news.setVotenum(news.getVotenum() - 1);
				}
			}
			if (type == 2) {
				if (v.getDislike() == 0 && operate == 1) {
					news.setDislikenum(news.getDislikenum() + 1);
					v.setDislike(1);
				}
				if (v.getDislike() == 1 && operate == 0) {
					news.setDislikenum(news.getDislikenum() - 1);
					v.setDislike(0);
				}
			}
			noticeNewsMapper.updateByPrimaryKeySelective(news);
			noticeNewsVoteMapper.updateByPrimaryKeySelective(v);
		}
		return KePuResult.ok(ResultConstant.code_ok, "操作成功", "");
	}

	@Override
	public KePuResult searchNews(String query, Integer page, Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		StNoticeNewsExample example = new StNoticeNewsExample();
		StNoticeNewsExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		criteria.andTitleLike("%" + query + "%");
		PageHelper.startPage(page, size);
		List<StNoticeNews> myList = noticeNewsMapper.selectByExample(example);
		List<Map<String, String>> newsList = new LinkedList<Map<String, String>>();
		Map<String, String> temp;
		for (StNoticeNews news : myList) {
			temp = new HashMap<String, String>();
			temp.put("newsId", news.getUid() + "");
			temp.put("title", news.getTitle());
			temp.put("auchor", news.getNewsauthor());
			temp.put("newsStyle", news.getNewsstyle() + "");
			temp.put("publishTime", DateUtil.formatDate(news.getUpdatetime(), MyConstant.updatetime));
			temp.put("pics", news.getNewsimages());
			temp.put("view", news.getView() + "");
			newsList.add(temp);
		}
		PageInfo<StNoticeNews> pageInfo = new PageInfo<StNoticeNews>(myList);
		long total = pageInfo.getTotal();
		map.put("totalcount", total + "");
		map.put("newsList", newsList);
		map.put("pagesize", size + "");
		map.put("totalpage", (total / size + 1) + "");
		map.put("currentpage", page + "");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public void addHotSearch(String query) {
		if (query.length() < 2)
			return;
		StNoticeNewsHotSearchExample example = new StNoticeNewsHotSearchExample();
		StNoticeNewsHotSearchExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(0);
		List<StNoticeNewsHotSearch> list = noticeNewsHotSearchMapper.selectByExample(example);
		float maxSilimar = 0l;
		int tempId = 1;
		for (StNoticeNewsHotSearch stHotSearch : list) {
			String word = stHotSearch.getWord();
			float current = StrSimilarityUtils.getSimilarityRatio(word, query);
			if (current > maxSilimar) {
				maxSilimar = current;
				tempId = stHotSearch.getUid();
			}
		}
		if (maxSilimar > 0.4) {
			StNoticeNewsHotSearch r = noticeNewsHotSearchMapper.selectByPrimaryKey(tempId);
			r.setSearchnum(r.getSearchnum() + 1);
			r.setUpdatetime(new Date());
			noticeNewsHotSearchMapper.updateByPrimaryKeySelective(r);
		} else {
			StNoticeNewsHotSearch r = new StNoticeNewsHotSearch();
			r.setWord(query);
			r.setCreatetime(new Date());
			r.setUpdatetime(new Date());
			noticeNewsHotSearchMapper.insertSelective(r);
		}
	}

	@Override
	public KePuResult getHotSearch() {
		Map<String, Object> map = new HashMap<String, Object>();
		StNoticeNewsHotSearchExample example = new StNoticeNewsHotSearchExample();
		StNoticeNewsHotSearchExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(0);
		PageHelper.startPage(1, 20);
		List<StNoticeNewsHotSearch> list = noticeNewsHotSearchMapper.selectByExample(example);
		List<String> r = new LinkedList<String>();
		for (StNoticeNewsHotSearch stHotSearch : list) {
			r.add(stHotSearch.getWord());
		}
		map.put("hotWords", r);
		map.put("totalcount", r.size() + "");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public List<Integer> findNewsIdsByTownId(Integer townId) {
		StVillageExample se = new StVillageExample();
		StVillageExample.Criteria c1 = se.createCriteria();
		c1.andParentEqualTo(townId);
		List<StVillage> v1 = villageMapper.selectByExample(se);
		List<Integer> l1 = new LinkedList<Integer>();
		for (StVillage stVillage : v1) {
			l1.add(stVillage.getId());
		}
		StNoticeNewsRelationExample ex = new StNoticeNewsRelationExample();
		StNoticeNewsRelationExample.Criteria criteria1 = ex.createCriteria();
		criteria1.andVillageidIn(l1); // 二级ID
		List<StNoticeNewsRelation> n = relationMapper.selectByExample(ex);
		List<Integer> l = new LinkedList<Integer>();
		for (StNoticeNewsRelation stNoticeNewsRelation : n) {
			l.add(stNoticeNewsRelation.getNewsid());
		}
		return l;
	}

	@Override
	public StNoticeNews getStickTownNews(Integer townId) {
		List<Integer> l = findNewsIdsByTownId(townId);
		StNoticeNewsExample example = new StNoticeNewsExample();
		StNoticeNewsExample.Criteria criteria = example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		criteria.andStickEqualTo(1);
		criteria.andUidIn(l);
		criteria.andDraftEqualTo(0);
		PageHelper.startPage(1, 1);
		List<StNoticeNews> list = noticeNewsMapper.selectByExample(example);
		if (list.size() == 0)
			return null;
		return list.get(0);
	}
}
