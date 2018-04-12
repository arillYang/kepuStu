package com.kepu.service.impl;



import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.constant.MyConstant;
import com.kepu.constant.ResultConstant;
import com.kepu.dao.JedisClient;
import com.kepu.mapper.StBuildingClassifyMapper;
import com.kepu.mapper.StBuildingrentContentMapper;
import com.kepu.mapper.StBuildingsellContentMapper;
import com.kepu.mapper.StSkillandbuildingCommentMapper;
import com.kepu.pojo.DeviceMessage;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StBuildingClassify;
import com.kepu.pojo.StBuildingClassifyExample;
import com.kepu.pojo.StBuildingrentContent;
import com.kepu.pojo.StBuildingrentContentExample;
import com.kepu.pojo.StBuildingsellContent;
import com.kepu.pojo.StBuildingsellContentExample;
import com.kepu.pojo.StSkillandbuildingCommentExample;
import com.kepu.service.BuildService;
import com.kepu.service.SysService;
import com.kepu.util.DateUtil;
import com.kepu.util.StringUtil;
import com.kepu.util.SystemSession;

@Service
public class BuildServiceImpl implements BuildService {

	@Autowired
	private SysService sysService;
	@Autowired
	private StBuildingClassifyMapper buildingClassifyMapper;
	@Autowired
	private StBuildingsellContentMapper buildingsellContentMapper;
	@Autowired
	private StBuildingrentContentMapper buildingrentContentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private StSkillandbuildingCommentMapper commonCommentMapper;
	@Override
	public KePuResult getClassify() {
		Map<String,Object> map=new HashMap<String, Object>();
		StBuildingClassifyExample example=new StBuildingClassifyExample();
		example.setOrderByClause("parent");
		StBuildingClassifyExample.Criteria criteria=example.createCriteria();
		criteria.andStateEqualTo(0);
		List<StBuildingClassify> result = buildingClassifyMapper.selectByExample(example);
		List<Map<String,Object>> rs=new LinkedList<Map<String,Object>>();
		Map<String,Object> m;
		for (StBuildingClassify stBuildingClassify : result) {
			if(stBuildingClassify.getParent()==-1){
				m=new HashMap<String, Object>();
				m.put("uid", stBuildingClassify.getUid()+"");
				m.put("name", stBuildingClassify.getShowname());
				LinkedList<Map<String,String>>  menu=new LinkedList<Map<String,String>>();
				Map<String,String> m1;
				for (StBuildingClassify sc : result) {
					if(sc.getParent()==stBuildingClassify.getUid()){
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
	public KePuResult releaseSell(StBuildingsellContent buildingsellContent) {
		int line=buildingsellContentMapper.insertSelective(buildingsellContent);
		Map<String,Object> map=new HashMap<String, Object>();
		if(line==1){
			map.put("buildingId", buildingsellContent.getUid()+"");
			return KePuResult.ok(ResultConstant.code_ok, "发布成功", map);
		}else{
			return KePuResult.ok(ResultConstant.code_yewu, "发布失败", "");
		}
	}

	@Override
	public KePuResult getCarousel() {
		String value=sysService.getParam("carousel_buildSell");
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
		String version=SystemSession.get().getAppVersion();
		int isNew=1;
		if(StringUtil.isNotEmpty(version)&&version.compareTo("6.2.0")<0)
			isNew=0;
		if(size>0){
			StBuildingsellContentExample example=new StBuildingsellContentExample();
			example.setOrderByClause(" createtime  desc");
			StBuildingsellContentExample.Criteria criteria=example.createCriteria();
			criteria.andUidIn(list);
			List<StBuildingsellContent> result = buildingsellContentMapper.selectByExample(example);
			for (StBuildingsellContent content : result) {
				temp=new HashMap<String, String>();
				temp.put("uid", content.getUid()+"");
				temp.put("pic", content.getCoverpic());
				temp.put("title", content.getTitle());
				r.add(temp);
				if(isNew==1)
					temp.put("type", "1");
			}
		}
		if(isNew==1){
			List<Map<String, String>> linkList = sysService.getLinkMapByType(9);
			for (Map<String, String> map : linkList) {
				r.add(map);
			}
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", r);
	}

	@Override
	public KePuResult getSellDetail(Integer buildingId) {
		Map<String,String> map=new HashMap<String, String>();
		StBuildingsellContent content = buildingsellContentMapper.selectByPrimaryKey(buildingId);
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
		map.put("houseType", content.getHousetype()+"");
		map.put("classifyName", content.getClassifyname());
		map.put("buildingId", content.getUid()+"");
		map.put("coverPic", content.getCoverpic());
		map.put("size", content.getSize()+"");
		map.put("decorate", content.getDecorate()+"");
		map.put("sellType", content.getSelltype()+""); 
		map.put("config", content.getConfig()+""); 
		map.put("trade", content.getTrade()); 
		// 评论数
		String v=jedisClient.get("commom_commentNum_"+content.getUid()+"_type"+"_"+3);
		int num=0;
		if(StringUtil.isEmpty(v)){
			StSkillandbuildingCommentExample ske=new StSkillandbuildingCommentExample();
			StSkillandbuildingCommentExample.Criteria sc=ske.createCriteria();
			sc.andTypeEqualTo(3);
			sc.andTypeidEqualTo(content.getUid());
		    num=commonCommentMapper.countByExample(ske);
			jedisClient.set("commom_commentNum_"+content.getUid()+"_type"+"_"+3,num+"");
		}else{
			num=Integer.valueOf(v);
		}
		map.put("commentCount", num+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult getSellList(Integer page, Integer size,
			Map<String, String> conditions) {
		Map<String,Object> map=new HashMap<String, Object>();
		StBuildingsellContentExample example=new StBuildingsellContentExample();
		StringBuffer orderString=new StringBuffer();
		if(conditions.containsKey("square")){
			Integer v=Integer.valueOf(conditions.get("square"));
			if(v==0)
				orderString.append("size asc").append(",");
			else
				orderString.append("size desc").append(",");
		}
		if(conditions.containsKey("price")){
			Integer v=Integer.valueOf(conditions.get("price"));
			if(v==0)
				orderString.append("price asc").append(",");
			else
				orderString.append("price desc").append(",");
		}
		if(orderString.length()!=0){
			orderString.deleteCharAt(orderString.length() - 1);
			example.setOrderByClause(orderString.toString());
		}else{
			example.setOrderByClause("createtime desc");
		}
		StBuildingsellContentExample.Criteria criteria=example.createCriteria();
		if(conditions.containsKey("query")){
			criteria.andTitleLike("%"+(String)conditions.get("query")+"%");
		}
		criteria.andStateEqualTo(0);
		/*if(conditions.containsKey("startArea")){
			Integer v=Integer.valueOf(conditions.get("startArea"));
			criteria.andSizeGreaterThanOrEqualTo(v);
		}
		if(conditions.containsKey("endArea")){
			Integer v=Integer.valueOf(conditions.get("endArea"));
			criteria.andSizeLessThanOrEqualTo(v);
		}
		if(conditions.containsKey("startPrice")){
			Double v=Double.valueOf(conditions.get("startPrice"));
			criteria.andPriceGreaterThanOrEqualTo(new BigDecimal(v));
		}
		if(conditions.containsKey("endPrice")){
			Double v=Double.valueOf(conditions.get("endPrice"));
			criteria.andPriceLessThanOrEqualTo(new BigDecimal(v));
		}*/
		if(conditions.containsKey("classify")){
			String v=String.valueOf(conditions.get("classify"));
			criteria.andClassifynameLike("%"+v+"%");
		}
		if(conditions.containsKey("trade")){
			String v=String.valueOf(conditions.get("trade"));
			criteria.andTradeEqualTo(v);
		}
		PageHelper.startPage(page, size);
		List<StBuildingsellContent> myList = buildingsellContentMapper.selectByExample(example);
		List<Map<String,String>> sellList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StBuildingsellContent content : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", content.getTitle());
			temp.put("createTime",DateUtil.formatDate(content.getCreatetime(), MyConstant.updatetime));
			temp.put("price", content.getPrice()+"");
			temp.put("uid", content.getUid()+"");
			temp.put("coverPic", content.getCoverpic());
			temp.put("classifyName", content.getClassifyname());
			temp.put("trade", content.getTrade());
			temp.put("size", content.getSize()+""); 
			temp.put("houseType", content.getHousetype()+"");
			temp.put("address", content.getAddress());
			sellList.add(temp);
		}
		PageInfo<StBuildingsellContent> pageInfo=new PageInfo<StBuildingsellContent>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("buildingList", sellList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public StBuildingsellContent getSellContentById(Integer sellId) {
		return buildingsellContentMapper.selectByPrimaryKey(sellId);
	}

	@Override
	public int saveSellContent(StBuildingsellContent buildingsellContent) {
		return buildingsellContentMapper.updateByPrimaryKeySelective(buildingsellContent);
	}

	@Override
	public KePuResult releaseRent(StBuildingrentContent buildingrentContent) {
		int line=buildingrentContentMapper.insertSelective(buildingrentContent);
		Map<String,Object> map=new HashMap<String, Object>();
		if(line==1){
			map.put("rentId", buildingrentContent.getUid()+"");
			return KePuResult.ok(ResultConstant.code_ok, "发布成功", map);
		}else{
			return KePuResult.ok(ResultConstant.code_yewu, "发布失败", "");
		}
	}

	@Override
	public KePuResult getRentList(Integer page, Integer size,
			Map<String, String> conditions) {
		Map<String,Object> map=new HashMap<String, Object>();
		StBuildingrentContentExample example=new StBuildingrentContentExample();
		StringBuffer orderString=new StringBuffer();
		if(conditions.containsKey("square")){
			Integer v=Integer.valueOf(conditions.get("square"));
			if(v==0)
				orderString.append("lowsize asc").append(",");
			else
				orderString.append("lowsize desc").append(",");
		}
		if(conditions.containsKey("price")){
			Integer v=Integer.valueOf(conditions.get("price"));
			if(v==0)
				orderString.append("lowprice asc").append(",");
			else
				orderString.append("lowprice desc").append(",");
		}
		if(orderString.length()!=0){
			orderString.deleteCharAt(orderString.length() - 1);
			example.setOrderByClause(orderString.toString());
		}else{
			example.setOrderByClause("createtime desc");
		}
		StBuildingrentContentExample.Criteria criteria=example.createCriteria();
		if(conditions.containsKey("query")){
			criteria.andTitleLike("%"+(String)conditions.get("query")+"%");
		}
		criteria.andStateEqualTo(0);
		/*if(conditions.containsKey("startArea")){
			Integer v=Integer.valueOf(conditions.get("startArea"));
			criteria.andLowsizeGreaterThanOrEqualTo(v);
		}
		if(conditions.containsKey("endArea")){
			Integer v=Integer.valueOf(conditions.get("endArea"));
			criteria.andHighsizeLessThanOrEqualTo(v);
		}
		if(conditions.containsKey("startPrice")){
			Double v=Double.valueOf(conditions.get("startPrice"));
			criteria.andLowpriceGreaterThanOrEqualTo(new BigDecimal(v));
		}
		if(conditions.containsKey("endPrice")){
			Double v=Double.valueOf(conditions.get("endPrice"));
			criteria.andHighpriceLessThanOrEqualTo(new BigDecimal(v));
		}*/
		if(conditions.containsKey("classify")){
			String v=String.valueOf(conditions.get("classify"));
			criteria.andClassifynameLike("%"+v+"%");
		}
		if(conditions.containsKey("trade")){
			String v=String.valueOf(conditions.get("trade"));
			criteria.andTradeEqualTo(Integer.valueOf(v));
		}
		PageHelper.startPage(page, size);
		List<StBuildingrentContent> myList = buildingrentContentMapper.selectByExample(example);
		List<Map<String,String>> rentList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StBuildingrentContent content : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", content.getTitle());
			temp.put("createTime",DateUtil.formatDate(content.getCreatetime(), MyConstant.updatetime));
			temp.put("startPrice", content.getLowprice()+"");
			temp.put("endPrice", content.getHighprice()+"");
			temp.put("startArea", content.getLowsize()+"");
			temp.put("endArea", content.getHighsize()+"");
			temp.put("rentId", content.getUid()+"");
			temp.put("classifyName", content.getClassifyname());
			temp.put("trade", content.getTrade()+"");
			temp.put("locationRequire", content.getLocationrequire());
			temp.put("hourseTypeRequire", content.getHoursetyperequire());
			temp.put("sellType", content.getSelltype()+"");
			rentList.add(temp);
		}
		PageInfo<StBuildingrentContent> pageInfo=new PageInfo<StBuildingrentContent>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("rentList", rentList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult getRentDetail(Integer rentId) {
		Map<String,String> map=new HashMap<String, String>();
		StBuildingrentContent content = buildingrentContentMapper.selectByPrimaryKey(rentId);
		if(content==null||content.getState()==1){
			return KePuResult.ok(ResultConstant.code_yewu, "已被删除或不存在", map);
		}
		map.put("title", content.getTitle());
		map.put("createTime",DateUtil.formatDate(content.getCreatetime(), MyConstant.updatetime));
		map.put("startPrice", content.getLowprice()+"");
		map.put("endPrice", content.getHighprice()+"");
		map.put("startArea", content.getLowsize()+"");
		map.put("endArea", content.getHighsize()+"");
		map.put("trade", content.getTrade()+"");
		map.put("locationRequire", content.getLocationrequire());
		map.put("hourseTypeRequire", content.getHoursetyperequire());
		map.put("avatar", content.getAvatar());
		map.put("realName", content.getRealname());
		map.put("mobile", content.getMobile());
		map.put("trade", content.getTrade()+"");
		map.put("rentId", content.getUid()+"");
		map.put("introduce", content.getIntroduce());
		map.put("classifyName", content.getClassifyname());
		map.put("sellType", content.getSelltype()+""); 
		map.put("userId", content.getUserid()+""); 
		// 评论数
		String v=jedisClient.get("commom_commentNum_"+content.getUid()+"_type"+"_"+4);
		int num=0;
		if(StringUtil.isEmpty(v)){
			StSkillandbuildingCommentExample ske=new StSkillandbuildingCommentExample();
			StSkillandbuildingCommentExample.Criteria sc=ske.createCriteria();
			sc.andTypeEqualTo(4);
			sc.andTypeidEqualTo(content.getUid());
		    num=commonCommentMapper.countByExample(ske);
			jedisClient.set("commom_commentNum_"+content.getUid()+"_type"+"_"+4,num+"");
		}else{
			num=Integer.valueOf(v);
		}
		map.put("commentCount", num+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public StBuildingrentContent getRentContentById(Integer rentId) {
		return buildingrentContentMapper.selectByPrimaryKey(rentId);
	}

	@Override
	public int saveRentContent(StBuildingrentContent buildingrentContent) {
		return buildingrentContentMapper.updateByPrimaryKeySelective(buildingrentContent);
	}

	@Override
	public KePuResult getMyBuilding(Integer userId, Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StBuildingsellContentExample example=new StBuildingsellContentExample();
		example.setOrderByClause("createtime desc");
		StBuildingsellContentExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		PageHelper.startPage(page, size);
		List<StBuildingsellContent> myList = buildingsellContentMapper.selectByExample(example);
		List<Map<String,String>> sellList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		for (StBuildingsellContent content : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", content.getTitle());
			temp.put("createTime",DateUtil.formatDate(content.getCreatetime(), MyConstant.updatetime));
			temp.put("price", content.getPrice()+"");
			temp.put("uid", content.getUid()+"");
			temp.put("coverPic", content.getCoverpic());
			temp.put("classifyName", content.getClassifyname());
			temp.put("trade", content.getTrade());
			temp.put("size", content.getSize()+""); 
			temp.put("houseType", content.getHousetype()+"");
			sellList.add(temp);
		}
		PageInfo<StBuildingsellContent> pageInfo=new PageInfo<StBuildingsellContent>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("buildingList", sellList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	@Override
	public KePuResult getMyRent(Integer userId, Integer page, Integer size) {
		Map<String,Object> map=new HashMap<String, Object>();
		StBuildingrentContentExample example=new StBuildingrentContentExample();
		example.setOrderByClause("createtime desc");
		StBuildingrentContentExample.Criteria criteria=example.createCriteria();
		criteria.andUseridEqualTo(userId);
		PageHelper.startPage(page, size);
		List<StBuildingrentContent> myList = buildingrentContentMapper.selectByExample(example);
		List<Map<String,String>> rentList=new LinkedList<Map<String,String>>();
		Map<String,String> temp;
		DeviceMessage d = SystemSession.get();
		String v = d.getAppVersion();
		for (StBuildingrentContent content : myList) {
			temp=new HashMap<String, String>();
			temp.put("title", content.getTitle());
			temp.put("createTime",DateUtil.formatDate(content.getCreatetime(), MyConstant.updatetime));
			temp.put("startPrice", content.getLowprice()+"");
			temp.put("endPrice", content.getHighprice()+"");
			temp.put("startArea", content.getLowsize()+"");
			temp.put("endArea", content.getHighsize()+"");
			temp.put("uid", content.getUid()+"");
			temp.put("rentId", content.getUid()+"");
			temp.put("classifyName", content.getClassifyname());
			temp.put("trade", content.getTrade()+"");
			temp.put("locationRequire", content.getLocationrequire());
			temp.put("hourseTypeRequire", content.getHoursetyperequire());
			rentList.add(temp);
		}
		PageInfo<StBuildingrentContent> pageInfo=new PageInfo<StBuildingrentContent>(myList);
		long total=pageInfo.getTotal();
		map.put("totalcount",total+"");
		map.put("rentList", rentList);
		map.put("pagesize", size+"");
		map.put("totalpage", (total/size+1)+"");
		map.put("currentpage", page+"");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	

	
	

	

}
