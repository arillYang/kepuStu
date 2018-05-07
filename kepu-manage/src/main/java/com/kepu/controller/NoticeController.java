package com.kepu.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.PictrueResult;
import com.kepu.pojo.StClassify;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StNewsContent;
import com.kepu.pojo.StNoticeNews;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StNoticeNews;
import com.kepu.pojo.StNoticeNewsContent;
import com.kepu.pojo.TUser;
import com.kepu.service.NewService;
import com.kepu.service.NoticeNewsService;
import com.kepu.service.PictureService;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.service.NoticeNewsService;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;


@Controller
@RequestMapping("/notice")
public class NoticeController {

	protected final Log logger = LogFactory.getLog(NoticeController.class);
	@Autowired
	private NoticeNewsService noticeNewsService;
	@Autowired
	private UserService userService;
	@Autowired
	private PictureService pictureService;
	@Autowired
	private SysService sysService;
	
	//公告列表
	@RequestMapping("list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,StNoticeNews news,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("news", news);
		}else{
			news=(StNoticeNews) session.getAttribute("news");
		}
		// 获取权限
		TUser resultUser=(TUser) session.getAttribute("currentUser");
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=noticeNewsService.findNoticeNews(pageBean, news,resultUser.getTownid(),resultUser.getCountryid());
		List<StNoticeNews> newsList=(List<StNoticeNews>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/notice/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "公告管理");
		mav.addObject("newsList", newsList);
		mav.addObject("mainPage", "notice/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("preSave")
	public ModelAndView newsPreSave(@RequestParam(value="id",required=false)String id,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "notice/save.jsp");
		mav.addObject("modeName", "编辑公告");
		mav.setViewName("main");
		// 获取权限
	    TUser resultUser=(TUser) request.getSession().getAttribute("currentUser");
	    Integer townId=resultUser.getTownid();
	    Integer villageId=resultUser.getCountryid();
	    if(townId!=0&&villageId==0){
	    	mav.addObject("townId", townId);
	    }
		List<StVillage> town = sysService.getTowns();
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑公告");
			StNoticeNews news = noticeNewsService.getStNoticeNewsById(Integer.valueOf(id));
			StNoticeNewsContent realcontent=noticeNewsService.getStNoticeNewsContent(Integer.valueOf(id));
			mav.addObject("town", town);
			mav.addObject("townId",noticeNewsService.getTownIdByNewsId(news.getUid()));
			mav.addObject("villageIds", noticeNewsService.getVillageIdsBelongByNewsId(news.getUid()));
			mav.addObject("realcontent", realcontent);
		    mav.addObject("news", news);
		    String logo[]=news.getNewsimages().split(",");
		    mav.addObject("logo", logo);
		}else{
			mav.addObject("town", town);
			mav.addObject("actionName", "新增公告");			
		}
		return mav;
	}
	
	@RequestMapping("delete")
	public @ResponseBody Map<String, Object> newsDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			noticeNewsService.deleteStNoticeNewsById(Integer.valueOf(id));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("save")
	public String newsSave(StNoticeNews news,String realcontent,String myContent,String town,String village,HttpServletRequest request){
		if(news.getNewsstyle()!=3){
			if(StringUtil.isNotEmpty(news.getNewsimages())){
				news.setNewsimages(news.getNewsimages().split(",")[0]);
			}
		}
		TUser resultUser=(TUser) request.getSession().getAttribute("currentUser");
		Integer townId=resultUser.getTownid();
		Integer villageId=resultUser.getCountryid();
		if(StringUtil.isEmpty(town))
			town=String.valueOf(townId);
		if("-100".equals(village))
			village=null;
		/*if(StringUtil.isEmpty(village)){
			if(StringUtil.isEmpty(town))
				village=String.valueOf(villageId);
			else
				town=String.valueOf(townId);
		}*/
		String[] villageIds=null;
		if(StringUtil.isNotEmpty(village)){
			villageIds=village.split(",");
		}
		noticeNewsService.saveNews(news,realcontent,myContent,town,villageIds);
		return "redirect:/notice/list";
	}
	
}
