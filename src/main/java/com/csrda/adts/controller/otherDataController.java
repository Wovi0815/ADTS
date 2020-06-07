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
	
	@RequestMapping("/qryDataType")
	@ResponseBody
	public List<Map<String, Object>> qryDataType(){
		return otherDataService.qryTypeData();
	}
	
	@RequestMapping("/saveBasicDataType")
	@ResponseBody
	public int saveBasicDataType(String typId,String typName,String typAttr,String typSize,String typDesc) {
		if(Integer.valueOf(otherDataService.qryRep(typId).get(0).get("num").toString())>0) {
			return 0;
		}
		Map<String, String> typeData=new HashMap<String, String>();
		typeData.put("typId", typId);
		typeData.put("typName", typName);
		typeData.put("typAttr", typAttr);
		typeData.put("typSize", typSize);
		typeData.put("typDesc", typDesc);
		return otherDataService.saveBasicDataType(typeData);
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
		return otherDataService.updateBasicDataType(typeData);
	}
	
	@RequestMapping("/detailBasicDataType")
	@ResponseBody
	public List<Map<String,Object>> detailBasicDataType(String typId){
		return otherDataService.detailBasicDataType(typId);
	}
	
	@RequestMapping("/delBasicDataType")
	@ResponseBody
	public int delBasicDataType(String typId) {
		return otherDataService.delBasicDataType(typId);
	}
	
	@RequestMapping("/addStruct")
	public String addStruct() {
		return "addStruct";
	}
	
	@RequestMapping("/addStructData")
	@ResponseBody
	public String addStructData(String typId,String typName,String typSize,String typDesc,String memList){
		return otherDataService.addStruct(typId, typName, typSize, typDesc, memList);
	}

	@RequestMapping("/detailStructMem")
	@ResponseBody
	public List<Map<String, Object>> detailStructMem(String typId){
		return otherDataService.qryStructMem(typId);
	}
	
	@RequestMapping("/detailStruct")
	public String detailStruct(String typId){
		return "detailStruct";
	}
	
	@RequestMapping("/editStructData")
	@ResponseBody
	public String editStructData(String typId,String typName,String typSize,String typDesc,String memList){
		return otherDataService.updateStruct(typId, typName, typSize, typDesc, memList);
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
}
