package com.kepu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class DateUtil {
	
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	
	public static Date formatString(String str,String format) throws Exception{
		if(StringUtil.isEmpty(str)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	public static String getCurrentDateStr()throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(date);
	}
	
	public static String getSaleDate(){
		Calendar c=Calendar.getInstance();
		int date=c.get(Calendar.DATE);
		
		StringBuffer sb=new StringBuffer();
		if(date>15){
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(new Date()); 
			cal.set(Calendar.DAY_OF_MONTH, 1); 
			sb.append(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())).append(" 00:00:00").append(",");
			cal.set(Calendar.DAY_OF_MONTH, 15);
			sb.append(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())).append(" 23:59:59");
			return sb.toString();
		}else{
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, 16);
			sb.append(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())).append(" 00:00:00").append(",");
			Calendar calendar2 = Calendar.getInstance();
			calendar2.set(Calendar.DAY_OF_MONTH, 1); 
			calendar2.add(Calendar.DATE, -1);
			sb.append(new SimpleDateFormat("yyyy-MM-dd").format(calendar2.getTime())).append(" 23:59:59");
			return sb.toString();
		}
	}
	//��ȡ���µ�һ��
	public static String getCurrentMonth(){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		Date theDate=calendar.getTime();
		GregorianCalendar gcLast=(GregorianCalendar)Calendar.getInstance();
		gcLast.setTime(theDate);
		//����Ϊ��һ��
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first=sf.format(gcLast.getTime());
		//��ӡ���µ�һ��
		//System.out.println(day_first+" 00:00:00");
		return day_first+" 00:00:00";
	}
	//��ȡ�������һ��?
	public static String getCurrentLast(){
		Calendar calendar=Calendar.getInstance();
		//��������Ϊ�����������?
		calendar.set(Calendar.DATE, calendar.getActualMaximum(calendar.DATE));
		//�������ڸ�ʽ
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String ss=sf.format(calendar.getTime());
		return ss+" 23:59:59";
	}
	/*public static void main(String[] args) {
		System.out.println(startOfTodDay());
		System.out.println(endOfTodDay());
		DateUtil d=new DateUtil();
		System.out.println(d.getCurrentLast());
		String str=formatDate(new Date(),"yyyyMMddHHmmssSS");
		System.out.println(str);
		try {
			Date date=formatString("2016091723161234", "yyyyMMddHHmmssSS");
			System.out.println(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Date d=getlastDate(new Date(),2);
			System.out.println(formatDate(d, LinConstant.formatStr));
			System.out.println(formatDate(new Date(), LinConstant.formatStr));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	/**
	   * ����Ŀ�ʼʱ��?
	   * @return
	   */
	  public static Date startOfTodDay() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	  }
	  /**
	   * ����Ľ���ʱ��?
	   * @return
	   */
	  public static Date endOfTodDay() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 999);
	    return calendar.getTime();
	  }
	  /**
	   * ����Ŀ�ʼʱ��?
	   * @return
	   */
	  public static long startOfyesterday() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.add(Calendar.DATE, -1);
	    calendar.set(Calendar.MILLISECOND, 0);
	    Date date=calendar.getTime();
	    return date.getTime();
	  }
	  /**
	   * ����Ľ���ʱ��?
	   * @return
	   */
	  public static long endOfyesterday() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 999);
	    calendar.add(Calendar.DATE, -1);
	    Date date=calendar.getTime();
	    return date.getTime();
	  }
	  /**
	   * ���ܣ���ȡ���ܵĿ�ʼʱ��
	   */
	  public static long startOfLastWeek() {// ���ܿ�ʼʱ��
	    return startOfThisWeek() - 7 * 24 * 60 * 60 * 1000;
	  }
	  /**
	   * ���ܣ���ȡ���ܵĽ���ʱ��
	   */
	  public static long endOfLastWeek() {// ���ܿ�ʼʱ��
	    return endOfThisWeek() - 7 * 24 * 60 * 60 * 1000;
	  }
	  /**
	   * ���ܣ���ȡ���ܵĿ�ʼʱ�� ʾ��2013-05-13 00:00:00
	   */
	  public static long startOfThisWeek() {// ���ܿ�ʼʱ��
	    Calendar currentDate = Calendar.getInstance();
	    currentDate.setFirstDayOfWeek(Calendar.MONDAY);
	    currentDate.set(Calendar.HOUR_OF_DAY, 0);
	    currentDate.set(Calendar.MINUTE, 0);
	    currentDate.set(Calendar.SECOND, 0);
	    currentDate.set(Calendar.MILLISECOND, 0);
	    currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    Date date=currentDate.getTime();
	    return date.getTime();
	  }
	  /**
	   * ���ܣ���ȡ���ܵĽ���ʱ�� ʾ��2013-05-19 23:59:59
	   */
	  public static long endOfThisWeek() {// ���ܽ���ʱ��
	    Calendar currentDate = Calendar.getInstance();
	    currentDate.setFirstDayOfWeek(Calendar.MONDAY);
	    currentDate.set(Calendar.HOUR_OF_DAY, 23);
	    currentDate.set(Calendar.MINUTE, 59);
	    currentDate.set(Calendar.SECOND, 59);
	    currentDate.set(Calendar.MILLISECOND, 999);
	    currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	    Date date=currentDate.getTime();
	    return date.getTime();
	  }
	  /**
	   * ���ܣ���ȡ���µĿ�ʼʱ��
	   */
	  public static long startOfThisMonth() {// ���ܿ�ʼʱ��
	    Calendar currentDate = Calendar.getInstance();
	    currentDate.set(Calendar.HOUR_OF_DAY, 0);
	    currentDate.set(Calendar.MINUTE, 0);
	    currentDate.set(Calendar.SECOND, 0);
	    currentDate.set(Calendar.MILLISECOND, 0);
	    currentDate.set(Calendar.DAY_OF_MONTH, 1);
	    Date date=currentDate.getTime();
	    return date.getTime();
	  }
	  public static long endOfThisMonth() {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.set(Calendar.HOUR_OF_DAY, 23);
	    cal.set(Calendar.MINUTE, 59);
	    cal.set(Calendar.SECOND, 59);
	    cal.set(Calendar.MILLISECOND, 999);
	    cal.add(Calendar.MONTH, 1);
	    cal.add(Calendar.DATE, -1);
	    Date date=cal.getTime();
	    return date.getTime();
	  }
	  /**
	   * ���ܣ���ȡ���µĿ�ʼʱ��
	   */
	  public static long startOfLastMonth() {// ���ܿ�ʼʱ��
	    Calendar currentDate = Calendar.getInstance();
	    currentDate.set(Calendar.HOUR_OF_DAY, 0);
	    currentDate.set(Calendar.MINUTE, 0);
	    currentDate.set(Calendar.SECOND, 0);
	    currentDate.set(Calendar.MILLISECOND, 0);
	    currentDate.set(Calendar.DAY_OF_MONTH, 1);
	    currentDate.add(Calendar.MONTH, -1);
	    Date date=currentDate.getTime();
	    return date.getTime();
	  }
	  /**
	   * ���ܣ���ȡ���µĽ���ʱ��
	   */
	  public static long endOfLastMonth() {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    cal.set(Calendar.HOUR_OF_DAY, 23);
	    cal.set(Calendar.MINUTE, 59);
	    cal.set(Calendar.SECOND, 59);
	    cal.set(Calendar.MILLISECOND, 999);
	    cal.add(Calendar.DATE, -1);
	    Date date=cal.getTime();
	    return date.getTime();
	  }
	  /**
	   *  ���long����year
	   * @param milliseconds
	   * @return
	   */
	  public static Object[] theYearOfTime(long milliseconds){
	    Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	    int thisYear=date.getYear()+1900;
	    cal.setTimeInMillis(milliseconds);
	    date=cal.getTime();
	    int regirsterYear=date.getYear()+1900;
	    if(regirsterYear<thisYear){
	      List<Integer> yearL=new ArrayList<Integer>();
	      for(int i=regirsterYear;i<=thisYear;i++){
	        yearL.add(i);
	      }
	      return yearL.toArray();
	    }else{
	      return new Object[]{thisYear};
	    }
	  }
	  /**
	   * ���ܣ���ȡ����Ŀ�ʼʱ��?
	   */
	  public static long startOfTheYear(int year) {// ���ܿ�ʼʱ��
	    Calendar currentDate = Calendar.getInstance();
	    currentDate.set(Calendar.YEAR, year);
	    currentDate.set(Calendar.MONTH, 0);
	    currentDate.set(Calendar.HOUR_OF_DAY, 0);
	    currentDate.set(Calendar.MINUTE, 0);
	    currentDate.set(Calendar.SECOND, 0);
	    currentDate.set(Calendar.MILLISECOND, 0);
	    currentDate.set(Calendar.DAY_OF_MONTH, 1);
	    Date date=currentDate.getTime();
	    return date.getTime();
	  }
	  /**
	   * ���ܣ���ȡ����Ŀ�ʼʱ��?
	   */
	  public static long endOfTheYear(int year) {
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH,11);
	    cal.set(Calendar.DAY_OF_MONTH, 31);
	    cal.set(Calendar.HOUR_OF_DAY, 23);
	    cal.set(Calendar.MINUTE, 59);
	    cal.set(Calendar.SECOND, 59);
	    cal.set(Calendar.MILLISECOND, 999);
	    Date date=cal.getTime();
	    return date.getTime();
	  }
	  
	  /**
	   * ��ȡָ������������
	 * @throws ParseException 
	   */
	  public static Date getlastDate(Date now,int dateNum) throws ParseException{
		  SimpleDateFormat dateFormat = new SimpleDateFormat(LinConstant.formatStr); // ���ڸ�ʽ
		 // Date date = dateFormat.parse("2015-07-31"); // ָ������
		  Date newDate = addDate(now, dateNum); // ָ�����ڼ���20��
		 // System.out.println(dateFormat.format(date));// �����ʽ���������
		 // System.out.println(dateFormat.format(newDate));
		  return newDate;
	  }
	  
	  public static Date addDate(Date date,long day) {
		  long time = date.getTime(); // �õ�ָ�����ڵĺ�����
		  day = day*24*60*60*1000; // Ҫ���ϵ�����ת���ɺ�����
		  time+=day; // ��ӵõ��µĺ�����?
		  return new Date(time); // ��������ת��������
		 }
	  
	  public static Date addMinute(Date date,long minute) {
		  long time = date.getTime(); // �õ�ָ�����ڵĺ�����
		  minute = minute*60*1000; // Ҫ���ϵ�����ת���ɺ�����
		  time+=minute; // ��ӵõ��µĺ�����?
		  return new Date(time); // ��������ת��������
		 }
	  
	  
	  /**
		* ��ȡ�·���ʼ����
		* @param date
		* @return
		* @throws ParseException
		*/
		public static Date getMinMonthDate(Date date) throws ParseException{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			return calendar.getTime();
		}
		
		/**
		* ��ȡ�·��������?
		* @param date
		* @return
		* @throws ParseException
		*/
		public static Date getMaxMonthDate(Date date) throws ParseException{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			return calendar.getTime();
		}
		
		public static void main(String[] args) {
			
		}
}
