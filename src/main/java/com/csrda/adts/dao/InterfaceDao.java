package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface InterfaceDao {

	
	@Select("SELECT * FROM t_midware")
	List<Map<String,Object>> qryMidware();
	
}
