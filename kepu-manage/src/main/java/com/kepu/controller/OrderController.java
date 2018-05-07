package com.kepu.controller;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;
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
import com.kepu.mapper.OrderInfoMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StUser;
import com.kepu.pojo.TRole;
import com.kepu.pojo.TUser;
import com.kepu.service.OrderService;
import com.kepu.service.UserService;
import com.kepu.util.LocalDateUtil;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;
import com.sun.xml.internal.bind.v2.TODO;

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

	protected final Log logger = LogFactory.getLog(OrderController.class);
	
	// 查询订单列表
	@SuppressWarnings("unchecked")
	@RequestMapping("orderList")
	public ModelAndView orderList(@RequestParam(value="page",required=false)String page,OrderInfo orderInfo,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("orderInfo", orderInfo);
		}else{
			orderInfo=(OrderInfo) session.getAttribute("orderInfo");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=orderService.findOrderInfo(pageBean, orderInfo);
		List<OrderInfo> orderInfoList=(List<OrderInfo>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/order/orderList",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("orderInfoList", orderInfoList);
		mav.addObject("mainPage", "order/orderList.jsp");
		mav.setViewName("main");
		return mav;
	}
		// 查询某订单
		@RequestMapping("findOrderById")
		public ModelAndView findOrderBy(HttpServletRequest request){
			String orderid=request.getParameter("order_id");
			ModelAndView mav=new ModelAndView();
			 OrderInfo  order=orderInfomapper.selectByPrimaryKey(Integer.parseInt(orderid));
			//通过购买者的ID，查询对应的用户名
			StUser stUser = userService.getUserById(order.getBuyUserId());					//查找用户信息
			if(null!=stUser.getNickname()){
				 mav.addObject("BuyUserName",stUser.getNickname());
			} 
			StUser stUser2 = userService.getUserById(order.getSellUserId());					//查找用户信息
			 mav.addObject("SellUser",stUser2);
			 mav.addObject("order", order);
			 mav.addObject("mainPage","order/order_details.jsp");
			 mav.setViewName("main");
			return mav;
		}
	
		// 修改订单跳转
				@RequestMapping("UpdateOrder")
				public ModelAndView UpdateOrder(HttpServletRequest request){
					String orderid=request.getParameter("order_id");
					ModelAndView mav=new ModelAndView();
					 OrderInfo  order=orderInfomapper.selectByPrimaryKey(Integer.parseInt(orderid));
					//通过购买者的ID，查询对应的用户名
					StUser stUser = userService.getUserById(order.getBuyUserId());					//查找用户信息
					if(null!=stUser.getNickname()){
						 mav.addObject("BuyUserName",stUser.getNickname());
					} 
					StUser stUser2 = userService.getUserById(order.getSellUserId());					//查找用户信息
					 mav.addObject("SellUser",stUser2);
					 mav.addObject("order", order);
					 mav.addObject("mainPage","order/order_update.jsp");
					 mav.setViewName("main");
					return mav;
				}
		
				// 修改订单提交
				//TODO
				@RequestMapping("updateCommit")
				public String UpdateCommit(HttpServletRequest request,OrderInfo orderInfo,StUser stUser){
					ModelAndView mav=new ModelAndView();
					System.out.println(orderInfo.toString());
					//将数据放入数据库，执行修改
					int orderInfo2=orderInfomapper.updateByPrimaryKeySelective(orderInfo);
					return "redirect:orderList";
				}
				
		       // 取消订单
				@RequestMapping("deleteOrder")
				public ModelAndView deleteOrder(@RequestParam(value="id",required=false)String id,HttpServletRequest request){
					ModelAndView mav=new ModelAndView();
					String orderid=request.getParameter("order_id");
					 OrderInfo  order=orderInfomapper.selectByPrimaryKey(Integer.parseInt(orderid));
					 mav.addObject("order", order);
					 mav.addObject("mainPage","order/order_cancel.jsp");
					 mav.setViewName("main");
					return mav;
				}
				
				@RequestMapping("selectOrder")
				public ModelAndView selectOrder(HttpServletRequest request){
					ModelAndView mav=new ModelAndView();
					Map<String,String> map =request.getParameterMap();
					System.out.println();
					List<OrderInfo> order=orderInfomapper.selectOrder(map);
					mav.addObject("order", order);
					mav.addObject("mainPage", "order/orderList.jsp");
					mav.setViewName("main");
					return mav;
				}
				/**
				 * 返回到订单列表里面
				 * @param request
				 * @return
				 */
				@RequestMapping("selectReturn")
				public String selectReturn(){
					return "redirect:orderList";
				}
				/**
				 * 返回到ajax
				 * @param request
				 * @return
				 */
				@RequestMapping("updateStatus")
				@ResponseBody
				public int updateStatus(HttpServletRequest request){
					String orderId=request.getParameter("orderId");
					String orderStatu=request.getParameter("orderStatu");
					Map map=new HashMap<>();
					map.put("orderStatu", orderStatu);
					map.put("orderId", orderId);
					orderInfomapper.updateStatus(map);
					int a=200;
					return a;
				}
				
}

