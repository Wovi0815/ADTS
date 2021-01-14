package com.csrda.adts.shiro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.csrda.adts.pojo.User;
import com.csrda.adts.service.IndexService;


 /** 
 * @ClassName: UserRealm 
 * @Description: 自定义Realm
 * @author: wangwei
 * @date: 2020年3月15日 下午11:51:50 
 */
public class UserRealm extends AuthorizingRealm{
	@Autowired
	IndexService indexService;
	
	
	//执行授权逻辑
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("执行授权逻辑");
		//给资源进行授权
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				   
				Subject subject = SecurityUtils.getSubject();
			    //获取用户信息
			    User userName = (User) subject.getPrincipal();
		        //根据用户查询角色及其权限
			    List<Map<String, Object>> permsList = indexService.qryRoleAndPermsByUserName(userName);
			    
			   
		        //User user = us.querybyname(phone);
		        //记录用户的所有角色和权限
		     
		       // for(Role r:user.getRoles()){
		            //将所有的角色信息添加进来。
		          //  simpleAuthorizationInfo.addRole(r.getRname());
		         //   for(Permission p:r.getPermissions()){
		                //将此次遍历到的角色的所有权限拿到，添加·进来
		         //       simpleAuthorizationInfo.addStringPermission(p.getPname());
		         //   }
		     //   }
		        return info;


	}
	//执行认证逻辑
	
	
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("执行认证逻辑");
		
		//编写shiro判断逻辑，判断用户名和密码  
				//1.判断用户名
				UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
				String username = (String) token.getPrincipal();
				User user=indexService.getUser(username);
				if( user == null)
				return null;//
				
				
				//2.判断密码
				return new SimpleAuthenticationInfo(user,user.getPassWord(),this.getName());
		
		
		
	
	
	}
	

	
	
	

}
