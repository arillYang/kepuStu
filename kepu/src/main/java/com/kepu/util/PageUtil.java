package com.kepu.util;

/**
 * 分页工具�?
 * @author Administrator
 *
 */
public class PageUtil {

	/**
	 * 获取分页代码
	 * @param targetUrl 目标地址
	 * @param totalNum 总记录数
	 * @param currentPage 当前�?
	 * @param pageSize 每页大小
	 * @return
	 */
	public static String getPagation(String targetUrl,int totalNum,int currentPage,int pageSize){
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		if(totalPage==0){
			return "<font color=red>未查询到数据�?/font>";
		}
		StringBuffer pageCode=new StringBuffer();
		pageCode.append("<li><a href='"+targetUrl+"?page=1'>首页</a></li>");
		if(currentPage==1){
			pageCode.append("<li class='disabled'><a href='#'>上一�?/a></li>");
		}else{
			pageCode.append("<li><a href='"+targetUrl+"?page="+(currentPage-1)+"'>上一�?/a></li>");
		}
		
		for(int i=currentPage-2;i<=currentPage+2;i++){
			if(i<1 || i>totalPage){
				continue;
			}
			if(i==currentPage){
				pageCode.append("<li class='active'><a href='#'>"+i+"</a></li>");
			}else{
				pageCode.append("<li><a href='"+targetUrl+"?page="+i+"'>"+i+"</a></li>");
			}
			
		}
		
		if(currentPage==totalPage){
			pageCode.append("<li class='disabled'><a href='#'>下一�?/a></li>");
		}else{
			pageCode.append("<li><a href='"+targetUrl+"?page="+(currentPage+1)+"'>下一�?/a></li>");
		}
		pageCode.append("<li><a href='"+targetUrl+"?page="+totalPage+"'>尾页</a></li>");
		pageCode.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color=red size=4 >共有"+totalNum+"条记�?/font>");
		return pageCode.toString();
	}
}
