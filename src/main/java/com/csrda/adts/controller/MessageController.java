package com.csrda.adts.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

		List<Map<String,Object>>  destinationList = new ArrayList<Map<String, Object>>() ;
		List<Map<String,Object>>  sourceList = new ArrayList<Map<String, Object>>() ;
		String[]  d= destination.split(",");
		String[]  s= source.split(",");
		for(int i=0;i<d.length;i++) {
			Map<String, Object>  destinationMap = new HashMap<String, Object>();
			destinationMap.put("destination", d[i]);
			destinationList.add(destinationMap);
		}
		for(int i=0;i<s.length;i++) {
			System.out.println("源"+s[i]);
			Map<String, Object>  sourceMap = new HashMap<String, Object>();
			sourceMap.put("source",s[i]);
			sourceList.add(sourceMap);
		}

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if((destination=="")&& (source!="")) {
			result = messageService.qryMesByDtSource(sourceList, mesType);
		}else if((source=="")&&(destination!="")){
			result = messageService.qryMesByDtDestination(destinationList, mesType);
		}else if((source=="")&&(destination=="")){
			result = messageService.qryMessage(mesType);
		}else if((source!="")&&(destination!="")) {
			result =messageService.qryMesBySelect(sourceList, destinationList, mesType);
		}
		return result;
		   
	}
	
	
	// 查询报文的进一步信息
	@ResponseBody
	@RequestMapping("/qryMesDetail.do")
	public Map<String, Object> qryMesDetail(String mesId){
		Map<String, Object> result = messageService.qryMesDetail(mesId);
		return result;
	}

	//查询报文数据详情
	//根据报文类型查询所有报文
	@ResponseBody
	@RequestMapping("/qryMesDataDetail.do")
	public List<Map<String, Object>> qryMesDataDetail(String mesId){
		List<Map<String, Object>> result = messageService.qryMesDataDetail(mesId);
		return result;
	}

}
