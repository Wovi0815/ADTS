package com.csrda.adts.controller;

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
	@RequestMapping("/qryMessage")
	public List<Map<String, Object>> qryMessage(String mesType){
		List<Map<String, Object>> result = messageService.qryMessage(mesType);
		return result;
	}
	
	
	
}
