package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface WordExportDao {
	@Select("SELECT" + 
			"   typ_id AS typeId,"+
			"	typ_name AS typeName," + 
			"	typ_size AS typeSize," + 
			"	typ_desc AS typeDesc" + 
			" FROM" + 
			"	`t_data_type`")
	public List<Map<String,Object>> qryDataType();
	
	@Select("SELECT" + 
			"   typ_id AS typeId,"+
			"	typ_name AS typeName," + 
			"	typ_size AS typeSize," + 
			"	typ_desc AS typeDesc" + 
			" FROM" + 
			"	`t_data_type` where typ_attr='struct';")
	public List<Map<String,Object>> qryStruct();

	@Select("SELECT\r\n" + 
			"	t.mem_id,\r\n" + 
			"	t.mem_name,\r\n" + 
			"	t.mem_type,\r\n" + 
			"	t.mem_desc,\r\n" + 
			"	IFNULL( t.mem_phy_dim, \" \" ) AS mem_phy_dim,\r\n" + 
			"	IFNULL( t.mem_max, \" \" ) AS mem_max,\r\n" + 
			"	IFNULL( t.mem_min, \" \" ) AS mem_min,\r\n" + 
			"	IFNULL( t.mem_default, \" \" ) AS mem_default \r\n" + 
			"FROM\r\n" + 
			"	t_struct_member t \r\n" + 
			"WHERE\r\n" + 
			"	t.mem_struct = \"student\" \r\n" + 
			"ORDER BY\r\n" + 
			"	t.mem_no;")
	public List<Map<String,Object>> qryStructMem(String typeId);
	
	@Select("SELECT * from t_midware")
	public List<Map<String,Object>> qryMidWare();
	
	@Select("SELECT " + 
			"c.c_id," + 
			"c.c_name," + 
			"c.c_desc," + 
			"IFNULL(c.c_father,\" \") as c_father," + 
			"IFNULL(c.c_midware,\" \") as c_midware " + 
			"FROM	" + 
			"t_class c " + 
			"WHERE	c_midware =#{midWareId};")
	public List<Map<String,Object>> qryObject(String midWareId);
	
	@Select("SELECT * FROM t_interface WHERE i_class=#{objectId};")
	public List<Map<String,Object>> qryMethod(String objectId);
	
	@Select("SELECT\r\n" + 
			"	t.para_id,\r\n" + 
			"	t.para_name,\r\n" + 
			"	t.para_type,\r\n" + 
			"	t.para_desc,\r\n" + 
			"	IFNULL(t.para_phy_dim,\" \") as para_phy_dim,\r\n" + 
			"	IFNULL(t.para_max,\" \") as para_max,\r\n" + 
			"	IFNULL(t.para_min,\" \") as para_min,\r\n" + 
			"	IFNULL(t.para_default,\" \") as para_default\r\n" + 
			"FROM\r\n" + 
			"	`t_parameter` t \r\n" + 
			"WHERE\r\n" + 
			"	t.para_attr = \"输入\" \r\n" + 
			"	AND t.para_interface = #{interfaceId} \r\n" + 
			"ORDER BY\r\n" + 
			"	t.para_eq;")
	public List<Map<String,Object>> qryInputParam(String interfaceId);
	
	@Select("SELECT\r\n" + 
			"	t.para_id,\r\n" + 
			"	t.para_name,\r\n" + 
			"	t.para_type,\r\n" + 
			"	t.para_desc,\r\n" + 
			"	IFNULL(t.para_phy_dim,\" \") as para_phy_dim,\r\n" + 
			"	IFNULL(t.para_max,\" \") as para_max,\r\n" + 
			"	IFNULL(t.para_min,\" \") as para_min,\r\n" + 
			"	IFNULL(t.para_default,\" \") as para_default\r\n" + 
			"FROM\r\n" + 
			"	`t_parameter` t \r\n" + 
			"WHERE\r\n" + 
			"	t.para_attr = \"输出\" \r\n" + 
			"	AND t.para_interface = #{interfaceId} \r\n" + 
			"ORDER BY\r\n" + 
			"	t.para_eq;")
	public List<Map<String,Object>> qryOutputParam(String interfaceId);
}
