package com.csrda.adts.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
			String modalCFather);
	
	/**
	 * 删除类
	 */
	int deleteCls(String cId);

	/**
	 * 查询类下面的接口
	 */
	List<Map<String,Object>> qryClsInterface(String classId);
	
	/**
	 * 查询类下属接口的所有的参数个数构建下拉框
	 */
	List<Map<String,Object>> qryInterParaCount(String classId);
	
	/**
	 * 查询类下属接口的所有返回值类型构建下拉框
	 */
	List<Map<String,Object>> qryInterReturnType(String classId);
	
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
	
	/**
	 * 新增接口
	 */
	int InsertInterface(String interfaceId,String interfaceName,String interfaceDesc,
			String interfaceRemark,String interfaceRetnTyp,String interfaceRetnDesc,
			String interfaceCls,String interfaceParaList,String interfaceParaCount);
	
	
	/**
	 * 新增参数之前判断是否唯一
	 */
	Map<String, Object> qryParaIsExit(String paraNo,String paraId,String uniqueInterid);
	
	
	
	
	/**
	 * 新增接口参数
	 */
	int InsertInterfacePara(String paraId,String paraName,String paraDesc,
			String paraType,String paraAttr,String paraNo,
			String id,String paraPhy,String paraMax,String paraMin,String paraDefault);
	
	
	/**
	 * 删除接口
	 */
	int deleteInterface(String id);
	
	/**
	 * 删除接口所有参数
	 */
	int deleteInterfacePara(String id);
	
	/**
	 * 修改接口
	 */
	int updateInterface(String interfaceName,String interfaceDesc,String interfaceRemark,
			String interfaceRetnTyp,String interfaceRetnDesc,
			String interfaceId,String interfaceParaCount,String interfaceParaList);
	
	/**
	 * 编辑参数之前根据所属接口和参数次序进行回填
	 */
	Map<String,Object> qryParaFillback(String uniqueInterid,String paraNo);
	
	
	
	/**
	 *  编辑更新接口
	 */
	int UpdateIinfo(String pCount,String paraList,String uniqueInterid);
	
	/**
	 * 修改接口参数
	 */
	int UpdateParaData(String paraId, String paraName, String paraDesc, 
			String paraType, String paraAttr, String paraNo, String id, 
			String paraPhy, String paraMax, String paraMin, String paraDefault);
	/**
	 * 单个删除接口参数
	 */
	int deleteOnePara(String uniqueInterid,String paraNo);
	
	
	/**
	 * 新增接口和参数的系列操作
	 */
	
	String addInterfaceAndPara(String interfaceMap,String paraListMap) ;
	
}
