package com.kepu.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class SerializeUtil {
	public static String serialize(Object object) {
        ObjectOutputStream oos = null;
         ByteArrayOutputStream baos = null;
         try {
              // ���л�
             baos = new ByteArrayOutputStream();
             oos = new ObjectOutputStream(baos);
             oos.writeObject(object);
              byte[] bytes = baos.toByteArray();
              return bytes.toString();
        } catch (Exception e) {

        }
         return null;
  }

   public static Object unserialize( byte[] bytes) {
        ByteArrayInputStream bais = null;
         try {
              // �����л�
             bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais);
              return ois.readObject();
        } catch (Exception e) {

        }
         return null;
  }
   
   public static int findMaxDivision(int[] A, int n) {
       int max=Integer.MIN_VALUE;
       int min=Integer.MAX_VALUE;
       for(int i=0;i<n;i++){
           if(A[i]>max)  max=A[i];
           if(A[i]<min)  min=A[i];
       }
       if(max==min)
           return 0;
       int drupNum=max-min+1;
       int drup[]=new int[drupNum];
       for(int i=0;i<drupNum;i++){
           drup[i]=-1;
       }
       for(int i=0;i<n;i++){
           drup[A[i]-min]=A[i];
       }
       int result=1;
       int temp=1;
       for(int i=0;i<drupNum;i++){
           if(drup[i]==-1)
               result++;
           else{
               temp=result>temp?result:temp;
               result=1;
           }
       }
       return temp;
   }
   
   
   public static ListNode reverse(ListNode n){
	   if(n==null||n.next==null)
		   return n;
	   ListNode head=new ListNode(-1);
	   head.next=n;
	   while(n.next!=null){
		   ListNode temp=head.next;
		   head.next=n.next;
		   n.next=n.next.next;
		   head.next.next=temp;
	   }
	   return head.next;
   }
   public static void main(String[] args) throws CloneNotSupportedException {
	   int A[]={11,7,7,6,14,2,14,15,2,1,2,12,13,9,8,15,13,8,10,11,14,10,2,9,4,9,3,7,6,10,15,4,7,6,15,3,9,13,5,2,6,10,10,1,12,4,3,3,8,8,1,4,7,11,13,5,13,15,4,3,1,11,6,11,9,9,11,15,12,10,13,3,11,4,8,9,7,3,13,9,11,3,2,11,10,1,4,2,3,3,14,11,5,10,1,14,8,1,11,3,1,9,14,6,1,7,15,10,14,6,4,12,11};
	   int n=113;
	   int sum=16;
	   int count=countPairs(A,n,sum);
	   System.out.println(count);
   }
   
   public static int countPairs(int[] A, int n, int sum) {
       int count=0;
       HashMap<Integer,Integer> map=new HashMap<Integer, Integer>();
       for (int i = 0; i < n; i++) {
    	   if(map.get(A[i])==null)
    		   map.put(A[i], 1);
    	   else
    		   map.put(A[i], map.get(A[i])+1);
       }
       for (int i = 0; i < n; i++) {
    	   int want=sum-A[i];
    	   if(map.containsKey(want)){
    		   if(want==A[i])
    			   count+=map.get(want)-1;
    		   else
    			   count+=map.get(want);
    	   }
       }
       return count/2;
   }
}
class ListNode  {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}