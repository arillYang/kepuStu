package com.kepu.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �ַ�����
 * @author 
 *
 */
public class StringUtil {

	public static List<Integer> asIntegerList(String[] str){
		List<Integer> values=new LinkedList<Integer>();
		for (String string : str) {
			int i=Integer.valueOf(string);
			values.add(i);
		}
		return values;
	}
	/**
	 * �ж��Ƿ��ǿ�
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	
	public static boolean isExistEmpty(String ...string){
		for (String str : string) {
			if(str==null||"".equals(str.trim())){
				return true;
			}
		}
		return false;
	}
	/**
	 * �ж��Ƿ��ǿ�
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if((str!=null)&&!"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getStrNotSame(String str1,String str2){
	        String[] arr1 = str1.split(",") ;
	        String arr2[] = str2.split(",") ; 
	        for (int i = 0; i < arr2.length; i++){
	            for (int j = 0; j < arr1.length; j++){
	                if (arr1[j].equals(arr2[i])){
	                    arr1[j] = "" ;
	                }
	            }
	        }
	         StringBuffer sb = new StringBuffer() ;
	        for (int j = 0; j < arr1.length; j++){
	            if (!"".equals(arr1[j]) ){
	                sb.append(arr1[j] + ",") ;
	            }
	        }
	         if(StringUtil.isEmpty(sb.toString()))
	        	 return "";
	         else
	        	 return sb.deleteCharAt(sb.length()-1).toString();
	    }
	/**
	 * ͳ��һ�λ��е��ʳ��ֵĴ���
	 * @param str
	 */
	public static void StringCount(String str){
		String s=str.replace(", ", " ").replace(". ", " ");
		String[] currentStr=s.split(" ");
		Map<String,Integer> map=new HashMap<String, Integer>();
		for (String st : currentStr) {
			if(map.get(st)==null){
				map.put(st, 1);
			}else{
				map.put(st, map.get(st)+1);
			}
		}
		for(Entry<String, Integer> entry:map.entrySet()){
			System.out.println(entry.getKey()+","+entry.getValue());
		}
	}
	
	public static String ListToString(List<String> list){
		StringBuilder sb=new StringBuilder();
		for (String string : list) {
			sb.append(string).append(",");
		}
		return sb.deleteCharAt(sb.length()-1).toString();
	}
	
	public static List<String> StringToList(String str){
		String[] s=str.split(",");
		ArrayList<String> list=new ArrayList<String>();
		for (String string : s) {
			list.add(string);
		}
		return list;
	}
	private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
 
    // ������ж����ĺ��ֺͷ��
    public static boolean isChinese(String strName) {
    	boolean flag=true;
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!isChinese(c)) {
                return false;
            }
        }
        return flag;
    }
	
    public static boolean isMobileNO(String mobiles){
    	Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
    	Matcher m = p.matcher(mobiles);  
    	return m.matches();  
    }
	public static void main(String[] args) {
		 /*String str1 = "1,2,3";
	     String str2 = "1,2,4";
		System.out.println(StringUtil.getStrNotSame(str1, str2));*/
		//String str="Welcome to ADempiere, a commons-based peer-production of Open Source ERP Applications. This Wiki is for the global community to contribute and share know-how and domain expertise. We hope you can find as much open information and participate in making it most usable for everyone. This project has a bazaar of Citizens with a Community Council Team which work in theFunctional Team and Technical Team along the Software Development Procedure supported and funded by the foundation ADempiere";
		//StringUtil.StringCount(str);
		System.out.println(isMobileNO("11111111111"));
	}
}
