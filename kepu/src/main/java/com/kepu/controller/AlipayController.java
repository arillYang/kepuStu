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
import com.kepu.constant.ResultConstant;
import com.kepu.mapper.OrderInfoMapper;
import com.kepu.mapper.ProportionSettingMapper;
import com.kepu.mapper.StProductMapper;
import com.kepu.mapper.StUserMapper;
import com.kepu.pojo.KePuResult;
import com.kepu.pojo.OrderInfo;
import com.kepu.pojo.OrderInfoExample;
import com.kepu.pojo.StProduct;
import com.kepu.pojo.StUser;
import com.kepu.util.LocalDateUtil;
import com.kepu.util.StringUtil;

@Controller
@RequestMapping(value = "/alipay")
/**
 * 
 * @author Ly
 *
 */
public class AlipayController {

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
	 *            支付方式1:余额支付2:微信支付3:支付宝, order_des 订单留言, order_code 订单号
	 *            ,address,return_url 返回界面 地址
	 * @return ModelAndView
	 * @throws IOException
	 */
	@RequestMapping(value = "/dopay")
	public @ResponseBody Object getCode(@RequestBody Map<String, String> map, HttpServletRequest request1) {
		String orderStr = null;// 返回参数
		try {
			String orderCode = null;

			if (null != request1.getParameter("order_code")) {

				orderCode = request1.getParameter("order_code");
			} else {
				orderCode = map.get("order_code");
			}
			String address = null;

			if (null != request1.getParameter("address")) {

				address = request1.getParameter("address");
			} else {
				address = map.get("address");
			}
			String order_des = null;

			if (null != request1.getParameter("order_des")) {

				order_des = request1.getParameter("order_des");
			} else {
				order_des = map.get("order_des");
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
				if (orderInfo.getOrderStatu() == 0) {////orderStatu=0 待付款才能进入
					Integer buyUserId = orderInfo.getBuyUserId();// 买家（客户）
					if(orderInfo.getProductId()==null) return KePuResult.ok(ResultConstant.code_ok, "订单不存在产品", orderStr);
					// 获得商家商品内容开始
					Integer productId = Integer.valueOf(orderInfo.getProductId());//  商品id
					
					// 获取该商品详情
					StProduct stProduct = stProductMapper.selectByPrimaryKey(productId);
					BigDecimal money = stProduct.getMoney();//获取商品价格
					Integer sellUserId = stProduct.getUserid();// 卖家（店铺）
					String introduce = stProduct.getIntroduce();// 商家说明
					// 获得商家商品内容结束
					
					

					// ******获得用户（客户）详情开始
					StUser stUser = stUserMapper.selectByPrimaryKey(buyUserId);
					//订单用户不存自
					if(stUser==null) return KePuResult.ok(ResultConstant.code_ok, "用户不存在", orderStr);
					String nickName = StringUtil.isNotEmpty(stUser.getNickname())?stUser.getNickname():"";// 获取用户昵称
					String mobile = stUser.getMobile();// 获取用户（客户）手机号
					// 获得用户详情结束

					
					// 放入OrderInfo开始
					Double num=orderInfo.getBillNum()==null?1:Double.valueOf(orderInfo.getBillNum());// 商品数量默认为1
					orderInfo.setBalance(0.00);// 默认积分抵扣数额为0.00
					orderInfo.setBillDesc(StringUtil.isNotEmpty(stProduct.getTitle())?stProduct.getTitle():"无");// 默认发票备注为无
					orderInfo.setBillNum(num);// 商品数量默认为1
					orderInfo.setBillPrice(money.doubleValue()*num);
					orderInfo.setBillTitle(StringUtil.isNotEmpty(stProduct.getTitle())?stProduct.getTitle():"无");// 默认发票抬头为无
					Date date = LocalDateUtil.getNow();
					orderInfo.setCreateTime(date);
					//积分抵扣数额
					orderInfo.setCredit(0.00);
					
					orderInfo.setProductId(String.valueOf(productId));
					//订单地址
					orderInfo.setOrderAddress(address);
					//订单号
					orderInfo.setOrderCode(orderCode);
					
					orderInfo.setOrderDate(date);
					// 订单留言
					orderInfo.setOrderDes(order_des);
					orderInfo.setOrderNote(introduce);
					orderInfo.setOrderPhone(mobile);
					orderInfo.setOrderStatu(6);// 增加订单状态:支付中
					orderInfo.setOrderUser(nickName);
					orderInfo.setBuyUserId(buyUserId);
					
					//Integer payType = Integer.valueOf(3);//支付方式  1：普通支付\r\n            2：微信支付            3：支付宝
					orderInfo.setPayType(3);
					// 卖家（店铺）
					orderInfo.setSellUserId(sellUserId);
					//ProportionSetting setting = proportionSettingMapper.selectByPrimaryKey(1);
					//orderInfo.setRatio(setting.getPresentProportion());//提现比例
					// 放入OrderInfo结束
					// 更新订单
					System.out.println(orderInfo.toString());
					int status = orderInfomapper.updateByPrimaryCode(orderInfo);
					// 更新订单结束
					// 建立请求
					if (status > 0) {
						AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
						AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
						model.setBody(stProduct.getIntroduce());
						model.setSubject(stProduct.getTitle()); // 商品标题
						model.setOutTradeNo(orderInfo.getOrderCode()); // 商家订单编号
						model.setTimeoutExpress("30m"); // 超时关闭该订单时间
						model.setTotalAmount(money.toString()); // 订单总金额
						model.setProductCode("QUICK_MSECURITY_PAY"); // 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
						model.setSellerId(AlipayConfig.seller_id);
						request.setBizModel(model);
						request.setNotifyUrl(AlipayConfig.service); // 回调地址
						AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
						orderStr = response.getBody();
						System.out.println(orderStr);// 就是orderString
						// 可以直接给客户端请求，无需再做处理。
					}
				}
			} else {
				return KePuResult.ok(ResultConstant.code_ok, "订单号不存在", orderStr);
			}
			if (null != orderStr && "" != orderStr) {
				return KePuResult.ok(ResultConstant.code_ok, "支付宝返回成功", orderStr);
			} else {
				return KePuResult.ok(ResultConstant.code_exception, "支付宝返回失败", orderStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return KePuResult.ok(ResultConstant.code_exception, "系统异常", orderStr);
		}

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

		// 鑾峰彇杩斿洖鏁版嵁

		String orderTitle = request.getParameter("subject");// 璁㈠崟鍚嶇О

		String payType = request.getParameter("payment_type");// 鏀粯绫诲瀷

		String outTradeNo = request.getParameter("out_trade_no");// 璁㈠崟鍙�

		String tradeNo = request.getParameter("trade_no");// 鏀粯瀹濅氦鏄撳彿

		String notifyId = request.getParameter("notify_id");// 鏀粯鏍￠獙id

		String amount = request.getParameter("total_fee");// 浜ゆ槗閲戦

		String notifyTime = request.getParameter("notify_time");// 閫氱煡鏃堕棿

		String tradeStatus = request.getParameter("trade_status");// 浜ゆ槗鐘舵��

		String returnId = request.getParameter("extra_common_param");// 椤圭洰id

		String payer = request.getParameter("buyer_email");// 鏀粯鑰呰处鍙�

		//

		if (AlipayNotify.verify(params)) {// 楠岃瘉鎴愬姛

			if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {

				// 瑕佸啓鐨勯�昏緫銆傝嚜宸辨寜鑷繁鐨勮姹傚啓

				// 灏佽浜ゆ槗淇℃伅瀹炰綋锛屽瓨鍏ユ暟鎹簱涔嬬被鐨�

				System.out.println(">>>>>寮傛杩斿洖:" + tradeNo);

			}

			return "success/alipay-success";

		} else {// 楠岃瘉澶辫触

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

		String tradeNo = request.getParameter("trade_no");// 鏀粯瀹濅氦鏄撳彿

		String tradeStatus = request.getParameter("trade_status");// 浜ゆ槗鐘舵��

		if (AlipayNotify.verify(params)) {// 楠岃瘉鎴愬姛

			if (tradeStatus.equals("TRADE_FINISHED") || tradeStatus.equals("TRADE_SUCCESS")) {

				// 瑕佸啓鐨勯�昏緫銆傝嚜宸辨寜鑷繁鐨勮姹傚啓

				System.out.println(">>>>>鍏呭�兼垚鍔�" + tradeNo);

			}

			return "...";

		} else {// 楠岃瘉澶辫触

			return "success/fail";

		}

	}

}
