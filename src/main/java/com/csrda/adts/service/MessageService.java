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
	 * 根据下拉框重新刷新表格
	 */
	List<Map<String,Object>> qryMesBySelect(String dataSource,String dataDestination,String mesType);
}
