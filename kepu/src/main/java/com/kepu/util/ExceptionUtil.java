package com.kepu.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.UncategorizedSQLException;

import com.kepu.controller.UserController;

public class ExceptionUtil {
	/**
	 * 
	 */
	private static final Logger LOG = Logger.getLogger(ExceptionUtil.class);
	public  static String getStackTrace(Throwable t){
		StringWriter sw=new StringWriter();
		PrintWriter pw=new PrintWriter(sw);
		try {
			t.printStackTrace(pw);
			LOG.info(sw.toString());
			//return sw.toString();
		}finally{
			pw.close();
		}
		String ms="操作失败";
		if(t instanceof UncategorizedSQLException)
			ms="不能插入表情";
		if(t instanceof NumberFormatException){
			ms="输入格式有误(请输入数字):"+t.getMessage().replaceAll("For input string:", "");
		}
		return ms;
	}
}
