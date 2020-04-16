package com.csrda.adts.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.sql.visitor.functions.Char;
import com.csrda.adts.dao.WordExportDao;
import com.csrda.adts.pojo.DataType;
import com.csrda.adts.pojo.Message;
import com.csrda.adts.pojo.MessageData;
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
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

@Controller
public class WordExport {
	
	@Autowired
	WordExportDao wordExportDao;
	
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/wordExport")
	@ResponseBody
	public Boolean wordExport(){
		try {
			//ThirdLevelTitle datatypes=null;
		//datatypes=qryDataType();
		SecondLevelTitle secondLevelTitle=new SecondLevelTitle();
		secondLevelTitle.setStructs(qryStruct());
		secondLevelTitle.setDataTypes(qryDataType());
		//secondLevelTitle.setDataType(datatypes.getDataType());
		secondLevelTitle.setMidWares(qryMidWare());
		secondLevelTitle.setMessages(qryMessage());
		HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
		Configure config = Configure.newBuilder().bind("dataTypes",policy)
				.bind("structMembers", policy)
				.bind("objectDatas", policy)
				.bind("interfaces", policy)
				.bind("inputParams", policy)
				.bind("outputParams", policy)
				.bind("mesDatas", policy)
				.build();
				
		
		XWPFTemplate template = XWPFTemplate.compile("src/main/resources/public/temp/temp.docx",config).render(secondLevelTitle);
		FileOutputStream out = new FileOutputStream("src/main/resources/public/output/output.docx");
		template.write(out); 
		out.flush();
		out.close();
		template.close();
		return true;
		} catch (Exception e) {
			//System.out.println(e.getStackTrace());
			logger.debug(e.getStackTrace().toString());
			return false;
		}
		
		
	}
	
	@RequestMapping("/exportDataType")
	@ResponseBody
	public Boolean exportDataType() throws IOException {
		try {
		SecondLevelTitle secondLevelTitle=new SecondLevelTitle();
		secondLevelTitle.setStructs(qryStruct());
		secondLevelTitle.setDataTypes(qryDataType());
		HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
		Configure config = Configure.newBuilder()
				.bind("dataTypes",policy)
				.bind("structMembers", policy)
				.build();
				
		XWPFTemplate template = XWPFTemplate.compile("src/main/resources/public/temp/typeDataTemp.docx",config).render(secondLevelTitle);
		FileOutputStream out = new FileOutputStream("src/main/resources/public/output/typeData.docx");
		template.write(out); 
		out.flush();
		out.close();
		template.close();
		return true;
		} catch (Exception e) {
			logger.debug(e.getStackTrace().toString());
			return false;
		}
	}
	
	@RequestMapping("/exportMidware")
	@ResponseBody
	public Boolean exportMidware() {
		try {
			SecondLevelTitle secondLevelTitle=new SecondLevelTitle();
			
			secondLevelTitle.setMidWares(qryMidWare());
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.newBuilder()
					.bind("objectDatas", policy)
					.bind("interfaces", policy)
					.bind("inputParams", policy)
					.bind("outputParams", policy)
					.build();
					
			XWPFTemplate template = XWPFTemplate.compile("src/main/resources/public/temp/midWareTemp.docx",config).render(secondLevelTitle);
			FileOutputStream out = new FileOutputStream("src/main/resources/public/output/midWare.docx");
			template.write(out); 
			out.flush();
			out.close();
			template.close();
			return true;
		} catch (Exception e) {
			//System.out.println(e.getStackTrace());
			logger.debug(e.getStackTrace().toString());
			return false;
		}
	}
	
