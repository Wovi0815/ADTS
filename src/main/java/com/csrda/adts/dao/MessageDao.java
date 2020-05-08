package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface MessageDao {
	
	 @Select("SELECT m.mes_type FROM t_message m WHERE m.is_delete = '0' GROUP by m.mes_type" )
	 public List<Map<String, Object>> QueryMessageTyp();

	
	 @Select("SELECT " + 
	 		"id, mes_id as mesId,mes_name as mesName,mes_desc as mesDesc,mes_destination as mesDestination," + 
	 		"mes_id_num as mesIdNum,mes_fun_id as mesFunId,mes_type as mesType " + 
	 		"FROM t_message " + 
	 		"WHERE	mes_type = #{mesType}")
	 public List<Map<String, Object>> qryMessage(String mesType);
	 
	 
	 
}
