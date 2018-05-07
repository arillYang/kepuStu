package com.kepu.util;

import java.util.HashMap;
import java.util.Map;

public class Test {
	/*public static void main(String[] args) {
		String[] ss={"position","introduce","area","address","lowPrice","highPrice","mobile",
				"realName","introduce"};
		for (String string : ss) {
			String str="if(map.containsKey(\"##\")){"+
					"##=map.get(\"##\");"+
					"}else{"+
					"sb.append(\"##\").append(\",\");"+
					"}";
			System.out.println(str.replaceAll("##", string));
		}
	}
	*/
	/*public static void main(String[] args) {
	
		for (int i=1;i<=21;i++) {
			String str="case ##:"+
					"return location_##;"+
					"break;";
			System.out.println(str.replaceAll("##", i+""));
		}
	}*/
	public static void main(String[] args) {
		try {
			String str="鞠婧祎";
			double i=Double.valueOf(str);
			System.out.println(i);
		} catch (NumberFormatException e) {
			System.out.println("输入格式有误(请输入数字):"+e.getMessage().replaceAll("For input string:", ""));
			//System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
	}
}
