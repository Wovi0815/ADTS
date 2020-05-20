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


	@Override
	public Map<String, Object> qryMesDetail(String mesId,String mesTyp) {
		return messageDao.qryMesDetail(mesId,mesTyp);
	}


	@Override
	public List<Map<String, Object>> qryMesDataDetail(String mesId) {
		return messageDao.qryMesDataDetail(mesId);
	}


	@Override
	public int UpdateMes(String modalmesId, String modalmesName, String modalmesDesc, String modalmesRemark,
			String modalmesFunNum, String modalmesIDNum, String modalmesTyp, String modalmesSource,
			String modalmesDestination) {
		
		return messageDao.UpdateMes(modalmesId, modalmesName, modalmesDesc, 
				modalmesRemark, modalmesFunNum, modalmesIDNum,
				modalmesTyp, modalmesSource, modalmesDestination);
	}


	@Override
	public int InsertMes(String mesId, String mesName, String mesDesc, String mesRemark, String mesSource,
			String mesDestination, String mesID, String mesFunId, String mesTyp) {
		
		return messageDao.InsertMes(mesId, mesName, mesDesc, 
				mesRemark, mesSource, mesDestination,
				mesID, mesFunId, mesTyp);
	}


	@Override
	public int InsertMesData(String mesId, String dataRange, String dataName, String dataDesc, String dataType) {
		return  messageDao.InsertMesData(mesId, dataRange, dataName, dataDesc, dataType);
	}


	


	


	





	

	
	
}
