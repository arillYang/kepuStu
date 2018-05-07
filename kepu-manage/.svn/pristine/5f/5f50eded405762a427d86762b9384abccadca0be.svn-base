package com.kepu.util;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.kepu.pojo.StClassify;
import com.kepu.pojo.StUser;
import com.kepu.pojo.StVillage;
import com.kepu.pojo.activity.ActivityResult;
import com.kepu.pojo.user.UserStatistic;
import com.kepu.pojo.user.active.BaseSS;
import com.kepu.pojo.user.click.ClickResult;
import com.kepu.pojo.user.read.ReadResult;
import com.kepu.service.SysService;


public class ExcelUtil {
	 
	 
	public static HSSFWorkbook export(List<StUser> list,Map<Integer,String> map) { 
		 String[] excelHeader = { "昵称","行政区","手机号","注册时间","性别","职业","学历","生日","活动地"};  
	        HSSFWorkbook wb = new HSSFWorkbook();    
	        HSSFSheet sheet = wb.createSheet("sheet1"); 
	        HSSFRow row = sheet.createRow((int) 0); 
	        HSSFCellStyle style = wb.createCellStyle();    
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
	        sheet.setColumnWidth(0, 15*2*256);
	        sheet.setColumnWidth(1, 20*2*256);
	        sheet.setColumnWidth(2, 20*2*256);
	        sheet.setColumnWidth(3, 20*2*256);
	        sheet.setColumnWidth(4, 15*2*256);
	        sheet.setColumnWidth(5, 15*2*256);
	        sheet.setColumnWidth(6, 20*2*256);
	        sheet.setColumnWidth(7, 20*2*256);
	        sheet.setColumnWidth(8, 20*2*256);
	        for (int i = 0; i < excelHeader.length; i++) { 
	            HSSFCell cell = row.createCell(i);    
	            cell.setCellValue(excelHeader[i]);    
	            cell.setCellStyle(style); 
	        }    
	        String[] cs={"公务员","教师","医务人员","科研人员","学生","农民","工人","企业主","企业管理人员","金融服务业","律师","技术人员","自由职业"};
			String[] edu={"博士","硕士","大学","大专 ","高中 ","高中以下"};
	        for (int i = 0; i < list.size(); i++) {    
	            row = sheet.createRow(i + 1);     
	            row.createCell(0).setCellValue(list.get(i).getNickname());    
	            row.createCell(1).setCellValue(list.get(i).getArea()==null?"":
	            	map.get(list.get(i).getArea()));  
	            row.createCell(2).setCellValue(list.get(i).getMobile());  
	            row.createCell(3).setCellValue(list.get(i).getRegtime()==null?"":DateUtil.formatDate(list.get(i).getRegtime(), "yyyy-MM-dd HH:mm:ss"));  
	            row.createCell(4).setCellValue(list.get(i).getSex()==1?"男":"女");  
	            // 数组-1
	            row.createCell(5).setCellValue(list.get(i).getCareer()==null?"":cs[list.get(i).getCareer()-1]); 
	            row.createCell(6).setCellValue(list.get(i).getEducation()==null?"":edu[list.get(i).getEducation()-1]); 
	            row.createCell(7).setCellValue(list.get(i).getBirthday()==null?"":DateUtil.formatDate(list.get(i).getBirthday(), "yyyy/MM"));
	            row.createCell(8).setCellValue(list.get(i).getAddress());
	        }    
	        return wb;    
	    }   
	
