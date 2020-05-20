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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class dataTypeController {
	@Autowired
	TypeDataDao typeDataDao;

	@Autowired
	private DataSourceTransactionManager transaction;
	
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
	@Transactional
	public String addStructData(String typId,String typName,String typSize,String typDesc,String memList){
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String, Object>> memData = mapper.readValue(memList, new TypeReference<List<Map<String, Object>>>(){});
			if(Integer.valueOf(typeDataDao.qryRep(typId).get(0).get("num").toString())>0) {
				return "TypRep";
			}
			Map<String, String> typeData=new HashMap<String, String>();
			typeData.put("typId", typId);
			typeData.put("typName", typName);
			typeData.put("typAttr", "struct");
			typeData.put("typSize", typSize);
			typeData.put("typDesc", typDesc);
			typeDataDao.saveBasicDataType(typeData);

			for (int i = 0; i < memData.size(); i++) {
				if(Integer.valueOf(typeDataDao.qryStructMemRep(typId, memData.get(i).get("memId").toString()).get(0).get("num").toString())>0) {
					return "structRep";
				}
				memData.get(i).put("memStruct", typId);
				typeDataDao.addStructMem(memData);
			}
			return "1";
		}
		catch(Exception e){
			e.printStackTrace();
			return "0";
		}


	}


}