	@RequestMapping("/exportMessage")
	@ResponseBody
	public Boolean exportMessage() {
		try {
			SecondLevelTitle secondLevelTitle=new SecondLevelTitle();
			secondLevelTitle.setMessages(qryMessage());
			HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();
			Configure config = Configure.newBuilder()
					.bind("mesDatas", policy)
					.build();
					
			XWPFTemplate template = XWPFTemplate.compile("src/main/resources/public/temp/messageTemp.docx",config).render(secondLevelTitle);
			FileOutputStream out = new FileOutputStream("src/main/resources/public/output/message.docx");
			template.write(out); 
			out.flush();
			out.close();
			template.close();
			
			return true;
		} catch (Exception e) {
			//System.out.println(e.getStackTrace());
			logger.debug(e.getStackTrace().toString());
			return false;
		}
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
	
	public List<DataType> qryStruct() {
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
			dataType.setDefinition(definition);
			structs.add(dataType);
		}
		//secondLevelTitle.setStructs(structs);
		
		return structs;
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
				objectData.setEq(j+1);
				objectData.setClassId(qryObject.get(j).get("c_id").toString());
				objectData.setClassName(qryObject.get(j).get("c_name").toString());
				objectData.setClassDesc(qryObject.get(j).get("c_desc").toString());
				objectData.setClassFather(qryObject.get(j).get("c_father").toString());
				List<Map<String, Object>> qryMethod=wordExportDao.qryMethod(objectData.getClassId());
				List<Method> interfaces=new ArrayList<Method>();
				List<Method> methods=new ArrayList<Method>();
				for (int k = 0; k < qryMethod.size(); k++) {
					Method method=new Method();
					method.setEq(k+1);
					method.setId(qryMethod.get(k).get("id").toString());
					method.setInterfaceId(qryMethod.get(k).get("i_id").toString());
					method.setInterfaceName(qryMethod.get(k).get("i_name").toString());
					method.setInterfaceDesc(qryMethod.get(k).get("i_desc").toString());
					method.setInterfaceReturn(qryMethod.get(k).get("i_return").toString());
					method.setInterfaceReturnDesc(qryMethod.get(k).get("i_return_desc").toString());
					method.setInterfaceCode(method.getInterfaceReturn()+" "+
							objectData.getClassId()+"::"+
							method.getInterfaceId()+"(");
					List<Map<String, Object>> qryInputParam=wordExportDao.qryInputParam(method.getId());
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
					List<Map<String, Object>> qryOutputParam=wordExportDao.qryOutputParam(method.getId());
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
	
	
	public List<Message> qryMessage(){
		List<Message> messages=new ArrayList<Message>();
		List<Map<String, Object>> qryMessage=wordExportDao.qryMessage();
		for (int i = 0; i < qryMessage.size(); i++) {
			Message message=new Message();
			message.setMesId(qryMessage.get(i).get("mes_id").toString());
			message.setMesName(qryMessage.get(i).get("mes_name").toString());
			message.setMesDesc(qryMessage.get(i).get("mes_desc").toString());
			message.setMesDestination(qryMessage.get(i).get("mes_destination").toString());
			message.setMesFunId(qryMessage.get(i).get("mes_fun_id").toString());
			message.setMesSource(qryMessage.get(i).get("mes_source").toString());
			message.setMesIdNum(qryMessage.get(i).get("mes_id_num").toString());
			List<MessageData> mesDatas=new ArrayList<MessageData>();
			List<Map<String, Object>> qryMesMem=wordExportDao.qryMesMem(message.getMesId());
			for (int j = 0; j < qryMesMem.size(); j++) {
				MessageData messageData=new MessageData();
				messageData.setMesDataRange(qryMesMem.get(j).get("mes_data_range").toString());
				messageData.setMesDataName(qryMesMem.get(j).get("mes_data_name").toString());
				messageData.setMesDataDesc(qryMesMem.get(j).get("mes_data_desc").toString());
				messageData.setMesDataType(qryMesMem.get(j).get("mes_data_type").toString());
				messageData.setMesDataLong(qryMesMem.get(j).get("mes_data_long").toString());
				mesDatas.add(messageData);
			}
			System.out.println(mesDatas.toString());
			message.setMesDatas(mesDatas);
			messages.add(message);
		}
		return messages;
	}
	
	
	

}
