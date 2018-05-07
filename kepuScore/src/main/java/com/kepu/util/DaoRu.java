package com.kepu.util;

public class DaoRu {
	
	public static void main(String[] args) {
		
	}
	public static int getEdu(String edu){
		if(edu.equals("��ʿ"))
			return 1;
		else if(edu.equals("˶ʿ"))
			return 2;
		else if(edu.equals("��ѧ"))
			return 3;
		else if(edu.equals("��ר"))
			return 4;
		else if(edu.equals("����"))
			return 5;
		else if(edu.equals("��������"))
			return 6;
		return 6;
	}
	public static int getCarrer(String career){
		//1 ����Ա 2 ��ʦ 3 ҽ����Ա 4 ������Ա 5 ѧ�� 6ũ�� 7���� 8��ҵ��
		//9 ��ҵ������Ա 10 ���ڷ���ҵ 11 ��ʦ 12 ������Ա 13 ����ְҵ
		if(career.equals("����Ա "))
			return 1;
		else if(career.equals("��ʦ"))
			return 2;
		else if(career.equals("ҽ����Ա"))
			return 3;
		else if(career.equals("������Ա"))
			return 4;
		else if(career.equals("ѧ��"))
			return 5;
		else if(career.equals("ũ��"))
			return 6;
		else if(career.equals("����"))
			return 7;
		else if(career.equals("��ҵ��"))
			return 8;
		else if(career.equals("��ҵ������Ա"))
			return 9;
		else if(career.equals("���ڷ���ҵ"))
			return 10;
		else if(career.equals("��ʦ"))
			return 11;
		else if(career.equals("������Ա"))
			return 12;
		else if(career.equals("����ְҵ"))
			return 13;
		return 13;
	}
	
}