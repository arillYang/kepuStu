package com.kepu.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.kepu.service.impl.PictureServiceImpl;

public class OSSUploadUtil {
	private static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
	private static String accessKeyId = "LTAICGqfkFo5h33o";
	private static String accessKeySecret = "PfbU4cLZdk9PGJafNFhx1Q7XHL3WW6";
	private static String bucketName = "ikow";
	
	protected static final Log logger = LogFactory.getLog(OSSUploadUtil.class);
	public static void putObject(String key, InputStream content) throws IOException {

	    // 初始化OSSClient
		OSSClient  client = new OSSClient(endpoint, accessKeyId,  accessKeySecret); 

	    // 获取指定文件的输入流
	    //File file = new File(filePath);
	    //InputStream content = new FileInputStream(file);

	    // 创建上传Object的Metadata
	    ObjectMetadata meta = new ObjectMetadata();

	    // 必须设置ContentLength
	    meta.setContentLength(content.available());
	    
	    Date expire = new Date(new Date().getTime() + 30 * 1000);
	    meta.setExpirationTime(expire);

	    // 上传Object.
	    
	    PutObjectResult result = client.putObject(bucketName, key, content, meta);
	    // 打印ETag
	    logger.info("etag--------------->"+result.getETag());
	   //System.out.println("etag--------------->"+result.getETag());
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		/*OSSUploadUtil.putObject(bucketName, "美团.txt", "C:\\Users\\niepan\\Desktop\\美团.txt");
		File file = new File("C:\\Users\\niepan\\Desktop\\美团.txt");
		String md5 = testOSSUpload.getFileMD5(file);
		
		System.out.println("md5---------------->"+md5);*/
	}
	
	public static String getFileMD5(File file) {
	    if (!file.isFile()){
	      return null;
	    }
	    MessageDigest digest = null;
	    FileInputStream in=null;
	    byte buffer[] = new byte[1024];
	    int len;
	    try {
	      digest = MessageDigest.getInstance("MD5");
	      in = new FileInputStream(file);
	      while ((len = in.read(buffer, 0, 1024)) != -1) {
	        digest.update(buffer, 0, len);
	      }
	      in.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	    BigInteger bigInt = new BigInteger(1, digest.digest());
	    return bigInt.toString(16).toUpperCase();
	  }
}
