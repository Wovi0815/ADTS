package com.csrda.adts.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.csrda.adts.dao.WordExportDao;
import com.csrda.adts.pojo.DataType;
import com.csrda.adts.pojo.Method;
import com.csrda.adts.pojo.MidWare;
import com.csrda.adts.pojo.ObjectData;
import com.csrda.adts.pojo.Param;
import com.csrda.adts.pojo.SecondLevelTitle;
import com.csrda.adts.pojo.StructMember;
import com.csrda.adts.pojo.ThirdLevelTitle;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;

@Controller
public class WordExport {
	
	@Autowired
	WordExportDao wordExportDao;
	
	@RequestMapping("/wordExport")
	@ResponseBody
	public String wordExport() throws IOException {
		
		//ThirdLevelTitle datatypes=null;
		//datatypes=qryDataType();
		SecondLevelTitle secondLevelTitle=null;
		secondLevelTitle=qryStruct();
		secondLevelTitle.setDataTypes(qryDataType());
		//secondLevelTitle.setDataType(datatypes.getDataType());
		secondLevelTitle.setMidWares(qryMidWare());
		HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
		Configure config = Configure.newBuilder().bind("dataTypes",policy)
				.bind("structMembers", policy)
				.bind("objectDatas", policy)
				.bind("interfaces", policy)
				.bind("inputParams", policy)
				.bind("outputParams", policy)
				.build();
				
		
		XWPFTemplate template = XWPFTemplate.compile("src/main/resources/temp.docx",config).render(secondLevelTitle);
		FileOutputStream out = new FileOutputStream("src/main/resources/public/output.docx");
		template.write(out); 
		out.flush();
		out.close();
		template.close();
		
		
		return "success";
	}
	
	
	public List<DataType> qryDataType() {
		ThirdLevelTitle thirdLevelTitle=new ThirdLevelTitle();
		List<Map<String, Object>> datas=wordExportDao.qryDataType();
		List<DataType> dataTypes=new ArrayList<DataType>();
		for(int i=0;i<datas.size();i++) {
			DataType dataType=new DataType();
			dataType.setTypeId(datas.get(i).get("typeId").toString());
			dataType.setTypeSize(datas.get(i).get("typeSize").toString());
			dataType.setTypeDesc(datas.get(i).get("typeDesc").toString());
			dataTypes.add(dataType);
		}
		thirdLevelTitle.setDataTypes(dataTypes);
		
		return dataTypes;
	}
	
