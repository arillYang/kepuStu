package com.kepu.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.springframework.jdbc.UncategorizedSQLException;

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
		String ms="����ʧ��";
		if(t instanceof UncategorizedSQLException)
			ms="���ܲ������";
		if(t instanceof NumberFormatException){
			ms="�����ʽ����(����������):"+t.getMessage().replaceAll("For input string:", "");
		}
		return ms;
	}
}
