package com.csrda.adts.service;

import java.util.List;
import java.util.Map;

public interface OtherDataService{

	public String addStruct(String typId,String typName,String typSize,String typDesc,String memList);
	
	public String updateStruct(String typId, String typName, String typSize, String typDesc, String memList);
	
	
	
	public List<Map<String, Object>> qryTypeData();
	
	public int saveBasicDataType(Map<String,String> typeData);
	
	public int updateBasicDataType(Map<String,String> typeData);
	
	public int delBasicDataType(String typId);
	
	public List<Map<String, Object>> detailBasicDataType(String typId);
	
	public List<Map<String, Object>> qryRep(String typId);
	
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
