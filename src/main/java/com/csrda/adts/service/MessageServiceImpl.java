package com.csrda.adts.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csrda.adts.dao.InterfaceDao;
import com.csrda.adts.dao.MessageDao;
@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
	private MessageDao messageDao;

	@Override
	public List<Map<String, Object>> QueryMessageTyp() {
		
		return messageDao.QueryMessageTyp();
	}
	

	@Override
	public List<Map<String, Object>> qryMessage(String mesType) {
		return messageDao.qryMessage(mesType);
	}


	@Override
	public List<Map<String, Object>> qryModuleKind() {
		return messageDao.qryModuleKind();
	}


	@Override
	public List<Map<String, Object>> qryMesBySelect(String dataSource, String dataDestination, String mesType) {
		return messageDao.qryMesBySelect(dataSource, dataDestination, mesType);
	}



	

	
	
}
