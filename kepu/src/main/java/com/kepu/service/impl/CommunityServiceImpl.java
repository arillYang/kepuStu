package com.kepu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.constant.MyConstant;
import com.kepu.constant.ResultConstant;
import com.kepu.mapper.StArticleCommentMapper;
import com.kepu.mapper.StArticleVoteMapper;
import com.kepu.mapper.StCommunityApplyMapper;
import com.kepu.mapper.StCommunityArticleMapper;
import com.kepu.mapper.StCommunityHotSearchMapper;
import com.kepu.mapper.StCommunityMapper;
import com.kepu.mapper.StCommunityNoticeMapper;
import com.kepu.mapper.StCommunityTeachDetailMapper;
import com.kepu.mapper.StCommunityTeachMapper;
import com.kepu.mapper.StCommunityUserMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.mapper.StVillageMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StActivityRecord;
import com.kepu.pojo.StArticleComment;
import com.kepu.pojo.StArticleCommentExample;
import com.kepu.pojo.StArticleVote;
import com.kepu.pojo.StArticleVoteExample;
import com.kepu.pojo.StCommunity;
import com.kepu.pojo.StCommunityApply;
import com.kepu.pojo.StCommunityApplyExample;
import com.kepu.pojo.StCommunityArticle;
import com.kepu.pojo.StCommunityArticleExample;
import com.kepu.pojo.StCommunityExample;
import com.kepu.pojo.StCommunityHotSearch;
import com.kepu.pojo.StCommunityHotSearchExample;
import com.kepu.pojo.StCommunityNotice;
import com.kepu.pojo.StCommunityNoticeExample;
import com.kepu.pojo.StCommunityTeach;
import com.kepu.pojo.StCommunityTeachDetail;
import com.kepu.pojo.StCommunityTeachDetailExample;
import com.kepu.pojo.StCommunityTeachExample;
import com.kepu.pojo.StCommunityUser;
import com.kepu.pojo.StCommunityUserExample;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageExample;
import com.kepu.pojo.community.RemindResult;
import com.kepu.pojo.community.TeachResult;
import com.kepu.service.CommunityService;
import com.kepu.service.SysService;
import com.kepu.util.DateUtil;
import com.kepu.util.LotteryUtil;
import com.kepu.util.StrSimilarityUtils;
import com.kepu.util.StringUtil;
import com.kepu.util.SystemSession;