	public static HSSFWorkbook exportStatistic(List<UserStatistic> list) { 
		 String[] excelHeader = {"行政区名","统计数"};  
	        HSSFWorkbook wb = new HSSFWorkbook();    
	        HSSFSheet sheet = wb.createSheet("sheet1"); 
	        HSSFRow row = sheet.createRow((int) 0); 
	        HSSFCellStyle style = wb.createCellStyle();    
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
	        sheet.setColumnWidth(0, 15*2*256);
	        sheet.setColumnWidth(1, 20*2*256);
	        for (int i = 0; i < excelHeader.length; i++) { 
	            HSSFCell cell = row.createCell(i);    
	            cell.setCellValue(excelHeader[i]);    
	            cell.setCellStyle(style); 
	        }    
	        for (int i = 0; i < list.size(); i++) {    
	            row = sheet.createRow(i + 1);     
	            row.createCell(0).setCellValue(list.get(i).getName());    
	            row.createCell(1).setCellValue(list.get(i).getNumber());
	        }    
	        return wb;    
	}  
	public static HSSFWorkbook exportReading(List<ActivityResult> list,  Map<Integer,String> map) { 
		 String[] excelHeader = {"昵称","手机号","总分","名次","所属行政区"};  
	        HSSFWorkbook wb = new HSSFWorkbook();    
	        HSSFSheet sheet = wb.createSheet("sheet1"); 
	        HSSFRow row = sheet.createRow((int) 0); 
	        HSSFCellStyle style = wb.createCellStyle();    
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
	        //sheet.setColumnWidth(0, 15*2*256);
	        //sheet.setColumnWidth(1, 20*2*256);
	        for (int i = 0; i < excelHeader.length; i++) { 
	            HSSFCell cell = row.createCell(i);    
	            cell.setCellValue(excelHeader[i]);    
	            cell.setCellStyle(style); 
	        }    
	        for (int i = 0; i < list.size(); i++) {    
	            row = sheet.createRow(i + 1);     
	            row.createCell(0).setCellValue(list.get(i).getShowName());    
	            row.createCell(1).setCellValue(list.get(i).getMobile());
	            row.createCell(2).setCellValue(list.get(i).getTotal());
	            row.createCell(3).setCellValue(list.get(i).getRowNum());
	            String name=map.get(list.get(i).getVillage());
	            row.createCell(4).setCellValue(name);
	        }    
	        return wb;    
	}  
	public static HSSFWorkbook exportRead(List<ReadResult> list) { 
		 String[] excelHeader = {"行政区名","阅读数"};  
	        HSSFWorkbook wb = new HSSFWorkbook();    
	        HSSFSheet sheet = wb.createSheet("sheet1"); 
	        HSSFRow row = sheet.createRow((int) 0); 
	        HSSFCellStyle style = wb.createCellStyle();    
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
	        //sheet.setColumnWidth(0, 15*2*256);
	        //sheet.setColumnWidth(1, 20*2*256);
	        for (int i = 0; i < excelHeader.length; i++) { 
	            HSSFCell cell = row.createCell(i);    
	            cell.setCellValue(excelHeader[i]);    
	            cell.setCellStyle(style); 
	        }    
	        for (int i = 0; i < list.size(); i++) {    
	            row = sheet.createRow(i + 1);     
	            row.createCell(0).setCellValue(list.get(i).getName());    
	            row.createCell(1).setCellValue(list.get(i).getNum());
	        }    
	        return wb;    
	} 
	public static HSSFWorkbook exportActive(List<BaseSS> list) { 
		 String[] excelHeader = {"行政区名","注册总数","用户数","月活跃度(%)","点击量"};  
	        HSSFWorkbook wb = new HSSFWorkbook();    
	        HSSFSheet sheet = wb.createSheet("sheet1"); 
	        HSSFRow row = sheet.createRow((int) 0); 
	        HSSFCellStyle style = wb.createCellStyle();    
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
	        //sheet.setColumnWidth(0, 15*2*256);
	        //sheet.setColumnWidth(1, 20*2*256);
	        for (int i = 0; i < excelHeader.length; i++) { 
	            HSSFCell cell = row.createCell(i);    
	            cell.setCellValue(excelHeader[i]);    
	            cell.setCellStyle(style); 
	        }    
	        for (int i = 0; i < list.size(); i++) {    
	            row = sheet.createRow(i + 1);     
	            row.createCell(0).setCellValue(list.get(i).getName());    
	            row.createCell(1).setCellValue(list.get(i).getTotal());
	            row.createCell(2).setCellValue(list.get(i).getPeople());
	            row.createCell(3).setCellValue(list.get(i).getActive());
	            row.createCell(4).setCellValue(list.get(i).getView());
	        }    
	        return wb;    
	} 
	public static HSSFWorkbook exportClick(List<ClickResult> list) { 
		 String[] excelHeader = {"行政区名","总点击量","首页点击","首页占比","乡镇点击","乡镇占比","服务点击","服务占比","社团点击","社团占比"};  
	        HSSFWorkbook wb = new HSSFWorkbook();    
	        HSSFSheet sheet = wb.createSheet("sheet1"); 
	        HSSFRow row = sheet.createRow((int) 0); 
	        HSSFCellStyle style = wb.createCellStyle();    
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
	        //sheet.setColumnWidth(0, 15*2*256);
	        //sheet.setColumnWidth(1, 20*2*256);
	        for (int i = 0; i < excelHeader.length; i++) { 
	            HSSFCell cell = row.createCell(i);    
	            cell.setCellValue(excelHeader[i]);    
	            cell.setCellStyle(style); 
	        }    
	        for (int i = 0; i < list.size(); i++) {    
	            row = sheet.createRow(i + 1);     
	            row.createCell(0).setCellValue(list.get(i).getName());    
	            row.createCell(1).setCellValue(list.get(i).getTotal());
	            row.createCell(2).setCellValue(list.get(i).getType1());
	            row.createCell(3).setCellValue(list.get(i).getP1());
	            row.createCell(4).setCellValue(list.get(i).getType2());
	            row.createCell(5).setCellValue(list.get(i).getP2());
	            row.createCell(6).setCellValue(list.get(i).getType3());
	            row.createCell(7).setCellValue(list.get(i).getP3());
	            row.createCell(8).setCellValue(list.get(i).getType4());
	            row.createCell(9).setCellValue(list.get(i).getP4());
	        }    
	        return wb;    
	}
	
