package com.kepu.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kepu.constant.ResultConstant;
import com.kepu.dao.JedisClient;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StBuildingrentContent;
import com.kepu.pojo.StBuildingsellContent;
import com.kepu.pojo.StUser;
import com.kepu.service.BuildService;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.util.ExceptionUtil;
import com.kepu.util.StringUtil;

@Controller
@RequestMapping("/building")
public class BuildController {

	@Autowired
	private UserService userService;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private SysService sysService;
	@Autowired
	private BuildService buildService;
	
	/**
	 * 获取分类   category/menu
	 */
	@RequestMapping(value="getClassify")
	public @ResponseBody Object getClassify(HttpServletRequest request){
		try {
			return buildService.getClassify();
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	/**
	 * 获取装修 
	 */
	@RequestMapping(value="getDecorate")
	public @ResponseBody Object getDecorate(HttpServletRequest request){
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			String decorate = sysService.getParam("decorate");
			LinkedList<String> list=new LinkedList<String>();
		    if(StringUtil.isNotEmpty(decorate)){
		    	String[] ds=decorate.split(",");
		    	for (String string : ds) {
		    		if(StringUtil.isNotEmpty(string))
		    			list.add(string);
				}
		    }
		    map.put("list", list);
			return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取配置
	 */
	@RequestMapping(value="getConfig")
	public @ResponseBody Object getConfig(HttpServletRequest request){
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			String config = sysService.getParam("config");
			LinkedList<String> list=new LinkedList<String>();
		    if(StringUtil.isNotEmpty(config)){
		    	String[] ds=config.split(",");
		    	for (String string : ds) {
		    		if(StringUtil.isNotEmpty(string))
		    			list.add(string);
				}
		    }
		    map.put("list", list);
			return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 发布出租出售
	 */
	@RequestMapping(value="release")
	public @ResponseBody Object release(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			String title="";
			String introduce="";
			String coverPic="";
			String detailPics="";
			String realName="";
			String price="";
			String houseType="";
			String address="";
			String mobile="";
			String size="";
			String decorate="";
			String config="";
			String trade="";
			String sellType="";
			String classifyName="";  // 一级+二级
			StringBuffer sb=new StringBuffer();
			if(map.containsKey("title")){title=map.get("title");}else{sb.append("title").append(",");}
			if(map.containsKey("introduce")){introduce=map.get("introduce");}else{sb.append("introduce").append(",");}
			if(map.containsKey("coverPic")){coverPic=map.get("coverPic");}else{sb.append("coverPic").append(",");}
			if(map.containsKey("detailPics")){detailPics=map.get("detailPics");}else{sb.append("detailPics").append(",");}
			if(map.containsKey("realName")){realName=map.get("realName");}else{sb.append("realName").append(",");}
			if(map.containsKey("price")){price=map.get("price");}else{sb.append("price").append(",");}
			if(map.containsKey("houseType")){houseType=map.get("houseType");}else{sb.append("houseType").append(",");}
			if(map.containsKey("address")){address=map.get("address");}else{sb.append("address").append(",");}
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
			}else{
				sb.append("mobile").append(",");
			}
			if(map.containsKey("size")){
				size=map.get("size");
			}else{
				sb.append("size").append(",");
			}
			if(map.containsKey("decorate")){decorate=map.get("decorate");}else{sb.append("decorate").append(",");}
			if(map.containsKey("config")){config=map.get("config");}else{sb.append("config").append(",");}
			if(map.containsKey("trade")){trade=map.get("trade");}else{sb.append("trade").append(",");}
			if(map.containsKey("sellType")){sellType=map.get("sellType");}else{sb.append("sellType").append(",");}
			if(map.containsKey("classifyName")){classifyName=map.get("classifyName");}else{sb.append("classifyName").append(",");}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");	
			}
			StBuildingsellContent sc=new StBuildingsellContent();
			sc.setAddress(address);
			sc.setCoverpic(coverPic);
			sc.setAvatar(stUser2.getAvatar());
			sc.setClassifyname(classifyName);
			sc.setUserid(stUser2.getUserid());
			sc.setDetailpics(detailPics);
			sc.setPrice(new BigDecimal(price));
			sc.setRealname(realName);
			sc.setAddress(address);
			sc.setTitle(title);
			sc.setIntroduce(introduce);
			sc.setCreatetime(new Date());
			sc.setMobile(mobile);
			sc.setNickname(stUser2.getNickname());
			sc.setHousetype(houseType);
			sc.setSelltype(Integer.valueOf(sellType));
			sc.setConfig(config);
			sc.setTrade(trade);
			sc.setDecorate(decorate);
			sc.setSize(Double.valueOf(size));
			String approve=sysService.getParam("need_approve");
			if("1".equals(approve))  // 审核开关
				sc.setState(2);  //需要审核
			return buildService.releaseSell(sc);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取轮播图 
	 * @return
	 */
	@RequestMapping(value="getCarousel")
	public @ResponseBody Object getCarousel(){
		try {
			return buildService.getCarousel();
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取出租出售详情
	 * @return
	 */
	@RequestMapping(value="getBuildingDetail/{buildingId}")
	public @ResponseBody Object getProductDetail(@PathVariable Integer buildingId){
		try {
			
			return buildService.getSellDetail(buildingId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取出租出售列表
	 * @return
	 */
	@RequestMapping(value="getBuildingList/{page}")
	public @ResponseBody Object getSkillList(@PathVariable Integer page,@RequestBody Map<String, String> map,
			HttpServletRequest request){
		try {
			if(page==1&&map.containsKey("query")){
				sysService.addHotSearch((String)map.get("query"),3);
			}
			return buildService.getSellList(page, 10, map);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 修改出租出售
	 * @param map
	 * @return
	 */
	@RequestMapping(value="editBuilding/{buildingId}",method=RequestMethod.POST)
	public @ResponseBody Object editProduct(@PathVariable Integer buildingId,@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			StUser stUser=userService.getUserById(stUser2.getUserid());
			String title="";
			String introduce="";
			String coverPic="";
			String detailPics="";
			String realName="";
			String price="";
			String houseType="";
			String address="";
			String mobile="";
			String size="";
			String decorate="";
			String config="";
			String trade="";
			String sellType="";
			String classifyName="";  // 一级+二级
			StringBuffer sb=new StringBuffer();
			
			StBuildingsellContent content = buildService.getSellContentById(buildingId);
			if(content==null||content.getState()==1)
				return KePuResult.build(ResultConstant.code_param, "已被删除或不存在"+sb.toString(), "");
			int userId=content.getUserid();
			int real=stUser2.getUserid();
			if(userId!=real)
				return KePuResult.build(ResultConstant.code_param, "只有发布者才允许修改"+sb.toString(), "");
			if(map.containsKey("title")){
				title=map.get("title");
				content.setTitle(title);
			}
			if(map.containsKey("introduce")){
				introduce=map.get("introduce");
				content.setIntroduce(introduce);
			}
			if(map.containsKey("coverPic")){
				coverPic=map.get("coverPic");
				content.setCoverpic(coverPic);
			}
			if(map.containsKey("detailPics")){
				detailPics=map.get("detailPics");
				content.setDetailpics(detailPics);
			}
			if(map.containsKey("realName")){
				realName=map.get("realName");
				content.setRealname(realName);
			}  
			if(map.containsKey("price")){
				price=map.get("price");
				content.setPrice(new BigDecimal(price));
			}
			if(map.containsKey("houseType")){
				houseType=map.get("houseType");
				content.setHousetype(houseType);
			}
			if(map.containsKey("address")){
				address=map.get("address");
				content.setAddress(address);
			}
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
				content.setMobile(mobile);
			}
			if(map.containsKey("size")){
				size=map.get("size");
				content.setSize(Double.valueOf(size));
			}
			if(map.containsKey("decorate")){
				decorate=map.get("decorate");
				content.setDecorate(decorate);
			}
			if(map.containsKey("config")){
				config=map.get("config");
				content.setConfig(config);
			}
			if(map.containsKey("trade")){
				trade=map.get("trade");
				content.setTrade(trade);
			}
			if(map.containsKey("sellType")){
				sellType=map.get("sellType");
				content.setSelltype(Integer.valueOf(sellType));
			}
			if(map.containsKey("classifyName")){
				classifyName=map.get("classifyName");
				content.setClassifyname(classifyName);
			}
			content.setCreatetime(new Date());
			buildService.saveSellContent(content);
			return KePuResult.ok(ResultConstant.code_ok, "修改成功", "");
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 发布求租求购
	 */
	@RequestMapping(value="rentRelease")
	public @ResponseBody Object rentRelease(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			String title="";
			String introduce="";
			String realName="";
			String mobile="";
			String startPrice="";
			String endPrice="";
			String startArea="";
			String endArea="";
			String locationRequire="";
			String hourseTypeRequire="";
			String sellType="";  
			String trade="";
			String classifyName="";
			StringBuffer sb=new StringBuffer();
			if(map.containsKey("title")){title=map.get("title");}else{sb.append("title").append(",");}
			if(map.containsKey("introduce")){introduce=map.get("introduce");}else{sb.append("introduce").append(",");}
			if(map.containsKey("realName")){realName=map.get("realName");}else{sb.append("realName").append(",");}
			if(map.containsKey("mobile")){mobile=map.get("mobile");}else{sb.append("mobile").append(",");}
			if(map.containsKey("startPrice")){startPrice=map.get("startPrice");}else{sb.append("startPrice").append(",");}
			if(map.containsKey("endPrice")){endPrice=map.get("endPrice");}else{sb.append("endPrice").append(",");}
			if(map.containsKey("startArea")){startArea=map.get("startArea");}else{sb.append("startArea").append(",");}
			if(map.containsKey("endArea")){endArea=map.get("endArea");}else{sb.append("endArea").append(",");}
			if(map.containsKey("locationRequire")){locationRequire=map.get("locationRequire");}else{sb.append("locationRequire").append(",");}
			if(map.containsKey("hourseTypeRequire")){hourseTypeRequire=map.get("hourseTypeRequire");}else{sb.append("hourseTypeRequire").append(",");}
			if(map.containsKey("sellType")){sellType=map.get("sellType");}else{sb.append("sellType").append(",");}
			if(map.containsKey("trade")){trade=map.get("trade");}else{sb.append("trade").append(",");}
			if(map.containsKey("classifyName")){classifyName=map.get("classifyName");}else{sb.append("classifyName").append(",");}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");	
			}
			StBuildingrentContent  sc=new StBuildingrentContent();
			sc.setTitle(title);
			sc.setIntroduce(introduce);
			sc.setRealname(realName);
			sc.setMobile(mobile);
			sc.setLowprice(new BigDecimal(startPrice));
			sc.setHighprice(new BigDecimal(endPrice));
			sc.setLowsize(Double.valueOf(startArea));
			sc.setHighsize(Double.valueOf(endArea));
			sc.setLocationrequire(locationRequire);
			sc.setHoursetyperequire(hourseTypeRequire);
			sc.setSelltype(Integer.valueOf(sellType));
			sc.setTrade(Integer.valueOf(trade));
			sc.setClassifyname(classifyName);
			sc.setUserid(stUser2.getUserid());
			sc.setAvatar(stUser2.getAvatar());
			sc.setCreatetime(new Date());
			sc.setNickname(stUser2.getNickname());
			String approve=sysService.getParam("need_approve");
			if("1".equals(approve))  // 审核开关
				sc.setState(2);  //需要审核
			return buildService.releaseRent(sc);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取求租求购列表
	 * @return
	 */
	@RequestMapping(value="getRentList/{page}")
	public @ResponseBody Object getRentList(@PathVariable Integer page,@RequestBody Map<String, String> map,
			HttpServletRequest request){
		try {
			if(page==1&&map.containsKey("query")){
				sysService.addHotSearch((String)map.get("query"),4);
			}
			return buildService.getRentList(page, 10, map);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取求租求购详情
	 * @return
	 */
	@RequestMapping(value="getRentDetail/{rentId}")
	public @ResponseBody Object gettaskDetail(@PathVariable Integer rentId){
		try {
			
			return buildService.getRentDetail(rentId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 修改求租求购
	 * @param map
	 * @return
	 */
	@RequestMapping(value="editRent/{rentId}",method=RequestMethod.POST)
	public @ResponseBody Object editTask(@PathVariable Integer rentId,@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			StUser stUser=userService.getUserById(stUser2.getUserid());
			String title="";
			String introduce="";
			String realName="";
			String mobile="";
			String startPrice="";
			String endPrice="";
			String startArea="";
			String endArea="";
			String locationRequire="";
			String hourseTypeRequire="";
			String sellType="";  
			String trade="";
			String classifyName="";
			StringBuffer sb=new StringBuffer();
			StBuildingrentContent content = buildService.getRentContentById(rentId);
			if(content==null||content.getState()==1)
				return KePuResult.build(ResultConstant.code_param, "已被删除或不存在"+sb.toString(), "");
			int userId=content.getUserid();
			int real=stUser2.getUserid();
			if(userId!=real)
				return KePuResult.build(ResultConstant.code_param, "只有发布者才允许修改"+sb.toString(), "");
			if(map.containsKey("title")){
				title=map.get("title");
				content.setTitle(title);
			}
			if(map.containsKey("introduce")){
				introduce=map.get("introduce");
				content.setIntroduce(introduce);
			}
			if(map.containsKey("realName")){
				realName=map.get("realName");
				content.setRealname(realName);
			} 
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
				content.setMobile(mobile);
			}
			if(map.containsKey("startPrice")){
				startPrice=map.get("startPrice");
				content.setLowprice(new BigDecimal(startPrice));
			}
			if(map.containsKey("endPrice")){
				endPrice=map.get("endPrice");
				content.setHighprice(new BigDecimal(endPrice));
			}
			if(map.containsKey("startArea")){
				startArea=map.get("startArea");
				content.setLowsize(Double.valueOf(startArea));
			}
			if(map.containsKey("endArea")){
				endArea=map.get("endArea");
				content.setHighsize(Double.valueOf(endArea));
			}
			if(map.containsKey("locationRequire")){
				locationRequire=map.get("locationRequire");
				content.setLocationrequire(locationRequire);
			}
			if(map.containsKey("hourseTypeRequire")){
				hourseTypeRequire=map.get("hourseTypeRequire");
				content.setHoursetyperequire(hourseTypeRequire);
			}
			if(map.containsKey("sellType")){
				sellType=map.get("sellType");
				content.setSelltype(Integer.valueOf(sellType));
			}
			if(map.containsKey("trade")){
				trade=map.get("trade");
				content.setTrade(Integer.valueOf(trade));
			}
			if(map.containsKey("classifyName")){
				classifyName=map.get("classifyName");
				content.setClassifyname(classifyName);
			}
			content.setCreatetime(new Date());
			buildService.saveRentContent(content);
			return KePuResult.ok(ResultConstant.code_ok, "修改成功", "");
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	/**
	 * 获取我发布的出租出售
	 * @return
	 */
	@RequestMapping(value="getMyBuilding/{page}")
	public @ResponseBody Object getMySkill(@PathVariable Integer page,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			StUser stUser2 = userService.getUserByToken(token);
			return buildService.getMyBuilding(stUser2.getUserid(),page,20);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取我发布的任务
	 * @return
	 */
	@RequestMapping(value="getMyRent/{page}")
	public @ResponseBody Object getMyRent(@PathVariable Integer page,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			StUser stUser2 = userService.getUserByToken(token);
			return buildService.getMyRent(stUser2.getUserid(),page,20);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
}
