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
	public List<Map<String, Object>> qryMidwareClass(String midwareName) {
		return interfaceDao.qryMidwareClass(midwareName);
	}

}
