package com.csrda.adts.service;


import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csrda.adts.dao.IndexDao;
import com.csrda.adts.dao.UserDao;
import com.csrda.adts.pojo.User;


@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	private IndexDao indexDao;




	/**
	 * 根据用户名查询对应的用户
	 */
	@Override
	public  User getUser(String userName) {
		return indexDao.getUser(userName);
	}


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

	/**
	 * 根据用户名查询角色及权限
	 * @param userName
	 * @return
	 */
	@Override
	public List<Map<String, Object>> qryRoleAndPermsByUserName(User userName) {
		
		return null;
	}

	
	
}
