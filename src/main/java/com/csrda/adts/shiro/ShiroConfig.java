package com.csrda.adts.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ShiroConfig {

	/**
	 * 创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		System.out.println("————————————执行Shiro————————————");
		//添加Shiro内置过滤器
		/**
		 * Shiro内置过滤器，可以实现权限相关的拦截器
		 *    常用的过滤器：
		 *       anon: 无需认证（登录）可以访问
		 *       authc: 必须认证才可以访问
		 *       user: 如果使用rememberMe的功能可以直接访问
		 *       perms： 该资源必须得到资源权限才可以访问
		 *       role: 该资源必须得到角色权限才可以访问
		 */ 
		Map<String,String> filterMap = new LinkedHashMap<String,String>();
		
		filterMap.put("/login", "anon");
		filterMap.put("/druid", "anon");
		filterMap.put("/druid/**", "anon");
		//资源拦截
		//filterMap.put("/add", "perms[add]");
		//filterMap.put("/update", "perms[update]");
		
		
		
		
		
		
		//filterMap.put("/InsertInterfaceParas.do", "anon");
	//	filterMap.put("/QryInterfaceBySelect.do", "anon");
		//filterMap.put("/QryInterfaceDetail.do", "anon");
		//filterMap.put("/QryClsReturnType.do", "anon");
		//filterMap.put("/QryClsParaCount.do", "anon");
		//filterMap.put("/QryClsInterface.do", "anon");
		
		//filterMap.put("/DeleteCls.do", "anon");
		//filterMap.put("/InsertCls.do", "anon");
		//filterMap.put("/QryClsBeFather.do", "anon");
		//filterMap.put("/QryMidClsByCId.do", "anon");
		//filterMap.put("/QryMidClsByFather.do", "anon");
		//filterMap.put("/QueryMidwareClass.do", "anon");
		//filterMap.put("/classMain", "anon");
		//filterMap.put("/QueryMidware.do", "anon");
		
		filterMap.put("/*", "authc");
		
	
		
		//修改调整的登录页面
		shiroFilterFactoryBean.setLoginUrl("/tologin");
		//设置未授权页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

		return shiroFilterFactoryBean;
	}
	
	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//关联realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	
	/**
	 * 创建Realm
	 */
	@Bean(name="userRealm")
	public UserRealm getRealm(){
		return new UserRealm();
	}
	

	
}
