package com.kepu.HandlerInterceptor;




import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kepu.constant.MyConstant;
import com.kepu.dao.JedisClient;
import com.kepu.mapper.StUserAccountMapper;
import com.kepu.pojo.DeviceMessage;
import com.kepu.pojo.StActivityRecord;
import com.kepu.pojo.StDevice;
import com.kepu.pojo.StLog;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StUserAccount;
import com.kepu.pojo.StVillage;
import com.kepu.service.NewService;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.service.VillageService;
import com.kepu.util.GpsSession;
import com.kepu.util.ImgHelperUtil;
import com.kepu.util.JsonUtils;
import com.kepu.util.RequestUtil;
import com.kepu.util.StringUtil;
import com.kepu.util.SystemSession;




public class LoginInterceptor implements HandlerInterceptor{

	protected final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private SysService sysService;
	@Autowired
	private VillageService  villageService;
	@Autowired
	private UserService userService;
	@Autowired
	private NewService newService;
	
	@Autowired
	private StUserAccountMapper userAccountMapper;
	
	private  static LinkedList<String> list=new LinkedList<String>();
	
	private static final Map<Integer,Integer> map=new HashMap<Integer, Integer>();
	
	
	// 权限控制
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		String system=request.getHeader("system")==null?"":request.getHeader("system");
		if(StringUtil.isNotEmpty(system)){
			String json=new String(ImgHelperUtil.decode(system));
			DeviceMessage message=JsonUtils.jsonToPojo(json, DeviceMessage.class);
			StDevice stDevice=new StDevice();
			stDevice.setAppldentifier(message.getAppIdentifier());
			stDevice.setApplication(message.getApplication());
			stDevice.setAppversion(message.getAppVersion());
			stDevice.setHardware(message.getHardware());
			stDevice.setPagename(message.getPageName());
			stDevice.setSystemversion(message.getSystemVersion());
			SystemSession.set(message);
			//sysService.insertDeviceMessage(stDevice);
			request.setAttribute("appVersion", message.getAppVersion());
			request.setAttribute("application", message.getApplication());
		}
		
