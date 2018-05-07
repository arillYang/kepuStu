package com.kepu.util;




public class UtilApp {

	
	
	
	/**
	 * ����ָ��λ��������
	 * 
	 * @param
	 * @return
	 */
	public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
             random = random + 0.1;
        } for (int i = 0; i < length; i++) {
             num = num * 10;
        }
        return (int) ((random * num));
 }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*System.out.println(("8".split("\\|")).length);
		System.out.println(UtilApp.buildRandom(6));*/
		System.out.println("3.0.1".compareTo("3.0.1"));
	}

}
