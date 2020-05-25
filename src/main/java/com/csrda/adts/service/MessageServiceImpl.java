package com.csrda.adts.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.csrda.adts.dao.InterfaceDao;
import com.csrda.adts.dao.MessageDao;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	public int InsertMesData(String mesId, String dataRange, String dataLong,String dataName, String dataDesc, String dataType) {
		return  messageDao.InsertMesData(mesId, dataRange, dataLong,dataName, dataDesc, dataType);
	}


	@Override
	@Transactional
	public String addMesAndData(String mesMap, String mesDataMap) {
		try {
		ObjectMapper mapper = new ObjectMapper();   
		List<Map<String, Object>> mMap;
		mMap = mapper.readValue(mesMap, new TypeReference<List<Map<String, Object>>>(){});
		String mesId=mMap.get(0).get("imesId").toString();
		String mesName=mMap.get(0).get("imesName").toString();
		String mesDesc=mMap.get(0).get("imesDesc").toString();
		String mesRemark=mMap.get(0).get("imesRemark").toString();
		String mesSource=mMap.get(0).get("imesSource").toString();
		String mesDestination=mMap.get(0).get("imesDestination").toString();
		String mesID=mMap.get(0).get("imesID").toString();
		String mesFunId=mMap.get(0).get("imesFunId").toString();
		String mesTyp=mMap.get(0).get("imesTyp").toString();
		Map<String, Object> map = messageDao.qryMesDetail(mesId,mesTyp);
		if(map!=null) {
			System.out.println("!!!!!!!!!!!!Exist");
			return "Exist";
		}else {
			//插入报文
			int mresult = messageDao.InsertMes(mesId, mesName, mesDesc, 
					mesRemark, mesSource, mesDestination,
					mesID, mesFunId, mesTyp);
			if(mresult!=1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				System.out.println("!!!!!!!!!!!!InsertMesFail");
				return "InsertMesFail";
			}
			
			String mId =messageDao.qryMesDetail(mesId,mesTyp).get("mes_id").toString();
			List<Map<String, Object>> dMap = mapper.readValue(mesDataMap, new TypeReference<List<Map<String, Object>>>(){});
			for(int i=0;i<dMap.size();i++) {
				String  dataRange = dMap.get(i).get("dataRange").toString();
				String  dataLong = dMap.get(i).get("length").toString();
				String  dataName = dMap.get(i).get("dataName").toString();
				String  dataDesc = dMap.get(i).get("dataDesc").toString();
				String  dataType = dMap.get(i).get("dataType").toString();
				//查询数据范围是否合规
				List<Map<String, Object>> list = messageDao.qryMesDataDetail(mId);
				System.out.println("!!!!!!!!!!!"+list);
				for(int j=0;j<list.size();j++) {
					String dtRange = list.get(i-1).get("mes_data_range").toString();
					System.out.println("!!"+dtRange);
					String[]  lastlist= dtRange.split("~");
					String[]  nowlist= dataRange.split("~");
					if(lastlist.length!=0 && nowlist.length!=0) {
						int a =Integer.valueOf(lastlist[1]);
						int b =Integer.valueOf(nowlist[0]);
						if(a>=b) {
							System.out.println("!!!!!!!!!!!!DataRangeERROR");
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							return "DataRangeERROR";
						}
						
					}else if(lastlist.length!=0 && nowlist.length==0) {
						int a =Integer.valueOf(lastlist[1]);
						int b =Integer.valueOf(dataRange);
						if(a>=b) {
							System.out.println("!!!!!!!!!!!!DataRangeERROR");
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							return "DataRangeERROR";
						}
					}else if(lastlist.length==0 && nowlist.length!=0) {
						int a =Integer.valueOf(dtRange);
						int b =Integer.valueOf(nowlist[0]);
						if(a>=b) {
							System.out.println("!!!!!!!!!!!!DataRangeERROR");
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							return "DataRangeERROR";
						}
					}else if(lastlist.length==0 && nowlist.length==0) {
						int a =Integer.valueOf(dtRange);
						int b =Integer.valueOf(dataRange);
						if(a>=b) {
							System.out.println("!!!!!!!!!!!!DataRangeERROR");
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							return "DataRangeERROR";
						}
					}
				}
				
				int dresult = messageDao.InsertMesData(mesID, dataRange,dataLong, dataName,
						dataDesc, dataType);
				if(dresult!=1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					System.out.println("!!!!!!!!!!!!InsertMesDataFail");
					return "InsertMesDataFail";
				}

			}
		}
		
		
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "SUCCESS";
	}


	


	


	





	

	
	
}
