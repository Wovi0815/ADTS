package com.csrda.adts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.csrda.adts.dao.OtherDataDao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OtherDataServiceImpl implements OtherDataService{

	@Autowired
	OtherDataDao otherDataDao;
	
	@Override
	@Transactional
	public String addStruct(String typId, String typName, String typSize, String typDesc, String memList) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String, Object>> memData = mapper.readValue(memList, new TypeReference<List<Map<String, Object>>>(){});
			if(Integer.valueOf(otherDataDao.qryTypeRepeat(typId).get(0).get("num").toString())>0) {
				//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return "TypRep";
			}
			Map<String, String> typeData=new HashMap<String, String>();
			typeData.put("typId", typId);
			typeData.put("typName", typName);
			typeData.put("typAttr", "struct");
			typeData.put("typSize", typSize);
			typeData.put("typDesc", typDesc);
			otherDataDao.saveBasicDataType(typeData);

			for (int i = 0; i < memData.size(); i++) {
				if(Integer.valueOf(otherDataDao.qryStructMemRep(typId, memData.get(i).get("memId").toString()).get(0).get("num").toString())>0) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return "structRep";
				}
				memData.get(i).put("memStruct", typId);
				otherDataDao.addStructMem(memData.get(i));
			}
			return "success";
		}
		catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "fault";
		}
	}

	@Transactional
	@Override
	public String updateStruct(String typId, String typName, String typSize, String typDesc, String memList) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String, Object>> memData = mapper.readValue(memList, new TypeReference<List<Map<String, Object>>>(){});
			Map<String, String> typeData=new HashMap<String, String>();
			typeData.put("typId", typId);
			typeData.put("typName", typName);
			typeData.put("typAttr", "struct");
			typeData.put("typSize", typSize);
			typeData.put("typDesc", typDesc);
			otherDataDao.updateBasicDataType(typeData);
			otherDataDao.delStructMem(typId);
			for (int i = 0; i < memData.size(); i++) {
				if(Integer.valueOf(otherDataDao.qryStructMemRep(typId, memData.get(i).get("memId").toString()).get(0).get("num").toString())>0) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return "structRep";
				}
				memData.get(i).put("memStruct", typId);
				otherDataDao.addStructMem(memData.get(i));
			}
			return "success";
		}
		catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "fault";
		}
	}

	@Override
	public List<Map<String, Object>> qryDataType() {
		return otherDataDao.qryDataType();
	}

	@Override
	public int saveBasicDataType(Map<String, String> typeData) {
		return otherDataDao.saveBasicDataType(typeData);
	}

	@Override
	public int updateBasicDataType(Map<String, String> typeData) {
		return otherDataDao.updateBasicDataType(typeData);
	}

	@Override
	public int delBasicDataType(String typId) {
		return otherDataDao.delBasicDataType(typId);
	}

	@Override
	public Map<String, Object> qryDetailBasicDataType(String typId) {
		return otherDataDao.qryDetailBasicDataType(typId);
	}

	@Override
	public List<Map<String, Object>> qryTypeRepeat(String typId) {
		return otherDataDao.qryTypeRepeat(typId);
	}

	@Override
	public List<Map<String, Object>> qryStructMemRep(String struct, String memId) {
		return otherDataDao.qryStructMemRep(struct, memId);
	}

	@Override
	public int addStructMem(Map<String, Object> memData) {
		return otherDataDao.addStructMem(memData);
	}

	@Override
	public int delStructMem(String typId) {
		return otherDataDao.delStructMem(typId);
	}

	@Override
	public List<Map<String, Object>> qryStructMem(String typId) {
		return otherDataDao.qryStructMem(typId);
	}

	@Override
	public int InsertMid(String modMidId, String modMidName, String modMidDesc) {
		return otherDataDao.InsertMid(modMidId, modMidName, modMidDesc);
	}

	@Override
	public Map<String, Object> qryMidByMidId(String midId) {
		return otherDataDao.qryMidByMidId(midId);
	}

	@Override
	public int UpdateMid(String modMidId, String modMidName, String modMidDesc) {
		return otherDataDao.UpdateMid(modMidId, modMidName, modMidDesc);
	}

	@Override
	public int deleteMid(String midId) {
		return otherDataDao.deleteMid(midId);
	}

	@Override
	public List<Map<String, Object>> qryAllModule() {
		return otherDataDao.qryAllModule();
	}
	
	
	
	
	

}
