package com.kepu.controller;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kepu.pojo.PageBean;
import com.kepu.pojo.StJob;
import com.kepu.pojo.StJobApply;
import com.kepu.pojo.StPositionClassify;
import com.kepu.pojo.StSkillContent;
import com.kepu.service.ProductService;
import com.kepu.service.SysService;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;


@Controller
@RequestMapping("/myJob")
public class JobController {

	protected final Log logger = LogFactory.getLog(JobController.class);
	
	@Autowired
	private ProductService productService;
	@Autowired
	private SysService sysService;
	// 职业类别
	@RequestMapping("classify/job/{parent}")
	public ModelAndView buildClass(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,@PathVariable Integer parent){
		ModelAndView mav=new ModelAndView();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		if(parent==null){
			parent=-1;
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findJobClassify(pageBean, parent);
		List<StPositionClassify> menuList=(List<StPositionClassify>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/myJob/classify/job/"+parent,  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "职业类别管理");
		mav.addObject("menuList", menuList);
		mav.addObject("parent", parent);
		mav.addObject("mainPage", "jobType/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("classify/job/PreSave/{parent}")
	public ModelAndView jobClassPreSave(@RequestParam(value="id",required=false)String id,@PathVariable Integer parent){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "jobType/save.jsp");
		mav.addObject("modeName", "编辑职业分类");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑职业分类");
			StPositionClassify classify = productService.getPositionClassifyById(Integer.valueOf(id));
		    mav.addObject("classify", classify);
		    mav.addObject("parent", parent);
		}else{
			mav.addObject("actionName", "新增职业分类");
			mav.addObject("parent", parent);
		}
		return mav;
	}
	
	@RequestMapping("classify/job/save")
	public String jobClassSave(StPositionClassify classify){
		productService.saveStPositionClassify(classify);
		return "redirect:/myJob/classify/job/"+classify.getParent();
	}
	
	@RequestMapping("classify/job/delete")
	public @ResponseBody Map<String, Object> jobClassDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			int r=productService.deleteStPositionClassifyById(Integer.valueOf(id));
			if(r==1)
				result.put("result", "success");
			else{
				result.put("result", "error");
				result.put("errorInfo", "存在二级分类,删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	// 招聘列表
	@RequestMapping("jobList")
	public ModelAndView jobList(@RequestParam(value="page",required=false)String page,StJob job,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("job", job);
		}else{
			job=(StJob) session.getAttribute("job");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findStJob(pageBean, job);
		List<StJob> jobList=(List<StJob>) map.get("list");
		String r = sysService.getParameter("carousel_job");
		String[] lb=r.split(",");
		LinkedList<String> list=new LinkedList<String>();
		for (String string : lb) {
			list.add(string);
		}
		int size=jobList.size();
		int[] carousel=new int[size];
		for (int i = 0; i < size; i++) {
			carousel[i]=list.contains(jobList.get(i).getUid()+"")?1:0;
		}
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/myJob/jobList",  new Long(total).intValue(), Integer.parseInt(page), 10);
		
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "招聘管理");
		mav.addObject("carousel", carousel);
		mav.addObject("jobList", jobList);
		mav.addObject("mainPage", "product/job/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("jobPreSave")
	public ModelAndView jobPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "product/job/save.jsp");
		mav.addObject("modeName", "编辑招聘");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑招聘");
			StJob job = productService.getStJobById(Integer.valueOf(id));
		    mav.addObject("job", job);
		    String logo[]=job.getDetailpics().split(",");
		    mav.addObject("logo", logo);
		}
		return mav;
	}
	
	@RequestMapping("jobDelete")
	public @ResponseBody Map<String, Object> jobDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			productService.deleteStJobById(Integer.valueOf(id));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("jobSave")
	public String jobSave(StJob job){
		productService.saveStJob(job);
		return "redirect:/myJob/jobList";
	}
	
	@RequestMapping("jobSetHome")
	public @ResponseBody Map<String,Object> jobSetHome(@RequestParam(value="id")String id){
		Map<String,Object> result=new HashMap<String, Object>();
		String r = sysService.getParameter("carousel_job");
		String[] lb=r.split(",");
		LinkedList<String> list=new LinkedList<String>();
		for (String string : lb) {
			list.add(string);
		}
		if(list.contains(id))
			list.remove(id);
		else
			list.add(id);
		StringBuffer sb=new StringBuffer();
		for (String string : list) {
			sb.append(string).append(",");
		}
		sysService.saveParameter("carousel_job", sb.length()==0?"":sb.deleteCharAt(sb.length()-1).toString());
		result.put("result", "success");
		return result;
	}
	
	// 求职列表
	@RequestMapping("jobApplyList")
	public ModelAndView jobApplyList(@RequestParam(value="page",required=false)String page,StJobApply job,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("job", job);
		}else{
			job=(StJobApply) session.getAttribute("job");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findStJobApply(pageBean, job);
		List<StJobApply> jobList=(List<StJobApply>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/myJob/jobApplyList",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "求职管理");
		mav.addObject("jobList", jobList);
		mav.addObject("mainPage", "product/jobApply/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("jobApplyPreSave")
	public ModelAndView jobApplyPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "product/jobApply/save.jsp");
		mav.addObject("modeName", "编辑求职");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑求职");
			StJobApply job = productService.getStJobApplyById(Integer.valueOf(id));
		    mav.addObject("job", job);
		}
		return mav;
	}
	
	@RequestMapping("jobApplyDelete")
	public @ResponseBody Map<String, Object> jobApplyDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			productService.deleteStJobApplyById(Integer.valueOf(id));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("jobApplySave")
	public String jobApplySave(StJobApply job){
		productService.saveStJobApply(job);
		return "redirect:/myJob/jobApplyList";
	}
}
