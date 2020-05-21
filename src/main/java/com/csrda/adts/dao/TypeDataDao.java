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
	public int saveBasicDataType(Map<String,String> typeData);
	
	public int updateBasicDataType(Map<String,String> typeData);
	
	@Select("SELECT\r\n" + 
			"	count( * ) as num\r\n" + 
			"FROM\r\n" + 
			"	( SELECT * FROM `t_data_type` WHERE typ_id = #{typId} ) a")
	public List<Map<String, Object>> qryRep(String typId);
	
	@Select("SELECT\r\n" + 
			"	count( * ) AS num \r\n" + 
			"FROM\r\n" + 
			"	( SELECT * FROM `t_struct_member` WHERE mem_struct = #{struct} AND mem_id = #{memId} ) a")
	public List<Map<String, Object>> qryStructMemRep(String struct,String memId);
	
	@Insert("INSERT INTO `csrda_adts_test`.`t_struct_member` ( `mem_id`, `mem_name`, `mem_type`, `mem_struct`, `mem_no`, `mem_desc`, `mem_phy_dim`, `mem_max`, `mem_min`, `mem_default`, `modify_user` )\r\n" + 
			"VALUES\r\n" + 
			"	(\r\n" + 
			"		#{memId},\r\n" + 
			"		#{memName},\r\n" + 
			"		#{memType},\r\n" + 
			"		#{memStruct},\r\n" + 
			"		#{memNo},\r\n" + 
			"		#{memDesc},\r\n" + 
			"		#{memPhyDim},\r\n" + 
			"		#{memMax},\r\n" + 
			"		#{memMin},\r\n" + 
			"		#{memDefault},\r\n" + 
			"	'admin' \r\n" + 
			"	);")
	public int addStructMem(Map<String, Object> memData);
	
	public int delStructMem(String typId);
	
	public List<Map<String, Object>> qryStructMem(String typId);
}
