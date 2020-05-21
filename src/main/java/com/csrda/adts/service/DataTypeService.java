package com.csrda.adts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.csrda.adts.dao.TypeDataDao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DataTypeService implements DataTypeServiceImpl {

	@Autowired
	TypeDataDao typeDataDao;
	
	@Override
	@Transactional
	public String addStruct(String typId, String typName, String typSize, String typDesc, String memList) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String, Object>> memData = mapper.readValue(memList, new TypeReference<List<Map<String, Object>>>(){});
			if(Integer.valueOf(typeDataDao.qryRep(typId).get(0).get("num").toString())>0) {
				//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return "TypRep";
			}
			Map<String, String> typeData=new HashMap<String, String>();
			typeData.put("typId", typId);
			typeData.put("typName", typName);
			typeData.put("typAttr", "struct");
			typeData.put("typSize", typSize);
			typeData.put("typDesc", typDesc);
			typeDataDao.saveBasicDataType(typeData);

			for (int i = 0; i < memData.size(); i++) {
				if(Integer.valueOf(typeDataDao.qryStructMemRep(typId, memData.get(i).get("memId").toString()).get(0).get("num").toString())>0) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return "structRep";
				}
				memData.get(i).put("memStruct", typId);
				typeDataDao.addStructMem(memData.get(i));
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
	public String updateStruct(String typId, String typName, String typSize, String typDesc, String memList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	

}
