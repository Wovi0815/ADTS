package com.csrda.adts.pojo;

import java.util.List;

public class ThirdLevelTitle extends Title {
	public Message message;
	public Method method;
	public ObjectData objectData;
	public List<FourthLevelTitle> titles;
	public List<DataType> dataTypes;
	public List<DataType> getDataTypes() {
		return dataTypes;
	}
	public void setDataTypes(List<DataType> dataTypes) {
		this.dataTypes = dataTypes;
	}
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
	public ObjectData getObjectDatas() {
		return objectData;
	}
	public void setObjectDatas(ObjectData objectDatas) {
		this.objectData = objectData;
	}
	public List<FourthLevelTitle> getTitles() {
		return titles;
	}
	public void setTitles(List<FourthLevelTitle> titles) {
		this.titles = titles;
	}
	
}
