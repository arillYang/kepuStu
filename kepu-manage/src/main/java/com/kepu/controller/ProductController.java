package com.kepu.controller;


import java.util.Collections;
import java.util.Date;
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
import com.kepu.pojo.StAuthenticCompany;
import com.kepu.pojo.StAuthenticPeople;
import com.kepu.pojo.StBuildingClassify;
import com.kepu.pojo.StBuildingrentContent;
import com.kepu.pojo.StBuildingsellContent;
import com.kepu.pojo.StCommunityApply;
import com.kepu.pojo.StOutchainDetail;
import com.kepu.pojo.StProduct;
import com.kepu.pojo.StSkillClassify;
import com.kepu.pojo.StSkillContent;
import com.kepu.pojo.StTab;
import com.kepu.pojo.StTaskContent;
import com.kepu.service.ProductService;
import com.kepu.service.SysService;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;


@Controller
@RequestMapping("/product")
public class ProductController {

	protected final Log logger = LogFactory.getLog(ProductController.class);
	@Autowired
	private ProductService productService;
	@Autowired
	private SysService sysService;
	
	//服务首页tab列表
	@RequestMapping("index")
	public ModelAndView index(@RequestParam(value="page",required=false)String page,StTab stTab,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("stTab", stTab);
		}else{
			stTab=(StTab) session.getAttribute("stTab");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findStTab(pageBean, -1);
		List<StTab> menuList=(List<StTab>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/product/index",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "栏目管理");
		mav.addObject("menuList", menuList);
		mav.addObject("mainPage", "product/menuList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("indexPreSave")
	public ModelAndView indexPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "product/menuSave.jsp");
		mav.addObject("modeName", "编辑栏目");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑栏目");
			StTab tab = productService.getStTabById(Integer.valueOf(id));
		    mav.addObject("tab", tab);
		}
		return mav;
	}
	
	@RequestMapping("indexSave")
	public String indexSave(StTab stTab){
		productService.saveTab(stTab);
		return "redirect:/product/index";
	}
	
	//更多--> 小分类
	@RequestMapping("smallClass")
	public ModelAndView smallClass(@RequestParam(value="page",required=false)String page,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findStTab(pageBean, 3);
		List<StTab> menuList=(List<StTab>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/product/smallClass",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "栏目管理");
		mav.addObject("menuList", menuList);
		mav.addObject("mainPage", "product/more.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("smallClassPreSave")
	public ModelAndView smallClassPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "product/moreSave.jsp");
		mav.addObject("modeName", "编辑分类");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑分类");
			StTab tab = productService.getStTabById(Integer.valueOf(id));
		    mav.addObject("tab", tab);
		}
		return mav;
	}
	
	@RequestMapping("smallClassSave")
	public String smallClassSave(StTab stTab){
		productService.saveTab(stTab);
		return "redirect:/product/smallClass";
	}
	
	@RequestMapping("smallClassDelete")
	public @ResponseBody Map<String, Object> smallClassDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			int r=productService.deleteTabById(Integer.valueOf(id));
			if(r==1)
				result.put("result", "success");
			else{
				result.put("result", "error");
				result.put("errorInfo", "分类存在外链,删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	// 外链列表  
	@RequestMapping("link")
	public ModelAndView link(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="outchainId")int outchainId,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findLink(pageBean, outchainId);
		List<StOutchainDetail> linkList=(List<StOutchainDetail>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/product/link",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "外链管理");
		mav.addObject("linkList", linkList);
		mav.addObject("outchainId", outchainId+"");
		mav.addObject("mainPage", "product/link.jsp");
		mav.setViewName("main");
		return mav;
	}
	@RequestMapping("linkPreSave")
	public ModelAndView linkPreSave(@RequestParam(value="id",required=false)String id,
			@RequestParam(value="outchainId",required=false)String outchainId){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "product/linkSave.jsp");
		mav.addObject("modeName", "编辑外链");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑外链");
			StOutchainDetail outchain = productService.getStOutchainDetailById(Integer.valueOf(id));
		    mav.addObject("outchain", outchain);
		}
		 mav.addObject("outchainId", outchainId);
		return mav;
	}
	
	@RequestMapping("linkSave")
	public String linkSave(StOutchainDetail outchain,String outchainId){
		if(StringUtil.isNotEmpty(outchainId)){
			outchain.setOutchainid(Integer.valueOf(outchainId));
		}else{
			outchainId=outchain.getOutchainid()+"";
		}
		productService.saveStOutchainDetail(outchain);
		return "redirect:/product/link?outchainId="+outchainId;
	}
	
