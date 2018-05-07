package com.kepu.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kepu.constant.MyConstant;
import com.kepu.constant.ResultConstant;
import com.kepu.dao.JedisClient;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StAuthenticCompany;
import com.kepu.pojo.StAuthenticPeople;
import com.kepu.pojo.StSkillContent;
import com.kepu.pojo.StTaskContent;
import com.kepu.pojo.StUser;
import com.kepu.service.SkillService;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.util.DateUtil;
import com.kepu.util.ExceptionUtil;
import com.kepu.util.StringUtil;

@Controller
@RequestMapping("/skill")
public class SkillController {

	@Autowired
	private UserService userService;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private SkillService skillService;
	@Autowired
	private SysService sysService;
	/**
	 * 个人认证
	 * @return
	 */
	@RequestMapping(value="auth/personal")
	public @ResponseBody Object authPersonal(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			String userName="";
			String IDNo="";
			String IDcardZ="";
			String IDcardF=null;
			String IDcardZHold=null;
			String photo=null;
			String message=null;
			String education="";
			String selfIntroduction="";
			StringBuffer sb=new StringBuffer();
			if(map.containsKey("userName")){
				userName=map.get("userName");
			}else{
				sb.append("userName").append(",");
			}
			if(map.containsKey("IDNo")){
				IDNo=map.get("IDNo");
			}else{
				sb.append("IDNo").append(",");
			}
			if(map.containsKey("IDcardZ"))
				IDcardZ=map.get("IDcardZ");
			else{
				sb.append("IDcardZ").append(",");
			}
			if(map.containsKey("IDcardF"))
				IDcardF=map.get("IDcardF");
			if(map.containsKey("IDcardZHold"))
				IDcardZHold=map.get("IDcardZHold");
			if(map.containsKey("photo"))
				photo=map.get("photo");
			if(map.containsKey("message"))
				message=map.get("message");
			if(map.containsKey("education"))
				education=map.get("education");
			else{
				sb.append("education").append(",");
			}
			if(map.containsKey("selfIntroduction"))
				selfIntroduction=map.get("selfIntroduction");
			else{
				sb.append("selfIntroduction").append(",");
			}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");	
			}
			StAuthenticPeople sap=new StAuthenticPeople();
			sap.setCreatetime(new Date());
			sap.setEducation(Integer.valueOf(education));
			sap.setIdcardno(IDNo);
			sap.setHandz(IDcardZHold);
			sap.setAvatar(photo);
			sap.setIdcardz(IDcardZ);
			sap.setIdcardf(IDcardF);
			sap.setMessage(message);
			sap.setRealname(userName);
			sap.setIntroduce(selfIntroduction);
			sap.setUserid(stUser2.getUserid());
			sap.setState(1); // 1:待审核 0:通过 2:拒绝
			return skillService.authPersonal(sap);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 企业认证
	 * @return
	 */
	@RequestMapping(value="auth/company")
	public @ResponseBody Object authCompany(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			String userName="";
			String corporate="";
			String socialCode="";
			String licenceZ="";
			String licenceF=null;
			String permitZ=null;
			String permitF=null;
			String address="";
			String introduce="";
			String mobile="";
			StringBuffer sb=new StringBuffer();
			if(map.containsKey("userName")){
				userName=map.get("userName");
			}else{
				sb.append("userName").append(",");
			}
			if(map.containsKey("corporate")){
				corporate=map.get("corporate");
			}else{
				sb.append("corporate").append(",");
			}
			if(map.containsKey("socialCode"))
				socialCode=map.get("socialCode");
			else{
				sb.append("socialCode").append(",");
			}
			if(map.containsKey("licenceZ"))
				licenceZ=map.get("licenceZ");
			else{
				sb.append("licenceZ").append(",");
			}
			if(map.containsKey("licenceF"))
				licenceF=map.get("licenceF");
			if(map.containsKey("permitZ"))
				permitZ=map.get("permitZ");
			if(map.containsKey("permitF"))
				permitF=map.get("permitF");
			if(map.containsKey("address"))
				address=map.get("address");
			else{
				sb.append("address").append(",");
			}
			if(map.containsKey("introduce"))
				introduce=map.get("introduce");
			else{
				sb.append("introduce").append(",");
			}
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
				/*if(mobile.length()>12)
					return KePuResult.build(ResultConstant.code_param, ResultConstant.mobile_error, "");*/
			}
			else{
				sb.append("mobile").append(",");
			}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");	
			}
			StAuthenticCompany sap=new StAuthenticCompany();
			sap.setCreatetime(new Date());
			sap.setAddress(address);
			sap.setCompanyname(userName);
			sap.setLicencef(licenceF);
			sap.setLicencez(licenceZ);
			sap.setPermitz(permitZ);
			sap.setPermitf(permitF);
			sap.setMobile(mobile);
			sap.setIntroduce(introduce);
			sap.setSocialcode(socialCode);
			sap.setCorporate(corporate);
			sap.setUserid(stUser2.getUserid());
			sap.setState(1); // 1:待审核 0:通过 2:拒绝
			return skillService.authCompany(sap);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}

	
	/**
	 * 查询用户是否进行个人认证或企业认证
	 */
	@RequestMapping(value="getAuthStatus")
	public @ResponseBody Object getAuthStatus(HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			return skillService.getAuthStatus(stUser2.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取分类   category/menu
	 */
	@RequestMapping(value="getClassify")
	public @ResponseBody Object getClassify(HttpServletRequest request){
		try {
			return skillService.getClassify();
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 发布技能
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
			String coverPic=null;
			String detailPics=null;
			String realName="";
			String mobile="";
			String price="";
			String authorityType="";
			String classifyName="";  // 一级+二级
			String address="";
			String lon="";
			String lat="";
			StringBuffer sb=new StringBuffer();
			String location=MyConstant.getLocation(stUser2.getTownid());
			if(map.containsKey("title")){
				title=map.get("title");
			}else{
				sb.append("title").append(",");
			}
			if(map.containsKey("introduce")){
				introduce=map.get("introduce");
			}else{
				sb.append("introduce").append(",");
			}
			if(map.containsKey("coverPic")){
				coverPic=map.get("coverPic");
				if(StringUtil.isEmpty(coverPic))
					coverPic=null;
			}
			if(map.containsKey("detailPics"))
				detailPics=map.get("detailPics");
			if(map.containsKey("realName"))
				realName=map.get("realName");
			else{
				sb.append("realName").append(",");
			}
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
				/*if(mobile.length()>12)
					return KePuResult.build(ResultConstant.code_param, ResultConstant.mobile_error, "");*/
			}
			else{
				sb.append("mobile").append(",");
			}
			if(map.containsKey("price"))
				price=map.get("price");
			else{
				sb.append("price").append(",");
			}
			if(map.containsKey("authorityType"))
				authorityType=map.get("authorityType");
			else{
				sb.append("authorityType").append(",");
			}
			if(map.containsKey("classifyName"))
				classifyName=map.get("classifyName");
			else{
				sb.append("classifyId").append(",");
			}
			if(map.containsKey("address"))
				address=map.get("address");
			else{
				sb.append("address").append(",");
			}
			if(map.containsKey("lon")){
				lon=map.get("lon");
				if(StringUtil.isEmpty(lon))
					lon=location.split(",")[0];
			} 
			else{
				lon=location.split(",")[0];
			}
			if(map.containsKey("lat")){
				lat=map.get("lat");
				if(StringUtil.isEmpty(lat))
					lat=location.split(",")[1];
			}
			else{
				lat=location.split(",")[1];
			}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");	
			}
			// 检查是否认证
			if(!skillService.checkAuth(stUser2.getUserid(), Integer.valueOf(authorityType))){
				return KePuResult.build(ResultConstant.code_yewu, "请先认证", "");
			}
			StSkillContent sc=new StSkillContent();
			sc.setAddress(address);
			sc.setCoverpic(coverPic);
			sc.setAuthoritytype(Integer.valueOf(authorityType));
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
			sc.setLon(Double.valueOf(lon));
			sc.setLat(Double.valueOf(lat));
			String approve=sysService.getParam("need_approve");
			if("1".equals(approve))  // 审核开关
				sc.setState(2);  //需要审核
			return skillService.releaseSkill(sc);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取轮播图 
	 * @return
	 */
	@RequestMapping(value="getCarousel")
	public @ResponseBody Object getCarousel(@RequestParam(required=false) Integer type){
		try {
			// 	1：技能 2：任务
			return skillService.getCarousel(type==null?1:type);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取技能详情
	 * @return
	 */
	@RequestMapping(value="getskillDetail/{skillId}")
	public @ResponseBody Object getProductDetail(@PathVariable Integer skillId){
		try {
			return skillService.getskillDetail(skillId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取技能列表
	 * @return
	 */
	@RequestMapping(value="getSkillList/{page}")
	public @ResponseBody Object getSkillList(@PathVariable Integer page,@RequestBody Map<String, String> map,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			if(!map.containsKey("lat")){
				String location=MyConstant.getLocation(stUser2.getTownid());
				map.put("lat", location.split(",")[1]);
			}
		    if(!map.containsKey("lon")){
		    	String location=MyConstant.getLocation(stUser2.getTownid());
		    	map.put("lon", location.split(",")[0]);
		    }
			if(page==1&&map.containsKey("query")){
				sysService.addHotSearch((String)map.get("query"),1);
			}
			return skillService.getSkillList(page, 10, map);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 修改技能
	 * @param map
	 * @return
	 */
	@RequestMapping(value="editSkill/{skillId}",method=RequestMethod.POST)
	public @ResponseBody Object editProduct(@PathVariable Integer skillId,@RequestBody Map<String, String> map,HttpServletRequest request){
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
			String mobile="";
			String price="";
			String authorityType="";
			String classifyName="";  // 一级+二级
			String address="";
			StringBuffer sb=new StringBuffer();
			
			StSkillContent content = skillService.getSkillContentById(skillId);
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
			if(map.containsKey("price")){
				price=map.get("price");
				content.setPrice(new BigDecimal(price));
			}
			if(map.containsKey("address")){
				address=map.get("address");
				content.setAddress(address);
			}
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
				content.setMobile(mobile);
			}
			if(map.containsKey("detailPics")){
				detailPics=map.get("detailPics");
				content.setDetailpics(detailPics);
			}
			if(map.containsKey("coverPic")){
				coverPic=map.get("coverPic");
				content.setCoverpic(coverPic);
			}
			if(map.containsKey("classifyName")){
				classifyName=map.get("classifyName");
				content.setClassifyname(classifyName);
			}
			if(map.containsKey("realName")){
				realName=map.get("realName");
				content.setRealname(realName);
			}  
			if(map.containsKey("authorityType")){
				authorityType=map.get("authorityType");
				content.setAuthoritytype(Integer.valueOf(authorityType));
			}
			content.setCreatetime(new Date());
			skillService.saveSkillContent(content);
			return KePuResult.ok(ResultConstant.code_ok, "修改成功", "");
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 发布任务
	 */
	@RequestMapping(value="taskRelease")
	public @ResponseBody Object taskRelease(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			String title="";
			String introduce="";
			String coverPic=null;
			String detailPics=null;
			String realName="";
			String mobile="";
			String price="";
			String authorityType="1";
			String classifyName="";  // 一级+二级
			String address="";
			String lon="";
			String lat="";
			String startTime="";
			StringBuffer sb=new StringBuffer();
			String location=MyConstant.getLocation(stUser2.getTownid());
			if(map.containsKey("title")){
				title=map.get("title");
			}else{
				sb.append("title").append(",");
			}
			if(map.containsKey("introduce")){
				introduce=map.get("introduce");
			}else{
				sb.append("introduce").append(",");
			}
			if(map.containsKey("coverPic")){
				coverPic=map.get("coverPic");
				if(StringUtil.isEmpty(coverPic))
					coverPic=null;
			}
			if(map.containsKey("detailPics"))
				detailPics=map.get("detailPics");
			if(map.containsKey("realName"))
				realName=map.get("realName");
			else{
				sb.append("realName").append(",");
			}
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
			}
			else{
				sb.append("mobile").append(",");
			}
			if(map.containsKey("price"))
				price=map.get("price");
			else{
				sb.append("price").append(",");
			}
			if(map.containsKey("classifyName"))
				classifyName=map.get("classifyName");
			else{
				sb.append("classifyId").append(",");
			}
			if(map.containsKey("address"))
				address=map.get("address");
			else{
				sb.append("address").append(",");
			}
			if(map.containsKey("lon")){
				lon=map.get("lon");
				if(StringUtil.isEmpty(lon))
					lon=location.split(",")[0];
			}
			else{
				lon=location.split(",")[0];
			}
			if(map.containsKey("lat")){
				lat=map.get("lat");
				if(StringUtil.isEmpty(lat))
					lat=location.split(",")[1];
			}
			else{
				lat=location.split(",")[1];
			}
			if(map.containsKey("startTime"))
				startTime=map.get("startTime");
			else{
				sb.append("startTime").append(",");
			}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");	
			}
			// 检查是否认证
			/*if(!skillService.checkAuth(stUser2.getUserid(), Integer.valueOf(authorityType))){
				return KePuResult.build(ResultConstant.code_yewu, "请先认证", "");
			}*/
			StTaskContent  sc=new StTaskContent();
			sc.setAddress(address);
			sc.setCoverpic(coverPic);
			sc.setAuthoritytype(Integer.valueOf(authorityType));
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
			sc.setLon(Double.valueOf(lon));
			sc.setLat(Double.valueOf(lat));
			sc.setStarttime(DateUtil.formatString(startTime, "yyyy-MM-dd"));
			String approve=sysService.getParam("need_approve");
			if("1".equals(approve))  // 审核开关
				sc.setState(2);  //需要审核
			return skillService.releaseTask(sc);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取任务列表
	 * @return
	 */
	@RequestMapping(value="getTaskList/{page}")
	public @ResponseBody Object getTaskList(@PathVariable Integer page,@RequestBody Map<String, String> map,
			HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			if(!map.containsKey("lat")){
				String location=MyConstant.getLocation(stUser2.getTownid());
				map.put("lat", location.split(",")[1]);
			}
		    if(!map.containsKey("lon")){
		    	String location=MyConstant.getLocation(stUser2.getTownid());
		    	map.put("lon", location.split(",")[0]);
		    }
			if(page==1&&map.containsKey("query")){
				sysService.addHotSearch((String)map.get("query"),2);
			}
			return skillService.getTaskList(page, 10, map);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取任务详情
	 * @return
	 */
	@RequestMapping(value="gettaskDetail/{taskId}")
	public @ResponseBody Object gettaskDetail(@PathVariable Integer taskId){
		try {
			
			return skillService.getTaskDetail(taskId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 修改任务
	 * @param map
	 * @return
	 */
	@RequestMapping(value="editTask/{taskId}",method=RequestMethod.POST)
	public @ResponseBody Object editTask(@PathVariable Integer taskId,@RequestBody Map<String, String> map,HttpServletRequest request){
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
			String mobile="";
			String price="";
			String authorityType="1";
			String classifyName="";  // 一级+二级
			String address="";
			String startTime="";
			StringBuffer sb=new StringBuffer();
			
			StTaskContent content = skillService.getTaskContentById(taskId);
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
			if(map.containsKey("price")){
				price=map.get("price");
				content.setPrice(new BigDecimal(price));
			}
			if(map.containsKey("address")){
				address=map.get("address");
				content.setAddress(address);
			}
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
				/*if(mobile.length()>12)
					return KePuResult.build(ResultConstant.code_param, ResultConstant.mobile_error, "");*/
				content.setMobile(mobile);
			}
			if(map.containsKey("detailPics")){
				detailPics=map.get("detailPics");
				content.setDetailpics(detailPics);
			}
			if(map.containsKey("coverPic")){
				coverPic=map.get("coverPic");
				content.setCoverpic(coverPic);
			}
			if(map.containsKey("classifyName")){
				classifyName=map.get("classifyName");
				content.setClassifyname(classifyName);
			}
			if(map.containsKey("realName")){
				realName=map.get("realName");
				content.setRealname(realName);
			}  
			/*if(map.containsKey("authorityType")){
				authorityType=map.get("authorityType");
				content.setAuthoritytype(Integer.valueOf(authorityType));
			}*/
			if(map.containsKey("startTime")){
				startTime=map.get("startTime");
				content.setStarttime(DateUtil.formatString(startTime, "yyyy-MM-dd"));
			}
			content.setCreatetime(new Date());
			skillService.saveTaskContent(content);
			return KePuResult.ok(ResultConstant.code_ok, "修改成功", "");
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取我发布的技能
	 * @return
	 */
	@RequestMapping(value="getMySkill/{page}")
	public @ResponseBody Object getMySkill(@PathVariable Integer page,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			StUser stUser2 = userService.getUserByToken(token);
			return skillService.getMySkill(stUser2.getUserid(),page,20);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取我发布的任务
	 * @return
	 */
	@RequestMapping(value="getMyTask/{page}")
	public @ResponseBody Object getMyTask(@PathVariable Integer page,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			StUser stUser2 = userService.getUserByToken(token);
			return skillService.getMyTask(stUser2.getUserid(),page,20);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
}
