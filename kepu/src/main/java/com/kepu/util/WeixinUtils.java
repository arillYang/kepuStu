package com.kepu.util;
import java.security.MessageDigest;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.kepu.dao.JedisClient;

public class WeixinUtils {
	 
     // 微信appId
     //private static String APPID="wxcd39408a1d7a5c1d";
	private static String APPID="wx751b287f5f30d2a3";
     // 微信公众号唯一密钥
     //private static String APPSECRET="5ec15a2d86495f388bce0a024fc45e7b";
     private static String APPSECRET="31cb5e6d26058bae7d3888faecebf192";
     // 获取acc_token的接口
     private static String ACC_TOKEN_URL;
     // 获取jsapi_ticket url
     private static String JSAPI_TICKET_URL;
     // 生成签名的随机串
     private static String NONCE_STR;
     /*@Autowired
     private JedisClient jedisClient2;*/
     public static JedisClient redisService;
 
     static {
         //Properties prop = AppPropTools.getProperties("/weixin.properties");
         ACC_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"+ "&appid=" + APPID
                 + "&secret=" + APPSECRET;
         //https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
         JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
         NONCE_STR = "wozhidao";
     }
 
     // 私有构造方法
     private WeixinUtils() {
 
     }
 
     /**
      * 获取微信acc_token
      * 
      * @return
      * @throws Exception
      */
     public  static String getAccToken() throws Exception {
         // 先从redis取，取不到再从微信里面取
         String weixin_acc_token = redisService.get("weixin_acc_token");
         //RedisHelper.getStringValue("weixin_acc_token", 2);
         if (StringUtils.isEmpty(weixin_acc_token)) {
        	 String resultStr =HttpClientUtil.sendGet(ACC_TOKEN_URL, "");
             /*String resultStr = HttpURLConnectionUtil
                     .getWebHTMLCode(ACC_TOKEN_URL);*/
             JSONObject resultObj = JSONObject.parseObject(resultStr);
             String accToken = resultObj.getString("access_token");
             int expiresIn = resultObj.getIntValue("expires_in");
             // 写进redis
             if(StringUtil.isNotEmpty(accToken)){
	             redisService.set("weixin_acc_token", accToken);
	             redisService.expire("weixin_acc_token", expiresIn);
             }
             //RedisHelper.setStringValue("weixin_acc_token", accToken, expiresIn,2);
             return accToken;
         }
         return weixin_acc_token;
 
     }
 
     /**
      * 获取微信票据
      * 
      * @return
      * @throws Exception
      */
     public static String getTicket() throws Exception {
         // 先从redis中取ticket，没有再从这里取
    	 String weixin_js_api_tiket = redisService.get("weixin_js_api_tiket");
         //String weixin_js_api_tiket = RedisHelper.getStringValue("weixin_js_api_tiket", 2);
         if (StringUtils.isEmpty(weixin_js_api_tiket)) {
 
             String accToken = getAccToken();
             //String resultStr = HttpURLConnectionUtil.getWebHTMLCode(JSAPI_TICKET_URL + accToken);
             String resultStr =HttpClientUtil.sendGet(JSAPI_TICKET_URL.replaceFirst("ACCESS_TOKEN", accToken), "");
             JSONObject resultObj = JSONObject.parseObject(resultStr);
             if (resultObj.getIntValue("errcode") == 0) {
                 String ticket = resultObj.getString("ticket");
                 int expires_in = resultObj.getIntValue("expires_in");
                 // 写入redis
                 //RedisHelper.setStringValue("weixin_js_api_tiket", ticket, expires_in, 2);
                 if(StringUtil.isNotEmpty(ticket)){
	                 redisService.set("weixin_js_api_tiket", ticket);
	                 redisService.expire("weixin_js_api_tiket", expires_in);
                 }
                 return ticket;
             }
             return null;
         }
         return weixin_js_api_tiket;
     }
 
     /**
      * 获取签名
      * 
      * @param timeStamp
      * @param requestUrl
      * @return
      * @throws Exception
      */
     public static String getSignature(Long timeStamp, String requestUrl)
             throws Exception {
         String ticket = getTicket();
         StringBuffer allStr = new StringBuffer("jsapi_ticket=");
         allStr.append(ticket).append("&noncestr=").append(NONCE_STR)
                 .append("&timestamp=").append(timeStamp).append("&url=")
                 .append(requestUrl);
         MessageDigest crypt = MessageDigest.getInstance("SHA-1");
         crypt.reset();
         crypt.update(allStr.toString().getBytes("UTF-8"));
         String signature = byteToHex(crypt.digest());
         return signature;
     }
 
     /**
      * SHA1加密
      * 
      * @param hash
      * @return
      */
     private static String byteToHex(final byte[] hash) {
         Formatter formatter = new Formatter();
         for (byte b : hash) {
             formatter.format("%02x", b);
         }
         String result = formatter.toString();
         formatter.close();
         return result;
     }
 
     /**
      * 获取微信JS页面Config所需参数
      * 
      * @param timeStamp
      * @param requestUrl
      * @return
      * @throws Exception
      */
     public static Map<String, Object> getConfig(long timeStamp,
             String requestUrl,JedisClient jedisClient) throws Exception {
    	 redisService=jedisClient;
         Map<String, Object> map = new HashMap<String, Object>();
         String signature = getSignature(timeStamp, requestUrl);
         map.put("signature", signature);
         map.put("nonceStr", NONCE_STR);
         map.put("timestamp", timeStamp);
         map.put("appId", APPID);
         return map;
     }
}
