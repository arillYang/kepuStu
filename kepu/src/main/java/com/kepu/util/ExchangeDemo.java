package com.kepu.util;

public class ExchangeDemo {
	public static void main(String[] args) {
		int x=1;
		int y=2;
		x^=y;
		y^=x;
		x^=y;
		System.out.println("x="+x+",y="+y);
	}
}
