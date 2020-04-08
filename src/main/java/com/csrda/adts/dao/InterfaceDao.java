package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface InterfaceDao {

	/**
	 * 查所有中间件
	 */
	@Select("SELECT * FROM t_midware")
	List<Map<String,Object>> qryMidware();
	
	
	/**
	 * 查所有父类
	 */
	@Select("SELECT c_father FROM t_class")
	List<Map<String,Object>> qryClsFather();
	
	
	
	
	/**
	 * 根据中间件查下属所有类
	 */
	@Select("SELECT c.* FROM t_class c "
			+ "LEFT JOIN t_midware m "
			+ "ON c.c_midware = m.mid_id AND m.mid_name = #{midwareName}" )
	List<Map<String,Object>> qryMidwareClass(String midwareName);
	
	
	/**
	 * 根据中间件和父类查下属所有类
	 */
	@Select("SELECT c.* FROM t_class c "
			+ "RIGHT JOIN t_midware m "
			+ "ON c.c_midware = m.mid_id AND m.mid_name = #{midwareName} AND c.c_father= #{cfather}" )
	List<Map<String,Object>> qryMidClsByFather(String cfather,String midwareName);
	
	
	
	/**
	 * 根据中间件和类标识查下属所有类
	 */
	@Select("SELECT c.c_id,c.c_name,c.c_desc,c.c_father,c.c_midware FROM t_class c "
			+ "LEFT JOIN t_midware m "
			+ "ON c.c_midware = m.mid_id AND m.mid_name = #{midwareName} AND c.c_id= #{cId}" )
	Map<String,Object> qryMidClsByCId(String cId,String midwareName);
	
}
