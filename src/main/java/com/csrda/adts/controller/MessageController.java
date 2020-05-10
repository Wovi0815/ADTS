package com.csrda.adts.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.csrda.adts.service.MessageService;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;
	//查报文类型构建菜单
	@ResponseBody
	@RequestMapping("/QueryMessageTyp.do")
	public List<Map<String, Object>> QueryMessageTyp(){
		List<Map<String, Object>> result = messageService.QueryMessageTyp();
		return result;
	}
	
	//跳转
	@RequestMapping("/messageManager")
	public String messageManager() {
		return "messageManager";
	}
	//根据报文类型查询所有报文
	@ResponseBody
	@RequestMapping("/qryMessage.do")
	public List<Map<String, Object>> qryMessage(String mesType){
		List<Map<String, Object>> result = messageService.qryMessage(mesType);
		return result;
	}
	
	// 查询硬件模块 ,构建 信源信宿的下拉框
	@ResponseBody
	@RequestMapping("/qryModSelect.do")
	public List<Map<String, Object>> qryModSelect(){
		List<Map<String, Object>> result = messageService.qryModuleKind();
	return result;
	}
	
	
	//根据下拉框刷新表格
	@ResponseBody
	@RequestMapping("/qryMesBySelect.do")
	public List<Map<String, Object>> qryMesBySelect(String mesType,String source,String destination){
		List<Map<String, Object>> result = new ArrayList();
		System.out.println("yuan:"+source);
		System.out.println("xiu:"+destination);
		if((destination=="")&& (source!="")) {
			System.out.println("根据源");
			 result = messageService.qryMesByDtSource(source, mesType);
		}else if((source=="")&&(destination!="")){
			System.out.println("根据宿");
			 result = messageService.qryMesByDtDestination(destination, mesType);
		}else if((source=="")&&(destination=="")){
			System.out.println("所有");
			 result = messageService.qryMessage(mesType);
		}else if((source!="")&&(destination!="")) {
			System.out.println("两个都有");
			result =messageService.qryMesBySelect(source, destination, mesType);
		}
		return result;
		
	}
	
	
}
