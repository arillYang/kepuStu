package com.kepu.HandlerInterceptor;




import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kepu.dao.JedisClient;
import com.kepu.pojo.DeviceMessage;
import com.kepu.pojo.StDevice;
import com.kepu.pojo.StUser;
import com.kepu.pojo.TUser;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.util.ImgHelperUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.StringUtil;




public class LoginInterceptor implements HandlerInterceptor{

	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private SysService sysService;
	@Autowired
	private UserService userService;
	
	LinkedList<String> list=new LinkedList<String>();
	// 权限控制
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		StringBuffer url=request.getRequestURL();
		String u=url.toString();
		for (String string : list) {
			//如果是免登则放行
			if(u.indexOf(string)>=0)
				return true;
		}
		if(u.endsWith(".css")||u.endsWith(".js")||u.endsWith(".jsp")||u.endsWith(".html"))
			return true;
		if(u.indexOf("/user/index")>=0||u.indexOf("/user/login")>=0||u.indexOf("/news/upload")>=0)
			return true;
		// 后台管理
		TUser resultUser=(TUser) request.getSession().getAttribute("currentUser");
		if(resultUser==null){
			request.getRequestDispatcher("/user/index").forward(request, response);
			return false;
		}else{
			Integer roleId=resultUser.getRoleid();
			List<String> urls = userService.getMenuUrlsByRoleId(roleId);
			for (String string : urls) {
				if(u.indexOf(string)>=0)
					return true;
			}
			List<String> urls2 = userService.getMenuUrlsByRoleId(1);
			boolean f=true;
			for (String string : urls2) {
				if(u.indexOf(string)>=0){
					f=false;
				}
			}
			if(!f)
				request.getRequestDispatcher("/user/NoPermission").forward(request, response);
			return f;
		}
	}

	
	//  放入公用模型数据  如菜单导航
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}



	//   统一异常，日志处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3) throws IOException
					{
		String url=request.getRequestURL().toString();
		if(url.indexOf(".")<0){
			System.out.println(request.getRequestURL().toString());
			//logger.info(request.getRequestURL().toString());
		}
	}
}