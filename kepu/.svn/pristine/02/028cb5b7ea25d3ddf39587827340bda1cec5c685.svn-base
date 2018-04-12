package com.kepu.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.kepu.constant.ResultConstant;
import com.kepu.mapper.UserAddressMapper;
import com.kepu.mapper.WithdrawCashMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StUser;
import com.kepu.pojo.UserAddress;
import com.kepu.pojo.UserAddressExample;
import com.kepu.pojo.WithdrawCash;
import com.kepu.pojo.WithdrawCashExample;
import com.kepu.service.AddressService;
import com.kepu.service.UserService;
import com.kepu.service.WithdrawCashService;
import com.kepu.util.LocalDateUtil;

@Controller
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private UserAddressMapper userAddressMapper;
	@Autowired
	private WithdrawCashService withdrawCashService;
	@Autowired
	private WithdrawCashMapper withdrawCashMapper;

	protected final Log logger = LogFactory.getLog(AddressController.class);
	//添加地址
	@RequestMapping("addAddress")
	@ResponseBody
//	public Object addAddress(@RequestBody Map<String,String> map,HttpServletRequest request) {
	public  Object addAddress(@RequestBody Map<String, String> map, HttpServletRequest request) {
		UserAddress userAddress=new UserAddress();
		Integer userId=Integer.valueOf(map.get("userId"));
		String contactName=map.get("contactName");
		String contactPhone=map.get("contactPhone"); 
		String province=map.get("province");
		String city=map.get("city"); 
		String area=map.get("area");
		String street=map.get("street");
		String addressDetail=map.get("addressDetail");
		Integer status=Integer.valueOf(map.get("status"));
		Integer isdelete=0;
		userAddress .setUserId(userId);
		userAddress .setContactName(contactName);
		userAddress .setContactPhone(contactPhone);
		userAddress .setProvince(province);
		userAddress .setCity(city); 
		userAddress .setArea(area);
		userAddress .setStreet(street);
		userAddress .setAddressDetail(addressDetail);
		userAddress .setStatus(status); 
		Date date=LocalDateUtil.getNow();
		userAddress.setUpdateTime(date);
		userAddress.setCreateTime(date);
		userAddress.setIsDelete(isdelete);
		
		//如果修改地址为1  查询出要修改状态的所有地址 并修改为2
		if(status==1&&null!=userAddress){
			UserAddressExample example=new UserAddressExample();
			UserAddressExample.Criteria criteria=example.createCriteria();
			criteria.andStatusEqualTo(1);
			criteria.andUserIdEqualTo(userId);
			List<UserAddress> addressList = userAddressMapper.selectByExample(example);
			if(addressList.size()>0){
				for (UserAddress userAddress2 : addressList) {
					if(userAddress2.getStatus()==1){
						userAddress2.setStatus(2);
						userAddressMapper.updateByPrimaryKeySelective(userAddress2);
					}
				}		
			}
		}
		int a=userAddressMapper.insert(userAddress);
		if(a>0){
			return KePuResult.ok(ResultConstant.code_ok, "添加地址成功", "");
		}else{
			return KePuResult.ok(ResultConstant.code_exception, "添加地址失败2", "");
		}
		
	}

	// 查询订单列表
	@RequestMapping("addressList")
	@ResponseBody
	public Object addressList(@RequestBody Map<String,String> map,HttpServletRequest request) {
		Integer userid=null;
		if(null==request.getParameter("userId")){
			 userid=Integer.valueOf(map.get("userId"));
		}else{
			 userid=Integer.valueOf(request.getParameter("userId"));
		}
		UserAddressExample example=new UserAddressExample();
		UserAddressExample.Criteria criteria=example.createCriteria();
		criteria.andUserIdEqualTo(userid);
		example.setOrderByClause("update_time desc ");
		List<UserAddress> address = userAddressMapper.selectByExample(example);
		return KePuResult.ok(ResultConstant.code_ok, "获取列表成功", address);
	}

	// 修改订单跳转
	@RequestMapping("selectAddress")
	public @ResponseBody Object selectAddress(@RequestBody Map<String,String> map,HttpServletRequest request) {
		String addressId =null;
		if(null==request.getParameter("addressId")){
			 addressId = map.get("addressId");
		}else{
			 addressId=request.getParameter("addressId");
		}
		UserAddress address = userAddressMapper.selectByPrimaryKey(Integer.parseInt(addressId));
		return KePuResult.ok(ResultConstant.code_ok, "查询地址成功", address);
	}

	// 修改地址提交
	@RequestMapping("updateCommit")
		public @ResponseBody  Object UpdateCommit(@RequestBody Map<String, String> map, HttpServletRequest request) {
		Integer userId=Integer.valueOf(map.get("userId"));
		Integer status=Integer.valueOf(map.get("status"));
		String addressId2 =null;
		if(null==request.getParameter("addressId")){
			 addressId2 = map.get("addressId");
		}else{
			 addressId2=request.getParameter("addressId");
		}
		//查询出要修改的地址
		UserAddress userAddress = userAddressMapper.selectByPrimaryKey(Integer.parseInt(addressId2));
		//如果修改地址为1  查询出要修改状态的所有地址 并修改为2
		if(status==1&&null!=userAddress){
			UserAddressExample example=new UserAddressExample();
			UserAddressExample.Criteria criteria=example.createCriteria();
			criteria.andStatusEqualTo(1);
			criteria.andUserIdEqualTo(userId);
			List<UserAddress> addressList = userAddressMapper.selectByExample(example);
			if(addressList.size()>0){
				for (UserAddress userAddress2 : addressList) {
					if(userAddress2.getStatus()==1){
						userAddress2.setStatus(2);
						userAddressMapper.updateByPrimaryKeySelective(userAddress2);
					}
				}	
			}
		}
		String contactName=map.get("contactName");
		String contactPhone=map.get("contactPhone");
		String province=map.get("province");
		String city=map.get("city"); 
		String area=map.get("area");
		String street=map.get("street");
		String addressDetail=map.get("addressDetail"); 
		userAddress.setUserId(userId);
		userAddress.setContactName(contactName);
		userAddress.setContactPhone(contactPhone);
		userAddress.setProvince(province);
		userAddress.setCity(city); 
		userAddress.setArea(area);
		userAddress.setStreet(street);
		userAddress.setAddressDetail(addressDetail);
		userAddress.setStatus(status);
		Date date=LocalDateUtil.getNow();
		userAddress.setUpdateTime(date);
		int s = userAddressMapper.updateByPrimaryKeySelective(userAddress) ;
		if (s > 0){
			return KePuResult.ok(ResultConstant.code_ok, "更改地址成功", "");
		}
		return KePuResult.ok(ResultConstant.code_exception, "更改失败", "");
	}
	
	@RequestMapping("withdrawCashList")
	@ResponseBody
