package com.csrda.adts.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csrda.adts.service.UserService;




@Controller

public class UserController {
	@Autowired
	private UserService userService;
	
	//跳转到人员管理
	@RequestMapping("/userManager")
	public String userManager(){
		return "userManager";
	}
		
	//查询所有用户
	@RequestMapping("/QryAllUser")
	@ResponseBody
	public List<Map<String, Object>> QryAllUser() {
		List<Map<String, Object>> result = userService.qryAllUser();
		for(int i=0;i<result.size();i++) {
			String[]  r=  result.get(i).get("role_id").toString().split(",");
			String role= "";
			for(int j=0;j<r.length;j++) {
				String roleName = userService.qryRoleName(r[j]);
				if(j == r.length -1) {
					role = role+roleName;
				}else {
					role = role+roleName+",";
				}
				result.get(i).put("role_name", role);
			}	
		}
		return result;
	}	
	//查询所有角色构建下拉框
		@RequestMapping("/QryRole")
		@ResponseBody
		public List<Map<String, Object>> QryRole() {
			List<Map<String, Object>> result = userService.qryRole();
			return result;
		}	
		
	//根据角色下拉框重新查询
		@RequestMapping("/QryUserBySelect")
		@ResponseBody
		public List<Map<String, Object>> QryUserBySelect(String role) {
			List<Map<String,Object>>  roleList = new ArrayList<Map<String, Object>>() ;
			String[]  r= role.split(",");
			for(int i=0;i<r.length;i++) {
				Map<String, Object>  roleMap = new HashMap<String, Object>();
				roleMap.put("role_id", r[i]);
				roleList.add(roleMap);
			}
			List<Map<String, Object>> result = userService.qryUserBySelect(roleList);
			for(int i=0;i<result.size();i++) {
				String[]  rold_id=  result.get(i).get("role_id").toString().split(",");
				String role_name= "";
				for(int j=0;j<rold_id.length;j++) {
					System.out.println(rold_id[j]);
					String roleName = userService.qryRoleName(rold_id[j]);
					if(j == rold_id.length -1) {
						role_name = role_name+roleName;
					}else {
						role_name = role_name+roleName+",";
					}
					result.get(i).put("role_name", role_name);
				}	
			}
			return result;
		}
		
		//新增角色
		@RequestMapping("/InsertUser")
		@ResponseBody
		public String InsertUser(String username,String password,
				String phone,String role_id)  {
			return userService.insertUser(username, password,phone,role_id);
	}
		
		//根据用户名查询信息
		@RequestMapping("/QryUserDetail")
		@ResponseBody
		public Map<String, Object> QryUserDetail(String username) {
			Map<String, Object> result = userService.qryUserDetail(username);
			return result;
		}	

		//修改用户
		@RequestMapping("/UpdateUser")
		@ResponseBody
		public String UpdateUser(String eusername,String ephone,
				String erole)  {
			return userService.updateUser(eusername,ephone,erole);
		}

		//删除用户
		@RequestMapping("/DeleteUser")
		@ResponseBody
		public String DeleteUser(String username)  {
			return userService.deleteUser(username);
		}

		//重置用户密码
		@RequestMapping("/ResetPwd")
		@ResponseBody
		public int ResetPwd(String username)  {
			int result = userService.resetPwd(username);
			return result;
		}
}
		
		
		
		
		
		

	
	


