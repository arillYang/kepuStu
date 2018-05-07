package com.kepu.controller;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.kepu.apppay.alipay.util.AlipayConfig;
import com.kepu.mapper.OrderInfoMapper;
import com.kepu.mapper.StActivityRecordMapper;
import com.kepu.mapper.StExchangeBalanceMapper;
import com.kepu.mapper.StIntegralProductMapper;
import com.kepu.mapper.StRecordExchangeMapper;
import com.kepu.mapper.StSignMapper;
import com.kepu.mapper.StUserAccountMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.mapper.WithdrawCashMapper;
import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.StActivityRecord;
import com.kepu.pojo.StExchangeBalance;
import com.kepu.pojo.StIntegralProduct;
import com.kepu.pojo.StRecordExchange;
import com.kepu.pojo.StSign;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StUserAccount;
import com.kepu.pojo.WithdrawCash;

@Controller
@RequestMapping("/duihuan")
public class kepuController {
	@Autowired
	private StIntegralProductMapper integralProductDao;

	@Autowired
	private StRecordExchangeMapper stRecordExchangeMapper;

	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Autowired
	private StActivityRecordMapper stActivityRecordMapper;

	@Autowired
	private StUserAccountMapper userAccountMapper;

	@Autowired
	private WithdrawCashMapper withdrawCashMapper;

	@Autowired
	private StUserMapper stUserMapper;

	@Autowired
	private StSignMapper stSignMapper;

	@Autowired
	private StExchangeBalanceMapper stExchangeBalanceMapper;

	// private JSONObject json;

