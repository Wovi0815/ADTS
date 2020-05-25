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

	/**
	 * 查询报文的进一步信息
	 */
   Map<String,Object>  qryMesDetail(String mesId,String mesTyp);
   /**
      * 查询报文数据的信息
    */
   List<Map<String, Object>> qryMesDataDetail(String mesId);
   
   /**
    * 修改报文
    */
   int UpdateMes(String modalmesId,String modalmesName,String modalmesDesc,
			String modalmesRemark,String modalmesFunNum,String modalmesIDNum,String modalmesTyp,
			String modalmesSource,String modalmesDestination);
   
   /**
	 * 新增报文
	 */
	int InsertMes(String mesId,String mesName,String mesDesc,
			String mesRemark,String mesSource,String mesDestination,
			String mesID,String mesFunId,String mesTyp);
	
	/**
	 * 新增报文数据
	 */
	int InsertMesData(String mesId,String dataRange,String dataLong,
			String dataName,String dataDesc,String dataType);
	
	
	/**
	 * 新增报文、报文数据的系列操作
	 */
	String addMesAndData(String mesMap,String mesDataMap);
	
   
}
