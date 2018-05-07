package com.kepu.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kepu.pojo.PageBean;
import com.kepu.pojo.StCommunity;
import com.kepu.pojo.StCommunityApply;
import com.kepu.pojo.StCommunityArticle;
import com.kepu.pojo.StCommunityNotice;
import com.kepu.pojo.StCommunityTeach;
import com.kepu.pojo.StCommunityUser;
import com.kepu.pojo.StProduct;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.TUser;
import com.kepu.pojo.community.MemberList;
import com.kepu.service.CommunityService;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;

@Controller
@RequestMapping("/community")
public class CommunityController {
	
	@Autowired
	private CommunityService communityService;
	//社团列表
//	@RequestMapping("list")
//	public ModelAndView list(@RequestParam(value="page",required=false)String page,
//			StCommunity community,HttpServletRequest request){
//		ModelAndView mav=new ModelAndView();
//		HttpSession session=request.getSession();
//		if(StringUtil.isEmpty(page)){
//			page="1";
//			session.setAttribute("community", community);
//		}else{
//			community=(StCommunity) session.getAttribute("community");
//		}
//		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
//		Map<String,Object> map=communityService.findStCommunity(pageBean, community);
//		List<StCommunity> communityList=(List<StCommunity>) map.get("list");
//		long total=(Long) map.get("total");
//		String pageCode=PageUtil.getPagation(request.getContextPath()+"/community/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
//		mav.addObject("pageCode", pageCode);
//		mav.addObject("modeName", "社团管理");
//		mav.addObject("communityList", communityList);
//		mav.addObject("mainPage", "community/list.jsp");
//		mav.setViewName("main");
//		return mav;
//	}

