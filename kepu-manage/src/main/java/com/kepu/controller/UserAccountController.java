package com.kepu.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StUserAccount;
import com.kepu.pojo.User;
import com.kepu.service.SysService;
import com.kepu.service.UserAccountService;
import com.kepu.service.UserService;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private SysService sysService;		//地址
	/**
	 * 根据用户的ID，查询对应的用户详情
	 * @param request
	 * @return
	 */
	@RequestMapping("selectUserById")
	public ModelAndView selectUserById(HttpServletRequest request){
		String userId = request.getParameter("userId");
		ModelAndView mav = new ModelAndView();
		StUser stUser = userService.getUserById(Integer.parseInt(userId));					//查找用户信息
		StUserAccount stUserAccount = userAccountService.selectByPrimaryKey(Integer.parseInt(userId));    //用户的账户信息
		List<String> addressList=new LinkedList<String>();
		Map<Integer,String> map2=sysService.getAddressName();
		String adderss = map2.get(stUser.getArea());										//查询对应的行政区
		String[] cs={"公务员","教师","医务人员","科研人员","学生","农民","工人","企业主","企业管理人员","金融服务业","律师","技术人员","自由职业"};
		String[] edu={"博士","硕士","大学","大专 ","高中 ","高中以下"};
		List<String> careerList = Arrays.asList(cs);						//职业
		List<String> eduList = Arrays.asList(edu);							//教育程度
		mav.addObject("careerList", careerList);
		mav.addObject("eduList", eduList);
		mav.addObject("stUser", stUser);
		mav.addObject("stUserAccount",stUserAccount);						//账户余额
	    mav.addObject("mainPage","user/user_details.jsp");
	    mav.addObject("addressList",adderss);
	    mav.setViewName("main");
		return mav;
	}
	
	
	/**
	 * 根据用户的ID，修改对应的用户详情
	 * @param request
	 * @return
	 */
	@RequestMapping("updateUserById")
	public String updateUserById(HttpServletRequest request,StUser stUser,StUserAccount stUserAccount){
		String userId = request.getParameter("userId");
		ModelAndView mav = new ModelAndView();
		if(stUser!=null){
			userService.updateByPrimaryKey(stUser);					//调用用户修改方法
		}
		if(stUserAccount !=null){
			userAccountService.updateByPrimaryKey(stUserAccount);
		}
		return "redirect:selectUserById";
	}
	
	/**
	 * 根据用户的ID，冻结改用户
	 * @param request
	 * @return
	 */
	@RequestMapping("deleteUserById")
	public String updateUserById(HttpServletRequest request){
		String userId = request.getParameter("userId");
		String state = request.getParameter("state");
		StUser stUser = new StUser();
		if(userId !=null && state !=null){
			stUser.setUserid(Integer.parseInt(userId));
			if(state =="2"){								//state 0 是已经冻结 2是正常
				stUser.setState(0);
			}else{
				stUser.setState(2);
			}
		}
		userService.updateByPrimaryKey(stUser);					//调用用户修改方法
		return "redirect:selectUserById";
	}
	
	
	
	
}
