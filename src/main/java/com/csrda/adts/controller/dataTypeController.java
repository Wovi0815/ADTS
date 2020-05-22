package com.csrda.adts.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csrda.adts.dao.TypeDataDao;
import com.csrda.adts.service.DataTypeServiceImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class dataTypeController {
	@Autowired
	TypeDataDao typeDataDao;

	@Autowired
	DataTypeServiceImpl dataTypeServiceImpl;
	
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
	
	@RequestMapping("/updateBasicDataType")
	@ResponseBody
	public int updateBasicDataType(String typId,String typName,String typAttr,String typSize,String typDesc) {
		Map<String, String> typeData=new HashMap<String, String>();
		typeData.put("typId", typId);
		typeData.put("typName", typName);
		typeData.put("typAttr", typAttr);
		typeData.put("typSize", typSize);
		typeData.put("typDesc", typDesc);
		return typeDataDao.updateBasicDataType(typeData);
	}
	
	@RequestMapping("/detailBasicDataType")
	@ResponseBody
	public List<Map<String,Object>> detailBasicDataType(String typId){
		return typeDataDao.detailBasicDataType(typId);
	}
	
	@RequestMapping("/delBasicDataType")
	@ResponseBody
	public int delBasicDataType(String typId) {
		return typeDataDao.delBasicDataType(typId);
	}
	
	@RequestMapping("/addStruct")
	public String addStruct() {
		return "addStruct";
	}
	
	@RequestMapping("/addStructData")
	@ResponseBody
	public String addStructData(String typId,String typName,String typSize,String typDesc,String memList){
		return dataTypeServiceImpl.addStruct(typId, typName, typSize, typDesc, memList);
	}

	@RequestMapping("/detailStructMem")
	@ResponseBody
	public List<Map<String, Object>> detailStructMem(String typId){
		return typeDataDao.qryStructMem(typId);
	}
	
	@RequestMapping("/detailStruct")
	public String detailStruct(String typId){
		return "detailStruct";
	}
	
	@RequestMapping("/editStructData")
	@ResponseBody
	public String editStructData(String typId,String typName,String typSize,String typDesc,String memList){
		return dataTypeServiceImpl.updateStruct(typId, typName, typSize, typDesc, memList);
	}

}
