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
	public List<Map<String, Object>> qryMesBySelect(List<Map<String,Object>> sourceList, List<Map<String,Object>> destinationList, String mesType) {
		return messageDao.qryMesBySelect(sourceList, destinationList, mesType);
	}


	@Override
	public List<Map<String, Object>> qryMesByDtSource(List<Map<String,Object>> sourceList, String mesType) {
		return messageDao.qryMesByDtSource(sourceList, mesType);
	}


	@Override
	public List<Map<String, Object>> qryMesByDtDestination(List<Map<String,Object>> destinationList, String mesType) {
		return messageDao.qryMesByDtDestination(destinationList, mesType);
	}


	





	

	
	
}
