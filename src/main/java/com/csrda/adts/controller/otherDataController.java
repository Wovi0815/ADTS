package com.csrda.adts.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.csrda.adts.service.OtherDataService;



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
	@RequestMapping("/qryDataType")
	@ResponseBody
	public List<Map<String, Object>> qryDataType(){
		return otherDataService.qryDataType();
	}
	/**
	 * 保存数据类型
	 */
	@RequestMapping("/saveDataType")
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
		return otherDataService.saveDataType(typeData);
	}
	/**
	 * 修改数据类型
	 */
	@RequestMapping("/updateDataType")
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
	
	@RequestMapping("/addStruct")
	@ResponseBody
	public String addStruct(String structId,String structName,String structSize,String structDesc,String memList){
		return otherDataService.addStruct(structId, structName, structSize, structDesc, memList);
	}
	

	
	/**
	 * 根据typId 查信息，可用于回填模态框
	 */
	@RequestMapping("/qryDetailDataType")
	@ResponseBody
	public Map<String,Object> qryDetailBasicDataType(String typId){
		return otherDataService.qryDetailDataType(typId);
	}
	/**
	 * 删除数据类型
	 */

	@RequestMapping("/delDataType")
	@ResponseBody
	public int delDataType(String typId) {
		return otherDataService.delDataType(typId);
	}
	
	/**
	 * 删除结构体成员
	 */
	@RequestMapping("/delStructMem")
	@ResponseBody
	public int delStructMem(String structId){
		return otherDataService.delStructMem(structId);
	}
	
	
	
	
	@RequestMapping("/detailStructMem")
	@ResponseBody
	public List<Map<String, Object>> detailStructMem(String typId){
		return otherDataService.qryStructMem(typId);
	}
	
	
	
	
	

	
	//跳转到中间件页面
	@RequestMapping("/midwareManager")
	public String toMidwareManager() {		
	return "midwareManager";
	}
	
	//中间件编辑模态框数据回填
	@RequestMapping("/QryMidByMidId")
	@ResponseBody
	public Map<String, Object> qryMidByMidId(String midId) {		
		return otherDataService.qryMidByMidId(midId);
	}
	
	// 新增中间件	
	@RequestMapping("/InsertMid")
	@ResponseBody
	public int InsertCls(String modMidId,String modMidName,String modMidDesc) {
		int result = otherDataService.InsertMid(modMidId,modMidName,modMidDesc);
		return result;
	};


	// 编辑中间件
	@RequestMapping("/UpdateMid")
	@ResponseBody
	public int UpdateCls(String modMidId,String modMidName,String modMidDesc) {
		int result = otherDataService.UpdateMid(modMidId,modMidName,modMidDesc);
		return result;
	};

	// 删除中中间件
	@RequestMapping("/DeleteMid")
	@ResponseBody
	public int deleteCls(String midId) {
		int result = otherDataService.deleteMid(midId);
		return result;
	};	
	
	//跳转到中间件页面
	@RequestMapping("/messageManager")
	public String toMessageManager() {		
		return "messageManager";
	}
	
	
	//跳转到硬件设备页面
	@RequestMapping("/moduleManager")
	public String toModuleManager() {		
		return "moduleManager";
	}
	//查询所有节点下的设备
	@RequestMapping("/QryAllModule")
	@ResponseBody
	public List<Map<String, Object>> qryAllModule() {		
		return otherDataService.qryAllModule();
	}
}
