package cn.itcast.utils;

import java.util.UUID;

public class ContactUtils {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}