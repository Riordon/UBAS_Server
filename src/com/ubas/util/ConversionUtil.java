package com.ubas.util;

public class ConversionUtil {
	/**
	 * 字符串转Long 处理异常 加入日志
	 * 
	 * @param str
	 * @return
	 */
	public static long strParseLong(String str) {
		long result = 0;
		try {
			result = Long.parseLong(str);
			return result;
		} catch (NumberFormatException nfe) {
			Logs.addLog(str + "转换'long'失败");
			return 0;
		}
	}

	/**
	 * 字符串转Int 处理异常 加入日志
	 * 
	 * @param str
	 * @return
	 */
	public static int strParseInt(String str) {
		int result = 0;
		try {
			result = Integer.parseInt(str);
			return result;
		} catch (NumberFormatException nfe) {
			Logs.addLog(str + "转换'int'失败");
			return 0;
		}
	}
}
