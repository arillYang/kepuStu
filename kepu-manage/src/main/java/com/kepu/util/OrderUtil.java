package com.kepu.util;

import java.util.UUID;

public class OrderUtil {
	public static  String getOrderNo(){
		int machineId=1;  //��Ⱥ����������
		int hashCodeV=UUID.randomUUID().toString().hashCode();
		hashCodeV=hashCodeV<0?-hashCodeV:hashCodeV;
		return machineId+String.format("%015d", hashCodeV);
	}
    public static void main(String[] args){
    		for (int i = 0; i < 10; i++) {
    			System.out.println(OrderUtil.getOrderNo());  
    			
			}
            
           
	}
}
