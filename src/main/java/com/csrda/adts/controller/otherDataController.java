package com.csrda.adts.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.csrda.adts.service.OtherDataService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
public class otherDataController {


	@Autowired
	OtherDataService otherDataService;
	
	@RequestMapping("/dataTypeManager")
	public String dataTypeManager() {
		return "dataTypeManager";
	}
	/**
	 * 查数据类型
	 */
	@RequestMapping("/QryDataType")
	@ResponseBody
	public List<Map<String, Object>> qryDataType(){
		return otherDataService.qryDataType();
	}
	/**
	 * 保存数据类型
	 */
	@RequestMapping("/InsertDataType")
	@ResponseBody
	public int saveDataType(String typId,String typName,String typAttr,String typSize,String typDesc) {
		if(Integer.valueOf(otherDataService.qryTypeRepeat(typId).get(0).get("num").toString())>0) {
			return 0;
		}
		Map<String, String> typeData=new HashMap<String, String>();
		typeData.put("typId", typId);
		typeData.put("typName", typName);
		typeData.put("typAttr", typAttr);
		typeData.put("typSize", typSize);
		typeData.put("typDesc", typDesc);
		return otherDataService.insertDataType(typeData);
	}
	/**
	 * 修改数据类型
	 */
	@RequestMapping("/UpdateDataType")
	@ResponseBody
	public int updateDataType(String typId,String typName,String typAttr,String typSize,String typDesc) {
		Map<String, String> typeData=new HashMap<String, String>();
		typeData.put("typId", typId);
		typeData.put("typName", typName);
		typeData.put("typAttr", typAttr);
		typeData.put("typSize", typSize);
		typeData.put("typDesc", typDesc);
		return otherDataService.updateDataType(typeData);
	}
	
	/**
	 * 保存结构体数据类型系列操作
	 */
	
	@RequestMapping("/AddStruct")
	@ResponseBody
	public String addStruct(String structId,String structName,String structSize,String structDesc,String structMemCount,String memList){
		return otherDataService.addStruct(structId, structName, structSize, structDesc,structMemCount, memList);
	}
	

	
	/**
	 * 根据typId 查信息，可用于回填模态框
	 */
	@RequestMapping("/QryDetailDataType")
	@ResponseBody
	public Map<String,Object> qryDetailBasicDataType(String typId){
		return otherDataService.qryDetailDataType(typId);
	}
	/**
	 * 删除数据类型
	 */

	@RequestMapping("/DelDataType")
	@ResponseBody
	public int delDataType(String typId) {
		return otherDataService.delDataType(typId);
	}
	
	/**
	 * 删除结构体所有成员
	 */
	@RequestMapping("/DelStructMem")
	@ResponseBody
	public int delStructMem(String structId){
		return otherDataService.delStructMem(structId);
	}
	
	/**
	 * 跳转到结构体成员页面
	 */
	@RequestMapping("/memberForStruct")
	public String toMember() {		
		return "memberForStruct";
	}
	
	/**
	 * 查结构体的所有成员
	 */
	@RequestMapping("/QryStructMem")
	@ResponseBody
	public List<Map<String, Object>> qryStructMem(String structId){
		return otherDataService.qryStructMem(structId);
	}
	
	
	/**
	 * 查结构体的成员详情
	 */
	@RequestMapping("/QryMemDetail")
	@ResponseBody
	public Map<String, Object> qryMemDetail(String structId,String memId){
		return otherDataService.qryMemDetail(structId,memId);
	}
	
	/**
	 * 编辑结构体的成员，回填
	 */
	@RequestMapping("/QryMemFillback")
	@ResponseBody
	public Map<String, Object> qryMemFillback(String structId,String memNo){
		return otherDataService.qryMemFillback(structId,memNo);
	}
	
	/**
	 * 改变参数之前判断是否唯一
	 */

	@RequestMapping("/QryMemIsExist")		
	@ResponseBody
	List<Map<String, Object>> qryMemIsExist(String memNo,String memId,String structId){
		List<Map<String, Object>> result = otherDataService.qryMemIsExist(memNo, memId, structId);
		return result;
   }
	

