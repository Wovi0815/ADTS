package com.csrda.adts.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
	
	//接口详情
		@RequestMapping("/interfaceMain")
		public String functionManager(Model model) {
			model.addAttribute("midwire", "voice");
			return "interfaceMain";
		}
			
	
		
		
		
		
		
	
	
}
