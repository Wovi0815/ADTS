package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface OtherDataDao {
	
	@Select("SELECT\r\n" + 
			"	typ_id as typId,\r\n" + 
			"	typ_name as typName,\r\n" + 
			"	typ_size as typSize,\r\n" + 
			"	typ_attr as typAttr,\r\n" + 
			"	typ_desc as typDesc\r\n" + 
			"FROM\r\n" + 
			"	`t_data_type`"
			+ "WHERE is_delete = '0' ;")
	public List<Map<String, Object>> qryDataType();

	@Insert("INSERT INTO `t_data_type` ( `typ_id`, `typ_name`, `typ_size`, `typ_attr`, `typ_desc`, `modify_user` )\r\n" + 
			"VALUES\r\n" + 
			"	(#{typId}, #{typName}, #{typSize}, #{typAttr}, #{typDesc}, 'admin');")
	public int saveBasicDataType(Map<String,String> typeData);
	
	@Update("UPDATE t_data_type set\r\n" + 
			"typ_name=#{typName},\r\n" + 
			"typ_size=#{typSize},\r\n" + 
			"typ_desc=#{typDesc}\r\n" + 
			"WHERE\r\n" + 
			"typ_id=#{typId}")
	public int updateBasicDataType(Map<String,String> typeData);
	
	@Update("UPDATE \r\n" + 
			"	t_data_type t \r\n" + 
			"SET t.is_delete = '1' " +
			"WHERE\r\n" + 
			"	t.typ_id = #{typId};")
	public int delBasicDataType(String typId);
	
	@Select("SELECT  " + 
			"	typ_id as typId,\r\n" + 
			"	typ_name as typName,\r\n" + 
			"	typ_size as typSize,\r\n" + 
			"	typ_attr as typAttr,\r\n" + 
			"	typ_desc as typDesc,\r\n" + 
			"	stru_para_count as stru_para_count,\r\n" + 
			"	create_time as create_time,\r\n" + 
			"	update_time as update_time,\r\n" + 
			"	modify_user as modify_user\r\n" + 
			"FROM\r\n" + 
			"	`t_data_type` where typ_id=#{typId} AND is_delete = '0';")
	public Map<String, Object> qryDetailDataType(String typId);
	
	@Select("SELECT\r\n" + 
			"	count( * ) as num\r\n" + 
			"FROM\r\n" + 
			"	( SELECT * FROM `t_data_type` WHERE typ_id = #{typId} ) a")
	public List<Map<String, Object>> qryTypeRepeat(String typId);
	
	@Select("SELECT\r\n" + 
			"	count( * ) AS num \r\n" + 
			"FROM\r\n" + 
			"	( SELECT * FROM `t_struct_member` WHERE mem_struct = #{struct} AND mem_id = #{memId} ) a")
	public List<Map<String, Object>> qryStructMemRep(String struct,String memId);
	
	@Insert("INSERT INTO `t_struct_member` ( `mem_id`, `mem_name`, `mem_type`, `mem_struct`, `mem_no`, `mem_desc`, `mem_phy_dim`, `mem_max`, `mem_min`, `mem_default`, `modify_user` )\r\n" + 
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
	
	@Update("UPDATE \r\n" + 
			"	t_struct_member m \r\n" + 
			"SET m.is_delete = '1' " +
			"WHERE\r\n" + 
			"	m.mem_struct = #{typId};")
	public int delStructMem(String typId);
	
	@Select("SELECT\r\n" + 
			"	tdt.typ_id AS typId,\r\n" + 
			"	tdt.typ_name AS typName,\r\n" + 
			"	tdt.typ_size AS typSize,\r\n" + 
			"	tdt.typ_desc AS typDesc,\r\n" + 
			"	tsm.mem_no AS memNo,\r\n" + 
			"	tsm.mem_id AS memId,\r\n" + 
			"	tsm.mem_name AS memName,\r\n" + 
			"	tsm.mem_type AS memType,\r\n" + 
			"	tsm.mem_phy_dim AS memPhyDim,\r\n" + 
			"	tsm.mem_max AS memMax,\r\n" + 
			"	tsm.mem_min AS memMin,\r\n" + 
			"	tsm.mem_default AS memDefault,\r\n" + 
			"	tsm.mem_desc AS memDesc \r\n" + 
			"FROM\r\n" + 
			"	t_data_type tdt\r\n" + 
			"	LEFT JOIN t_struct_member tsm ON tdt.typ_id = tsm.mem_struct \r\n" + 
			"WHERE\r\n" + 
			"	tdt.typ_id = #{typId} order by tsm.mem_no ASC;")
	public List<Map<String, Object>> qryStructMem(String typId);
	
	
	/**
	 * 根据中间件Id查找中间件
	 */
	
	@Select("SELECT " + 
	 		"mid_id,mid_name,mid_desc "+ 
	 		"FROM t_midware  " + 
	 		"WHERE	mid_id = #{midId} AND is_delete ='0' ")
	public Map<String, Object>qryMidByMidId(String midId);
	
	
	
	/**
	 *  新增中间件
	 */
	@Insert("INSERT INTO t_midware (mid_id,mid_name,mid_desc)VALUE(#{modMidId},"
			+ "#{modMidName},#{modMidDesc})")
	int InsertMid(String modMidId, String modMidName, String modMidDesc);


	/**
	 *  编辑更新中间件
	 */
	@Update("UPDATE  t_midware SET mid_name=#{modMidName},"
			+ "mid_desc=#{modMidDesc}"
			+ "WHERE t_midware.mid_id =#{modMidId}")
	int UpdateMid(String modMidId, String modMidName, String modMidDesc);
	
	
	/**
	 * 	删除中间件
	 */
	
	@Update("UPDATE t_midware m SET m.is_delete ='1' WHERE m.mid_id=#{midId}")
	int deleteMid(String midId);
	
	/**
	 * 查询所有节点下的设备
	 */
	 @Select("SELECT  * " + 
		 	"FROM t_module " )
	  List<Map<String, Object>> qryAllModule();
	
}
