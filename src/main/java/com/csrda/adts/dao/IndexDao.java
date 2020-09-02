package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.csrda.adts.pojo.User;


public interface IndexDao {
	/**
	 * 根据用户名和密码查询对应的用户
	 */
	@Select("SELECT id,username,password,role_id FROM t_user WHERE username = #{username}")
	User getUser(@Param("username") String username);
	
	
	/**
	 * 查询所有用户
	 */
	@Select("SELECT  t.username,t.phone,r.role_name " + 
			"FROM t_user t " + 
			"LEFT JOIN t_role r ON r.role_id = t.role_id " + 
			"WHERE t.is_delete ='0'")
	List<Map<String, Object>> qryAllUser();
}
