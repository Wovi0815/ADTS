package com.csrda.adts.service;

import java.util.List;
import java.util.Map;

import com.csrda.adts.pojo.User;

public interface IndexService {
	

	/**
	 * 根据用户名查询对应的用户
	 *
	 * @param username 用户名
	 */
	User getUser(String username);

	/**
	 * 查询当前登录用户的权限等信息
	 */
	//List<Map<String,Object>> getInfo();

	/**
	 * 退出登录
	 */
	String logout();

	/**
	 * 查询所有用户
	 */
	List<Map<String, Object>> qryAllUser();
	
	
}
