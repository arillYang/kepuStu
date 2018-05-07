package com.kepu.controller;



import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kepu.constant.MyConstant;
import com.kepu.constant.ResultConstant;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.ProportionSetting;
import com.kepu.pojo.StAdvice;
import com.kepu.pojo.StClassify;
import com.kepu.pojo.StClassifyStatistic;
import com.kepu.pojo.StPush;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.TRole;
import com.kepu.pojo.TUser;
import com.kepu.pojo.activity.CoefficientSet;
import com.kepu.pojo.user.AdviceFeedback;
import com.kepu.pojo.user.AuthorResult;
import com.kepu.pojo.user.ClassifyResultEx;
import com.kepu.pojo.user.UserStatistic;
import com.kepu.pojo.user.active.BaseSS;
import com.kepu.pojo.user.active.CountySS;
import com.kepu.pojo.user.active.PeopleSS;
import com.kepu.pojo.user.active.TownSS;
import com.kepu.pojo.user.click.ClickResult;
import com.kepu.pojo.user.read.ReadResult;
import com.kepu.service.NewService;
import com.kepu.service.RedisService;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.service.VillageService;
import com.kepu.util.DateUtil;
import com.kepu.util.ExcelUtil;
import com.kepu.util.ExceptionUtil;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private NewService newService;
	@Autowired
	private SysService sysService;
	@Autowired
	private VillageService villageService;

	
	private static final Logger LOG = Logger.getLogger(UserController.class);
	
	@RequestMapping("/index")
	public String index(){
		return "login";
	}
	
	@RequestMapping("/NoPermission")
	public String NoPermission(){
		return "403";
	}
	
	@RequestMapping("/login")
	public String login(String userName,String password,Model model
			,HttpServletRequest request){
		String error="";
		if(StringUtil.isExistEmpty(userName,password)){
			error="用户名或密码不能为空";
			model.addAttribute("error", error);
			return "login";
		}
		TUser temp=new TUser();
		temp.setUsername(userName);
		temp.setPassword(password);
		TUser resultUser=userService.login(temp);
		if(resultUser!=null){
			if(resultUser.getState()==1){
				request.setAttribute("currentUser", resultUser);
				error="该账户被停用!";
				model.addAttribute("error", error);
				return "login";
			}
			HttpSession session=request.getSession();
			session.setAttribute("currentUser", resultUser);
			int right=0;
			int town=resultUser.getTownid();
			int  village=resultUser.getCountryid();
			if(town!=0){
				if(village==0)
					right=1;
				else
					right=2;
			}
			session.setAttribute("right", right);
			// 初始化按钮
			Map<String, Object> menuList = userService.getMenu(resultUser.getRoleid());
			session.setAttribute("initMenuList", menuList);
			return "main";
		}else{
			error="用户名或密码错误!";
			model.addAttribute("error", error);
			return "login";
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "login";
	}
	
	// 意见反馈
	@RequestMapping("advice/list")
	public ModelAndView adviceList(@RequestParam(value="page",required=false)String page,StAdvice advice,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("advice", advice);
		}else{
			advice=(StAdvice) session.getAttribute("advice");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=userService.findStAdvice(pageBean, advice);
		List<AdviceFeedback> adviceList=(List<AdviceFeedback>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/advice/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "意见反馈");
		mav.addObject("adviceList", adviceList);
		mav.addObject("mainPage", "advice/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	// 权限管理
	@RequestMapping("admin/list")
	public ModelAndView adminList(@RequestParam(value="page",required=false)String page,TUser tUser,
			HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("tUser", tUser);
		}else{
			tUser=(TUser) session.getAttribute("tUser");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=userService.findTUser(pageBean, tUser);
		List<TUser> userList=(List<TUser>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/admin/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "权限管理");
		mav.addObject("userList", userList);
		mav.addObject("mainPage", "admin/list.jsp");
		mav.setViewName("main");
		return mav;
	}
		
	@RequestMapping("admin/preSave")
	public ModelAndView adminPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "admin/save.jsp");
		mav.addObject("modeName", "权限管理");
		List<TRole> roleList = userService.getRoleList();
		mav.addObject("roleList",roleList);
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "修改管理员信息");
			TUser tUser = userService.getTuserById(Integer.valueOf(id));
			mav.addObject("tUser", tUser);
			if(tUser.getCountryid()==0){
				if(tUser.getTownid()==0){
					mav.addObject("town", -100);
					mav.addObject("village", -100);
					mav.addObject("townName","仙居县");
					mav.addObject("villageName", "-----");
				}else{
					mav.addObject("town", tUser.getTownid());
					mav.addObject("village", -100);
					mav.addObject("townName",sysService.getVillageNameById(tUser.getTownid()));
					mav.addObject("villageName", "-----");
				}
			}else{
					mav.addObject("town", tUser.getTownid());
					mav.addObject("village", tUser.getCountryid());
					mav.addObject("townName",sysService.getVillageNameById(tUser.getTownid()));
					mav.addObject("villageName", sysService.getVillageNameById(tUser.getCountryid()));
			}
		}else {
			mav.addObject("actionName", "添加管理员");			
		}
		return mav;
	}
	
	@RequestMapping("admin/save")
	public String adminSave(TUser tUser,Integer town,Integer village){
		if(StringUtil.isEmpty(tUser.getPassword()))
			tUser.setPassword(null);
		if(StringUtil.isEmpty(tUser.getUsername()))
			tUser.setUsername(null);
		if(StringUtil.isEmpty(tUser.getTruename()))
			tUser.setTruename(tUser.getTruename());
		if(town!=null){
			if(town!=-100)
				tUser.setTownid(town);
			else
				tUser.setTownid(0);
		}
		if(village!=null){
			if(village!=-100)
				tUser.setCountryid(village);
			else
				tUser.setCountryid(0);
		}
		userService.saveTUser(tUser);
		return "redirect:/user/admin/list";
	}
	
	@RequestMapping("admin/delete")
	public @ResponseBody Map<String, Object> adminDelete(@RequestParam(value="id")String id,
			@RequestParam(value="type")String type,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			userService.deleteTUserById(Integer.valueOf(id),Integer.valueOf(type));
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	// 角色管理
	@RequestMapping("role/list")
	public ModelAndView roleList(@RequestParam(value="page",required=false)String page,TRole role,
			HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("role", role);
		}else{
			role=(TRole) session.getAttribute("role");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=userService.findRole(pageBean, role);
		List<TRole> list=(List<TRole>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/role/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "角色管理");
		mav.addObject("list", list);
		mav.addObject("mainPage", "role/list.jsp");
		mav.setViewName("main");
		return mav;
	}
		
	@RequestMapping("role/preSave")
	public ModelAndView rolePreSave(@RequestParam(value="id",required=false)String id,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "role/save.jsp");
		mav.addObject("modeName", "角色管理");
		List<TRole> roleList = userService.getRoleList();
		mav.addObject("roleList",roleList);
		TUser resultUser=(TUser) request.getSession().getAttribute("currentUser");
		Map<String, Object> menuList = userService.getMenu(resultUser.getRoleid());
		mav.addObject("menuList",menuList);
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "修改角色信息");
			TRole role = userService.getTRoleById(Integer.valueOf(id));
			mav.addObject("role", role);
			String typeList = userService.getMenusByRoleId(role.getUid());
			mav.addObject("typeList", typeList);
		}else {
			mav.addObject("actionName", "添加角色");			
		}
		return mav;
	}
	
	@RequestMapping("role/save")
	public String roleSave(TRole role,String mids){
		List<Integer> menuIds=new LinkedList<Integer>();
		if(StringUtil.isNotEmpty(mids)){
			String[] m=mids.split(",");
			if(StringUtil.isNotEmpty(mids)){
				for (String str : m) {
					menuIds.add(Integer.valueOf(str));
				}
			}
		}
		userService.saveRole(role, menuIds);
		return "redirect:/user/role/list";
	}
	
	@RequestMapping("role/delete")
	public @ResponseBody Map<String, Object> roleDelete(@RequestParam(value="id")String id,
			@RequestParam(value="type")String type,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			int flag=userService.deleteRoleById(Integer.valueOf(id));
			if(flag==1)
				result.put("result", "success");
			else{
				result.put("result", "error");
				result.put("errorInfo", "存在用户为该角色,删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	
	//用户管理
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,StUser stUser,
			HttpServletRequest request,@RequestParam(value="town",required=false)Integer town,
			@RequestParam(value="village",required=false)Integer village){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(town!=null&&town!=-100){
				if(village==null||village==-100)
					stUser.setTownid(town);
				else if(village!=null&&village!=-100)
					stUser.setArea(village);
			}
			session.setAttribute("stUser", stUser);
		}else{
			stUser=(StUser) session.getAttribute("stUser");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=userService.findStUser(pageBean, stUser);
		List<StUser> userList=(List<StUser>) map.get("list");
		List<String> addressList=new LinkedList<String>();
		Map<Integer,String> map2=sysService.getAddressName();
		for (StUser stUser2 : userList) {
			addressList.add(map2.get(stUser2.getArea()));
		}
		long total=(Long) map.get("total");
		Integer manNum=Integer.valueOf((String) map.get("manNum"));
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "用户管理");
		String[] cs={"公务员","教师","医务人员","科研人员","学生","农民","工人","企业主","企业管理人员","金融服务业","律师","技术人员","自由职业"};
		String[] edu={"博士","硕士","大学","大专 ","高中 ","高中以下"};
		List<String> careerList = Arrays.asList(cs);
		List<String> eduList = Arrays.asList(edu);
		mav.addObject("userList", userList);
		mav.addObject("careerList", careerList);
		mav.addObject("eduList", eduList);
		mav.addObject("addressList", addressList);
		mav.addObject("manNum", manNum);
		mav.addObject("womanNum", total-manNum);
		mav.addObject("mainPage", "user/list.jsp");
		// 查询条件回显
		if(town!=null&&town!=-100){
			mav.addObject("town", town);
			mav.addObject("townName",sysService.getVillageNameById(town));
			if(village!=null&&village!=-100){
				mav.addObject("village", village);
				mav.addObject("villageName", sysService.getVillageNameById(village));
			}
		}
		mav.setViewName("main");
		
		return mav;
	}
	
	
	//用户管理
	@RequestMapping("export")
	public void export(StUser stUser,
			HttpServletRequest request,HttpServletResponse response,@RequestParam(value="town",required=false)Integer town,
			@RequestParam(value="village",required=false)Integer village){
		HttpSession session=request.getSession();
		if(town!=null&&town!=-100){
			if(village==null||village==-100)
				stUser.setArea(town);
			else if(village!=null&&village!=-100)
				stUser.setArea(village);
		}
		Map<String,Object> map=userService.findStUser(null, stUser);
		List<StUser> userList=(List<StUser>) map.get("list");
		Map<Integer,String> map2=sysService.getAddressName();
		long total=(Long) map.get("total");
		HSSFWorkbook wb = ExcelUtil.export(userList,map2);    
        response.setContentType("application/msexcel");
        /*response.setContentType("application/vnd.ms-excel")*/;   
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        String dateString = dateFormat.format(new Date());
        response.setHeader("Content-disposition", "attachment;filename="+dateString+".xls");    
        OutputStream ouputStream;
        try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);    
	        ouputStream.flush();    
	        ouputStream.close();
	        LOG.info("导出用户信息成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.info("导出异常");
		}
	}
	
	//用户统计
	@RequestMapping("statistic/list")
	public ModelAndView statisticList(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,@RequestParam(value="town",required=false)Integer town){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(town==null){
				town=-100;
			}
			session.setAttribute("town", town);
		}else{
			town=(Integer) session.getAttribute("town");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),100);
		String townId=town.toString();
		Map<String,Object> map;
		if("-100".equals(townId)){
			map=userService.getStatistic(pageBean);
		}else{
			map=userService.getStatisticByTownId(pageBean, townId);
		}
		List<UserStatistic> list=(List<UserStatistic>) map.get("list");
		long total=(Long) map.get("total");
		Integer count=(Integer) map.get("count");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/statistic/list",  new Long(total).intValue(), Integer.parseInt(page), 100);
		mav.addObject("list", list);
		mav.addObject("total", total);
		mav.addObject("count", count);
		mav.addObject("pageCode", pageCode);
		mav.addObject("mainPage", "user/statistic.jsp");
		// 查询条件回显
		if(town!=null&&town!=-100){
			mav.addObject("town", town);
			mav.addObject("townName",sysService.getVillageNameById(town));
		}else{
			mav.addObject("town", -100);
			mav.addObject("townName","==仙居县===");
		}
		mav.setViewName("main");
		return mav;
	}
	
	//用户统计导出
	@RequestMapping("statistic/export")
	public void statisticExport(StUser stUser,
			HttpServletRequest request,HttpServletResponse response,@RequestParam(value="town",required=false)Integer town){
		HttpSession session=request.getSession();
		if(town==null){
			town=-100;
		}
		String townId=town.toString();
		Map<String,Object> map;
		if("-100".equals(townId)){
			map=userService.getStatistic(new PageBean(1, 100));
		}else{
			map=userService.getStatisticByTownId(new PageBean(1, 100), townId);
		}
		List<UserStatistic> list=(List<UserStatistic>) map.get("list");
		HSSFWorkbook wb = ExcelUtil.exportStatistic(list);   
        response.setContentType("application/msexcel");  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        String dateString = dateFormat.format(new Date());
        response.setHeader("Content-disposition", "attachment;filename="+dateString+".xls");    
        OutputStream ouputStream;
        try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);    
	        ouputStream.flush();    
	        ouputStream.close(); 
	        LOG.info("导出用户信息统计成功");
		} catch (IOException e) {
			LOG.info("导出异常");
		} 
	}
	
	//活跃用户统计
	@RequestMapping("active/list")
	public ModelAndView activeList(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,@RequestParam(value="town",required=false)Integer town,
			@RequestParam(value="village",required=false)Integer village,
			String t1,String t2,String click1,String click2){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(town==null){
				town=-100;
			}
			if(village==null){
				village=-100;
			}
			if(StringUtil.isNotEmpty(t1)) {session.setAttribute("t1", t1);}
			if(StringUtil.isNotEmpty(t2)) {session.setAttribute("t2", t2);}
			if(StringUtil.isNotEmpty(click1)) {session.setAttribute("click1", click1);}
			if(StringUtil.isNotEmpty(click2)) {session.setAttribute("click2", click2);}
			session.setAttribute("town", town);
			session.setAttribute("village", village);
		}else{
			t1=(String) session.getAttribute("t1");
			t2=(String) session.getAttribute("t2");
			click1=(String) session.getAttribute("click1");
			click2=(String) session.getAttribute("click2");
			town=(Integer) session.getAttribute("town");
			village=(Integer) session.getAttribute("village");
		}
		String townId=town.toString();
		Map<String,Object> map=null;
		// 增加查询条件      点击量     时间
		int size=10;
		try {
			Date date1=null;
			Date date2=null;
			Integer c1=null;
			Integer c2=null;
			// 注册日期
			if(StringUtil.isNotEmpty(t1))
				date1=DateUtil.formatString(t1, MyConstant.birthday);
			if(StringUtil.isNotEmpty(t2))
				date2=DateUtil.formatString(t2, MyConstant.birthday);
			// 点击量
			if(StringUtil.isNotEmpty(click1))
				c1=Integer.valueOf(click1);
			if(StringUtil.isNotEmpty(click2))
				c2=Integer.valueOf(click2);
			if(!(-100==village.intValue())){
				// 具体到用户
				PageBean pageBean=new PageBean(Integer.parseInt(page),10);
				map=userService.getPeopleSS(date1, date2, c1, c2, pageBean,village);
				List<PeopleSS> list=(List<PeopleSS>) map.get("list");
				mav.addObject("list", list);
			}else{
				if("-100".equals(townId)){
					PageBean pageBean=new PageBean(Integer.parseInt(page),50);
					size=50;
					map=userService.getTownSS(date1, date2, c1, c2, pageBean);
					List<TownSS> list=(List<TownSS>) map.get("list");
					mav.addObject("list", list);
				}else{
					PageBean pageBean=new PageBean(Integer.parseInt(page),100);
					size=100;
					map=userService.getCountySS(date1, date2, c1, c2, pageBean,Integer.valueOf(townId));
					List<CountySS> list=(List<CountySS>) map.get("list");
					mav.addObject("list", list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer stotal;
		Integer people;
		Integer view;
		if("1".equals(page)){
			  stotal=(Integer) map.get("stotal");
			  people=(Integer) map.get("people");
			  view=(Integer) map.get("view");
			  session.setAttribute("stotal",stotal);
			  session.setAttribute("people",people);
			  session.setAttribute("view",view);
		}else{
			  stotal=(Integer) session.getAttribute("stotal");
			  people=(Integer) session.getAttribute("people");
			  view=(Integer) session.getAttribute("view");
		}
		mav.addObject("ativetotal", stotal);
		mav.addObject("ativepeople", people);
		mav.addObject("ativeview", view);
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/active/list",  new Long(total).intValue(), Integer.parseInt(page), size);
		mav.addObject("total", total);
		mav.addObject("pageCode", pageCode);
		mav.addObject("mainPage", "user/active.jsp");
		// 查询条件回显
		if(town!=null&&town!=-100){
			mav.addObject("town", town);
			mav.addObject("townName",sysService.getVillageNameById(town));
		}else{
			mav.addObject("town", -100);
			mav.addObject("townName","==仙居县===");
		}
		if(village!=null&&village!=-100){
			mav.addObject("village", village);
			mav.addObject("villageName",sysService.getVillageNameById(village));
		}
		mav.addObject("t1", StringUtil.isEmpty(t1)?"":t1);
		mav.addObject("t2", StringUtil.isEmpty(t2)?"":t2);
		mav.addObject("click1", StringUtil.isEmpty(click1)?"":click1);
		mav.addObject("click2", StringUtil.isEmpty(click2)?"":click2);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("active/export")
	public void activeExport(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,@RequestParam(value="town",required=false)Integer town,
			@RequestParam(value="village",required=false)Integer village,
			String t1,String t2,String click1,String click2,HttpServletResponse response){
		if(StringUtil.isEmpty(page)){
			page="1";
			if(town==null){
				town=-100;
			}
			if(village==null){
				village=-100;
			}
		}
		String townId=town.toString();
		Map<String,Object> map=null;
		List<BaseSS> base=new LinkedList<BaseSS>();
		try {
			Date date1=null;
			Date date2=null;
			Integer c1=null;
			Integer c2=null;
			if(StringUtil.isNotEmpty(t1))
				date1=DateUtil.formatString(t1, MyConstant.birthday);
			if(StringUtil.isNotEmpty(t2))
				date2=DateUtil.formatString(t2, MyConstant.birthday);
			if(StringUtil.isNotEmpty(click1))
				c1=Integer.valueOf(click1);
			if(StringUtil.isNotEmpty(click2))
				c2=Integer.valueOf(click2);
			BaseSS  ss;
			DecimalFormat df = new DecimalFormat("0.00");   
			if(!(-100==village.intValue())){
				// 具体到用户
				PageBean pageBean=new PageBean(Integer.parseInt(page),100000);
				map=userService.getPeopleSS(date1, date2, c1, c2, pageBean,village);
				List<PeopleSS> list=(List<PeopleSS>) map.get("list");
				for (PeopleSS peopleSS : list) {
					ss=new BaseSS();
					ss.setName(peopleSS.getName());
					ss.setPeople(peopleSS.getPeople());
					ss.setTotal(peopleSS.getTotal());
					ss.setView(peopleSS.getView());
					ss.setActive(1);
					base.add(ss);
				}
			}else{
				if("-100".equals(townId)){
					PageBean pageBean=new PageBean(Integer.parseInt(page),50);
					map=userService.getTownSS(date1, date2, c1, c2, pageBean);
					List<TownSS> list=(List<TownSS>) map.get("list");
					for (TownSS townSS : list) {
						ss=new BaseSS();
						ss.setName(townSS.getName());
						ss.setPeople(townSS.getPeople());
						ss.setTotal(townSS.getTotal());
						ss.setView(townSS.getView());
						int activeNum=townSS.getPeople();
						String active=df.format(townSS.getTotal()==0?0:activeNum*100.0/townSS.getTotal());
						ss.setActive(Double.valueOf(active));
						base.add(ss);
					}
				}else{
					PageBean pageBean=new PageBean(Integer.parseInt(page),100);
					map=userService.getCountySS(date1, date2, c1, c2, pageBean,Integer.valueOf(townId));
					List<CountySS> list=(List<CountySS>) map.get("list");
					for (CountySS townSS : list) {
						ss=new BaseSS();
						ss.setName(townSS.getName());
						ss.setPeople(townSS.getPeople());
						ss.setTotal(townSS.getTotal());
						ss.setView(townSS.getView());
						int activeNum=townSS.getPeople();
						String active=df.format(townSS.getTotal()==0?0:activeNum*100.0/townSS.getTotal());
						ss.setActive(Double.valueOf(active));
						base.add(ss);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		HSSFWorkbook wb = ExcelUtil.exportActive(base);   
        response.setContentType("application/msexcel");  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        String dateString = dateFormat.format(new Date());
        response.setHeader("Content-disposition", "attachment;filename="+dateString+".xls");    
        OutputStream ouputStream;
        try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);    
	        ouputStream.flush();    
	        ouputStream.close(); 
	        LOG.info("导出活跃用户 成功");
		} catch (IOException e) {
			LOG.info("导出异常");
		} 
	}
	//点击量分布
	@RequestMapping("click/list")
	public ModelAndView clickList(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,@RequestParam(value="town",required=false)Integer town,
			@RequestParam(value="village",required=false)Integer village,
			String t1,String t2){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(town==null){
				town=-100;
			}
			if(village==null)
				village=-100;
			if(StringUtil.isNotEmpty(t1)) {session.setAttribute("t1", t1);}
			if(StringUtil.isNotEmpty(t2)) {session.setAttribute("t2", t2);}
			session.setAttribute("town", town);
			session.setAttribute("village", village);
		}else{
			town=(Integer) session.getAttribute("town");
			village=(Integer) session.getAttribute("village");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),100);
		String townId=town.toString();
		Map<String,Object> map=null;
		try {
			Date date1=null;
			Date date2=null;
			if(StringUtil.isNotEmpty(t1))
				date1=DateUtil.formatString(t1, MyConstant.birthday);
			if(StringUtil.isNotEmpty(t2))
				date2=DateUtil.formatString(t2, MyConstant.birthday);
			if("-100".equals(townId)){
				map=userService.getClickTown(date1, date2, pageBean);
			}else if(village.intValue()==-100){
				map=userService.getClickCounty(date1, date2,pageBean,Integer.valueOf(townId));
			}else{
				map=userService.getClickPeople(date1, date2, pageBean, village);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ClickResult> list=(List<ClickResult>) map.get("list");
		Integer stotal;
		Integer stype1;
		Integer stype2;
		Integer stype3;
		Integer stype4;
		if("1".equals(page)){
			  stotal=(Integer) map.get("stotal");
			  stype1=(Integer) map.get("stype1");
			  stype2=(Integer) map.get("stype2");
			  stype3=(Integer) map.get("stype3");
			  stype4=(Integer) map.get("stype4");
			  session.setAttribute("clickstotal",stotal);
			  session.setAttribute("clickstype1",stype1);
			  session.setAttribute("clickstype2",stype2);
			  session.setAttribute("clickstype3",stype3);
			  session.setAttribute("clickstype4",stype4);
		}else{
			  stotal=(Integer) session.getAttribute("clickstotal");
			  stype1=(Integer) session.getAttribute("clickstype1");
			  stype2=(Integer) session.getAttribute("clickstype2");
			  stype3=(Integer) session.getAttribute("clickstype3");
			  stype4=(Integer) session.getAttribute("clickstype4");
		}
		mav.addObject("stotal", stotal);
		mav.addObject("stype1", stype1);
		mav.addObject("stype2", stype2);
		mav.addObject("stype3", stype3);
		mav.addObject("stype4", stype4);
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/click/list",  new Long(total).intValue(), Integer.parseInt(page), 100);
		mav.addObject("list", list);
		mav.addObject("total", total);
		mav.addObject("pageCode", pageCode);
		mav.addObject("mainPage", "user/click.jsp");
		// 查询条件回显
		if(town!=null&&town!=-100){
			mav.addObject("town", town);
			mav.addObject("townName",sysService.getVillageNameById(town));
		}else{
			mav.addObject("town", -100);
			mav.addObject("townName","==仙居县===");
		}
		if(village!=null&&village!=-100){
			mav.addObject("village", village);
			mav.addObject("villageName",sysService.getVillageNameById(village));
		}
		mav.addObject("t1", StringUtil.isEmpty(t1)?"":t1);
		mav.addObject("t2", StringUtil.isEmpty(t2)?"":t2);
		mav.setViewName("main");
		return mav;
	}
	@RequestMapping("click/export")
	public void clickExport(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,@RequestParam(value="town",required=false)Integer town,
			@RequestParam(value="village",required=false)Integer village,
			String t1,String t2,HttpServletResponse response){
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(town==null){
				town=-100;
			}
			if(village==null)
				village=-100;
			if(StringUtil.isNotEmpty(t1)) {session.setAttribute("t1", t1);}
			if(StringUtil.isNotEmpty(t2)) {session.setAttribute("t2", t2);}
			session.setAttribute("town", town);
			session.setAttribute("village", village);
		}else{
			town=(Integer) session.getAttribute("town");
			village=(Integer) session.getAttribute("village");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),100);
		String townId=town.toString();
		Map<String,Object> map=null;
		try {
			Date date1=null;
			Date date2=null;
			if(StringUtil.isNotEmpty(t1))
				date1=DateUtil.formatString(t1, MyConstant.birthday);
			if(StringUtil.isNotEmpty(t2))
				date2=DateUtil.formatString(t2, MyConstant.birthday);
			if("-100".equals(townId)){
				map=userService.getClickTown(date1, date2, pageBean);
			}else if(village.intValue()==-100){
				map=userService.getClickCounty(date1, date2,pageBean,Integer.valueOf(townId));
			}else{
				map=userService.getClickPeople(date1, date2, pageBean, village);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ClickResult> list=(List<ClickResult>) map.get("list");
		HSSFWorkbook wb = ExcelUtil.exportClick(list);   
        response.setContentType("application/msexcel");  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        String dateString = dateFormat.format(new Date());
        response.setHeader("Content-disposition", "attachment;filename="+dateString+".xls");    
        OutputStream ouputStream;
        try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);    
	        ouputStream.flush();    
	        ouputStream.close(); 
	        LOG.info("导出点击量分布成功");
		} catch (IOException e) {
			LOG.info("导出异常");
		} 
	}
	
	//用户阅读喜好
	@RequestMapping("detail/list")
	public ModelAndView detailList(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,String t1,String t2,
			@RequestParam(value="town",required=false)Integer town){
		ModelAndView mav=new ModelAndView(); 
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(town==null||town==-100){
				town=-1;
			}
			if(StringUtil.isNotEmpty(t1)) {session.setAttribute("t1", t1);}
			if(StringUtil.isNotEmpty(t2)) {session.setAttribute("t2", t2);}
			session.setAttribute("town", town);
		}else{
			town=(Integer) session.getAttribute("town");
		}
		String townId=town.toString();
		Map<String,Object> map=null;
		try {
			Date date1=null;
			Date date2=null;
			if(StringUtil.isNotEmpty(t1))
				date1=DateUtil.formatString(t1, MyConstant.birthday);
			if(StringUtil.isNotEmpty(t2))
				date2=DateUtil.formatString(t2, MyConstant.birthday);
			map=userService.getDetail(date1, date2, Integer.valueOf(townId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data[][]=(String[][]) map.get("data");
		int total=(int) map.get("total");
		List<StClassify> classList=(List<StClassify>) map.get("classList");
		List<StVillage> villageList=(List<StVillage>) map.get("villageList");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/detail/list",  new Long(total).intValue(), Integer.parseInt(page), 50);
		int sp[]=(int[]) map.get("sp");
		int stotal=(int) map.get("stotal");
		mav.addObject("sp", sp);
		mav.addObject("detailTotal", stotal);
		mav.addObject("data", data);
		mav.addObject("villageList", villageList);
		mav.addObject("classNamelist", classList);
		mav.addObject("cNum", classList.size());
		mav.addObject("total", total);
		mav.addObject("pageCode", pageCode);
		mav.addObject("mainPage", "user/detail.jsp");
		mav.addObject("town", town);
		mav.addObject("t1", StringUtil.isEmpty(t1)?"":t1);
		mav.addObject("t2", StringUtil.isEmpty(t2)?"":t2);
		mav.setViewName("main");
		// 查询条件回显
		if(town!=null&&town!=-100){
			mav.addObject("town", town);
			mav.addObject("townName",sysService.getVillageNameById(town));
		}else{
			mav.addObject("town", -100);
			mav.addObject("townName","==仙居县===");
		}
		return mav;
	}
	//用户阅读喜好 导出
	@RequestMapping("detail/export")
	public void detailExport(HttpServletRequest request,String t1,String t2,
			String townId,HttpServletResponse response){
		Map<String,Object> map=null;
		try {
			Date date1=null;
			if(townId==null||townId.equals("-100")){
				townId="-1";
			}
			Date date2=null;
			if(StringUtil.isNotEmpty(t1))
				date1=DateUtil.formatString(t1, MyConstant.birthday);
			if(StringUtil.isNotEmpty(t2))
				date2=DateUtil.formatString(t2, MyConstant.birthday);
			map=userService.getDetail(date1, date2, Integer.valueOf(townId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data[][]=(String[][]) map.get("data");
		List<StClassify> classList=(List<StClassify>) map.get("classList");
		List<StVillage> villageList=(List<StVillage>) map.get("villageList");
		HSSFWorkbook wb = ExcelUtil.exportDetail(data, villageList, classList);
        response.setContentType("application/msexcel");  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        String dateString = dateFormat.format(new Date());
        response.setHeader("Content-disposition", "attachment;filename="+dateString+".xls");    
        OutputStream ouputStream;
        try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);    
	        ouputStream.flush();    
	        ouputStream.close(); 
	        LOG.info("导出阅读喜好 成功");
		} catch (IOException e) {
			LOG.info("导出异常");
		} 
	}
	
	//用户具体点击
	@RequestMapping("read/list")
	public ModelAndView readList(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,String t1,String t2,
			String title){
		ModelAndView mav=new ModelAndView();
		if(StringUtil.isEmpty(page))
			page="1";
		Map<String,Object> map=null;
		try {
			Date date1=null;
			Date date2=null;
			if(StringUtil.isNotEmpty(t1))
				date1=DateUtil.formatString(t1, MyConstant.birthday);
			if(StringUtil.isNotEmpty(t2))
				date2=DateUtil.formatString(t2, MyConstant.birthday);
			map=userService.getReadDetail(date1, date2, title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ReadResult> list=(List<ReadResult>) map.get("list");
		long total=(Long) map.get("total");
		Integer view=(Integer) map.get("view");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/read/list",  new Long(total).intValue(), Integer.parseInt(page), 50);
		String realTitle=(String) map.get("realTitle");
		mav.addObject("realTitle", realTitle);
		mav.addObject("list", list);
		mav.addObject("view", view);
		mav.addObject("total", total);
		mav.addObject("pageCode", pageCode);
		mav.addObject("mainPage", "user/read.jsp");
		mav.addObject("title", title);
		mav.addObject("t1", StringUtil.isEmpty(t1)?"":t1);
		mav.addObject("t2", StringUtil.isEmpty(t2)?"":t2);
		mav.setViewName("main");
		return mav;
	}
	//用户具体点击 导出
	@RequestMapping("read/export")
	public void readExport(HttpServletRequest request,String t1,String t2,
			String title,HttpServletResponse response){
		Map<String,Object> map=null;
		try {
			Date date1=null;
			Date date2=null;
			if(StringUtil.isNotEmpty(t1))
				date1=DateUtil.formatString(t1, MyConstant.birthday);
			if(StringUtil.isNotEmpty(t2))
				date2=DateUtil.formatString(t2, MyConstant.birthday);
			map=userService.getReadDetail(date1, date2, title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ReadResult> list=(List<ReadResult>) map.get("list");
		HSSFWorkbook wb = ExcelUtil.exportRead(list);   
        response.setContentType("application/msexcel");  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        String dateString = dateFormat.format(new Date());
        response.setHeader("Content-disposition", "attachment;filename="+dateString+".xls");    
        OutputStream ouputStream;
        try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);    
	        ouputStream.flush();    
	        ouputStream.close(); 
	        LOG.info("导出用户具体点击 成功");
		} catch (IOException e) {
			LOG.info("导出异常");
		} 
	}
	
	// 作者统计
	@RequestMapping("author/list")
	public ModelAndView authorList(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,String t1,String t2,String name){
		ModelAndView mav=new ModelAndView(); 
		HttpSession session=request.getSession();
		Map<String,Object> map=null;
		if(StringUtil.isEmpty(page))
			page="1";
		try {
			Date date1=null;
			Date date2=null;
			if(StringUtil.isNotEmpty(t1))
				date1=DateUtil.formatString(t1, MyConstant.birthday);
			if(StringUtil.isNotEmpty(t2))
				date2=DateUtil.formatString(t2, MyConstant.birthday);
			PageBean pageBean=new PageBean(Integer.parseInt(page),10);
			map=userService.getAuthorStatistic(date1, date2, name,pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<AuthorResult> list=(List<AuthorResult>) map.get("list");
		AuthorResult t=(AuthorResult) map.get("t");
		Long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/author/list",  total.intValue(), Integer.parseInt(page), 10);
		mav.addObject("list", list);
		mav.addObject("total", total);
		mav.addObject("t", t);
		mav.addObject("pageCode", pageCode);
		mav.addObject("mainPage", "user/author.jsp");
		mav.addObject("t1", StringUtil.isEmpty(t1)?"":t1);
		mav.addObject("t2", StringUtil.isEmpty(t2)?"":t2);
		mav.addObject("name", StringUtil.isEmpty(name)?"":name);
		mav.setViewName("main");
		return mav;
	}
	
	// 新闻分类统计
	@RequestMapping("classify/list")
	public ModelAndView classifyList(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,String name,String week,String week2){
		ModelAndView mav=new ModelAndView(); 
		Map<String,Object> map=null;
		if(StringUtil.isEmpty(page))
			page="1";
		try {
			Calendar calendar = Calendar.getInstance();
			int cyear=-1;
			int cweek=-1;
			int cyear2=calendar.get(Calendar.YEAR);
			int cweek2=calendar.get(Calendar.WEEK_OF_YEAR);
			int day=calendar.get(Calendar.DAY_OF_WEEK);
			if(day==1)
				cweek2--;
			if(StringUtil.isNotEmpty(week)){
				String ws[]=week.split("-W");
				 cyear=Integer.valueOf(ws[0]);
				 cweek=Integer.valueOf(ws[1]);
				 mav.addObject("week", week);
			}
			if(StringUtil.isNotEmpty(week2)){
				String ws[]=week2.split("-W");
				// 2018 3     2018 4   2019 5
				int tp1=Integer.valueOf(ws[0]);
				int tp2=Integer.valueOf(ws[1]);
				if(tp1>cyear2){
					cyear2=tp1;
				}else if(tp1==cyear2){
					cweek2=Math.min(tp2, cweek2);
				}else {
					cyear2=tp1;
					cweek2=tp2;
				}
				 mav.addObject("week2", week2);
			}
			map=userService.getMyWeekClassifyStatistic(name,cyear,cweek,cyear2,cweek2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//List<ClassifyResultEx> list=(List<ClassifyResultEx>) map.get("list");
		List<StClassifyStatistic> list=(List<StClassifyStatistic>) map.get("list");
		int total=(int) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/classify/list",  total, Integer.parseInt(page), 100);
		/*int classifyNum=(int) map.get("classifyNum");
		int classifyView=(int) map.get("classifyView");*/
		mav.addObject("list", list);
		mav.addObject("total", total);
		mav.addObject("pageCode", pageCode);
		/*mav.addObject("classifyNum", classifyNum);
		mav.addObject("classifyView", classifyView);*/
		mav.addObject("mainPage", "user/classify.jsp");
		mav.addObject("name", StringUtil.isEmpty(name)?"":name);
		mav.setViewName("main");
		/*List<StClassifyStatistic> last = userService.getLastWeekClassifyStatistic(name);
		Map<Integer,StClassifyStatistic> lmap=new HashMap<Integer, StClassifyStatistic>();
		for (StClassifyStatistic stClassifyStatistic : last) {
			lmap.put(stClassifyStatistic.getClassfyid(), stClassifyStatistic);
		}
		mav.addObject("lmap", lmap);*/
		return mav;
	}
	
	
	//用户推送
	@RequestMapping("push/list")
	public ModelAndView pushList(@RequestParam(value="page",required=false)String page,StPush push,
			HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("push", push);
		}else{
			push=(StPush) session.getAttribute("push");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		TUser resultUser=(TUser) request.getSession().getAttribute("currentUser");
		Integer townId=resultUser.getTownid();
		Integer villageId=resultUser.getCountryid();
		Map<String, Object> map = userService.getPushRecord(pageBean,push,townId,villageId);
		List<StPush> list= (List<StPush>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/user/push/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("list", list);
		mav.addObject("total", total);
		mav.addObject("pageCode", pageCode);
		mav.addObject("mainPage", "user/pushRecord.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="getAddress")
	public @ResponseBody Object getAddress(@RequestParam(value="parentId",required=false)Integer parentId){
		try {
			List<StVillage> list = userService.getAddressByParent(parentId);
			return list;
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	@RequestMapping(value="getProvinces")
	public @ResponseBody Object getProvinces(HttpServletRequest request){
		try {
			// 获取权限
		    TUser resultUser=(TUser) request.getSession().getAttribute("currentUser");
		    Integer townId=resultUser.getTownid();
		    Integer villageId=resultUser.getCountryid();
			List<StVillage> list = userService.getAddressByParent(-1);
			List<String> r=new LinkedList<String>();
			if(townId==0&&villageId==0){
				for (StVillage stVillage : list) {
					r.add(stVillage.getId()+"");
				}
			}else{
				r.add(townId.toString());
			}
			return r;
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	@RequestMapping(value="getLocalDataCitiesList")
	public @ResponseBody Object getLocalDataCitiesList(HttpServletRequest request){
		try {
			// 获取权限
		    TUser resultUser=(TUser) request.getSession().getAttribute("currentUser");
		    Integer townId=resultUser.getTownid();
		    Integer villageId=resultUser.getCountryid();
		    boolean f=townId==0&&villageId==0;
			List<StVillage> list = userService.getAddressByParent(null);
			Map<String,Object> map=new HashMap<String, Object>();
			for (StVillage stVillage : list) {
				boolean p=false;
				if(f){
					p=true;
				}else{
					if(townId!=0&&villageId==0){
						if(stVillage.getId().intValue()==townId||stVillage.getParent().intValue()==townId){
							p=true;
						}
					}else if(townId!=0&&villageId!=0){
						if(stVillage.getId().intValue()==townId||stVillage.getId().intValue()==villageId){
							p=true;
						}
					}
				}
				if(p){
					LinkedList<String> ls=new LinkedList<String>();
					ls.add(stVillage.getName());
					map.put(stVillage.getId().toString(),ls);
				}
			}
			return map;
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	@RequestMapping(value="getRelations")
	public @ResponseBody Object getRelations(HttpServletRequest request){
		try {
			// 获取权限
		    TUser resultUser=(TUser) request.getSession().getAttribute("currentUser");
		    Integer townId=resultUser.getTownid();
		    Integer villageId=resultUser.getCountryid();
		    boolean f=townId==0&&villageId==0;
			List<StVillage> list = null;
			if(townId==0)
				list=userService.getAddressByParent(-1);
			else{
				list=new LinkedList<StVillage>();
				StVillage v=new StVillage();
				v.setId(townId);
				list.add(v);
			}
			Map<String,Object> map=new HashMap<String, Object>();
			for (StVillage stVillage : list) {
				LinkedList<String> ls=new LinkedList<String>();
				List<StVillage> list2 = null;
				if(villageId==0)
					list2=userService.getAddressByParent(stVillage.getId());
				else{
					list2=new LinkedList<StVillage>();
					StVillage v=new StVillage();
					v.setId(villageId);
					list2.add(v);
				}
				for (StVillage stVillage2 : list2) {
					ls.add(stVillage2.getId().toString());
				}
				map.put(stVillage.getId().toString(),ls);
			}
			return map;
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	//  系统设置(关于我们，开机图，引导图，)
	@RequestMapping("/system/preSave")
	public ModelAndView systemPreSave(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "system/save.jsp");
		mav.addObject("modeName", "系统设置");
		mav.setViewName("main");
		mav.addObject("actionName", "系统设置");
		String launchPage=sysService.getLaunchPage();
		List<String> guideList = sysService.getGuidePage();
		String coefficient=sysService.getParameter("coefficient");
		if(StringUtil.isNotEmpty(coefficient)){
			String cf[]=coefficient.split(",");
			CoefficientSet cs=new CoefficientSet();
			cs.setCoefficient_1(cf[0]);
			cs.setCoefficient_2(cf[1]);
			cs.setCoefficient_3(cf[2]);
			cs.setCoefficient_4(cf[3]);
			cs.setCoefficient_5(cf[4]);
			cs.setCoefficient_6(cf[5]);
			cs.setCoefficient_7(cf[6]);
			mav.addObject("cf", cs);
		}
		//积分
		ProportionSetting propr=sysService.findProportionSetting(1);
		mav.addObject("propr", propr);
		
		mav.addObject("launchPage", launchPage);
		mav.addObject("guideList", guideList);
		String content=sysService.getParameter("content");
		List<String> logo=Arrays.asList(content.split(","));
		mav.addObject("logo", logo);
		mav.addObject("temp", sysService.getParameter("temp"));
		mav.addObject("need_approve", sysService.getParameter("need_approve"));
		return mav;
	}
	
	@RequestMapping("/system/save")
	public String systemSave(String launchPage,String guides,
			String about_us,String temp,String need_approve,
			CoefficientSet set){
		sysService.SaveSystemParams(launchPage, guides,about_us,temp,need_approve,set);
		return "main";
	}
	
	//  行业设置
	@RequestMapping("/industry/preSave")
	public ModelAndView industryPreSave(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "jobType/industrySave.jsp");
		mav.addObject("modeName", "行业设置");
		mav.setViewName("main");
		mav.addObject("actionName", "行业设置");
		String industry=sysService.getParameter("industry");
		if(StringUtil.isEmpty(industry)){
			industry="";
		}
		mav.addObject("industry", industry);
		return mav;
	}
	
	@RequestMapping("/industry/save")
	public String industrySave(String industry){
		sysService.saveParameter("industry", industry);
		return "main";
	}
	
	
	
	@RequestMapping(value="init")
	public @ResponseBody Object test() throws Exception{
		userService.readStatistics();
		return KePuResult.ok(ResultConstant.code_ok, "初始化成功", "");
	}
	// 用户查询
	@RequestMapping("findUser")
	public ModelAndView findUser(HttpServletRequest request){
		String orderid=request.getParameter("user_id");
		ModelAndView mav=new ModelAndView();
		
		
		
		
	    mav.addObject("mainPage","user/user_details.jsp");
	    mav.setViewName("main");
		return mav;
	}
	/**
	 * 返回到会员列表里面
	 * @param request
	 * @return
	 */
	@RequestMapping("selectReturn")
	public String selectReturn(){
		return "redirect:list";
	}
	
	// 积分
/*	@RequestMapping("findProportionSetting")
	public ModelAndView findProportionSetting(HttpServletRequest request){
		String setting_id=request.getParameter("setting_id");
		ProportionSetting propr=sysService.findProportionSetting(Integer.parseInt(setting_id));
		ModelAndView mav=new ModelAndView();
		mav.addObject("propr", propr);
	    mav.addObject("mainPage","user/user_details.jsp");
	    mav.setViewName("main");
		return mav;
	}*/
	
	// 修改积分
		@RequestMapping("updateProportionSetting")
		public ModelAndView updateProportionSetting(ProportionSetting proport,HttpServletRequest request){
			
			int propr=sysService.updateProportionSetting(proport);
			ModelAndView mav=new ModelAndView();
			String k=(propr>1)?"成功":"失败";
			mav.addObject("data", k);
		    mav.addObject("mainPage","user/user_details.jsp");
		    mav.setViewName("main");
			return mav;
		}
}
