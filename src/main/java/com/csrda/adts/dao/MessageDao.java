package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MessageDao {
	/**
	 *  查所有的报文类型,构建菜单
	 */
	 @Select("SELECT m.mes_type FROM t_message m WHERE m.is_delete = '0' GROUP by m.mes_type" )
	 List<Map<String, Object>> QueryMessageTyp();

	/**
	 *  根据报文类型,查所有的报文
	*/
	 @Select("SELECT " + 
	 		"id, mes_id as mesId,mes_name as mesName,mes_desc as mesDesc,"+ 
	 		"mes_source as mesSource,mes_destination as mesDestination," + 
	 		"mes_id_num as mesIdNum,mes_fun_id as mesFunId,mes_type as mesType " + 
	 		"FROM t_message " + 
	 		"WHERE	mes_type = #{mesType} AND is_delete ='0' ")
	  List<Map<String, Object>> qryMessage(String mesType);
	 /**
	  * 查所有的硬件模块种类
	  */
	 @Select("SELECT * FROM t_module m WHERE m.is_delete = '0' GROUP by m.mod_pid" )
	 List<Map<String, Object>> qryModuleKind();
	
	 /**
	   * 根据下拉框信源查询报文
	 */
	 
	 @Select("<script>" +
			 "SELECT " + 
		 		"id, mes_id as mesId,mes_name as mesName,mes_desc as mesDesc,"+ 
		 		"mes_source as mesSource,mes_destination as mesDestination," + 
		 		"mes_id_num as mesIdNum,mes_fun_id as mesFunId,mes_type as mesType " + 
		 	"FROM t_message " + 
	 	    "WHERE	mes_type = #{mesType} AND is_delete ='0' AND "+
	 	   "<if test='sourceList.size > 1'>"+
	 	   "<foreach collection='sourceList' item='sourceMap' index='index' separator='OR'>"+
	 	   "mes_source like '%${sourceMap.source}%' "+
 		   "</foreach>"+
 		   "</if>"+
 		   "<if test='sourceList.size == 1'>"+
 		   "<foreach collection='sourceList' item='sourceMap' index='index' separator=' '>"+
 		   "mes_source like '%${sourceMap.source}%' "+
 		   "</foreach>"+
 		   "</if>"+
 		   "</script>")
	
	 	List<Map<String,Object>>qryMesByDtSource(@Param("sourceList") List<Map<String ,Object>> sourceList,@Param("mesType") String mesType);
		
		/**
		 * 根据下拉框信宿查询报文
		 */
	 @Select("<script>" +
			 "SELECT " + 
		 		"id, mes_id as mesId,mes_name as mesName,mes_desc as mesDesc,"+ 
		 		"mes_source as mesSource,mes_destination as mesDestination," + 
		 		"mes_id_num as mesIdNum,mes_fun_id as mesFunId,mes_type as mesType " + 
		 	"FROM t_message " + 
	 	    "WHERE	mes_type = #{mesType} AND is_delete ='0' AND " +
		 	  "<if test='destinationList.size > 1'>"+
	 		  "<foreach collection='destinationList' item='destinationMap' index='index' separator='OR'>"+
	 		  "mes_destination like '%${destinationMap.destination}%' "+
	 		  "</foreach>"+
	 		  "</if>"+
	 		 "<if test='destinationList.size == 1'>"+
	 		 "<foreach collection='destinationList' item='destinationMap' index='index' separator=' '>"+
	 		 "mes_destination like '%${destinationMap.destination}%' "+
	 		 "</foreach>"+
	 		  "</if>"+
 		   "</script>")
	 
	 	List<Map<String, Object>> qryMesByDtDestination(@Param("destinationList") List<Map<String ,Object>> destinationList,@Param("mesType") String mesType);
	 
	 /**
	  * 根据信源和信宿查报文
	  */
	 @Select("<script>" +
			 "SELECT " + 
			 		"id, mes_id as mesId,mes_name as mesName,mes_desc as mesDesc,"+ 
			 		"mes_source as mesSource,mes_destination as mesDestination," + 
			 		"mes_id_num as mesIdNum,mes_fun_id as mesFunId,mes_type as mesType " + 
			 	"FROM t_message " + 
		 	    "WHERE	mes_type = #{mesType} AND is_delete ='0'  AND "+ 
		 	   "<if test='sourceList.size > 1'>"+
		 	   "<foreach collection='sourceList' item='sourceMap' index='index' separator='OR'>"+
		 	   "<if test='index == 0'>"+
		 	   "(mes_source like '%${sourceMap.source}%' "+
	 		   "</if>"+
	 		   "<if test='index != 0'>"+
		 	   "mes_source like '%${sourceMap.source}%' "+
		 	   "</if>"+
		 	   "</foreach>"+
	 		   "</if>"+
	 		   "<if test='sourceList.size == 1'>"+
	 		   "<foreach collection='sourceList' item='sourceMap' index='index' separator=''>"+
	 		   "(mes_source like '%${sourceMap.source}%' "+
	 		   "</foreach>"+
	 		   "</if>"+
	 		  "<if test='destinationList.size > 1'>"+
	 		  "<foreach collection='destinationList' item='destinationMap' index='index' separator='OR'>"+
	 		  "<if test='index == 0'>"+
	 		  ")AND (mes_destination like '%${destinationMap.destination}%'" +
	 		  "</if>"+
	 		  "<if test='index != 0'>"+
	 		  "mes_destination like '%${destinationMap.destination}%') "+
	 		  "</if>"+
	 		  "</foreach>"+
	 		  "</if>"+
	 		  "<if test='destinationList.size == 1'>"+
	 		  "<foreach collection='destinationList' item='destinationMap' index='index' separator=' '>"+
	 		  ")AND mes_destination like '%${destinationMap.destination}%' "+
	 		  "</foreach>"+
	 		  "</if>"+
	 		  "</script>")
	 List<Map<String, Object>> qryMesBySelect(@Param("sourceList") List<Map<String ,Object>> sourceList,@Param("destinationList") List<Map<String ,Object>> destinationList,@Param("mesType") String mesType);
	 
	 

	 /**
	  * 查询报文的进一步详细信息
	  */

	 @Select("SELECT " + 
		 		"id, mes_id ,mes_name ,mes_desc ,mes_source ,mes_destination, " + 
		 		"mes_id_num,mes_fun_id ,mes_type,mes_remark " + 
		 		"FROM t_message " + 
		 		"WHERE	mes_id = #{mesId} AND is_delete ='0' AND mes_type=#{mesTyp} ")
	  Map<String, Object> qryMesDetail(String mesId,String mesTyp);
	 
	 
	 
	 /**
	  * 查询报文的详细信息(数据)
	  */

	 @Select("SELECT " + 
		 		"id, mes_id ,mes_data_range, mes_data_long,mes_data_name ,"+ 
		 		"mes_data_desc ,mes_data_type " + 
		 		"FROM t_mes_data " + 
		 		"WHERE	mes_id = #{mesId} AND is_delete ='0' ")
	  List<Map<String, Object>> qryMesDataDetail(String mesId);
	 
	 /**
	  * 更新报文
	  */
	@Update("UPDATE  t_message  m SET m.mes_name=#{modalmesName},"
				+ "m.mes_desc=#{modalmesDesc},m.mes_remark=#{modalmesRemark},m.mes_fun_id= #{modalmesFunNum},"
				+ "m.mes_id_num =#{modalmesIDNum},m.mes_source =#{modalmesSource},m.mes_destination =#{modalmesDestination}"
				+ "WHERE m.mes_id =#{modalmesId} AND m.mes_type =#{modalmesTyp} ")
	int UpdateMes(String modalmesId,String modalmesName,String modalmesDesc,
			String modalmesRemark,String modalmesFunNum,String modalmesIDNum,
			String modalmesTyp,String modalmesSource,String modalmesDestination);
	
	
	
	
	/**
	 * 新增报文
	 */
	@Insert("INSERT INTO t_message (mes_id,mes_name,mes_desc,mes_remark,mes_source,mes_destination,mes_id_num,mes_fun_id,mes_type) "
			+ "VALUE(#{mesId},#{mesName},#{mesDesc},#{mesRemark},#{mesSource},#{mesDestination}, "
			+ "#{mesID},#{mesFunId},#{mesTyp} )"  
			)
	int InsertMes(String mesId,String mesName,String mesDesc,
			String mesRemark,String mesSource,String mesDestination,
			String mesID,String mesFunId,String mesTyp);
	

	
	/**
	 * 新增报文数据
	 */
	@Insert("INSERT INTO t_mes_data "
			+ "(mes_id,mes_data_range,mes_data_long,mes_data_name,mes_data_desc,mes_data_type) "
			+ "VALUE(#{mesId},#{dataRange},#{dataLong},#{dataName},#{dataDesc},#{dataType})"  
			)
	int InsertMesData(String mesId,String dataRange,String dataLong,String dataName,
			String dataDesc,String dataType);
	
	
	/**
	 * 删除报文
	 */
	@Update("UPDATE t_message m SET m.is_delete ='1' WHERE m.mes_id = #{mesId} AND m.mes_type = #{mesTyp}")
	int deleteMes(String mesId,String mesTyp);
	
	/**
	 * 删除报文的数据
	 */
	@Update("UPDATE t_mes_data d SET d.is_delete ='1' WHERE d.mes_id = #{mesId}")
	int deleteMesData(String mesId);
	
	/**
	 * 报文数据的回填
	 */
	 @Select("SELECT " + 
		 		"id, mes_id ,mes_data_range, mes_data_long,mes_data_name ,"+ 
		 		"mes_data_desc ,mes_data_type " + 
		 		"FROM t_mes_data " + 
		 		"WHERE	mes_data_name = #{dataName} AND mes_id = #{mesId} AND is_delete ='0' ")
	Map<String, Object> QryDataFillback(String dataName,String mesId);
	
	 
}
