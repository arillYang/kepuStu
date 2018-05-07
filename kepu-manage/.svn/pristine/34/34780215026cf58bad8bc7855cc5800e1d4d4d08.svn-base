package com.kepu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.controller.NewsController;
import com.kepu.mapper.StCommunityApplyMapper;
import com.kepu.mapper.StCommunityArticleMapper;
import com.kepu.mapper.StCommunityMapper;
import com.kepu.mapper.StCommunityNoticeMapper;
import com.kepu.mapper.StCommunityTeachMapper;
import com.kepu.mapper.StCommunityUserMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StCommunity;
import com.kepu.pojo.StCommunityApply;
import com.kepu.pojo.StCommunityApplyExample;
import com.kepu.pojo.StCommunityArticle;
import com.kepu.pojo.StCommunityArticleExample;
import com.kepu.pojo.StCommunityExample;
import com.kepu.pojo.StCommunityNotice;
import com.kepu.pojo.StCommunityNoticeExample;
import com.kepu.pojo.StCommunityTeach;
import com.kepu.pojo.StCommunityTeachExample;
import com.kepu.pojo.StCommunityUser;
import com.kepu.pojo.StCommunityUserExample;
import com.kepu.pojo.StUser;
import com.kepu.pojo.community.MemberList;
import com.kepu.service.CommunityService;
import com.kepu.service.VillageService;
import com.kepu.util.SendTemplateSMSUtil;
import com.kepu.util.StringUtil;

@Service
public class CommunityServiceImpl implements CommunityService{

