package com.csrda.adts.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.csrda.adts.service.InterfaceService;


@Controller
public class InterfaceController {
@Autowired
private InterfaceService interfaceService;
Logger logger= LoggerFactory.getLogger(this.getClass());
	//查询中间件
	@RequestMapping("/QueryMidware.do")
	@ResponseBody
	public List<Map<String,Object>> QueryMidware() {
		List<Map<String, Object>> result = interfaceService.qryMidware();
		return result;
	}

	//跳转到中间件所属类页面
		@RequestMapping("/classMain")
			public String toClassMain() {		
			return "classMain";
		}
		
	//根据中间件查询下属类
		@RequestMapping("/QueryMidwareClass.do")
		@ResponseBody
		public List<Map<String,Object>> QueryMidwareClass(String midwareName) {		
			List<Map<String, Object>> result = interfaceService.qryMidwareClass(midwareName);
			return result;
		}

		
	//查询中间件下所有父类，下拉框
		@RequestMapping("/QryMidClsFather.do")
		@ResponseBody
		public List<Map<String,Object>> QueryMidClsFather(String midwareName) {
			List<Map<String, Object>> result = interfaceService.qryMidClsFather(midwareName);
			return result;
		} 

	//根据下拉框所选择父类进行筛选
		@RequestMapping("/QryMidClsByFather.do")
		@ResponseBody
		public List<Map<String,Object>> QueryMidClsByFather(String cfather,String midwareName) {
			System.out.println("cfather:"+cfather);
			List<Map<String, Object>> result = interfaceService.qryMidClsByFather(cfather, midwareName);
			System.out.println("result!!@@@@!!!"+result);
			return result;
		}
		
		
	//根据中间件、类标识查询类信息
		@RequestMapping("/QryMidClsByCId.do")
		@ResponseBody
		public Map<String,Object> QueryMidClsByCId(String cId,String midwareName) {
			Map<String, Object>result = interfaceService.qryMidClsByCId(cId, midwareName);
			return result;
		};

	//查询所有父类、
		@RequestMapping("/QryClsFather.do")
		@ResponseBody
		public List<Map<String,Object>> QueryClsFather() {
			List<Map<String, Object>> result = interfaceService.qryClsFather();
			return result;
		};

		
		
	//跳转到类下属接口页面
		@RequestMapping("/interfaceMain")
		public String toInterfaceMain() {
			return "interfaceMain";
		}
			
	
		
		
		
		
		
	
	
}