		StringBuffer url=request.getRequestURL();
		// 初始化免登录列表
		synchronized (list) {
			if(list.size()==0){
				InputStream is=this.getClass().getResourceAsStream("/OpenUrl.properties");
				Properties properties=new Properties();
				properties.load(is);
				is.close();
				String open=properties.getProperty("open"); 
				if(StringUtil.isEmpty(open))
					return true;
				String[] openList=open.split(",");
				for (String str : openList) {
					list.add(str);
				}
				logger.info("初始化免登录列表成功");
				}
		}
		String u=url.toString();
		for (String string : list) {
			//如果是免登则放行
			if(u.indexOf(string)>=0){
				return true;
			}
		}
		if(u.endsWith(".css")||u.endsWith(".js")||u.endsWith(".jsp"))
			return true;
		// 后台管理
		StUser resultUser=(StUser) request.getSession().getAttribute("currentUser");
		//  从Header取出
		String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");  
		//String token=CookieUtils.getCookieValue(request, "staro2_token");
		if(StringUtil.isEmpty(token)){
			if(resultUser==null){
				if(u.indexOf("admin")<0&&u.indexOf("anchor")<0){
					request.getRequestDispatcher("/user/login2").forward(request, response);
					return false;
				}else{
					request.getRequestDispatcher("/admin/login").forward(request, response);
					return false;
					
				}
			}else{
				return true;
			}
		}
		logger.info("token:"+token);
		String value=jedisClient.get("st_user_"+token);
		//StUser user=JsonUtils.jsonToPojo(value, StUser.class);
		// token失效
		if(StringUtil.isEmpty(value)){
			request.getRequestDispatcher("/user/login3").forward(request, response);
			return false;
		}else{
			String cid=request.getHeader("cid")==null?"":request.getHeader("cid");
			StUser user = userService.getUserByToken(token);
			if(user==null){
				request.getRequestDispatcher("/user/login3").forward(request, response);
				return false;
			}
			StUser user2 = userService.getUserById(user.getUserid());
			if(StringUtil.isNotEmpty(cid)&&(!cid.equals(user.getCid()))||StringUtil.isEmpty(user2.getCid())){
				user2.setCid(cid);
				userService.updateStUser(user2, token);
			}
			request.setAttribute("user", user2);
			String location=MyConstant.getLocation(user2.getTownid());
			GpsSession.set(location);
			return true;
		}
	}

	
	//  放入公用模型数据  如菜单导航
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}



	//   统一异常，日志处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3) throws IOException
					{
		SystemSession.remove();
		GpsSession.remove();
		String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams");  
		StUser user=userService.getUserByToken(token);
		StUserAccount stuser = userAccountMapper.selectByPrimaryKey(user.getUserid());
		StUserAccount u = new StUserAccount();
		Date date=new Date();
		if(user!=null){
			//active begin
			/*Map<String, String> jmap = jedisClient.hgetAll("active_user");
			Date now=new Date();
			if(jmap==null||jmap.size()==0){
				jedisClient.hset("active_user", "-1", "1");
				jedisClient.expire("active_user",(int)(DateUtil.endOfTodDay().getTime()-now.getTime())/1000);
			}
			String userId=jedisClient.hget("active_user", user.getUserid().toString());
			if(StringUtil.isEmpty(userId)){
				user.setUpdatetime(now);
				userService.saveUser(user);
				jedisClient.hset("active_user", user.getUserid().toString(), "1");
			}*/
			//end
			String url=request.getRequestURL().toString();
			StLog log=new StLog();
			log.setUserid(user.getUserid());
			log.setIp(RequestUtil.getRemoteAddrIp(request));
			log.setCountyid(user.getArea());
			Integer town=map.get(user.getArea());
			if(town==null){
				StVillage parent = villageService.getVillageNameById(user.getArea());
				log.setTownid(parent.getParent());
				map.put(user.getArea(), parent.getParent());
			}else{
				log.setTownid(town);
			}
			log.setCreatetime(new Date());
			int index=url.indexOf("kepu");
			log.setRequesturl(url.substring(index+5));
			if(url.indexOf("news/getNewsDetail")>=0){
				int type=Integer.valueOf(url.substring(url.lastIndexOf("/")+1)); //新闻ID
				log.setType(type);
				StActivityRecord record=new StActivityRecord();
				record.setScore(1.0);
				sysService.insertActivityRecord(user, record, 1, "新闻阅读,ID:"+type);
				if(stuser==null || stuser.equals(""))
				{
					u.setUserid(user.getUserid());
					u.setScore(1.0);
					u.setCreatetime(date);//创建时间
					u.setUpdatetime(date);//修改时间
					u.setMobile(user.getMobile());//手机号
					u.setShowname(user.getNickname());//昵称
				     userAccountMapper.insert(u);
				}
				else {
					u.setUserid(user.getUserid());
					u.setScore(stuser.getScore()+1.0);// 当前积分
					userAccountMapper.updateByPrimaryKeySelective(u);
				}
				/*if(!newService.hasRecord(type, user.getUserid(),1)){
				}*/
				log.setStyle(1);
				userService.saveLog(log);
			}else if(url.indexOf("villages/getNewsDetail")>=0){
				int type=Integer.valueOf(url.substring(url.lastIndexOf("/")+1)); //乡镇新闻ID
				/*if(!newService.hasRecord(type, user.getUserid(),11)){*/
					StActivityRecord record=new StActivityRecord();
					record.setScore(1.0);
					sysService.insertActivityRecord(user, record, 11, "乡镇新闻阅读,ID:"+type);
					if(stuser==null || stuser.equals(""))
					{
						u.setUserid(user.getUserid());
						u.setScore(1.0);
						u.setCreatetime(date);//创建时间
						u.setUpdatetime(date);//修改时间
						u.setMobile(user.getMobile());//手机号
						u.setShowname(user.getNickname());//昵称
					     userAccountMapper.insert(u);
					}
					else {
						u.setUserid(user.getUserid());
						u.setScore(stuser.getScore()+1.0);// 当前积分
						userAccountMapper.updateByPrimaryKeySelective(u);
					}
				/*}*/
				log.setType(type);
				log.setStyle(11);
				userService.saveLog(log);
			}else if(url.indexOf("clickTab")>=0){
				int type=Integer.valueOf(url.substring(url.lastIndexOf("/")+1)); //tab类型
				if(type==3){
					long time=jedisClient.incr("clickTab_"+user.getUserid());
					if(time<=10){
						if(time==1)
							jedisClient.expire("clickTab_"+user.getUserid(), 86400);
						StActivityRecord record=new StActivityRecord();
						record.setScore(1.0);
						int r=sysService.insertActivityRecord(user, record, 6, "点击服务功能");
						if(stuser==null || stuser.equals(""))
						{
							u.setUserid(user.getUserid());
							u.setScore(1.0);
							u.setCreatetime(date);//创建时间
							u.setUpdatetime(date);//修改时间
							u.setMobile(user.getMobile());//手机号
							u.setShowname(user.getNickname());//昵称
						     userAccountMapper.insert(u);
						}
						else {
							u.setUserid(user.getUserid());
							u.setScore(stuser.getScore()+1.0);// 当前积分
							userAccountMapper.updateByPrimaryKeySelective(u);
						}
						System.out.println("-------"+r);
						if(r!=1){
							jedisClient.decr("clickTab_"+user.getUserid());
						}
					}
				}
				log.setType(type);
				log.setStyle(6);
				userService.saveLog(log);
			}
		}
	}
}