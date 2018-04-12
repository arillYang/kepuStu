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
import com.kepu.constant.MyConstant;
import com.kepu.constant.ResultConstant;
import com.kepu.dao.JedisClient;
import com.kepu.mapper.StActivityRecordMapper;
import com.kepu.mapper.StBuildingrentContentMapper;
import com.kepu.mapper.StBuildingsellContentMapper;
import com.kepu.mapper.StCommentMapper;
import com.kepu.mapper.StCommonSearchMapper;
import com.kepu.mapper.StCommonVoteMapper;
import com.kepu.mapper.StDeviceMapper;
import com.kepu.mapper.StDictionaryMapper;
import com.kepu.mapper.StJobApplyMapper;
import com.kepu.mapper.StJobMapper;
import com.kepu.mapper.StLinkMapper;
import com.kepu.mapper.StProductMapper;
import com.kepu.mapper.StReportMapper;
import com.kepu.mapper.StSkillContentMapper;
import com.kepu.mapper.StSkillandbuildingCommentMapper;
import com.kepu.mapper.StSkillandbuildingReplyMapper;
import com.kepu.mapper.StTaskContentMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StActivityRecord;
import com.kepu.pojo.StBuildingrentContent;
import com.kepu.pojo.StBuildingsellContent;
import com.kepu.pojo.StComment;
import com.kepu.pojo.StCommonSearch;
import com.kepu.pojo.StCommonSearchExample;
import com.kepu.pojo.StCommonVote;
import com.kepu.pojo.StCommonVoteExample;
import com.kepu.pojo.StDevice;
import com.kepu.pojo.StDictionary;
import com.kepu.pojo.StDictionaryExample;
import com.kepu.pojo.StJob;
import com.kepu.pojo.StJobApply;
import com.kepu.pojo.StLink;
import com.kepu.pojo.StLinkExample;
import com.kepu.pojo.StProduct;
import com.kepu.pojo.StReport;
import com.kepu.pojo.StReportExample;
import com.kepu.pojo.StSkillContent;
import com.kepu.pojo.StSkillandbuildingComment;
import com.kepu.pojo.StSkillandbuildingCommentExample;
import com.kepu.pojo.StSkillandbuildingReply;
import com.kepu.pojo.StSkillandbuildingReplyExample;
import com.kepu.pojo.StTaskContent;
import com.kepu.pojo.StUser;
import com.kepu.service.SysService;
import com.kepu.util.DateUtil;
import com.kepu.util.StrSimilarityUtils;
import com.kepu.util.StringUtil;

@Service
public class SysServiceImpl implements SysService{

	
	@Autowired
	private StDictionaryMapper stDictionaryMapper;
	@Autowired
	private StUserMapper stUserMapper;
	@Autowired
	private StCommentMapper commentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private StDeviceMapper deviceMapper;
	@Autowired
	private StReportMapper reportMapper;
	@Autowired
	private StCommonSearchMapper commonSearchMapper;
	@Autowired
	private StSkillContentMapper skillContentMapper;
	@Autowired
	private StTaskContentMapper  stTaskContentMapper;
	@Autowired
	private StBuildingsellContentMapper buildingsellContentMapper;
	@Autowired
	private StBuildingrentContentMapper buildingrentContentMapper;
	@Autowired
	private StSkillandbuildingCommentMapper commonCommentMapper;
	@Autowired
	private StSkillandbuildingReplyMapper commonReplyMapper;
	@Autowired
	private StCommonVoteMapper commonVoteMapper;
	@Autowired
	private StProductMapper productMapper;
	@Autowired
	private StJobMapper jobMapper;
	@Autowired
	private StJobApplyMapper jobApplyMapper;
	@Autowired
	private StActivityRecordMapper activityRecordMapper;
	@Autowired
	private StLinkMapper linkMapper;
	@Override
	public String getLaunchPage() {
		StDictionaryExample example=new StDictionaryExample();
		StDictionaryExample.Criteria criteria=example.createCriteria();
		criteria.andDicKeyEqualTo("launchPage");
		List<StDictionary> list = stDictionaryMapper.selectByExample(example);
		if(list.size()==0)
			return "";
		return list.get(0).getDicValue();
	}

	

