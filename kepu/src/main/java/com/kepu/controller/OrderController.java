package com.kepu.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.github.pagehelper.PageInfo;
import com.kepu.constant.ResultConstant;
import com.kepu.mapper.OrderInfoMapper;
import com.kepu.mapper.ProportionSettingMapper;
import com.kepu.mapper.ScoreDetailMapper;
import com.kepu.mapper.StProductMapper;
import com.kepu.mapper.StUserAccountMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.mapper.UserAddressMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.OrderInfoExample;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.ProportionSetting;
import com.kepu.pojo.ScoreDetail;
import com.kepu.pojo.StProduct;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StUserAccount;
import com.kepu.pojo.UserAddress;
import com.kepu.pojo.UserAddressExample;
import com.kepu.service.OrderService;
import com.kepu.service.UserService;
import com.kepu.util.LocalDateUtil;
import com.kepu.util.StringUtil;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderInfoMapper orderInfomapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private StUserMapper stUserMapper;
	@Autowired
	private StProductMapper stProductMapper;
	@Autowired
	private StUserAccountMapper stUserAccountMapper;
	@Autowired
	private ProportionSettingMapper proportionSettingMapper;
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	@Autowired
	private ScoreDetailMapper scoreDetailMapper;
	@Autowired
	private UserAddressMapper userAddressMapper;
	@Autowired
	private ScoreDetailMapper sdmapper;
	protected final Log logger = LogFactory.getLog(OrderController.class);

	// 查询订单列表
	@RequestMapping("orderList")
	public Object addAddress(@RequestBody Map<String, String> map1, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		OrderInfoExample example = new OrderInfoExample();
		example.setOrderByClause("order_date desc ");
		OrderInfoExample.Criteria criteria = example.createCriteria();
		if (null != (map1.get("orderId")))
			criteria.andOrderIdEqualTo(Integer.valueOf(map1.get("orderId")));
		if (null != (map1.get("orderUser")))
			criteria.andOrderUserEqualTo(map1.get("orderUser"));
		if (null != (map1.get("orderStatu")))
			criteria.andOrderStatuEqualTo(Integer.valueOf(map1.get("orderStatu")));
		if (null != (map1.get("buyUserId")))
			criteria.andBuyUserIdEqualTo(Integer.valueOf(map1.get("buyUserId")));
		List<OrderInfo> list = orderInfoMapper.selectByExample(example);
		map.put("list", list);
		return KePuResult.ok(ResultConstant.code_ok, "订单列表", map);
	}

	// 查询某订单
	@RequestMapping("findOrderById")
	public Object findOrderBy(@RequestBody Map<String, String> map1, HttpServletRequest request) {
		String orderid = map1.get("order_id");
		OrderInfo order = orderInfomapper.selectByPrimaryKey(Integer.parseInt(orderid));
		// 通过购买者的ID，查询对应的用户名
		StUser stUser = userService.getUserById(order.getBuyUserId()); // 查找用户信息
		Map map = new HashMap<>();
		map.put("order", order);
		map.put("userInfo", stUser);
		return KePuResult.ok(ResultConstant.code_ok, "订单列表", map);
	}

	/*
	 * // 取消订单
	 * 
	 * @RequestMapping("deleteOrder") public ModelAndView
	 * deleteOrder(@RequestParam(value="id",required=false)String
	 * id,HttpServletRequest request){ ModelAndView mav=new ModelAndView();
	 * String orderid=request.getParameter("order_id"); OrderInfo
	 * order=orderInfomapper.selectByPrimaryKey(Integer.parseInt(orderid));
	 * mav.addObject("order", order);
	 * mav.addObject("mainPage","order/order_cancel.jsp");
	 * mav.setViewName("main"); return mav; }
	 */

	@RequestMapping("selectOrder")
	public Object selectOrder(@RequestBody Map<String, String> map1, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Map<String, String> map = request.getParameterMap();
		List<OrderInfo> order = orderInfomapper.selectOrder(map);
		mav.addObject("order", order);
		mav.addObject("mainPage", "order/orderList.jsp");
		mav.setViewName("main");
		return mav;
	}

	/**
	 * 返回到订单列表里面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("selectReturn")
	public String selectReturn() {
		return "redirect:orderList";
	}

	/**
	 * 返回到ajax
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("updateStatus")
	@ResponseBody
	public int updateStatus(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		String orderStatu = request.getParameter("orderStatu");
		Map map = new HashMap<>();
		map.put("orderStatu", orderStatu);
		map.put("orderId", orderId);
		orderInfomapper.updateStatus(map);
		int a = 200;
		return a;
	}

	/**
	 * 生成订单
	 * 
	 * @param request
	 *            buy_user_id 下单人 ,bill_num 商品数量, product_id 商品id,order_des 订单留言
	 * @return
	 */
	@RequestMapping("creatOrder")
	@ResponseBody
	public KePuResult creatOrder(@RequestBody Map<String, String> map2, HttpServletRequest request) {
		Integer buyUserId = null;
		OrderInfo orderInfo = new OrderInfo();
		String bill_num = null;
		String product_id = null;
		String address = null;
		String orderDes = map2.get("orderDes");
		if (null == map2.get("orderDes")) {
			orderDes = "";
		}
		if (null == request.getParameter("bill_num")) {
			bill_num = map2.get("bill_num");
		}
		if (null == request.getParameter("product_id")) {
			product_id = map2.get("product_id");
		}
		// // 判断是否登录
		// String token = request.getHeader("baseParams") == null ? "" : 
		// request.getHeader("baseParams");
		// StUser user = userService.getUserByToken(token);
		// if (user == null) {
		// return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
		// }
		// if (user != null) {
		// buyUserId = user.getUserid();
		// }
		// 判断是否登录结束
		if (null != request.getParameter("buy_user_id") && "" != request.getParameter("buy_user_id")) {
			buyUserId = Integer.valueOf(request.getParameter("buy_user_id"));// 下单人
		} else {
			buyUserId = Integer.valueOf(map2.get("buy_user_id"));// 下单人
		}
		// 获取默认地址
		UserAddressExample example = new UserAddressExample();
		UserAddressExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(buyUserId);
		criteria.andStatusEqualTo(1);
		if (userAddressMapper.selectByExample(example).isEmpty()) {
			return KePuResult.ok(ResultConstant.code_exception, "查找地址失败", "");
		}
		List<UserAddress> ua2 = userAddressMapper.selectByExample(example);
		UserAddress ua = null;//默认联系人信息
		for (UserAddress userAddress : ua2) {
			if("1".equals(userAddress.getStatus())){
				ua = userAddress;
				break;
			}
		}
		if(ua==null){
			ua = ua2.get(0);
		}
		String address2 = ua.getProvince() + ua.getCity() + ua.getArea() + ua.getStreet() + ua.getAddressDetail();
		// 获取默认地址
		// 获得商家商品内容开始
		if(StringUtil.isEmpty(product_id)){
			return KePuResult.ok(ResultConstant.code_yewu, "订单product_id为空", "");
		}
		Integer productId = Integer.valueOf(product_id);// 商品id
		// Integer
		// payType=Integer.valueOf(request.getParameter("pay_type"));//支付方式
		StProduct stProduct = stProductMapper.selectByPrimaryKey(productId);// 获取该商品详情
		if(stProduct==null){
			return KePuResult.ok(ResultConstant.code_yewu, "商品不存在", "");
		}
		BigDecimal money = stProduct.getMoney();// 获取商品价格
		Integer sellUserId = stProduct.getUserid();// sellUserId 商家id
		String introduce = stProduct.getIntroduce();// 商家说明
		// 获得商家商品内容结束

		// 获得用户详情开始
		StUser stUser = stUserMapper.selectByPrimaryKey(buyUserId);
		String nickName =  StringUtil.isNotEmpty(ua.getContactName())?ua.getContactName():stUser.getNickname();// 获取用户昵称
		String mobile = StringUtil.isNotEmpty(ua.getContactPhone())?ua.getContactPhone():stUser.getMobile();// 获取用户手机号
		// Integer state=stUser.getState();//获取用户状态
		// 获得用户详情结束

		// 放入OrderInfo开始
		Double bill_num2 = Double.valueOf(bill_num);
		orderInfo.setBalance(0.00);// 默认积分抵扣数额为0.00
		orderInfo.setBillDesc("无");// 默认发票备注为无
		if (null != Double.valueOf(bill_num2)) {
			orderInfo.setBillNum(bill_num2);// 商品数量默认为1
		}
		Double nowPrice = money.doubleValue() * bill_num2;
		orderInfo.setBillPrice(nowPrice);
		orderInfo.setBillTitle("无");// 默认发票抬头为无
		Date date = LocalDateUtil.getNow();
		orderInfo.setCreateTime(date);
		orderInfo.setCredit(0.00);
		orderInfo.setProductId(String.valueOf(productId));
		;
		if (StringUtil.isNotEmpty(address2)) {
			address = address2 ;
		} else {
			address = map2.get("address");// 如果没有默认地址 就把传过来的地址赋值给address
		}
		orderInfo.setOrderAddress(address);
		String orderCode = String.valueOf(new Date().getTime());
		orderInfo.setOrderCode(orderCode);
		orderInfo.setOrderDate(date);
		orderInfo.setOrderDes(orderDes);// 订单留言
		orderInfo.setOrderNote(introduce);
		orderInfo.setOrderPhone(mobile);
		orderInfo.setOrderStatu(0);// 增加订单状态:待付款
		orderInfo.setOrderUser(nickName);
		orderInfo.setBuyUserId(buyUserId);
		// orderInfo.setPayType(payType);
		orderInfo.setSellUserId(sellUserId);
		ProportionSetting setting = proportionSettingMapper.selectByPrimaryKey(1);
		orderInfo.setRatio(setting.getPresentProportion());
		orderInfo.setSysAreaId("");
		orderInfo.setOrderZipcode("");
		orderInfo.setOrderType(1);
		orderInfo.setOrderSubjctId("");
		orderInfo.setOrderPay(0);
		orderInfo.setOrderLogisticsCode("");
		orderInfo.setOrderLogisticsDate(date);
		orderInfo.setOrderLogiscompanyCode("");
		orderInfo.setOrderLogiscompanyPrice(0.00);
		orderInfo.setPayEndTime(date);
		orderInfo.setEndTime(date);
		orderInfo.setIsDelete(0);

		// 放入OrderInfo结束
		// 存入订单
		Map map = new HashMap<>();
		int status = orderInfomapper.insert(orderInfo);
		if (status > 0) {
			// mv.addObject("orderInfo",orderInfo);
			// if(payType==2){//微信支付
			// mv.setViewName("redirect:/alipay/dopay");
			// return KePuResult.ok(ResultConstant.code_ok, "订单详情", map);
			// }
			// else if(payType==3){//支付宝支付
			// mv.setViewName("redirect:/alipay/dopay");
			// return mv;
			// }

			orderInfo.setOrderId(0);
			orderInfo.setPayType(0);
			map.put("orderInfo", orderInfo);
			return KePuResult.ok(ResultConstant.code_ok, "订单生成成功", map);
		}
		// mv.addObject("msg","下单失败!");
		return KePuResult.ok(ResultConstant.code_yewu, "订单生成失败", "");
	}

	/**
	 * 支付宝异步回调 ,修改订单状态,查询购买积分比例,修改用户钱包积分,添加积分明细
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("async")
	@ResponseBody
	public Object async(Map<String, String> formParams, HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		for (String key : formParams.keySet()) {
			String value = formParams.get(key).toString().replace("[", "");// 去除处理特殊字符
			value = value.replace("]", "");// 去除处理特殊字符
			params.put(key, value);
		}
		String ordercode = params.get("out_trade_no");
		OrderInfo order = new OrderInfo();
		// 商户订单号
		order.setOrderCode(ordercode);
		// 状态3交易成功
		order.setOrderStatu(3);
		// 修改支付状态
		int i = orderInfoMapper.updateByPrimaryCode(order);
		ProportionSetting setting = proportionSettingMapper.selectByPrimaryKey(1);
		Double RebateRatio = setting.getRebateRatio();// 余额兑换比例RebateRatio
		OrderInfo oi = orderInfoMapper.fandByPrimaryId(ordercode);
		// 获取orderinfo里的buyuserid 和 余额
		Double billPrice = oi.getBillPrice();
		// 获得的积分
		Double getScore = billPrice * RebateRatio;
		StUser stuserlist = stUserMapper.fandStUserlist(oi.getBuyUserId());// 购买者id查询
		StUserAccount sa = stUserAccountMapper.fandByPrimaryKeySelectivelist(stuserlist.getUserid());
		if (sa == null) {
			System.out.println("null");
		} else {
			sa.setUserid(oi.getBuyUserId());
			Double score = sa.getScore();
			sa.setScore(score + getScore);// 之前积分加上获得积分
			int s = stUserAccountMapper.updateByPrimaryKeySelective(sa);
			ScoreDetail scor = new ScoreDetail();
			scor.setScoreRatio(RebateRatio);// 积分兑换比例
			scor.setOrderId(oi.getBuyUserId());// order_info的id 积分提现无id
			Date date = LocalDateUtil.getNow();
			scor.setTime(date);// 时间
			scor.setBuyUserId(oi.getBuyUserId());// 购买者id
			scor.setScoreDetails("下单返积分");
			scor.setBuyUserPhone(stuserlist.getMobile());// 手机号
			scor.setIsOverdue(0); // 积分是否过期 0:未过期 1已过期
			scor.setSellerId(oi.getSellUserId());// 卖家id
			StProduct stproduct = stProductMapper.fandstproductlist(oi.getProductId());
			scor.setCommName(stproduct.getTitle());// 购买商品名称.
			scor.setCommMoney(stproduct.getMoney().doubleValue());// 商品价格
			scor.setTransactionType(1);// 交易类型 1:收入 2:支出
			scor.setScoreNum(getScore.intValue());// 积分数量
			scor.setTransactionCurrencyType("2");// 交易货币类型 1:余额 2:积分
			int j = scoreDetailMapper.insertScoreDetaillist(scor);
			if (j > 1) {
				return KePuResult.ok(ResultConstant.code_ok, "交易明细失败", "");
			}
		}
		return sa;
	}
}
