package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface MessageDao {
	
	 @Select("SELECT\r\n" + 
	 		"  id,\r\n" + 
	 		"	mes_id as mesId,\r\n" + 
	 		"	mes_name as mesName,\r\n" + 
	 		"	mes_desc as mesDesc,\r\n" + 
	 		"	mes_source as mesSource,\r\n" + 
	 		"	mes_destination as mesDestination,\r\n" + 
	 		"	mes_id_num as mesIdNum,\r\n" + 
	 		"	mes_fun_id as mesFunId,\r\n" + 
	 		"	mes_type as mesType\r\n" + 
	 		"FROM\r\n" + 
	 		"	t_message \r\n" + 
	 		"WHERE\r\n" + 
	 		"	mes_type = #{mesType}")
	 public List<Map<String, Object>> qryMessage(String mesType);
	 
	 
	 
}
