package com.kepu.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kepu.constant.MyConstant;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StActivityRecord;
import com.kepu.pojo.activity.ActivityResult;
import com.kepu.service.ActivityService;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.util.DateUtil;
import com.kepu.util.ExcelUtil;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;


@Controller
@RequestMapping("/activity")
public class ActivityController {

	protected final Log logger = LogFactory.getLog(ActivityController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private SysService sysService;
	@Autowired
	private ActivityService activityService;
	//阅读竞赛统计
	@RequestMapping("list")
	public ModelAndView statisticList(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,@RequestParam(value="town",required=false)Integer town,
			@RequestParam(value="t1",required=false)String t1,
			@RequestParam(value="t2",required=false)String t2,
			@RequestParam(value="paiming",required=false)Integer paiming,
			@RequestParam(value="mobile",required=false)String mobile){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(town==null){
				town=-100;
			}
			session.setAttribute("town", town);
			session.setAttribute("t1", t1);
			session.setAttribute("t2", t2);
			session.setAttribute("paiming", paiming);
			session.setAttribute("mobile", mobile);
		}else{
			town=(Integer) session.getAttribute("town");
			t1=(String) session.getAttribute("t1");
			t2=(String) session.getAttribute("t2");
			paiming=(Integer) session.getAttribute("paiming");
			mobile=(String) session.getAttribute("mobile");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		String townId=town==null?"-100":town.toString();
		Map<String,Object> map=new HashMap<String, Object>();
		if(!"-100".equals(townId)){
			map.put("town", townId);
		}
		if(StringUtil.isNotEmpty(mobile)){
			map.put("mobile", "%"+mobile+"%");
			mav.addObject("mobile", mobile);
		}
		Date date1=null;
		Date date2=null;
		try {
			if(StringUtil.isNotEmpty(t1)){
				date1=DateUtil.formatString(t1+" 00:00:00", MyConstant.updatetime);
				mav.addObject("t1", t1);
				map.put("d1", date1);
			}
			if(StringUtil.isNotEmpty(t2)){
				date2=DateUtil.formatString(t2+" 23:59:59", MyConstant.updatetime);
				mav.addObject("t2", t2);
				map.put("d2", date2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(paiming!=null){
			map.put("paiming", paiming);
			mav.addObject("paiming", paiming);
		}
		Map<String, Object> map2 = activityService.getStatistic(pageBean, map);
		List<ActivityResult> list=(List<ActivityResult>) map2.get("list");
		List<String> addressList=new LinkedList<String>();
		Map<Integer,String> map3=sysService.getAddressName();
		for (ActivityResult activityResult : list) {
			addressList.add(map3.get(activityResult.getVillage()));
		}
		long total=(Long) map2.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/activity/list",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("list", list);
		mav.addObject("page", Integer.valueOf(page));
		mav.addObject("addressList", addressList);
		mav.addObject("total", total);
		mav.addObject("pageCode", pageCode);
		mav.addObject("mainPage", "score/statistic.jsp");
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
	
	//详细列表
	@RequestMapping("detail")
	public ModelAndView detail(@RequestParam(value="page",required=false)String page,
			HttpServletRequest request,@RequestParam(value="t1",required=false)String t1,
			@RequestParam(value="t2",required=false)String t2,
			@RequestParam(value="id",required=false)Integer id){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		Date date1=null;
		Date date2=null;
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("id", id);
			if(StringUtil.isNotEmpty(t1)){
				session.setAttribute("t1", t1);
			}
			if(StringUtil.isNotEmpty(t2)){
				session.setAttribute("t2", t2);
			}
		}else{
			id=(Integer) session.getAttribute("id");
			t1=(String) session.getAttribute("t1");
			t2=(String) session.getAttribute("t2");
		}
		try {
			if(StringUtil.isNotEmpty(t1)){
				date1=DateUtil.formatString(t1+" 00:00:00", MyConstant.updatetime);
			}
			mav.addObject("t1", t1==null?"":t1);
			if(StringUtil.isNotEmpty(t2)){
				date2=DateUtil.formatString(t2+" 23:59:59", MyConstant.updatetime);
			}
			mav.addObject("t2", t2==null?"":t2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=activityService.getDetail(pageBean, id,date1,date2);
		List<StActivityRecord> list=(List<StActivityRecord>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/activity/detail",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("list", list);
		mav.addObject("userId", id);
		mav.addObject("mainPage", "score/detail.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	//用户统计导出
	@RequestMapping("export")
	public void statisticExport(
			HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="town",required=false)Integer town,
			@RequestParam(value="t1",required=false)String t1,
			@RequestParam(value="t2",required=false)String t2,
			@RequestParam(value="paiming",required=false)Integer paiming,
			@RequestParam(value="mobile",required=false)String mobile){
		HttpSession session=request.getSession();
		if(town==null){
			town=-100;
		}
		String townId=town.toString();
		Map<String,Object> map=new HashMap<String, Object>();
		if(!"-100".equals(townId)){
			map.put("town", townId);
		}
		if(StringUtil.isNotEmpty(mobile)){
			map.put("mobile", "%"+mobile+"%");
		}
		Date date1=null;
		Date date2=null;
		try {
			if(StringUtil.isNotEmpty(t1)){
				date1=DateUtil.formatString(t1+" 00:00:00", MyConstant.updatetime);
				map.put("d1", date1);
			}
			if(StringUtil.isNotEmpty(t2)){
				date2=DateUtil.formatString(t2+" 23:59:59", MyConstant.updatetime);
				map.put("d2", date2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(paiming!=null){
			map.put("paiming", paiming);
		}
		Map<String, Object> map2 = activityService.getStatistic(null, map);
		List<ActivityResult> list=(List<ActivityResult>) map2.get("list");
		Map<Integer,String> map3=sysService.getAddressName();
		HSSFWorkbook wb = ExcelUtil.exportReading(list, map3);  
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
	        logger.info("导出阅读竞赛成功");
		} catch (IOException e) {
			logger.info("导出异常");
		} 
	}
	
	
	
}
