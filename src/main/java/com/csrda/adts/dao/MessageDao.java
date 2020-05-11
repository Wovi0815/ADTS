package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface MessageDao {
	/**
	 *  查所有的报文类型,构建菜单
	 */
	 @Select("SELECT m.mes_type FROM t_message m WHERE m.is_delete = '0' GROUP by m.mes_type" )
	 public List<Map<String, Object>> QueryMessageTyp();

	/**
	 *  根据报文类型,查所有的报文
	*/
	 @Select("SELECT " + 
	 		"id, mes_id as mesId,mes_name as mesName,mes_desc as mesDesc,"+ 
	 		"mes_source as mesSource,mes_destination as mesDestination," + 
	 		"mes_id_num as mesIdNum,mes_fun_id as mesFunId,mes_type as mesType " + 
	 		"FROM t_message " + 
	 		"WHERE	mes_type = #{mesType} AND is_delete ='0' ")
	 public List<Map<String, Object>> qryMessage(String mesType);
	 /**
	  * 查所有的硬件模块种类
	  */
	 @Select("SELECT * FROM t_module m WHERE m.is_delete = '0' GROUP by m.mod_pid" )
	 public List<Map<String, Object>> qryModuleKind();
	
	 /**
	   * 根据下拉框信源查询报文
	 */
	 
	 @Select("SELECT " + 
		 		"id, mes_id as mesId,mes_name as mesName,mes_desc as mesDesc,"+ 
		 		"mes_source as mesSource,mes_destination as mesDestination," + 
		 		"mes_id_num as mesIdNum,mes_fun_id as mesFunId,mes_type as mesType " + 
		 	"FROM t_message " + 
	 	    "WHERE	mes_type = #{mesType} AND is_delete ='0' AND mes_source like '%${source}%' ")
	
	 public	List<Map<String,Object>>qryMesByDtSource(String source, String mesType);
		
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
	 
	 public	List<Map<String, Object>> qryMesByDtDestination(@Param("destinationList") List<Map<String ,Object>> destinationList,@Param("mesType") String mesType);
	 
	 /**
	  * 根据信源和信宿查报文
	  */
	 @Select("SELECT " + 
			 		"id, mes_id as mesId,mes_name as mesName,mes_desc as mesDesc,"+ 
			 		"mes_source as mesSource,mes_destination as mesDestination," + 
			 		"mes_id_num as mesIdNum,mes_fun_id as mesFunId,mes_type as mesType " + 
			 	"FROM t_message " + 
		 	    "WHERE	mes_type = #{mesType} AND is_delete ='0' "
		 	    + "AND mes_source like '%${source}%' AND mes_destination like '%${destination}%'")
		 public List<Map<String, Object>> qryMesBySelect(String source, String destination, String mesType);
}
