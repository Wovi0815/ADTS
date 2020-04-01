package com.csrda.adts.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class IndexController {

	//登录逻辑
	
	@RequestMapping("/login")
	public String login(String userName ,String passWord,Model model) {
		
		
		//shiro 认证操作
		//1.获取subject
		Subject subject = SecurityUtils.getSubject();
		//2.封装用户数据
		UsernamePasswordToken token = new UsernamePasswordToken(userName,passWord);
		
		//3.执行登录方法
		try {
			subject.login(token);
			//登录成功
			return "index";
		} catch (UnknownAccountException e) {
			//e.printStackTrace();
			//登录失败:用户名不存在
			model.addAttribute("msg", "用户名不存在");
			return "login";
		}catch (IncorrectCredentialsException e) {
			//e.printStackTrace();
			//登录失败:密码错误
			model.addAttribute("msg", "密码错误");
			return "login";
		}
	}
	
	//返回登录页
	@RequestMapping("/tologin")
	public String toLogin(){
		return "/login";
	}
	//未授权页
	@RequestMapping("/unAuth")
	public String unAuth() {
		return "unAuth";
	}
	
	


}
		
		
		
		
		
		

	
	


