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
	@RequestMapping("/QueryMessageTyp")
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
	@RequestMapping("/qryMessage")
	public List<Map<String, Object>> qryMessage(String mesType){
		List<Map<String, Object>> result = messageService.qryMessage(mesType);
		return result;
	}
	
	// 查询硬件模块 ,构建 信源信宿的下拉框
	@ResponseBody
	@RequestMapping("/qryModSelect")
	public List<Map<String, Object>> qryModSelect(){
		List<Map<String, Object>> result = messageService.qryModuleKind();
	return result;
	}
	
	
	//根据下拉框刷新表格
	@ResponseBody
	@RequestMapping("/qryMesBySelect")
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
	@RequestMapping("/qryMesDetail")
	public Map<String, Object> qryMesDetail(String mesId,String mesTyp){
		Map<String, Object> result = messageService.qryMesDetail(mesId,mesTyp);
		return result;
	}

	//查询报文数据详情
	@ResponseBody
	@RequestMapping("/qryMesDataDetail")
	public List<Map<String, Object>> qryMesDataDetail(String mesId){
		List<Map<String, Object>> result = messageService.qryMesDataDetail(mesId);
		return result;
	}
	
	//修改报文
	@RequestMapping("/UpdateMes")
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
	@RequestMapping("/InsertMes")		
	@ResponseBody
	public String addMesAndData(String mesMap,String mesDataMap)  {
		return messageService.addMesAndData(mesMap, mesDataMap);
	}
	
	
	//删除接口
	@RequestMapping("/deleteMes")		
	@ResponseBody
	int deleteInterface(String mesId,String mesTyp){
		int delresult = messageService.deleteMes(mesId,mesTyp);
		return delresult;	
	}


	//删除接口参数
	@RequestMapping("/deleteMesData")		
	@ResponseBody
	int deleteInterfacePara(String mesId){
		int result = messageService.deleteMesData(mesId);
		return result;		
	}

	//跳转到报文的数据页面
	@RequestMapping("/dataForMes")
	public String toMesData() {
		return "dataForMes";
	}
	
	//报文数据回填
	@ResponseBody
	@RequestMapping("/QryDataFillback")
	public Map<String, Object> QryDataFillback(String dataName,String mesId){
		Map<String, Object> result = messageService.QryDataFillback(dataName,mesId);
		return result;
	}
	
	//新增之前查询报文数据是否重复
	@ResponseBody
	@RequestMapping("/QryDataIsExist")
	public String QryDataIsExist(String dataMap)throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();   
		List<Map<String, Object>> map = mapper.readValue(dataMap, new TypeReference<List<Map<String, Object>>>(){});
		String clickKey =map.get(0).get("clickKey").toString();
		String  mesId = map.get(0).get("mesId").toString();
		String  dataStart = map.get(0).get("dataStart").toString();
		String  dataLong = map.get(0).get("dataLong").toString();
		String  dataName = map.get(0).get("dataName").toString();
		List<Map<String, Object>> result = null ;
		if(clickKey.equals("add")) {//新增
			 result = messageService.qryMesDataDetail(mesId);
		}else if(clickKey.equals("edit")) {//编辑
			 result = messageService.qryUpdateMesdataIsExist(mesId,dataName);
		}
		for(int i=0;i<result.size();i++) {
			String dataRange = result.get(i).get("mes_data_range").toString();
			String dataname = result.get(i).get("mes_data_name").toString();
			String[]  list= dataRange.split("~");
			if(list.length ==1) {
				int a =Integer.valueOf(dataStart);
				int b =Integer.valueOf(list[0]);
				int c =Integer.valueOf(dataLong) + a - 1;	
				for(int j=a;j<=c;j++) {
					if(j==b) {
						return "dataRangeExist";
					}
				}	
			}else if(list.length ==2) {
				int start = Integer.valueOf(list[0]);
				int end = Integer.valueOf(list[1]);
				int a =Integer.valueOf(dataStart);
				int b =Integer.valueOf(dataLong) + a - 1;
					for(int j=a;j<=b;j++) {
						if(j>=start && j<=end) {
							return "dataRangeExist";
						}
				}	
			}

			if(dataName==dataname) {
				return "dataNameExist";
			}
			
		}
		return "SUCCESS";
	}
		
	
	
	//数据页面新增数据
	@RequestMapping("/InsertMesData")		
	@ResponseBody
	public int InsertMesData(String dataMap) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();   
		int result =0;
		List<Map<String, Object>> map = mapper.readValue(dataMap, new TypeReference<List<Map<String, Object>>>(){});
			String  dataRange = map.get(0).get("dataRange").toString();
			String  dataLong = map.get(0).get("dataLong").toString();
			String  dataName = map.get(0).get("dataName").toString();
			String  dataDesc = map.get(0).get("dataDesc").toString();
			String  dataTyp = map.get(0).get("dataTyp").toString();
			String  mesId = map.get(0).get("mesId").toString();
		    result = messageService.InsertMesData(mesId, dataRange, dataLong, dataName, dataDesc, dataTyp);
		    return result;	
		}

	
	//数据页面新增数据
	@RequestMapping("/UpdateMesData")		
	@ResponseBody
	public int UpdateMesData(String dataMap) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();   
		int result =0;
		List<Map<String, Object>> map = mapper.readValue(dataMap, new TypeReference<List<Map<String, Object>>>(){});
		String  dataRange = map.get(0).get("dataRange").toString();
		String  dataLong = map.get(0).get("dataLong").toString();
		String  dataName = map.get(0).get("dataName").toString();
		String  dataDesc = map.get(0).get("dataDesc").toString();
		String  dataTyp = map.get(0).get("dataTyp").toString();
		String  mesId = map.get(0).get("mesId").toString();
		result = messageService.UpdateMesData(mesId, dataRange, dataLong, dataName, dataDesc, dataTyp);
		return result;	
		}	
	
	
	//单个删除接口参数
	@RequestMapping("/deleteOneData")
	@ResponseBody
	  int deleteOnePara(String dataName,String mesId){
			int result = messageService.deleteOneData(mesId,dataName);
	  return result;		
	}
			
	
	
	//单独报文页面 查所有报文
	
	@ResponseBody
	@RequestMapping("/qryAllMessage")
	public List<Map<String, Object>> qryAllMessage(){
		List<Map<String, Object>> result = messageService.qryAllMessage();
		return result;
	}
	
	
}