	@RequestMapping("list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,
			StCommunity community,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("community", community);
		}else{
			community=(StCommunity) session.getAttribute("community");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=communityService.findStCommunity(pageBean, community);
		List<StCommunity> communityList=(List<StCommunity>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/community/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "社团管理");
		mav.addObject("communityList", communityList);
		mav.addObject("mainPage", "community/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("preSave")
	public ModelAndView preSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "community/save.jsp");
		mav.addObject("modeName", "修改社团信息");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "修改社团信息");
			StCommunity community = communityService.getCommunityById(Integer.valueOf(id));
		    mav.addObject("community", community);
		}else{
			mav.addObject("actionName", "新增社团");	
		}
		return mav;
	}
	
	@RequestMapping("save")
	public String save(StCommunity community){
		communityService.saveStCommunity(community);
		return "redirect:/community/list";
	}
	
	@RequestMapping("setHome")
	public @ResponseBody Map<String,Object> setHome(@RequestParam(value="id")String id){
		Map<String,Object> result=new HashMap<String, Object>();
		StCommunity community = communityService.getCommunityById(Integer.valueOf(id));
		if(community!=null){
			community.setCarousel(1-community.getCarousel());
			communityService.saveStCommunity(community);
			result.put("result", "success");
		}else{
			result.put("result", "id不存在");
		}
		return result;
	}
	
	//  成员部分
	@RequestMapping(value="member/list/{communityId}")
	public ModelAndView memberlist(@PathVariable Integer communityId,@RequestParam(value="page",required=false)String page,
			MemberList memberList,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(memberList==null)
				memberList=new MemberList();
			memberList.setCommunityid(communityId);
			session.setAttribute("memberList", memberList);
		}else{
			memberList=(MemberList) session.getAttribute("memberList");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=communityService.findMemberlist(pageBean, memberList);
		List<MemberList>  MemberList=(List<MemberList>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/community/member/list/"+communityId,  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "成员管理");
		mav.addObject("communityId", communityId); 
		mav.addObject("MemberList", MemberList);
		mav.addObject("mainPage", "community/memberList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("member/setHome/{communityId}")
	public @ResponseBody Map<String,Object> memberSetHome(@PathVariable Integer communityId,@RequestParam(value="id")String id){
		Map<String,Object> result=new HashMap<String, Object>();
		StCommunityUser communityUser = communityService.getCommunityUser(communityId,Integer.valueOf(id));
		if(communityUser!=null){
			communityUser.setType(1-communityUser.getType());
			communityService.saveCommunityUser(communityUser);
			result.put("result", "success");
		}else{
			result.put("result", "id不存在");
		}
		return result;
	}
	
	//  公告部分
	@RequestMapping(value="notice/list/{communityId}")
	public ModelAndView noticelist(@PathVariable Integer communityId,@RequestParam(value="page",required=false)String page,
			StCommunityNotice communityNotice,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(communityNotice==null)
				communityNotice=new StCommunityNotice();
			communityNotice.setCommunityid(communityId);
			session.setAttribute("communityNotice", communityNotice);
		}else{
			communityNotice=(StCommunityNotice) session.getAttribute("communityNotice");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=communityService.findStCommunityNotice(pageBean, communityNotice);
		List<StCommunityNotice>  noticeList=(List<StCommunityNotice>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/community/notice/list/"+communityId,  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "公告管理");
		mav.addObject("communityId", communityId);
		mav.addObject("noticeList", noticeList);
		mav.addObject("mainPage", "community/noticeList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("notice/preSave/{communityId}")
	public ModelAndView preSave(@PathVariable Integer communityId,@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "community/noticeSave.jsp");
		mav.addObject("modeName", "修改公告");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "修改公告");
			StCommunityNotice communityNotice = communityService.getCommunityNoticeById(Integer.valueOf(id));
		    mav.addObject("communityNotice", communityNotice);
		}else{
			mav.addObject("actionName", "新增公告");	
		}
		return mav;
	}
	
	@RequestMapping("notice/save/{communityId}")
	public String save(StCommunityNotice communityNotice,@PathVariable Integer communityId){
		communityNotice.setCommunityid(communityId);
		communityService.saveStCommunityNotice(communityNotice);
		return "redirect:/community/notice/list/"+communityId;
	}
	
	@RequestMapping("notice/delete")
	public @ResponseBody Map<String, Object> noticeDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			int status=communityService.deleteCommunityNotice(Integer.valueOf(id));
			if(status!=1){
				result.put("result", "error");
				result.put("errorInfo", "删除失败");
			}else{
				result.put("result", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	// 帖子部分
	@RequestMapping(value="article/list/{communityId}")
	public ModelAndView articlelist(@PathVariable Integer communityId,@RequestParam(value="page",required=false)String page,
			StCommunityArticle communityArticle,HttpServletRequest request){
		TUser resultUser=(TUser) request.getSession().getAttribute("currentUser");
	    Integer townId=resultUser.getTownid();
	    Integer villageId=resultUser.getCountryid();
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(communityArticle==null)
				communityArticle=new StCommunityArticle();
			else{
				communityArticle.setCommunityid(communityId);
			}
			session.setAttribute("communityArticle", communityArticle);
		}else{
			communityArticle=(StCommunityArticle) session.getAttribute("communityArticle");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=communityService.findStCommunityArticle(pageBean, communityArticle,townId,villageId);
		List<StCommunityArticle>  articleList=(List<StCommunityArticle>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/community/article/list/"+communityId,  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "帖子管理");
		mav.addObject("communityId", communityId);
		mav.addObject("articleList", articleList);
		mav.addObject("mainPage", "community/articleList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("article/delete")
	public @ResponseBody Map<String, Object> articleDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			int status=communityService.deleteCommunityArticle(Integer.valueOf(id));
			if(status!=1){
				result.put("result", "error");
				result.put("errorInfo", "删除失败");
			}else{
				result.put("result", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	// 教学部分
	@RequestMapping(value="teach/list/{communityId}")
	public ModelAndView teachlist(@PathVariable Integer communityId,@RequestParam(value="page",required=false)String page,
			StCommunityTeach communityTeach,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(communityTeach==null)
				communityTeach=new StCommunityTeach();
			communityTeach.setCommunityid(communityId);
			session.setAttribute("communityTeach", communityTeach);
		}else{
			communityTeach=(StCommunityTeach) session.getAttribute("communityTeach");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=communityService.findStCommunityTeach(pageBean, communityTeach);
		List<StCommunityTeach>  teachList=(List<StCommunityTeach>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/community/teach/list/"+communityId,  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "教学管理");
		mav.addObject("communityId", communityId);
		mav.addObject("teachList", teachList);
		mav.addObject("mainPage", "community/teachList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("teach/delete")
	public @ResponseBody Map<String, Object> teachDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			int status=communityService.deleteCommunityTeach(Integer.valueOf(id));
			if(status!=1){
				result.put("result", "error");
				result.put("errorInfo", "删除失败");
			}else{
				result.put("result", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	//  审核部分
	@RequestMapping(value="apply/list/{communityId}")
	public ModelAndView applylist(@PathVariable Integer communityId,@RequestParam(value="page",required=false)String page,
			StCommunityApply communityApply,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(communityApply==null)
				communityApply=new StCommunityApply();
			communityApply.setCommunityid(communityId);
			session.setAttribute("communityApply", communityApply);
		}else{
			communityApply=(StCommunityApply) session.getAttribute("communityApply");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=communityService.findApplylist(pageBean, communityApply);
		List<StCommunityApply>  applyList=(List<StCommunityApply>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/community/apply/list/"+communityId,  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "申请审核");
		mav.addObject("communityId", communityId);
		mav.addObject("applyList", applyList);
		mav.addObject("mainPage", "community/applyList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("apply/preSave/{communityId}")
	public ModelAndView applyPreSave(@PathVariable Integer communityId,@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "community/applySave.jsp");
		mav.addObject("modeName", "申请审核");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "修改公告");
			StCommunityApply apply = communityService.getStCommunityApplyById(Integer.valueOf(id));
		    mav.addObject("apply", apply);
		}
		return mav;
	}
	
	@RequestMapping("apply/save/{communityId}")
	public String applySave(StCommunityApply communityApply,@PathVariable Integer communityId){
		communityApply.setCommunityid(communityId);
		communityService.saveStCommunityApply(communityApply);
		return "redirect:/community/apply/list/"+communityId;
	}
}
