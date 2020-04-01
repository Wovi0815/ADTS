package com.csrda.adts.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.sql.DataSource;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.WebFilter;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {

	
	@ConfigurationProperties(prefix="spring.datasource")
	@Bean
	public DataSource druid() {
		
		return new DruidDataSource();
	}
	
	@Bean
	public ServletRegistrationBean statViewServlet(){
		ServletRegistrationBean bean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		Map<String,String> initParams=new HashMap<String,String>();
		initParams.put("loginUsername", "admin");
		initParams.put("loginPassword", "admin");
		initParams.put("allow", "");
		bean.setInitParameters(initParams);
		return bean;
	}
	
	public FilterRegistrationBean webStatFilter() {
		FilterRegistrationBean bean=new FilterRegistrationBean();
		bean.setFilter(new WebStatFilter());
		Map<String,String> initParams=new HashMap<String,String>();
		initParams.put("exclusions", "*.js,*.css,*.png,*.jpg,*.json,*.scss,*.map,*.ts,*.svg,*.eot,*.ttf,*.woff,*.woff2,*.less,/druid/*");
		bean.setInitParameters(initParams);
		bean.setUrlPatterns(Arrays.asList("/*"));
		return bean;
		
	}
	
}