	@Override
	public String getAboutUs() {
		StDictionaryExample example=new StDictionaryExample();
		StDictionaryExample.Criteria criteria=example.createCriteria();
		criteria.andDicKeyEqualTo("about_us");
		List<StDictionary> list = stDictionaryMapper.selectByExample(example);
		if(list.size()==0)
			return "";
		return list.get(0).getDicValue();
	}



	@Override
	public void insertDeviceMessage(StDevice stDevice) {
		stDevice.setCreatetime(new Date());
		deviceMapper.insertSelective(stDevice);
	}



	@Override
	public KePuResult reportNewsComment(Integer userId,Long commentId) {
		StComment comment = commentMapper.selectByPrimaryKey(commentId);
		if(comment==null)
			return KePuResult.ok(ResultConstant.code_yewu, "该评论已被删除或不存在", "");
		StReportExample example=new StReportExample();
		StReportExample.Criteria criteria=example.createCriteria();
		criteria.andCommentidEqualTo(commentId);
		List<StReport> reportList = reportMapper.selectByExample(example);
		if(reportList.size()!=0)
			return KePuResult.ok(ResultConstant.code_yewu, "您的举报已被其他用户提交过，感谢您的支持", "");
		StReport report=new StReport();
		report.setCommentid(commentId);
		report.setReportuser(userId);
		report.setCreatetime(new Date());
		reportMapper.insertSelective(report);
		return KePuResult.ok(ResultConstant.code_ok, "举报成功","");
	}



	@Override
	public String getParam(String param) {
		StDictionaryExample example=new StDictionaryExample();
		StDictionaryExample.Criteria criteria=example.createCriteria();
		criteria.andDicKeyEqualTo(param);
		List<StDictionary> list = stDictionaryMapper.selectByExample(example);
		if(list.size()==0)
			return "";
		return list.get(0).getDicValue();
	}



	@Override
	public void addHotSearch(String query, Integer type) {
		if(query.length()<2||query.length()>10)
			return;
		StCommonSearchExample example=new StCommonSearchExample();
		StCommonSearchExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andTypeEqualTo(type);
		List<StCommonSearch> list = commonSearchMapper.selectByExample(example);
		float maxSilimar=0l;
		int tempId=1;
		for (StCommonSearch stHotSearch : list) {
			String word=stHotSearch.getWord();
			float current=StrSimilarityUtils.getSimilarityRatio(word, query);
			if(current>maxSilimar){
				maxSilimar=current;
				tempId=stHotSearch.getUid();
			}	
		}
		if(maxSilimar>0.4){
			StCommonSearch r = commonSearchMapper.selectByPrimaryKey(tempId);
			r.setSearchnum(r.getSearchnum()+1);
			r.setUpdatetime(new Date());
			commonSearchMapper.updateByPrimaryKeySelective(r);
		}else{
			StCommonSearch r =new StCommonSearch();
			r.setWord(query);
			r.setCreatetime(new Date());
			r.setUpdatetime(new Date());
			r.setType(type);
			commonSearchMapper.insertSelective(r);
		}
		
	}