//	public @ResponseBody Object withdrawCashList(@RequestParam(value="page",required=false)String page,WithdrawCash withdrawCash,HttpServletRequest request){
	public Object withdrawCashList(@RequestBody Map<String,String> map,HttpServletRequest request) {	
		Integer userid=null;
		if(null==request.getParameter("userId")){
			 userid=Integer.valueOf(map.get("userId"));
		}else{
			 userid=Integer.valueOf(request.getParameter("userId"));
		}
		WithdrawCashExample example=new WithdrawCashExample();
		WithdrawCashExample.Criteria criteria=example.createCriteria();
		criteria.andBuyUserIdEqualTo(userid);
		List<WithdrawCash> cash=withdrawCashMapper.selectByExample(example);
		return KePuResult.ok(ResultConstant.code_ok, "获取列表成功", cash);
	}
	
	// 修改地址提交
		@RequestMapping("deleteCommit")
			public @ResponseBody  Object deleteCommit(@RequestBody Map<String, String> map, HttpServletRequest request) {
			// 判断是否登录
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			// 判断是否登录结束
			Integer addressId=Integer.valueOf(map.get("addressId"));
			int s = userAddressMapper.deleteByPrimaryKey(addressId);
			if (s > 0)
				return KePuResult.ok(ResultConstant.code_ok, "删除地址成功", "");
			return KePuResult.ok(ResultConstant.code_exception, "删除失败", "");
		}
}
