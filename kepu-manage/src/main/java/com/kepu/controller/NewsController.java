package com.kepu.controller;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
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

import com.getui.java.pushmessage.PushList;
import com.getui.java.pushmessage.PushtoAPP;
import com.google.gson.Gson;
import com.kepu.constant.MyConstant;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.PictrueResult;
import com.kepu.pojo.StClassify;
import com.kepu.pojo.StLink;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StNewsContent;
import com.kepu.pojo.StNewsQuestion;
import com.kepu.pojo.StNewsTimetask;
import com.kepu.pojo.StNoticeNews;
import com.kepu.pojo.StPush;
import com.kepu.pojo.StVillageNews;
import com.kepu.pojo.TUser;
import com.kepu.service.LinkService;
import com.kepu.service.NewService;
import com.kepu.service.NoticeNewsService;
import com.kepu.service.PictureService;
import com.kepu.service.UserService;
import com.kepu.service.VillageNewsService;
import com.kepu.service.VillageService;
import com.kepu.util.DateUtil;
import com.kepu.util.LinConstant;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;


@Controller
@RequestMapping("/news")
public class NewsController {

	protected final Log logger = LogFactory.getLog(NewsController.class);
	@Autowired
	private NewService newService;
	@Autowired
	private UserService userService;
	@Autowired
	private PictureService pictureService;
	@Autowired
	private VillageNewsService villageNewsService;
	@Autowired
	private NoticeNewsService noticeNewsService;
	@Autowired
	private VillageService villageService;
	@Autowired
	private LinkService linkService;
	
