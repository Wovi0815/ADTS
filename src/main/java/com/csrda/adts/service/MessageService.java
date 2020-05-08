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
	
	
}
