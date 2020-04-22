package com.csrda.adts.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csrda.adts.dao.InterfaceDao;
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
	public int UpdateCls(String modalCId, String modalCName, String modalCDesc, String modalCFather,
			String modalCMidware) {
		return interfaceDao.UpdateCls(modalCId, modalCName, modalCDesc, modalCFather, modalCMidware);
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

	
	







}
