package com.csrda.adts.service;


import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.csrda.adts.dao.IndexDao;
import com.csrda.adts.dao.MessageDao;
import com.csrda.adts.dao.UserDao;
import com.csrda.adts.pojo.User;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public List<Map<String, Object>> qryAllUser() {
		
		return userDao.qryAllUser();
	}

	@Override
	public String qryRoleName(String roleId) {
		return userDao.qryRoleName(roleId);
	}

	@Override
	public List<Map<String, Object>> qryRole() {
		return userDao.qryRole();
	}

	@Override
	public List<Map<String, Object>> qryUserBySelect( List<Map<String, Object>> roleList) {
		return userDao.qryUserBySelect(roleList);
	}

	@Override
	@Transactional
	public String insertUser(String username, String password, String phone, String role_id) {
		try {
			if(userDao.qryUsernameRepeat(username)==username) {
				return "Exist";
			}else if(userDao.insertUser(username,password,phone)==1){
				userDao.insertUserRole(username,role_id);
			}		
		}catch(Exception e){
		e.printStackTrace();
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return "Fault";
	}
		return "SUCCESS";
}

	@Override
	public Map<String, Object> qryUserDetail(String username) {
		
		return userDao.qryUserDetail(username);
	}
	@Override
	public String updateUser(String eusername, String ephone,String erole) {
		if(userDao.updateUser(eusername,ephone)==0 && 
		 userDao.updateUserRole(eusername,erole)==0){
			return "Fault";
		}
	    	 return "SUCCESS" ; 
	}

	@Override
	@Transactional
	public String deleteUser(String username) {
		try {
			 if(userDao.deleteUser(username)!=1	||
				userDao.deleteUserRole(username)!=1){
				 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				 return "Fault" ;
				 }
			}catch(Exception e){
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return "Fault";
			}
		    	 return "SUCCESS" ; 
	}

	@Override
	public int resetPwd(String username) {
		
		return userDao.resetPwd(username);
	}

	
}
