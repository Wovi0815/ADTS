package com.csrda.adts.service;

import java.util.List;
import java.util.Map;

public interface InterfaceService {

	/**
	 * 查询所有中间件
	 */
	List<Map<String,Object>> qryMidware();
	
	/**
	 * 查询中间件下属类
	 */
	List<Map<String,Object>> qryMidwareClass(String midwareName);
	
	/**
	 * 根据下拉框所选择的父类查询类
	 */
	List<Map<String,Object>> qryMidClsByFather(String cfather,String midwareName);
	
	
	
}
