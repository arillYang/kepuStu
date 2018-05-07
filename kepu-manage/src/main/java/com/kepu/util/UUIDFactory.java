package com.kepu.util;

import java.util.UUID;

public class UUIDFactory {
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}
}
