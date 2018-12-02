package cn.itcast.utils;

import java.util.UUID;

/**
 * 随机生成字符串
 * @author Administrator
 *
 */
public class MyUUIDUtil {
	
	/**
	 * 随机的生成字符串
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}

}
