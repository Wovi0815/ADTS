package com.csrda.adts.service;

import java.util.List;
import java.util.Map;

public interface OtherDataService{

	/**
	 * 新增结构体,系列操作
	 */
	public String addStruct(String structId,String structName,String structSize,String structDesc,String memList);
	

	/**
	 * 查数据类型
	 */
	public List<Map<String, Object>> qryDataType();
	/**
	 * 保存数据类型
	 */
	public int saveDataType(Map<String,String> typeData);
	/**
	 * 修改数据类型
	 */
	public int updateDataType(Map<String,String> typeData);
	/**
	 * 删除数据类型
	 */
	public int delDataType(String typId);
	
	/**
	 * 数据类型详情
	 */
	public Map<String, Object> qryDetailDataType(String typId);
	/**
	 * 查数据类型是否重复
	 */
	public List<Map<String, Object>> qryTypeRepeat(String typId);
	/**
	 * 查结构体类型是否重复
	 */
	public List<Map<String, Object>> qryStructMemRep(String struct,String memId);
	
	
	public int addStructMem(Map<String, Object> memData);
	
	
	public int delStructMem(String typId);
	
	public List<Map<String, Object>> qryStructMem(String typId);
	
	
	
	
	/**
	 * 根据中间件Id查中间件
	 */
	public Map<String, Object> qryMidByMidId(String midId);
	


	/**
	 * 新增中间件
	 */
	public int InsertMid(String modMidId, String modMidName, String modMidDesc);

	/**
	 * 更新中间件
	 */
	public int UpdateMid(String modMidId, String modMidName, String modMidDesc);

	/**
	 * 删除中间件
	 */
	public int deleteMid(String midId);
	/**
	 * 查询所有节点下的设备
	 */
	public List<Map<String, Object>> qryAllModule();	
	
}
