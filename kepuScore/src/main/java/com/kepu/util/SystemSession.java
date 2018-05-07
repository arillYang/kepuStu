package com.kepu.util;

import java.util.LinkedHashMap;


public class SystemSession {
	

    
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
