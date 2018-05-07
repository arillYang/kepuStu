package com.getui.java.pushmessage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.kepu.util.ImgHelperUtil;
import com.kepu.util.JsonUtils;

public class PushList {
	// 采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	private static String appId = "XgcJ8WzkOt6HHYSlt5FJt8";//"xbynRBJ4LX7Mr1QQoqNmf5";//
	private static String appKey = "eTlr5g530n68gZjFsftKr7";//"XP0pZhKSpb8yEhjqzHFMOA";//
	private static String masterSecret ="QhVogMHbhyAZZ7cRkx95E6"; //"N8N7VfZOkiAhYWaItevyH2";//

	static String CID1 = "e605a0db5ce3cca9b76b012978064940";
	static String CID2 = "453ffc4fec7bfd43d4705639eacb41d0";
	// 别名推送方式
	// static String Alias1 = "";
	// static String Alias2 = "";
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";

	public static String pushListMessage(List<String> list,String title,String auchor,String publishTime,String type,
			String typeId,String device) throws UnsupportedEncodingException{
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		// 通知透传模板
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
		ListMessage message = new ListMessage();
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 配置推送目标
		List targets = new ArrayList();
		Target target;
		/*target=new Target();
		target.setAppId(appId);
		target.setClientId(list.get(0));
		targets.add(target);
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		System.out.println(ret.getResponse().toString());*/
		List<List<String>> r=MyUtil.transfer(list);
		StringBuffer sb=new StringBuffer();
		for (List<String> list2 : r) {
			for (String cid : list2) {
				target=new Target();
				target.setAppId(appId);
				target.setClientId(cid);
				targets.add(target);
			}
			String taskId = push.getContentId(message);
			IPushResult ret = push.pushMessageToList(taskId, targets);
			sb.append( ret.getResponse().toString()).append(",");
		}
		return sb.toString();
		// Target target1 = new Target();
		// Target target2 = new Target();
		// target1.setAppId(appId);
		// target1.setClientId(CID1);
		// target1.setAlias(Alias1);
		// target2.setAppId(appId);
		// target2.setClientId(CID2);
		// target2.setAlias(Alias2);
		// targets.add(target1);
		// targets.add(target2);
		// taskId用于在推送时去查找对应的message
	}
	
	public static void main(String[] args) throws Exception {
		// 配置返回每个用户返回用户状态，可选
		/*System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		// 通知透传模板
		NotificationTemplate template = notificationTemplateDemo();
		ListMessage message = new ListMessage();
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 配置推送目标
		List targets = new ArrayList();
		Target target1 = new Target();
		Target target2 = new Target();
		target1.setAppId(appId);
		target1.setClientId(CID1);
		// target1.setAlias(Alias1);
		target2.setAppId(appId);
		target2.setClientId(CID2);
		// target2.setAlias(Alias2);
		targets.add(target1);
		targets.add(target2);
		// taskId用于在推送时去查找对应的message
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		System.out.println(ret.getResponse().toString());*/
		List<String> test=new LinkedList<String>();
		test.add("625e16af97958a3adee691c179704f35");
		String str=pushListMessage(test,"穿越梦幻绿道，跑进仙侠世界，仙马带您寻找属于自己的那一份执着",
				"张楠","2017-11-26 18:28:48","1","816","IOS");
		System.out.println(str);
		System.exit(0);
	}

	public static TransmissionTemplate transmissionTemplateDemo(String title,String context,String content) {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
	    template.setTransmissionType(1);
	    template.setTransmissionContent(content);
	    // 设置定时展示时间
	    // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
	    APNPayload payload = new APNPayload();
		payload.setAutoBadge("+1");
		payload.setContentAvailable(1);
		payload.setAlertMsg(getDictionaryAlertMsg(title,context));
		payload.addCustomMsg("payload", content);
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
		 //设置通知栏标题与内容
		 style.setTitle("");
		 style.setText("");
		 //配置通知栏图标
		 //style.setLogo("icon.png");
		 //配置通知栏网络图标
		 //style.setLogoUrl("");
		 //设置通知是否响铃，震动，或者可清除
		 style.setRing(true);
		 style.setVibrate(true);
		 style.setClearable(true);
		 //template.setStyle(style);

		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		template.setTransmissionContent(content);
		template.setAPNInfo(new APNPayload().setAutoBadge("+1"));
		return template;
	}
}
