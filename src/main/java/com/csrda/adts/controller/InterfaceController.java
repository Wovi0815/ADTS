package com.csrda.adts.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csrda.adts.pojo.Param;
import com.csrda.adts.service.InterfaceService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class InterfaceController {
@Autowired
private InterfaceService interfaceService;
Logger logger= LoggerFactory.getLogger(this.getClass());
	//查询中间件,构建菜单
	@RequestMapping("/QueryMidware.do")
	@ResponseBody
	public List<Map<String,Object>> QueryMidware() {
		List<Map<String, Object>> result = interfaceService.qryMidware();
		return result;
	}

	//跳转到中间件所属类页面
		@RequestMapping("/classMain")
			public String toClassMain() {		
			return "classMain";
		}
		
	//根据中间件查询下属类
		@RequestMapping("/QueryMidwareClass.do")
		@ResponseBody
		public List<Map<String,Object>> QueryMidwareClass(String midwareId) {		
			List<Map<String, Object>> result = interfaceService.qryMidwareClass(midwareId);
			return result;
		}

	//查询中间件下所有父类，构建筛选下拉框
		@RequestMapping("/QryMidClsFather.do")
		@ResponseBody
		public List<Map<String,Object>> QueryMidClsFather(String midwareId) {
			List<Map<String, Object>> result = interfaceService.qryMidClsFather(midwareId);
			return result;
		} 

	//根据下拉框所选择父类进行筛选
		@RequestMapping("/QryMidClsByFather.do")
		@ResponseBody
		public List<Map<String,Object>> QueryMidClsByFather(String cfather,String midwareId) {
			List<Map<String, Object>> result = null;
			if(cfather==""){
				result = interfaceService.qryMidwareClass(midwareId);	
			}else {
				result = interfaceService.qryMidClsByFather(cfather, midwareId);
			}
		return result;
		}
		
		
	//根据类标识查询类信息
		@RequestMapping("/QryMidClsByCId.do")
		@ResponseBody
		public Map<String,Object> QueryMidClsByCId(String cId) {
			Map<String, Object>result = interfaceService.qryMidClsByCId(cId);
			return result;
		};

	//查询所有父类、
		@RequestMapping("/QryClsBeFather.do")
		@ResponseBody
		public List<Map<String,Object>> QueryClsFather(String midwareId) {
			List<Map<String, Object>> result = interfaceService.qryClsBeFather(midwareId);
			return result;
		};

	// 新增类	
		@RequestMapping("/InsertCls.do")
		@ResponseBody
		public int InsertCls(String modalCId,String modalCName,
				String modalCDesc,String modalCFather,String modalCMidware) {
			int result = interfaceService.InsertCls(modalCId,modalCName,modalCDesc,modalCFather, modalCMidware);
			return result;
		};
		
		
	// 编辑类
		@RequestMapping("/UpdateCls.do")
		@ResponseBody
		public int UpdateCls(String modalCId,String modalCName,
				String modalCDesc,String modalCFather,String modalCMidware) {
			int result = interfaceService.UpdateCls(modalCId, modalCName, modalCDesc, modalCFather, modalCMidware);
			return result;
		};
	
	// 删除类
		@RequestMapping("/DeleteCls.do")
		@ResponseBody
		public int deleteCls(String cId) {
			int result = interfaceService.deleteCls(cId);
			return result;
		};
		
	//跳转到类下属接口页面
		@RequestMapping("/interfaceMain")
		public String toInterfaceMain() {
			return "interfaceMain";
		}
			
	//查询类下属接口
	@RequestMapping("/QryClsInterface.do")
	@ResponseBody
	public List<Map<String,Object>> QryClsInterface(String classId) {		
		List<Map<String, Object>> result = interfaceService.qryClsInterface(classId);
		return result;
	}
	
	//查询类下属接口下所有参数个数以构建下拉框，筛选数据
	@RequestMapping("/QryInterParaCount.do")
	@ResponseBody
	public List<Map<String,Object>> QryClsParaCount(String classId) {
		List<Map<String, Object>> result = interfaceService.qryInterParaCount(classId);
		return result;
	}	
	//查询类下属接口下所有返回值类型以构建下拉框，筛选数据
	@RequestMapping("/QryInterReturnType.do")		
	@ResponseBody
	List<Map<String,Object>> qryClsReturnType(String classId){
		List<Map<String, Object>> result = interfaceService.qryInterReturnType(classId);
		return result;
		
	}
		
		
	//查询接口详情
	@RequestMapping("/QryInterfaceParaDetail.do")		
	@ResponseBody
	List<Map<String, Object>> qryInterfaceParaDetail(String interfaceId,String interfaceParaCount,
			 String interfaceParaList){
		Map<String, Object> map = interfaceService.qryFindUniqueInterface(interfaceId,
				interfaceParaCount,interfaceParaList);
		String id = map.get("id").toString();
		List<Map<String, Object>> result = interfaceService.qryInterfacePara(id);
		return result;
	
	}
	
	//查询接口详情(接口描述、备注)
		@RequestMapping("/QryInterfaceDetail.do")		
		@ResponseBody
		Map<String, Object> qryInterfaceDetail(String interfaceId,String interfaceParaCount,
				 String interfaceParaList){
			Map<String, Object> map = interfaceService.qryFindUniqueInterface(interfaceId,
					interfaceParaCount,interfaceParaList);
			return map;
		
		}
	
	
	//根据下拉框查询接口
		@RequestMapping("/QryInterfaceBySelect.do")		
		@ResponseBody
		List<Map<String, Object>> qryInterfaceBySelect(String selectReturn,String selectCount,String classId){
			List<Map<String, Object>> result = new ArrayList();
			if((selectReturn=="")&& (selectCount!="")) {
				 result = interfaceService.qryInterfaceByParaCount(selectCount, classId);
			}else if((selectCount=="")&&(selectReturn!="")){
				 result = interfaceService.qryInterfaceByParaReturnType(selectReturn, classId);
			}else if((selectCount=="")&&(selectReturn=="")){
				 result = interfaceService.qryClsInterface(classId);
			}else if((selectCount!="")&&(selectReturn!="")) {
				result =interfaceService.qryInterfaceBySelect(selectReturn, selectCount, classId);
			}
			return result;
		
		}
		//新增接口 信息
		@RequestMapping("/InsertInterface.do")		
		@ResponseBody
		public String InsertInterface(String interfaceMap) throws JsonParseException, JsonMappingException, IOException {
		    ObjectMapper mapper = new ObjectMapper();   
			List<Map<String, Object>> iMap = mapper.readValue(interfaceMap, new TypeReference<List<Map<String, Object>>>(){});
			String interfaceId=iMap.get(0).get("interfaceId").toString();
			String interfaceName=iMap.get(0).get("interfaceName").toString();
			String interfaceDesc=iMap.get(0).get("interfaceDesc").toString();
			String interfaceRemark=iMap.get(0).get("interfaceRemark").toString();
			String interfaceRetnTyp=iMap.get(0).get("interfaceRetnTyp").toString();
			String interfaceRetnDesc=iMap.get(0).get("interfaceRetnDesc").toString();
			String interfaceCls=iMap.get(0).get("interfaceCls").toString();
			String interfaceParaList=iMap.get(0).get("interfaceParaList").toString();
			String interfaceParaCount=iMap.get(0).get("interfaceParaCount").toString();
			int inresult = interfaceService.InsertInterface(interfaceId, interfaceName, interfaceDesc, 
					interfaceRemark, interfaceRetnTyp, interfaceRetnDesc, interfaceCls, 
					interfaceParaList, interfaceParaCount);
			Map<String, Object> map = interfaceService.qryFindUniqueInterface(interfaceId,
					interfaceParaCount,interfaceParaList);
			String id = map.get("id").toString();
			return id;
			
		}

		//新增接口 参数信息
		@RequestMapping("/InsertInterfaceParas.do")		
		@ResponseBody
		int InsertInterfaceParas(String paraListMap,String id) throws JsonParseException, JsonMappingException, IOException{
			 ObjectMapper mapper = new ObjectMapper();   
			 List<Map<String, Object>> paraList = mapper.readValue(paraListMap, new TypeReference<List<Map<String, Object>>>(){});
			 int result =0;
			 for(int i=0;i<paraList.size();i++) {
				  String  paraAttr = paraList.get(i).get("paraAttr").toString();
				  String  paraId = paraList.get(i).get("paraId").toString();
				  String  paraName = paraList.get(i).get("paraName").toString();
				  String  paraType = paraList.get(i).get("paraType").toString();
				  String  paraNo = paraList.get(i).get("paraNo").toString();
				  String  paraDesc = paraList.get(i).get("paraDesc").toString();
				  String  paraPhy = paraList.get(i).get("paraPhy").toString();
				  String  paraMax = paraList.get(i).get("paraMax").toString();
				  String  paraMin = paraList.get(i).get("paraMin").toString();
				  String  paraDefault =  paraList.get(i).get("paraDefault").toString();  
				  
				  result = interfaceService.InsertInterfacePara(paraId, paraName, paraDesc, 
							paraType, paraAttr, paraNo, 
							id, paraPhy, paraMax, paraMin, paraDefault);
			 }
			 return result;
			
			
		}
		
	//删除接口
		@RequestMapping("/deleteInterface.do")		
		@ResponseBody
		Map<String,Object> deleteInterface(String interfaceId,String interfaceParaCount,String interfaceParaList){
			Map<String, Object> map = interfaceService.qryFindUniqueInterface(interfaceId,
					interfaceParaCount,interfaceParaList);
			String id = map.get("id").toString();
			int delresult = interfaceService.deleteInterface(id);
			Map<String, Object> result =new HashMap<String, Object>();
			result.put("delresult", delresult);
			result.put("uniqueId", id);
			return result;	
			
		
	}
	
		
	//删除接口参数
		@RequestMapping("/deleteInterfacePara.do")		
		@ResponseBody
		int deleteInterfacePara(String id){
			int result = interfaceService.deleteInterfacePara(id);
			return result;		
		}		
}
