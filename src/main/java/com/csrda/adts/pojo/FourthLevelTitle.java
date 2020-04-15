package com.csrda.adts.pojo;

public class FourthLevelTitle extends Title {
	public Message message;
	public Method method;
	public ObjectData objectData;
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
		this.objectData = objectDatas;
	}
}
