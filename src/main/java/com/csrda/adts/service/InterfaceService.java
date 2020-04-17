package com.csrda.adts.service;

import java.util.List;
import java.util.Map;

public interface InterfaceService {

	/**
	 * 查询所有中间件
	 */
	List<Map<String,Object>> qryMidware();
	
	/**
	 * 查询中间件所有类构建父类
	 */
	List<Map<String,Object>> qryClsBeFather(String midwareId);
	
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

	/**
	 * 查询类下面的接口
	 */
	List<Map<String,Object>> qryClsInterface(String classId);
	
	/**
	 * 查询接口的所有的参数个数构建下拉框
	 */
	List<Map<String,Object>> qryClsParaCount(String classId);
	
	/**
	 * 查询接口的所有返回值类型构建下拉框
	 */
	List<Map<String,Object>> qryClsReturnType(String classId);
	
	/**
	 * 根据接口标识、参数个数、参数返回值正序列表 找到唯一的接口
	 */
	Map<String,Object>  qryFindUniqueInterface(String interfaceId,String interfaceParaCount,
			 String interfaceParaList);
	
	/**
	 * 根据接口的唯一数据id序号，找参数
	 */
	List<Map<String,Object>> qryInterfacePara(String id);
	
	/**
	 * 根据下拉框【参数个数】查找接口
	 */
	List<Map<String,Object>> qryInterfaceByParaCount(String selectCount,String classId);
	
	/**
	 * 根据下拉框【参数返回值类型】查找接口
	 */
	List<Map<String,Object>> qryInterfaceByParaReturnType(String selectReturn,String classId);
	
	/**
	 * 根据下拉框【参数返回值类型】、【参数个数】查找接口
	 */
	List<Map<String,Object>> qryInterfaceBySelect(String selectReturn,String selectCount,String classId);
}
