package com.csrda.adts.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.csrda.adts.service.MessageService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	@RequestMapping("/messageMain")
	public String messageMain() {
		return "messageMain";
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
	public Map<String, Object> qryMesDetail(String mesId,String mesTyp){
		Map<String, Object> result = messageService.qryMesDetail(mesId,mesTyp);
		return result;
	}

	//查询报文数据详情
	@ResponseBody
	@RequestMapping("/qryMesDataDetail.do")
	public List<Map<String, Object>> qryMesDataDetail(String mesId){
		List<Map<String, Object>> result = messageService.qryMesDataDetail(mesId);
		return result;
	}
	
	//修改报文
	@RequestMapping("/UpdateMes.do")
	@ResponseBody
	public int UpdateMes(String modalmesId,String modalmesName,String modalmesDesc,
			String modalmesRemark,String modalmesFunNum,String modalmesIDNum,String modalmesTyp,
			String modalmesSource,String modalmesDestination) {
		int result = messageService.UpdateMes(modalmesId, modalmesName, modalmesDesc, 
				modalmesRemark, modalmesFunNum, modalmesIDNum,
				modalmesTyp, modalmesSource, modalmesDestination);
		return result;
	};
	
	//新增报文
	@RequestMapping("/InsertMes.do")		
	@ResponseBody
	public String InsertMes(String mesMap) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();   
		List<Map<String, Object>> mMap = mapper.readValue(mesMap, new TypeReference<List<Map<String, Object>>>(){});
		String mesId=mMap.get(0).get("imesId").toString();
		String mesName=mMap.get(0).get("imesName").toString();
		String mesDesc=mMap.get(0).get("imesDesc").toString();
		String mesRemark=mMap.get(0).get("imesRemark").toString();
		String mesSource=mMap.get(0).get("imesSource").toString();
		String mesDestination=mMap.get(0).get("imesDestination").toString();
		String mesID=mMap.get(0).get("imesID").toString();
		String mesFunId=mMap.get(0).get("imesFunId").toString();
		String mesTyp=mMap.get(0).get("imesTyp").toString();
		int inresult = messageService.InsertMes(mesId, mesName, mesDesc, mesRemark,
				mesSource, mesDestination, 
				mesID, mesFunId, mesTyp);
		Map<String, Object> result = messageService.qryMesDetail(mesId,mesTyp);
		String id =result.get("mes_id").toString();
		return id;

	}
	
	//新增报文数据
	@RequestMapping("/InsertMesData.do")		
	@ResponseBody
	public int InsertMesData(String mesId,String dataRange,
			String dataName,String dataDesc,String dataType) {
		int result = messageService.
		return result;
		
	}

		
	
	
}
