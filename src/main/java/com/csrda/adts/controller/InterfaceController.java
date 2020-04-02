package com.csrda.adts.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csrda.adts.service.InterfaceService;

@Controller
public class InterfaceController {
@Autowired
private InterfaceService interfaceService;
	
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

	//跳转到类下属接口页面
		@RequestMapping("/interfaceMain")
		public String toInterfaceMain() {
			return "interfaceMain";
		}
			
	
		
		
		
		
		
	
	
}
