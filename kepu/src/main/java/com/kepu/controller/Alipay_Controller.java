package com.kepu.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.kepu.apppay.alipay.util.AlipayConfig;
import com.kepu.apppay.alipay.util.AlipayNotify;
import com.kepu.mapper.OrderInfoMapper;
import com.kepu.mapper.ProportionSettingMapper;
import com.kepu.mapper.StProductMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.OrderInfoExample;
import com.kepu.pojo.ProportionSetting;
import com.kepu.pojo.StProduct;
import com.kepu.pojo.StUser;
import com.kepu.util.LocalDateUtil;

@Controller
@RequestMapping(value = "/pay")
@Transactional
/**
 * 
 * @author Ly
 *
 */
public class Alipay_Controller {

	@Autowired
	private OrderInfoMapper orderInfomapper;
	@Autowired
	private StUserMapper stUserMapper;
	@Autowired
	private StProductMapper stProductMapper;
	@Autowired
	private ProportionSettingMapper proportionSettingMapper;

	/**
	 * 预支付获取用户权限code
	 * 
	 * @param pay_type
	 *            支付方式1:余额支付2:微信支付3:支付宝, order_des 订单留言, order_code 订单号 ,address,return_url 返回界面
	 *            地址
	 * @return ModelAndView
	 * @throws IOException
	 */

