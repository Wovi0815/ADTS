package com.csrda.adts.controller;

import java.util.List;
import java.util.Map;
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

		@RequestMapping("/QueryMidwareClass.do")
		@ResponseBody
		public List<Map<String,Object>> QueryMidwareClass(String midwareName) {
			List<Map<String, Object>> result = interfaceService.qryMidwareClass(midwareName);
			System.out.println("result:~~"+result);
			return result;
		}
		
		
		
	//跳转到类下属接口页面
		@RequestMapping("/interfaceMain")
		public String toInterfaceMain() {
			return "interfaceMain";
		}
			
	
		
		
		
		
		
	
	
}
