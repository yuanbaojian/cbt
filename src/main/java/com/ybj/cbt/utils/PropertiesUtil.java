package com.ybj.cbt.utils;


import com.ybj.cbt.common.Constants;

import java.io.File;
import java.util.Map;

public class PropertiesUtil {
	private static Map<String, String> msgsMap;


	public static Map<String, String> getMsgsMap() {
		return msgsMap;
	}

	public static void setMsgsMap(Map<String, String> msgsMap) {
		PropertiesUtil.msgsMap = msgsMap;
	}
	/**
	 * 获取电子仓库的根路径的名称
	 * @return
	 */
	public static String getOppositePath(){
		String rootPathC = getMsgsMap().get(Constants.STOREHOUSE_PATH);
		String rootPathO = rootPathC.substring(rootPathC.lastIndexOf(File.separator) + 1, rootPathC.length());
		return rootPathO;
	}
	/**
	 * 获取电子仓库的绝对路径
	 * @return
	 */
	public static String getAbsolutePath(){
		String rootPathC = getMsgsMap().get(Constants.STOREHOUSE_PATH);
		return rootPathC;
	}
}
