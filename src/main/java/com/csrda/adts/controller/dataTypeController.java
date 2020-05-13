package com.csrda.adts.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csrda.adts.dao.TypeDataDao;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class dataTypeController {
	@Autowired
	TypeDataDao typeDataDao;

	@RequestMapping("/dataTypeManager")
	public String dataTypeManager() {
		return "dataTypeManager";
	}
	
	@RequestMapping("/qryDataType")
	@ResponseBody
	public List<Map<String, Object>> qryDataType(){
		return typeDataDao.qryTypeData();
	}
	
	@RequestMapping("/saveBasicDataType")
	@ResponseBody
	public int saveBasicDataType(String typId,String typName,String typAttr,String typSize,String typDesc) {
		Map<String, String> typeData=new HashMap<String, String>();
		typeData.put("typId", typId);
		typeData.put("typName", typName);
		typeData.put("typAttr", typAttr);
		typeData.put("typSize", typSize);
		typeData.put("typDesc", typDesc);
		return typeDataDao.saveBasicDataType(typeData);
	}
	
	@RequestMapping("/addStruct")
	public String addStruct() {
		
		return "addStruct";
	}
	
	@RequestMapping("/addStructData")
	@ResponseBody
	public String addStructData(String typId,String typName,String typSize,String typDesc,String memList) throws Exception, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> memData = mapper.readValue(memList, new TypeReference<List<Map<String, Object>>>(){});
		System.out.println(memList.toString());
		System.out.println(typId);
		System.out.println(typName);
		System.out.println(typSize);
		System.out.println(typDesc);
		
		return "addStruct";
	}
	
	
}
