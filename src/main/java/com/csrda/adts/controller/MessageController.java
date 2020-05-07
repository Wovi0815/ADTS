package com.csrda.adts.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csrda.adts.dao.MessageDao;

@Controller
public class MessageController {

	@Autowired
	MessageDao messageDao;
	
	@RequestMapping("/messageManager")
	public String messageManager() {
		return "messageManager";
	}
	
	@ResponseBody
	@RequestMapping("/qryMessage")
	public List<Map<String, Object>> qryMessage(String mesType){
		return messageDao.qryMessage(mesType);
	}
	
}
