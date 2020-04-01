package com.csrda.adts.pojo;

public class FourthLevelTitle extends Title {
	public Message message;
	public Method method;
	public ObjectDatas objectDatas;
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
}
