package com.csrda.adts.service;


import com.csrda.adts.pojo.User;

public interface LoginService {
	

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
}
