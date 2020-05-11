package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface TypeDataDao {
	
	@Select("SELECT\r\n" + 
			"	typ_id as typId,\r\n" + 
			"	typ_name as typName,\r\n" + 
			"	typ_size as typSize,\r\n" + 
			"	typ_attr as typAttr,\r\n" + 
			"	typ_desc as typDesc\r\n" + 
			"FROM\r\n" + 
			"	`t_data_type`;")
	public List<Map<String, Object>> qryTypeData();

	@Insert("INSERT INTO `csrda_adts_test`.`t_data_type` ( `typ_id`, `typ_name`, `typ_size`, `typ_attr`, `typ_desc`, `modify_user` )\r\n" + 
			"VALUES\r\n" + 
			"	(#{typId}, #{typName}, #{typSize}, #{typAttr}, #{typDesc}, 'admin');")
	//@SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
	public int saveBasicDataType(Map<String,String> typeData);
}
