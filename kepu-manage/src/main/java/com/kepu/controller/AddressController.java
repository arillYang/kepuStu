package com.kepu.controller;


import java.util.HashMap;
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
import com.kepu.mapper.OrderInfoMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.mapper.UserAddressMapper;
import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.PageBean;
import com.kepu.pojo.StUser;
import com.kepu.pojo.UserAddress;
import com.kepu.service.AddressService;
import com.kepu.service.UserService;
import com.kepu.util.PageUtil;
import com.kepu.util.StringUtil;

@Controller
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private OrderInfoMapper orderInfomapper;
	@Autowired
	private AddressService addressService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserAddressMapper userAddressMapper;

	protected final Log logger = LogFactory.getLog(AddressController.class);
	
	// 查询订单列表
	@SuppressWarnings("unchecked")
	@RequestMapping("addressList")
	public ModelAndView addressList(@RequestParam(value="page",required=false)String page,UserAddress userAddress,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("userAddress", userAddress);
		}else{
			userAddress=(UserAddress) session.getAttribute("userAddress");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=addressService.findUserAddress(pageBean, userAddress);
		List<OrderInfo> addressList=(List<OrderInfo>) map.get("list");
		long total=(Long) map.get("total");
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/address/addressList",  new Long(total).intValue(), Integer.parseInt(page), 10);
		mav.addObject("pageCode", pageCode);
		mav.addObject("addressList", addressList);
		mav.addObject("mainPage", "address/addressList.jsp");
		mav.setViewName("main");
		return mav;
	}
		
		// 修改订单跳转
				@RequestMapping("UpdateAddress")
				public ModelAndView UpdateOrder(HttpServletRequest request){
					String addressId=request.getParameter("addressId");
					ModelAndView mav=new ModelAndView();
					UserAddress  address=userAddressMapper.selectByPrimaryKey(Integer.parseInt(addressId));
					 mav.addObject("address",address);
					 mav.addObject("mainPage","address/addressUpdate.jsp");
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
}

