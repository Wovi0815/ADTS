package com.csrda.adts.service;

import java.util.List;
import java.util.Map;

public interface InterfaceService {

	/**
	 * 查询所有中间件
	 */
	List<Map<String,Object>> qryMidware();
	
	/**
	 * 查询所有父类
	 */
	List<Map<String,Object>> qryClsFather();
	
	
	
	/**
	 * 查询中间件下属类
	 */
	List<Map<String,Object>> qryMidwareClass(String midwareName);
	
	/**
	 * 查询中间件下属所有父类
	 */
	List<Map<String,Object>> qryMidClsFather(String midwareName);
	
	
	
	/**
	 * 根据下拉框所选择的父类查询类
	 */
	List<Map<String,Object>> qryMidClsByFather(String cfather,String midwareName);
	
	
	/**
	 * 模态框根据类标识，中间件回填数据
	 */
	Map<String,Object> qryMidClsByCId(String cId,String midwareName);
}