	/**
	 * 新增成员
	 */
	@RequestMapping("/InsertMemData")		
	@ResponseBody
	public int InsertMemData(String memMap) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();   
		int result =0;
		List<Map<String, Object>> mem = mapper.readValue(memMap, new TypeReference<List<Map<String, Object>>>(){});	
		Map<String, Object> memData =mem.get(0);
		result = otherDataService.addStructMem(memData);
		return result;	
	}
	
	/**
	 * 编辑成员
	 */
	@RequestMapping("/UpdateMemData")		
	@ResponseBody
	public int UpdateMemData(String memMap) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();   
		int result =0;
		List<Map<String, Object>> mem = mapper.readValue(memMap, new TypeReference<List<Map<String, Object>>>(){});	
		Map<String, Object> memData =mem.get(0);
		result = otherDataService.updateStructMem(memData);
		return result;	
	}
	
	/**
	 * 删除结构体单一成员
	 */
	@RequestMapping("/DeleteOneMem")
	@ResponseBody
	public int deleteOneMem(String structId,String memId){
		return otherDataService.delStructOneMem(structId,memId);
	}
	
	
	
	/**
	 * 跳转到中间件页面
	 */
	@RequestMapping("/midwareManager")
	public String toMidwareManager() {		
	return "midwareManager";
	}
	
	
	/**
	 * 中间件编辑模态框数据回填
	 */
	@RequestMapping("/QryMidByMidId")
	@ResponseBody
	public Map<String, Object> qryMidByMidId(String midId) {		
		return otherDataService.qryMidByMidId(midId);
	}
	/**
	 * 新增中间件	
	 */
	@RequestMapping("/InsertMid")
	@ResponseBody
	public int InsertCls(String modMidId,String modMidName,String modMidDesc) {
		int result = otherDataService.InsertMid(modMidId,modMidName,modMidDesc);
		return result;
	};

	/**
	 * 编辑中间件
	 */
	@RequestMapping("/UpdateMid")
	@ResponseBody
	public int UpdateCls(String modMidId,String modMidName,String modMidDesc) {
		int result = otherDataService.UpdateMid(modMidId,modMidName,modMidDesc);
		return result;
	};

	/**
	 * 删除中间件
	 */
	@RequestMapping("/DeleteMid")
	@ResponseBody
	public int deleteCls(String midId) {
		int result = otherDataService.deleteMid(midId);
		return result;
	};	
	
	/**
	 * 跳转到中间件页面
	 */
	@RequestMapping("/messageManager")
	public String toMessageManager() {		
		return "messageManager";
	}


	/**
	 * 跳转到硬件设备页面
	 */
	@RequestMapping("/moduleManager")
	public String toModuleManager() {		
		return "moduleManager";
	}

	/**
	 * 查询所有节点下的设备
	 */
	@RequestMapping("/QryAllModule")
	@ResponseBody
	public List<Map<String, Object>> qryAllModule() {		
		return otherDataService.qryAllModule();
	}
	/**
	 * 查设备详细信息
	 */
	@RequestMapping("/QryModDetail")
	@ResponseBody
	public Map<String, Object> qryModDetail(String modId,String modNod) {		
		return otherDataService.qryModDetail(modId,modNod);
	}
	
	/**
	 * 保存之前查重
	 */
	@RequestMapping("/QryModIsExist")
	@ResponseBody
	public Map<String, Object> qryModIsExist(String modId,String modNod) {		
		return otherDataService.qryModDetail(modId,modNod);
	}
	
	/**
	 * 新增设备
	 */
	@RequestMapping("/InsertModule")		
	@ResponseBody
	public int InsertModule(String moduleMap) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();   
		int result =0;
		List<Map<String, Object>> map = mapper.readValue(moduleMap, new TypeReference<List<Map<String, Object>>>(){});
		Map<String, Object> data = map.get(0);
		    result = otherDataService.InsertModule(data);
		return result;	
	}
	
	
	/**
	 * 修改设备
	 */
	@RequestMapping("/UpdateModule")		
	@ResponseBody
	public int UpdateModule(String moduleMap) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();   
		int result =0;
		List<Map<String, Object>> map = mapper.readValue(moduleMap, new TypeReference<List<Map<String, Object>>>(){});
		Map<String, Object> data = map.get(0);
		   result = otherDataService.UpdateModule(data);
		return result;	
	}
	
	
	@RequestMapping("/DeleteModule")
	@ResponseBody
	public int deleteModule(String modId,String modNod) {
		System.out.println(modId);
		System.out.println(modNod);
		int result = otherDataService.deleteModule(modId,modNod);
		return result;
	};	
}
