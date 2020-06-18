package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface InterfaceDao {

	/**
	 * 查所有中间件
	 */
	@Select("SELECT * FROM t_midware  WHERE t_midware.is_delete='0'")
	List<Map<String,Object>> qryMidware();
	
	
	/**
	 * 查中间件所有类构建父类
	 */
	@Select("SELECT c.c_id FROM t_class c WHERE c.is_delete='0' AND c.c_midware=#{midwareId} GROUP BY c.c_id")
	List<Map<String,Object>> qryClsBeFather(String midwareId);
	
	
	/**
	 * 根据中间件查下属所有类
	 */
	@Select("SELECT c.* FROM t_class c WHERE c.c_midware=#{midwareId} AND c.is_delete='0'" )
	List<Map<String,Object>> qryMidwareClass(String midwareId);
	
	/**
	 * 查询中间件下所有父类，构建下拉框
	 */
	@Select("SELECT c.c_father FROM t_class c " + 
			"WHERE c.c_midware=#{midwareId} AND c.is_delete='0'  " + 
			" GROUP BY c.c_father")
	List<Map<String,Object>> QryMidClsFather(String midwareId);
	

	
	/**
	 * 根据中间件和父类查下属所有类
	 */
	@Select("SELECT c.* FROM t_class c " + 
			"WHERE c.c_midware =#{midwareId} AND c.c_father=#{cfather} AND c.is_delete='0' " )
	List<Map<String,Object>> qryMidClsByFather(String cfather,String midwareId);

	/**
	 * 根据类标识查类信息
	 */
	@Select("SELECT * FROM t_class c "+
			"WHERE c.c_id= #{cId}  AND c.is_delete='0'" )
	Map<String,Object> qryMidClsByCId(String cId);

	/**
	 *  新增类
	 */
	@Insert("INSERT INTO t_class (c_id,c_name,c_desc,c_father,c_midware)VALUE(#{modalCId},#{modalCName},"
			+ "#{modalCDesc},#{modalCFather},#{modalCMidware})")
	int InsertCls(String modalCId, String modalCName, String modalCDesc, String modalCFather, String modalCMidware);


	/**
	 *  编辑更新类
	 */
	@Update("UPDATE  t_class SET c_name=#{modalCName},"
			+ "c_desc=#{modalCDesc},c_father=#{modalCFather}"
			+ "WHERE t_class.c_id =#{modalCId}")
	int UpdateCls(String modalCId, String modalCName, String modalCDesc, String modalCFather);
	
	
	/**
	 * 	删除类
	 */
	
	@Update("UPDATE t_class c SET c.is_delete ='1' WHERE c.c_id=#{cId}")
	int deleteCls(String cId);
	
	
	
	/**
	 * 查询类下属接口
	 */
	@Select("SELECT i.i_id,i_name,i.i_desc,i_return,i.i_para_count,i.i_para_list FROM t_interface i " + 
			"WHERE i.i_class=#{classId} AND i.is_delete='0'" )
	List<Map<String,Object>> qryClsInterface(String classId);
	
	/**
	 * 查询类下属接口的所有的参数个数构建下拉框
	 */
	@Select("SELECT i.i_para_count FROM t_interface i WHERE i.i_class=#{classId} AND i.is_delete='0'GROUP BY i.i_para_count" )
	List<Map<String,Object>> qryClsParaCount(String classId);
	
	/**
	 * 查询类下属接口的所有的返回值类型构建下拉框
	 */
	@Select("SELECT i.i_return FROM t_interface i WHERE i.i_class=#{classId} AND i.is_delete='0'GROUP BY i.i_return" )
	List<Map<String,Object>> qryClsReturnType(String classId);
	
	/**
	 * 根据接口标识、参数个数、参数返回值正序列表 找到唯一的接口
	 */
	@Select("SELECT * FROM `t_interface` i " + 
			"WHERE i.i_id =#{interfaceId} AND i.i_para_count=#{interfaceParaCount} AND  i.i_para_list= #{interfaceParaList} "
			+ "AND i.is_delete='0'")
	Map<String,Object>  qryFindUniqueInterface(String interfaceId,String interfaceParaCount,
			 String interfaceParaList);
	
	/**
	 * 根据接口的唯一数据id序号，找参数
	 */
	@Select("SELECT i.i_id,p.para_attr,p.para_id,p.para_name,p.para_type,p.para_eq," + 
			"p.para_desc,p.para_phy_dim,p.para_min,p.para_max,p.para_default " + 
			"FROM t_parameter p " + 
			"LEFT JOIN t_interface i ON p.para_interface = i.id " + 
			"WHERE p.para_interface= #{id}  AND  p.is_delete='0' " + 
			"ORDER BY p.para_eq")
	
	List<Map<String,Object>> qryInterfacePara(String id);
	
	
	/**
	 * 根据下拉框【参数个数】查找接口
	 */
	@Select("SELECT i.i_id,i_name,i_return,i.i_para_count,i.i_para_list FROM t_interface i " + 
			"WHERE i.i_class=#{classId} AND i.is_delete='0' AND i.i_para_count=#{selectCount}")
	List<Map<String,Object>> qryInterfaceByParaCount(String selectCount,String classId);
	
	/**
	 * 根据下拉框【参数返回值类型】查找接口
	 */
	@Select("SELECT i.i_id,i_name,i_return,i.i_para_count,i.i_para_list FROM t_interface i " + 
			"WHERE i.i_class=#{classId} AND i.is_delete='0' AND i.i_return=#{selectReturn}")
	List<Map<String,Object>> qryInterfaceByParaReturnType(String selectReturn,String classId);
	
	/**
	 * 根据下拉框【参数返回值类型】、【参数个数】查找接口
	 */
	@Select("SELECT i.i_id,i_name,i.i_desc,i_return,i.i_para_count,i.i_para_list FROM t_interface i " + 
			"WHERE i.i_class=#{classId} AND i.is_delete='0' AND i.i_return=#{selectReturn} "
			+ "AND i.i_para_count=#{selectCount}")
	List<Map<String,Object>> qryInterfaceBySelect(String selectReturn,String selectCount,String classId); 
	
	/**
	 * 新增接口
	 */
	@Insert("INSERT INTO t_interface (i_id,i_name,i_desc,i_class,i_return,i_return_desc,i_remark,i_para_list,i_para_count) "
			+ "VALUE(#{interfaceId},#{interfaceName},#{interfaceDesc},#{interfaceCls},#{interfaceRetnTyp},#{interfaceRetnDesc}, "
			+ "#{interfaceRemark},#{interfaceParaList},#{interfaceParaCount} )"  
			)
	int InsertInterface(String interfaceId,String interfaceName,String interfaceDesc,
			String interfaceRemark,String interfaceRetnTyp,String interfaceRetnDesc,
			String interfaceCls,String interfaceParaList,String interfaceParaCount);
	
	
	
	/**
	 * 新增参数之前判断是否唯一
	 */
	@Select("SELECT * FROM( " + 
			"SELECT * FROM t_parameter p " + 
			"WHERE p.para_interface=#{uniqueInterid} AND p.is_delete='0' ) a " + 
			"WHERE a.para_eq=#{paraNo} OR a.para_id=#{paraId} ")
	List<Map<String,Object>>  qryParaIsExist(String paraNo,String paraId,String uniqueInterid);
	
	
	

	
	/**
	 * 新增接口参数
	 */
	@Insert("INSERT INTO t_parameter "
			+ "(para_id,para_name,para_desc,para_type,para_attr,para_eq,para_interface,para_phy_dim,para_max,para_min,para_default) "
			+ "VALUE(#{paraId},#{paraName},#{paraDesc},#{paraType},#{paraAttr},#{paraNo}, "
			+ "#{id},#{paraPhy},#{paraMax},#{paraMin},#{paraDefault} )"  
			)
	int InsertInterfacePara(String paraId,String paraName,String paraDesc,
			String paraType,String paraAttr,String paraNo,
			String id,String paraPhy,String paraMax,String paraMin,String paraDefault);
	
	
	/**
	 * 删除接口
	 */
	@Update("UPDATE t_interface i SET i.is_delete ='1' WHERE i.id=#{id}")
	int deleteInterface(String id);
	
	/**
	 * 删除接口所有参数
	 */
	@Update("UPDATE t_parameter p SET p.is_delete ='1' WHERE p.para_interface=#{id}")
	int deleteInterfacePara(String id);
	
	
	
	/**
	 *  编辑更新接口
	 */
	@Update("UPDATE  t_interface  i SET i.i_name=#{interfaceName},"
			+ "i.i_desc=#{interfaceDesc},i.i_remark=#{interfaceRemark},i.i_return= #{interfaceRetnTyp},"
			+ "i.i_return_desc =#{interfaceRetnDesc}"
			+ "WHERE i.i_id =#{interfaceId} AND i.i_para_count =#{interfaceParaCount} AND i.i_para_list= #{interfaceParaList}")
	int updateInterface(String interfaceName,String interfaceDesc,String interfaceRemark,
			String interfaceRetnTyp,String interfaceRetnDesc,
			String interfaceId,String interfaceParaCount,String interfaceParaList);
	
	/**
	 * 编辑参数之前根据所属接口和参数次序进行回填
	 */
	@Select("SELECT * FROM t_parameter p " + 
			"WHERE p.para_interface=#{uniqueInterid} AND p.is_delete='0' AND p.para_eq=#{paraNo}")
	Map<String,Object> qryParaFillback(String uniqueInterid,String paraNo);

	
	
	
	/**
	 *  修改参数后对接口进行修改
	 */
	@Update("UPDATE  t_interface  i SET i.i_para_count =#{pCount},"
			+ "i.i_para_list= #{paraList}"
			+ "WHERE i.id =#{uniqueInterid} ")
	int UpdateIinfo(String pCount,String paraList,String uniqueInterid);
	
	
	/**
	 * 修改接口参数
	 */
	@Update("UPDATE  t_parameter p  SET "
			+ "p.para_name=#{paraName},p.para_desc=#{paraDesc},p.para_type= #{paraType},"
			+ "p.para_attr =#{paraAttr},p.para_eq=#{paraNo},"
			+"p.para_phy_dim=#{paraPhy},p.para_max=#{paraMax},p.para_min=#{paraMin},p.para_default=#{paraDefault}"
			+ "WHERE p.para_interface=#{id} AND p.para_id=#{paraId}")

	int UpdateParaData(String paraId, String paraName, String paraDesc, 
			String paraType, String paraAttr, String paraNo, String id, 
			String paraPhy, String paraMax, String paraMin, String paraDefault);
	
	/**
	 * 单个删除参数
	 */
	@Update("UPDATE t_parameter p SET p.is_delete ='1' WHERE p.para_interface=#{uniqueInterid} AND p.para_eq =#{paraNo}")
	int deleteOnePara(String uniqueInterid,String paraNo);
}