package com.csrda.adts.app;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.csrda.adts.dao.WordExportDao;



@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
	Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	WordExportDao wordExportDao;
	

	@Test
	public void ceshi() {

	}
}
