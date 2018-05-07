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
import com.kepu.mapper.StJobApplyMapper;
import com.kepu.mapper.StJobMapper;
import com.kepu.mapper.StPositionClassifyMapper;
import com.kepu.mapper.StVillageMapper;
import com.kepu.pojo.DeviceMessage;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StAuthenticCompany;
import com.kepu.pojo.StAuthenticCompanyExample;
import com.kepu.pojo.StJob;
import com.kepu.pojo.StJobApply;
import com.kepu.pojo.StJobApplyExample;
import com.kepu.pojo.StJobExample;
import com.kepu.pojo.StPositionClassify;
import com.kepu.pojo.StPositionClassifyExample;
import com.kepu.pojo.StVillage;
import com.kepu.service.JobService;
import com.kepu.service.SysService;
import com.kepu.service.VillageService;
import com.kepu.util.DateUtil;
import com.kepu.util.StringUtil;
import com.kepu.util.SystemSession;

@Service
public class JobServiceImpl implements JobService {

	@Autowired
	private SysService sysService;
	@Autowired
	private StPositionClassifyMapper positionClassifyMapper;
	@Autowired
	private StJobMapper jobMapper;
	@Autowired
	private StJobApplyMapper StJobApplyMapper ;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private StAuthenticCompanyMapper authenticCompanyMapper;
	@Autowired
	private VillageService villageService;
	@Autowired
	private StVillageMapper stVillageMapper;
	@Override
	public KePuResult getClassify() {
		Map<String,Object> map=new HashMap<String, Object>();
		StPositionClassifyExample example=new StPositionClassifyExample();
		example.setOrderByClause("parent");
		StPositionClassifyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		List<StPositionClassify> result = positionClassifyMapper.selectByExample(example);
		List<Map<String,Object>> rs=new LinkedList<Map<String,Object>>();
		Map<String,Object> m;
		for (StPositionClassify positionClassify : result) {
			if(positionClassify.getParent()==-1){
				m=new HashMap<String, Object>();
				m.put("uid", positionClassify.getUid()+"");
				m.put("name", positionClassify.getShowname());
				LinkedList<Map<String,String>>  menu=new LinkedList<Map<String,String>>();
				Map<String,String> m1;
				for (StPositionClassify sc : result) {
					if(sc.getParent().intValue()==positionClassify.getUid().intValue()){
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
	public KePuResult releaseJob(StJob job) {
		int line=jobMapper.insertSelective(job);
		Map<String,Object> map=new HashMap<String, Object>();
		if(line==1){
			map.put("jobId", job.getUid()+"");
			return KePuResult.ok(ResultConstant.code_ok, "发布成功", map);
		}else{
			return KePuResult.ok(ResultConstant.code_yewu, "发布失败", "");
		}
	}

	

	@Override
	public KePuResult getJobDetail(Integer jobId) {
		Map<String,String> map=new HashMap<String, String>();
		StJob job = jobMapper.selectByPrimaryKey(jobId);
		if(job==null||job.getState()==1){
			return KePuResult.ok(ResultConstant.code_yewu, "已被删除或不存在", map);
		}
		map.put("title", job.getJobname());
		map.put("createTime",DateUtil.formatDate(job.getCreatetime(), MyConstant.updatetime));
		map.put("address", job.getAddress());
		map.put("lowPrice", job.getLowprice()+"");
		map.put("highPrice", job.getHighprice()+"");
		map.put("education",MyConstant.edu[job.getEducation()-1]);
		map.put("workExperience",job.getWorkexperience());
		map.put("require",job.getJobrequire()); 
		map.put("introduce",job.getDescription());
		map.put("mobile", job.getMobile());
		map.put("jobId", job.getUid()+"");
		map.put("detailPics", job.getDetailpics());
		map.put("coverPic", job.getCoverpic());
		map.put("position", job.getPosition());
		map.put("realName", job.getRealname());
		map.put("company", getAuthCompanyName(job.getUserid()));
		DeviceMessage device = SystemSession.get();
		String application = device.getApplication();
		if(application.equalsIgnoreCase("ANDROID")){
			// 安卓给areaName
			if(device!=null&&device.getAppVersion().compareTo("6.2.0")>=0){
				StVillage v = stVillageMapper.selectByPrimaryKey(job.getCounty());
				map.put("area", v.getName());
				map.put("areaCode", job.getCounty()+"");
			}else{
				map.put("area", job.getCounty()+"");
			}
		}else{
			map.put("area", job.getCounty()+"");
		}
		map.put("userId", job.getUserid()+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}
	
	@Override
	public String  getAuthCompanyName(Integer userId){
		StAuthenticCompanyExample example=new StAuthenticCompanyExample();
		example.setOrderByClause("createtime desc");
		StAuthenticCompanyExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		criteria.andStateEqualTo(0);
		List<StAuthenticCompany> result = authenticCompanyMapper.selectByExample(example);
		if(result.size()!=0){
			return result.get(0).getCompanyname();
		}
		return "";
	}
	
	@Override
	public KePuResult getJobList(Integer page, Integer size,
			Map<String, String> conditions) {
		Map<String,Object> map=new HashMap<String, Object>();
		StJobExample example=new StJobExample();
		example.setOrderByClause("createtime desc");
		StJobExample.Criteria criteria=example.createCriteria();
		if(conditions.containsKey("area")){
			Integer v=Integer.valueOf(conditions.get("area"));
			if(v<50)
				criteria.andTownEqualTo(v);
			else
				criteria.andCountyEqualTo(v);
		}
		if(conditions.containsKey("position")){
			String position=conditions.get("position");
			criteria.andJobnameLike("%"+position+"%");
		}
		if(conditions.containsKey("education")){
			Integer education=Integer.valueOf(conditions.get("education"));
			criteria.andEducationEqualTo(education);
		}
		if(conditions.containsKey("query")){
			criteria.andJobnameLike("%"+conditions.get("query")+"%");
		}
		if(conditions.containsKey("startPrice")){
			Integer v=Integer.valueOf(conditions.get("startPrice"));
			criteria.andLowpriceGreaterThanOrEqualTo(v);
		}
		if(conditions.containsKey("endPrice")){
			Integer v=Integer.valueOf(conditions.get("endPrice"));
			criteria.andHighpriceLessThanOrEqualTo(v);
		}
		if(conditions.containsKey("userId")){
			Integer v=Integer.valueOf(conditions.get("userId"));
			criteria.andUseridEqualTo(v);
		}
		criteria.andStateEqualTo(0);
		PageHelper.startPage(page, size);
		List<StJob> myList = jobMapper.selectByExample(example);
		List<Map<String,String>> jobList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StJob job : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", job.getJobname());
			temp.put("createTime",DateUtil.formatDate(job.getCreatetime(), MyConstant.updatetime));
			temp.put("education",MyConstant.edu[job.getEducation()-1]);
			temp.put("lowPrice", job.getLowprice()+"");
			temp.put("highPrice", job.getHighprice()+"");
			temp.put("company", getAuthCompanyName(job.getUserid()));
			temp.put("address", job.getAddress());
			temp.put("jobId", job.getUid()+"");
			temp.put("mobile", job.getMobile());
			temp.put("coverPic", job.getCoverpic());
			jobList.add(temp);
		}
		PageInfo<StJob> pageInfo=new PageInfo<StJob>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("jobList", jobList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public StJob getJobById(Integer jobId) {
		return jobMapper.selectByPrimaryKey(jobId);
	}

	@Override
	public int saveJob(StJob job) {
		return jobMapper.updateByPrimaryKeySelective(job);
	}

	@Override
	public KePuResult releaseApply(StJobApply apply) {
		int line=StJobApplyMapper.insertSelective(apply);
		Map<String,Object> map=new HashMap<String, Object>();
		if(line==1){
			map.put("applyId", apply.getUid()+"");
			return KePuResult.ok(ResultConstant.code_ok, "发布成功", map);
		}else{
			return KePuResult.ok(ResultConstant.code_yewu, "发布失败", "");
		}
	}

	@Override
	public KePuResult getApplyList(Integer page, Integer size,
			Map<String, String> conditions) {
		Map<String,Object> map=new HashMap<String, Object>();
		StJobApplyExample example=new StJobApplyExample();
		example.setOrderByClause("createtime desc");
		StJobApplyExample.Criteria criteria=example.createCriteria();
		if(conditions.containsKey("area")){
			Integer v=Integer.valueOf(conditions.get("area"));
			if(v<50)
				criteria.andTownEqualTo(v);
			else
				criteria.andCountyEqualTo(v);
		}
		if(conditions.containsKey("position")){
			String position=conditions.get("position");
			criteria.andPositionLike("%"+position+"%");
		}
		if(conditions.containsKey("industry")){
			String industry=conditions.get("industry");
			criteria.andHyLike("%"+industry+"%");
		}
		if(conditions.containsKey("query")){
			criteria.andPositionLike("%"+conditions.get("query")+"%");
		}
		if(conditions.containsKey("startPrice")){
			Integer v=Integer.valueOf(conditions.get("startPrice"));
			criteria.andLowpriceGreaterThanOrEqualTo(v);
		}
		if(conditions.containsKey("endPrice")){
			Integer v=Integer.valueOf(conditions.get("endPrice"));
			criteria.andHighpriceLessThanOrEqualTo(v);
		}
		if(conditions.containsKey("userId")){
			Integer v=Integer.valueOf(conditions.get("userId"));
			criteria.andUseridEqualTo(v);
		}
		criteria.andStateEqualTo(0);
		PageHelper.startPage(page, size);
		List<StJobApply> myList = StJobApplyMapper.selectByExample(example);
		List<Map<String,String>> jobList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StJobApply job : myList) {
			temp=new HashMap<String, String>();
			temp.put("position", job.getPosition());
			temp.put("createTime",DateUtil.formatDate(job.getCreatetime(), MyConstant.updatetime));
			temp.put("industry", job.getHy());
			temp.put("lowPrice", job.getLowprice()+"");
			temp.put("highPrice", job.getHighprice()+"");
			temp.put("realName", job.getRealname());
			temp.put("address", job.getAddress());
			temp.put("applyId", job.getUid()+"");
			temp.put("mobile", job.getMobile());
			temp.put("title", job.getTitle());
			jobList.add(temp);
		}
		PageInfo<StJobApply> pageInfo=new PageInfo<StJobApply>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("jobApplyList", jobList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	
	@Override
	public KePuResult getApplyDetail(Integer applyId) {
		Map<String,String> map=new HashMap<String, String>();
		StJobApply job= StJobApplyMapper.selectByPrimaryKey(applyId);
		if(job==null||job.getState()==1){
			return KePuResult.ok(ResultConstant.code_yewu, "已被删除或不存在", map);
		}
		map.put("position", job.getPosition());
		map.put("createTime",DateUtil.formatDate(job.getCreatetime(), MyConstant.updatetime));
		map.put("address", job.getAddress());
	    int area=job.getCounty();
	    //String name = villageService.getVillageName(area);
	    StVillage v1 = stVillageMapper.selectByPrimaryKey(area);
		map.put("area", v1.getName());
		DeviceMessage device = SystemSession.get();
		String application = device.getApplication();
		if(application.equalsIgnoreCase("ANDROID")){
			// 安卓给areaName
			if(device!=null&&device.getAppVersion().compareTo("6.2.0")>=0){
				map.put("areaCode", job.getCounty()+"");
			}
		}
		map.put("lowPrice", job.getLowprice().intValue()==-1?"0":job.getLowprice()+"");
		map.put("highPrice", job.getHighprice().intValue()==-1?"面议":job.getHighprice()+"");
		map.put("industry", job.getHy());
		map.put("realName", job.getRealname());
		map.put("applyId", job.getUid()+"");
		map.put("mobile", job.getMobile());
		map.put("title", job.getTitle());
		map.put("introduce", job.getDescription());
		map.put("userId", job.getUserid()+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public StJobApply getApplyById(Integer applyId) {
		return StJobApplyMapper.selectByPrimaryKey(applyId);
	}

	@Override
	public int saveApply(StJobApply apply) {
		return StJobApplyMapper.updateByPrimaryKeySelective(apply);
	}

	@Override
	public KePuResult getCarousel() {
		String value=sysService.getParam("carousel_job");
		value=StringUtil.isEmpty(value)?"":value;
		/*if(StringUtil.isEmpty(value)){
			return KePuResult.ok(ResultConstant.code_yewu, "获取失败", null);
		}*/
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
		String version=SystemSession.get().getAppVersion();
		int isNew=1;
		if(StringUtil.isNotEmpty(version)&&version.compareTo("6.2.0")<0)
			isNew=0;
		if(size>0){
			StJobExample example=new StJobExample();
			example.setOrderByClause(" createtime  desc");
			StJobExample.Criteria criteria=example.createCriteria();
			criteria.andUidIn(list);
			List<StJob> result = jobMapper.selectByExample(example);
			for (StJob job : result) {
				temp=new HashMap<String, String>();
				temp.put("uid", job.getUid()+"");
				temp.put("pic", job.getCoverpic());
				temp.put("title", job.getJobname());
				if(isNew==1)
					temp.put("type", "1");
				r.add(temp);
			}
		}
		if(isNew==1){
			List<Map<String, String>> linkList = sysService.getLinkMapByType(8);
			for (Map<String, String> map : linkList) {
				r.add(map);
			}
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", r);
	}

	
}
