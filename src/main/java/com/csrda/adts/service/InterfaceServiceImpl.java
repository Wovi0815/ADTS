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
	public List<Map<String, Object>> qryClsFather() {
		
		return interfaceDao.qryClsFather();
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

	
	







}