	/**
	 * 测试首页
	 * 
	 * @param userId
	 * @param session
	 * @return
	 */
	@RequestMapping("/index")
	@ResponseBody
	public Object findintegralmall(@RequestParam("userId") String userId, HttpSession session) {
		JSONObject json = new JSONObject();
		// ModelAndView mav = new ModelAndView();
		// session.setAttribute("userId", userId);
		StUserAccount stUser = userAccountMapper.selectByPrimaryKey(Integer.parseInt(userId));
		StActivityRecord stactiv = stActivityRecordMapper.selectByScoreList(Integer.parseInt(userId));
		StUserAccount stuy = userAccountMapper.selectBylScoreList(Integer.parseInt(userId));
		if (stUser != null) {
			StUserAccount u = new StUserAccount();
			u.setUserid(stactiv.getUserid());
			if (stactiv.getScore() == 0) {
				double d = 0;
				u.setScore(d);// 当前绿币
			} else {
				u.setScore(stactiv.getScore());// 当前绿币
			}
			u.setUpdatetime(new Date());
			if (stUser.getBalance() != null) {
				u.setBalance(stUser.getBalance());// 余额
			} else {
				u.setBalance(0.0);// 余额
			}
			u.setMobile(stactiv.getMobile());// 手机号
			u.setTown(stactiv.getTown());// 乡镇id
			u.setVillage(stactiv.getVillage());// 村id
			u.setShowname(stactiv.getShowname());// 昵称
			userAccountMapper.updateByPrimaryKeySelective(u);
		} else {
			StUserAccount u = new StUserAccount();
			u.setUserid(stactiv.getUserid());
			u.setScore(stactiv.getScore());// 当前绿币
			u.setUpdatetime(new Date());
			u.setCreatetime(new Date());// 创建时间
			u.setBalance(0.0);// 余额
			u.setMobile(stactiv.getMobile());// 手机号
			u.setTown(stactiv.getTown());// 乡镇id
			u.setVillage(stactiv.getVillage());// 村id
			u.setShowname(stactiv.getShowname());// 昵称
			userAccountMapper.insert(u);
		}
		List<StIntegralProduct> list = integralProductDao.findProductlist();
		List<StSign> st = stSignMapper.selectAll(Integer.parseInt(userId));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getDisplaythepicture() == null || list.get(i).getDisplaythepicture().equals("")) {
					list.get(i).setDisplaythepicture("");
				}
				String[] p = list.get(i).getDisplaythepicture().split(",");
				list.get(i).setDisplaythepicture(p[0]);
			}
			json.put("list", list);
		}
		json.put("st", st);// 签到查询
		json.put("town", stuy.getTown());// 排名
		json.put("stu", stuy);// 用户账户

		return json;
	}

	/**
	 * recordlist 兑换记录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("recordlist")
	@ResponseBody
	public Object findrecordofexchange(HttpSession session, @RequestParam("userId") String userId) {
		JSONObject json = new JSONObject();
		// String user = session.getAttribute("userId").toString();
		List<StRecordExchange> st = stRecordExchangeMapper.selectByPrimaryuserid(Integer.parseInt(userId));
		if (st.size() > 0) {
			for (int i = 0; i < st.size(); i++) {
				if (st.get(i).getRecordimg() == null || st.get(i).getRecordimg().equals("")) {
					st.get(i).setRecordimg("");
				}
				String[] p = st.get(i).getRecordimg().split(",");
				st.get(i).setRecordimg(p[0]);
			}
			json.put("list", st);
		}
		return json;
	}

	/**
	 * 商品详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("productdetails")
	@ResponseBody
	public Object productdetails(@RequestParam(value = "id", required = true) int id,HttpSession session) {
		JSONObject json = new JSONObject();
		StIntegralProduct st = integralProductDao.selectByPrimaryKey(id);
		String userId=(String) session.getAttribute("userId");
		StUserAccount stUser = userAccountMapper.selectByPrimaryKey(Integer.parseInt(userId));
		List list = new ArrayList();
		if (st.getDisplaythepicture() != null || st.getDisplaythepicture().equals("")) {
			String[] p = st.getDisplaythepicture().split(",");
			for (int i = 0; i < p.length; i++) {
				list.add(p[i]);
			}
			json.put("p", list);
		}
		json.put("usercore",stUser.getScore());
		json.put("st", st);
		return json;
	}

	/**
	 * 绿币排行
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("rankinglist")
	public ModelAndView rankinglist(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String user = session.getAttribute("userId").toString();
		List<StRecordExchange> st = new ArrayList<StRecordExchange>();
		List<StUserAccount> stu = userAccountMapper.selectBylist();
		StUserAccount stuy = userAccountMapper.selectBylScoreList(Integer.parseInt(user));
		for (int i = 0; i < stu.size(); i++) {
			StUser kl = stUserMapper.selectByPrimaryKey(stu.get(i).getUserid());
			StRecordExchange s = new StRecordExchange();
			s.setRecordimg(kl.getAvatar());// 头像
			s.setRecordname(kl.getNickname());// 昵称
			s.setSum(stu.get(i).getUserid().toString());
			s.setRecordid(stu.get(i).getTown());// 排名
			s.setRecordintegral(stu.get(i).getScore());// 绿币
			st.add(s);
		}
		if (stuy != null) {
			StUser kl = stUserMapper.selectByPrimaryKey(stuy.getUserid());
			StRecordExchange s = new StRecordExchange();
			s.setRecordimg(kl.getAvatar());// 头像
			s.setRecordname(kl.getNickname());// 昵称
			s.setSum(kl.getUserid().toString());
			s.setRecordid(stuy.getTown());// 排名
			s.setRecordintegral(stuy.getScore());// 绿币
			mav.addObject("score", s);
		}
		if (!st.isEmpty()) {
			mav.addObject("liststq", st);
		}
		mav.addObject("modeName", "绿币排行");
		mav.addObject("mainPage", "duihuan/rankinglist.jsp");
		return mav;
	}

	/**
	 * 商品兑换
	 * 
	 * @param proid
	 *            商品id
	 * @param name
	 *            收货人名字
	 * @param phone
	 *            收货人手机号
	 * @param province
	 *            省
	 * @param city
	 *            市
	 * @param area
	 *            区
	 * @param address
	 *            地址
	 * @param session
	 * @return
	 */
	@RequestMapping("insertrankinglist")
	@ResponseBody
	public int insertrankinglist(@RequestParam(value = "proid", required = true) String proid,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "dizi", required = true) String dizi,
			@RequestParam(value = "address", required = true) String address, HttpSession session,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		StIntegralProduct st = integralProductDao.selectByPrimaryKey(Integer.parseInt(proid));
		// StUserAccount user=(StUserAccount) session.getAttribute("stuser");
		StUserAccount user = userAccountMapper
				.selectByPrimaryKey(Integer.parseInt(session.getAttribute("userId").toString()));
		if (st.getNumbe() != 0) {
			if (user != null) {

				if (user.getScore() >= st.getIntegral()) {
					if (user.getBalance() != null) {
						if (Double.valueOf(user.getBalance()) >= Double.valueOf(st.getFreight())) {
							Date now = new Date();
							WithdrawCash w = new WithdrawCash();
							w.setBuyUserId(user.getUserid());// 购买人id
							w.setBuyUserPhone(phone);// 购买人手机号
							w.setConsumeScore(st.getIntegral());// 消费绿币
							double h = user.getScore() - st.getIntegral();// 当前绿币
							w.setWcDesc("使用:" + st.getIntegral() + "绿币兑换:" + st.getCommodityname() + "商品,兑换后绿币为:" + h);// 兑换描述
							w.setNowScore(h);// 当前绿币
							double k = Double.valueOf(user.getBalance()) - Double.valueOf(st.getFreight());
							w.setNowBalance(k);// 当前余额
							w.setWcTime(now);// 兑换日期
							w.setBeforeScore(user.getScore());// 之前绿币
							w.setBeforeBalance(Double.valueOf(user.getBalance()));// 之前余额

							StUserAccount u = new StUserAccount();
							u.setScore(h);
							u.setUserid(user.getUserid());
							u.setBalance(k);

							StActivityRecord stactic = new StActivityRecord();
							stactic.setUserid(user.getUserid());
							stactic.setScore(-st.getIntegral());// 当前绿币
							stactic.setCreatetime(new Date());// 创建时间
							stactic.setMobile(user.getMobile());// 手机号
							stactic.setRest(1.0);
							stactic.setMessage("使用" + st.getIntegral() + "绿币兑换：" + st.getCommodityname() + "商品");
							stactic.setTown(user.getTown());// 乡镇id
							stactic.setVillage(user.getVillage());// 村id
							stactic.setShowname(user.getShowname());// 昵称
							stactic.setType(0);

							OrderInfo order = new OrderInfo();
							order.setOrderAddress(dizi + address);// 收货人地址
							order.setOrderPhone(phone);// 订单收件电话
							order.setOrderZipcode("");// 订单收件邮编
							order.setOrderUser(name);// 订单收件人
							order.setOrderType(1);// 订单类型
							order.setOrderStatu(1);// 0:"待付款";1:"待发货";2:"已发货";3:"交易成功";4:"交易关闭";5:"已评价";6:"支付中";7:充值；8：订单中的商品已全部退款成功
							order.setOrderSubjctId("");// 备注字段
							order.setOrderPay(0);
							order.setOrderDes("");// 订单留言
							order.setOrderDate(now);
							StRecordExchange record = new StRecordExchange();
							if (st.getDisplaythepicture() != null) {
								String[] p = st.getDisplaythepicture().split(",");
								record.setRecordimg(p[0]);// 记录图片
							}
							record.setRecordname(st.getCommodityname());// 商品名字
							record.setRecordtime(now);
							record.setRecordintegral(st.getIntegral());
							record.setUserid(user.getUserid());

							DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
							int p = (int) (Math.random() * 100);
							String k1 = dateFormat.format(now) + String.valueOf(p);
							order.setSellUserId(user.getUserid());
							order.setOrderCode(k1);// 订单号
							order.setProductId(proid);// 商品id
							order.setBillPrice(0.0);// 订单支付金额
							order.setBillNum(0.0);
							order.setOrderLogisticsCode(null);// 订单物流编码
							order.setOrderLogisticsDate(now);// 发货时间
							order.setOrderLogiscompanyCode(null);// 订单物流公司编码
							order.setOrderLogiscompanyPrice(0.0);// 订单物流金额
							order.setSellUserId(st.getUserid());// 卖家的ID
							order.setBuyUserId(user.getUserid());// 购买者id
							order.setPayType(null);// 1：普通支付 2：微信支付 3：支付宝
							order.setPayEndTime(null);// 订单完成时间
							order.setOrderNote("无");// 订单备注[商家]
							order.setBillTitle("无");// 发票抬头
							order.setBillDesc("无");// 发票备注
							order.setBalance(0.0);// 余额抵扣金额
							order.setCredit(0.0);// 绿币抵扣数额
							order.setRatio(0.0);// 下单时后台设置的绿币换算比
							order.setCreateTime(now);
							order.setIsDelete(1);
							StIntegralProduct st1 = integralProductDao.selectByPrimaryKey(st.getId());
							if (st1 != null) {
								if (st1.getNumbe() > 0) {
									withdrawCashMapper.insert(w);
									userAccountMapper.updateByPrimaryKeySelective(u);
									stActivityRecordMapper.insert(stactic);
									stRecordExchangeMapper.insert(record);
									orderInfoMapper.insert(order);
									session.setAttribute("successid", order.getOrderId().toString());
									StIntegralProduct numbe = new StIntegralProduct();
									numbe.setId(st.getId());
									numbe.setNumbe(st.getNumbe() - 1);
									integralProductDao.updateByPrimaryKeySelective(numbe);
									return order.getOrderId();
								}
								return 4;// 数量不足
							}

						}
						return 2;// 余额不足

					}
					return 2;// 余额不足
				}
				return 1;// 绿币不足

			}
			return 0;// 兑换失败
		}
		return 4;// 数量不足
	}

	/**
	 * 兑换成功
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("productsuc")
	@ResponseBody
	public Object success(HttpServletRequest request, HttpSession session, @RequestParam("orderId") String orderId) {
		JSONObject json = new JSONObject();
		OrderInfo orde = orderInfoMapper.selectByPrimaryKey(Integer.parseInt(orderId));
		StIntegralProduct st = integralProductDao.selectByPrimaryKey(Integer.parseInt(orde.getProductId()));
		if (st.getDisplaythepicture() == null || st.getDisplaythepicture().equals("")) {
			st.setDisplaythepicture("");
		}
		String[] p = st.getDisplaythepicture().split(",");
		for (int i = 0; i < p.length; i++) {
			String[] p1 = st.getDisplaythepicture().split(",");

			st.setDisplaythepicture(p[0]);
		}

		// ModelAndView mav = new ModelAndView();
		json.put("orde", orde);
		json.put("st", st);
		return json;

	}

	// 计算日期可以直接天数相减也可以转换成毫秒计算
	public static long differentDaysByMillisecond(Date date1, Date date2) {
		long days = (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24);

		return days;
	}

	/**
	 * 绿币明细
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("detaillist")
	@ResponseBody
	public Object detaillist(@RequestParam(value = "userId", required = true) int userId, HttpSession session) {
		JSONObject json = new JSONObject();
		// String userId = (String) session.getAttribute("userId");
		StExchangeBalance stexchang = stExchangeBalanceMapper.selectByPrimaryKey(1);
		stexchang.getExchange();// 积分比例
		ModelAndView mav = new ModelAndView();
		SimpleDateFormat fE = new SimpleDateFormat("yyyy-MM");
		List<StActivityRecord> stard = stActivityRecordMapper.selectByMeitScorList(userId, fE.format(new Date()));
		StUserAccount stu = userAccountMapper.selectByPrimaryKey(userId);
		String[] star = null;
		if (stard.size() > 0) {
			for (int g = 0; g < stard.size(); g++) {
				star = stard.get(g).getMessage().split(",");
				stard.get(g).setMessage(star[0]);
			}
			// 格式化数值 保留两位小数 四舍五入
			DecimalFormat df = new DecimalFormat("0.00");
			df.setRoundingMode(RoundingMode.HALF_UP);
			json.put("stexchang", stexchang.getExchange());
			json.put("user", stu);
			json.put("list", stard);
		}
		return json;

	}

	/**
	 * 获取绿币记录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("earnwaylist")
	@ResponseBody
	public Object earnwaylist(@RequestParam(value = "userId", required = true) int userId, HttpSession session) {
		JSONObject json = new JSONObject();
		// String userId = (String) session.getAttribute("userId");
		SimpleDateFormat fE = new SimpleDateFormat("yyyy-MM");
		List<StActivityRecord> stard = stActivityRecordMapper.selectByMeitlist(userId, fE.format(new Date()));
		String[] star = null;
		if (!stard.isEmpty()) {
			for (int g = 0; g < stard.size(); g++) {
				star = stard.get(g).getMessage().split(",");
				stard.get(g).setMessage(star[0]);
			}
			json.put("list", stard);
		}

		return json;

	}

	/**
	 * 广告页 杨杰
	 * 
	 * @return
	 */
	@RequestMapping("greenback")
	public ModelAndView greenback() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("modeName", "广告页");
		mav.addObject("mainPage", "duihuan/greenback.jsp");
		return mav;
	}

	/**
	 * 提现 杨杰
	 * 
	 * @param session
	 * @return
	 * @throws AlipayApiException
	 */
	@RequestMapping("withdraw")
	@ResponseBody
	public Object withdraw(HttpSession session, @RequestParam("amount") double amount, HttpServletRequest rs)
			throws AlipayApiException {
		JSONObject json = new JSONObject();
		// 格式化数值 保留两位小数 四舍五入
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		// 设置当前获取金额
		json.put("amount", df.format(amount));

		return json;
	}

	/**
	 * 提现 杨杰
	 * 
	 * @param session
	 * @return
	 * @throws AlipayApiException
	 */
	@RequestMapping(value = "withdrawPay", method = RequestMethod.POST)
	@ResponseBody
	public String withdrawPay(HttpSession session, @RequestParam("username") String username,
			@RequestParam("account") String account, @RequestParam("amount") Double amount, HttpServletRequest rs)
			throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
				AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
				AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		Long outs = System.currentTimeMillis();
		String outno = outs.toString();
		request.setBizContent("{" + "\"out_biz_no\":\"" + outno + "\"," + "\"payee_type\":\"ALIPAY_LOGONID\","
				+ "\"payee_account\":\"" + account + "\"," + "\"amount\":\"" + amount + "\"," + "\"payee_real_name\":\""
				+ username + "\"," + "\"remark\":\"提现备注\"" + "}");
		AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
		if (response.isSuccess()) {
			System.out.println("调用成功");
			return "1";
		} else {
			System.out.println("调用失败");
			return "2";
		}
	}

	/**
	 * 提现 成功
	 * 
	 * @param session
	 * @return
	 * @throws AlipayApiException
	 */
	@RequestMapping("withOk")
	@ResponseBody
	public Object withOk(@RequestParam(value = "userId", required = true) int userId, HttpSession session,
			HttpServletRequest rs) throws AlipayApiException {
		// 渲染返回实体类
		JSONObject json = new JSONObject();
		// 得到session 域中的值
		// String userId = session.getAttribute("userId").toString();
		// 根据id得到账户值 对象
		StUserAccount user = userAccountMapper.selectByPrimaryKey(userId);
		// 新建一个账户实体对象
		StUserAccount u = new StUserAccount();
		// 绿币获取记录实体对象
		StActivityRecord stactic = new StActivityRecord();
		// 赋值
		stactic.setUserid(user.getUserid());
		stactic.setScore(-user.getScore());// 当前绿币
		stactic.setCreatetime(new Date());// 创建时间
		stactic.setMobile(user.getMobile());// 手机号
		stactic.setRest(1.0);
		stactic.setMessage("当前绿币已经提现");
		stactic.setTown(user.getTown());// 乡镇id
		stactic.setVillage(user.getVillage());// 村id
		stactic.setShowname(user.getShowname());// 昵称
		stactic.setType(0);
		// 创建获取记录
		stActivityRecordMapper.insert(stactic);
		u.setBalance(user.getBalance());
		u.setMobile(user.getMobile());
		u.setScore(0.0);
		u.setUserid(user.getUserid());
		u.setVillage(user.getVillage());
		u.setUpdatetime(new Date());
		u.setShowname(user.getShowname());
		u.setTown(user.getTown());
		// 提现成功 修改绿币总和
		userAccountMapper.updateByPrimaryKeySelective(u);
		json.put("modeName", "提现成功");
		return json;

	}

	/**
	 * 杨杰 签到前的查询
	 */
	@RequestMapping("signCheck")
	@ResponseBody
	public List<StSign> signCheck(HttpSession session) {
		String userId = session.getAttribute("userId").toString();
		List<StSign> st = stSignMapper.selectAll(Integer.parseInt(userId));
		if (st.size() != 0) {
			for (int i = 0; i < st.size(); i++) {
				if (st.get(i).getKeysta() == 7) {
					List<StSign> si = stSignMapper.selectAlltype(Integer.parseInt(userId), st.get(i).getDays(),
							st.get(i).getDays());
					for (int i1 = 0; i1 < si.size(); i1++) {
						SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM");
						String g2 = f1.format(new Date());
						String g1 = f1.format(si.get(i1).getSigndate());
						System.err.println("dasdljajasflas");
						if (g2.equals(g1)) {
							SimpleDateFormat fy = new SimpleDateFormat("dd");
							String sql = fy.format(si.get(i1).getSigndate());
							si.get(i1).setSqlsigndate(Integer.parseInt(sql));
							String creenttime = fy.format(new Date());
							si.get(0).setAjxsigndate(creenttime);
						}
					}
					return si;
				} else {
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
					String g = f.format(new Date());
					if (st.get(i) != null) {
						String g1 = f.format(st.get(i).getSigndate());
						if (g.equals(g1)) {
							SimpleDateFormat y = new SimpleDateFormat("dd");
							String sql = y.format(st.get(i).getSigndate());
							st.get(i).setSqlsigndate(Integer.parseInt(sql));
							String creenttime = y.format(new Date());
							st.get(0).setAjxsigndate(creenttime);
						}
					}
					// System.err.println("dsad");
				}
			}
		}
		// System.err.println("dsad");
		return st;
	}

	private StSign selectAlltype(int parseInt, Integer type) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 杨杰 签到
	 * 
	 * @param keys
	 * @param session
	 * @return
	 */
	@RequestMapping("sign")
	@ResponseBody
	public int sign(@RequestParam("keysta") int keys, String userId, HttpSession session) {
		// 定义变量
		// String userId = session.getAttribute("userId").toString();
		StSign sty = stSignMapper.findtype(Integer.parseInt(userId), keys);
		StSign s = new StSign();
		/*
		 * List<StSign> stdate = stSignMapper.selectAll(Integer.parseInt(userId));
		 */
		List<StSign> hads = stSignMapper.selectAllUserId(Integer.parseInt(userId));
		StUserAccount user = userAccountMapper.selectByPrimaryKey(Integer.parseInt(userId));
		if (sty != null) {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < hads.size(); i++) {
				if (sim.format(new Date()).equals(sim.format(hads.get(i).getSigndate()))) {
					return 0;// 已签到
				} else {// 没有
					StSign st = stSignMapper.findtype(Integer.parseInt(userId), keys - 1);
					if (st != null) {
						SimpleDateFormat sim1 = new SimpleDateFormat("dd");
						String k1 = sim1.format(new Date());
						String k = sim1.format(st.getSigndate());
						int j = Integer.parseInt(k1) - 1;
						if (Integer.parseInt(k1) - 1 == Integer.parseInt(k)) {
							if (hads.get(i).getKeysta() == 7) {
								StSign stqw = stSignMapper.findtype(Integer.parseInt(userId), keys);
								s.setUserid(Integer.parseInt(userId));
								s.setSigndate(new Date());
								s.setScore(keys);
								s.setType(st.getType());
								if (stqw.getDays() == 0) {
									s.setDays(2);
									s.setScore(keys);
								} else {
									s.setScore(7);
									s.setDays(stqw.getDays() + 1);
								}
								s.setKeysta(keys);
								stSignMapper.updateByPrimaryKeySelective(s);

								StActivityRecord stactic = new StActivityRecord();
								stactic.setUserid(Integer.parseInt(userId));
								if (st.getKeysta() > 1) {
									stactic.setScore((double) 7);// 当前绿币
									stactic.setMessage("签到增加" + 7 + "绿币");
								} else {
									stactic.setScore((double) keys);// 当前绿币
									stactic.setMessage("签到增加" + keys + "绿币");
								}
								stactic.setCreatetime(new Date());// 创建时间
								stactic.setMobile(user.getMobile());// 手机号
								stactic.setRest(1.0);
								stactic.setTown(user.getTown());// 乡镇id
								stactic.setVillage(user.getVillage());// 村id
								stactic.setShowname(user.getShowname());// 昵称
								stactic.setType(6);
								stActivityRecordMapper.insert(stactic);
								return 1;

							} else {
								s.setUserid(Integer.parseInt(userId));
								s.setSigndate(new Date());
								s.setType(st.getType());
								if (keys == 7) {
									if (st.getDays() > 0) {
										s.setDays(st.getDays() + 1);
									} else {
										s.setDays(2);
										s.setScore(7);
									}
								} else {
									if (st.getDays() > 0) {
										s.setDays(st.getDays());
										s.setScore(7);
									} else {
										s.setDays(st.getDays());
										s.setScore(keys);
									}
								}
								s.setKeysta(keys);
								stSignMapper.updateByPrimaryKeySelective(s);
								StActivityRecord stactic = new StActivityRecord();
								stactic.setUserid(Integer.parseInt(userId));
								if (st.getType() > 1) {

									stactic.setScore((double) 7);// 当前绿币
									stactic.setMessage("签到增加" + 7 + "绿币");
								} else {
									stactic.setScore((double) keys);// 当前绿币
									stactic.setMessage("签到增加" + keys + "绿币");
								}
								stactic.setCreatetime(new Date());// 创建时间
								stactic.setMobile(user.getMobile());// 手机号
								stactic.setRest(1.0);

								stactic.setTown(user.getTown());// 乡镇id
								stactic.setVillage(user.getVillage());// 村id
								stactic.setShowname(user.getShowname());// 昵称
								stactic.setType(6);
								stActivityRecordMapper.insert(stactic);
								return 1;
							}
						} else {// 不等于
							if (keys == hads.get(i).getKeysta() + 1) {
								StSign stg = stSignMapper.findtype(Integer.parseInt(userId), hads.get(i).getKeysta());
								SimpleDateFormat sim11 = new SimpleDateFormat("dd");
								String k11 = sim11.format(new Date());
								String k12 = sim11.format(stg.getSigndate());
								if (Integer.parseInt(k11) - 1 == Integer.parseInt(k12)) {

								} else {
									s.setUserid(Integer.parseInt(userId));
									s.setType(0);
									s.setDays(0);
									stSignMapper.updateByPrimaryKeyType(s);
									return 1;
								}
							}
						}

					} else {
						StSign st1 = stSignMapper.findtype(Integer.parseInt(userId), 7);
						if (st1 != null) {
							SimpleDateFormat sim1 = new SimpleDateFormat("dd");
							String k1 = sim1.format(new Date());
							String k = sim1.format(st1.getSigndate());
							if (Integer.parseInt(k1) - 1 == Integer.parseInt(k)) {
								s.setUserid(Integer.parseInt(userId));
								s.setSigndate(new Date());

								if (st1.getDays() != 0) {
									s.setType(st1.getDays());
									s.setDays(st1.getDays());
									s.setScore(7);
								} else {
									s.setType(1);
									s.setDays(0);
									s.setScore(keys);
								}

								s.setKeysta(keys);
								stSignMapper.updateByPrimaryKeySelective(s);
								StActivityRecord stactic = new StActivityRecord();
								stactic.setUserid(Integer.parseInt(userId));
								if (st1.getDays() != 0) {
									stactic.setScore(7.0);// 当前绿币
									stactic.setMessage("签到增加" + 7 + "绿币");
								} else {
									stactic.setScore((double) keys);// 当前绿币
									stactic.setMessage("签到增加" + keys + "绿币");
								}
								stactic.setCreatetime(new Date());// 创建时间
								stactic.setMobile(user.getMobile());// 手机号
								stactic.setRest(1.0);
								stactic.setTown(user.getTown());// 乡镇id
								stactic.setVillage(user.getVillage());// 村id
								stactic.setShowname(user.getShowname());// 昵称
								stactic.setType(6);
								stActivityRecordMapper.insert(stactic);
								return 1;
							} else {
								s.setUserid(Integer.parseInt(userId));
								s.setSigndate(new Date());
								s.setScore(keys);
								if (st1.getDays() != 0) {
									s.setType(st1.getDays());
									s.setDays(st1.getDays());
								} else {
									s.setType(1);
									s.setDays(0);
								}
								s.setKeysta(keys);
								stSignMapper.updateByPrimaryKeySelective(s);
								StActivityRecord stactic = new StActivityRecord();
								stactic.setUserid(Integer.parseInt(userId));
								stactic.setScore((double) keys);// 当前绿币
								stactic.setCreatetime(new Date());// 创建时间
								stactic.setMobile(user.getMobile());// 手机号
								stactic.setRest(1.0);
								stactic.setMessage("签到增加" + keys + "绿币");
								stactic.setTown(user.getTown());// 乡镇id
								stactic.setVillage(user.getVillage());// 村id
								stactic.setShowname(user.getShowname());// 昵称
								stactic.setType(6);
								stActivityRecordMapper.insert(stactic);
								return 1;

							}
						} else {
							if (sty == null) {
								s.setSigndate(new Date());
								s.setUserid(Integer.parseInt(userId));
								s.setType(1);
								s.setScore(1);
								s.setKeysta(keys);
								s.setDays(2);
								stSignMapper.insert(s);
								StActivityRecord stactic = new StActivityRecord();
								stactic.setUserid(Integer.parseInt(userId));
								stactic.setScore((double) 7);// 当前绿币
								stactic.setCreatetime(new Date());// 创建时间
								stactic.setMobile(user.getMobile());// 手机号
								stactic.setRest(1.0);
								stactic.setMessage("签到增加" + 7 + "绿币");
								stactic.setTown(user.getTown());// 乡镇id
								stactic.setVillage(user.getVillage());// 村id
								stactic.setShowname(user.getShowname());// 昵称
								stactic.setType(6);
								stActivityRecordMapper.insert(stactic);
								return 1;
							} else {
								s.setUserid(Integer.parseInt(userId));
								s.setSigndate(new Date());
								s.setType(1);
								if (sty.getKeysta() == 7) {
									s.setDays(2);
									s.setScore(7);
								} else {
									s.setDays(0);
									s.setScore(1);
								}
								s.setKeysta(keys);
								stSignMapper.updateByPrimaryKeySelective(s);

								StActivityRecord stactic = new StActivityRecord();
								stactic.setUserid(Integer.parseInt(userId));
								stactic.setScore((double) keys);// 当前绿币
								stactic.setCreatetime(new Date());// 创建时间
								stactic.setMobile(user.getMobile());// 手机号
								stactic.setRest(1.0);
								stactic.setMessage("签到增加" + keys + "绿币");
								stactic.setTown(user.getTown());// 乡镇id
								stactic.setVillage(user.getVillage());// 村id
								stactic.setShowname(user.getShowname());// 昵称
								stactic.setType(6);
								stActivityRecordMapper.insert(stactic);
								return 1;
							}
						}
					}
				}
			}
		}
		if (sty == null) {
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			if (hads.size() == 0) {
				if (sty == null) {
					if (keys == 1) {
						s.setSigndate(new Date());
						s.setUserid(Integer.parseInt(userId));
						s.setType(1);
						s.setScore(1);
						s.setKeysta(keys);
						if (keys == 7) {
							s.setDays(2);
						} else {
							s.setDays(0);
						}
						stSignMapper.insert(s);
						StActivityRecord stactic = new StActivityRecord();
						stactic.setUserid(Integer.parseInt(userId));
						stactic.setScore((double) keys);// 当前绿币
						stactic.setCreatetime(new Date());// 创建时间
						stactic.setMobile(user.getMobile());// 手机号
						stactic.setRest(1.0);
						stactic.setMessage("签到增加" + keys + "绿币");
						stactic.setTown(user.getTown());// 乡镇id
						stactic.setVillage(user.getVillage());// 村id
						stactic.setShowname(user.getShowname());// 昵称
						stactic.setType(6);
						stActivityRecordMapper.insert(stactic);
						return 1;
					}
					return 0;
				}
			}

			for (int i = 0; i < hads.size(); i++) {
				if (sim.format(new Date()).equals(sim.format(hads.get(i).getSigndate()))) {
					return 0;
				} else {
					StSign st = stSignMapper.findtype(Integer.parseInt(userId), keys - 1);
					if (st != null) {
						SimpleDateFormat sim1 = new SimpleDateFormat("dd");
						String k1 = sim1.format(new Date());
						String k = sim1.format(st.getSigndate());
						int j = Integer.parseInt(k1) - 1;
						if (Integer.parseInt(k1) - 1 == Integer.parseInt(k)) {
							if (hads.get(i).getKeysta() == 7) {
								StSign stqw = stSignMapper.findtype(Integer.parseInt(userId), keys);
								s.setUserid(Integer.parseInt(userId));
								s.setSigndate(new Date());
								s.setType(st.getType());
								s.setScore(keys);
								if (stqw.getDays() == 0) {
									s.setDays(2);
								} else {
									s.setDays(stqw.getDays() + 1);
								}
								s.setKeysta(keys);
								stSignMapper.updateByPrimaryKeySelective(s);

								StActivityRecord stactic = new StActivityRecord();
								stactic.setUserid(Integer.parseInt(userId));
								if (stqw.getKeysta() > 1) {

									stactic.setScore((double) 7);// 当前绿币
									stactic.setMessage("签到增加" + 7 + "绿币");
								} else {
									stactic.setScore((double) keys);// 当前绿币
									stactic.setMessage("签到增加" + keys + "绿币");
								}
								stactic.setCreatetime(new Date());// 创建时间
								stactic.setMobile(user.getMobile());// 手机号
								stactic.setRest(1.0);
								stactic.setTown(user.getTown());// 乡镇id
								stactic.setVillage(user.getVillage());// 村id
								stactic.setShowname(user.getShowname());// 昵称
								stactic.setType(6);
								stActivityRecordMapper.insert(stactic);
								return 1;
							} else {
								if (sty != null) {
									s.setUserid(Integer.parseInt(userId));
									s.setSigndate(new Date());
									s.setType(st.getType());
									if (keys == 7) {
										s.setDays(2);
									} else {
										s.setDays(0);
									}
									s.setKeysta(keys);
									s.setScore(keys);
									stSignMapper.updateByPrimaryKeySelective(s);
									StActivityRecord stactic = new StActivityRecord();
									stactic.setUserid(Integer.parseInt(userId));
									if (sty.getKeysta() > 1) {
										stactic.setScore((double) 7);// 当前绿币
										stactic.setMessage("签到增加" + 7 + "绿币");
									} else {
										stactic.setScore((double) keys);// 当前绿币
										stactic.setMessage("签到增加" + keys + "绿币");
									}
									stactic.setCreatetime(new Date());// 创建时间
									stactic.setMobile(user.getMobile());// 手机号
									stactic.setRest(1.0);

									stactic.setTown(user.getTown());// 乡镇id
									stactic.setVillage(user.getVillage());// 村id
									stactic.setShowname(user.getShowname());// 昵称
									stactic.setType(6);
									stActivityRecordMapper.insert(stactic);
									return 1;
								} else {
									s.setSigndate(new Date());
									s.setUserid(Integer.parseInt(userId));
									s.setType(1);
									s.setScore(1);
									s.setKeysta(keys);
									if (keys == 7) {
										s.setDays(2);
									} else {
										s.setDays(0);
									}
									stSignMapper.insert(s);
									StActivityRecord stactic = new StActivityRecord();
									stactic.setUserid(Integer.parseInt(userId));
									stactic.setScore((double) keys);// 当前绿币
									stactic.setCreatetime(new Date());// 创建时间
									stactic.setMobile(user.getMobile());// 手机号
									stactic.setRest(1.0);
									stactic.setMessage("签到增加" + keys + "绿币");
									stactic.setTown(user.getTown());// 乡镇id
									stactic.setVillage(user.getVillage());// 村id
									stactic.setShowname(user.getShowname());// 昵称
									stactic.setType(6);
									stActivityRecordMapper.insert(stactic);
									return 1;
								}
							}
						} else {// 不等于
							if (keys == hads.get(i).getKeysta() + 1) {
								StSign stg = stSignMapper.findtype(Integer.parseInt(userId), hads.get(i).getKeysta());
								SimpleDateFormat sim11 = new SimpleDateFormat("dd");
								String k11 = sim11.format(new Date());
								String k12 = sim11.format(stg.getSigndate());
								if (Integer.parseInt(k11) - 1 == Integer.parseInt(k12)) {
									return 0;
								} else {
									s.setUserid(Integer.parseInt(userId));
									s.setType(0);
									s.setDays(0);
									stSignMapper.updateByPrimaryKeyType(s);
									return 1;
								}
							}
						}
					} else {
						return 0;
					}

				}
			}
		}
		return 0;

	}

}
