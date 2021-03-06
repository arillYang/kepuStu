package com.kepu.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kepu.constant.MyConstant;
import com.kepu.constant.ResultConstant;
import com.kepu.dao.JedisClient;
import com.kepu.mapper.StUserAccountMapper;
import com.kepu.mapper.WithdrawCashMapper;
import com.kepu.pojo.DeviceMessage;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.StProduct;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.WithdrawCash;
import com.kepu.service.NewService;
import com.kepu.service.ProductService;
import com.kepu.service.RedisService;
import com.kepu.service.SysService;
import com.kepu.service.UserService;
import com.kepu.service.VillageService;
import com.kepu.util.DateUtil;
import com.kepu.util.ExceptionUtil;
import com.kepu.util.FindMobileAddress;
import com.kepu.util.HttpClientUtilNew;
import com.kepu.util.IdentifyCode;
import com.kepu.util.ImgHelperUtil;
import com.kepu.util.LocalDateUtil;
import com.kepu.util.SendTemplateSMSUtil;
import com.kepu.util.StringUtil;
import com.kepu.util.SystemSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private NewService newService;
	@Autowired
	private SysService sysService;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private VillageService villageService;
	@Autowired
	private WithdrawCashMapper withdrawCash;
	@Autowired
	private StUserAccountMapper stUserAccountMapper;
	@Autowired
	private ProductService productService;

	private static final Logger LOG = Logger.getLogger(UserController.class);

	/**
	 * 用户注册
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public @ResponseBody Object register(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			String mobile = "";
			String password = "";
			String area = "";
			String address = "";
			String pin = "";
			String account = "";
			StringBuffer sb = new StringBuffer();
			if (map.containsKey("mobile")) {
				mobile = map.get("mobile");
			} else {
				sb.append("mobile").append(",");
			}
			if (map.containsKey("account")) {
				account = map.get("account");
			} else {
				sb.append("account").append(",");
			}
			if (map.containsKey("password"))
				password = map.get("password");
			else {
				sb.append("password").append(",");
			}
			if (map.containsKey("area"))
				area = map.get("area");
			else {
				sb.append("area").append(",");
			}
			if (map.containsKey("address"))
				address = map.get("address");
			else {
				sb.append("address").append(",");
			}
			if (map.containsKey("pin"))
				pin = map.get("pin");
			else {
				sb.append("pin").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");

			}
			String mac = map.get("mac") == null ? "" : map.get("mac");
			if (!redisService.checkSmsCode(mobile, pin, 1)) {
				return KePuResult.build(ResultConstant.code_SmsError, "验证码错误或已过期", "");
			}
			String appVersion = (String) request.getAttribute("appVersion");
			String temp = jedisClient.get("QPS_Product" + mobile);
			if (StringUtil.isNotEmpty(temp)) {
				return KePuResult.build(ResultConstant.code_yewu, "请求太频繁,请稍后再试", "");
			}
			KePuResult result = userService.createUser(account, mobile, password, Integer.valueOf(area), address, mac,
					appVersion);
			jedisClient.set("QPS_Product" + mobile, "1");
			jedisClient.expire("QPS_Product" + mobile, 5);
			return result;
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody Object login(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			String appVersion = (String) request.getAttribute("appVersion");
			String account = "";
			String password = "";
			StringBuffer sb = new StringBuffer();
			if (map.containsKey("account")) {
				account = map.get("account");
			} else {
				sb.append("account").append(",");
			}
			if (map.containsKey("password"))
				password = map.get("password");
			else {
				sb.append("password").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			KePuResult result = userService.login(account, password, appVersion);
			LOG.info("登录成功");
			return result;
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 用户登出
	 * 
	 * @return
	 */
	@RequestMapping(value = "logout")
	public @ResponseBody Object logout(HttpServletRequest request) {
		String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
		redisService.del("st_user_" + token);
		return KePuResult.ok(ResultConstant.code_ok, "退出成功", "");
	}

	/**
	 * 发送验证码
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "requestPIN", method = RequestMethod.POST)
	public @ResponseBody Object requestPIN(@RequestBody Map<String, String> map) {
		try {
			String mobile = "";
			String pinflag = ""; // 1表示注册，2表示重置密码
			StringBuffer sb = new StringBuffer();
			if (map.containsKey("mobile")) {
				mobile = map.get("mobile");
			} else {
				sb.append("mobile").append(",");
			}
			if (map.containsKey("pinflag"))
				pinflag = map.get("pinflag");
			else {
				sb.append("password").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.ok(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			boolean status = userService.checkMobile(mobile);
			if ("1".equals(pinflag)) {
				if (!status) {
					DeviceMessage device = SystemSession.get();
					LOG.info("手机号" + mobile + ":手机号已注册------------->AppVersion:" + device.getAppVersion() + ";hardware"
							+ device.getHardware() + ";systemVersion" + device.getSystemVersion());
					return KePuResult.ok(ResultConstant.code_yewu, "手机号已注册", "");
				} else {
					String pin = IdentifyCode.getIdentifyCode();
					if (!StringUtil.isMobileNO(mobile))
						return KePuResult.ok(ResultConstant.code_yewu, "请输入正确的手机号码", "");
					String open = sysService.getParam("open");
					int f = StringUtil.isNotEmpty(open) && "1".equals(open) ? 1 : 0;
					if (f == 1 && !"0576".equals(FindMobileAddress.getRequest1(mobile))) {
						return KePuResult.ok(ResultConstant.code_yewu, "获取验证码失败", "");
					}
					SendTemplateSMSUtil.SendTemplateSMS(mobile, pin, "5");
					redisService.set("sms_" + mobile, pin, 5 * 60);
					return KePuResult.ok(ResultConstant.code_ok, "短信发送成功", "");
				}
			} else if ("2".equals(pinflag)) {
				if (status) {
					return KePuResult.build(ResultConstant.code_yewu, "未注册手机,请重新输入", "");
				} else {
					String pin = IdentifyCode.getIdentifyCode();
					if (!StringUtil.isMobileNO(mobile))
						return KePuResult.ok(ResultConstant.code_yewu, "请输入正确的手机号码", "");
					SendTemplateSMSUtil.SendTemplateSMS(mobile, pin, "5");
					redisService.set("sms_find_password_" + mobile, pin, 5 * 60);
					return KePuResult.ok(ResultConstant.code_ok, "短信发送成功", "");
				}
			} else {
				return KePuResult.build(ResultConstant.code_yewu, "pinflag类型不正确", "");
			}
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 找回密码
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "setPassword", method = RequestMethod.POST)
	public @ResponseBody Object setPassword(@RequestBody Map<String, String> map) {
		try {
			String account = "";
			String pin = "";
			String newpassword = "";
			StringBuffer sb = new StringBuffer();
			if (map.containsKey("account")) {
				account = map.get("account");
			} else {
				sb.append("account").append(",");
			}
			if (map.containsKey("pin"))
				pin = map.get("pin");
			else {
				sb.append("pin").append(",");
			}
			if (map.containsKey("newpassword"))
				newpassword = map.get("newpassword");
			else {
				sb.append("newpassword").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			if (!redisService.checkSmsCode(account, pin, 2)) {
				return KePuResult.build(ResultConstant.code_SmsError, "验证码错误或已过期", "");
			}
			return userService.resetPassword(account, newpassword);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 获取个人信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "getUserMessage")
	public @ResponseBody Object getUserMessage(HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			// 缓存中取用户信息
			StUser stUser2 = userService.getUserByToken(token);
			if (stUser2 == null) {
				return KePuResult.build(ResultConstant.code_yewu, "用户id错误", "");
			}
			int userId = stUser2.getUserid();
			StUser stUser = userService.getUserById(userId);
			stUser.setPassword(null);
			Map<String, String> map = new HashMap<String, String>();
			map.put("nickName", stUser.getNickname());
			map.put("userId", stUser.getUserid() + "");
			map.put("avatar", stUser.getAvatar());
			map.put("sex", stUser.getSex() + "");
			map.put("address", stUser.getAddress());
			map.put("mobile", stUser.getMobile());
			DateTime time = new DateTime(stUser.getBirthday());
			map.put("birthday", time.getYear() + "," + time.getMonthOfYear());
			map.put("career", stUser.getCareer() + "");
			map.put("education", stUser.getEducation() + "");
			map.put("area", stUser.getArea() + "");
			DeviceMessage device = SystemSession.get();
			if (device != null && device.getApplication().equalsIgnoreCase("IOS")) {
				String ad = villageService.getVillageName(stUser.getArea());
				map.put("address", ad);
			} else {
				map.put("address", stUser.getAddress() + "");
			}
			return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
		} catch (Exception e) {
			return KePuResult.ok(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 修改个人信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "setUserMessage")
	public @ResponseBody Object setUserMessage(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			String sex = map.get("sex");
			String nickname = map.get("nickname");
			String address = map.get("address");
			String avatar = map.get("avatar");
			String birthday = map.get("birthday");
			String career = map.get("career");
			String education = map.get("education");
			String area = map.get("area");
			Map<String, String> data = null;
			if (StringUtil.isNotEmpty(nickname))
				user.setNickname(nickname);
			;
			if (StringUtil.isNotEmpty(sex))
				user.setSex(Integer.valueOf(sex));
			if (StringUtil.isNotEmpty(address))
				user.setAddress(address);
			if (StringUtil.isNotEmpty(career))
				user.setCareer(Integer.valueOf(career));
			if (StringUtil.isNotEmpty(birthday)) {
				user.setBirthday(DateUtil.formatString(birthday, "yyyy,MM"));
			}
			if (StringUtil.isNotEmpty(area))
				user.setArea(Integer.valueOf(area));
			if (StringUtil.isNotEmpty(education))
				user.setEducation(Integer.valueOf(education));
			if (StringUtil.isNotEmpty(avatar)) {
				LOG.info("修改头像开始------------");
				String str[] = avatar.split(",");
				String type = str[0].split(";")[0].split(":")[1].split("/")[1];
				avatar = str[1];
				byte[] b = ImgHelperUtil.decode(avatar);
				String result = HttpClientUtilNew.doPostFile("", b, type);
				if (StringUtil.isNotEmpty(result))
					user.setAvatar(result);
				data = new HashMap<String, String>();
				data.put("url", result);
				LOG.info("修改头像结束------------" + result);
			}
			userService.updateStUser(user, token);
			return KePuResult.build(ResultConstant.code_ok, "修改成功", data);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 重置密码
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "resetPassword")
	public @ResponseBody Object resetPassword(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			StringBuffer sb = new StringBuffer();
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			String password = "";
			String newPassword = "";
			if (map.containsKey("password"))
				password = map.get("password");
			else {
				sb.append("password").append(",");
			}
			if (map.containsKey("newPassword"))
				newPassword = map.get("newPassword");
			else {
				sb.append("newPassword").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			return userService.resetMyPassword(user.getUserid(), password, newPassword);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 发表新闻评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "sentComment")
	public @ResponseBody Object sentComment(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			StringBuffer sb = new StringBuffer();
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			String comment = map.get("comment");
			String newsId = map.get("newsId");
			if (map.containsKey("comment"))
				comment = map.get("comment");
			else {
				sb.append("comment").append(",");
			}
			if (map.containsKey("newsId"))
				newsId = map.get("newsId");
			else {
				sb.append("newsId").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			return userService.sentComment(user, Integer.valueOf(newsId), comment);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 回复评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "replyComment")
	public @ResponseBody Object replyComment(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			StringBuffer sb = new StringBuffer();
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			String comment = map.get("comment");
			String commentId = map.get("commentId");
			if (map.containsKey("comment"))
				comment = map.get("comment");
			else {
				sb.append("comment").append(",");
			}
			if (map.containsKey("commentId"))
				commentId = map.get("commentId");
			else {
				sb.append("commentId").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			return userService.replyComment(user, Long.valueOf(commentId), comment);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 收藏新闻
	 * 
	 * @return
	 */
	@RequestMapping(value = "likeNews")
	public @ResponseBody Object likeNews(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			StringBuffer sb = new StringBuffer();
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			String newsId = map.get("newsId");
			if (map.containsKey("newsId"))
				newsId = map.get("newsId");
			else {
				sb.append("newsId").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			return userService.likeNews(Integer.valueOf(newsId), user.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 删除收藏的新闻
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete/likeNews")
	public @ResponseBody Object deletelikeNews(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			StringBuffer sb = new StringBuffer();
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			String newsId = map.get("newsId");
			if (map.containsKey("newsId"))
				newsId = map.get("newsId");
			else {
				sb.append("newsId").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			String[] newsIds = newsId.split(",");
			return userService.deletelikeNews(newsIds, user.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 举报新闻评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "report/{commentId}")
	public @ResponseBody Object reportNewsComment(@PathVariable Long commentId, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			StringBuffer sb = new StringBuffer();
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			return sysService.reportNewsComment(user.getUserid(), commentId);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 获取服务器时间
	 * 
	 * @return
	 */
	@RequestMapping(value = "getSystemTime")
	public @ResponseBody Object getSystemTime() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("systemTime", DateUtil.formatDate(new Date(), MyConstant.updatetime));
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	/**
	 * 开机页
	 * 
	 * @return
	 */
	@RequestMapping(value = "launchPage")
	public @ResponseBody Object launchPage() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("pic_url", sysService.getLaunchPage());
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	/**
	 * 关于我们
	 * 
	 * @return
	 */
	@RequestMapping(value = "aboutUS")
	public @ResponseBody Object aboutUS() {
		Map<String, String> map = new HashMap<String, String>();
		String str = sysService.getAboutUs();
		map.put("content", str);
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	/**
	 * 意见反馈
	 * 
	 * @return
	 */
	@RequestMapping(value = "sentAdvice")
	public @ResponseBody Object sentAdvice(@RequestBody Map<String, String> map, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			StringBuffer sb = new StringBuffer();
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户id错误", "");
			}
			String comment = map.get("comment");
			if (map.containsKey("comment"))
				comment = map.get("comment");
			else {
				sb.append("comment").append(",");
			}
			if (sb.length() != 0) {
				return KePuResult.build(ResultConstant.code_param, "以下参数不能为空" + sb.toString(), "");
			}
			return userService.sentAdvice(user.getUserid(), comment);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 评论/回复点赞
	 * 
	 * @return
	 */
	@RequestMapping(value = "praise/{type}/{typeId}")
	public @ResponseBody Object praise(@PathVariable Integer type, @PathVariable Long typeId,
			HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			return userService.praise(type, typeId, user.getUserid());
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 新闻点赞/不喜欢
	 * 
	 * @return
	 */
	@RequestMapping(value = "dp/{newsId}/{type}/{operate}")
	public @ResponseBody Object dp(@PathVariable Integer newsId, @PathVariable Integer type,
			@PathVariable Integer operate, HttpServletRequest request) {
		try {
			// type=1 点赞 type=2 不喜欢
			// operate=1 点击 operate=0 取消
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			return userService.dpNews(newsId, type, user.getUserid(), operate);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 获取我的收藏
	 * 
	 * @return
	 */
	@RequestMapping(value = "getMyLikeNews/{page}")
	public @ResponseBody Object getMyLikeNews(@PathVariable Integer page, HttpServletRequest request) {
		try {
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}

			return userService.getMyLikeNews(user.getUserid(), page, 10);
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e), "");
		}
	}

	/**
	 * 安卓更新版本 2017-11-14
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "getVersion")
	public @ResponseBody Object getVersion() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		DeviceMessage device = SystemSession.get();
		if (device != null && device.getApplication().equalsIgnoreCase("ANDROID")) {
			String Android_Version = sysService.getParameter("Android_Version");
			String Android_Version_url = sysService.getParameter("Android_Version_url");
			map.put("version", Android_Version);
			map.put("url", Android_Version_url);
		} else {
			String IOS_Version = sysService.getParameter("IOS_Version");
			String IOS_url = sysService.getParameter("IOS_url");
			String version = device.getAppVersion();
			int update = 0;
			if (version.compareTo(IOS_Version) < 0)
				update = 1;
			map.put("version", update + "");
			map.put("url", IOS_url);
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	/**
	 * tab点击统计 新闻/乡镇新闻/服务/社团
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "clickTab/{type}")
	public @ResponseBody Object clickTab(@PathVariable Integer type) throws IOException {
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", "");
	}

	/**
	 * tab点击统计 新闻/乡镇新闻/服务/社团
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "share/{type}/{typeId}")
	public @ResponseBody Object share(@PathVariable Integer type, @PathVariable Integer typeId,
			HttpServletRequest request) {
		String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
		StUser user = userService.getUserByToken(token);
		if (user == null) {
			return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
		}
		return userService.share(type, typeId, user.getUserid());
	}

	@RequestMapping(value = "getAreaJson")
	public @ResponseBody Object getAreaJson() throws IOException {
		// List<Map<String,Object>> r=new LinkedList<Map<String,Object>>();
		List<StVillage> list = villageService.getAddressByParent(-1);
		List<Map<String, Object>> l2 = new LinkedList<Map<String, Object>>();
		for (StVillage stVillage : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", stVillage.getId() + "");
			map.put("name", stVillage.getName());
			List<StVillage> l3 = villageService.getAddressByParent(stVillage.getId());
			List<Map<String, Object>> l4 = new LinkedList<Map<String, Object>>();
			for (StVillage stVillage2 : l3) {
				Map<String, Object> map3 = new HashMap<String, Object>();
				map3.put("id", stVillage2.getId() + "");
				map3.put("name", stVillage2.getName());
				l4.add(map3);
			}
			map.put("child", l4);
			l2.add(map);
		}
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", l2);
	}

	/**
	 * 获取帮助
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "getHelp")
	public @ResponseBody Object getHelp() throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", sysService.getParam("content"));
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	/**
	 * 获取阅读竞赛积分
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "getScore")
	public @ResponseBody Object getScore(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
		StUser user = userService.getUserByToken(token);
		if (user == null) {
			return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
		}
		map.put("score", sysService.getActivityScore(user.getUserid()) + "");
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	// IOS 审核开关
	@RequestMapping(value = "getTemp")
	public @ResponseBody Object getTemp() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("temp", sysService.getParameter("temp"));
		return KePuResult.ok(ResultConstant.code_ok, "获取成功", map);
	}

	// 重定向
	@RequestMapping("/login2")
	public @ResponseBody Object login2(HttpServletRequest request) throws Exception {
		return KePuResult.ok(ResultConstant.code_tokenExpire, "请先登录", "");
	}

	// 重定向
	@RequestMapping("/login3")
	public @ResponseBody Object login3(HttpServletRequest request) throws Exception {
		return KePuResult.ok(ResultConstant.code_tokenExpire, "token不存在或已过期", "");
	}

	// 兑换积分功能
	/**
	 * 
	 * @param request consumeScore
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exchange")
		public Object exchange(@RequestBody Map<String, String> map1, HttpServletRequest request) {
		DecimalFormat df = new DecimalFormat("#0.00"); 
		String msg;
			// 判断是否登录
			String token = request.getHeader("baseParams") == null ? "" : request.getHeader("baseParams");
			StUser user = userService.getUserByToken(token);
			if (user == null) {
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
//			Map<String,String> user=new HashMap();
//			user.put("userid", "1039");
			// 判断是否登录结束
			if (user != null) {
				Integer userId = user.getUserid();
//				Integer userId=Integer.valueOf(user.get("userid"));
				//获取用户的积分和余额,和兑换百分比
				Map map= stUserAccountMapper.getUserBalanceAndScore(userId);
				//取出以前的积分
				Double consumeScore=Double.valueOf((String)map1.get("consumeScore"));
				Double score=Double.valueOf((Double) map.get("score"));
				if(consumeScore>score){
					return KePuResult.ok(ResultConstant.code_exception, "积分少于兑换金额", "");
				}
				//取出以前的余额
				Double balance=Double.valueOf((Double)map.get("balance"));
				//取出兑换比例
				Double ratio=Double.valueOf((Double)map.get("present_proportion"));
				//获取传过来的兑换积分
				Double get_money=consumeScore*ratio;
				Double nowMoney=balance+get_money;
				Double nowScore=score-consumeScore;
				Map map2=new HashMap();
				map2.put("userId", userId);
				map2.put("balance", nowMoney);
				map2.put("score", nowScore);
				//更新数据
				int update=stUserAccountMapper.updateUserBalanceAndScore(map2);
				//计算获得的余额结束
				//增加积分兑换记录
				if(update>0){
					WithdrawCash wc=new WithdrawCash();
					wc.setBuyUserId(userId);
					if(null!=map.get("mobile")&&""!=map.get("mobile")){
						wc.setBuyUserPhone((String)map.get("mobile"));
					}
					wc.setBeforeBalance(balance);
					wc.setBeforeScore(score);
					wc.setConsumeScore(consumeScore);//消费积分
					wc.setNowBalance(nowMoney);
					wc.setWcDesc("使用"+consumeScore+"积分兑换:"+get_money+"余额,兑换后余额为:"+nowMoney);
					//获取当前时间
					Date date=LocalDateUtil.getNow();
					wc.setWcTime(date);
					wc.setNowScore(nowScore);
					int in=withdrawCash.insert(wc);
					//增加积分明细记录结束
					if(in>0){
						return KePuResult.ok(ResultConstant.code_ok, "兑换成功", "");
					}
				}
			}
		return KePuResult.ok(ResultConstant.code_exception, "兑换失败", "");
	}

	/**
	 * 收藏商品
	 * @return
	 */
	@RequestMapping(value="likeProduct")
	public @ResponseBody Object likeProduct(@RequestBody Map<String, String> map,HttpServletRequest request){
		try {
			String token=request.getHeader("baseParams")==null?"":request.getHeader("baseParams"); 
			StUser user=userService.getUserByToken(token);
			StringBuffer sb=new StringBuffer();
			if(user==null){
				return KePuResult.ok(ResultConstant.code_yewu, "用户ID错误", "");
			}
			String productId=map.get("productId");
			if(StringUtil.isEmpty(productId))
				return KePuResult.build(ResultConstant.code_param, "productId不能为空", "");
			StProduct product = productService.getProductById(Integer.valueOf(productId));
			if(product==null)
				return KePuResult.build(ResultConstant.code_param, "商品已删除或不存在", "");
			
			return productService.likeProduct(user.getUserid(),Integer.valueOf(productId));
		} catch (Exception e) {
			return KePuResult.build(ResultConstant.code_exception, ExceptionUtil.getStackTrace(e),"");
		}
	}
	
	/**
	 * 分享成功
	 * @return
	 */
	@RequestMapping(value="shareSuccess")
	public @ResponseBody Object shareSuccess(HttpServletRequest request){
		return "yes";
	}
}
