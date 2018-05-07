package com.kepu.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kepu.mapper.OrderInfoMapper;
import com.kepu.mapper.StExchangeBalanceMapper;
import com.kepu.mapper.StIntegralProductMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.mapper.WithdrawCashMapper;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StExchangeBalance;
import com.kepu.pojo.StIntegralProduct;
import com.kepu.pojo.StNews;
import com.kepu.pojo.StUser;
import com.kepu.pojo.TUser;
import com.kepu.pojo.WithdrawCash;
import com.kepu.service.IntegralProductService;
import com.kepu.service.UserService;
import com.kepu.service.WithdrawCashService;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;

@Controller
@RequestMapping("/withdrawCash")
public class WithdrawCashController {
	@Autowired
	private OrderInfoMapper orderInfomapper;
	@Autowired
	private WithdrawCashService withdrawCashService;
	@Autowired
	private UserService userService;
	@Autowired
	private StUserMapper stUserMapper;
	@Autowired
	private WithdrawCashMapper withdrawCashMapper;

	@Autowired
	private IntegralProductService integralProductService;

	@Autowired
	private StIntegralProductMapper integralProductDao;
	
	
	
	@Autowired
	private StExchangeBalanceMapper stExchangeBalanceMapper;

	protected final Log logger = LogFactory.getLog(WithdrawCashController.class);

	// 查询订单列表
	@RequestMapping("withdrawCashList")
	public ModelAndView withdrawCashList(@RequestParam(value = "page", required = false) String page,
			WithdrawCash withdrawCash, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
			session.setAttribute("withdrawCash", withdrawCash);
		} else {
			withdrawCash = (WithdrawCash) session.getAttribute("withdrawCash");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
		Map<String, Object> map = withdrawCashService.findWithdrawCash(pageBean, withdrawCash);
		List<WithdrawCash> withdrawCashList = (List<WithdrawCash>) map.get("list");
		System.out.println("------------------------------" + withdrawCashList.size());
		long total = (Long) map.get("total");
		String pageCode = PageUtil.getPagation(request.getContextPath() + "/withdrawCash/withdrawCashList",
				new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("withdrawCashList", withdrawCashList);
		mav.addObject("mainPage", "withdrawCash/withdrawCashList.jsp"); 
		mav.setViewName("main");
		return mav;
	}

	// 查询积分明细
	@RequestMapping("findwithdrawCashByUserId")
	public ModelAndView findWithdrawCashByUserId(HttpServletRequest request, WithdrawCash withdrawCash) {
		Integer buyUserId = Integer.valueOf(request.getParameter("buyUserId"));
		ModelAndView mav = new ModelAndView();
		WithdrawCash withdrawCash2 = withdrawCashMapper.selectWithdrawCashByUserId(buyUserId);
		// 通过购买者的ID，查询对应的用户名
		StUser stUser = userService.getUserById(buyUserId); // 查找用户信息
		if (null != stUser.getNickname()) {
			mav.addObject("BuyUserName", stUser.getNickname());
		}
		mav.addObject("withdrawCash", withdrawCash2);
		mav.addObject("mainPage", "withdrawCash/withdrawCashDetail.jsp");
		mav.setViewName("main");
		return mav;
	}

	@RequestMapping("findwithdrawCashBywcId")
	public ModelAndView findwithdrawCashBywcId(HttpServletRequest request, WithdrawCash withdrawCash) {
		Integer wcId = Integer.valueOf(request.getParameter("wcId"));
		Integer buyUserId = Integer.valueOf(request.getParameter("buyUserId"));
		ModelAndView mav = new ModelAndView();
		WithdrawCash withdrawCash2 = withdrawCashMapper.selectByPrimaryKey(wcId);
		// 通过购买者的ID，查询对应的用户名
		StUser stUser = userService.getUserById(buyUserId); // 查找用户信息
		if (null != stUser.getNickname()) {
			mav.addObject("BuyUserName", stUser.getNickname());
		}
		mav.addObject("withdrawCash", withdrawCash2);
		mav.addObject("mainPage", "withdrawCash/withdrawCashDetail.jsp");
		mav.setViewName("main");
		return mav;
	}

		
	// 查询数据
	@RequestMapping("integralmall")
	public ModelAndView integralmall(@RequestParam(value = "page", required = false) String page,StIntegralProduct stIntegralProduct,
			HttpServletRequest request) { 
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		TUser tuser = (TUser) session.getAttribute("currentUser");
		stIntegralProduct.setId(tuser.getId());
		System.err.println(tuser.getId());
	//	List<StIntegralProduct> list = integralProductService.findIntegralProductlist(tuser.getId());
		if (StringUtil.isEmpty(page)) {
			page = "1";
			session.setAttribute("stIntegralProduct", stIntegralProduct);
		}else {
			stIntegralProduct = (StIntegralProduct) session.getAttribute("stIntegralProduct");
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
		Map<String, Object> map = integralProductService.findWithdrawCash(pageBean, stIntegralProduct);
		List<StIntegralProduct> list = (List<StIntegralProduct>) map.get("list");
		System.err.println("---------------"+list.size());
		long total = (Long) map.get("total");
		String pageCode = PageUtil.getPagation(request.getContextPath() + "/withdrawCash/integralmall",
				new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getDisplaythepicture() == null || list.get(i).getDisplaythepicture().equals("")) {
					list.get(i).setDisplaythepicture("");
				}
				String[] p = list.get(i).getDisplaythepicture().split(",");

				list.get(i).setDisplaythepicture(p[0]);
			}
			mav.addObject("list", list);
		}
		mav.addObject("modeName", "积分商城");
		mav.addObject("mainPage", "withdrawCash/integralmall.jsp");
		mav.setViewName("main");
		return mav;
	}

