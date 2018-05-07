package com.kepu.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.kepu.mapper.ScoreDetailMapper;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.ScoreDetail;
import com.kepu.pojo.StUser;
import com.kepu.service.ScoreDetailService;
import com.kepu.service.UserService;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;

@Controller
@RequestMapping("/scoreDetail")
public class ScoreDetailController {
	@Autowired
	private ScoreDetailMapper sdmapper;
	@Autowired
	private ScoreDetailService sdService;
	@Autowired
	private UserService userService;

	protected final Log logger = LogFactory.getLog(ScoreDetailController.class);

	// 查询积分明细列表
	@SuppressWarnings("unchecked")
	@RequestMapping("scoreDetailList")
	public ModelAndView orderList(@RequestParam(value = "page", required = false) String page, ScoreDetail scoreDetail,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
			session.setAttribute("scoreDetail", scoreDetail);
		} else {
			scoreDetail = (ScoreDetail) session.getAttribute("scoreDetail");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
		Map<String, Object> map = sdService.findScoreDetail(pageBean, scoreDetail);
		List<ScoreDetail> ScoreDetailList = (List<ScoreDetail>) map.get("list");
		long total = (Long) map.get("total");
		String pageCode = PageUtil.getPagation(request.getContextPath() + "/scoreDetail/scoreDetailList",
				new Long(total).intValue(), Integer.parseInt(page), 10);
		if(null!=request.getParameter("find")&&""!=request.getParameter("find")){
			mav.addObject("find",request.getParameter("find"));
		}
		mav.addObject("pageCode", pageCode);
		mav.addObject("ScoreDetailList", ScoreDetailList);
		mav.addObject("mainPage", "scoreDetail/scoreDetailList.jsp");
		mav.setViewName("main");
		return mav;
	}

	// 查询积分明细
	@RequestMapping("findScoreDetailById")
	public ModelAndView findOrderBy(HttpServletRequest request) {
		String ScoreDetailId = request.getParameter("ScoreDetailId");
		ModelAndView mav = new ModelAndView();
		ScoreDetail scoreDetail = sdmapper.selectByPrimaryKey(Integer.parseInt(ScoreDetailId));
		// 通过购买者的ID，查询对应的用户名
		StUser stUser = userService.getUserById(scoreDetail.getBuyUserId()); // 查找用户信息
		if (null != stUser.getNickname()) {
			mav.addObject("BuyUserName", stUser.getNickname());
		}
		mav.addObject("scoreDetail", scoreDetail);
		mav.addObject("mainPage", "scoreDetail/scoreDetail.jsp");
		mav.setViewName("main");
		return mav;
	}

	// 修改积分明细
	@RequestMapping("PreUpdateScoreDetail")
	public ModelAndView PreUpdateScoreDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int ScoreDetailId = Integer.parseInt(request.getParameter("ScoreDetailId"));
		ScoreDetail scoreDetail2 = sdmapper.selectByPrimaryKey(ScoreDetailId);
		StUser stUser = userService.getUserById(scoreDetail2.getBuyUserId()); // 查找用户信息
		if (null != stUser.getNickname()) {
			mav.addObject("BuyUserName", stUser.getNickname());
		}
		mav.addObject("scoreDetail", scoreDetail2);
		mav.addObject("mainPage", "scoreDetail/scoreDetailUpdate.jsp");
		mav.setViewName("main");
		return mav;
	}

	// 修改积分明细
	@RequestMapping("UpdateScoreDetail")
	public String UpdateOrder(ScoreDetail scoreDetail, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int scoreDetail2 = sdmapper.updateByPrimaryKeySelective(scoreDetail);
		return "redirect:scoreDetailList";
	}

	// 删除积分明细
	@RequestMapping("deleteScoreDetail")
	public ModelAndView deleteOrder(@RequestParam(value = "id", required = false) String id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		return mav;
	}

	/**
	 * 返回到ajax
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("updateRebateRatio")
	public String updateRebateRatio(double ratio) {
		int yes = sdmapper.updateRebateRatio(ratio);
		if (yes == 1) {
			return "1";
		} else {
			return "0";
		}
	}
}
