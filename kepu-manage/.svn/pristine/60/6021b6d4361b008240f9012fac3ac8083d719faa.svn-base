package com.kepu.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
import com.kepu.pojo.StUser;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.StVillageNews;
import com.kepu.pojo.StVillageNewsContent;
import com.kepu.pojo.TUser;
import com.kepu.service.NewService;
import com.kepu.service.PictureService;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.service.VillageNewsService;
import com.kepu.service.VillageService;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;


@Controller
@RequestMapping("/villageNews")
public class VillageNewsController {

	protected final Log logger = LogFactory.getLog(VillageNewsController.class);
	@Autowired
	private VillageNewsService villageNewsService;
	@Autowired
	private UserService userService;
	@Autowired
	private PictureService pictureService;
	@Autowired
	private SysService sysService;
	@Autowired
	private VillageService villageService;
	//乡镇新闻列表
	@RequestMapping("list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,StVillageNews news,
			HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("news", news);
		}else{
			news=(StVillageNews) session.getAttribute("news");
		}
		// 获取权限
		TUser resultUser=(TUser) session.getAttribute("currentUser");
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=villageNewsService.findStVillageNews(pageBean, news,resultUser.getTownid(),resultUser.getCountryid());
		List<StVillageNews> newsList=(List<StVillageNews>) map.get("list");
		List<String> townName=new LinkedList<String>();
		for (StVillageNews stVillageNews : newsList) {
			int town=villageNewsService.getTownIdByNewsId(stVillageNews.getUid());
			String name = sysService.getVillageNameById(town);
			townName.add(name);
			//mav.addObject("villageIds", villageNewsService.getVillageIdsBelongByNewsId(stVillageNews.getUid()));
		}
		mav.addObject("townName",townName);
		long total=(Long) map.get("total");
		mav.addObject("viewNum", map.get("viewCount"));
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/villageNews/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "乡镇新闻管理");
		mav.addObject("newsList", newsList);
		mav.addObject("mainPage", "villageNews/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("preSave")
	public ModelAndView newsPreSave(@RequestParam(value="id",required=false)String id,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "villageNews/save.jsp");
		mav.addObject("modeName", "编辑乡镇新闻");
		mav.setViewName("main");
		List<StVillage> town = sysService.getTowns();
		// 获取权限
	    TUser resultUser=(TUser) request.getSession().getAttribute("currentUser");
	    Integer townId=resultUser.getTownid();
	    Integer villageId=resultUser.getCountryid();
	    if(townId!=0&&villageId==0){
	    	// 乡镇级权限
	    	/*List<StVillage> village=sysService.getVillages(townId);
	    	mav.addObject("village", village);*/
	    	mav.addObject("townId", townId);
	    }
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑乡镇新闻");
			StVillageNews news = villageNewsService.getVillageNewsById(Integer.valueOf(id));
			StVillageNewsContent realcontent=villageNewsService.getVillageNewsContent(Integer.valueOf(id));
			mav.addObject("town", town);
			mav.addObject("townId",villageNewsService.getTownIdByNewsId(news.getUid()));
			mav.addObject("villageIds", villageNewsService.getVillageIdsBelongByNewsId(news.getUid()));
			mav.addObject("realcontent", realcontent);
		    mav.addObject("news", news);
		    String logo[]=news.getNewsimages().split(",");
		    mav.addObject("logo", logo);
		}else{
			mav.addObject("town", town);
			mav.addObject("actionName", "新增新闻");			
		}
		return mav;
	}
	
	@RequestMapping("delete")
	public @ResponseBody Map<String, Object> newsDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			villageNewsService.deleteStVillageNewsById(Integer.valueOf(id));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("save")
	public String newsSave(StVillageNews news,String realcontent,String myContent,String town,
			String village,HttpServletRequest request){
		if(news.getNewsstyle()!=3){
			if(StringUtil.isNotEmpty(news.getNewsimages())){
				news.setNewsimages(news.getNewsimages().split(",")[0]);
			}
		}
		// 获取权限
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
		villageNewsService.saveNews(news,realcontent,myContent,town,villageIds);
		return "redirect:/villageNews/list";
	}
	
	@RequestMapping(value="/upload")
	public @ResponseBody String pictureUpload(HttpServletRequest request,MultipartFile uploadFile){
		
	    HashMap<String, String> extMap = new HashMap<String, String>();  
        //支持的文件类型  
        extMap.put("image", "jpg,jpeg,png,bmp");   
        //最大文件大小    
        long maxSize = 32505856;   
      //获取文件类型  
        String dirName = request.getParameter("dir");  
        if (dirName == null) {  
            dirName = "image";  
        }
        MultipartHttpServletRequest mrequest= (MultipartHttpServletRequest)request;  
        Map map=mrequest.getFileMap();  
        Collection<MultipartFile> c = map.values();  
        Iterator item = c.iterator();
        //遍历选择的图片  
        CommonsMultipartFile file=(CommonsMultipartFile) item.next();  
        FileItem fileItem=file.getFileItem();  
        long fileSize = file.getSize();  
        logger.info("文件大小为"+fileSize+"字节");
        //检查文件大小  
        uploadFile=file; 
        Gson g=new Gson();
		//System.out.println(request);
        PictrueResult result=pictureService.uploadPicture(uploadFile);
        String s=g.toJson(result);
        return s;
	}
	
}
