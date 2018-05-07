package com.kepu.util;

import java.util.UUID;

public class ReturnUtil {
	public static  String getReturnNo(){
		int machineId=2;  //��Ⱥ����������  �˻�ר��
		int hashCodeV=UUID.randomUUID().toString().hashCode();
		hashCodeV=hashCodeV<0?-hashCodeV:hashCodeV;
		return machineId+String.format("%015d", hashCodeV);
	}
	public static  String getCheckNo(){
		int machineId=3;  //��Ⱥ����������  �˵�ר��
		int hashCodeV=UUID.randomUUID().toString().hashCode();
		hashCodeV=hashCodeV<0?-hashCodeV:hashCodeV;
		return machineId+String.format("%015d", hashCodeV);
	}
    public static void main(String[] args){
    		for (int i = 0; i < 10; i++) {
    			System.out.println(ReturnUtil.getReturnNo());  
    			
			}
            
           
	}
}
