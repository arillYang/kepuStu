package com.kepu.util;

import com.kepu.constant.MyConstant;


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
    public static Double getLat() {  
    	if(StringUtil.isEmpty(local.get()))
    		local.set(MyConstant.location_21);
		String lat=local.get().split(",")[1]; 
		return Double.valueOf(lat);
    } 
    
    public static Double getLon() {  
    	if(StringUtil.isEmpty(local.get()))
    		local.set(MyConstant.location_21);
		String lon=local.get().split(",")[0]; 
		return Double.valueOf(lon);
    } 
    /** 
     * �Ƴ� 
     */  
    public static void remove(){  
    	local.remove();  
    }  
    
}