	@RequestMapping(value = "/dopay")
//	public String getCode(HttpServletRequest request1, HttpServletResponse response1) throws IOException {
	public @ResponseBody  Object getCode(@RequestBody Map<String, String> map, HttpServletRequest request1) {
		
		try {
			String orderCode=null;
			
			if(null!=request1.getParameter("order_code")){
				
				orderCode= request1.getParameter("order_code");
			}else{
				orderCode=map.get("order_code");
			}
			String address=null;
			
			if(null!=request1.getParameter("address")){
				
				address= request1.getParameter("address");
			}else{
				address=map.get("address");
			}
			String order_des=null;
			
			if(null!=request1.getParameter("order_des")){
				
				order_des= request1.getParameter("order_des");
			}else{
				order_des=map.get("order_des");
			}
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY, AlipayConfig.APP_ID,
					AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
					AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
			// 生成订单
			OrderInfoExample example = new OrderInfoExample();
			OrderInfoExample.Criteria criteria = example.createCriteria();
			criteria.andOrderCodeEqualTo(orderCode);
			List<OrderInfo> orderInfo2 = orderInfomapper.selectByExample(example);
			OrderInfo orderInfo = orderInfo2.get(0);
			if (null != orderInfo) {
				if (orderInfo.getOrderStatu() == 0) {//待付款才能进入
					Integer buyUserId = orderInfo.getBuyUserId();// 下单人
					// 获得商家商品内容开始
					Integer productId = Integer.valueOf(orderInfo.getProductId());// 商品id
					Integer payType = Integer.valueOf(3);// 支付方式
					StProduct stProduct = stProductMapper.selectByPrimaryKey(productId);// 获取该商品详情
					BigDecimal money = stProduct.getMoney();// 获取商品价格
					Integer sellUserId = stProduct.getUserid();// sellUserId
					String introduce = stProduct.getIntroduce();// 商家说明
					// 获得商家商品内容结束

					// 获得用户详情开始
					StUser stUser = stUserMapper.selectByPrimaryKey(buyUserId);
					String nickName = stUser.getNickname();// 获取用户昵称
					String mobile = stUser.getMobile();// 获取用户手机号
					// 获得用户详情结束

					// 放入OrderInfo开始
					orderInfo.setBalance(0.00);// 默认积分抵扣数额为0.00
					orderInfo.setBillDesc("无");// 默认发票备注为无
					orderInfo.setBillNum(Double.valueOf(orderInfo.getBillNum()));// 商品数量默认为1
					orderInfo.setBillPrice(money.doubleValue());
					orderInfo.setBillTitle("无");// 默认发票抬头为无
					Date date = LocalDateUtil.getNow();
					orderInfo.setCreateTime(date);
					orderInfo.setCredit(0.00);
					orderInfo.setProductId(String.valueOf(productId));
					orderInfo.setOrderAddress(address);
					orderInfo.setOrderCode(orderCode);
					orderInfo.setOrderDate(date);
					orderInfo.setOrderDes(order_des);// 订单留言
					orderInfo.setOrderNote(introduce);
					orderInfo.setOrderPhone(mobile);
					orderInfo.setOrderStatu(6);// 增加订单状态:支付中
					orderInfo.setOrderUser(nickName);
					orderInfo.setBuyUserId(buyUserId);
					orderInfo.setPayType(payType);
					orderInfo.setSellUserId(sellUserId);
					ProportionSetting setting = proportionSettingMapper.selectByPrimaryKey(1);
					orderInfo.setRatio(setting.getPresentProportion());
					// 放入OrderInfo结束
					// 更新订单
					System.out.println(orderInfo.toString());
					int status = orderInfomapper.updateByPrimaryCode(orderInfo);
					// 更新订单结束
					// 建立请求
					if (status > 0) {
						// String sHtmlText =
						// AlipaySubmit.buildRequest(sParaTemp, "post",
						// "确认");
						AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
						AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
						model.setBody(stProduct.getIntroduce());
						model.setSubject(stProduct.getTitle()); // 商品标题
						model.setOutTradeNo(orderInfo.getOrderCode()); // 商家订单编号
						model.setTimeoutExpress("30m"); // 超时关闭该订单时间
						model.setTotalAmount(money.toString()); // 订单总金额
						model.setProductCode("QUICK_MSECURITY_PAY"); // 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
						request.setBizModel(model);
						request.setNotifyUrl(AlipayConfig.service); // 回调地址
//						request.setReturnUrl("http://kp.appwzd.cn");
						request.setReturnUrl(request1.getParameter("returnUrl"));
						String orderStr = "";
						AlipayTradeAppPayResponse response = alipayClient.execute(request);
						orderStr = response.getBody();
						System.out.println(orderStr);// 就是orderString
						// 可以直接给客户端请求，无需再做处理。
						return orderStr;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("rawtypes")

	@RequestMapping(value = "/async", method = RequestMethod.POST)

	public String async(HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();

		Map requestParams = request.getParameterMap();

		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {

			String name = (String) iter.next();

			String[] values = (String[]) requestParams.get(name);

			String valueStr = "";

			for (int i = 0; i < values.length; i++) {

				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";

			}

			params.put(name, valueStr);

		}

		// 获取返回数据

		String orderTitle = request.getParameter("subject");// 订单名称

		String payType = request.getParameter("payment_type");// 支付类型

		String outTradeNo = request.getParameter("out_trade_no");// 订单号

		String tradeNo = request.getParameter("trade_no");// 支付宝交易号

		String notifyId = request.getParameter("notify_id");// 支付校验id

		String amount = request.getParameter("total_fee");// 交易金额

		String notifyTime = request.getParameter("notify_time");// 通知时间

		String tradeStatus = request.getParameter("trade_status");// 交易状态

		String returnId = request.getParameter("extra_common_param");// 项目id

		String payer = request.getParameter("buyer_email");// 支付者账号

		//

		if (AlipayNotify.verify(params)) {// 验证成功

			if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {

				// 要写的逻辑。自己按自己的要求写

				// 封装交易信息实体，存入数据库之类的

				System.out.println(">>>>>异步返回:" + tradeNo);

			}

			return "success/alipay-success";

		} else {// 验证失败

			return "success/alipay-fail";

		}

	}

	@RequestMapping(value = "/return_url", method = RequestMethod.GET)
	public String Return_url(HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();

		Map requestParams = request.getParameterMap();

		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {

			String name = (String) iter.next();

			String[] values = (String[]) requestParams.get(name);

			String valueStr = "";

			for (int i = 0; i < values.length; i++) {

				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";

			}

			params.put(name, valueStr);

		}

		String tradeNo = request.getParameter("trade_no");// 支付宝交易号

		String tradeStatus = request.getParameter("trade_status");// 交易状态

		if (AlipayNotify.verify(params)) {// 验证成功

			if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {

				// 要写的逻辑。自己按自己的要求写

				System.out.println(">>>>>充值成功" + tradeNo);

			}

			return "...";

		} else {// 验证失败

			return "success/fail";

		}

	}

}
