package com.ibeetl.admin.core.util;

import org.apache.commons.lang3.StringUtils;
import org.beetl.ext.fn.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 数据格式转化类
 * @author xiandafu
 *
 */
public class ConvertUtil {
	/**
	 * 转化逗号分隔的id到long数组，通常用于批量操作
	 * @param str
	 * @return
	 */
	public static List<Long> str2longs(String str){
		if(str.length()==0){
			return Collections.EMPTY_LIST;
		}
		String[] array = str.split(",");
		List<Long> rets = new ArrayList(array.length);
		int i = 0;
		for(String id:array){
			try{
				rets.add(Long.parseLong(id));
			}catch(Exception ex){
				throw new RuntimeException("转化 "+str+ " 到Long数组出错");
			}
			
		}
		return rets;
	}

	/**
	 * 按照逗号分隔的字符串转化为字符串列表
	 * @param str
	 * @return
	 */
	public static List<String> str2Strs(String str){
		if(StringUtils.isBlank(str)){
			return new ArrayList<>();
		}
		return Arrays.asList(str.split(","));
	}
}
