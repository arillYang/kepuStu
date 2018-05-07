package com.kepu.util;



public class GpsSession {
	
	private static ThreadLocal<String> local=new ThreadLocal<String>();
	/** 
     * ��ֵ 
     * @param session 
     */  
    public static void set(String location) {    
    	local.set(location);    
    }    
    /** 
     * ȡֵ 
     * @return 
     */  
  
    /** 
     * �Ƴ� 
     */  
    public static void remove(){  
    	local.remove();  
    }  
    
}