	//新闻列表
	@RequestMapping("list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,
			StNews news,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(news==null)
				news=new StNews();
			session.setAttribute("news", news);
		}else{
			news=(StNews) session.getAttribute("news");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		String carousel=request.getParameter("carousel");
		String stick=request.getParameter("stick");
		String draft=request.getParameter("draft");
		String temp="";
		int type=0;
		if(StringUtil.isNotEmpty(carousel)){
			news.setCarousel(1);
			mav.addObject("modeName", "轮播管理");
			temp="?carousel=1";
			type=1;
		}
		else if(StringUtil.isNotEmpty(stick)){
			news.setStick(1);
			mav.addObject("modeName", "置顶新闻管理");
			temp="?stick=1";
			type=2;
		}
		else if(StringUtil.isNotEmpty(draft)){
			news.setDraft(1);
			mav.addObject("modeName", "草稿管理");
			temp="?draft=1";
			type=3;
		}else{
			mav.addObject("modeName", "新闻管理");
		}
		Map<String,Object> map=newService.findStNews(pageBean, news);
		List<StNews> newsList=(List<StNews>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/news/list"+(StringUtil.isNotEmpty(temp)?temp:""),  
				new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("newsList", newsList);
		mav.addObject("temp", temp);
		mav.addObject("type", type);
		mav.addObject("viewNum", map.get("viewCount"));
		mav.addObject("mainPage", "news/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("preSave")
	public ModelAndView newsPreSave(@RequestParam(value="id",required=false)String id,@RequestParam(value="type",required=false)Integer type){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "news/save.jsp");
		mav.addObject("modeName", "编辑新闻");
		mav.setViewName("main");
		List<StClassify> classifyList=newService.getAllClass();
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑新闻");
			StNews news = newService.getNewsById(Integer.valueOf(id));
			StNewsContent realcontent=newService.getNewsContent(Integer.valueOf(id));
			String s=StringUtils.join(newService.getClassfyByNewsId(news.getUid()), ",");
			mav.addObject("typeList",s );
			mav.addObject("classifyList", classifyList);
			mav.addObject("realcontent", realcontent);
		    mav.addObject("news", news);
		    String logo[]=news.getNewsimages().split(",");
		    mav.addObject("logo", logo);
		    // 查询是否定时任务
		    StNewsTimetask r= newService.getTask(news.getUid());
		    if(r!=null){
		    	String dateStr=DateUtil.formatDate(r.getPublishtime(), "yyyy-MM-dd");
		    	int hour=r.getPublishtime().getHours();
		    	mav.addObject("dateStr",dateStr);
		    	mav.addObject("hour",hour);
		    }
		    List<StNewsQuestion> qList = newService.getStNewsQuestion(news.getUid());
		    if(qList.size()!=0){
		    	mav.addObject("questionStr","1");
		    	mav.addObject("qList",qList);
		    }
		    String url=news.getFullurl();
		    if(StringUtil.isNotEmpty(url)){
		    	mav.addObject("urlName", url.split(",")[0]);
		    	mav.addObject("url", url.split(",")[1]);
		    }
		}else{
			mav.addObject("classifyList", classifyList);
			mav.addObject("actionName", "新增新闻");			
		}
		mav.addObject("type", type==null?0:type);
		return mav;
	}
	
	@RequestMapping("delete")
	public @ResponseBody Map<String, Object> newsDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			newService.deleteStNewsById(Integer.valueOf(id));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("cancel")
	public @ResponseBody Map<String, Object> newsCancel(@RequestParam(value="id")String id,
			@RequestParam(value="type")String type,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			newService.cancelNewsById(Integer.valueOf(id),Integer.valueOf(type));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("save")
	public String newsSave(StNews news,String realcontent,String myContent,String classify,int type,
			String time,String hour,String question,String urlName,String url,
			String title1,String opA1,String opB1,String opC1,String opD1,String answer1,String uid1,
			String title2,String opA2,String opB2,String opC2,String opD2,String answer2,String uid2,
			String title3,String opA3,String opB3,String opC3,String opD3,String answer3,String uid3){
		if(news.getNewsstyle()!=3){
			if(StringUtil.isNotEmpty(news.getNewsimages())){
				news.setNewsimages(news.getNewsimages().split(",")[0]);
			}
		}
		Date d=null;
		if(StringUtil.isNotEmpty(time)){
		   if(hour.length()==1)  hour="0"+hour;
		   String t=time+" "+hour+":00:00";
		   try {
			   d=DateUtil.formatString(t, LinConstant.formatStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			newService.deleteNewsTimetask(news.getUid());
		}
		if(StringUtil.isNotEmpty(url)&&StringUtil.isNotEmpty(urlName))
			news.setFullurl(urlName+","+url);
		Integer newsId=newService.saveNews(news,realcontent,myContent,classify,d,false);
		if("1".equals(question)){
			StNewsQuestion sq=new StNewsQuestion();
			sq.setSubject(title1);
			sq.setAnswer(answer1);
			sq.setChoice1(opA1);
			sq.setChoice2(opB1);
			sq.setChoice3(opC1);
			sq.setChoice4(opD1);
			sq.setNewsid(newsId);
			if(!StringUtil.isEmpty(uid1))
				sq.setUrid(Integer.valueOf(uid1));
			newService.saveStNewsQuestion(sq);
			StNewsQuestion sq2=new StNewsQuestion();
			sq2.setSubject(title2);
			sq2.setAnswer(answer2);
			sq2.setChoice1(opA2);
			sq2.setChoice2(opB2);
			sq2.setChoice3(opC2);
			sq2.setChoice4(opD2);
			sq2.setNewsid(newsId);
			if(!StringUtil.isEmpty(uid2))
				sq2.setUrid(Integer.valueOf(uid2));
			newService.saveStNewsQuestion(sq2);
			StNewsQuestion sq3=new StNewsQuestion();
			sq3.setSubject(title3);
			sq3.setAnswer(answer3);
			sq3.setChoice1(opA3);
			sq3.setChoice2(opB3);
			sq3.setChoice3(opC3);
			sq3.setChoice4(opD3);
			sq3.setNewsid(newsId);
			if(!StringUtil.isEmpty(uid3))
				sq3.setUrid(Integer.valueOf(uid3));
			newService.saveStNewsQuestion(sq3);
		}else if("0".equals(question)){
			newService.deleteStNewsQuestion(newsId);
		}
		String temp="";
		if(type==1)
			temp="?carousel=1";
		else if(type==2)
			temp="?stick=1";
		else if(type==3)
			temp="?draft=1";
		return "redirect:/news/list"+(StringUtil.isNotEmpty(temp)?temp:"");
		//return "redirect:/news/preSave?id="+newsId+"&type="+type;
	}
	
	@RequestMapping("save2")
	public @ResponseBody Map<String,Object> newsSave2(StNews news,String tempContent,String myContent,String classify,int type,
			String time,String hour,String question,String urlName,String url,
			String title1,String opA1,String opB1,String opC1,String opD1,String answer1,String uid1,
			String title2,String opA2,String opB2,String opC2,String opD2,String answer2,String uid2,
			String title3,String opA3,String opB3,String opC3,String opD3,String answer3,String uid3){
		if(news.getNewsstyle()!=3){
			if(StringUtil.isNotEmpty(news.getNewsimages())){
				news.setNewsimages(news.getNewsimages().split(",")[0]);
			}
		}
		Date d=null;
		if(StringUtil.isNotEmpty(time)){
		   if(hour.length()==1)  hour="0"+hour;
		   String t=time+" "+hour+":00:00";
		   try {
			   d=DateUtil.formatString(t, LinConstant.formatStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			newService.deleteNewsTimetask(news.getUid());
		}
		news.setDraft(1);
		if(StringUtil.isNotEmpty(url)&&StringUtil.isNotEmpty(urlName))
			news.setFullurl(urlName+","+url);
		int newsId=newService.saveNews(news,tempContent,myContent,classify,d,true);
		if("1".equals(question)){
			StNewsQuestion sq=new StNewsQuestion();
			sq.setSubject(title1);
			sq.setAnswer(answer1);
			sq.setChoice1(opA1);
			sq.setChoice2(opB1);
			sq.setChoice3(opC1);
			sq.setChoice4(opD1);
			sq.setNewsid(newsId);
			if(!StringUtil.isEmpty(uid1))
				sq.setUrid(Integer.valueOf(uid1));
			newService.saveStNewsQuestion(sq);
			StNewsQuestion sq2=new StNewsQuestion();
			sq2.setSubject(title2);
			sq2.setAnswer(answer2);
			sq2.setChoice1(opA2);
			sq2.setChoice2(opB2);
			sq2.setChoice3(opC2);
			sq2.setChoice4(opD2);
			sq2.setNewsid(newsId);
			if(!StringUtil.isEmpty(uid2))
				sq2.setUrid(Integer.valueOf(uid2));
			newService.saveStNewsQuestion(sq2);
			StNewsQuestion sq3=new StNewsQuestion();
			sq3.setSubject(title3);
			sq3.setAnswer(answer3);
			sq3.setChoice1(opA3);
			sq3.setChoice2(opB3);
			sq3.setChoice3(opC3);
			sq3.setChoice4(opD3);
			sq3.setNewsid(newsId);
			if(!StringUtil.isEmpty(uid3))
				sq3.setUrid(Integer.valueOf(uid3));
			newService.saveStNewsQuestion(sq3);
		}else if("0".equals(question)){
			newService.deleteStNewsQuestion(newsId);
		}
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("result", "success");
		result.put("newsId",newsId);
		return result;
	}
	
	//新闻类型列表
	@RequestMapping("type/list")
	public ModelAndView typeList(@RequestParam(value="page",required=false)String page,StClassify classify,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("classify", classify);
		}else{
			classify=(StClassify) session.getAttribute("classify");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=newService.findStClassify(pageBean, classify);
		List<StClassify> classifieList=(List<StClassify>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/news/type/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "新闻分类管理");
		mav.addObject("classifieList", classifieList);
		mav.addObject("mainPage", "newsType/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("type/preSave")
	public ModelAndView newsTypePreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "newsType/save.jsp");
		mav.addObject("modeName", "编辑新闻类型");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑新闻类型");
			StClassify classify = newService.getClassifyById(Integer.valueOf(id));
		    mav.addObject("classify", classify);
		}else{
			mav.addObject("actionName", "新增新闻类型");			
		}
		return mav;
	}
	
	@RequestMapping("type/save")
	public String typeSave(StClassify classify){
		newService.saveStClassify(classify);
		return "redirect:/news/type/list";
	}
	
	@RequestMapping("type/delete")
	public @ResponseBody Map<String, Object> typeDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			int status=newService.deleteStClassify(Integer.valueOf(id));
			if(status==-1){
				result.put("result", "error");
				result.put("errorInfo", "该分类下有新闻,不能删除");
			}else{
				result.put("result", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	/**
	 * 新闻,乡镇新闻,公告的推送
	 * @param news
	 * @param realcontent
	 * @param myContent
	 * @param classify
	 * @return
	 */
	@RequestMapping("push")
	public ModelAndView push(@RequestParam(value="id",required=false)String id,@RequestParam(value="type",
			required=false)Integer type,HttpServletRequest request){
		// 获取权限
	    TUser resultUser=(TUser) request.getSession().getAttribute("currentUser");
	    Integer townId=resultUser.getTownid();
	    Integer villageId=resultUser.getCountryid();
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "news/push.jsp");
		mav.addObject("modeName", "推送");
		mav.addObject("type", type);
		mav.addObject("typeId", id);
		mav.addObject("villageId", villageId);
		if(villageId!=null&&villageId.intValue()!=0){
			String name=villageService.getVillageById(villageId).getName();
			mav.addObject("villageName", name);
		}
		mav.addObject("townId", townId);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("push/save")
	public String pushSave(String typeId,String xian,String vs,String type,String villageName,
			HttpServletRequest request) throws UnsupportedEncodingException{
		int x=StringUtil.isEmpty(xian)?0:Integer.valueOf(xian);
		int t=Integer.valueOf(type);
		String title="";
		String auchor="";
		if(t==1){
			StNews news = newService.getNewsById(Integer.valueOf(typeId));
			title=news.getTitle();
			auchor=news.getKeywords(); //栏目名称  2017-09-14 
		}else if(t==2){
			StVillageNews news = villageNewsService.getVillageNewsById(Integer.valueOf(typeId));
			title=news.getTitle();
			auchor=news.getNewsauthor();
		}else if(t==3){
			StNoticeNews news = noticeNewsService.getStNoticeNewsById(Integer.valueOf(typeId));
			title=news.getTitle();
			auchor=news.getNewsauthor();
		}
		if(x==1){
			//  全推
			String time=DateUtil.formatDate(new Date(), MyConstant.updatetime);
			logger.info("app推送开始--------");
			String  str=PushtoAPP.pushAll(title, auchor, time, type, typeId);
			logger.info("app推送开始--------"+str);
		}else{
			//  列表推送
			String[] ids=vs.split("-");
			int array[] = new int[ids.length];  
			List<Integer> town=new LinkedList<Integer>();
			List<Integer> country=new LinkedList<Integer>();
			for(int i=0;i<ids.length;i++){  
			    array[i]=Integer.parseInt(ids[i]);   
			    if(array[i]>30){
			    	country.add(array[i]);
			    }else{
			    	town.add(array[i]);
			    }
			}
			// 根据area 合并查询 cids
			String time=DateUtil.formatDate(new Date(), MyConstant.updatetime);
			//List<String> list=new LinkedList<String>();
			List<String> r = userService.getPushCids(town, country);
			logger.info("列表推送开始--------");
			String  str=PushList.pushListMessage(r, title, auchor, time, type, typeId, "");
			logger.info("列表推送结束--------"+str);
		}
		StPush push=new StPush();
		push.setAuthor(auchor);
		push.setType(Integer.valueOf(type));
		push.setTypeid(Integer.valueOf(typeId));
		push.setTitle(title);
		push.setPushtime(new Date());
		push.setAddress(x==1?"全县":villageName);
		TUser resultUser=(TUser) request.getSession().getAttribute("currentUser");
		Integer townId=resultUser.getTownid();
		Integer villageId=resultUser.getCountryid();
		push.setUserid(resultUser.getId());
		push.setTown(townId);
		push.setVillage(villageId);
		userService.savePushRecord(push);
		return "redirect:/user/push/list";
	}
	
	
	// 外链轮播部分
	@RequestMapping("/link/list")
	public ModelAndView linkList(@RequestParam(value="page",required=false)String page,StLink link,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("link", link);
		}else{
			link=(StLink) session.getAttribute("link");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=linkService.getLinkList(pageBean.getPage(), 10);
		List<StLink> linkList=(List<StLink>) map.get("list");
		String[] types={"新闻","乡镇新闻","乡镇公告","服务","商品","技能","任务","招聘","出租出售"};
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/news/link/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "商务管理");
		mav.addObject("types",types);
		mav.addObject("linkList", linkList);
		mav.addObject("mainPage", "link/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/link/preSave")
	public ModelAndView linkPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "link/save.jsp");
		mav.addObject("modeName", "商务管理");
		mav.setViewName("main");
		List<StClassify> classifyList=newService.getAllClass();
		mav.addObject("classifyList", classifyList);
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑商务");
			StLink link =linkService.getStLinkById(Long.valueOf(id));
			mav.addObject("link", link);
			mav.addObject("typeList",link.getClassifyids());
		}else {
			mav.addObject("actionName", "新增商务");			
		}
		return mav;
	}
	
	@RequestMapping("/link/save")
	public String linkSave(StLink link){
		if(StringUtil.isEmpty(link.getLunbo()))
			link.setLunbo(null);
		linkService.save(link);
		return "redirect:/news/link/list";
	}
	
	
	@RequestMapping("/link/delete")
	public @ResponseBody Map<String, Object> linkDelete(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			linkService.deleteLinkById(Long.valueOf(id));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/link/setHome")
	public @ResponseBody Map<String,Object> linkSetHome(@RequestParam(value="id")String id,@RequestParam(value="value")String value){
		Map<String,Object> result=new HashMap<String, Object>();
		StLink link = linkService.getStLinkById(Long.valueOf(id));
		if(link!=null){
			if("0".equals(value)){
				link.setIsHome(1);
				link.setHotTime(new Date());
			}else{
				link.setIsHome(0);
			}
			linkService.save(link);
			result.put("result", "success");
		}else{
			result.put("result", "id不存在");
		}
		return result;
	}
	@RequestMapping(value="/upload")
	public @ResponseBody String pictureUpload(HttpServletRequest request,
			MultipartFile uploadFile,@RequestParam(required=false) Integer head){
		
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
        String oldName=uploadFile.getOriginalFilename();
        PictrueResult result=null;
        if(head!=null&&head==1&&oldName.contains("gif")){
        	result=PictrueResult.error("gif not support");
		}else{
			result=pictureService.uploadPicture(uploadFile);
		}
        String s=g.toJson(result);
        return s;
	}
	
}
