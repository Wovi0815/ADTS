package com.csrda.adts.service;

import java.util.List;
import java.util.Map;

public interface UserService {

	/**
	 * 人员管理，查询所有用户
	 */
	List<Map<String, Object>> qryAllUser();
	/**
	 * 根据角色Id查询角色名称
	 */
	String qryRoleName(String roleId);

	/**
	 * 查询所有角色构建下拉框
	 */
	List<Map<String, Object>> qryRole();
	/**
	 * 根据角色下拉框，查询用户
	 */
	List<Map<String, Object>> qryUserBySelect(List<Map<String, Object>> roleList);
	/**
	 * 新增角色
	 */
	String insertUser(String username, String password, String phone, String role_id);
	/**
	 * 根据用户名查信息,（编辑用户回填）
	 */
	Map<String, Object> qryUserDetail(String username);
	/**
	 * 编辑用户
	 */
	String updateUser(String eusername, String ephone, String erole);
	/**
	 *删除用户
	 */
	String deleteUser(String username);
	/**
	 *重置密码
	 */
	int resetPwd(String username);

	
	
}