@Service
public class CommunityServiceImpl implements CommunityService {

	
	@Autowired
	private StCommunityMapper communityMapper;
	@Autowired
	private StCommunityUserMapper communityUserMapper;
	@Autowired
	private StCommunityApplyMapper communityApplyMapper;
	@Autowired
	private StCommunityArticleMapper communityArticleMapper;
	@Autowired
	private StUserMapper userMapper;
	@Autowired
	private StArticleVoteMapper articleVoteMapper;
	@Autowired
	private StArticleCommentMapper articleCommentMapper;
	@Autowired
	private StCommunityNoticeMapper communityNoticeMapper;
	@Autowired
	private StCommunityTeachMapper communityTeachMapper;
	@Autowired
	private StCommunityTeachDetailMapper communityTeachDetailMapper;
	@Autowired
	private StCommunityHotSearchMapper communityHotSearchMapper;
	@Autowired
	private SysService sysService;
	@Autowired
	private StVillageMapper villageMapper;
	@Override
	public KePuResult getCarousel(Integer userId,Integer total) {
		StCommunityExample example=new StCommunityExample();
		StCommunityExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("createTime desc");
		criteria.andCarouselEqualTo(1);
		criteria.andStateEqualTo(0);
		PageHelper.startPage(1, total);
		List<StCommunity> list = communityMapper.selectByExample(example);
		List<Map<String,String>> data=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StCommunity community:list ) {
			temp=new HashMap<String, String>();
			temp.put("name",community.getName());
			temp.put("logo",community.getLogo());
			temp.put("communityId",community.getUid()+"");
			temp.put("type",community.getType()+"");
			// 0:未加入 1:申请中 2：已加入
			int state=0;
			StCommunityUserExample example2=new StCommunityUserExample();
			StCommunityUserExample.Criteria criteria2=example2.createCriteria();
			criteria2.andCommunityidEqualTo(community.getUid());
			criteria2.andUseridEqualTo(userId);
			List<StCommunityUser> join = communityUserMapper.selectByExample(example2);
			if(join!=null&&join.size()!=0){
				state=2;
			}else{
				StCommunityApplyExample example3=new StCommunityApplyExample();
				StCommunityApplyExample.Criteria criteria3=example3.createCriteria();
				criteria3.andUseridEqualTo(userId);
				criteria3.andCommunityidEqualTo(community.getUid());
				criteria3.andStateEqualTo(0);
				List<StCommunityApply> apply = communityApplyMapper.selectByExample(example3);
				if(apply!=null&&apply.size()!=0)
					state=1;
			}
			temp.put("state",state+"");
			data.add(temp);
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", data);
	}
	@Override
	public KePuResult getAllCommunity(Integer userId,Integer page, Integer size,String query) {
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityExample example=new StCommunityExample();
		example.setOrderByClause("createTime desc");
		StCommunityExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		if(StringUtil.isNotEmpty(query))
			criteria.andNameLike("%"+query+"%");
		PageHelper.startPage(page, size);
		List<StCommunity> myList = communityMapper.selectByExample(example);
		List<Map<String,String>> communityList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StCommunity community : myList) {
			temp=new HashMap<String, String>();
			temp.put("name",community.getName());
			temp.put("logo",community.getLogo());
			temp.put("communityId",community.getUid()+"");
			temp.put("type",community.getType()+"");
			// 0:未加入 1:申请中 2：已加入
			int state=0;
			StCommunityUserExample example2=new StCommunityUserExample();
			StCommunityUserExample.Criteria criteria2=example2.createCriteria();
			criteria2.andCommunityidEqualTo(community.getUid());
			criteria2.andUseridEqualTo(userId);
			List<StCommunityUser> join = communityUserMapper.selectByExample(example2);
			if(join!=null&&join.size()!=0){
				state=2;
			}else{
				StCommunityApplyExample example3=new StCommunityApplyExample();
				StCommunityApplyExample.Criteria criteria3=example3.createCriteria();
				criteria3.andUseridEqualTo(userId);
				criteria3.andCommunityidEqualTo(community.getUid());
				criteria3.andStateEqualTo(0);
				List<StCommunityApply> apply = communityApplyMapper.selectByExample(example3);
				if(apply!=null&&apply.size()!=0)
					state=1;
			}
			temp.put("state",state+"");
			communityList.add(temp);
		}
		PageInfo<StCommunity> pageInfo=new PageInfo<StCommunity>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("communityList", communityList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getMyCommunity(Integer userId,Long timestamp) {
		List<Integer> r=getMyJoinCommunityId(userId);
		if(r.size()==0){
			List<Map<String,String>> communityList=new LinkedList<Map<String,String>>();
			return KePuResult.ok(ResultConstant.code_ok, "获取成功", communityList);
		}
		Map<String,Object> map=new HashMap<String, Object>();
		List<Map<String,String>> communityList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		StCommunityExample example=new StCommunityExample();
		example.setOrderByClause("CONVERT(NAME USING gbk)");
		StCommunityExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andUidIn(r);
		List<StCommunity> myList = communityMapper.selectByExample(example);
		for (StCommunity stCommunity : myList) {
			temp=new HashMap<String, String>();
			temp.put("name", stCommunity.getName());
			temp.put("logo",stCommunity.getLogo());
			temp.put("communityId",stCommunity.getUid()+"");
			int updateNum=0;
			if(timestamp!=null){
				Date begin=new Date(timestamp);
				StCommunityArticleExample example2=new StCommunityArticleExample();
				StCommunityArticleExample.Criteria criteria2 =example2.createCriteria();
				criteria2.andCommunityidEqualTo(stCommunity.getUid());
				criteria2.andPublishtimeGreaterThan(begin);
				List<StCommunityArticle> article = communityArticleMapper.selectByExample(example2);
				temp.put("updateNum",article.size()+"");
			}else{
				temp.put("updateNum",updateNum+"");
			}
			communityList.add(temp);
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", communityList);
	}
	@Override
	public List<Integer> getMyJoinCommunityId(Integer userId) {
		StCommunityUserExample example=new StCommunityUserExample();
		StCommunityUserExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		List<StCommunityUser> join = communityUserMapper.selectByExample(example);
		List<Integer> r=new LinkedList<Integer>();
		for (StCommunityUser stCommunityUser : join) {
			r.add(stCommunityUser.getCommunityid());
		}
		return r;
	}
	@Override
	public KePuResult joinCommunity(Integer userId, Integer communityId) {
		StCommunity community = communityMapper.selectByPrimaryKey(communityId);
		if(community==null){
			return KePuResult.ok(ResultConstant.code_yewu, "该社团不存在", "");
		}
		if(community.getType()==1){
			return KePuResult.ok(ResultConstant.code_yewu, "加入该社团需要审核", "");
		}
		if(whetherMyJoin(userId,communityId)){
			return KePuResult.ok(ResultConstant.code_yewu, "请勿重复申请", "");
		}
		StCommunityUser scu=new StCommunityUser();
		scu.setCommunityid(communityId);
		scu.setUserid(userId);
		scu.setJointime(new Date());
		scu.setType(0);
		communityUserMapper.insertSelective(scu);
		community.setMembernum(community.getMembernum()+1);
		communityMapper.updateByPrimaryKeySelective(community);
		return KePuResult.ok(ResultConstant.code_ok, "加入成功", "");
	}
	@Override
	public KePuResult applyCommunity(Integer userId, Integer communityId,
			String userName, String mobile, String IDcardZ, String IDcardF,
			String position, String credential,String sex,String birthday,String photo,
			String career,String positional,String education,String selfIntroduction,String IDcardZHold) {
		StCommunity community = communityMapper.selectByPrimaryKey(communityId);
		if(community==null){
			return KePuResult.ok(ResultConstant.code_yewu, "该社团不存在", "");
		}
		if(whetherMyJoin(userId,communityId)){
			return KePuResult.ok(ResultConstant.code_yewu, "请勿重复申请", "");
		}
		if(community.getType()==2){
			return KePuResult.ok(ResultConstant.code_yewu, "普通社团直接申请加入", "");
		}
		StCommunityApplyExample example=new StCommunityApplyExample();
		example.setOrderByClause("createtime desc");
		StCommunityApplyExample.Criteria criteria=example.createCriteria();
		criteria.andCommunityidEqualTo(communityId);
		criteria.andUseridEqualTo(userId);
		List<StCommunityApply> r = communityApplyMapper.selectByExample(example);
		if(r.size()!=0){
			StCommunityApply a=r.get(0);
			if(a.getState()==0)
				return KePuResult.ok(ResultConstant.code_yewu, "申请正在处理中", "");
		}
		StCommunityApply apply=new StCommunityApply();
		apply.setUserid(userId);
		apply.setCommunityid(communityId);
		apply.setCreatetime(new Date());
		apply.setCredential(credential);
		apply.setIdcardf(IDcardF);
		apply.setIdcardz(IDcardZ);
		apply.setPosition(position);
		apply.setMobile(mobile);
		apply.setUsername(userName);
		apply.setSex(sex);
		apply.setBirthday(birthday);
		apply.setPhoto(photo);
		apply.setCareer(career);
		apply.setPositional(positional);
		apply.setEducation(education);
		apply.setSelfintroduction(selfIntroduction);
		apply.setIdcardzhold(IDcardZHold);
		communityApplyMapper.insertSelective(apply);
		community.setApplynum(community.getApplynum()+1);
		communityMapper.updateByPrimaryKeySelective(community);
		return KePuResult.ok(ResultConstant.code_ok, "申请成功,请等待管理员审核", "");
	}
	@Override
	public Boolean whetherMyJoin(Integer userId, Integer communityId) {
		StCommunityUserExample example=new StCommunityUserExample();
		StCommunityUserExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		criteria.andCommunityidEqualTo(communityId);
		List<StCommunityUser> join = communityUserMapper.selectByExample(example);
		return join.size()==1;
	}
	@Override
	public  KePuResult  publishArticle(Integer userId, Integer communityId,
			String content, String detailPics) {
		StCommunityArticle article=new StCommunityArticle();
		article.setCommunityid(communityId);
		article.setUserid(userId);
		StUser user = userMapper.selectByPrimaryKey(userId);
		article.setAvatar(user.getAvatar());
		article.setContent(content);
		article.setDetailpics(detailPics);
		article.setPublishtime(new Date());
		article.setUsername(user.getNickname());
		communityArticleMapper.insertSelective(article);
		Map<String,String> map=new HashMap<String, String>();
		map.put("articleId", article.getUid()+"");
		StActivityRecord arecord=new StActivityRecord();
		arecord.setScore(1.0);
		if(communityId<1000){
			sysService.insertActivityRecord(user, arecord, 5, "发表圈子动态,帖子ID"+article.getUid());
			// 调用抽奖接口
			/*if(StringUtil.isNotEmpty(content))
				LotteryUtil.getLottery(user.getNickname(), user.getMobile(), content, user.getAvatar());*/
		}
		else
			sysService.insertActivityRecord(user, arecord, 51, "发表乡镇圈子动态,帖子ID"+article.getUid());
		return KePuResult.ok(ResultConstant.code_ok, "发表成功", map);
	}
	
	
	@Override
	public KePuResult getArticle(Integer userId, Integer communityId,
			Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityArticleExample example=new StCommunityArticleExample();
		example.setOrderByClause("publishTime desc");
		StCommunityArticleExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andCommunityidEqualTo(communityId);
		PageHelper.startPage(page, size);
		List<StCommunityArticle> myList = communityArticleMapper.selectByExample(example);
		List<Map<String,Object>> articleList=new LinkedList<Map<String,Object>>();
		Map<String,Object> temp;
		for (StCommunityArticle article : myList) {
			temp=new HashMap<String, Object>();
			temp.put("nickName",article.getUsername());
			temp.put("userId",article.getUserid()+"");
			temp.put("avatar",article.getAvatar());
			temp.put("content",article.getContent());
			temp.put("publishTime",DateUtil.formatDate(article.getPublishtime(), MyConstant.updatetime));
			temp.put("detailPics",article.getDetailpics());
			temp.put("articleId",article.getUid()+"");
			temp.put("praiseNum",article.getPraisenum()+"");
			temp.put("replyNum",article.getReplynum()+"");
			StArticleVoteExample example2=new StArticleVoteExample();
			StArticleVoteExample.Criteria criteria2=example2.createCriteria();
			criteria2.andArticleidEqualTo(article.getUid());
			criteria2.andUseridEqualTo(userId);
			criteria2.andStateEqualTo(0);
			List<StArticleVote> r = articleVoteMapper.selectByExample(example2);
			temp.put("myPraise",r.size()==0?"0":"1");
			// 2017-11-04  加载前10条评论 begin
			String version=SystemSession.get().getAppVersion();
			if(StringUtil.isNotEmpty(version)&&version.compareTo("6.1.0")>0){
				List<Map<String,Object>> commentList=new LinkedList<Map<String,Object>>();
				StArticleCommentExample example3=new StArticleCommentExample();
				example3.setOrderByClause("createTime desc");
				StArticleCommentExample.Criteria criteria3=example3.createCriteria();
				criteria3.andTypeEqualTo(1);
				criteria3.andTypeidEqualTo(article.getUid());
				PageHelper.startPage(1, 10);
				List<StArticleComment> r1 = articleCommentMapper.selectByExample(example3);
				Map<String,Object> t1;
				for (StArticleComment stArticleComment : r1) {
					t1=new HashMap<String, Object>();
					t1.put("avatar", stArticleComment.getAvatar());
					t1.put("nickName", stArticleComment.getUsername());
					t1.put("userId", stArticleComment.getUserid()+"");
					t1.put("createTime", DateUtil.formatDate(stArticleComment.getCreatetime(), MyConstant.updatetime));
					t1.put("comment", stArticleComment.getContent());
					t1.put("commentId", stArticleComment.getUid()+"");
					StArticleCommentExample example4=new StArticleCommentExample();
					example2.setOrderByClause("createTime desc");
					StArticleCommentExample.Criteria criteria4=example4.createCriteria();
					criteria4.andTypeEqualTo(2);
					criteria4.andTypeidEqualTo(stArticleComment.getUid());
					List<StArticleComment> r2 = articleCommentMapper.selectByExample(example4);
					Map<String,Object> t2;
					List<Map<String,Object>> replyList=new LinkedList<Map<String,Object>>();
					for (StArticleComment stArticleComment2 : r2) {
						t2=new HashMap<String, Object>();
						t2.put("avatar", stArticleComment2.getAvatar());
						t2.put("nickName", stArticleComment2.getUsername());
						t2.put("userId", stArticleComment2.getUserid()+"");
						t2.put("createTime", DateUtil.formatDate(stArticleComment2.getCreatetime(), MyConstant.updatetime));
						t2.put("comment", stArticleComment2.getContent());
						t2.put("replyId", stArticleComment2.getUid()+"");
						replyList.add(t2);
					}
					t1.put("replyList", replyList);
					commentList.add(t1);
				}
				temp.put("commentList",commentList);
			}
			//end
			articleList.add(temp);
		}
		PageInfo<StCommunityArticle> pageInfo=new PageInfo<StCommunityArticle>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("articleList", articleList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult dpArticle(Integer articleId, Integer userId,
			Integer operate) {
		StCommunityArticle a = communityArticleMapper.selectByPrimaryKey(articleId);
		if(a==null){
			return KePuResult.ok(ResultConstant.code_yewu, "该帖子已被删除或不存在", "");
		}
		StArticleVoteExample example=new StArticleVoteExample();
		StArticleVoteExample.Criteria criteria=example.createCriteria();
		criteria.andArticleidEqualTo(articleId);
		criteria.andUseridEqualTo(userId);
		List<StArticleVote> resultList = articleVoteMapper.selectByExample(example);
		if(resultList.size()==0&&operate==1){
			StArticleVote v=new StArticleVote();
			v.setArticleid(articleId);
			v.setUserid(userId);
			v.setState(0);
			articleVoteMapper.insertSelective(v);
			a.setPraisenum(a.getPraisenum()+1);
			communityArticleMapper.updateByPrimaryKeySelective(a);
		}else if(resultList.size()!=0){
			StArticleVote v=resultList.get(0);
			if(v.getState()==1&&operate==1){
				v.setState(0);
				articleVoteMapper.updateByPrimaryKeySelective(v);
				a.setPraisenum(a.getPraisenum()+1);
				communityArticleMapper.updateByPrimaryKeySelective(a);
			}else if(v.getState()==0&&operate==0){
				v.setState(1);
				articleVoteMapper.updateByPrimaryKeySelective(v);
				a.setPraisenum(Math.max(0, a.getPraisenum()-1));
				communityArticleMapper.updateByPrimaryKeySelective(a);
			}
		}
		return KePuResult.ok(ResultConstant.code_ok, "操作成功", "");
	}
	@Override
	public KePuResult getCommunityDetail(Integer userId, Integer communityId,Date timestamp) {
		StCommunity community = communityMapper.selectByPrimaryKey(communityId);
		Map<String,String> data=new HashMap<String, String>();
		data.put("name", community.getName());
		data.put("introduce", community.getIntroduce());
		data.put("display", community.getDisplay());
		if(userId!=null){
			StCommunityUserExample example=new StCommunityUserExample();
			StCommunityUserExample.Criteria criteria=example.createCriteria();
			criteria.andUseridEqualTo(userId);
			criteria.andCommunityidEqualTo(communityId);
			List<StCommunityUser> join = communityUserMapper.selectByExample(example);
			data.put("adminUser", join.get(0).getType()+""); 
	
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("userId", userId);
			param.put("communityId", communityId);
			param.put("timestamp", timestamp);
			List<RemindResult> myRemind = articleCommentMapper.getMyRemind(param);
			data.put("remindNum", myRemind.size()+"");  
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", data);
	}
	@Override
	public StCommunity getCommunityByArticleId(Integer articleId) {
		StCommunityArticle l = communityArticleMapper.selectByPrimaryKey(articleId);
		if(l!=null){
			return communityMapper.selectByPrimaryKey(l.getCommunityid());
		}
		return null;
	}
	@Override
	public KePuResult getArticleDetail(Integer articleId, Integer userId) {
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityArticle article = communityArticleMapper.selectByPrimaryKey(articleId);
		if(article==null||article.getState()!=0)
			return KePuResult.ok(ResultConstant.code_yewu, "该帖子已被删除或不存在", "");
		map.put("nickName",article.getUsername());
		map.put("userId",article.getUserid()+"");
		map.put("avatar",article.getAvatar());
		map.put("content",article.getContent());
		map.put("publishTime",DateUtil.formatDate(article.getPublishtime(), MyConstant.updatetime));
		map.put("detailPics",article.getDetailpics());
		List<Map<String,Object>> commentList=new LinkedList<Map<String,Object>>();
		StArticleCommentExample example=new StArticleCommentExample();
		example.setOrderByClause("createTime desc");
		StArticleCommentExample.Criteria criteria=example.createCriteria();
		criteria.andTypeEqualTo(1);
		criteria.andTypeidEqualTo(articleId);
		List<StArticleComment> r1 = articleCommentMapper.selectByExample(example);
		Map<String,Object> t1;
		for (StArticleComment stArticleComment : r1) {
			t1=new HashMap<String, Object>();
			t1.put("avatar", stArticleComment.getAvatar());
			t1.put("nickName", stArticleComment.getUsername());
			t1.put("userId", stArticleComment.getUserid()+"");
			t1.put("createTime", DateUtil.formatDate(stArticleComment.getCreatetime(), MyConstant.updatetime));
			t1.put("comment", stArticleComment.getContent());
			t1.put("commentId", stArticleComment.getUid()+"");
			StArticleCommentExample example2=new StArticleCommentExample();
			example2.setOrderByClause("createTime desc");
			StArticleCommentExample.Criteria criteria2=example2.createCriteria();
			criteria2.andTypeEqualTo(2);
			criteria2.andTypeidEqualTo(stArticleComment.getUid());
			List<StArticleComment> r2 = articleCommentMapper.selectByExample(example2);
			Map<String,Object> t2;
			List<Map<String,Object>> replyList=new LinkedList<Map<String,Object>>();
			for (StArticleComment stArticleComment2 : r2) {
				t2=new HashMap<String, Object>();
				t2.put("avatar", stArticleComment2.getAvatar());
				t2.put("nickName", stArticleComment2.getUsername());
				t2.put("userId", stArticleComment2.getUserid()+"");
				t2.put("createTime", DateUtil.formatDate(stArticleComment2.getCreatetime(), MyConstant.updatetime));
				t2.put("comment", stArticleComment2.getContent());
				t2.put("replyId", stArticleComment2.getUid()+"");
				replyList.add(t2);
			}
			t1.put("replyList", replyList);
			commentList.add(t1);
		}
		map.put("commentList",commentList);
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult sentComment(StUser user, Integer articleId, String comment) {
		Map<String,String> data=new HashMap<String, String>();
		StCommunityArticle article = communityArticleMapper.selectByPrimaryKey(articleId);
		if(article==null||article.getState()!=0)
			return KePuResult.ok(ResultConstant.code_yewu, "该帖子已被删除或不存在", "");
		StUser u=userMapper.selectByPrimaryKey(user.getUserid());
		StArticleComment stComment=new StArticleComment();
		stComment.setUserid(u.getUserid());
		stComment.setUsername(u.getNickname());
		stComment.setAvatar(u.getAvatar());
		stComment.setContent(comment);
		stComment.setCreatetime(new Date());
		stComment.setType(1);
		stComment.setTypeid(articleId);
		articleCommentMapper.insertSelective(stComment);
		article.setReplynum(article.getReplynum()+1);
		communityArticleMapper.updateByPrimaryKeySelective(article);
		String version=SystemSession.get().getAppVersion();
		if(StringUtil.isNotEmpty(version)&&version.compareTo("6.2.0")<0){
			data=null;
		}else{
			data.put("commentId", stComment.getUid()+"");
		}
		// 调用抽奖接口   帖子名以@开头
		StCommunity community=getCommunityByArticleId(articleId);
		if(community.getUid()<10000){
			String content=article.getContent();
			if(StringUtil.isNotEmpty(content)&&content.startsWith("@"))
				LotteryUtil.getLottery(user.getNickname(),user.getMobile(),comment,user.getAvatar(),community.getName());
		}
		return KePuResult.ok(ResultConstant.code_ok, "发表成功", data);
	}
	@Override
	public KePuResult replyComment(StUser user, Integer commentId, String comment) {
		Map<String,String> data=new HashMap<String, String>();
		StArticleComment c=articleCommentMapper.selectByPrimaryKey(commentId);
		if(c==null)
			return KePuResult.ok(ResultConstant.code_yewu, "该评论已被删除或不存在", "");
		StUser u=userMapper.selectByPrimaryKey(user.getUserid());
		StArticleComment stComment=new StArticleComment();
		stComment.setUserid(u.getUserid());
		stComment.setUsername(u.getNickname());
		stComment.setAvatar(u.getAvatar());
		stComment.setContent(comment);
		stComment.setCreatetime(new Date());
		stComment.setType(2);
		stComment.setTypeid(commentId);
		articleCommentMapper.insertSelective(stComment);
		String version=SystemSession.get()==null?"":SystemSession.get().getAppVersion();
		if(StringUtil.isNotEmpty(version)&&version.compareTo("6.2.0")<0){
			data=null;
		}else
			data.put("replyId", stComment.getUid()+"");
		return KePuResult.ok(ResultConstant.code_ok, "回复成功", data);
	}
	@Override
	public StCommunity getCommunityByCommentId(Integer commentId) {
		StArticleComment c=articleCommentMapper.selectByPrimaryKey(commentId);
		if(c!=null){
			StCommunityArticle l = communityArticleMapper.selectByPrimaryKey(c.getTypeid());
			if(l!=null){
				return communityMapper.selectByPrimaryKey(l.getCommunityid());
			}
		}
		return null;
	}
	@Override
	public KePuResult getNoticeList(Integer communityId, Integer page,
			Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityNoticeExample example=new StCommunityNoticeExample();
		StCommunityNoticeExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("createTime desc");
		criteria.andCommunityidEqualTo(communityId);
		PageHelper.startPage(page, size);
		List<StCommunityNotice> list = communityNoticeMapper.selectByExample(example);
		PageInfo<StCommunityNotice> pageInfo=new PageInfo<StCommunityNotice>(list);
		Map<String,String> temp;
		List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
		for (StCommunityNotice notice : list) {
			temp=new HashMap<String, String>();
			temp.put("noticeId", notice.getUid()+"");
			temp.put("title", notice.getTitle());
			temp.put("author", notice.getAuchor());
			temp.put("publishTime",DateUtil.formatDate(notice.getCreatetime(), MyConstant.Team_date));
			myList.add(temp);
		}
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("noticeList", myList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult publishNotice(Integer userId, Integer communityId,
			String title, String auchor, String content) {
		StCommunityNotice notice=new StCommunityNotice();
		notice.setAuchor(auchor);
		notice.setContent(content);
		notice.setCreatetime(new Date());
		notice.setUserid(userId);
		notice.setTitle(title);
		notice.setCommunityid(communityId);
		communityNoticeMapper.insertSelective(notice);
		Map<String,String> map=new HashMap<String, String>();
		map.put("noticeId", notice.getUid()+"");
		return KePuResult.ok(ResultConstant.code_ok, "发表成功", map);
	}
	@Override
	public Boolean whetherAdmin(Integer userId, Integer communityId) {
		StCommunityUserExample example=new StCommunityUserExample();
		StCommunityUserExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		criteria.andCommunityidEqualTo(communityId);
		List<StCommunityUser> join = communityUserMapper.selectByExample(example);
		return join.size()==1&&join.get(0).getType()==1;
	}
	@Override
	public StCommunity getCommunityByNoticeId(Integer noticeId) {
		StCommunityNotice l = communityNoticeMapper.selectByPrimaryKey(noticeId);
		if(l!=null){
			return communityMapper.selectByPrimaryKey(l.getCommunityid());
		}
		return null;
	}
	@Override
	public KePuResult getNoticeDetail(Integer noticeId) {
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityNotice notice = communityNoticeMapper.selectByPrimaryKey(noticeId);
		if(notice==null)
			return KePuResult.ok(ResultConstant.code_yewu, "该公告已被删除或不存在", "");
		map.put("title",notice.getTitle());
		map.put("noticeId",notice.getUid()+"");
		map.put("content",notice.getContent());
		map.put("author", notice.getAuchor());
		map.put("publishTime",DateUtil.formatDate(notice.getCreatetime(), MyConstant.Team_date));
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getTeachList(Integer communityId, Integer page,
			Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityTeachExample example=new StCommunityTeachExample();
		StCommunityTeachExample.Criteria criteria=example.createCriteria();
		example.setOrderByClause("createTime desc");
		criteria.andCommunityidEqualTo(communityId);
		PageHelper.startPage(page, size);
		List<StCommunityTeach> list = communityTeachMapper.selectByExample(example);
		PageInfo<StCommunityTeach> pageInfo=new PageInfo<StCommunityTeach>(list);
		Map<String,String> temp;
		List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
		for (StCommunityTeach teach : list) {
			temp=new HashMap<String, String>();
			temp.put("teachId", teach.getUid()+"");
			temp.put("title", teach.getTitle());
			temp.put("publishTime",DateUtil.formatDate(teach.getCreatetime(), MyConstant.Team_date));
			StCommunityTeachDetailExample example2=new StCommunityTeachDetailExample();
			StCommunityTeachDetailExample.Criteria criteria2=example2.createCriteria();
			criteria2.andTeachidEqualTo(teach.getUid());
			List<StCommunityTeachDetail> detailList = communityTeachDetailMapper.selectByExample(example2);
			if(detailList.size()!=0){
				StCommunityTeachDetail detail=detailList.get(0);
				temp.put("coverPic", detail.getCoverpic());
				temp.put("description", detail.getDescription());
			}else{
				temp.put("coverPic", "");
				temp.put("description", "");
			}
			myList.add(temp);
		}
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("teachList", myList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult publishTeach(Integer userId, Integer communityId,
			String title, List<TeachResult> teachList) {
		//每天两次
		StCommunityTeachExample example=new StCommunityTeachExample();
		StCommunityTeachExample.Criteria criteria=example.createCriteria();
		criteria.andCommunityidEqualTo(communityId);
		criteria.andCreatetimeBetween(DateUtil.startOfTodDay(), DateUtil.endOfTodDay());
		List<StCommunityTeach> list = communityTeachMapper.selectByExample(example);
		if(list.size()>=2){
			return KePuResult.ok(ResultConstant.code_yewu, "超出每日教学上传次数", "");
		}
		StCommunityTeach teach=new StCommunityTeach();
		teach.setCommunityid(communityId);
		teach.setCreatetime(new Date());
		teach.setTitle(title);
		teach.setUserid(userId);
		communityTeachMapper.insertSelective(teach);
		Iterator iterator = teachList.iterator();
		while(iterator.hasNext()){
			Map<String,String> map=(Map<String, String>) iterator.next();
			StCommunityTeachDetail detail=new StCommunityTeachDetail();
			detail.setCoverpic(map.get("coverPic"));
			detail.setVediourl(map.get("vedioUrl"));
			detail.setTeachid(teach.getUid());
			detail.setDescription(map.get("description"));
			communityTeachDetailMapper.insertSelective(detail);
		}
		Map<String,String> map=new HashMap<String, String>();
		map.put("teachId", teach.getUid()+"");
		return KePuResult.ok(ResultConstant.code_ok, "发表成功", map);
	}
	@Override
	public StCommunity getCommunityByTeachId(Integer teachId) {
		StCommunityTeach c=communityTeachMapper.selectByPrimaryKey(teachId);
		if(c!=null){
				return communityMapper.selectByPrimaryKey(c.getCommunityid());
		}
		return null;
	}
	@Override
	public KePuResult getTeachDetail(Integer teachId) {
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityTeach c= communityTeachMapper.selectByPrimaryKey(teachId);
		if(c==null)
			return KePuResult.ok(ResultConstant.code_yewu, "该公告已被删除或不存在", "");
		map.put("title",c.getTitle());
		map.put("publishTime",DateUtil.formatDate(c.getCreatetime(), MyConstant.Team_date));
		StCommunityTeachDetailExample example=new StCommunityTeachDetailExample();
		StCommunityTeachDetailExample.Criteria criteria=example.createCriteria();
		criteria.andTeachidEqualTo(teachId);
		List<StCommunityTeachDetail> teachList=communityTeachDetailMapper.selectByExample(example);
		Map<String,String> temp;
		List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
		for (StCommunityTeachDetail detail : teachList) {
			temp=new HashMap<String, String>();
			temp.put("coverPic", detail.getCoverpic());
			temp.put("description", detail.getDescription());
			temp.put("vedioUrl", detail.getVediourl());
			myList.add(temp);
		}
		map.put("contentList", myList);
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult getMyRemind(Integer userId, Integer communityId,Date timestamp) {
		// 帖子被别人评论  / 评论被别人回复
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("communityId", communityId);
		param.put("timestamp", timestamp);
		List<RemindResult> myRemind = articleCommentMapper.getMyRemind(param);
		List<Map<String,String>> myList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (RemindResult remindResult : myRemind) {
			temp=new HashMap<String, String>();
			temp.put("publishTime",DateUtil.formatDate(remindResult.getCreatetime(), MyConstant.updatetime));
			temp.put("userId", remindResult.getUserid()+"");
			temp.put("nickName", remindResult.getUsername());
			temp.put("avatar", remindResult.getAvatar());
			temp.put("comment", remindResult.getContent());
			temp.put("articleId", remindResult.getArticleId()+"");
			String pic="";
			if(StringUtil.isNotEmpty(remindResult.getDetailPics())){
				String ps[]=remindResult.getDetailPics().split(",");
				pic=ps[0];
			}
			temp.put("pic", pic);
			myList.add(temp);
		}
		map.put("RemindList",myList);
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public KePuResult quitCommunity(Integer userId, Integer communityId) {
		StCommunity community = communityMapper.selectByPrimaryKey(communityId);
		if(community==null){
			return KePuResult.ok(ResultConstant.code_yewu, "该社团不存在", "");
		}
		StCommunityUserExample scu=new StCommunityUserExample();
		StCommunityUserExample.Criteria sc=scu.createCriteria();
		sc.andUseridEqualTo(userId);
		sc.andCommunityidEqualTo(communityId);
		List<StCommunityUser> r = communityUserMapper.selectByExample(scu);
		if(r.size()==1){
			communityUserMapper.deleteByExample(scu);
			community.setMembernum(community.getMembernum()-1);
			communityMapper.updateByPrimaryKeySelective(community);
			return KePuResult.ok(ResultConstant.code_ok, "退出成功", "");
		}else{
			return KePuResult.ok(ResultConstant.code_yewu, "该用户未加入该社团", "");
		}
	}
	@Override
	public void addHotSearch(String query) {
		if(query.length()<2)
			return;
		StCommunityHotSearchExample example=new StCommunityHotSearchExample();
		StCommunityHotSearchExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		List<StCommunityHotSearch> list = communityHotSearchMapper.selectByExample(example);
		float maxSilimar=0l;
		int tempId=1;
		for (StCommunityHotSearch stHotSearch : list) {
			String word=stHotSearch.getWord();
			float current=StrSimilarityUtils.getSimilarityRatio(word, query);
			if(current>maxSilimar){
				maxSilimar=current;
				tempId=stHotSearch.getUid();
			}	
		}
		if(maxSilimar>0.4){
			StCommunityHotSearch r = communityHotSearchMapper.selectByPrimaryKey(tempId);
			r.setSearchnum(r.getSearchnum()+1);
			r.setUpdatetime(new Date());
			communityHotSearchMapper.updateByPrimaryKeySelective(r);
		}else{
			StCommunityHotSearch r =new StCommunityHotSearch();
			r.setWord(query);
			r.setCreatetime(new Date());
			r.setUpdatetime(new Date());
			communityHotSearchMapper.insertSelective(r);
		}
		
	}
	@Override
	public KePuResult getHotSearch() {
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityHotSearchExample example=new StCommunityHotSearchExample();
		StCommunityHotSearchExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		PageHelper.startPage(1, 20);
		List<StCommunityHotSearch> list = communityHotSearchMapper.selectByExample(example);
		List<String> r=new LinkedList<String>();
		for (StCommunityHotSearch stHotSearch : list) {
			r.add(stHotSearch.getWord());
		}
		map.put("hotWords", r);
		map.put("totalcount", r.size()+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	@Override
	public Integer getCommunityIdByArticleId(Integer articleId) {
		StCommunityArticle l = communityArticleMapper.selectByPrimaryKey(articleId);
		if(l!=null){
			return l.getCommunityid();
		}
		return null;
	}
	@Override
	public Integer getCommunityIdByCommentId(Integer commentId) {
		StArticleComment c=articleCommentMapper.selectByPrimaryKey(commentId);
		if(c!=null){
			StCommunityArticle l = communityArticleMapper.selectByPrimaryKey(c.getTypeid());
			if(l!=null){
				return l.getCommunityid();
			}
		}
		return null;
	}
	@Override
	public KePuResult editArticle(Integer userId, Integer articleId,
			String content, String detailPics) {
		StCommunityArticle article = communityArticleMapper.selectByPrimaryKey(articleId);
		if(article==null)
			return KePuResult.ok(ResultConstant.code_yewu, "该帖子已被删除或不存在", "");
		else{
			if(article.getUserid().intValue()!=userId.intValue())
				return KePuResult.ok(ResultConstant.code_yewu, "权限不足,修改失败", "");
			else{
				if(article.getCommunityid()<1000){
					if(!whetherMyJoin(userId, article.getCommunityid()))
						return KePuResult.ok(ResultConstant.code_yewu, "已退出社团,仅可删除操作", "");
				}	
				article.setContent(content);
				article.setDetailpics(detailPics);
				communityArticleMapper.updateByPrimaryKeySelective(article);
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("articleId", article.getUid()+"");
				return KePuResult.ok(ResultConstant.code_ok, "修改成功",map);
			}
		}
	}
	@Override
	public KePuResult deleteArticle(Integer userId,Integer articleId) {
		StCommunityArticle article = communityArticleMapper.selectByPrimaryKey(articleId);
		if(article==null)
			return KePuResult.ok(ResultConstant.code_yewu, "该帖子已被删除或不存在", "");
		else{
			if(article.getUserid().intValue()!=userId.intValue())
				return KePuResult.ok(ResultConstant.code_yewu, "权限不足,删除失败", "");
			else{
				article.setState(1);
				communityArticleMapper.updateByPrimaryKeySelective(article);
				return KePuResult.ok(ResultConstant.code_ok, "删除成功", "");
			}
		}
	}
	@Override
	public KePuResult getMyArticle(Integer userId, Integer page, Integer size,Integer type) {
		StVillageExample example4=new StVillageExample();
		StVillageExample.Criteria criteria4=example4.createCriteria();
		List<StVillage> vList = villageMapper.selectByExample(example4);
		Map<Integer,String> vMap=new HashMap<Integer, String>();
		for (StVillage stVillage : vList) {
			vMap.put(stVillage.getId(), stVillage.getName());
		}
		StCommunityExample example5=new StCommunityExample();
		StCommunityExample.Criteria criteria5=example5.createCriteria();
		List<StCommunity> cList = communityMapper.selectByExample(example5);
		Map<Integer,String> cMap = new HashMap<Integer, String>();
		for (StCommunity stCommunity : cList) {
			cMap.put(stCommunity.getUid(), stCommunity.getName());
		}
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityArticleExample example=new StCommunityArticleExample();
		example.setOrderByClause("publishTime desc");
		StCommunityArticleExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		criteria.andUseridEqualTo(userId);
		if(type==1)
			criteria.andCommunityidLessThan(1000);
		else if(type==2)
			criteria.andCommunityidGreaterThan(1000);
		PageHelper.startPage(page, size);
		List<StCommunityArticle> myList = communityArticleMapper.selectByExample(example);
		List<Map<String,String>> articleList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StCommunityArticle article : myList) {
			temp=new HashMap<String, String>();
			temp.put("nickName",article.getUsername());
			temp.put("userId",article.getUserid()+"");
			temp.put("avatar",article.getAvatar());
			temp.put("content",article.getContent());
			temp.put("publishTime",DateUtil.formatDate(article.getPublishtime(), MyConstant.updatetime));
			temp.put("detailPics",article.getDetailpics());
			temp.put("articleId",article.getUid()+"");
			temp.put("praiseNum",article.getPraisenum()+"");
			temp.put("replyNum",article.getReplynum()+"");
			if(type==1)
				temp.put("communityName",cMap.get(article.getCommunityid())); // 社团名
			else if(type==2)
				temp.put("villageName",vMap.get(article.getCommunityid())); // 乡镇名
			StArticleVoteExample example2=new StArticleVoteExample();
			StArticleVoteExample.Criteria criteria2=example2.createCriteria();
			criteria2.andArticleidEqualTo(article.getUid());
			criteria2.andUseridEqualTo(userId);
			criteria2.andStateEqualTo(0);
			List<StArticleVote> r = articleVoteMapper.selectByExample(example2);
			temp.put("myPraise",r.size()==0?"0":"1");
			articleList.add(temp);
		}
		PageInfo<StCommunityArticle> pageInfo=new PageInfo<StCommunityArticle>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("articleList", articleList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	

}
