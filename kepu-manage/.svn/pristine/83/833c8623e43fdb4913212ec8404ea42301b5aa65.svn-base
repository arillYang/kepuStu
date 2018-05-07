package com.getui.java.pushmessage;

import java.util.LinkedList;
import java.util.List;

public class MyUtil {
	
	public static List<List<String>> transfer(List<String> cids){
		List<List<String>> result=new LinkedList<List<String>>();
		List<String> temp=new LinkedList<String>();
		for (String cid : cids) {
			temp.add(cid);
			if(temp.size()==999){
				result.add(temp);
				temp=new LinkedList<String>();
			}
		}
		if(temp.size()!=0)
			result.add(temp);
		return result;
	}
}