	// 模糊查询数据
	@RequestMapping("findintegralmall")
	public ModelAndView findintegralmall(String name, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		TUser tuser = (TUser) session.getAttribute("currentUser");
		List<StIntegralProduct> st = integralProductService.findintegralmall(name);
		if (st.size() > 0) {
			mav.addObject("list", st);
		}
		mav.addObject("modeName", "积分商城");
		mav.addObject("mainPage", "withdrawCash/integralmall.jsp");
		mav.setViewName("main");
		return mav;

	}

	// 积分商品添加跳转
	@RequestMapping("preSave")
	public ModelAndView preSave() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("modeName", "添加积分商品");
		mav.addObject("mainPage", "withdrawCash/save.jsp");
		mav.setViewName("main");
		return mav;
	}

	// 积分商品添加
	@RequestMapping("insertIntegral")
	public String insertIntegral(StIntegralProduct st, String qita) {
		if (st.getFreight().equals("70")) {
			st.setFreight(qita);
		}
		st.setCreatetime(new Date());
		/*
		 * if(StringUtil.isNotEmpty(st.getDisplaythepicture())){ StDictionaryExample
		 * example=new StDictionaryExample(); StDictionaryExample.Criteria
		 * criteria=example.createCriteria(); criteria.andDicKeyEqualTo("content");
		 * List<StDictionary> list = stDictionaryMapper.selectByExample(example);
		 * if(list.size()!=0){ StDictionary dic=list.get(0); dic.setDicValue(about_us);
		 * dic.setUpdatetime(new Date());
		 * stDictionaryMapper.updateByExampleSelective(dic, example); } }
		 */

		int i = integralProductDao.insert(st);
		if (i > 0) {
			return "redirect:/withdrawCash/integralmall";
		}
		return null;
	}

	// 删除积分商品
	@RequestMapping("integralDelete")
	@ResponseBody
	public ModelAndView integralDelete(@RequestParam(value = "id", required = true) int id, WithdrawCash withdrawCash) {
		ModelAndView mav = new ModelAndView();
		integralProductDao.deleteByPrimaryKey(id);
		mav.addObject("mainPage", "withdrawCash/save.jsp");
		mav.setViewName("main");
		return mav;
	}

	// 修改积分商品跳转
	@RequestMapping("updatepreSave")
	public ModelAndView updatepreSave(@RequestParam(value = "id", required = true) int id) {
		ModelAndView mav = new ModelAndView();
		StIntegralProduct st = integralProductDao.selectByPrimaryKey(id);

		String[] p = null;
		if (st.getDisplaythepicture() == null || st.getDisplaythepicture().equals("")) {
			st.setDisplaythepicture("");

		} else {
			p = st.getDisplaythepicture().split(",");
		}

		mav.addObject("st", st);
		mav.addObject("logo", p);
		mav.addObject("modeName", "修改积分商品");
		mav.addObject("mainPage", "withdrawCash/updateintegralmall.jsp");
		mav.setViewName("main");
		return mav;
	}

	// 积分商品修改
	@RequestMapping("updateinsertIntegral")
	public String updateinsertIntegral(StIntegralProduct st, String qita, String displaythepicture, StNews news) {
		if (st.getFreight().equals("70")) {
			st.setFreight(qita);
		}
		st.setCreatetime(new Date());
		int i = integralProductDao.updateByPrimaryKeySelective(st);
		System.err.println(i);
		if (i > 0) {
			return "redirect:/withdrawCash/integralmall";
		}
		return null;
	}
	
	
	//兑换余额
	@RequestMapping("findexchange")
	public ModelAndView findexchange()
	{
		ModelAndView mav=new ModelAndView(); 
		StExchangeBalance  stexchang= 	stExchangeBalanceMapper.selectByPrimaryKey(1);
		stexchang.getExchange();//积分比例
		mav.addObject("stexchang", stexchang);
		mav.addObject("modeName", "兑换余额比例");
    	mav.addObject("mainPage", "withdrawCash/findexchange.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	//修改兑换比例
	@RequestMapping("updatexchange")
	@ResponseBody
	public int updatexchange(@RequestParam(value="green",required=true)int  green)
	{
		StExchangeBalance  st=new StExchangeBalance();
		st.setId(1);
		st.setExchange(String.valueOf(green));
		st.setInsertdate(new Date());
		int  stexchang= stExchangeBalanceMapper.updateByPrimaryKey(st);
		return stexchang;
	}
	

}
