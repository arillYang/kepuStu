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
	
	public static String getCurrentMonth(){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		Date theDate=calendar.getTime();
		GregorianCalendar gcLast=(GregorianCalendar)Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first=sf.format(gcLast.getTime());
		return day_first+" 00:00:00";
	}
	
	public static String getCurrentLast(){
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMaximum(calendar.DATE));
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

	  public static Date startOfTodDay() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	  }
	 
	  public static Date endOfTodDay() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 999);
	    return calendar.getTime();
	  }
	  
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
	 
	  public static long startOfLastWeek() {
	    return startOfThisWeek() - 7 * 24 * 60 * 60 * 1000;
	  }
	  
	  public static long endOfLastWeek() {
	    return endOfThisWeek() - 7 * 24 * 60 * 60 * 1000;
	  }
	 
	  public static long startOfThisWeek() {
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
	 
	  public static long endOfThisWeek() {
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
	  
	  public static long startOfThisMonth() {
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
	 
	  public static long startOfLastMonth() {
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
	  
	 
	  public static Date getlastDate(Date now,int dateNum) throws ParseException{
		  SimpleDateFormat dateFormat = new SimpleDateFormat(LinConstant.formatStr); // ���ڸ�ʽ
		 // Date date = dateFormat.parse("2015-07-31"); // ָ������
		  Date newDate = addDate(now, dateNum); // ָ�����ڼ���20��
		 // System.out.println(dateFormat.format(date));// �����ʽ���������
		 // System.out.println(dateFormat.format(newDate));
		  return newDate;
	  }
	  
	  public static Date addDate(Date date,long day) {
		  long time = date.getTime(); 
		  day = day*24*60*60*1000; 
		  time+=day; 
		  return new Date(time); 
		 }
	  
	  public static Date addMinute(Date date,long minute) {
		  long time = date.getTime(); 
		  minute = minute*60*1000; 
		  time+=minute; 
		  return new Date(time); 
		 }
	  
	  
	 
		public static Date getMinMonthDate(Date date) throws ParseException{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			return calendar.getTime();
		}
		
		
		public static Date getMaxMonthDate(Date date) throws ParseException{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			return calendar.getTime();
		}
		
		
		
		public static void main(String[] args) {
			/*Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 2018); // 2016年
			cal.set(Calendar.WEEK_OF_YEAR, 24); // 设置为2016年的第10周
			cal.set(Calendar.DAY_OF_WEEK, 7); // 1表示周日，2表示周一，7表示周六
			cal.add(Calendar.DATE, 1);
			Date date = cal.getTime();
			System.out.println(dateFormat.format(date));*/
			Calendar calendar = Calendar.getInstance();
			int week=calendar.get(Calendar.DAY_OF_WEEK);
			System.out.println(week);
		}
}
