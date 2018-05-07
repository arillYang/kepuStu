package com.getui.java.pushmessage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.kepu.util.ImgHelperUtil;
import com.kepu.util.JsonUtils;

public class PushtoAPP {    
    //采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	private static String appId = "XgcJ8WzkOt6HHYSlt5FJt8";
	private static String appKey = "eTlr5g530n68gZjFsftKr7";
	private static String masterSecret = "QhVogMHbhyAZZ7cRkx95E6";
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";

	public static String pushAll(String title,String auchor,String publishTime,String type,String typeId) throws UnsupportedEncodingException{
		 IGtPush push = new IGtPush(host, appKey, masterSecret);

		 	Map<String,String> map=new HashMap<String, String>();
			map.put("title", title);
			map.put("auchor", auchor);
			map.put("publishTime", publishTime);
			map.put("type", type);
			map.put("typeId", typeId);
			String json=JsonUtils.objectToJson(map);
			String content=ImgHelperUtil.encode(json.getBytes("UTF-8"));
	        //NotificationTemplate template = notificationTemplateDemo(content);
			TransmissionTemplate template =transmissionTemplateDemo(title,auchor,content);
	        AppMessage message = new AppMessage();
	        message.setData(template);

	        message.setOffline(true);
	        //离线有效时间，单位为毫秒，可选
	        message.setOfflineExpireTime(24 * 1000 * 3600);
	        //推送给App的目标用户需要满足的条件
	        AppConditions cdt = new AppConditions(); 
	        List<String> appIdList = new ArrayList<String>();
	        appIdList.add(appId);
	        message.setAppIdList(appIdList);
	        //手机类型
	        //List<String> phoneTypeList = new ArrayList<String>();
	        //省份
	        // List<String> provinceList = new ArrayList<String>();
	        //自定义tag
	        //List<String> tagList = new ArrayList<String>();

	        //cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
	        //cdt.addCondition(AppConditions.REGION, provinceList);
	        //cdt.addCondition(AppConditions.TAG,tagList);
	        message.setConditions(cdt); 

	        IPushResult ret = push.pushMessageToApp(message,"全推");
	        return ret.getResponse().toString();
	}
    public static void main(String[] args) throws Exception {

        IGtPush push = new IGtPush(host, appKey, masterSecret);

        NotificationTemplate template = notificationTemplateDemo("");
        AppMessage message = new AppMessage();
        message.setData(template);

        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        //推送给App的目标用户需要满足的条件
        AppConditions cdt = new AppConditions(); 
        List<String> appIdList = new ArrayList<String>();
        appIdList.add(appId);
        message.setAppIdList(appIdList);
        //手机类型
        //List<String> phoneTypeList = new ArrayList<String>();
        //省份
        // List<String> provinceList = new ArrayList<String>();
        //自定义tag
        //List<String> tagList = new ArrayList<String>();

        //cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
        //cdt.addCondition(AppConditions.REGION, provinceList);
        //cdt.addCondition(AppConditions.TAG,tagList);
        message.setConditions(cdt); 

        IPushResult ret = push.pushMessageToApp(message,"全推");
        System.out.println(ret.getResponse().toString());
    }

    public static LinkTemplate linkTemplateDemo() throws Exception {
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTitle("请输入通知栏标题");
        template.setText("请输入通知栏内容");
        template.setLogo("icon.png");
        template.setLogoUrl("");
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        template.setUrl("http://www.baidu.com");
        return template;
    }
    
    
    public static TransmissionTemplate transmissionTemplateDemo(String title,String context,String content) {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
	    template.setTransmissionType(2);
	    template.setTransmissionContent(content);
	    // 设置定时展示时间
	    // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
	    template.setTransmissionType(2);
		template.setTransmissionContent(content);
		 APNPayload payload = new APNPayload();
		payload.setAutoBadge("+1");
		payload.setContentAvailable(1);
		payload.setAlertMsg(getDictionaryAlertMsg(title,context));
		payload.addCustomMsg("payload", content);
		template.setTransmissionType(1);
		template.setTransmissionContent(content);
		template.setAPNInfo(payload);
	    return template;
	}
    
    private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(String title,String body){
	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody(title);
	    //alertMsg.setActionLocKey("ActionLockey");
	    //alertMsg.setLocKey("LocKey");
	    //alertMsg.addLocArg("loc-args");
	    //alertMsg.setLaunchImage("launch-image");
	    // iOS8.2以上版本支持
	    //alertMsg.setTitle(title);
	    //alertMsg.setTitleLocKey("TitleLocKey");
	    //alertMsg.addTitleLocArg("TitleLocArg");
	    return alertMsg;
	}
    public static NotificationTemplate notificationTemplateDemo(String content) {
		NotificationTemplate template = new NotificationTemplate();
		template.setAppId(appId);
		template.setAppkey(appKey);
		Style0 style = new Style0();
		// 设置通知栏标题与内容
		style.setTitle("");
		style.setText("");
		style.setRing(true);
		style.setVibrate(true);
		style.setClearable(true);
		template.setStyle(style);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		APNPayload payload = new APNPayload();
		payload.setAutoBadge("+1");
		payload.setContentAvailable(1);
		template.setTransmissionType(1);
		template.setTransmissionContent(content);
		template.setAPNInfo(payload);
		return template;
	}

}