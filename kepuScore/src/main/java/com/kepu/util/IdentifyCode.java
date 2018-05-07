package com.kepu.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IdentifyCode {
	public static String getIdentifyCode() {  
        String[] beforeShuffle = new String[] {"0","1", "2", "3", "4", "5", "6", "7",  
                "8", "9" };  
        List list = Arrays.asList(beforeShuffle);  
        Collections.shuffle(list);  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(list.get(i));  
        }  
        String afterShuffle = sb.toString();  
        String result = afterShuffle.substring(5, 9);  
        return result;  
    }  
	
}
