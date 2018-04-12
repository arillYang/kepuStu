package com.kepu.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import com.kepu.pojo.DeviceMessage;

public class SystemSession {
	
	private static ThreadLocal<DeviceMessage> local=new ThreadLocal<DeviceMessage>();
	/** 
     * ¸³Öµ 
     * @param session 
     */  
    public static void set(DeviceMessage session) {    
        local.set(session);    
    }    
    /** 
     * È¡Öµ 
     * @return 
     */  
    public static DeviceMessage get() {    
        return local.get();    
    }      
    /** 
     * ÒÆ³ý 
     */  
    public static void remove(){  
        local.remove();  
    }  
    
   public static void main(String[] args) {
	   final int cacheSize=100;
	   LinkedHashMap<String, String> map=
			   new LinkedHashMap<String, String>((int) Math.ceil(cacheSize / 0.75f) + 1, 0.75f, true){
				private static final long serialVersionUID = 1L;
			@Override
		   	protected boolean removeEldestEntry(
		   			java.util.Map.Entry<String, String> eldest) {
		   		return size() > cacheSize;
		   	}
	   };
   }
}
