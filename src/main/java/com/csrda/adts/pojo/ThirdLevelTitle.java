package com.csrda.adts.pojo;

import java.util.List;

public class ThirdLevelTitle extends Title {
	public Message message;
	public Method method;
	public ObjectDatas objectDatas;
	public List<FourthLevelTitle> titles;
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public ObjectDatas getObjectDatas() {
		return objectDatas;
	}
	public void setObjectDatas(ObjectDatas objectDatas) {
		this.objectDatas = objectDatas;
	}
	public List<FourthLevelTitle> getTitles() {
		return titles;
	}
	public void setTitles(List<FourthLevelTitle> titles) {
		this.titles = titles;
	}
	
}
