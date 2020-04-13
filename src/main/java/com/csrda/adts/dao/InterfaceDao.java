package com.csrda.adts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public interface InterfaceDao {

	/**
	 * 查所有中间件
	 */
	@Select("SELECT * FROM t_midware ")
	List<Map<String,Object>> qryMidware();
	
	
	/**
	 * 查所有父类
	 */
	@Select("SELECT c_father FROM t_class GROUP BY c_father")
	List<Map<String,Object>> qryClsFather();
	
	
	/**
	 * 根据中间件查下属所有类
	 */
	@Select("SELECT c.* FROM t_class c WHERE c.c_midware=#{midwareId} AND c.is_delete='1'" )
	List<Map<String,Object>> qryMidwareClass(String midwareId);
	
	/**
	 * 查询中间件下所有父类，构建下拉框
	 */
	@Select("SELECT c.c_father FROM t_class c " + 
			"WHERE c.c_midware=#{midwareId}" + 
			"GROUP BY c.c_father")
	List<Map<String,Object>> QryMidClsFather(String midwareId);
	

	
	/**
	 * 根据中间件和父类查下属所有类
	 */
	@Select("SELECT c.* FROM t_class c " + 
			"WHERE c.c_midware =#{midwareId} AND c.c_father=#{cfather}" )
	List<Map<String,Object>> qryMidClsByFather(String cfather,String midwareId);

	/**
	 * 根据类标识查类信息
	 */
	@Select("SELECT c.c_id,c.c_name,c.c_desc,c.c_father,c.c_midware FROM t_class c "+
			"WHERE c.c_id= #{cId}" )
	Map<String,Object> qryMidClsByCId(String cId);

	/**
	 *  新增类
	 */
	@Insert("INSERT INTO t_class (c_id,c_name,c_desc,c_father,c_midware)VALUE(#{modalCId},#{modalCName},"
			+ "#{modalCDesc},#{modalCFather},#{modalCMidware})")
	int InsertCls(String modalCId, String modalCName, String modalCDesc, String modalCFather, String modalCMidware);


	/**
	 *  编辑更新类
	 */
	@Update("UPDATE  t_class SET c_name=#{modalCName},"
			+ "c_desc=#{modalCDesc},c_father=#{modalCFather},c_midware= #{modalCMidware}"
			+ "WHERE t_class.c_id =#{modalCId}")
	int UpdateCls(String modalCId, String modalCName, String modalCDesc, String modalCFather, String modalCMidware);
	
	
	/**
	 * 	删除类
	 */
	
	@Update("UPDATE t_class c SET c.is_delete ='0' WHERE c.c_id=#{cId}")
	int deleteCls(String cId);
	
	
	
	/**
	 * 查询类下属接口
	 */
	@Select("SELECT i.* FROM t_interface i WHERE i.i_class=#{classId} AND i.is_delete='1'" )
	List<Map<String,Object>> qryClsInterface(String classId);
	
	/**
	 * 查询接口的所有的参数个数构建下拉框
	 */
	@Select("SELECT i.i_para_count FROM t_interface i WHERE i.i_class=#{classId} AND i.is_delete='1'GROUP BY i.i_para_count" )
	List<Map<String,Object>> qryClsParaCount(String classId);
	
	/**
	 * 查询接口的所有的返回值类型构建下拉框
	 */
	@Select("SELECT i.i_return FROM t_interface i WHERE i.i_class=#{classId} AND i.is_delete='1'GROUP BY i.i_return" )
	List<Map<String,Object>> qryClsReturnType(String classId);
	
	
	
}