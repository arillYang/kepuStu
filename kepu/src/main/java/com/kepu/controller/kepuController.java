package com.kepu.controller;

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

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.kepu.apppay.alipay.util.AlipayConfig;
import com.kepu.mapper.OrderInfoMapper;
import com.kepu.mapper.StActivityRecordMapper;
import com.kepu.mapper.StIntegralProductMapper;
import com.kepu.mapper.StRecordExchangeMapper;
import com.kepu.mapper.StSignMapper;
import com.kepu.mapper.StUserAccountMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.mapper.WithdrawCashMapper;
import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.StActivityRecord;
import com.kepu.pojo.StIntegralProduct;
import com.kepu.pojo.StRecordExchange;
import com.kepu.pojo.StSign;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StUserAccount;
import com.kepu.pojo.WithdrawCash;
import com.kepu.service.RedisService;
import com.kepu.service.SysService;
import com.kepu.service.UserService;

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
	private RedisService redisService;
	
	@Autowired
	private StSignMapper stSignMapper;
	
	@Autowired
	private  SysService sysService;	
	
	@Autowired
	private UserService userService;
	

	/**
	 * 测试首页
	 * 
	 * @param userId
	 * @param session
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView findintegralmall(@RequestParam("userId") String userId, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.setAttribute("userId", userId);
		
		StUserAccount stu = userAccountMapper.selectByPrimaryKey(Integer.parseInt(userId));
		session.setAttribute("usercore", stu.getScore());
		List<StIntegralProduct> list = integralProductDao.findProductlist();
		List<StSign> st= stSignMapper.selectAll(Integer.parseInt(userId));
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getDisplaythepicture() == null || list.get(i).getDisplaythepicture().equals("")) {
					list.get(i).setDisplaythepicture("");
				}
				String[] p = list.get(i).getDisplaythepicture().split(",");
				list.get(i).setDisplaythepicture(p[0]);
			}
			mav.addObject("list", list);
			mav.addObject("stu", stu);
			mav.addObject("st",st);

		}
		mav.addObject("modeName", "积分商城");
		mav.addObject("mainPage", "/duihuan/index.jsp");
		return mav;

	}

	
	/**
	 * recordlist 兑换记录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("recordlist")
	public ModelAndView findrecordofexchange(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String user = session.getAttribute("userId").toString();
		List<StRecordExchange> st = stRecordExchangeMapper.selectByPrimaryuserid(Integer.parseInt(user));
		if (st.size() > 0) {
			for (int i = 0; i < st.size(); i++) {
				if (st.get(i).getRecordimg() == null || st.get(i).getRecordimg().equals("")) {
					st.get(i).setRecordimg("");
				}
				String[] p = st.get(i).getRecordimg().split(",");
				st.get(i).setRecordimg(p[0]);
			}
			mav.addObject("list", st);
		}
		mav.addObject("modeName", "积分商城");
		mav.addObject("mainPage", "duihuan/recordlist.jsp");
		return mav;
	}

	/**
	 * 商品详情
	 * @param id
	 * @return
	 */
	@RequestMapping("productdetails")
	public ModelAndView productdetails(int id) {
		ModelAndView mav = new ModelAndView();
		StIntegralProduct st = integralProductDao.selectByPrimaryKey(id);
		List list = new ArrayList();
		if (st.getDisplaythepicture() != null || st.getDisplaythepicture().equals("")) {
			String[] p = st.getDisplaythepicture().split(",");
			for (int i = 0; i < p.length; i++) {
				list.add(p[i]);
			}
			mav.addObject("p", list);
		}
		mav.addObject("st", st);
		mav.addObject("modeName", "积分商城");
		mav.addObject("mainPage", "duihuan/productdetails.jsp");
		return mav;
	}

	/**
	 * 积分排行
	 * @param session
	 * @return
	 */
	@RequestMapping("rankinglist")
	public ModelAndView rankinglist(HttpSession session) {
		ModelAndView mav = new ModelAndView();
	String l=	session.getAttribute("userId").toString();
		List<StRecordExchange> st = new ArrayList<StRecordExchange>();
		List<StUserAccount> stu = userAccountMapper.selectBylist();
		for (int i = 0; i < stu.size(); i++) {
			StUser kl = stUserMapper.selectByPrimaryKey(stu.get(i).getUserid());
			StRecordExchange s = new StRecordExchange();
			s.setRecordimg(kl.getAvatar());// 头像
			s.setRecordname(kl.getNickname());// 昵称
			s.setSum(kl.getUserid().toString());
			s.setRecordid(stu.get(i).getTown());// 排名
			s.setRecordintegral(stu.get(i).getScore());// 积分
			st.add(s);
		}
		if (!st.isEmpty()) {
			mav.addObject("liststq", st);
		}
		mav.addObject("modeName", "积分排行");
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
		if (user != null) {

			if (user.getScore() > st.getIntegral()) {
				if (Double.valueOf(user.getBalance()) > Double.valueOf(st.getFreight())) {
					Date now = new Date();
					WithdrawCash w = new WithdrawCash();
					w.setBuyUserId(user.getUserid());// 购买人id
					w.setBuyUserPhone(phone);// 购买人手机号
					w.setConsumeScore(st.getIntegral());// 消费积分
					double h = user.getScore() - st.getIntegral();// 当前积分
					w.setWcDesc("使用:" + st.getIntegral() + "积分兑换:" + st.getCommodityname() + "商品,兑换后积分为:" + h);// 兑换描述
					w.setNowScore(h);// 当前积分
					double k = Double.valueOf(user.getBalance()) - st.getMoney();
					w.setNowBalance(k);// 当前余额
					w.setWcTime(now);// 兑换日期
					w.setBeforeScore(user.getScore());// 之前积分
					w.setBeforeBalance(Double.valueOf(user.getBalance()));// 之前余额
					withdrawCashMapper.insert(w);

					StUserAccount u = new StUserAccount();
					u.setUserid(user.getUserid());
					u.setScore(h);// 当前积分
					u.setUpdatetime(now);
					u.setBalance(k);
					userAccountMapper.updateByPrimaryKeySelective(u);

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
					stRecordExchangeMapper.insert(record);

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
					order.setCredit(0.0);// 积分抵扣数额
					order.setRatio(0.0);// 下单时后台设置的积分换算比
					order.setCreateTime(now);
					order.setIsDelete(1);
					orderInfoMapper.insert(order);
					session.setAttribute("successid", order.getOrderId().toString());
					return 3;
				}
				return 2;// 余额不足
			}

			return 1;// 积分不足
		}
		return 0;// 兑换失败
	}

	
	
	
	/**
	 * 兑换成功
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("productsuc")
	public ModelAndView success(HttpServletRequest request, HttpSession session) {
		String successid = (String) session.getAttribute("successid");
		OrderInfo orde = orderInfoMapper.selectByPrimaryKey(Integer.parseInt(successid));
		StIntegralProduct st = integralProductDao.selectByPrimaryKey(Integer.parseInt(orde.getProductId()));

		if (st.getDisplaythepicture() == null || st.getDisplaythepicture().equals("")) {
			st.setDisplaythepicture("");
		}
		String[] p = st.getDisplaythepicture().split(",");
		for (int i = 0; i < p.length; i++) {
			String[] p1 = st.getDisplaythepicture().split(",");

			st.setDisplaythepicture(p[0]);
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("orde", orde);
		mav.addObject("st", st);
		mav.addObject("modeName", "兑换成功");
		mav.addObject("mainPage", "duihuan/productsuc.jsp");
		return mav;

	}

	/**
	 * 绿币明细
	 * @param session
	 * @return
	 */
	@RequestMapping("detaillist")
	public ModelAndView detaillist(HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		ModelAndView mav = new ModelAndView();
		List<StActivityRecord> stard = stActivityRecordMapper.selectBylist(Integer.parseInt(userId));
		StUserAccount stu =userAccountMapper.selectByPrimaryKey(Integer.parseInt(userId));
		if (!stard.isEmpty()) {
			mav.addObject("user", stu);
			    double j=stu.getScore()/500;
			    DecimalFormat df = new DecimalFormat("#0.00");
				mav.addObject("d",df.format(j));
			mav.addObject("list", stard);
		}
		mav.addObject("modeName", "积分排行");
		mav.addObject("mainPage", "duihuan/detaillist.jsp");
		return mav;

	}
	

	/**
	 * 获取绿币记录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("earnwaylist")
	public ModelAndView earnwaylist(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String userId = (String) session.getAttribute("userId");
		List<StActivityRecord> stard = stActivityRecordMapper.selectBylist(Integer.parseInt(userId));
		if (!stard.isEmpty()) {
			mav.addObject("list", stard);
		}
		mav.addObject("modeName", "获取绿币记录");
		mav.addObject("mainPage", "duihuan/earnwaylist.jsp");
		return mav;

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
	public ModelAndView withdraw(HttpSession session, @RequestParam("amount") Double amount, HttpServletRequest rs)
			throws AlipayApiException {
		ModelAndView mav = new ModelAndView();
		DecimalFormat df = new DecimalFormat("#.00");
		// 设置当前获取金额
		session.setAttribute("amount", df.format(amount));
		// 根据当前登录用户Id 获取用户手机号码
		StUser stUser = stUserMapper.selectByPrimaryKey(Integer.parseInt(session.getAttribute("userId").toString()));
		String phone = stUser.getMobile();
		// 设置当前获取到的手机号码
		session.setAttribute("phone", phone);
		mav.addObject("modeName", "提现");
		mav.addObject("mainPage", "duihuan/withdraw.jsp");
		return mav;

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
				+ "\"payee_account\":\"" + account + "\"," + "\"amount\":\""+amount+"\"," + "\"payee_real_name\":\"" + username
				+ "\"," + "\"remark\":\"提现备注\"" + "}");
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
	public ModelAndView withOk(HttpSession session, HttpServletRequest rs) throws AlipayApiException {
		ModelAndView mav = new ModelAndView();
		String userId=session.getAttribute("userId").toString();
		StUserAccount user = userAccountMapper.selectByPrimaryKey(Integer.parseInt(userId));
		StUserAccount u = new StUserAccount();
		u.setUserid(user.getUserid());
		u.setScore(0.0);// 当前积分
		userAccountMapper.updateByPrimaryKey(u);
		mav.addObject("modeName", "提现成功");
		mav.addObject("mainPage", "duihuan/withOk.jsp");
		return mav;

	}

	/**
	 * 签到前的查询
	 */
	@RequestMapping("signCheck")
	@ResponseBody
	public List<StSign> signCheck(HttpSession session) {
		String userId = session.getAttribute("userId").toString();
		List<StSign> st = stSignMapper.selectAll(Integer.parseInt(userId));
		System.out.println(st.size());
		for (int i = 0; i < st.size(); i++) {

			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM");
			String g = f.format(new Date());
			String g1 = f.format(st.get(i).getSigndate());
			if (g.equals(g1)) {
				SimpleDateFormat f1 = new SimpleDateFormat("dd");
				String sql = f1.format(st.get(i).getSigndate());
				st.get(i).setSqlsigndate(Integer.parseInt(sql));
			}
		}
		return st;
	}
	
	/**
	 * 杨杰
	 * 签到 
	 * @param keys
	 * @param session
	 * @return
	 */
	@RequestMapping("sign")
	@ResponseBody
	public int sign(@RequestParam("keysta") int keys, HttpSession session) {
		int i = 0;
		String userId = session.getAttribute("userId").toString();
		
		List<StSign> st = stSignMapper.selectAll(Integer.parseInt(userId));
		for (int a = 0; a < st.size(); a++) {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			String g = f.format(st.get(a).getSigndate());
			String g1 = f.format(new Date());
			if (g.equals(g1)) {
				return i;
			}

		}
		// 添加签到记录
		StSign sign = new StSign();
		sign.setUserid(Integer.parseInt(userId));
		sign.setKeysta(keys);
		sign.setScore(4);
		sign.setSigndate(new Date());
		int insert = stSignMapper.insert(sign);
		StUserAccount user = userAccountMapper.selectByPrimaryKey(Integer.parseInt(userId));
		double h = user.getScore() + 4;
		StUserAccount u = new StUserAccount();
		u.setUserid(user.getUserid());
		u.setScore(h);// 当前积分
		userAccountMapper.updateByPrimaryKey(u);
		
		return insert;

	}
}
