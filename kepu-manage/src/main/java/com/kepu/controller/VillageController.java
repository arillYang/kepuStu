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
import org.springframework.web.bind.annotation.PathVariable;
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
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageContent;
import com.kepu.pojo.StVillageContentWithBLOBs;
import com.kepu.pojo.StVillageNews;
import com.kepu.pojo.StVillageNewsContent;
import com.kepu.service.NewService;
import com.kepu.service.PictureService;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.service.VillageNewsService;
import com.kepu.service.VillageService;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;


@Controller
@RequestMapping("/village")
public class VillageController {

	protected final Log logger = LogFactory.getLog(VillageController.class);
	@Autowired
	private VillageService villageService;
	@Autowired
	private SysService sysService;
	
	//乡镇信息列表
	@RequestMapping("town/list")
	public ModelAndView townList(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="parent",required=false)Integer parent,
			StVillage village,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("village", village);
		}else{
			village=(StVillage) session.getAttribute("village");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=villageService.findStVillage(pageBean, village,parent==null?-1:parent);
		List<StVillage> villageList=(List<StVillage>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/village/town/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "乡镇信息管理");
		mav.addObject("villageList", villageList);
		mav.addObject("mainPage", "village/townList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("town/preSave")
	public ModelAndView townPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "village/townSave.jsp");
		mav.addObject("modeName", "编辑乡镇信息");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑乡镇信息");
			StVillage village = villageService.getVillageById(Integer.valueOf(id));
			StVillageContentWithBLOBs realcontent=villageService.getVillageContent(Integer.valueOf(id));
			mav.addObject("village", village);
			mav.addObject("realcontent", realcontent);
		}else{
			mav.addObject("actionName", "新增乡镇信息");
		}
		return mav;
	}
	
	@RequestMapping("town/save")
	public String newsSave(StVillage village,String realcontent,String myContent){
		if(village.getParent()==null)
			village.setParent(-1);
		if(StringUtil.isEmpty(village.getPic()))
			village.setPic(null);
		villageService.saveVillage(village,realcontent,myContent);
		return "redirect:/village/town/list";
	}
	
	
	//村信息列表
	@RequestMapping("country/list/{townId}")
	public ModelAndView countryList(@PathVariable Integer townId
			,@RequestParam(value="page",required=false)String page,
			@RequestParam(value="parent",required=false)Integer parent,
			StVillage village,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("village", village);
		}else{
			village=(StVillage) session.getAttribute("village");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=villageService.findStVillage(pageBean, village,townId);
		List<StVillage> villageList=(List<StVillage>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/village/country/list/"+townId,  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "乡村管理");
		mav.addObject("villageList", villageList);
		mav.addObject("parent", townId);
		mav.addObject("mainPage", "village/countryList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("country/preSave")
	public ModelAndView countryPreSave(@RequestParam(value="id",required=false)String id,
			@RequestParam(value="parent",required=false)String parent){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "village/countrySave.jsp");
		mav.addObject("modeName", "编辑乡村信息");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑乡村信息");
			StVillage village = villageService.getVillageById(Integer.valueOf(id));
			StVillageContentWithBLOBs realcontent=villageService.getVillageContent(Integer.valueOf(id));
			mav.addObject("village", village);
			mav.addObject("realcontent", realcontent);
		}else{
			mav.addObject("parent", parent);
			mav.addObject("actionName", "新增乡村信息");
		}
		return mav;
	}
	
	@RequestMapping("country/save")
	public String countrySave(StVillage village,String realcontent,String myContent,String parent){
		if(StringUtil.isEmpty(village.getPic()))
			village.setPic(null);
		if(StringUtil.isNotEmpty(parent))
			village.setParent(Integer.valueOf(parent));
		villageService.saveVillage(village,realcontent,myContent);
		Integer townId=sysService.getTownIdByVillageId(village.getId());
		return "redirect:/village/country/list/"+townId;
	}
	
	
}