	@RequestMapping("linkDelete")
	public @ResponseBody Map<String, Object> linkDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			productService.deleteStOutchainDetail(Integer.valueOf(id));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	//商品列表
	@RequestMapping("list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,StProduct product,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("product", product);
		}else{
			product=(StProduct) session.getAttribute("product");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findStProduct(pageBean, product);
		List<StProduct> productList=(List<StProduct>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/product/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "商品管理");
		mav.addObject("productList", productList);
		mav.addObject("mainPage", "product/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("preSave")
	public ModelAndView preSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "product/save.jsp");
		mav.addObject("modeName", "编辑商品");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑商品");
			StProduct product = productService.getProductById(Integer.valueOf(id));
		    mav.addObject("product", product);
		    String logo[]=product.getDetailpics().split(",");
		    mav.addObject("logo", logo);
		}
		return mav;
	}
	
	@RequestMapping("delete")
	public @ResponseBody Map<String, Object> delete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			productService.deleteProductById(Integer.valueOf(id));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("save")
	public String save(StProduct product){
		productService.saveProduct(product);
		return "redirect:/product/list";
	}
	
	@RequestMapping("setHome")
	public @ResponseBody Map<String,Object> setHome(@RequestParam(value="id")String id){
		Map<String,Object> result=new HashMap<String, Object>();
		StProduct product = productService.getProductById(Integer.valueOf(id));
		if(product!=null){
			product.setCarousel(1-product.getCarousel());
			product.setCarouseltime(new Date());
			productService.saveProduct(product);
			result.put("result", "success");
		}else{
			result.put("result", "id不存在");
		}
		return result;
	}
	
	//  个人认证部分
	@RequestMapping(value="auth/personallist")
	public ModelAndView applylist(@RequestParam(value="page",required=false)String page,
			StAuthenticPeople authenticPeople,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("authenticPeople", authenticPeople);
		}else{
			authenticPeople=(StAuthenticPeople) session.getAttribute("authenticPeople");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findPersonallist(pageBean, authenticPeople);
		List<StAuthenticPeople>  applyList=(List<StAuthenticPeople>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/product/auth/personallist/",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "个人认证");
		mav.addObject("applyList", applyList);
		mav.addObject("mainPage", "product/auth/personalList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("auth/personalPreSave")
	public ModelAndView personalPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "product/auth/personalSave.jsp");
		mav.addObject("modeName", "个人认证审核");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "个人认证审核");
			StAuthenticPeople apply = productService.getStAuthenticPeopleById(Integer.valueOf(id));
		    mav.addObject("apply", apply);
		    String[] edu={"博士","硕士","大学","大专 ","高中 ","高中以下"};
		    mav.addObject("edu", edu);
		}
		return mav;
	}
	
	@RequestMapping("auth/personalSave")
	public String personalSave(StAuthenticPeople apply){
		productService.saveStAuthenticPeople(apply);
		return "redirect:/product/auth/personallist";
	}
	
	//  企业认证部分
	@RequestMapping(value="auth/companylist")
	public ModelAndView companylist(@RequestParam(value="page",required=false)String page,
			StAuthenticCompany authenticCompany,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("authenticCompany", authenticCompany);
		}else{
			authenticCompany=(StAuthenticCompany) session.getAttribute("authenticCompany");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findCompanylist(pageBean, authenticCompany);
		List<StAuthenticCompany>  applyList=(List<StAuthenticCompany>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/product/auth/companylist/",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "企业认证");
		mav.addObject("applyList", applyList);
		mav.addObject("mainPage", "product/auth/companyList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("auth/companyPreSave")
	public ModelAndView companyPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "product/auth/companySave.jsp");
		mav.addObject("modeName", "企业认证审核");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "企业认证审核");
			StAuthenticCompany apply = productService.getStAuthenticCompanyById(Integer.valueOf(id));
		    mav.addObject("apply", apply);
		}
		return mav;
	}
	
	@RequestMapping("auth/companySave")
	public String companySave(StAuthenticCompany apply){
		productService.saveStAuthenticCompany(apply);
		return "redirect:/product/auth/companylist";
	}
	
	// 技能列表
	@RequestMapping("skillList")
	public ModelAndView skillList(@RequestParam(value="page",required=false)String page,StSkillContent product,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("product", product);
		}else{
			product=(StSkillContent) session.getAttribute("product");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findStSkillContent(pageBean, product);
		List<StSkillContent> productList=(List<StSkillContent>) map.get("list");
		String r = sysService.getParameter("carousel_skill");
		String[] lb=r.split(",");
		LinkedList<String> list=new LinkedList<String>();
		for (String string : lb) {
			list.add(string);
		}
		int size=productList.size();
		int[] carousel=new int[size];
		for (int i = 0; i < size; i++) {
			carousel[i]=list.contains(productList.get(i).getUid()+"")?1:0;
		}
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/product/skillList",  new Long(total).intValue(), Integer.parseInt(page), 10);
		
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "技能管理");
		mav.addObject("carousel", carousel);
		mav.addObject("productList", productList);
		mav.addObject("mainPage", "product/skill/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("skillPreSave")
	public ModelAndView skillPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "product/skill/save.jsp");
		mav.addObject("modeName", "编辑技能");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑技能");
			StSkillContent product = productService.getSkillContentById(Integer.valueOf(id));
		    mav.addObject("product", product);
		    String logo[]=product.getDetailpics().split(",");
		    mav.addObject("logo", logo);
		}
		return mav;
	}
	
	@RequestMapping("skillDelete")
	public @ResponseBody Map<String, Object> skillDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			productService.deleteSkillContentById(Integer.valueOf(id));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("skillSave")
	public String skillSave(StSkillContent product){
		productService.saveSkillContent(product);
		return "redirect:/product/skillList";
	}
	
	@RequestMapping("skillSetHome")
	public @ResponseBody Map<String,Object> skillSetHome(@RequestParam(value="id")String id){
		Map<String,Object> result=new HashMap<String, Object>();
		String r = sysService.getParameter("carousel_skill");
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
		sysService.saveParameter("carousel_skill", sb.length()==0?"":sb.deleteCharAt(sb.length()-1).toString());
		result.put("result", "success");
		return result;
	}
	// 任务列表
	@RequestMapping("taskList")
	public ModelAndView taskList(@RequestParam(value="page",required=false)String page,StTaskContent product,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("product", product);
		}else{
			product=(StTaskContent) session.getAttribute("product");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findTaskContent(pageBean, product);
		List<StTaskContent> productList=(List<StTaskContent>) map.get("list");
		String r = sysService.getParameter("carousel_task");
		String[] lb=r.split(",");
		LinkedList<String> list=new LinkedList<String>();
		for (String string : lb) {
			list.add(string);
		}
		int size=productList.size();
		int[] carousel=new int[size];
		for (int i = 0; i < size; i++) {
			carousel[i]=list.contains(productList.get(i).getUid()+"")?1:0;
		}
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/product/taskList",  new Long(total).intValue(), Integer.parseInt(page), 10);
		
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "任务管理");
		mav.addObject("carousel", carousel);
		mav.addObject("productList", productList);
		mav.addObject("mainPage", "product/task/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("taskPreSave")
	public ModelAndView taskPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "product/task/save.jsp");
		mav.addObject("modeName", "编辑任务");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑任务");
			StTaskContent product = productService.getTaskContentById(Integer.valueOf(id));
		    mav.addObject("product", product);
		    String logo[]=product.getDetailpics().split(",");
		    mav.addObject("logo", logo);
		}
		return mav;
	}
	
	@RequestMapping("taskDelete")
	public @ResponseBody Map<String, Object> taskDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			productService.deleteTaskContentById(Integer.valueOf(id));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("taskSave")
	public String taskSave(StTaskContent product){
		productService.saveTaskContent(product);
		return "redirect:/product/taskList";
	}
	
	@RequestMapping("taskSetHome")
	public @ResponseBody Map<String,Object> taskSetHome(@RequestParam(value="id")String id){
		Map<String,Object> result=new HashMap<String, Object>();
		String r = sysService.getParameter("carousel_task");
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
		sysService.saveParameter("carousel_task", sb.length()==0?"":sb.deleteCharAt(sb.length()-1).toString());
		result.put("result", "success");
		return result;
	}
	
	// 出租出售
	@RequestMapping("sellList")
	public ModelAndView sellList(@RequestParam(value="page",required=false)String page,StBuildingsellContent product,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("product", product);
		}else{
			product=(StBuildingsellContent) session.getAttribute("product");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findSellContent(pageBean, product);
		List<StBuildingsellContent> productList=(List<StBuildingsellContent>) map.get("list");
		String r = sysService.getParameter("carousel_buildSell");
		String[] lb=r.split(",");
		LinkedList<String> list=new LinkedList<String>();
		for (String string : lb) {
			list.add(string);
		}
		int size=productList.size();
		int[] carousel=new int[size];
		for (int i = 0; i < size; i++) {
			carousel[i]=list.contains(productList.get(i).getUid()+"")?1:0;
		}
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/product/sellList",  new Long(total).intValue(), Integer.parseInt(page), 10);
		
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "出租出售管理");
		mav.addObject("carousel", carousel);
		mav.addObject("productList", productList);
		mav.addObject("mainPage", "product/sell/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("sellPreSave")
	public ModelAndView sellPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "product/sell/save.jsp");
		mav.addObject("modeName", "编辑出租出售");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑出租出售");
			StBuildingsellContent product = productService.getSellContentById(Integer.valueOf(id));
		    mav.addObject("product", product);
		    String logo[]=product.getDetailpics().split(",");
		    mav.addObject("logo", logo);
		}
		return mav;
	}
	
	@RequestMapping("sellDelete")
	public @ResponseBody Map<String, Object> sellDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			productService.deleteSellContentById(Integer.valueOf(id));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("sellSave")
	public String sellSave(StBuildingsellContent product){
		productService.saveSellContent(product);
		return "redirect:/product/sellList";
	}
	
	@RequestMapping("sellSetHome")
	public @ResponseBody Map<String,Object> sellSetHome(@RequestParam(value="id")String id){
		Map<String,Object> result=new HashMap<String, Object>();
		String r = sysService.getParameter("carousel_buildSell");
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
		sysService.saveParameter("carousel_buildSell", sb.length()==0?"":sb.deleteCharAt(sb.length()-1).toString());
		result.put("result", "success");
		return result;
	}
	
	// 求租求购
	@RequestMapping("rentList")
	public ModelAndView rentList(@RequestParam(value="page",required=false)String page,StBuildingrentContent product,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("product", product);
		}else{
			product=(StBuildingrentContent) session.getAttribute("product");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findRentContent(pageBean, product);
		List<StBuildingrentContent> productList=(List<StBuildingrentContent>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/product/rentList",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "求租求购管理");
		mav.addObject("productList", productList);
		mav.addObject("mainPage", "product/rent/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("rentPreSave")
	public ModelAndView rentPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "product/rent/save.jsp");
		mav.addObject("modeName", "编辑求租求购");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑求租求购");
			StBuildingrentContent product = productService.getRentContentById(Integer.valueOf(id));
		    mav.addObject("product", product);
		}
		return mav;
	}
	
	@RequestMapping("rentDelete")
	public @ResponseBody Map<String, Object> rentDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			productService.deleteRentContentById(Integer.valueOf(id));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("rentSave")
	public String rentSave(StBuildingrentContent product){
		productService.saveRentContent(product);
		return "redirect:/product/rentList";
	}
	
	// 技能分类
	@RequestMapping("classify/skill/{parent}")
	public ModelAndView skillClass(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,@PathVariable Integer parent){
		ModelAndView mav=new ModelAndView();
		//HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		if(parent==null){
			parent=-1;
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=productService.findSkillClassify(pageBean, parent);
		List<StSkillClassify> menuList=(List<StSkillClassify>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/product/classify/skill/"+parent,  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "技能分类管理");
		mav.addObject("menuList", menuList);
		mav.addObject("parent", parent);
		mav.addObject("mainPage", "skillType/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("classify/skill/PreSave/{parent}")
	public ModelAndView skillClassPreSave(@RequestParam(value="id",required=false)String id,@PathVariable Integer parent){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "skillType/save.jsp");
		mav.addObject("modeName", "编辑技能分类");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑技能分类");
			StSkillClassify classify = productService.getSkillClassifyById(Integer.valueOf(id));
		    mav.addObject("classify", classify);
		    mav.addObject("parent", parent);
		}else{
			mav.addObject("actionName", "新增技能分类");
			mav.addObject("parent", parent);
		}
		return mav;
	}
	
	@RequestMapping("classify/skill/save")
	public String skillClassSave(StSkillClassify classify){
		productService.saveStSkillClassify(classify);
		return "redirect:/product/classify/skill/"+classify.getParent();
	}
	
	@RequestMapping("classify/skill/delete")
	public @ResponseBody Map<String, Object> skillClassDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			int r=productService.deleteSkillClassifyById(Integer.valueOf(id));
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
	// 房屋分类
	@RequestMapping("classify/build/{parent}")
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
		Map<String,Object> map=productService.findBuildClassify(pageBean, parent);
		List<StBuildingClassify> menuList=(List<StBuildingClassify>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/product/classify/build/"+parent,  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "房屋分类管理");
		mav.addObject("menuList", menuList);
		mav.addObject("parent", parent);
		mav.addObject("mainPage", "buildType/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("classify/build/PreSave/{parent}")
	public ModelAndView buildClassPreSave(@RequestParam(value="id",required=false)String id,@PathVariable Integer parent){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "buildType/save.jsp");
		mav.addObject("modeName", "编辑房屋分类");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑房屋分类");
			StBuildingClassify classify = productService.getBuildClassifyById(Integer.valueOf(id));
		    mav.addObject("classify", classify);
		    mav.addObject("parent", parent);
		}else{
			mav.addObject("actionName", "新增房屋分类");
			mav.addObject("parent", parent);
		}
		return mav;
	}
	
	@RequestMapping("classify/build/save")
	public String buildClassSave(StBuildingClassify classify){
		productService.saveBuildClassify(classify);
		return "redirect:/product/classify/build/"+classify.getParent();
	}
	
	@RequestMapping("classify/build/delete")
	public @ResponseBody Map<String, Object> buildClassDelete(@RequestParam(value="id")String id,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			int r=productService.deleteBuildClassifyById(Integer.valueOf(id));
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
}
