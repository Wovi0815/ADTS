package com.csrda.adts.service;


import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csrda.adts.dao.IndexDao;
import com.csrda.adts.pojo.User;


@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	private IndexDao indexDao;
	//@Autowired
	//private PermissionService permissionService;



	/**
	 * 根据用户名查询对应的用户
	 */
	@Override
	public  User getUser(String username) {
		return indexDao.getUser(username);
	}

	/**
	 * 查询当前登录用户的权限等信息
	 */
//	@Override
//	public List<Map<String,Object>> getInfo() {
//		//从session获取用户信息
//		Session session = SecurityUtils.getSubject().getSession();
//		String username = (String) session.getAttribute("username");
//		List<Map<String,Object>> userPermission = permissionService.getUserPermission(username);
//		session.setAttribute("userPermission", userPermission);
//		
//		return userPermission;
//	}

	/**
	 * 退出登录
	 */
	@Override
	public String logout() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		} catch (Exception e) {
		}
		return "success";
	}

	
	
}
