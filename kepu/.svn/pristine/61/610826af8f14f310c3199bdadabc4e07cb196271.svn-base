package com.kepu.service.impl;



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
import com.kepu.mapper.StAuthenticCompanyMapper;
import com.kepu.mapper.StAuthenticPeopleMapper;
import com.kepu.mapper.StSkillClassifyMapper;
import com.kepu.mapper.StSkillContentMapper;
import com.kepu.mapper.StSkillandbuildingCommentMapper;
import com.kepu.mapper.StTaskContentMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StAuthenticCompany;
import com.kepu.pojo.StAuthenticCompanyExample;
import com.kepu.pojo.StAuthenticPeople;
import com.kepu.pojo.StAuthenticPeopleExample;
import com.kepu.pojo.StSkillClassify;
import com.kepu.pojo.StSkillClassifyExample;
import com.kepu.pojo.StSkillContent;
import com.kepu.pojo.StSkillContentExample;
import com.kepu.pojo.StSkillandbuildingCommentExample;
import com.kepu.pojo.StTaskContent;
import com.kepu.pojo.StTaskContentExample;
import com.kepu.service.SkillService;
import com.kepu.service.SysService;
import com.kepu.util.DateUtil;
import com.kepu.util.MapUtils;
import com.kepu.util.StringUtil;
import com.kepu.util.SystemSession;

@Service
public class SkillServiceImpl implements SkillService {

	@Autowired
	private StAuthenticCompanyMapper authenticCompanyMapper;
	@Autowired
	private StAuthenticPeopleMapper authenticPeopleMapper;
	@Autowired
	private StSkillClassifyMapper  skillClassifyMapper;
	@Autowired
	private StSkillContentMapper skillContentMapper;
	@Autowired
	private StTaskContentMapper  stTaskContentMapper;
	@Autowired
	private SysService sysService;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private StSkillandbuildingCommentMapper commonCommentMapper;
	@Override
	public KePuResult authPersonal(StAuthenticPeople sap) {
		StAuthenticPeopleExample example=new StAuthenticPeopleExample();
		example.setOrderByClause("createtime desc");
		StAuthenticPeopleExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(sap.getUserid());
		List<StAuthenticPeople> result = authenticPeopleMapper.selectByExample(example);
		if(result.size()==0){
			authenticPeopleMapper.insertSelective(sap);
			return KePuResult.ok(ResultConstant.code_ok, "申请成功,请等待管理员审核", "");
		}else{
			StAuthenticPeople temp=result.get(0);
			if(temp.getState()==0)
				return KePuResult.ok(ResultConstant.code_yewu, "请勿重复申请", "");
			else if(temp.getState()==1){
				return KePuResult.ok(ResultConstant.code_yewu, "申请正在处理中", "");
			}else if(temp.getState()==2){
				authenticPeopleMapper.insertSelective(sap);
				return KePuResult.ok(ResultConstant.code_ok, "申请成功,请等待管理员审核", "");
			}
			return KePuResult.ok(ResultConstant.code_yewu, "系统异常", "");
		}
		
	}

	@Override
	public KePuResult authCompany(StAuthenticCompany sap) {
		StAuthenticCompanyExample example=new StAuthenticCompanyExample();
		example.setOrderByClause("createtime desc");
		StAuthenticCompanyExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(sap.getUserid());
		List<StAuthenticCompany> result = authenticCompanyMapper.selectByExample(example);
		if(result.size()==0){
			authenticCompanyMapper.insertSelective(sap);
			return KePuResult.ok(ResultConstant.code_ok, "申请成功,请等待管理员审核", "");
		}else{
			StAuthenticCompany temp=result.get(0);
			if(temp.getState()==0)
				return KePuResult.ok(ResultConstant.code_yewu, "请勿重复申请", "");
			else if(temp.getState()==1){
				return KePuResult.ok(ResultConstant.code_yewu, "申请正在处理中", "");
			}else if(temp.getState()==2){
				authenticCompanyMapper.insertSelective(sap);
				return KePuResult.ok(ResultConstant.code_ok, "申请成功,请等待管理员审核", "");
			}
			return KePuResult.ok(ResultConstant.code_yewu, "系统异常", "");
		}
	}

