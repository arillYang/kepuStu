package com.kepu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FtpUtil {
	private  FTPClient ftp; 
	protected final Log logger = LogFactory.getLog(getClass());
	 /**  
	     *   
	     * @param path 上传到ftp服务器哪个路径下     
	     * @param addr 地址  
	     * @param port 端口号  
	     * @param username 用户名  
	     * @param password 密码  
	     * @return  
	     * @throws Exception  
	     */    
	    private  boolean connect(String path,String addr,int port,String username,String password) throws Exception {      
	        boolean result = false;      
	        ftp = new FTPClient();      
	        int reply;      
	        ftp.connect(addr,port);      
	        ftp.login(username,password);      
	        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);      
	        reply = ftp.getReplyCode();  
	        logger.info("ftp连接状态"+reply);
	        if (!FTPReply.isPositiveCompletion(reply)) {      
	            ftp.disconnect();      
	            return result;      
	        }      
	        ftp.changeWorkingDirectory(path);      
	        result = true;      
	        return result;      
	    }      
	    /**  
	     *   
	     * @param file 上传的文件或文件夹  
	     * @throws Exception  
	     */    
	    private void upload(File file) throws Exception{      
	        if(file.isDirectory()){           
	            ftp.makeDirectory(file.getName());                
	            ftp.changeWorkingDirectory(file.getName());      
	            String[] files = file.list();             
	            for (int i = 0; i < files.length; i++) {      
	                File file1 = new File(file.getPath()+"\\"+files[i] );      
	                if(file1.isDirectory()){      
	                    upload(file1);      
	                    ftp.changeToParentDirectory();      
	                }else{                    
	                    File file2 = new File(file.getPath()+"\\"+files[i]);      
	                    FileInputStream input = new FileInputStream(file2);      
	                    ftp.storeFile(file2.getName(), input);      
	                    input.close();                            
	                }                 
	            }      
	        }else{      
	            File file2 = new File(file.getPath());      
	            FileInputStream input = new FileInputStream(file2);      
	            ftp.storeFile(file2.getName(), input);      
	            input.close();        
	        }      
	    }   
	    
	   private void uploadFile(InputStream in,String fileName,String basePath) throws IOException{  
		   		logger.info("文件上传开始-------");
		   		long startTime = System.currentTimeMillis();
		   		if(!basePath.equals("")){
		   			String path[]=basePath.split("/");
		   			for (String string : path) {
		   				ftp.makeDirectory(string);
		   				ftp.changeWorkingDirectory(string);
					}
		   			ftp.changeWorkingDirectory("/"+basePath);
		   			//FileInputStream input = (FileInputStream) in;   
		   			InputStream input=in;
		   			ftp.enterLocalPassiveMode();
		   			ftp.setBufferSize(1024*1024*5); 
		   			boolean flag=ftp.storeFile(fileName, input); 
		   			int count=0;
		   			while(!flag){
		   				count++;
		   				flag=ftp.storeFile(fileName, input); 
		   				if(count==4)
		   					break;
		   			}
		   			long endTime = System.currentTimeMillis();    //获取结束时间
		   			System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); 
		            logger.info(flag?"文件上传成功":"文件上传失败");
		            input.close();   
		   		}else{
		            FileInputStream input = (FileInputStream) in;      
		            ftp.storeFile(fileName, input);
		            input.close();  
		   		}
	    }  
	    
	   public static int uploadFile(String address,int port,String userName,
			   String password,String basePath,InputStream in,String fileName){
		   FtpUtil t = new FtpUtil();    
		   try {
				int r=t.connect("", address, port, userName, password)?1:0;
				t.uploadFile(in,fileName,basePath);
				return r;
		   } catch (Exception e) {
				e.printStackTrace();
				return -1;
		   }
	   }
	   public static void main(String[] args) throws Exception{    
		 /* FtpUtil t = new FtpUtil();    
	      t.connect("", "123.207.124.233", 21, "niepan", "lf969621.");    
	      File file = new File("E:\\jyj\\1.jpg");    
	      t.upload(file); */  
		   File file = new File("E:\\jyj\\1.jpg");  
		   FileInputStream input = new FileInputStream(file);   
		   FtpUtil.uploadFile("39.108.89.113", 21, "admin", "lf7855474.","2017/5/5",input,"1.jpg");
		   System.out.println("上传完成");
	   }    
}
