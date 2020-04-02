package com.csrda.adts.shiro;
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
import com.csrda.adts.service.LoginService;

 /** 
 * @ClassName: UserRealm 
 * @Description: 自定义Realm
 * @author: wangwei
 * @date: 2020年3月15日 下午11:51:50 
 */
public class UserRealm extends AuthorizingRealm{
	@Autowired
	LoginService loginService;
	
	
	//执行授权逻辑
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("执行授权逻辑");
		//给资源进行授权
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				//info.addStringPermission("0");
				
				//到数据库查询当前登录用户的授权字符串
				//获取当前登录用户
				Subject subject = SecurityUtils.getSubject();
				//User user = (User)subject.getPrincipal();//认证对象的传递
				//User dbUser = userService.findByUserName(user.getUserName());
				
				//在此进行简化设计,未来需将角色、权限单独建表
				//if("0".equals(dbUser.getRole())) {
				//	System.out.println("0000,管理员");
				//	info.addStringPermission("add");
				//}else if("1".equals(dbUser.getRole())) {
				//	System.out.println("1111,普通");
				//	info.addStringPermission("update");
				//}
				
				return null;

	}
	//执行认证逻辑
	
	
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("执行认证逻辑");
		
		//编写shiro判断逻辑，判断用户名和密码  
				//1.判断用户名
				UsernamePasswordToken token = (UsernamePasswordToken) arg0;
				String username = (String) token.getPrincipal();
				User user=loginService.getUser(username);
				if( user == null)
				return null;//
				
				
				//2.判断密码
				return new SimpleAuthenticationInfo(user,user.getPassWord(),"");
		
		
		
	
	
	}
	

	
	
	

}