	@Override
	public KePuResult getAuthStatus(Integer userId) {
		Map<String,String> data=new HashMap<String, String>();
		StAuthenticCompanyExample example=new StAuthenticCompanyExample();
		example.setOrderByClause("createtime desc");
		StAuthenticCompanyExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		//criteria.andStateEqualTo(0);
		List<StAuthenticCompany> result = authenticCompanyMapper.selectByExample(example);
		int cstate=0;
		if(result.size()!=0){
			int t=result.get(0).getState();
			if(t==1)  cstate=2;
			else if(t==0)  cstate=1;
		}
		StAuthenticPeopleExample example2=new StAuthenticPeopleExample();
		example2.setOrderByClause("createtime desc");
		StAuthenticPeopleExample.Criteria criteria2=example2.createCriteria();
		criteria2.andUseridEqualTo(userId);
		//criteria2.andStateEqualTo(0);
		List<StAuthenticPeople> result2 = authenticPeopleMapper.selectByExample(example2);
		int pstate=0;
		if(result2.size()!=0){
			int t=result2.get(0).getState();
			if(t==1)  pstate=2;
			else if(t==0)  pstate=1;
		}
		data.put("people", pstate+"");
		data.put("company", cstate+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", data);
	}

	@Override
	public KePuResult getClassify() {
		Map<String,Object> map=new HashMap<String, Object>();
		StSkillClassifyExample example=new StSkillClassifyExample();
		example.setOrderByClause("parent");
		StSkillClassifyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		List<StSkillClassify> result = skillClassifyMapper.selectByExample(example);
		List<Map<String,Object>> rs=new LinkedList<Map<String,Object>>();
		Map<String,Object> m;
		for (StSkillClassify stSkillClassify : result) {
			if(stSkillClassify.getParent()==-1){
				m=new HashMap<String, Object>();
				m.put("uid", stSkillClassify.getUid()+"");
				m.put("name", stSkillClassify.getShowname());
				LinkedList<Map<String,String>>  menu=new LinkedList<Map<String,String>>();
				Map<String,String> m1;
				for (StSkillClassify sc : result) {
					if(sc.getParent()==stSkillClassify.getUid()){
					m1=new HashMap<String, String>();
					m1.put("menuUid", sc.getUid()+"");
					m1.put("menuName", sc.getShowname());
					menu.add(m1);
				   }
				m.put("menu", menu);
				}
				rs.add(m);
			}
		}
		map.put("category", rs);
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public Boolean checkAuth(Integer userId, Integer type) {
		if(type==1){
			StAuthenticPeopleExample example2=new StAuthenticPeopleExample();
			example2.setOrderByClause("createtime desc");
			StAuthenticPeopleExample.Criteria criteria2=example2.createCriteria();
			criteria2.andUseridEqualTo(userId);
			criteria2.andStateEqualTo(0);
			List<StAuthenticPeople> result2 = authenticPeopleMapper.selectByExample(example2);
			return result2.size()==1;
		}else{
			StAuthenticCompanyExample example=new StAuthenticCompanyExample();
			example.setOrderByClause("createtime desc");
			StAuthenticCompanyExample.Criteria criteria=example.createCriteria();
			criteria.andUseridEqualTo(userId);
			criteria.andStateEqualTo(0);
			List<StAuthenticCompany> result = authenticCompanyMapper.selectByExample(example);
			return result.size()==1;
		}
	}

	@Override
	public KePuResult releaseSkill(StSkillContent skillContent) {
		int line=skillContentMapper.insertSelective(skillContent);
		Map<String,Object> map=new HashMap<String, Object>();
		if(line==1){
			map.put("skillId", skillContent.getUid()+"");
			return KePuResult.ok(ResultConstant.code_ok, "发布成功", map);
		}else{
			return KePuResult.ok(ResultConstant.code_yewu, "发布失败", "");
		}
	}

	@Override
	public KePuResult getCarousel(Integer type) {
		String value=sysService.getParam(type==1?"carousel_skill":"carousel_task");
		value=StringUtil.isEmpty(value)?"":value;
		String str[]=value.split(",");
		int size=StringUtil.isEmpty(value)?0:str.length;
		LinkedList<Integer> list=new LinkedList<Integer>();
		if(size>0){
			for (int i = 0; i < size; i++) {
				list.add(Integer.valueOf(str[i]));
			}
		}
		List<Map<String,String>> r=new LinkedList<Map<String,String>>();
		Map<String,String> temp; 
		String appVersion=SystemSession.get().getAppVersion();
		String application=SystemSession.get().getApplication();
		String uid="uid";
		if(StringUtil.isNotEmpty(application)&&StringUtil.isNotEmpty(appVersion))
			if("ANDROID".equalsIgnoreCase(application) && appVersion.compareTo("6.1.0")<0){
				uid = "productId";
			}
		int isNew=1;
		if(StringUtil.isNotEmpty(appVersion)&&appVersion.compareTo("6.2.0")<0)
			isNew=0;
		if(type==1){
			if(size>0){
				StSkillContentExample example=new StSkillContentExample();
				example.setOrderByClause(" createtime  desc");
				StSkillContentExample.Criteria criteria=example.createCriteria();
				criteria.andUidIn(list);
				List<StSkillContent> result = skillContentMapper.selectByExample(example);
				for (StSkillContent stSkillContent : result) {
					temp=new HashMap<String, String>();
					temp.put(uid, stSkillContent.getUid()+"");
					temp.put("pic", stSkillContent.getCoverpic());
					temp.put("title", stSkillContent.getTitle());
					if(isNew==1)
						temp.put("type", "1");
					r.add(temp);
				}
			}
		}else{
			if(size>0){
				StTaskContentExample example=new StTaskContentExample();
				StTaskContentExample.Criteria criteria=example.createCriteria();
				criteria.andUidIn(list);
				List<StTaskContent> result = stTaskContentMapper.selectByExample(example);
				for (StTaskContent taskContent : result) {
					temp=new HashMap<String, String>();
					temp.put(uid, taskContent.getUid()+"");
					temp.put("pic", taskContent.getCoverpic());
					temp.put("title", taskContent.getTitle());
					if(isNew==1)
						temp.put("type", "1");
					r.add(temp);
				}
			}
		}
		if(isNew==1){
			List<Map<String, String>> linkList = sysService.getLinkMapByType(type==1?6:7);
			for (Map<String, String> map : linkList) {
				r.add(map);
			}
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", r);
	}

	@Override
	public KePuResult getskillDetail(Integer skillId) {
		Map<String,String> map=new HashMap<String, String>();
		StSkillContent content = skillContentMapper.selectByPrimaryKey(skillId);
		if(content==null||content.getState()==1){
			return KePuResult.ok(ResultConstant.code_yewu, "已被删除或不存在", map);
		}
		map.put("title", content.getTitle());
		map.put("price", content.getPrice()+"");
		map.put("createTime",DateUtil.formatDate(content.getCreatetime(), MyConstant.updatetime));
		map.put("address", content.getAddress());
		map.put("avatar", content.getAvatar());
		map.put("realName", content.getRealname());
		map.put("mobile", content.getMobile());
		map.put("introduce", content.getIntroduce());
		map.put("detailPics", content.getDetailpics());
		map.put("userId", content.getUserid()+"");
		map.put("authorityType", content.getAuthoritytype()+"");
		map.put("classifyName", content.getClassifyname());
		map.put("skillId", content.getUid()+"");
		map.put("coverPic", content.getCoverpic());
		// 评论数
		String v=jedisClient.get("commom_commentNum_"+content.getUid()+"_type"+"_"+1);
		int num=0;
		if(StringUtil.isEmpty(v)){
			StSkillandbuildingCommentExample ske=new StSkillandbuildingCommentExample();
			StSkillandbuildingCommentExample.Criteria sc=ske.createCriteria();
			sc.andTypeEqualTo(1);
			sc.andTypeidEqualTo(content.getUid());
		    num=commonCommentMapper.countByExample(ske);
			jedisClient.set("commom_commentNum_"+content.getUid()+"_type"+"_"+1,num+"");
		}else{
			num=Integer.valueOf(v);
		}
		map.put("commentCount", num+"");
		map.put("coverPic", content.getCoverpic());
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult getSkillList(Integer page, Integer size,
			Map<String, String> conditions) {
		Map<String,Object> map=new HashMap<String, Object>();
		StSkillContentExample example=new StSkillContentExample();
		/*排序部分*/
		StringBuffer orderString=new StringBuffer();
		if(conditions.containsKey("all")){
			int v=Integer.valueOf(conditions.get("all"));
			if(v==0)
				orderString.append("price").append(",");
			if(v==1)
				orderString.append("price  desc").append(",");
		}
		/*end*/
		if(orderString.length()!=0){
			orderString.deleteCharAt(orderString.length() - 1);
			example.setOrderByClause(orderString.toString());
		}else{
			example.setOrderByClause("createtime desc");
		}
		StSkillContentExample.Criteria criteria=example.createCriteria();
		if(conditions.containsKey("query")){
			criteria.andTitleLike("%"+(String)conditions.get("query")+"%");
		}
		criteria.andStateEqualTo(0);
		if(conditions.containsKey("type")){
			int v=Integer.valueOf(conditions.get("type"));
			criteria.andAuthoritytypeEqualTo(v);
		}
		if(conditions.containsKey("classify")){
			String v=String.valueOf(conditions.get("classify"));
			criteria.andClassifynameLike("%"+v+"%");
		}
		PageHelper.startPage(page, size);
		List<StSkillContent> myList = null;
		if(!conditions.containsKey("distance"))
			myList=skillContentMapper.selectByExample(example);
		else{
			// 按照距离排序
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("lat", Double.valueOf((String)conditions.get("lat")));
			param.put("lon", Double.valueOf((String)conditions.get("lon")));
			int distance=Integer.valueOf((String)conditions.get("distance"));
			param.put("orderByClause", distance==0?"asc":"desc");
			if(conditions.containsKey("query")){
				String v=String.valueOf(conditions.get("query"));
				param.put("query", "%"+v+"%");
			}
			if(conditions.containsKey("classify")){
				String v2=String.valueOf(conditions.get("classify"));
				param.put("classify", "%"+v2+"%");
			}
			myList=skillContentMapper.selectOrderByDistance(param);
		}
		List<Map<String,String>> skillList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StSkillContent skill : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", skill.getTitle());
			temp.put("createTime",DateUtil.formatDate(skill.getCreatetime(), MyConstant.updatetime));
			temp.put("price", skill.getPrice()+"");
			temp.put("uid", skill.getUid()+"");
			temp.put("coverPic", skill.getCoverpic());
			temp.put("realName", skill.getRealname());
			temp.put("classifyName", skill.getClassifyname());
			// 计算距离
			double lat1=Double.valueOf((String)conditions.get("lat"));
			double lon1=Double.valueOf((String)conditions.get("lon"));
			double lat2=skill.getLat();
			double lon2=skill.getLon();
			double distance=MapUtils.GetDistance(lat1, lon1, lat2, lon2);
			temp.put("distance",(int)distance/1000+"" );
			skillList.add(temp);
		}
		PageInfo<StSkillContent> pageInfo=new PageInfo<StSkillContent>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("skillList", skillList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public StSkillContent getSkillContentById(Integer skillId) {
		return skillContentMapper.selectByPrimaryKey(skillId);
	}

	@Override
	public int saveSkillContent(StSkillContent skillContent) {
		return skillContentMapper.updateByPrimaryKey(skillContent);
	}

	@Override
	public KePuResult releaseTask(StTaskContent stTaskContent) {
		int line=stTaskContentMapper.insertSelective(stTaskContent);
		Map<String,Object> map=new HashMap<String, Object>();
		if(line==1){
			map.put("taskId", stTaskContent.getUid()+"");
			return KePuResult.ok(ResultConstant.code_ok, "发布成功", map);
		}else{
			return KePuResult.ok(ResultConstant.code_yewu, "发布失败", "");
		}
	}

	@Override
	public KePuResult getTaskList(Integer page, Integer size,
			Map<String, String> conditions) {
		Map<String,Object> map=new HashMap<String, Object>();
		StTaskContentExample example=new StTaskContentExample();
		/*排序部分*/
		StringBuffer orderString=new StringBuffer();
		if(conditions.containsKey("all")){
			int v=Integer.valueOf(conditions.get("all"));
			if(v==0)
				orderString.append("price").append(",");
			if(v==1)
				orderString.append("price  desc").append(",");
		}
		/*end*/
		if(orderString.length()!=0){
			orderString.deleteCharAt(orderString.length() - 1);
			example.setOrderByClause(orderString.toString());
		}else{
			example.setOrderByClause("createtime desc");
		}
		StTaskContentExample.Criteria criteria=example.createCriteria();
		if(conditions.containsKey("query")){
			criteria.andTitleLike("%"+(String)conditions.get("query")+"%");
		}
		criteria.andStateEqualTo(0);
		if(conditions.containsKey("type")){
			int v=Integer.valueOf(conditions.get("type"));
			criteria.andAuthoritytypeEqualTo(v);
		}
		if(conditions.containsKey("classify")){
			String v=String.valueOf(conditions.get("classify"));
			criteria.andClassifynameLike("%"+v+"%");
		}
		PageHelper.startPage(page, size);
		List<StTaskContent> myList = null;
		if(!conditions.containsKey("distance"))
			myList=stTaskContentMapper.selectByExample(example);
		else{
			// 按照距离排序
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("lat", Double.valueOf((String)conditions.get("lat")));
			param.put("lon", Double.valueOf((String)conditions.get("lon")));
			int distance=Integer.valueOf((String)conditions.get("distance"));
			param.put("orderByClause", distance==0?"asc":"desc");
			if(conditions.containsKey("query")){
				String v=String.valueOf(conditions.get("query"));
				param.put("query", "%"+v+"%");
			}
			if(conditions.containsKey("classify")){
				String v2=String.valueOf(conditions.get("classify"));
				param.put("classify", "%"+v2+"%");
			}
			myList=stTaskContentMapper.selectOrderByDistance(param);
		}
		List<Map<String,String>> taskList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StTaskContent skill : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", skill.getTitle());
			temp.put("createTime",DateUtil.formatDate(skill.getCreatetime(), MyConstant.updatetime));
			temp.put("price", skill.getPrice()+"");
			temp.put("uid", skill.getUid()+"");
			temp.put("coverPic", skill.getCoverpic());
			temp.put("realName", skill.getRealname());
			temp.put("classifyName", skill.getClassifyname());
			temp.put("startTime", DateUtil.formatDate(skill.getStarttime(), "yyyy-MM-dd"));
			// 计算距离
			double lat1=Double.valueOf((String)conditions.get("lat"));
			double lon1=Double.valueOf((String)conditions.get("lon"));
			double lat2=skill.getLat();
			double lon2=skill.getLon();
			double distance=MapUtils.GetDistance(lat1, lon1, lat2, lon2);
			temp.put("distance",(int)distance/1000+"" );
			taskList.add(temp);
		}
		PageInfo<StTaskContent> pageInfo=new PageInfo<StTaskContent>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("taskList", taskList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult getTaskDetail(Integer taskId) {
		Map<String,String> map=new HashMap<String, String>();
		StTaskContent content = stTaskContentMapper.selectByPrimaryKey(taskId);
		if(content==null||content.getState()==1){
			return KePuResult.ok(ResultConstant.code_yewu, "已被删除或不存在", map);
		}
		map.put("title", content.getTitle());
		map.put("price", content.getPrice()+"");
		map.put("createTime",DateUtil.formatDate(content.getCreatetime(), MyConstant.updatetime));
		map.put("address", content.getAddress());
		map.put("avatar", content.getAvatar());
		map.put("realName", content.getRealname());
		map.put("mobile", content.getMobile());
		map.put("introduce", content.getIntroduce());
		map.put("detailPics", content.getDetailpics());
		map.put("userId", content.getUserid()+"");
		map.put("authorityType", content.getAuthoritytype()+"");
		map.put("classifyName", content.getClassifyname());
		map.put("taskId", content.getUid()+"");
		map.put("coverPic", content.getCoverpic());
		map.put("startTime",DateUtil.formatDate(content.getStarttime(), "yyyy-MM-dd"));
		// 评论数
		String v=jedisClient.get("commom_commentNum_"+content.getUid()+"_type"+"_"+2);
		int num=0;
		if(StringUtil.isEmpty(v)){
			StSkillandbuildingCommentExample ske=new StSkillandbuildingCommentExample();
			StSkillandbuildingCommentExample.Criteria sc=ske.createCriteria();
			sc.andTypeEqualTo(2);
			sc.andTypeidEqualTo(content.getUid());
		    num=commonCommentMapper.countByExample(ske);
			jedisClient.set("commom_commentNum_"+content.getUid()+"_type"+"_"+2,num+"");
		}else{
			num=Integer.valueOf(v);
		}
		map.put("commentCount", num+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public StTaskContent getTaskContentById(Integer taskId) {
		return stTaskContentMapper.selectByPrimaryKey(taskId);
	}

	@Override
	public int saveTaskContent(StTaskContent stTaskContent) {
		return stTaskContentMapper.updateByPrimaryKey(stTaskContent);
	}

	@Override
	public KePuResult getMySkill(Integer userId, Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StSkillContentExample example=new StSkillContentExample();
		example.setOrderByClause("createtime desc");
		StSkillContentExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		List<Map<String,String>> skillList=new LinkedList<Map<String,String>>();
		List<StSkillContent> myList = skillContentMapper.selectByExample(example);
		Map<String,String> temp;
		for (StSkillContent skill : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", skill.getTitle());
			temp.put("createTime",DateUtil.formatDate(skill.getCreatetime(), MyConstant.updatetime));
			temp.put("price", skill.getPrice()+"");
			temp.put("uid", skill.getUid()+"");
			temp.put("coverPic", skill.getCoverpic());
			temp.put("realName", skill.getRealname());
			temp.put("classifyName", skill.getClassifyname());
			skillList.add(temp);
		}
		PageInfo<StSkillContent> pageInfo=new PageInfo<StSkillContent>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("skillList", skillList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult getMyTask(Integer userId, Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StTaskContentExample example=new StTaskContentExample();
		example.setOrderByClause("createtime desc");
		StTaskContentExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		PageHelper.startPage(page, size);
		List<StTaskContent> myList=stTaskContentMapper.selectByExample(example);
		List<Map<String,String>> taskList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StTaskContent skill : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", skill.getTitle());
			temp.put("createTime",DateUtil.formatDate(skill.getCreatetime(), MyConstant.updatetime));
			temp.put("price", skill.getPrice()+"");
			temp.put("uid", skill.getUid()+"");
			temp.put("coverPic", skill.getCoverpic());
			temp.put("realName", skill.getRealname());
			temp.put("classifyName", skill.getClassifyname());
			temp.put("startTime", DateUtil.formatDate(skill.getStarttime(), "yyyy-MM-dd"));
			taskList.add(temp);
		}
		PageInfo<StTaskContent> pageInfo=new PageInfo<StTaskContent>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("taskList", taskList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	
	

	

}
