package com.kepu.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.kepu.service.UserService;


public class DaoRu {
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		
	}
	public static int getEdu(String edu){
		if(edu.equals("博士"))
			return 1;
		else if(edu.equals("硕士"))
			return 2;
		else if(edu.equals("大学"))
			return 3;
		else if(edu.equals("大专"))
			return 4;
		else if(edu.equals("高中"))
			return 5;
		else if(edu.equals("高中以下"))
			return 6;
		return 6;
	}
	public static int getCarrer(String career){
		//1 公务员 2 教师 3 医务人员 4 科研人员 5 学生 6农民 7工人 8企业主
		//9 企业管理人员 10 金融服务业 11 律师 12 技术人员 13 自由职业
		if(career.equals("公务员 "))
			return 1;
		else if(career.equals("教师"))
			return 2;
		else if(career.equals("医务人员"))
			return 3;
		else if(career.equals("科研人员"))
			return 4;
		else if(career.equals("学生"))
			return 5;
		else if(career.equals("农民"))
			return 6;
		else if(career.equals("工人"))
			return 7;
		else if(career.equals("企业主"))
			return 8;
		else if(career.equals("企业管理人员"))
			return 9;
		else if(career.equals("金融服务业"))
			return 10;
		else if(career.equals("律师"))
			return 11;
		else if(career.equals("技术人员"))
			return 12;
		else if(career.equals("自由职业"))
			return 13;
		return 13;
	}
	
}