	public SecondLevelTitle qryStruct() {
		SecondLevelTitle secondLevelTitle=new SecondLevelTitle();
		List<Map<String, Object>> typeDatas=wordExportDao.qryStruct();
		List<DataType> structs=new ArrayList<DataType>();
		for(int i=0;i<typeDatas.size();i++) {
			DataType dataType=new DataType();
			dataType.setTypeId(typeDatas.get(i).get("typeId").toString());
			dataType.setTypeName(typeDatas.get(i).get("typeName").toString());
			dataType.setTypeDesc(typeDatas.get(i).get("typeDesc").toString());
			List<StructMember> structMems=new ArrayList<StructMember>();
			List<Map<String, Object>> structDatas=wordExportDao.qryStructMem(dataType.getTypeId());
			String definition="typedef struct "+dataType.getTypeId()+"{\r\n";
			for (int j = 0; j < structDatas.size(); j++) {
				
				StructMember structMember=new StructMember();
				structMember.setMemId(structDatas.get(j).get("mem_id").toString());
				structMember.setMemName(structDatas.get(j).get("mem_name").toString());
				structMember.setMemType(structDatas.get(j).get("mem_type").toString());
				structMember.setMemDesc(structDatas.get(j).get("mem_desc").toString());
				structMember.setMemPhyDim(structDatas.get(j).get("mem_phy_dim").toString());
				structMember.setMemMax(structDatas.get(j).get("mem_max").toString());
				structMember.setMemMin(structDatas.get(j).get("mem_min").toString());
				structMember.setMemDefault(structDatas.get(j).get("mem_default").toString());
				structMember.setMemDesc(structMember.getMemDesc()+
						"\n物理量刚:"+structMember.getMemPhyDim()+
						"\n最大值:"+structMember.getMemMax()+
						"\n最小值:"+structMember.getMemMin()+
						"\n默认值:"+structMember.getMemDefault()
						);
				structMems.add(structMember);
				definition=definition+"\t"+structMember.getMemType()+" "+structMember.getMemId()+";\r\n";
			}
			definition=definition+"}"+dataType.getTypeId()+";";
			dataType.setStructMembers(structMems);
			structs.add(dataType);
			dataType.setDefinition(definition);
		}
		secondLevelTitle.setStructs(structs);
		
		return secondLevelTitle;
	}
	
	
	public List<MidWare> qryMidWare(){
		List<MidWare> midWares=new ArrayList<MidWare>();
		List<Map<String, Object>> qryMidWare=wordExportDao.qryMidWare();
		for (int i = 0; i < qryMidWare.size(); i++) {
			MidWare midware=new MidWare();
			midware.setMidID(qryMidWare.get(i).get("mid_id").toString());
			midware.setMidName(qryMidWare.get(i).get("mid_name").toString());
			midware.setMidDesc(qryMidWare.get(i).get("mid_desc").toString());
			List<Map<String, Object>> qryObject=wordExportDao.qryObject(midware.getMidID());
			List<ObjectData> classs=new ArrayList<ObjectData>();
			List<ObjectData> objectDatas=new ArrayList<ObjectData>();
			for (int j = 0; j < qryObject.size(); j++) {
				ObjectData objectData=new ObjectData();
				objectData.setClassId(qryObject.get(j).get("c_id").toString());
				objectData.setClassName(qryObject.get(j).get("c_name").toString());
				objectData.setClassDesc(qryObject.get(j).get("c_desc").toString());
				objectData.setClassFather(qryObject.get(j).get("c_father").toString());
				List<Map<String, Object>> qryMethod=wordExportDao.qryMethod(objectData.getClassId());
				List<Method> interfaces=new ArrayList<Method>();
				List<Method> methods=new ArrayList<Method>();
				for (int k = 0; k < qryMethod.size(); k++) {
					Method method=new Method();
					method.setInterfaceId(qryMethod.get(k).get("i_id").toString());
					method.setInterfaceName(qryMethod.get(k).get("i_name").toString());
					method.setInterfaceDesc(qryMethod.get(k).get("i_desc").toString());
					method.setInterfaceReturn(qryMethod.get(k).get("i_return").toString());
					method.setInterfaceReturnDesc(qryMethod.get(k).get("i_return_desc").toString());
					method.setInterfaceCode(method.getInterfaceReturn()+" "+
							objectData.getClassId()+"::"+
							method.getInterfaceId()+"(");
					List<Map<String, Object>> qryInputParam=wordExportDao.qryInputParam(method.interfaceId);
					List<Param> inputParams=new ArrayList<Param>();
					List<Param> outputParams=new ArrayList<Param>();
					for (int l = 0; l < qryInputParam.size(); l++) {
						Param param=new Param();
						param.setParaId(qryInputParam.get(l).get("para_id").toString());
						param.setParaName(qryInputParam.get(l).get("para_name").toString());
						param.setParaType(qryInputParam.get(l).get("para_type").toString());
						param.setParaMax(qryInputParam.get(l).get("para_max").toString());
						param.setParaMin(qryInputParam.get(l).get("para_min").toString());
						param.setParaPhyDim(qryInputParam.get(l).get("para_phy_dim").toString());
						param.setParaDefault(qryInputParam.get(l).get("para_default").toString());
						param.setParaDesc(qryInputParam.get(l).get("para_desc").toString()+":\n"+
								"物理量刚:"+param.getParaPhyDim()+"\n"+
								"最大值:"+param.getParaMax()+"\n"+
								"最小值:"+param.getParaDefault());
						if(l==qryInputParam.size()-1) {
							method.setInterfaceCode(method.getInterfaceCode()+param.getParaId()+")");
						}else {
							method.setInterfaceCode(method.getInterfaceCode()+param.getParaId()+",");
						}
						inputParams.add(param);
					}
					List<Map<String, Object>> qryOutputParam=wordExportDao.qryOutputParam(method.interfaceId);
					for (int l = 0; l < qryOutputParam.size(); l++) {
						Param param=new Param();
						param.setParaId(qryInputParam.get(l).get("para_id").toString());
						param.setParaName(qryInputParam.get(l).get("para_name").toString());
						param.setParaType(qryInputParam.get(l).get("para_type").toString());
						param.setParaMax(qryInputParam.get(l).get("para_max").toString());
						param.setParaMin(qryInputParam.get(l).get("para_min").toString());
						param.setParaPhyDim(qryInputParam.get(l).get("para_phy_dim").toString());
						param.setParaDefault(qryInputParam.get(l).get("para_default").toString());
						param.setParaDesc(qryInputParam.get(l).get("para_desc").toString()+":\n"+
								"物理量刚:"+param.getParaPhyDim()+"\n"+
								"最大值:"+param.getParaMax()+"\n"+
								"最小值:"+param.getParaDefault());
						outputParams.add(param);
					}
					method.setInputParams(inputParams);
					if(qryOutputParam.size()==0) {
						Param param=new Param();
						param.setParaId(" ");
						param.setParaName(" ");
						param.setParaType(" ");
						param.setParaMax(" ");
						param.setParaMin(" ");
						param.setParaPhyDim(" ");
						param.setParaDefault(" ");
						param.setParaDesc(" ");
						outputParams.add(param);
						method.setOutputParams(outputParams);
					}else {
						method.setOutputParams(outputParams);
					}
					
					interfaces.add(method);
					methods.add(method);
				}
				objectData.setInterfaces(interfaces);
				objectData.setMethods(methods);
				classs.add(objectData);
				objectDatas.add(objectData);
			}
			midware.setClasss(classs);
			midware.setObjectDatas(objectDatas);
			midWares.add(midware);
		}
		
		return midWares;
		
	}
	
	
	

}
