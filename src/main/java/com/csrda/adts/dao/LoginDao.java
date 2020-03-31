package com.csrda.adts.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.csrda.adts.pojo.User;


public interface LoginDao {
	/**
	 * 根据用户名和密码查询对应的用户
	 */
	@Select("SELECT id,username,password,role_id FROM t_user WHERE username = #{username}")
	User getUser(@Param("username") String username);
}