	protected final Log logger = LogFactory.getLog(CommunityServiceImpl.class);
	@Autowired
	private StCommunityMapper communityMapper;
	@Autowired
	private StCommunityUserMapper communityUserMapper;
	@Autowired
	private StCommunityNoticeMapper communityNoticeMapper;
	@Autowired
	private StCommunityApplyMapper communityApplyMapper;
	@Autowired
	private StCommunityArticleMapper communityArticleMapper;
	@Autowired
	private StCommunityTeachMapper communityTeachMapper;
	@Autowired
	private StUserMapper userMapper;
	@Autowired
	private VillageService villageService;
	@Override
	public Map<String, Object> findStCommunity(PageBean pageBean,StCommunity community) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityExample example=new StCommunityExample();
		example.setOrderByClause("createtime desc");
		StCommunityExample.Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(community.getName()))
			criteria.andNameLike("%"+community.getName()+"%");
		List<StCommunity> list = communityMapper.selectByExample(example);
		PageInfo<StCommunity> pageInfo=new PageInfo<StCommunity>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StCommunity getCommunityById(Integer Id) {
		return communityMapper.selectByPrimaryKey(Id);
	}

	
	@Override
	public void deleteStCommunityById(Integer communityId) {
		communityMapper.deleteByPrimaryKey(communityId);
	}

	@Override
	public void saveStCommunity(StCommunity community){
		if(community!=null){
			if(community.getUid()!=null){
				if(StringUtil.isEmpty(community.getLogo()))
					community.setLogo(null);
				if(StringUtil.isEmpty(community.getDisplay()))
					community.setDisplay(null);
				communityMapper.updateByPrimaryKeySelective(community);
			}else{
				community.setCreatetime(new Date());
				communityMapper.insertSelective(community);
			}
		}
		
	}

	@Override
	public Map<String, Object> findMemberlist(PageBean pageBean,
			MemberList member) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		Map<String,Object> record=new HashMap<String, Object>();
		record.put("communityId", member.getCommunityid());
		if(StringUtil.isNotEmpty(member.getNickName()))
			record.put("nickName","%"+member.getNickName()+"%");
		if(StringUtil.isNotEmpty(member.getMobile()))
			record.put("mobile","%"+member.getMobile()+"%");
		List<MemberList> list = communityUserMapper.getMemberList(record);
		PageInfo<MemberList> pageInfo=new PageInfo<MemberList>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StCommunityUser getCommunityUser(Integer community, Integer userId) {
		StCommunityUserExample example=new StCommunityUserExample();
		StCommunityUserExample.Criteria criteria=example.createCriteria();
		criteria.andCommunityidEqualTo(community);
		criteria.andUseridEqualTo(userId);
		List<StCommunityUser> list = communityUserMapper.selectByExample(example);
		return list.size()==0?null:list.get(0);
	}

	@Override
	public void saveCommunityUser(StCommunityUser communityUser) {
		if(communityUser!=null){
			communityUserMapper.updateByPrimaryKeySelective(communityUser);
		}
		
	}

	@Override
	public Map<String, Object> findStCommunityNotice(PageBean pageBean,
			StCommunityNotice communityNotice) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityNoticeExample example=new StCommunityNoticeExample();
		example.setOrderByClause("createtime desc");
		StCommunityNoticeExample.Criteria criteria=example.createCriteria();
		criteria.andCommunityidEqualTo(communityNotice.getCommunityid());
		if(StringUtil.isNotEmpty(communityNotice.getTitle()))
			criteria.andTitleLike("%"+communityNotice.getTitle()+"%");
		List<StCommunityNotice> list = communityNoticeMapper.selectByExample(example);
		PageInfo<StCommunityNotice> pageInfo=new PageInfo<StCommunityNotice>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StCommunityNotice getCommunityNoticeById(Integer noticeId) {
		return communityNoticeMapper.selectByPrimaryKey(noticeId);
	}

	@Override
	public void saveStCommunityNotice(StCommunityNotice communityNotice) {
		if(communityNotice!=null){
			if(communityNotice.getUid()!=null){
				communityNoticeMapper.updateByPrimaryKeySelective(communityNotice);
			}
			else{
				communityNotice.setUserid(-1);
				communityNotice.setCreatetime(new Date());
				communityNoticeMapper.insertSelective(communityNotice);
			}
		}
		
	}

	@Override
	public Map<String, Object> findApplylist(PageBean pageBean,
			StCommunityApply communityApply) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityApplyExample example=new StCommunityApplyExample();
		example.setOrderByClause("createtime desc");
		StCommunityApplyExample.Criteria criteria=example.createCriteria();
		criteria.andCommunityidEqualTo(communityApply.getCommunityid());
		if(StringUtil.isNotEmpty(communityApply.getUsername()))
			criteria.andUsernameLike("%"+communityApply.getUsername()+"%");
		List<StCommunityApply> list = communityApplyMapper.selectByExample(example);
		PageInfo<StCommunityApply> pageInfo=new PageInfo<StCommunityApply>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public StCommunityApply getStCommunityApplyById(Integer applyId) {
		return communityApplyMapper.selectByPrimaryKey(applyId);
	}

	@Override
	public void saveStCommunityApply(StCommunityApply communityApply) {
		if(communityApply!=null){
			StUser user = userMapper.selectByPrimaryKey(communityApply.getUserid());
			String mobile=user.getMobile();
			StCommunity sc = communityMapper.selectByPrimaryKey(communityApply.getCommunityid());
			String communityName=sc.getName();
			int state=communityApply.getState();
			if(state==1){
				SendTemplateSMSUtil.SendCommunityReject(mobile, communityName);
				sc.setApplynum(sc.getApplynum()-1);
				communityMapper.updateByPrimaryKeySelective(sc);
			}else if(state==2){
				SendTemplateSMSUtil.SendCommunityPass(mobile, communityName);
				StCommunityUser su=new StCommunityUser();
				su.setCommunityid(communityApply.getCommunityid());
				su.setJointime(new Date());
				su.setUserid(user.getUserid());
				communityUserMapper.insertSelective(su);
				sc.setMembernum(sc.getMembernum()+1);
				sc.setApplynum(sc.getApplynum()-1);
				communityMapper.updateByPrimaryKeySelective(sc);
			}
			communityApplyMapper.updateByPrimaryKeySelective(communityApply);
		}
	}

	@Override
	public int deleteCommunityNotice(Integer noticeId) {
		return communityNoticeMapper.deleteByPrimaryKey(noticeId);
	}

	@Override
	public Map<String, Object> findStCommunityArticle(PageBean pageBean,
			StCommunityArticle communityArticle,Integer town,Integer village) {
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityArticleExample example=new StCommunityArticleExample();
		example.setOrderByClause("publishtime desc");
		StCommunityArticleExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		if(StringUtil.isNotEmpty(communityArticle.getContent()))
			criteria.andContentLike("%"+communityArticle.getContent()+"%");
		if(communityArticle.getCommunityid()!=-1)
			criteria.andCommunityidEqualTo(communityArticle.getCommunityid());
		else{
			if(village!=0){
				criteria.andCommunityidEqualTo(village);
			}else if(town!=0){
				List<Integer> values=villageService.getVillageIds(town);
				criteria.andCommunityidIn(values);
			}else{
				criteria.andCommunityidGreaterThan(10000);
			}
		}
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		List<StCommunityArticle> list = communityArticleMapper.selectByExample(example);
		PageInfo<StCommunityArticle> pageInfo=new PageInfo<StCommunityArticle>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public Map<String, Object> findStCommunityTeach(PageBean pageBean,
			StCommunityTeach communityTeach) {
		PageHelper.startPage(pageBean.getPage(), pageBean.getPageSize());
		Map<String,Object> map=new HashMap<String, Object>();
		StCommunityTeachExample example=new StCommunityTeachExample();
		example.setOrderByClause("createtime desc");
		StCommunityTeachExample.Criteria criteria=example.createCriteria();
		criteria.andCommunityidEqualTo(communityTeach.getCommunityid());
		if(StringUtil.isNotEmpty(communityTeach.getTitle()))
			criteria.andTitleLike("%"+communityTeach.getTitle()+"%");
		List<StCommunityTeach> list = communityTeachMapper.selectByExample(example);
		PageInfo<StCommunityTeach> pageInfo=new PageInfo<StCommunityTeach>(list);
		long total=pageInfo.getTotal();
		map.put("total",total);
		map.put("list", list);
		return map;
	}

	@Override
	public int deleteCommunityArticle(Integer articleId) {
		return communityArticleMapper.deleteByPrimaryKey(articleId);
	}

	@Override
	public int deleteCommunityTeach(Integer teachId) {
		return communityTeachMapper.deleteByPrimaryKey(teachId);
	}

	
}
