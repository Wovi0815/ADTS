package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface InterfaceDao {

	
	@Select("SELECT * FROM t_midware")
	List<Map<String,Object>> qryMidware();
	
	
	
	@Select("SELECT * FROM t_class c "
			+ "RIGHT JOIN t_midware m "
			+ "ON c.c_midware = m.mid_id AND m.mid_name = #{midwareName}" )
	List<Map<String,Object>> qryMidwareClass(String midwareName);
	
	
	
	@Select("SELECT * FROM t_class c "
			+ "RIGHT JOIN t_midware m "
			+ "ON c.c_midware = m.mid_id AND m.mid_name = #{midwareName} AND c.c_father= #{cfather}" )
	List<Map<String,Object>> qryMidClsByFather(String cfather,String midwareName);
	
}
