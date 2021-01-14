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
	@Select("SELECT id,username,password FROM t_user WHERE username = #{username}")
	User getUser(@Param("username") String username);
	
	/**
	 * 根据用户名查询对应的角色及权限
	 */
	@Select("")
	
	List<Map<String,Object>> qryRoleAndPermsByUserName(User userName);
}