	@Override
	public KePuResult getHotSearch(Integer type) {
		Map<String,Object> map=new HashMap<String, Object>();
		StCommonSearchExample example=new StCommonSearchExample();
		StCommonSearchExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andTypeEqualTo(type);
		PageHelper.startPage(1, 20);
		List<StCommonSearch> list = commonSearchMapper.selectByExample(example);
		List<String> r=new LinkedList<String>();
		for (StCommonSearch stHotSearch : list) {
			r.add(stHotSearch.getWord());
		}
		map.put("hotWords", r);
		map.put("totalcount", r.size()+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}



	@Override
	public KePuResult deletePublish(Integer type, Integer typeId, Integer userId) {
		if(type==1){
			StSkillContent r = skillContentMapper.selectByPrimaryKey(typeId);
			if(r.getUserid().intValue()!=userId.intValue()){
				return KePuResult.build(ResultConstant.code_param, "只有发布者才允许删除", "");
			}
			skillContentMapper.deleteByPrimaryKey(typeId);
			return KePuResult.ok(ResultConstant.code_ok, "删除成功", null);
		}else if(type==2){
			StTaskContent r = stTaskContentMapper.selectByPrimaryKey(typeId);
			if(r.getUserid().intValue()!=userId.intValue()){
				return KePuResult.build(ResultConstant.code_param, "只有发布者才允许删除", "");
			}
			stTaskContentMapper.deleteByPrimaryKey(typeId);
			return KePuResult.ok(ResultConstant.code_ok, "删除成功", null);
		}else if(type==3){
			StBuildingsellContent r = buildingsellContentMapper.selectByPrimaryKey(typeId);
			if(r.getUserid().intValue()!=userId.intValue()){
				return KePuResult.build(ResultConstant.code_param, "只有发布者才允许删除", "");
			}
			buildingsellContentMapper.deleteByPrimaryKey(typeId);
			return KePuResult.ok(ResultConstant.code_ok, "删除成功", null);
		}else if(type==4){
			StBuildingrentContent r = buildingrentContentMapper.selectByPrimaryKey(typeId);
			if(r.getUserid().intValue()!=userId.intValue()){
				return KePuResult.build(ResultConstant.code_param, "只有发布者才允许删除", "");
			}
			buildingrentContentMapper.deleteByPrimaryKey(typeId);
			return KePuResult.ok(ResultConstant.code_ok, "删除成功", null);
		}else if(type==5){
			StProduct r = productMapper.selectByPrimaryKey(typeId);
			if(r.getUserid().intValue()!=userId.intValue()){
				return KePuResult.build(ResultConstant.code_param, "只有发布者才允许删除", "");
			}
			productMapper.deleteByPrimaryKey(typeId);
			return KePuResult.ok(ResultConstant.code_ok, "删除成功", null);
		}else if(type==6){
			StJob r = jobMapper.selectByPrimaryKey(typeId);
			if(r.getUserid().intValue()!=userId.intValue()){
				return KePuResult.build(ResultConstant.code_param, "只有发布者才允许删除", "");
			}
			jobMapper.deleteByPrimaryKey(typeId);
			return KePuResult.ok(ResultConstant.code_ok, "删除成功", null);
		}else if(type==7){
			StJobApply r = jobApplyMapper.selectByPrimaryKey(typeId);
			if(r.getUserid().intValue()!=userId.intValue()){
				return KePuResult.build(ResultConstant.code_param, "只有发布者才允许删除", "");
			}
			jobApplyMapper.deleteByPrimaryKey(typeId);
			return KePuResult.ok(ResultConstant.code_ok, "删除成功", null);
		}
		return KePuResult.build(ResultConstant.code_param, "type错误", "");
	}



	@Override
	public KePuResult getComment(Integer type, Integer uid, Integer userId,
			Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StSkillandbuildingCommentExample example=new StSkillandbuildingCommentExample();
		example.setOrderByClause("createTime desc");
		StSkillandbuildingCommentExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andTypeEqualTo(type);
		criteria.andTypeidEqualTo(uid);
		PageHelper.startPage(page, size);
		List<StSkillandbuildingComment> myList = commonCommentMapper.selectByExample(example);
		List<Map<String,String>> commentList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StSkillandbuildingComment stComment : myList) {
			temp=new HashMap<String, String>();
			temp.put("commentId", stComment.getUid()+"");
			temp.put("createTime",DateUtil.formatDate(stComment.getCreatetime(), MyConstant.updatetime));
			temp.put("replyNum", stComment.getReplynum()+"");
			temp.put("praiseNum", stComment.getPraisenum()+"");
			temp.put("comment", stComment.getContent());
			temp.put("userId", stComment.getUserid()+"");
			temp.put("nickName", stComment.getUsername());
			temp.put("avatar", stComment.getAvatar());
			String myPraise=jedisClient.hget("common_commentPraise", "commentPraise_"+stComment.getUid()+
					"_"+userId);
			if("0".equals(myPraise)||StringUtil.isEmpty(myPraise)){
				String t=checkPraise(1, userId, stComment.getUid(),-1);
				if("1".equals(t)){
				     jedisClient.hset("common_commentPraise", "commentPraise_"+stComment.getUid()+
							"_"+userId,"1");
				}
				myPraise=t;
			}
			temp.put("myPraise", StringUtil.isEmpty(myPraise)?"0":myPraise);
			commentList.add(temp);
		}
		PageInfo<StSkillandbuildingComment> pageInfo=new PageInfo<StSkillandbuildingComment>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("commentList", commentList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}



	@Override
	public String checkPraise(Integer type, Integer userId, Integer typeId,
			Integer fromType) {
		StCommonVoteExample example=new StCommonVoteExample();
		StCommonVoteExample.Criteria criteria=example.createCriteria();
		criteria.andTypeEqualTo(type);
		criteria.andTypeidEqualTo((long)typeId);
		criteria.andUseridEqualTo(userId);
		criteria.andStatusEqualTo(0);
		if(fromType!=-1)
			criteria.andFromtypeEqualTo(fromType); //可无
		List< StCommonVote> list = commonVoteMapper.selectByExample(example);
		return list.size()==0?"0":"1";
	}



	@Override
	public KePuResult sentComment(StUser user, Integer uid, String comment,
			Integer fromType) {
		if(!checkExist(fromType,uid)){
			return KePuResult.ok(ResultConstant.code_yewu, "已被删除或不存在", "");
		}
		StSkillandbuildingComment stComment=new StSkillandbuildingComment();
		stComment.setUserid(user.getUserid());
		stComment.setUsername(user.getNickname());
		stComment.setState(0);
		stComment.setAvatar(user.getAvatar());
		stComment.setTypeid(uid);
		stComment.setReplynum(0);
		stComment.setPraisenum(0);
		stComment.setContent(comment);
		stComment.setCreatetime(new Date());
		stComment.setType(fromType);
		commonCommentMapper.insertSelective(stComment);
		String v=jedisClient.get("commom_commentNum_"+uid+"_type"+"_"+fromType);
		if(StringUtil.isEmpty(v))
			jedisClient.set("commom_commentNum_"+uid+"_type"+"_"+fromType, "1");
		else
			jedisClient.set("commom_commentNum_"+uid+"_type"+"_"+fromType, Integer.valueOf(v)+1+"");
		//news.setCommentcount(news.getCommentcount()+1);
		//newsMapper.updateByPrimaryKeySelective(news);
		return KePuResult.ok(ResultConstant.code_ok, "发表成功", "");
	}



	@Override
	public Boolean checkExist(Integer fromType, Integer uid) {
		if(fromType==1){
			StSkillContent v = skillContentMapper.selectByPrimaryKey(uid);
			return v!=null;
		}else if(fromType==2){
			StTaskContent v = stTaskContentMapper.selectByPrimaryKey(uid);
			return v!=null;
		}else if(fromType==3){
			StBuildingsellContent v = buildingsellContentMapper.selectByPrimaryKey(uid);
			return v!=null;
		}else if(fromType==4){
			StBuildingrentContent v = buildingrentContentMapper.selectByPrimaryKey(uid);
			return v!=null;
		}else if(fromType==5){
			StProduct v=productMapper.selectByPrimaryKey(uid);
			return v!=null;
		}
		return false;
	}



	@Override
	public KePuResult replyComment(StUser user, Integer commentId, String comment) {
		StSkillandbuildingComment myComment = commonCommentMapper.selectByPrimaryKey(commentId);
		if(myComment==null||myComment.getState()==1){
			return KePuResult.ok(ResultConstant.code_yewu, "该评论已被删除或不存在", "");
		}
		StSkillandbuildingReply reply=new StSkillandbuildingReply();
		reply.setUserid(user.getUserid());
		reply.setUsername(user.getNickname());
		reply.setState(0);
		reply.setAvatar(user.getAvatar());
		reply.setCommentid((long)commentId);
		reply.setPraisenum(0);
		reply.setContent(comment);
		reply.setCreatetime(new Date());
		commonReplyMapper.insertSelective(reply);
		myComment.setReplynum(myComment.getReplynum()+1);
		commonCommentMapper.updateByPrimaryKeySelective(myComment);
		return KePuResult.ok(ResultConstant.code_ok, "回复成功", "");
	}



	@Override
	public KePuResult getCommentReply(Integer commentId, Integer userId,
			Integer page, Integer size) {
		if(!checkComment(commentId))
			return KePuResult.ok(ResultConstant.code_yewu, "该评论已被删除或不存在", "");
		Map<String,Object> map=new HashMap<String, Object>();
		StSkillandbuildingReplyExample example=new StSkillandbuildingReplyExample();
		example.setOrderByClause("createTime desc");
		StSkillandbuildingReplyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andCommentidEqualTo((long)commentId);
		PageHelper.startPage(page, size);
		List<StSkillandbuildingReply> myList = commonReplyMapper.selectByExample(example);
		List<Map<String,String>> replyList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StSkillandbuildingReply stReply : myList) {
			temp=new HashMap<String, String>();
			temp.put("replyId", stReply.getUid()+"");
			temp.put("createTime",DateUtil.formatDate(stReply.getCreatetime(), MyConstant.updatetime));
			temp.put("praiseNum", stReply.getPraisenum()+"");
			temp.put("comment", stReply.getContent());
			temp.put("userId", stReply.getUserid()+"");
			temp.put("nickName", stReply.getUsername());
			temp.put("avatar", stReply.getAvatar());
			String myPraise=jedisClient.hget("common_replyPraise", "common_replyPraise_"+stReply.getUid()+
					"_"+userId);
			if("0".equals(myPraise)||StringUtil.isEmpty(myPraise)){
				String t=checkPraise(2, userId, stReply.getUid().intValue(),-1);
				if("1".equals(t)){
				     jedisClient.hset("common_replyPraise", "common_replyPraise_"+stReply.getUid()+
							"_"+userId,"1");
				}
				myPraise=t;
			}
			temp.put("myPraise", StringUtil.isEmpty(myPraise)?"0":myPraise);
			replyList.add(temp);
		}
		PageInfo<StSkillandbuildingReply> pageInfo=new PageInfo<StSkillandbuildingReply>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("replyList", replyList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}



	@Override
	public Boolean checkComment(Integer commentId) {
		StSkillandbuildingComment comment = commonCommentMapper.selectByPrimaryKey(commentId);
		if(comment==null||comment.getState()==1)
			return false;
		return true;
	}



	@Override
	public KePuResult praise(Integer type, Integer typeId, Integer userId) {
		if(type==1){
			StSkillandbuildingComment r = commonCommentMapper.selectByPrimaryKey(typeId);
			if(r==null)
				return KePuResult.ok(ResultConstant.code_yewu, "typeId不正确", "");
			r.setPraisenum(r.getPraisenum()+1);
			commonCommentMapper.updateByPrimaryKeySelective(r);
			StCommonVote vote=new StCommonVote();
			vote.setType(type);
			vote.setTypeid((long)typeId);
			vote.setVotetime(new Date());
			vote.setUserid(userId);
			jedisClient.hset("commentPraise", "commentPraise_"+typeId+
					"_"+userId,"1");
			commonVoteMapper.insertSelective(vote);
			return KePuResult.ok(ResultConstant.code_ok, "点赞成功", "");
		}else if(type==2){
			StSkillandbuildingReply r = commonReplyMapper.selectByPrimaryKey((long)typeId);
			if(r==null)
				return KePuResult.ok(ResultConstant.code_yewu, "typeId不正确", "");
			r.setPraisenum(r.getPraisenum()+1);
			commonReplyMapper.updateByPrimaryKeySelective(r);
			StCommonVote vote=new StCommonVote();
			vote.setType(type);
			vote.setTypeid((long)typeId);
			vote.setVotetime(new Date());
			vote.setUserid(userId);
			jedisClient.hset("common_replyPraise", "common_replyPraise_"+typeId+
					"_"+userId,"1");
			commonVoteMapper.insertSelective(vote);
			return KePuResult.ok(ResultConstant.code_ok, "点赞成功", "");
		}
		return KePuResult.ok(ResultConstant.code_yewu, "type不正确", "");
	}



	@Override
	public int insertActivityRecord(StUser user, StActivityRecord record,
			int type, String message) {
		String coefficient = getParameter("coefficient");
		if(StringUtil.isEmpty(coefficient))
			record.setRest(1.0);
		else{
			String cf[]=coefficient.split(",");
			double rest=1.0;
			if(type==1||type==11){
				rest=Double.valueOf(cf[0]);
			}else if(type==2||type==21){
				rest=Double.valueOf(cf[1]);
			}else if(type==3||type==31){
				rest=Double.valueOf(cf[2]);
			}else if(type==4||type==41){
				rest=Double.valueOf(cf[3]);
			}else if(type==5||type==51){
				rest=Double.valueOf(cf[4]);
			}else if(type==6){
				rest=Double.valueOf(cf[5]);
			}else if(type==7){
				rest=Double.valueOf(cf[6]);
			}
			record.setRest(rest);
		}
		record.setMessage(message);
		record.setCreatetime(new Date());
		record.setType(type);
		record.setUserid(user.getUserid());
		record.setShowname(user.getNickname());
		record.setMobile(user.getMobile());
		record.setVillage(user.getArea());
		record.setTown(user.getTownid());
		
		return activityRecordMapper.insertSelective(record);
	}



	@Override
	public Double getActivityScore(Integer userId) {
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("userId", userId);
		Double score=activityRecordMapper.getMyScore(param);
		/*StActivityRecordExample example=new StActivityRecordExample();
		StActivityRecordExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		List<StActivityRecord> list = activityRecordMapper.selectByExample(example);
		for (StActivityRecord stActivityRecord : list) {
			score+=stActivityRecord.getScore()*stActivityRecord.getRest();
		}*/
		return score;
	}
	
	@Override
	public String getParameter(String key) {
		StDictionaryExample example=new StDictionaryExample();
		StDictionaryExample.Criteria criteria=example.createCriteria();
		criteria.andDicKeyEqualTo(key);
		List<StDictionary> list = stDictionaryMapper.selectByExample(example);
		if(list.size()==0)
			return "";
		return list.get(0).getDicValue();
	}



	@Override
	public List<Map<String,String>> getLinkMapByType(Integer type) {
		List<Map<String,String>> data=new LinkedList<Map<String,String>>();
		StLinkExample example2=new StLinkExample();
		StLinkExample.Criteria criteria2=example2.createCriteria();
		criteria2.andIsHomeEqualTo(1);
		criteria2.andTypeEqualTo(type);
		List<StLink> r2 = linkMapper.selectByExample(example2);
		Map<String,String> temp;
		for (StLink stLink : r2) {
			temp=new HashMap<String, String>();
			temp.put("link", stLink.getLink());
			temp.put("pic", stLink.getLunbo());
			temp.put("type", "2");
			data.add(temp);
		}
		return data;
	}

}
