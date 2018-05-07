package com.kepu.util;

import java.util.LinkedList;
import java.util.List;

public class NewsUtil {
	
	public static List<Integer> getNewsTypes(Integer type){
		List<Integer> list=new LinkedList<Integer>();
		for (int i = 1; i <=4096; i++) {
			if((i>>(type-1)&1)==1)
				list.add(i);
		}
		return list;
	}
	public static void main(String[] args) {
		System.out.println(getNewsTypes(12));
	}
}
