package com.csrda.adts.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.csrda.adts.service.InterfaceService;


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

	//查询中间件下所有父类，构建下拉框
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
			List<Map<String, Object>> result = interfaceService.qryMidClsByFather(cfather, midwareId);
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
		@RequestMapping("/QryClsFather.do")
		@ResponseBody
		public List<Map<String,Object>> QueryClsFather() {
			List<Map<String, Object>> result = interfaceService.qryClsFather();
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
	
	//查询类下所有参数个数以构建下拉框
	@RequestMapping("/QryClsParaCount.do")
	@ResponseBody
	public List<Map<String,Object>> QryClsParaCount(String classId) {
		List<Map<String, Object>> result = interfaceService.qryClsParaCount(classId);
		return result;
	}	
	//查询类下所有返回值类型以构建下拉框
	@RequestMapping("/QryClsReturnType.do")		
	@ResponseBody
	List<Map<String,Object>> qryClsReturnType(String classId){
		List<Map<String, Object>> result = interfaceService.qryClsReturnType(classId);
		return result;
		
	}
		
		
	//查询接口详情
	@RequestMapping("/QryInterfaceDetail.do")		
	@ResponseBody
	List<Map<String, Object>> qryInterfaceDetail(String interfaceId,String interfaceParaCount,
			 String interfaceParaList){
		Map<String, Object> map = interfaceService.qryFindUniqueInterface(interfaceId,
				interfaceParaCount,interfaceParaList);
		String id = map.get("id").toString();
		List<Map<String, Object>> result = interfaceService.qryInterfacePara(id);
		return result;
	
	}
	
	
	//根据下拉框查询接口
		@RequestMapping("/QryInterfaceBySelect.do")		
		@ResponseBody
		List<Map<String, Object>> qryInterfaceBySelect(String selectReturn,String selectCount,String classId){
			List<Map<String, Object>> result = null;
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

}
