package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.csrda.adts.pojo.User;


public interface UserDao {
	
	/**
	 * 查询所有用户
	 */
	@Select("SELECT  t.username,t.phone,ur.role_id  " + 
			"FROM t_user t  " + 
			"LEFT JOIN t_user_role ur  ON t.username = ur.username " + 
			"WHERE t.is_delete ='0'")
	List<Map<String, Object>> qryAllUser();
	/**
	 * 根据角色ID查询角色名称
	 */
	@Select("SELECT r.role_name " + 
			"FROM t_role r   " + 
			"WHERE r.is_delete ='0' AND r.role_id =#{roleId} "+
			"GROUP BY r.role_id")
	String qryRoleName(String roleId);
	
	/**
	 * 查询所有角色构建下拉框
	 */
	@Select("SELECT r.role_id,r.role_name " + 
			"FROM t_role r   " + 
			"WHERE r.is_delete ='0' "+
			"GROUP BY r.role_id")
	List<Map<String, Object>> qryRole();
	
	
	/**
	 * 筛选含有下拉框角色的用户()
	 */
	
	@Select("<script>" +
			"SELECT  t.username,t.phone,ur.role_id  " + 
			"FROM t_user t  " + 
			"LEFT JOIN t_user_role ur  ON t.username = ur.username " + 	
	 	    "WHERE t.is_delete ='0' AND "+
	 	   "<if test='roleList.size > 1'>"+
	 	   "<foreach collection='roleList' item='roleMap' index='index' separator='OR'>"+
	 	   "ur.role_id like '%${roleMap.role_id}%' "+
		   "</foreach>"+
		   "</if>"+
		   "<if test='roleList.size == 1'>"+
		   "<foreach collection='roleList' item='roleMap' index='index' separator=' '>"+
		   "ur.role_id like '%${roleMap.role_id}%' "+
		   "</foreach>"+
		   "</if>"+
		   "</script>")
	List<Map<String, Object>> qryUserBySelect(@Param("roleList") List<Map<String ,Object>> roleList);
	/**
	 * 新增用户
	 */
	@Insert("INSERT INTO t_user (username,password, phone ) " + 
			"VALUES " + 
			"(#{username}, #{password}, #{phone} );")
	
	int insertUser(String username, String password, String phone);
	/**
	 * 添加用户角色
	 */
	@Insert("INSERT INTO t_user_role (username,role_id) " + 
			"VALUES " + 
			"(#{username},#{role_id} ); ")
	
	int insertUserRole(String username, String role_id);
	
	/**
	 * 对用户进行查重
	 */
	@Select("SELECT username " + 
			"FROM  t_user " + 
			" WHERE username = #{username} ")
	String qryUsernameRepeat(String username);
	/**
	 * 根据用户名查信息,（编辑用户回填）
	 */
	@Select("SELECT  t.username,t.phone,ur.role_id  " + 
			"FROM t_user t  " + 
			"LEFT JOIN t_user_role ur  ON t.username = ur.username " + 
			" WHERE t.username = #{username} ")
	Map<String, Object> qryUserDetail(String username);
	/**
	 * 编辑用户
	 */
	@Update("UPDATE  t_user SET phone=#{ephone}"
			+ "WHERE t_user.username =#{eusername}")
	int updateUser(String eusername, String ephone);
	/**
	 * 编辑用户角色
	 */
	@Update("UPDATE  t_user_role SET role_id=#{erole}"
			+ "WHERE t_user_role.username =#{eusername}")
	int updateUserRole(String eusername, String erole);
	/**
	 *删除用户
	 */
	@Update("UPDATE  t_user SET is_delete='1'"
			+ "WHERE t_user.username =#{username}")
	int deleteUser(String username);
	/**
	 *删除用户角色
	 */
	@Update("UPDATE  t_user_role SET is_delete='1'"
			+ "WHERE t_user_role.username =#{username}")
	int deleteUserRole(String username);
	/**
	 *重置密码
	 */
	@Update("UPDATE  t_user SET password='123456'"
			+ "WHERE t_user.username =#{username}")
	int resetPwd(String username);
	
	
	
	
	
}
