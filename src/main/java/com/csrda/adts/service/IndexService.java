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
	User getUser(String userName);


	/**
	 * 退出登录
	 */
	String logout();

	/**
	 * 根据用户名查询角色及权限
	 * @param userName
	 * @return
	 */
	List<Map<String, Object>> qryRoleAndPermsByUserName(User userName);



	
	
	
}
