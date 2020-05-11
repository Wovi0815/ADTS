package com.csrda.adts.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface MessageService {
	/**
	 * 查找报文的类型
	 */
	List<Map<String,Object>> QueryMessageTyp();
	
	/**
	 * 根据报文类型查询所有报文
	 */
	List<Map<String,Object>> qryMessage(String mesType);
	
	/**
	 * 查询所有的硬件模块种类
	 */
	List<Map<String,Object>> qryModuleKind();
	
	/**
	 * 根据下拉框信源查询报文
	 */
	List<Map<String,Object>>qryMesByDtSource(List<Map<String,Object>> sourceList, String mesType);
	
	
	/**
	 * 根据下拉框信宿查询报文
	 */
	List<Map<String,Object>>qryMesByDtDestination(List<Map<String,Object>> destinationList, String mesType);
	
	
	/**
	 * 根据下拉框信源信宿查询报文
	 */
   List<Map<String, Object>> qryMesBySelect(List<Map<String,Object>> sourceList, List<Map<String,Object>> destinationList, String mesType);

	
}
