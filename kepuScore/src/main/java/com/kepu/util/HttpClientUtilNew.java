package com.kepu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class HttpClientUtilNew {

	private static final Logger LOG = Logger.getLogger(HttpClientUtilNew.class);
	private static final String header_url_base ="http://kp.appwzd.cn/header/"; //ConfigUtil.getProperty("header_url_base");
	
	public static String doGet(String url, Map<String, String> param) {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);

			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状�?是否�?00
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static String doGet(String url) {
		return doGet(url, null);
	}

	public static String doPost(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultString;
	}

	public static String doPost(String url) {
		return doPost(url, null);
	}
	
	public static String doPostJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultString;
	}
	
	public static String doPostFile(String url,byte[] b,String type) {
		// 创建Httpclient对象
		/*CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;*/
		String resultString = "";
		try {
			LOG.info("头像大小为"+b.length);
			String fileName=UUIDFactory.getUUID();
			
			String name=fileName+"."+type;
			saveFile(name,b);
			// 创建Http Post请求
			/*HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("content-type","multipart/form-data,boundary='-----------------------------'");
			// 创建请求内容
			MultipartEntity mutiEntity = new MultipartEntity();
			File file = new File("E:\\tempPic\\"+fileName+"."+type);
			mutiEntity.addPart("pic", new FileBody(file));
			//StringEntity entity = new StringEntity(new String(b), ContentType.MULTIPART_FORM_DATA);
			httpPost.setEntity(mutiEntity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");*/
			resultString= header_url_base+name;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}

		return resultString;
	}
	
	/**
	* 将字节流转换成文�?
	* @param filename
	* @param data
	* @throws Exception
	*/
	public static void saveFile(String filename,byte [] data){ 
	    if(data != null){ 
	    	String filepath ="";
	    	if(windowsSystem())
	    		filepath ="C:\\tempPic\\" + filename; 
	    	else  
	    		//filepath="/usr/local/apache-tomcat-7.0.78/webapps/pic/"+filename;
	    		filepath="/usr/local/src/pic/"+filename; 
	    	LOG.info("图片路径——------"+filepath);
	      File file  = new File(filepath); 
	      /*if(file.exists()){ 
	         file.delete(); 
	      } */
	      LOG.info("图片开始写入——------"+filepath);
	      try {
	    	FileOutputStream fos = new FileOutputStream(file); 
			fos.write(data,0,data.length);
			LOG.info("图片结束写入,大小为"+data.length);
	        fos.flush(); 
	        fos.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.info("出现异常"+ExceptionUtil.getStackTrace(e));
		} 
	      
	    } 
	}
	
	static Boolean windowsSystem(){
		String s=System.getProperty("os.name");
		if(s.indexOf("Windows")>=0)
			return true;
		else
			return false;
	}
}
