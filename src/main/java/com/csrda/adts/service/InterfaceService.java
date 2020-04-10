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
	List<Map<String,Object>> qryMidwareClass(String midwareId);
	
	/**
	 * 查询中间件下属所有父类
	 */
	List<Map<String,Object>> qryMidClsFather(String midwareId);
	
	/**
	 * 根据下拉框所选择的父类查询所有类
	 */
	List<Map<String,Object>> qryMidClsByFather(String cfather,String midwareId);
	
	/**
	 * 模态框根据类标识，中间件回填数据
	 */
	Map<String,Object> qryMidClsByCId(String cId);
	

	
	/**
	 * 新增类
	 */
	int InsertCls(String modalCId,String modalCName,String modalCDesc,
			String modalCFather, String modalCMidware);
	
	/**
	 * 编辑更新类
	 */
	int UpdateCls(String modalCId,String modalCName,String modalCDesc,
			String modalCFather, String modalCMidware);
	
	
	/**
	 * 删除类
	 */
	int deleteCls(String cId);
	
}
