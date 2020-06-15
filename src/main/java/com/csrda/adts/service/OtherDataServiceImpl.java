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
	public String addStruct(String structId,String structName,String structSize,String structDesc,String memList){
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String, Object>> memData = mapper.readValue(memList, new TypeReference<List<Map<String, Object>>>(){});
			if(Integer.valueOf(otherDataDao.qryTypeRepeat(structId).get(0).get("num").toString())>0) {
				//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return "TypeRepeat";
			}
			Map<String, String> typeData=new HashMap<String, String>();
			typeData.put("typId", structId);
			typeData.put("typName", structName);
			typeData.put("typAttr", "struct");
			typeData.put("typSize", structSize);
			typeData.put("typDesc", structDesc);
			otherDataDao.saveBasicDataType(typeData);

			for (int i = 0; i < memData.size(); i++) {
				if(Integer.valueOf(otherDataDao.qryStructMemRep(structId, memData.get(i).get("memId").toString()).get(0).get("num").toString())>0) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return "structMemberRepeat";
				}
				memData.get(i).put("memStruct", structId);
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
	public int saveDataType(Map<String, String> typeData) {
		return otherDataDao.saveBasicDataType(typeData);
	}

	@Override
	public int updateDataType(Map<String, String> typeData) {
		return otherDataDao.updateBasicDataType(typeData);
	}

	@Override
	public int delDataType(String typId) {
		return otherDataDao.delBasicDataType(typId);
	}

	@Override
	public Map<String, Object> qryDetailDataType(String typId) {
		return otherDataDao.qryDetailDataType(typId);
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
