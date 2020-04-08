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
	public List<Map<String, Object>> qryMidwareClass(String midwareName) {
		return interfaceDao.qryMidwareClass(midwareName);
	}



	@Override
	public List<Map<String, Object>> qryMidClsByFather(String cfather, String midwareName) {
		
		return interfaceDao.qryMidClsByFather(cfather, midwareName);
	}



	@Override
	public Map<String, Object> qryMidClsByCId(String cId, String midwareName) {
		
		return interfaceDao.qryMidClsByCId(cId, midwareName);
	}








}
