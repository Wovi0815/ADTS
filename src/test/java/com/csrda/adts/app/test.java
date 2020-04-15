package com.csrda.adts.app;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.csrda.adts.dao.WordExportDao;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;



@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
	Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	WordExportDao wordExportDao;
	@Test
	public void ceshi() {
//		logger.debug("1111");
//		System.out.println("111");
//		System.out.println(wordExportDao.qryStruct().toString());
//		
//		System.out.println("111"+(char)13+1111);
		System.out.println(wordExportDao.qryStructMem("student"));
	}
}
