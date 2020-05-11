package com.csrda.adts.app;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.csrda.adts.dao.TypeDataDao;
import com.csrda.adts.dao.WordExportDao;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;



@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
	Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	WordExportDao wordExportDao;
	
	@Autowired
    TypeDataDao typeDataDao;
	@Test
	public void ceshi() {
//		logger.debug("1111");
//		System.out.println("111");
//		System.out.println(wordExportDao.qryStruct().toString());
//		
//		System.out.println("111"+(char)13+1111);
		//System.out.println(wordExportDao.qryStructMem("student"));
		//System.out.println(wordExportDao.qryMesMem("openCom1"));
		Map<String, String> typeData=new HashMap<String, String>();
		typeData.put("typId", "long");
		typeData.put("typName", "long");
		typeData.put("typAttr", "basic");
		typeData.put("typSize", "4");
		typeData.put("typDesc", "长整形");
		
		typeDataDao.saveBasicDataType(typeData);
	}
}
