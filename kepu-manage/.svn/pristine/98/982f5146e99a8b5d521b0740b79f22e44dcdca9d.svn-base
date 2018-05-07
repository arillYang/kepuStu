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
import com.kepu.constant.MyConstant;
import com.kepu.constant.ResultConstant;
import com.kepu.dao.JedisClient;
import com.kepu.mapper.StClassifyMapper;
import com.kepu.mapper.StCollectionMapper;
import com.kepu.mapper.StCommentMapper;
import com.kepu.mapper.StHotSearchMapper;
import com.kepu.mapper.StNewsContentMapper;
import com.kepu.mapper.StNewsDetailMapper;
import com.kepu.mapper.StNewsMapper;
import com.kepu.mapper.StNewsQuestionMapper;
import com.kepu.mapper.StNewsRelationMapper;
import com.kepu.mapper.StNewsTimetaskMapper;
import com.kepu.mapper.StReplyMapper;
import com.kepu.mapper.StVoteMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StClassify;
import com.kepu.pojo.StClassifyExample;
import com.kepu.pojo.StCollection;
import com.kepu.pojo.StCollectionExample;
import com.kepu.pojo.StComment;
import com.kepu.pojo.StCommentExample;
import com.kepu.pojo.StHotSearch;
import com.kepu.pojo.StHotSearchExample;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StNewsContent;
import com.kepu.pojo.StNewsDetail;
import com.kepu.pojo.StNewsDetailExample;
import com.kepu.pojo.StNewsExample;
import com.kepu.pojo.StNewsQuestion;
import com.kepu.pojo.StNewsQuestionExample;
import com.kepu.pojo.StNewsRelation;
import com.kepu.pojo.StNewsRelationExample;
import com.kepu.pojo.StNewsTimetask;
import com.kepu.pojo.StNewsTimetaskExample;
import com.kepu.pojo.StReply;
import com.kepu.pojo.StReplyExample;
import com.kepu.pojo.StVote;
import com.kepu.pojo.StVoteExample;
import com.kepu.pojo.news.NewsContent;
import com.kepu.service.NewService;
import com.kepu.util.DateUtil;
import com.kepu.util.ImgHelperUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.StrSimilarityUtils;
import com.kepu.util.StringUtil;

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
	private StNewsContentMapper contentMapper;
	@Autowired
	private StNewsRelationMapper newsRelationMapper;
	@Autowired
	private StNewsTimetaskMapper newsTimetaskMapper;
	@Autowired
	private StNewsQuestionMapper newsQuestionMapper;
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
	public KePuResult getCarousel(Integer total) {
		StNewsExample example=new StNewsExample();
		StNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andCarouselEqualTo(1);
		criteria.andStateEqualTo(0);
		PageHelper.startPage(1, total);
		List<StNews> list = stNewsMapper.selectByExample(example);
		List<Map<String,String>> data=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
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
			data.add(temp);
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", data);
	}
	@Override
	public KePuResult getNews(Integer type,Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StNewsExample example=new StNewsExample();
		StNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		criteria.andClassifyidEqualTo(type);
		PageHelper.startPage(page, size);
		List<StNews> list = stNewsMapper.selectByExample(example);
		PageInfo<StNews> pageInfo=new PageInfo<StNews>(list);
		List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		// page=1  获取置顶新闻放最第一个
		if(page==1){
			StNews stick=getStickNews();
			if(stick!=null){
				temp=new HashMap<String, String>();
				temp.put("newsId", stick.getUid()+"");
				temp.put("newsStyle", stick.getNewstype()+"");
				temp.put("pics", stick.getNewsimages());
				temp.put("auchor", stick.getNewsauthor());
				temp.put("publishTime",DateUtil.formatDate(stick.getUpdatetime(), MyConstant.updatetime));
				temp.put("view", stick.getView()+"");
				temp.put("title", stick.getTitle());
				temp.put("stick", "1");
				myList.add(temp);
			}
		}
		for (StNews stNews : list) {
			temp=new HashMap<String, String>();
			temp.put("newsId", stNews.getUid()+"");
			temp.put("newsStyle", stNews.getNewstype()+"");
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
	public StNews getStickNews() {
		StNewsExample example=new StNewsExample();
		StNewsExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("updateTime");
		criteria.andStateEqualTo(0);
		criteria.andStickEqualTo(1);
		PageHelper.startPage(1, 1);
		List<StNews> list = stNewsMapper.selectByExample(example);
		if(list.size()==0)
			return null;
		return  list.get(0);
	}
	@Override
	public KePuResult getNewsDetail(Integer userId,Integer newsId) {
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
		map.put("publishTime",DateUtil.formatDate(news.getUpdatetime(), MyConstant.updatetime));
		map.put("auchor", news.getNewsauthor());
		map.put("likeCount", news.getLikecount()+"");
		map.put("commentCount", news.getCommentcount()+"");
		String content=news.getContent();
		if(StringUtil.isEmpty(content)){
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
		}
		map.put("content",JsonUtils.jsonToList(content, NewsContent.class));
		//  访问量+1
		news.setView(news.getView()+1);
		stNewsMapper.updateByPrimaryKeySelective(news);
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
			temp.put("auchor", news.getNewsauthor());
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
	public Map<String, Object> findStNews(PageBean pageBean, StNews news) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StNewsExample example=new StNewsExample();
		example.setOrderByClause("updateTime desc");
		StNewsExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		if(news!=null&&StringUtil.isNotEmpty(news.getTitle()))
			criteria.andTitleLike("%"+news.getTitle()+"%");
		if(news!=null&&news.getCarousel()!=null)
			criteria.andCarouselEqualTo(1);
		if(news!=null&&news.getDraft()!=null)
			criteria.andDraftEqualTo(1);
		if(news!=null&&news.getStick()!=null)
			criteria.andStickEqualTo(1);
		List<StNews> list = stNewsMapper.selectByExample(example);
		int viewCount=stNewsMapper.getTotalViewNumber()==null?0:stNewsMapper.getTotalViewNumber();
		PageInfo<StNews> pageInfo=new PageInfo<StNews>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		map.put("viewCount", viewCount);
		return map;
	}
	@Override
	public StNews getNewsById(Integer newsId) {
		return stNewsMapper.selectByPrimaryKey(newsId);
	}
	@Override
	public List<StClassify> getAllClass() {
		StClassifyExample example=new StClassifyExample();
		StClassifyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		return classifyMapper.selectByExample(example);
	}
	@Override
	public Integer saveNews(StNews news,String realcontent,String mycontent,String classify,Date publishTime,boolean preView) {
		// realcontent  带html格式的
		// mycontent  去除格式后的
		if(news!=null){
			if(news.getUid()!=null){
				if(StringUtil.isEmpty(news.getNewsimages()))
					news.setNewsimages(null);
				if(StringUtil.isNotEmpty(classify)){
					// 分类处理
					StNewsRelationExample example=new StNewsRelationExample();
					StNewsRelationExample.Criteria criteria=example.createCriteria();
					criteria.andNewsidEqualTo(news.getUid());
					newsRelationMapper.deleteByExample(example);
					String[] cf=classify.split(",");
					for (String string : cf) {
						StNewsRelation record=new StNewsRelation();
						record.setClassfyid(Integer.valueOf(string));
						record.setNewsid(news.getUid());
						newsRelationMapper.insertSelective(record);
					}
				}
				//  处理文本信息
				if(StringUtil.isNotEmpty(mycontent)){
					mycontent=mycontent.replaceAll("<img", "<END><img");
					mycontent=mycontent.replaceAll("<embed", "<END><embed");
					mycontent=mycontent.replaceAll("/>", "/><END>");
					mycontent=mycontent.replaceAll("\r\n\r\n", "<br/>");
					mycontent=mycontent.replaceAll("\r\n", "<br/>");
					mycontent=mycontent.replaceAll("\t", "");
					mycontent=mycontent.replaceAll("<br/><br/><br/><br/><br/><br/>", "<br/>");
					mycontent=mycontent.replaceAll("<br/><br/> <br/>", "<br/>");
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
								if(type==1){
									if(string.startsWith("<br/>"))
										string.replace("<br/>", "");
									if(string.endsWith("<br/>")){
										int index=string.lastIndexOf("<br/>");
										string=string.substring(0,index);
									}
								}
								temp.put("contentNews", string);
								myList.add(temp);
							}
						}
						content=JsonUtils.objectToJson(myList);
						news.setContent(content);
				}
				if(publishTime!=null){
					news.setDraft(1);
					news.setCreatetime(publishTime==null?new Date():publishTime);
					news.setUpdatetime(new Date());
				}else if(!preView){
					news.setCreatetime(new Date());
				}
				stNewsMapper.updateByPrimaryKeySelective(news);
				StNewsTimetask r = newsTimetaskMapper.selectByPrimaryKey(news.getUid());
				if(publishTime!=null){
					if(r==null){
						r=new StNewsTimetask();
						r.setNewsid(news.getUid());
						r.setExec(0);
						r.setCreatetime(new Date());
						r.setPublishtime(publishTime);
						newsTimetaskMapper.insertSelective(r);
					}else{
						r.setExec(0);
						r.setPublishtime(publishTime);
						newsTimetaskMapper.updateByPrimaryKeySelective(r);
					}
				}
				if(news.getStick()==1){
					StNewsExample e=new StNewsExample();
					StNewsExample.Criteria criteria=e.createCriteria();
					criteria.andUidNotEqualTo(news.getUid());
					StNews n=new StNews();
					n.setStick(0);
					stNewsMapper.updateByExampleSelective(n, e);
				}
				Integer newsId=news.getUid();
				StNewsContent c = contentMapper.selectByPrimaryKey(newsId);
				c.setContent(realcontent);
				c.setUpdatetime(new Date());
				contentMapper.updateByPrimaryKeySelective(c);
				// 教学互动部分
				
				return newsId;
			}else{
				
				news.setCreatetime(publishTime==null?new Date():publishTime);
				news.setUpdatetime(new Date());
				//  处理文本信息
				if(StringUtil.isNotEmpty(mycontent)){
					mycontent=mycontent.replaceAll("<img", "<END><img");
					mycontent=mycontent.replaceAll("/>", "/><END>");
					mycontent=mycontent.replaceAll("\r\n\r\n", "<br/>");
					mycontent=mycontent.replaceAll("\t", "");
					mycontent=mycontent.replaceAll("<br/><br/><br/><br/><br/><br/>", "<br/>");
					mycontent=mycontent.replaceAll("<br/><br/> <br/>", "<br/>");
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
				if(publishTime!=null)
					news.setDraft(1);
				stNewsMapper.insertSelective(news);
				Integer newsId=news.getUid();
				if(publishTime!=null){
					StNewsTimetask r=new StNewsTimetask();
					r.setNewsid(news.getUid());
					r.setExec(0);
					r.setCreatetime(new Date());
					r.setPublishtime(publishTime);
					newsTimetaskMapper.insertSelective(r);
				}
				if(StringUtil.isNotEmpty(classify)){
					String[] cf=classify.split(",");
					for (String string : cf) {
						StNewsRelation record=new StNewsRelation();
						record.setClassfyid(Integer.valueOf(string));
						record.setNewsid(news.getUid());
						newsRelationMapper.insertSelective(record);
					}
				}
				if(news.getStick()==1){
					StNewsExample e=new StNewsExample();
					StNewsExample.Criteria criteria=e.createCriteria();
					criteria.andUidNotEqualTo(newsId);
					StNews n=new StNews();
					n.setStick(0);
					stNewsMapper.updateByExampleSelective(n, e);
				}
				StNewsContent c =new StNewsContent();
				c.setNewsid(newsId);
				c.setContent(realcontent);
				c.setUpdatetime(new Date());
				c.setCreatetime(new Date());
				contentMapper.insert(c);
				return newsId;
			}
		}
		return null;
	}
	@Override
	public StNewsContent getNewsContent(Integer newsId) {
		return contentMapper.selectByPrimaryKey(newsId);
	}
	@Override
	public void deleteStNewsById(Integer newsId) {
		StNews news = stNewsMapper.selectByPrimaryKey(newsId);
		if(news!=null){
			news.setState(1);
			stNewsMapper.updateByPrimaryKeySelective(news);
			contentMapper.deleteByPrimaryKey(newsId);
		}
	}
	@Override
	public Map<String, Object> findStClassify(PageBean pageBean,
			StClassify classify) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StClassifyExample example=new StClassifyExample();
		example.setOrderByClause("FIXED desc ");
		StClassifyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		if(StringUtil.isNotEmpty(classify.getClassifyname()))
			criteria.andClassifynameLike("%"+classify.getClassifyname()+"%");
		List<StClassify> list = classifyMapper.selectByExample(example);
		PageInfo<StClassify> pageInfo=new PageInfo<StClassify>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}
	@Override
	public List<Integer> getClassfyByNewsId(Integer newsId) {
		StNewsRelationExample example=new StNewsRelationExample();
		StNewsRelationExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		List<StNewsRelation> l = newsRelationMapper.selectByExample(example);
		List<Integer> list=new LinkedList<Integer>();
		for (StNewsRelation stNewsRelation : l) {
			list.add(stNewsRelation.getClassfyid());
		}
		return list;
	}
	@Override
	public StClassify getClassifyById(Integer classifyId) {
		return classifyMapper.selectByPrimaryKey(classifyId);
	}
	@Override
	public void saveStClassify(StClassify classify) {
		classify.setUpdatetime(new Date());
		if(classify.getUid()==null){
			classify.setCreatetime(new Date());
			classifyMapper.insertSelective(classify);
		}else{
			classifyMapper.updateByPrimaryKeySelective(classify);
		}
		
	}
	@Override
	public int deleteStClassify(Integer classifyId) {
		StNewsRelationExample example=new StNewsRelationExample();
		StNewsRelationExample.Criteria criteria=example.createCriteria();
		criteria.andClassfyidEqualTo(classifyId);
		List<StNewsRelation> r = newsRelationMapper.selectByExample(example);
		if(r.size()>0)
			return -1;
		StClassify c = classifyMapper.selectByPrimaryKey(classifyId);
		c.setState(1);
		c.setUpdatetime(new Date());
		classifyMapper.updateByPrimaryKeySelective(c);
		return 0;
	}
	@Override
	public synchronized void publishTask() throws Exception{
			StNewsTimetaskExample example=new StNewsTimetaskExample();
			StNewsTimetaskExample.Criteria criteria=example.createCriteria();
			criteria.andExecEqualTo(0);
			criteria.andPublishtimeLessThanOrEqualTo(new Date());
			List<StNewsTimetask> r = newsTimetaskMapper.selectByExample(example);
			for (StNewsTimetask stNewsTimetask : r) {
				StNews n = stNewsMapper.selectByPrimaryKey(stNewsTimetask.getNewsid());
				n.setCreatetime(new Date());
				//n.setUpdatetime(new Date());
				n.setDraft(0);
				stNewsMapper.updateByPrimaryKeySelective(n);
				stNewsTimetask.setExec(1);
				newsTimetaskMapper.updateByPrimaryKeySelective(stNewsTimetask);
			}
	}
	@Override
	public StNewsTimetask getTask(Integer newsId) {
		StNewsTimetaskExample example=new StNewsTimetaskExample();
		StNewsTimetaskExample.Criteria criteria=example.createCriteria();
		criteria.andExecEqualTo(0);
		criteria.andNewsidEqualTo(newsId);
		 List<StNewsTimetask> r = newsTimetaskMapper.selectByExample(example);
		return r.size()==0?null:r.get(0);
	}
	@Override
	public void deleteNewsTimetask(Integer newsId) {
		newsTimetaskMapper.deleteByPrimaryKey(newsId);
	}
	@Override
	public List<StNewsQuestion> getStNewsQuestion(Integer newsId) {
		StNewsQuestionExample example=new StNewsQuestionExample();
		StNewsQuestionExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		return newsQuestionMapper.selectByExample(example);
	}
	@Override
	public int saveStNewsQuestion(StNewsQuestion newsQuestion) {
		if(newsQuestion.getUrid()!=null)
			return newsQuestionMapper.updateByPrimaryKeySelective(newsQuestion);
		else
			return newsQuestionMapper.insertSelective(newsQuestion);
	}
	@Override
	public int deleteStNewsQuestion(Integer newsId) {
		StNewsQuestionExample example=new StNewsQuestionExample();
		StNewsQuestionExample.Criteria criteria=example.createCriteria();
		criteria.andNewsidEqualTo(newsId);
		return newsQuestionMapper.deleteByExample(example);
	}
	@Override
	public void cancelNewsById(Integer newsId, Integer type) {
		StNews news = stNewsMapper.selectByPrimaryKey(newsId);
		if(type==1)
			news.setCarousel(0);
		else if(type==2)
			news.setStick(0);
		stNewsMapper.updateByPrimaryKeySelective(news);
	}
}
