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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kepu.constant.MyConstant;
import com.kepu.constant.ResultConstant;
import com.kepu.dao.JedisClient;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StAuthenticCompany;
import com.kepu.pojo.StAuthenticPeople;
import com.kepu.pojo.StBuildingrentContent;
import com.kepu.pojo.StBuildingsellContent;
import com.kepu.pojo.StJob;
import com.kepu.pojo.StJobApply;
import com.kepu.pojo.StSkillContent;
import com.kepu.pojo.StTaskContent;
import com.kepu.pojo.StUser;
import com.kepu.service.JobService;
import com.kepu.service.SkillService;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.service.VillageService;
import com.kepu.util.DateUtil;
import com.kepu.util.ExceptionUtil;
import com.kepu.util.StringUtil;

@Controller
@RequestMapping("/job")
public class JobController {

	@Autowired
	private UserService userService;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private SysService sysService;
	@Autowired
	private JobService jobService;
	@Autowired
	private SkillService skillService;
	@Autowired
	private VillageService villageService;
	
	
	/**
	 * 获取轮播图 
	 * @return
	 */
	@RequestMapping(value="getCarousel")
	public @ResponseBody Object getCarousel(){
		try {
			return jobService.getCarousel();
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
			return jobService.getClassify();
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	/**
	 * 获取行业
	 */
	@RequestMapping(value="getIndustry")
	public @ResponseBody Object getIndustry(HttpServletRequest request){
		try {
			Map<String,Object> map=new HashMap<String, Object>();
			String industry = sysService.getParam("industry");
			LinkedList<String> list=new LinkedList<String>();
		    if(StringUtil.isNotEmpty(industry)){
		    	String[] ds=industry.split(",");
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
	 * 发布招聘
	 */
	@RequestMapping(value="release")
	public @ResponseBody Object release(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			// 检查是否认证
			if(!skillService.checkAuth(stUser2.getUserid(),2)){
				return KePuResult.build(ResultConstant.code_yewu, "请先进行企业认证", "");
			}
			String title="";
			String introduce="";
			String lowPrice="";
			String highPrice="";
			String area="";
			String education="";
			String workExperience="";
			String require="";
			String mobile="";
			String realName="";
			/*String position="";*/
			String coverPic=null;
			String detailPics=null;
			String address="";
			StringBuffer sb=new StringBuffer();
			if(map.containsKey("title")){title=map.get("title");}else{sb.append("title").append(",");}
			if(map.containsKey("introduce")){introduce=map.get("introduce");}else{sb.append("introduce").append(",");}
			if(map.containsKey("lowPrice")){lowPrice=map.get("lowPrice");}else{sb.append("lowPrice").append(",");}
			if(map.containsKey("highPrice")){highPrice=map.get("highPrice");}else{sb.append("highPrice").append(",");}
			if(map.containsKey("area")){area=map.get("area");}else{sb.append("area").append(",");}
			if(map.containsKey("education")){education=map.get("education");}else{sb.append("education").append(",");}
			if(map.containsKey("workExperience")){workExperience=map.get("workExperience");}else{sb.append("workExperience").append(",");}
			if(map.containsKey("require")){require=map.get("require");}else{sb.append("require").append(",");}
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
				/*if(mobile.length()>12)
					return KePuResult.build(ResultConstant.code_param, ResultConstant.mobile_error, "");*/
			}else{
				sb.append("mobile").append(",");
			}
			if(map.containsKey("realName")){realName=map.get("realName");}else{sb.append("realName").append(",");}
			//if(map.containsKey("position")){position=map.get("position");}else{sb.append("position").append(",");}
			if(map.containsKey("coverPic")){
				coverPic=map.get("coverPic");
				if(StringUtil.isEmpty(coverPic))
					coverPic=null;
			}
			if(map.containsKey("detailPics")){
				detailPics=map.get("detailPics");
				if(StringUtil.isEmpty(detailPics))
					detailPics=null;
			}else{
				//sb.append("detailPics").append(",");
			}
			if(map.containsKey("address")){address=map.get("address");}else{sb.append("address").append(",");}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");	
			}
			StJob sc=new StJob();
			sc.setJobname(title);
			sc.setDescription(introduce);
			sc.setLowprice(Integer.valueOf(lowPrice));
			sc.setHighprice(Integer.valueOf(highPrice));
			Integer countyId=Integer.valueOf(area);
			sc.setCounty(countyId);
			sc.setTown(villageService.getParentIdByCountyId(countyId));
			sc.setEducation(Integer.valueOf(education));
			sc.setWorkexperience(workExperience);
			sc.setJobrequire(require);
			sc.setMobile(mobile);
			sc.setRealname(realName);
			//sc.setPosition(position);
			sc.setCoverpic(coverPic);
			sc.setAddress(address);
			sc.setAvatar(stUser2.getAvatar());
			sc.setUserid(stUser2.getUserid());
			sc.setCreatetime(new Date());
			sc.setShowname(stUser2.getNickname());
			sc.setDetailpics(detailPics);
			sc.setState(0);
			//String approve=sysService.getParam("need_approve");
			/*if("1".equals(approve))  // 审核开关
				sc.setState(2);  //需要审核
*/			return jobService.releaseJob(sc);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	
	/**
	 * 获取职位详情
	 * @return
	 */
	@RequestMapping(value="getJobDetail/{jobId}")
	public @ResponseBody Object getProductDetail(@PathVariable Integer jobId){
		try {
			
			return jobService.getJobDetail(jobId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取招聘列表
	 * @return
	 */
	@RequestMapping(value="getJobList/{page}")
	public @ResponseBody Object getJobList(@PathVariable Integer page,@RequestBody(required=false) Map<String, String> map,
			HttpServletRequest request,@RequestParam(required=false) Integer mine){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			if(map==null)
				map=new HashMap<String, String>();
			if(page==1&&map.containsKey("query")){
				sysService.addHotSearch((String)map.get("query"),5);
			}
			if(mine!=null){
				map.put("userId", stUser2.getUserid()+"");
			}
			if(map.containsKey("salary")){
				int salary=Integer.valueOf(map.get("salary"));
				String startPrice="-1";
				String endPrice="-1";
				if(salary==1){
					startPrice="0";endPrice="1000";
				}else if(salary==2){
					startPrice="1000";endPrice="3000";
				}else if(salary==3){
					startPrice="3000";endPrice="5000";
				}else if(salary==4){
					startPrice="5000";endPrice="7000";
				}else if(salary==5){
					startPrice="7000";endPrice="10000";
				}else if(salary==6){
					startPrice="10000";endPrice="999999";
				}else if(salary==7){
					startPrice="-1";endPrice="-1";
				}
				map.put("startPrice", startPrice);
				map.put("endPrice", endPrice);
			}
			return jobService.getJobList(page, 10, map);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 修改招聘信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="editJob/{jobId}",method=RequestMethod.POST)
	public @ResponseBody Object editProduct(@PathVariable Integer jobId,@RequestBody Map<String, String> map,HttpServletRequest request){
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
			String lowPrice="";
			String highPrice="";
			String area="";
			String education="";
			String workExperience="";
			String require="";
			String mobile="";
			String realName="";
			//String position="";
			String coverPic="";
			String detailPics="";
			String address="";
			StringBuffer sb=new StringBuffer();
			
			StJob job = jobService.getJobById(jobId);
			if(job==null||job.getState()==1)
				return KePuResult.build(ResultConstant.code_param, "已被删除或不存在"+sb.toString(), "");
			int userId=job.getUserid();
			int real=stUser2.getUserid();
			if(userId!=real)
				return KePuResult.build(ResultConstant.code_param, "只有发布者才允许修改"+sb.toString(), "");
			if(map.containsKey("title")){
				title=map.get("title");
				job.setJobname(title);
			}
			if(map.containsKey("introduce")){
				introduce=map.get("introduce");
				job.setDescription(introduce);
			}
			if(map.containsKey("lowPrice")){
				lowPrice=map.get("lowPrice");
				job.setLowprice(Integer.valueOf(lowPrice));
			}
			if(map.containsKey("highPrice")){
				highPrice=map.get("highPrice");
				job.setHighprice(Integer.valueOf(highPrice));
			}
			if(map.containsKey("area")){
				area=map.get("area");
				Integer countyId=Integer.valueOf(area);
				job.setCounty(countyId);
				job.setTown(villageService.getParentIdByCountyId(countyId));
			}
			if(map.containsKey("education")){
				education=map.get("education");
				job.setEducation(Integer.valueOf(education));
			}  
			if(map.containsKey("workExperience")){
				workExperience=map.get("workExperience");
				job.setWorkexperience(workExperience);
			}
			if(map.containsKey("require")){
				require=map.get("require");
				job.setJobrequire(require);
			}
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
				/*if(mobile.length()>12)
					return KePuResult.build(ResultConstant.code_param, ResultConstant.mobile_error, "");*/
				job.setMobile(mobile);
			}
			if(map.containsKey("address")){
				address=map.get("address");
				job.setAddress(address);
			}
			if(map.containsKey("realName")){
				realName=map.get("realName");
				job.setRealname(realName);
			}
			/*if(map.containsKey("position")){
				position=map.get("position");
				job.setPosition(position);
			}*/
			if(map.containsKey("coverPic")){
				coverPic=map.get("coverPic");
				job.setCoverpic(coverPic);
			}
			if(map.containsKey("detailPics")){
				detailPics=map.get("detailPics");
				job.setDetailpics(detailPics);
			}
			job.setCreatetime(new Date());
			jobService.saveJob(job);
			return KePuResult.ok(ResultConstant.code_ok, "修改成功", "");
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 求职发布
	 */
	@RequestMapping(value="applyRelease")
	public @ResponseBody Object applyRelease(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			String position="";
			String industry="";
			String area="";
			String address="";
			String startPrice="-1";
			String endPrice="-1";
			String mobile="";
			String realName=""; 
			String introduce=""; 
			String title="";
			StringBuffer sb=new StringBuffer();
			if(map.containsKey("position")){position=map.get("position");}else{sb.append("position").append(",");}
			if(map.containsKey("industry")){industry=map.get("industry");}else{sb.append("industry").append(",");}
			if(map.containsKey("area")){area=map.get("area");}else{sb.append("area").append(",");}
			if(map.containsKey("address")){address=map.get("address");}else{sb.append("address").append(",");}
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
			}else{sb.append("mobile").append(",");}
			if(map.containsKey("realName")){realName=map.get("realName");}else{sb.append("realName").append(",");}
			if(map.containsKey("introduce")){introduce=map.get("introduce");}else{sb.append("introduce").append(",");}
			if(map.containsKey("title")){title=map.get("title");}else{sb.append("title").append(",");}
			if(map.containsKey("salary")){
				if(StringUtil.isEmpty(map.get("salary"))){
					startPrice="-1";endPrice="-1";}
				else{
					int salary=Integer.valueOf(map.get("salary"));
					if(salary==1){
						startPrice="0";endPrice="1000";
					}else if(salary==2){
						startPrice="1000";endPrice="3000";
					}else if(salary==3){
						startPrice="3000";endPrice="5000";
					}else if(salary==4){
						startPrice="5000";endPrice="7000";
					}else if(salary==5){
						startPrice="7000";endPrice="10000";
					}else if(salary==6){
						startPrice="10000";endPrice="999999";
					}else if(salary==7){
						startPrice="-1";endPrice="-1";
					}
				}
				map.put("startPrice", startPrice);
				map.put("endPrice", endPrice);
			}else{sb.append("salary").append(",");}
			if(sb.length()!=0){
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空"+sb.toString(), "");	
			}
			StJobApply jobApply =new StJobApply();
			jobApply.setPosition(position);
			jobApply.setDescription(introduce);
			jobApply.setRealname(realName);
			jobApply.setMobile(mobile);
			jobApply.setLowprice(new Integer(startPrice));
			jobApply.setHighprice(new Integer(endPrice));
			jobApply.setHy(industry);
			Integer countyId=Integer.valueOf(area);
			jobApply.setCounty(countyId);
			jobApply.setTown(villageService.getParentIdByCountyId(countyId));
			jobApply.setAddress(address);
			jobApply.setAvatar(stUser2.getAvatar());
			jobApply.setUserid(stUser2.getUserid());
			jobApply.setCreatetime(new Date());
			jobApply.setShowname(stUser2.getNickname());
			jobApply.setState(0);
			jobApply.setTitle(title);
			return jobService.releaseApply(jobApply);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取 求职列表
	 * @return
	 */
	@RequestMapping(value="getApplyList/{page}")
	public @ResponseBody Object getRentList(@PathVariable Integer page,@RequestBody(required=false) Map<String, String> map,
			HttpServletRequest request,@RequestParam(required=false) Integer mine){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			if(map==null)
				map=new HashMap<String, String>();
			if(page==1&&map.containsKey("query")){
				sysService.addHotSearch((String)map.get("query"),5);
			}
			if(mine!=null){
				map.put("userId", stUser2.getUserid()+"");
			}
			if(page==1&&map.containsKey("query")){
				sysService.addHotSearch((String)map.get("query"),4);
			}
			if(map.containsKey("salary")){
				String startPrice="-1";
				String endPrice="-1";
				if(StringUtil.isEmpty(map.get("salary"))){
					startPrice="-1";endPrice="-1";}
				else {
					int salary=Integer.valueOf(map.get("salary"));
					if(salary==1){
						startPrice="0";endPrice="1000";
					}else if(salary==2){
						startPrice="1000";endPrice="3000";
					}else if(salary==3){
						startPrice="3000";endPrice="5000";
					}else if(salary==4){
						startPrice="5000";endPrice="7000";
					}else if(salary==5){
						startPrice="7000";endPrice="10000";
					}else if(salary==6){
						startPrice="10000";endPrice="999999";
					}else if(salary==7){
						startPrice="-1";endPrice="-1";
					}
				}
				map.put("startPrice", startPrice);
				map.put("endPrice", endPrice);
			}
			return jobService.getApplyList(page, 10, map);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 获取 求职详情
	 * @return
	 */
	@RequestMapping(value="getApplyDetail/{applyId}")
	public @ResponseBody Object gettaskDetail(@PathVariable Integer applyId){
		try {
			
			return jobService.getApplyDetail(applyId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 修改 求职
	 * @param map
	 * @return
	 */
	@RequestMapping(value="editApplyJob/{applyId}",method=RequestMethod.POST)
	public @ResponseBody Object editApplyJob(@PathVariable Integer applyId,@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if(stUser2==null){
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			StUser stUser=userService.getUserById(stUser2.getUserid());
			String position="";
			String industry="";
			String area="";
			String address="";
			String mobile="";
			String realName=""; 
			String introduce=""; 
			String title="";
			StringBuffer sb=new StringBuffer();
			StJobApply job = jobService.getApplyById(applyId);
			if(job==null||job.getState()==1)
				return KePuResult.build(ResultConstant.code_param, "已被删除或不存在"+sb.toString(), "");
			int userId=job.getUserid();
			int real=stUser2.getUserid();
			if(userId!=real)
				return KePuResult.build(ResultConstant.code_param, "只有发布者才允许修改"+sb.toString(), "");
			if(map.containsKey("position")){
				position=map.get("position");
				job.setPosition(position);
			}
			if(map.containsKey("industry")){
				industry=map.get("industry");
				job.setHy(industry);
			}
			if(map.containsKey("area")){
				area=map.get("area");
				Integer countyId=Integer.valueOf(area);
				job.setCounty(countyId);
				job.setTown(villageService.getParentIdByCountyId(countyId));
			}
			if(map.containsKey("mobile")){
				mobile=map.get("mobile");
				/*if(mobile.length()>12)
					return KePuResult.build(ResultConstant.code_param, ResultConstant.mobile_error, "");*/
				job.setMobile(mobile);
			}
			if(map.containsKey("address")){
				address=map.get("address");
				job.setAddress(address);
			}
			if(map.containsKey("realName")){
				realName=map.get("realName");
				job.setRealname(realName);
			}
			if(map.containsKey("introduce")){
				introduce=map.get("introduce");
				job.setDescription(introduce);
			}
			if(map.containsKey("title")){
				title=map.get("title");
				job.setTitle(title);
			}
			if(map.containsKey("salary")){
				int salary=Integer.valueOf(map.get("salary"));
				String startPrice="-1";
				String endPrice="-1";
				if(salary==1){
					startPrice="0";endPrice="1000";
				}else if(salary==2){
					startPrice="1000";endPrice="3000";
				}else if(salary==3){
					startPrice="3000";endPrice="5000";
				}else if(salary==4){
					startPrice="5000";endPrice="7000";
				}else if(salary==5){
					startPrice="7000";endPrice="10000";
				}else if(salary==6){
					startPrice="10000";endPrice="999999";
				}else if(salary==7){
					startPrice="-1";endPrice="-1";
				}
				job.setLowprice(Integer.valueOf(startPrice));
				job.setHighprice(Integer.valueOf(endPrice));
			}
			job.setCreatetime(new Date());
			jobService.saveApply(job);
			return KePuResult.ok(ResultConstant.code_ok, "修改成功", "");
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
}