	public static HSSFWorkbook exportDetail(String data[][],
			List<StVillage> villageList,List<StClassify> classList) { 
		 //String[] excelHeader = {"行政区名","总点击量","首页点击","首页占比","乡镇点击","乡镇占比","服务点击","服务占比","社团点击","社团占比"};  
		    String[] excelHeader;
		    StringBuffer sb=new StringBuffer();
		    sb.append("行政区名").append(",").append("首页点击量").append(",");
		    for (StClassify cf : classList) {
		    	sb.append(cf.getClassifyname()).append(",");
			}
		    excelHeader=sb.deleteCharAt(sb.length()-1).toString().split(",");
		    HSSFWorkbook wb = new HSSFWorkbook();    
	        HSSFSheet sheet = wb.createSheet("sheet1"); 
	        HSSFRow row = sheet.createRow((int) 0); 
	        HSSFCellStyle style = wb.createCellStyle();    
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
	        for (int i = 0; i < excelHeader.length; i++) { 
	            HSSFCell cell = row.createCell(i);    
	            cell.setCellValue(excelHeader[i]);    
	            cell.setCellStyle(style); 
	        }    
	        for (int i = 0; i < villageList.size(); i++) {    
	            row = sheet.createRow(i + 1);  
	            for (int j = 0; j < excelHeader.length; j++) {
					if(j==0)
						 row.createCell(0).setCellValue(villageList.get(i).getName());
					else
						 row.createCell(j).setCellValue(data[i][j-1]);
				}
	        }    
	        return wb;    
	}
	
	public static void main(String[] args) throws IOException {
		//发件人信息	收件人信息	款式	数量	货款	快递	送货区域  
		//SELECT * FROM t_user WHERE regTime>='2016-03-04' AND regTime<='2016-03-07'
		//翟云霞，15810520728	收货地址： 李海花 ，13663130063 ， ，河北省 张家口市 蔚县 蔚县行政省批大厅 ，075700	微笑甜心/男-图片色-xl码×1??? 微笑甜心/女-图片色-m码×1??? 情侣钥匙扣-随机-0码×1???	3	155	申通快递	河北省

		try {
			/*InputStream is=new FileInputStream("C:\\Users\\niepan\\Desktop\\3.xls");
			POIFSFileSystem psf=new POIFSFileSystem(is);
			HSSFWorkbook hw=new HSSFWorkbook(psf);
			
			ExcelExtractor excelExtractor=new ExcelExtractor(hw);
			excelExtractor.setIncludeSheetNames(false);
			System.out.println(excelExtractor.getText());*/
			Workbook wb=new HSSFWorkbook();
			Sheet sheet=wb.createSheet("sheet1");
			Row row=sheet.createRow(2);  //创建行
			row.setHeightInPoints(15);
			
			createCell(wb, row, (short)0, HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.ALIGN_CENTER);
			createCell(wb, row, (short)1, HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.ALIGN_CENTER);
			createCell(wb, row, (short)2, HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.ALIGN_CENTER);
			createCell(wb, row, (short)3, HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.ALIGN_CENTER);
			createCell(wb, row, (short)4, HSSFCellStyle.ALIGN_CENTER,HSSFCellStyle.ALIGN_CENTER);
			
			FileOutputStream fileOut=new FileOutputStream("C:\\Users\\niepan\\Desktop\\1.xls");
			wb.write(fileOut);
			fileOut.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void createCell(Workbook wb,Row row,short colunmn,short halign,short valign){
		Cell cell=row.createCell(colunmn);  //创建单元格
		cell.setCellValue(new HSSFRichTextString("Align at"));  //设置值
		CellStyle cellStyle=wb.createCellStyle();
		cellStyle.setAlignment(halign); //设置单元格水平方向对齐
		cellStyle.setVerticalAlignment(valign); //设置单元格垂直方向对其
		cell.setCellStyle(cellStyle); //设置单元格样式
		
		
	}
}
