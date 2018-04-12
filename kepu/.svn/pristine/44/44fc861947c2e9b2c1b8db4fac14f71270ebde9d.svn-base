package com.kepu.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;




public class FindMobileAddress {
	
	public static void main(String[] args) {
		System.out.println(getRequest1("15990093541"));
	}
	 
	public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
 
    
    public static final String APPKEY ="79f236ba8750e65de18428699c971319";
 
   
    public static String getRequest1(String mobile){
        String result =null;
        String url ="http://apis.juhe.cn/mobile/get";
        Map params = new HashMap();
            params.put("phone",mobile);
            params.put("key",APPKEY);
            params.put("dtype","");
 
        try {
            result =net(url, params, "GET");
            JSONObject object =JSONObject.parseObject(result);
            if(object.getInteger("error_code")==0){
                System.out.println(object.get("result"));
                Map<String,String> map=(Map<String, String>) object.get("result");
                return map.get("areacode");
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
 

 
    /**
     *
     * @param strUrl 链接
     * @param params 参数
     * @param method 方法
     * @return  
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                 
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
 
   
    public static String urlencode(Map<String,String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
	 
	 
	 
	 
	
    public static String calcMobileCity(String mobileNumber) throws MalformedURLException{
        String jsonString = null;
        JSONArray array = null;
        JSONObject jsonObject = null;
        String urlString = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" + mobileNumber;
        StringBuffer sb = new StringBuffer();
        BufferedReader buffer;
        URL url = new URL(urlString);
        try{
            InputStream in = url.openStream();

        
            buffer = new BufferedReader(new InputStreamReader(in,"gb2312"));
            String line = null;
            while((line = buffer.readLine()) != null){
                sb.append(line);
            }
            in.close();
            buffer.close();
            // System.out.println(sb.toString());
            jsonString = sb.toString();
            jsonString = jsonString.replaceAll("^[__]\\w{14}+[_ = ]+", "[");
            String jsonString2 = jsonString + "]";
            array = JSONArray.parseArray(jsonString2);   
            jsonObject = array.getJSONObject(0);        

        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonObject.getString("areaVid");
    }

}
