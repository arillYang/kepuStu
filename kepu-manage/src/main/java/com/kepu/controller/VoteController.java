package com.kepu.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kepu.pojo.PageBean;
import com.kepu.pojo.StLottery;
import com.kepu.pojo.StLotteryJoin;
import com.kepu.service.UserService;
import com.kepu.service.VoteService;
import com.kepu.util.DateUtil;
import com.kepu.util.LinConstant;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;


@Controller
@RequestMapping("/vote")
public class VoteController {

	protected final Log logger = LogFactory.getLog(VoteController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private VoteService voteService;
	
	//投票列表
	@RequestMapping("list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,
			StLottery lottery,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(lottery==null)
				lottery=new StLottery();
			session.setAttribute("lottery", lottery);
		}else{
			lottery=(StLottery) session.getAttribute("lottery");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		mav.addObject("modeName", "投票管理");
		Map<String,Object> map=voteService.getVoteList(lottery, pageBean.getPage(), 10);
		List<StLottery> voteList=(List<StLottery>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/vote/list", 
				new Long(total).intValue(), Integer.parseInt(page), 10);
		String staName[]={"未开始","报名中","投票中","已结束"};
		mav.addObject("pageCode", pageCode);
		mav.addObject("voteList", voteList);
		mav.addObject("staName", staName);
		mav.addObject("mainPage", "vote/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("preSave")
	public ModelAndView newsPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "vote/save.jsp");
		mav.addObject("modeName", "编辑投票");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "编辑投票");
			StLottery vote = voteService.getVoteById(Integer.valueOf(id));
			String[] str=vote.getDetail().split("#FGF#");
			mav.addObject("activityTime", str[0]);
			mav.addObject("activityIntr", str[1]);
			mav.addObject("activityObj", str[2]);
		    mav.addObject("vote", vote);
		}else{
			mav.addObject("actionName", "新增投票");			
		}
		return mav;
	}
	
	@RequestMapping("delete")
	public @ResponseBody Map<String, Object> newsDelete(@RequestParam(value="id")Integer id,
			@RequestParam(value="type")Integer type,
			HttpServletResponse response)throws Exception{
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			voteService.deleteVoteById(id,type);
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("save")
	public String voteSave(StLottery vote,
			String activityTime,String activityIntr,String activityObj,String voteimages,
			String t1,String t2,String t3,String t4) throws Exception{
		String detail=activityTime+"#FGF#"+activityIntr+"#FGF#"+activityObj;
		if(StringUtil.isNotEmpty(voteimages))
			vote.setDisplaypic(voteimages);
		vote.setDetail(detail);
		vote.setJoinstart(DateUtil.formatString(t1, LinConstant.formatStr));
		vote.setJoinend(DateUtil.formatString(t2, LinConstant.formatStr));
		vote.setVotestart(DateUtil.formatString(t3, LinConstant.formatStr));
		vote.setVoteend(DateUtil.formatString(t4, LinConstant.formatStr));
		voteService.save(vote);
		return "redirect:/vote/list";
	}
	
	
	//排名列表
	@RequestMapping("joinUser")
	public ModelAndView joinUser(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="id",required=false)Integer id,
			StLotteryJoin lotteryJoin,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			if(lotteryJoin==null)
				lotteryJoin=new StLotteryJoin();
			session.setAttribute("lotteryJoin", lotteryJoin);
			session.setAttribute("id", id);
		}else{
			lotteryJoin=(StLotteryJoin) session.getAttribute("lottery");
			id=(Integer) session.getAttribute("id");
		}
		lotteryJoin.setLotteryid(id);
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		mav.addObject("modeName", "参与者管理");
		Map<String,Object> map=voteService.getJoinerList(lotteryJoin, pageBean.getPage(), 10);
		List<StLotteryJoin> voteList=(List<StLotteryJoin>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/vote/joinUser", 
				new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("voteList", voteList);
		mav.addObject("pm", (Integer.valueOf(page)-1)*10);
		mav.addObject("mainPage", "vote/joinList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("user")
	public ModelAndView userPreSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "vote/joinSave.jsp");
		mav.addObject("modeName", "查看报名信息");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "报名信息");
			StLotteryJoin joiner = voteService.getVoteUserById(Integer.valueOf(id));
			String[] logo=joiner.getPics().split(";");
			String[] videoList=joiner.getVedios().split(",");
		    mav.addObject("joiner", joiner);
		    mav.addObject("logo", logo);
		    mav.addObject("videoList", videoList);
		}
		return mav;
	}
	
	
}
