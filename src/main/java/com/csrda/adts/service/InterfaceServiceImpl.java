package com.csrda.adts.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.csrda.adts.dao.InterfaceDao;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class InterfaceServiceImpl implements InterfaceService{
	@Autowired
	private InterfaceDao interfaceDao;
	
	@Override
	public List<Map<String, Object>> qryMidware() {	
		return interfaceDao.qryMidware();
	}

	@Override
	public List<Map<String, Object>> qryClsBeFather(String midwareId) {
		return interfaceDao.qryClsBeFather(midwareId);
	}

	@Override
	public List<Map<String, Object>> qryMidwareClass(String midwareId) {
		return interfaceDao.qryMidwareClass(midwareId);
	}

	@Override
	public List<Map<String, Object>> qryMidClsFather(String midwareId) {
		return interfaceDao.QryMidClsFather(midwareId);
	}


	@Override
	public List<Map<String, Object>> qryMidClsByFather(String cfather, String midwareId) {
		
		return interfaceDao.qryMidClsByFather(cfather, midwareId);
	}

	@Override
	public Map<String, Object> qryMidClsByCId(String cId) {
		
		return interfaceDao.qryMidClsByCId(cId);
	}

	@Override
	public int InsertCls(String modalCId,String modalCName,String modalCDesc,
			String modalCFather, String modalCMidware) {
		return interfaceDao.InsertCls(modalCId,modalCName,modalCDesc,modalCFather, modalCMidware);
	}

	@Override
	public int UpdateCls(String modalCId, String modalCName, String modalCDesc, String modalCFather) {
		return interfaceDao.UpdateCls(modalCId, modalCName, modalCDesc, modalCFather);
	}

	@Override
	public int deleteCls(String cId) {
		return interfaceDao.deleteCls(cId);
	}

	@Override
	public List<Map<String, Object>> qryClsInterface(String classId) {
		return interfaceDao.qryClsInterface(classId);
	}

	@Override
	public List<Map<String, Object>> qryInterParaCount(String classId) {
		return interfaceDao.qryClsParaCount(classId);
	}

	@Override
	public List<Map<String, Object>> qryInterReturnType(String classId) {
		return interfaceDao.qryClsReturnType(classId);
	}

	@Override
	public Map<String, Object> qryFindUniqueInterface(String interfaceId, String interfaceParaCount,
			String interfaceParaList) {
			return interfaceDao.qryFindUniqueInterface(interfaceId, interfaceParaCount, interfaceParaList);
	}

	@Override
	public List<Map<String, Object>> qryInterfacePara(String id) {
		return interfaceDao.qryInterfacePara(id);
	}

	@Override
	public List<Map<String, Object>> qryInterfaceByParaCount(String selectCount, String classId) {
		return interfaceDao.qryInterfaceByParaCount(selectCount, classId);
	}

	@Override
	public List<Map<String, Object>> qryInterfaceByParaReturnType(String selectReturn, String classId) {
		return interfaceDao.qryInterfaceByParaReturnType(selectReturn, classId);
	}

	@Override
	public List<Map<String, Object>> qryInterfaceBySelect(String selectReturn, String selectCount, String classId) {
		return interfaceDao.qryInterfaceBySelect(selectReturn, selectCount, classId);
	}

	@Override
	public int InsertInterface(String interfaceId, String interfaceName, String interfaceDesc, String interfaceRemark,
			String interfaceRetnTyp, String interfaceRetnDesc, String interfaceCls, String interfaceParaList,
			String interfaceParaCount) {
		
		return interfaceDao.InsertInterface(interfaceId, interfaceName, 
				interfaceDesc, interfaceRemark, interfaceRetnTyp, interfaceRetnDesc, 
				interfaceCls, interfaceParaList, interfaceParaCount);
	}

	@Override
	public int InsertInterfacePara(String paraId, String paraName, String paraDesc, String paraType, String paraAttr,
			String paraNo, String id, String paraPhy, String paraMax, String paraMin, String paraDefault) {
		return interfaceDao.InsertInterfacePara(paraId, paraName, paraDesc, paraType, paraAttr, paraNo, id, paraPhy, paraMax, paraMin, paraDefault);
	}

	@Override
	public int deleteInterface(String id) {
		return interfaceDao.deleteInterface(id);
	}

	@Override
	public int deleteInterfacePara(String id) {
		return interfaceDao.deleteInterfacePara(id);
	}

	@Override
	public int updateInterface(String interfaceName, String interfaceDesc, String interfaceRemark,
			String interfaceRetnTyp, String interfaceRetnDesc, String interfaceId, String interfaceParaCount,
			String interfaceParaList) {
		
		return interfaceDao.updateInterface(interfaceName, interfaceDesc, interfaceRemark, interfaceRetnTyp,
				interfaceRetnDesc, interfaceId, interfaceParaCount, interfaceParaList);
	}

	@Override
	public Map<String, Object> qryParaFillback(String uniqueInterid, String paraNo) {
		return interfaceDao.qryParaFillback(uniqueInterid, paraNo);
	}

	@Override
	public List<Map<String,Object>> qryParaIsExist(String paraNo, String paraId, String uniqueInterid) {
		return interfaceDao.qryParaIsExist(paraNo, paraId, uniqueInterid);
	}

	@Override
	public int UpdateIinfo(String pCount, String paraList, String uniqueInterid) {
		return interfaceDao.UpdateIinfo(pCount, paraList, uniqueInterid);
	}

	@Override
	public int UpdateParaData(String paraId, String paraName, String paraDesc, 
			String paraType, String paraAttr,String paraNo, String id, String paraPhy, 
			String paraMax, String paraMin, String paraDefault) {
		return interfaceDao.UpdateParaData(paraId, paraName, paraDesc, paraType, paraAttr, 
				paraNo, id, paraPhy, paraMax, paraMin, paraDefault);
	}

	@Override
	public int deleteOnePara(String uniqueInterid, String paraNo) {
		return interfaceDao.deleteOnePara(uniqueInterid, paraNo);
	}

	@Override
	@Transactional
	public String addInterfaceAndPara(String interfaceMap, String paraListMap) {
		try {
		ObjectMapper mapper = new ObjectMapper();   
		List<Map<String, Object>> iMap;
			iMap = mapper.readValue(interfaceMap, new TypeReference<List<Map<String, Object>>>(){});
			String interfaceId=iMap.get(0).get("interfaceId").toString();
			String interfaceName=iMap.get(0).get("interfaceName").toString();
			String interfaceDesc=iMap.get(0).get("interfaceDesc").toString();
			String interfaceRemark=iMap.get(0).get("interfaceRemark").toString();
			String interfaceRetnTyp=iMap.get(0).get("interfaceRetnTyp").toString();
			String interfaceRetnDesc=iMap.get(0).get("interfaceRetnDesc").toString();
			String interfaceCls=iMap.get(0).get("interfaceCls").toString();
			String interfaceParaList=iMap.get(0).get("interfaceParaList").toString();
			String interfaceParaCount=iMap.get(0).get("interfaceParaCount").toString();
			Map<String, Object> map = interfaceDao.qryFindUniqueInterface(interfaceId, interfaceParaCount, interfaceParaList);
			if(map!=null) {
				System.out.println("!!!!!!!!!!!!Exist");
				return "Exist";
			}else {
				//插入
				int iresult = interfaceDao.InsertInterface(interfaceId, interfaceName, 
						interfaceDesc, interfaceRemark, interfaceRetnTyp, interfaceRetnDesc, 
						interfaceCls, interfaceParaList, interfaceParaCount);
				if(iresult!=1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					System.out.println("!!!!!!!!!!!!InsertInterfaceFail");
					return "InsertInterfaceFail";
				}

				String id = interfaceDao.qryFindUniqueInterface(interfaceId, interfaceParaCount, interfaceParaList).get("id").toString();
				List<Map<String, Object>> paraList = mapper.readValue(paraListMap, new TypeReference<List<Map<String, Object>>>(){});
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
					//先查询参数是否重名
					List<Map<String, Object>> list = interfaceDao.qryParaIsExist(paraNo, paraId, id);
							
					if(list!=null) {//重复
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						System.out.println("!!!!!!!!!!!!ParaExist");
						return "ParaExist";
					}
					int  presult = interfaceDao.InsertInterfacePara(paraId, paraName, paraDesc, 
							paraType, paraAttr, paraNo, id, paraPhy, 
							paraMax, paraMin, paraDefault);
					if(presult!=1) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						System.out.println("!!!!!!!!!!!!InsertParaFail");
						return "InsertParaFail";
					}
				}
			}
			
		
		} catch (JsonParseException e) {	
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {	
			e.printStackTrace();
		}
			
			return "SUCCESS";
		
	}


}
